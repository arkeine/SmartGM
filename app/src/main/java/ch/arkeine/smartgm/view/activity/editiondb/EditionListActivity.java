package ch.arkeine.smartgm.view.activity.editiondb;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.Game;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.model.helper.IdentifiedDataObject;
import ch.arkeine.smartgm.presenter.edition.EditionListPresenter;
import ch.arkeine.smartgm.view.adapter.SimpleDataAdapter;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActionBarActivity;
import static ch.arkeine.smartgm.presenter.edition.EditionListPresenter.DATA_TYPE;

@RequiresPresenter(EditionListPresenter.class)
public class EditionListActivity extends NucleusActionBarActivity<EditionListPresenter>
        implements NavigationView.OnNavigationItemSelectedListener {

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edition_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listData = (ListView) findViewById(R.id.list_multi_content);
        listData.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeContent((IdentifiedDataObject) listData.getItemAtPosition(position));
                return true;
            }
        });
        listData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editContent((IdentifiedDataObject) listData.getItemAtPosition(position));
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_edition_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            addContent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_universe) {
            getPresenter().setDataType(DATA_TYPE.UNIVERSE);
        } else if (id == R.id.nav_game) {
            getPresenter().setDataType(DATA_TYPE.GAME);
        } else if (id == R.id.nav_dice) {
            getPresenter().setDataType(DATA_TYPE.DICE);
        } else if (id == R.id.nav_table) {
            getPresenter().setDataType(DATA_TYPE.TABLE);
        } else if (id == R.id.nav_wiki) {
            getPresenter().setDataType(DATA_TYPE.WIKI);
        } else if (id == R.id.nav_timeline) {
            getPresenter().setDataType(DATA_TYPE.TIME_LINE);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setListContent(List list) {
        ArrayAdapter adapter = new SimpleDataAdapter(this, list);
        listData.setAdapter(adapter);
    }


    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void addContent() {
        Intent intent = createIntent();
        if(intent != null) {
            startActivity(intent);
            getPresenter().externalModificationOnDataSignal();
        }
    }

    private void editContent(final IdentifiedDataObject itemAtPosition) {
        Intent intent = createIntent();

        if (intent != null) {
            intent.putExtra(Constants.KEY_ID_CONTENT, itemAtPosition.getUniqueId());
            startActivity(intent);
            getPresenter().externalModificationOnDataSignal();
        }
    }

    private void removeContent(final IdentifiedDataObject itemAtPosition) {

        //Create a warning before delete an item

        AlertDialog.Builder builder = new AlertDialog.Builder(EditionListActivity.this);
        builder.setTitle(R.string.act_edition_popup_remove_title)
                .setMessage(R.string.act_edition_popup_remove_content)
                .setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //Create a second warning for the item with on delete cascade

                        if (itemAtPosition instanceof Universe ||
                                itemAtPosition instanceof Game) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(
                                    EditionListActivity.this);
                            builder.setTitle(R.string.act_edition_popup_cascade_title)
                                    .setMessage(R.string.act_edition_popup_cascade_content)
                                    .setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            getPresenter().remove(itemAtPosition);
                                        }
                                    })
                                    .setNegativeButton(R.string.button_no, null);
                            builder.show();
                        } else {
                            getPresenter().remove(itemAtPosition);
                        }
                    }
                })
                .setNegativeButton(R.string.button_no, null);
        builder.show();
    }


    private Intent createIntent() {
        Intent intent = null;

        // Select the activity for the data
        switch (getPresenter().getDataType()) {
            case UNIVERSE:
                intent = new Intent(this, UniverseEditionActivity.class);
                break;
            case GAME:
                intent = new Intent(this, GameEditionActivity.class);
                break;
            case DICE:
                intent = new Intent(this, DiceEditionActivity.class);
                break;
        }
        return intent;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private ListView listData;
}

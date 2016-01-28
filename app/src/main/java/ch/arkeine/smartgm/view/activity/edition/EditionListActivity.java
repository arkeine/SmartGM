package ch.arkeine.smartgm.view.activity.edition;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.handler.IdentifiedDataObject;
import ch.arkeine.smartgm.presenter.edition.EditionListPresenter;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActionBarActivity;

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
        getMenuInflater().inflate(R.menu.edition_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add) {
            Intent intent = createIntent();
            if(intent != null) startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_universe) {
            currentDataType = DataType.UNIVERSE;
        } else if (id == R.id.nav_game) {
            currentDataType = DataType.GAME;
        } else if (id == R.id.nav_dice) {
            currentDataType = DataType.DICE;
        } else if (id == R.id.nav_table) {
            currentDataType = DataType.TABLE;
        } else if (id == R.id.nav_wiki) {
            currentDataType = DataType.WIKI;
        } else if (id == R.id.nav_timeline) {
            currentDataType = DataType.TIME_LINE;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    /**
     * Edit a tuple from the data
     */
    private void editContent(IdentifiedDataObject itemAtPosition) {
        Intent intent = createIntent();

        if (intent != null) {
            // Start activity
            intent.putExtra(Constants.KEY_ID_CONTENT, itemAtPosition.getUniqueId());
            startActivity(intent);
        }
    }

    private Intent createIntent() {
        Intent intent = null;

        // Select the activity for the data
        switch (currentDataType) {
            case UNIVERSE:
                intent = new Intent(this, UniverseEditionActivity.class);
                break;
        }
        return intent;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private DataType currentDataType;
    private enum DataType {UNIVERSE, DICE, GAME, TABLE, TIME_LINE, WIKI};
}

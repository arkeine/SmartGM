package ch.arkeine.smartgm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.MainFilterManager;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.database.daoimplementation.CharacterDAO;
import ch.arkeine.smartgm.model.database.daoimplementation.DiceDAO;
import ch.arkeine.smartgm.model.database.daoimplementation.GameDAO;
import ch.arkeine.smartgm.model.database.daoimplementation.StatisticDAO;
import ch.arkeine.smartgm.model.database.daoimplementation.TableDAO;
import ch.arkeine.smartgm.model.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.presenter.MainActivityPresenter;
import ch.arkeine.smartgm.presenter.StatisticSettingActivityPresenter;
import ch.arkeine.smartgm.view.adapter.CharacterAdapter;
import ch.arkeine.smartgm.view.adapter.DiceAdapter;
import ch.arkeine.smartgm.view.adapter.GameAdapter;
import ch.arkeine.smartgm.view.adapter.StatisticAdapter;
import ch.arkeine.smartgm.view.adapter.TableAdapter;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        MainFilterManager.GameFilterChangeListener,
        MainFilterManager.CharacterFilterChangeListener {

    /* ============================================ */
    // OVERRRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        listMultiContent = (ListView) findViewById(R.id.list_multi_content);
        listMultiContent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                displayItemOption(position);
                return false;
            }
        });
        listMultiContent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                useContent((ObjectWithIdentifier) listMultiContent.getItemAtPosition(position));
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        SmartGmApplication app = (SmartGmApplication) getApplication();
        filter = app.getFilterManager();
        filter.setOnCharacterFilterChangeListener(this);
        filter.setOnGameFilterChangeListener(this);

        if (presenter == null) {
            presenter = new MainActivityPresenter(new GameDAO(this), new DiceDAO(this),
                    new TableDAO(this), new CharacterDAO(this), new StatisticDAO(this), filter);
        }
        filter.setCharacterId(null);
        filter.setGameId(null);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Not in ONCREATE cause because the swap between activity lose data
        presenter.onTakeView(this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            displaySoon();
            return true;
        } else if (id == R.id.action_add) {
            addNewContent();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_game) {
            presenter.setType(MainActivityPresenter.DATA_TYPE.GAME);
        } else if (id == R.id.nav_dice) {
            presenter.setType(MainActivityPresenter.DATA_TYPE.DICE);
        } else if (id == R.id.nav_table) {
            presenter.setType(MainActivityPresenter.DATA_TYPE.TABLE);
        } else if (id == R.id.nav_wiki) {
            displaySoon();
        } else if (id == R.id.nav_inventory) {
            displaySoon();
        } else if (id == R.id.nav_statistic) {
            presenter.setType(MainActivityPresenter.DATA_TYPE.STATISTIC);
        } else if (id == R.id.nav_character) {
            presenter.setType(MainActivityPresenter.DATA_TYPE.CHARACTER);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onCharacterSelectionChanged(Long characterId) {
        // Update the drawer menu
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.setGroupVisible(R.id.sub_options_character, filter.isCharacterSelected());
        if(filter.isCharacterSelected())
        {
            String title = getResources().getString(R.string.drawer_character_separator) +
                    " : " +presenter.getCharacterName(characterId);
            MenuItem item = menu.getItem(2);
            item.setTitle(title);
        }
    }

    @Override
    public void onGameSelectionChanged(Long gameId) {
        // Update the drawer menu
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menu = navigationView.getMenu();
        menu.setGroupVisible(R.id.sub_options_game, filter.isGameSelected());
        if(filter.isGameSelected())
        {
            String title = getResources().getString(R.string.drawer_world_separator) +
                    " : " +presenter.getGameName(gameId);
            MenuItem item = menu.getItem(1);
            item.setTitle(title);
        }
    }

	/* ============================================ */
    // DATA MANIPULATION
    /* ============================================ */

    /**
     * Load data into the list with the right adapter
     *
     * @param type Type of data
     * @param list List of data
     */
    public void setListContent(MainActivityPresenter.DATA_TYPE type, List list) {
        //Set the adapter relative to the type of item
        ArrayAdapter adapter = null;
        switch (type) {
            case GAME:
                adapter = new GameAdapter(this, list);
                setTitle(R.string.func_game_title);
                break;
            case DICE:
                adapter = new DiceAdapter(this, list);
                setTitle(R.string.func_dice_title);
                break;
            case TABLE:
                adapter = new TableAdapter(this, list);
                setTitle(R.string.func_table_title);
                break;
            case CHARACTER:
                adapter = new CharacterAdapter(this, list);
                setTitle(R.string.func_character_title);
                break;
            case STATISTIC:
                adapter = new StatisticAdapter(this, list);
                setTitle(R.string.func_statistic_title);
                break;
        }

        listMultiContent.setAdapter(adapter);
    }

    /**
     * Add a new tuple to the data
     */
    private void addNewContent() {
        Intent intent = null;

        // Select the activity for the data
        switch (presenter.getType()) {
            case GAME:
                intent = new Intent(MainActivity.this, GameSettingActivity.class);
                break;
            case DICE:
                intent = new Intent(MainActivity.this, DiceSettingActivity.class);
                break;
            case TABLE:
                intent = new Intent(MainActivity.this, TableSettingActivity.class);
                break;
            case CHARACTER:
                intent = new Intent(MainActivity.this, CharacterSettingActivity.class);
                break;
            case STATISTIC:
                intent = new Intent(MainActivity.this, StatisticSettingActivity.class);
                break;

            default:
                return;
        }

        // Start activity
        intent.putExtra(Constants.MODE_TITLE, Constants.MODE_CREATE);
        startActivity(intent);
    }

    /**
     * Remove a tuple from the data
     */
    private void removeContent(ObjectWithIdentifier itemAtPosition) {
        presenter.remove(itemAtPosition);
    }

    /**
     * Edit a tuple from the data
     */
    private void editContent(ObjectWithIdentifier itemAtPosition) {
        Intent intent = null;

        // Select the activity for the data
        switch (presenter.getType()) {
            case GAME:
                intent = new Intent(MainActivity.this, GameSettingActivity.class);
                break;
            case DICE:
                intent = new Intent(MainActivity.this, DiceSettingActivity.class);
                break;
            case TABLE:
                intent = new Intent(MainActivity.this, TableSettingActivity.class);
                break;
            case CHARACTER:
                intent = new Intent(MainActivity.this, CharacterSettingActivity.class);
                break;
            case STATISTIC:
                intent = new Intent(MainActivity.this, StatisticSettingActivity.class);
                break;

            default:
                return;
        }

        // Start activity
        intent.putExtra(Constants.MODE_TITLE, Constants.MODE_MODIFY);
        intent.putExtra(Constants.ID_TITLE, itemAtPosition.getId());
        startActivity(intent);
    }

    /**
     * Use a tuple from the data
     */
    private void useContent(ObjectWithIdentifier itemAtPosition) {
        Intent intent = null;

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Select the activity for the data
        switch (presenter.getType()) {
            case GAME:
                filter.setGameId(itemAtPosition.getId());

                drawer.openDrawer(GravityCompat.START);
                break;
            case CHARACTER:
                filter.setCharacterId(itemAtPosition.getId());

                drawer.openDrawer(GravityCompat.START);
                break;
        }
        if (intent == null) return;

        // Start activity
        intent.putExtra(Constants.MODE_TITLE, Constants.MODE_USE);
        intent.putExtra(Constants.ID_TITLE, itemAtPosition.getId());
        startActivity(intent);
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    /**
     * Display the edit or delete option on a tuple
     *
     * @param position the item's position in the list
     */
    private void displayItemOption(final int position) {

        // Create a dialog with the item the option list (delete and edit)
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.activity_main_dialog_edit_delete_title)
                .setItems(R.array.action_list_main_activity, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Add an action on user choice
                        switch (which) {
                            // Edit
                            case 0:
                                editContent((ObjectWithIdentifier) listMultiContent.getItemAtPosition(position));
                                break;
                            // Delete
                            case 1:
                                removeContent((ObjectWithIdentifier) listMultiContent.getItemAtPosition(position));
                                break;
                        }
                    }
                });
        builder.show();
    }

    private void displaySoon() {
        Toast toast = Toast.makeText(this, R.string.message_soon, Toast.LENGTH_SHORT);
        toast.show();
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static MainActivityPresenter presenter;
    private MainFilterManager filter;
    private ListView listMultiContent;
}

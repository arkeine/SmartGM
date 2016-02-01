package ch.arkeine.smartgm.view.activity.editiondb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.presenter.edition.GameEditionPresenter;
import ch.arkeine.smartgm.view.fragment.DataEditionButtons;
import ch.arkeine.smartgm.view.fragment.ForeignKeySelector;
import ch.arkeine.smartgm.view.fragment.WikiContent;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActionBarActivity;

@RequiresPresenter(GameEditionPresenter.class)
public class GameEditionActivity extends NucleusActionBarActivity<GameEditionPresenter>
    implements DataEditionButtons.OnDataEditionButtonClickedListener,
        ForeignKeySelector.OnFragmentInteractionListener {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edition);

        editTextTitle = (EditText) findViewById(R.id.name);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        foreignKeySelectorUniverse = (ForeignKeySelector) fragmentManager.findFragmentById(R.id.selector);

        Intent intent = getIntent();
        id = intent.getLongExtra(Constants.KEY_ID_CONTENT, Constants.INVALID_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.fragment_wiki_page_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Constants.displaySoon(this);
            return true;
        } else if (id == R.id.action_edition) {
            //Switch to the listing edition activity
            Intent intent = new Intent(this, EditionListActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_game_selection) {
            //Switch to the listing edition activity
            Intent intent = new Intent(this, UniverseEditionActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onButtonSaveClicked() {
        saveToDatabase = true;
        finish();
    }

    @Override
    public void onButtonCancelClicked() {
        finish();
    }

    @Override
    public void onButtonReloadClicked() {
        getPresenter().reloadData();
    }

    @Override
    public void onButtonAddClicked() {
        Intent intent = new Intent(this, UniverseEditionActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected() {

    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public long getId() {
        return id;
    }

    public boolean isSaveToDatabase() {
        return saveToDatabase;
    }

    public String getName() {
        return editTextTitle.getText().toString();
    }

    public void setName(String name) {
        editTextTitle.setText(name);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Universe getUniverse() {
        return (Universe)foreignKeySelectorUniverse.getSelectedItem();
    }

    public void setUniverse(Universe current) {
        foreignKeySelectorUniverse.setSelectedItem(current);
    }

    public void setUniverseList(List<Universe> l) {
        foreignKeySelectorUniverse.setItemList(l);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    //input/output
    private long id;
    private String description;
    //tool
    private boolean saveToDatabase;
    //gui
    private EditText editTextTitle;
    private ForeignKeySelector foreignKeySelectorUniverse;

}

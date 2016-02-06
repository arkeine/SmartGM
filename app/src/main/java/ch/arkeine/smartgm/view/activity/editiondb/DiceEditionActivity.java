package ch.arkeine.smartgm.view.activity.editiondb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.Universe;
import ch.arkeine.smartgm.presenter.edition.GameEditionPresenter;
import ch.arkeine.smartgm.view.activity.DescriptionDisplayActivity;
import ch.arkeine.smartgm.view.fragment.DataEditionButtons;
import ch.arkeine.smartgm.view.fragment.ForeignKeySelector;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActionBarActivity;

import static ch.arkeine.smartgm.Constants.getOrDefault;

@RequiresPresenter(GameEditionPresenter.class)
public class DiceEditionActivity extends NucleusActionBarActivity<GameEditionPresenter>
    implements DataEditionButtons.OnDataEditionButtonClickedListener,
        ForeignKeySelector.OnFragmentInteractionListener {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_edition);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        if (id == R.id.action_edit_wiki_page) {
            Intent intent = new Intent(this, DescriptionDisplayActivity.class);
            intent.putExtra(Constants.KEY_DESCRIPTION_CONTENT, getDescription());
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = getOrDefault(intent.getStringExtra(Constants.KEY_DESCRIPTION_CONTENT), "");
                getPresenter().externalDescriptionUpdate(result);
            }
        }
    }

    @Override
    public void onButtonSaveClicked() {

        //Missing universe : not save
        if(foreignKeySelectorUniverse.getSelectedItem() == null){
            AlertDialog.Builder builder = new AlertDialog.Builder(DiceEditionActivity.this);
            builder.setTitle(R.string.act_game_edition_popup_cascade_title)
                    .setMessage(R.string.act_game_edition_popup_cascade_content)
                    .setPositiveButton(R.string.button_yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            saveToDatabase = true;
                            finish();
                        }
                    })
                    .setNegativeButton(R.string.button_no, null);
            builder.show();
        }else{
            saveToDatabase = true;
            finish();
        }
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

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
import ch.arkeine.smartgm.presenter.edition.DiceEditionPresenter;
import ch.arkeine.smartgm.presenter.edition.GameEditionPresenter;
import ch.arkeine.smartgm.view.activity.DescriptionDisplayActivity;
import ch.arkeine.smartgm.view.component.NumberPicker;
import ch.arkeine.smartgm.view.fragment.DataEditionButtons;
import ch.arkeine.smartgm.view.fragment.ForeignKeySelector;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActionBarActivity;

import static ch.arkeine.smartgm.Constants.getOrDefault;

@RequiresPresenter(DiceEditionPresenter.class)
public class DiceEditionActivity extends NucleusActionBarActivity<DiceEditionPresenter>
    implements DataEditionButtons.OnDataEditionButtonClickedListener,
        ForeignKeySelector.OnFragmentInteractionListener {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice_edition);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pickerNbFace = (NumberPicker) findViewById(R.id.picker_face);
        pickerNbDice = (NumberPicker) findViewById(R.id.picker_dice);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        foreignKeySelectorUniverse = (ForeignKeySelector) fragmentManager.findFragmentById(R.id.selector);

        Intent intent = getIntent();
        id = intent.getLongExtra(Constants.KEY_ID_CONTENT, Constants.INVALID_ID);
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
    public void onItemSelected() {}

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public long getId() {
        return id;
    }

    public boolean isSaveToDatabase() {
        return saveToDatabase;
    }

    public int getNbFace() {
        return pickerNbFace.getCurrent();
    }

    public void setNbFace(int n) {
        pickerNbFace.setCurrent(n);
    }

    public int getNbDice() {
        return pickerNbDice.getCurrent();
    }

    public void setNbDice(int n) {
        pickerNbDice.setCurrent(n);
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
    //tool
    private boolean saveToDatabase;
    //gui
    private NumberPicker pickerNbFace;
    private NumberPicker pickerNbDice;
    private ForeignKeySelector foreignKeySelectorUniverse;

}

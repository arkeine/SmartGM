package ch.arkeine.smartgm.view.activity.editiondb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.EditText;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.presenter.edition.UniverseEditionPresenter;
import ch.arkeine.smartgm.view.fragment.DataEditionButtons;
import ch.arkeine.smartgm.view.fragment.WikiContent;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusActionBarActivity;

@RequiresPresenter(UniverseEditionPresenter.class)
public class UniverseEditionActivity extends NucleusActionBarActivity<UniverseEditionPresenter>
    implements DataEditionButtons.OnDataEditionButtonClickedListener{

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_universe_edition);

        editTextTitle = (EditText) findViewById(R.id.name);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        wikiContentDescription = (WikiContent) fragmentManager.findFragmentById(R.id.description);

        Intent intent = getIntent();
        id = intent.getLongExtra(Constants.KEY_ID_CONTENT, Constants.INVALID_ID);
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
        return wikiContentDescription.getContent();
    }

    public void setDescription(String description) {
        wikiContentDescription.setContent(description);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    //input
    private long id;
    //tool
    private boolean saveToDatabase;
    //gui
    private EditText editTextTitle;
    private WikiContent wikiContentDescription;
}

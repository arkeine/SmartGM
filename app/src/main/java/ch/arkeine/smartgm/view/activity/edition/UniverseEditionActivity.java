package ch.arkeine.smartgm.view.activity.edition;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
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

        // Get components from view
        editTextTitle = (EditText) findViewById(R.id.name);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        wikiContentDescription = (WikiContent) fragmentManager.findFragmentById(R.id.description);

        // Get the parameter from the intent
        Intent intent = getIntent();
        long id = intent.getLongExtra(Constants.KEY_ID_CONTENT, Constants.INVALID_ID);
        getPresenter().loadUniverse(id);

        Log.d("PRESENTER TEST", "ACTIVITY onCreate");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("PRESENTER TEST", "ACTIVITY onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("PRESENTER TEST", "ACTIVITY onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("PRESENTER TEST", "ACTIVITY onDestroy");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("PRESENTER TEST", "ACTIVITY onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("PRESENTER TEST", "ACTIVITY onStop");
    }

    @Override
    public void onButtonSaveClicked() {
        getPresenter().saveUniverse();
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

    //gui
    private EditText editTextTitle;
    private WikiContent wikiContentDescription;
}

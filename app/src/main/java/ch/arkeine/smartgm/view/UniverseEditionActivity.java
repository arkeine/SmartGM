package ch.arkeine.smartgm.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.database.dal.UniversePersistence;
import ch.arkeine.smartgm.presenter.UniverseEditionPresenter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

public class UniverseEditionActivity extends DescribableEditionActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_universe_edition);
        super.onCreate(savedInstanceState);

        // Get components from view
        editTextTitle = (EditText) findViewById(R.id.name);

        // Create the presenter and load data
        if (presenter == null)
            presenter = new UniverseEditionPresenter(new UniversePersistence(this));
        presenter.onTakeView(this);

        // Get the parameter from the intent
        Intent intent = getIntent();
        String mode = getOrDefault(intent.getStringExtra(Constants.KEY_MODE_CONTENT), Constants.MODE_CREATE);

        if (Constants.MODE_MODIFY.equals(mode)) {
            long id = intent.getLongExtra(Constants.KEY_ID_CONTENT, Constants.INVALID_ID);
            presenter.loadObjectDAO(id);
        }
    }

    @Override
    protected boolean onSave() {
        String title = editTextTitle.getText().toString();

        if (title.isEmpty()) {
            confirmeQuitNoSave();
            return false;
        }
        else
        {
            presenter.setName(editTextTitle.getText().toString());
            presenter.saveObjectDAO();
            return true;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_NAME_CONTENT, editTextTitle.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editTextTitle.setText(savedInstanceState.getString(KEY_NAME_CONTENT));
    }

    @Override
    protected String getDescription(){
        return presenter.getDescription();
    }

    @Override
    protected void setDescription(String s){
        presenter.setDescription(s);
    }

    /* ============================================ */
    // DATA MANIPULATION
    /* ============================================ */

    public void setName(String s) {
        editTextTitle.setText(s);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static UniverseEditionPresenter presenter;
    private EditText editTextTitle;

    private static final String KEY_NAME_CONTENT = "NAME";
}

package ch.arkeine.smartgm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.database.dal.GamePersistence;
import ch.arkeine.smartgm.model.database.dal.UniversePersistence;
import ch.arkeine.smartgm.presenter.GameEditionPresenter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

public class GameEditionActivity extends DescribableEditionActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_game_edition);
        super.onCreate(savedInstanceState);

        // Get components from view
        editTextName = (EditText) findViewById(R.id.name);
        editTextNote = (EditText) findViewById(R.id.short_note);

        SmartGmApplication app = (SmartGmApplication) getApplication();

        // Create the presenter and load data
        if (presenter == null)
            presenter = new GameEditionPresenter(
                    new GamePersistence(this),
                    new UniversePersistence(this));
        presenter.onTakeView(this);

        // Get the parameter from the intent
        Intent intent = getIntent();
        String mode = getOrDefault(intent.getStringExtra(Constants.KEY_MODE_CONTENT), Constants.MODE_CREATE);

        if (Constants.MODE_MODIFY.equals(mode)) {
            long id = intent.getLongExtra(Constants.KEY_ID_CONTENT, Constants.INVALID_ID);
            presenter.loadObjectDAO(id);
        }else if (Constants.MODE_CREATE.equals(mode)) {
            presenter.setUniverseId(app.getFilterManager().getUniverseId());
        }
    }

    @Override
    protected void onSave() {
        String title = editTextName.getText().toString();

        if (title.isEmpty()) {
            //TODO
        }
        else
        {
            presenter.setName(editTextName.getText().toString());
            presenter.setNote(editTextNote.getText().toString());
            presenter.saveObjectDAO();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_NAME_BUNDLE, editTextName.getText().toString());
        outState.putString(KEY_NOTE_BUNDLE, editTextNote.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editTextName.setText(savedInstanceState.getString(KEY_NAME_BUNDLE));
        editTextNote.setText(savedInstanceState.getString(KEY_NOTE_BUNDLE));
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
        editTextName.setText(s);
    }

    public void setNote(String s)
    {
        editTextNote.setText(s);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static GameEditionPresenter presenter;
    private EditText editTextName;
    private EditText editTextNote;

    private static final String KEY_NOTE_BUNDLE = "NOTE";
    private static final String KEY_NAME_BUNDLE = "NAME";
}

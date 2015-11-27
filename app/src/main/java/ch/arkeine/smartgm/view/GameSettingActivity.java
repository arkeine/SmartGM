package ch.arkeine.smartgm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.database.daoimplementation.GameDAO;
import ch.arkeine.smartgm.presenter.GameSettingActivityPresenter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

public class GameSettingActivity extends DataEditionActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_game_setting);
        super.onCreate(savedInstanceState);

        // Get components from view
        editTextTitle = (EditText) findViewById(R.id.title);
        editTextDescription = (EditText) findViewById(R.id.description_content);

        // Create the presenter and load data
        if (presenter == null)
            presenter = new GameSettingActivityPresenter(new GameDAO(this));
        presenter.onTakeView(this);

        // Get the parameter from the intent
        Intent intent = getIntent();
        String mode = getOrDefault(intent.getStringExtra(Constants.MODE_TITLE), Constants.MODE_CREATE);

        if (Constants.MODE_MODIFY.equals(mode)) {
            long id = intent.getLongExtra(Constants.ID_TITLE, -1);
            presenter.loadObjectDAO(id);
        }
    }

    @Override
    protected void onSave() {
        String title = editTextTitle.getText().toString();
        if (!title.isEmpty()) {
            presenter.setGameTitle(editTextTitle.getText().toString());
            presenter.setGameDesciption(editTextDescription.getText().toString());
            presenter.saveObjectDAO();
        }
    }

    /* ============================================ */
    // DATA MANIPULATION
    /* ============================================ */

    public void setTitle(String title) {
        editTextTitle.setText(title);
    }

    public void setDescription(String description) {
        editTextDescription.setText(description);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static GameSettingActivityPresenter presenter;
    private EditText editTextTitle;
    private EditText editTextDescription;
}

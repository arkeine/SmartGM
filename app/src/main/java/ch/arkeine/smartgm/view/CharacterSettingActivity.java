package ch.arkeine.smartgm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.database.daoimplementation.CharacterDAO;
import ch.arkeine.smartgm.presenter.CharacterSettingActivityPresenter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

public class CharacterSettingActivity extends DataEditionActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_character_setting);
        super.onCreate(savedInstanceState);

        textName = (EditText) findViewById(R.id.text);

        SmartGmApplication app = (SmartGmApplication) getApplication();

        // Create the presenter and load data
        if (presenter == null)
            presenter = new CharacterSettingActivityPresenter(new CharacterDAO(this));
        presenter.onTakeView(this);

        // Get the parameter from the intent
        Intent intent = getIntent();
        String mode = getOrDefault(intent.getStringExtra(Constants.MODE_TITLE), Constants.MODE_CREATE);

        if (Constants.MODE_MODIFY.equals(mode)) {
            long id = intent.getLongExtra(Constants.ID_TITLE, -1);
            presenter.loadObjectDAO(id);
        } else if (Constants.MODE_CREATE.equals(mode)) {
            presenter.setGameId(app.getFilterManager().getGameId());
        }
    }

    @Override
    protected void onSave() {
        String title = textName.getText().toString();
        if (!title.isEmpty()) {
            presenter.setName(title);
            presenter.saveObjectDAO();
        }
    }

    /* ============================================ */
    // DATA MANIPULATION
    /* ============================================ */

    public void setName(String name) {
        textName.setText(name);
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static CharacterSettingActivityPresenter presenter;
    private EditText textName;
}

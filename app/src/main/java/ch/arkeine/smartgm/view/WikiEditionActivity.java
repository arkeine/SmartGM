package ch.arkeine.smartgm.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.commonsware.cwac.anddown.AndDown;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.database.dal.WikiPagePersistence;
import ch.arkeine.smartgm.presenter.WikiEditionPresenter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

public class WikiEditionActivity extends DataEditionActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_wiki_page);
        super.onCreate(savedInstanceState);

        // Init and load components
        button = (ToggleButton) findViewById(R.id.switch_button);
        display = (WebView) findViewById(R.id.display_view);
        display.setBackgroundColor(Color.TRANSPARENT);
        editDescription = (EditText) findViewById(R.id.description);
        editName = (EditText) findViewById(R.id.name);
        converter = new AndDown();
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mode (isChecked);
            }
        });

        SmartGmApplication app = (SmartGmApplication) getApplication();

        // Create the presenter and load data
        if (presenter == null)
            presenter = new WikiEditionPresenter(new WikiPagePersistence(this));
        presenter.onTakeView(this);

        // Get the parameter from the intent
        Intent intent = getIntent();
        String mode = getOrDefault(intent.getStringExtra(Constants.KEY_MODE_CONTENT), Constants.MODE_CREATE);

        if (Constants.MODE_MODIFY.equals(mode)) {
            long id = intent.getLongExtra(Constants.KEY_ID_CONTENT, -1);
            presenter.loadObjectDAO(id);
        } else if (Constants.MODE_CREATE.equals(mode)) {
            presenter.setUniverseId(app.getFilterManager().getUniverseId());
        }
    }

    @Override
    protected boolean onSave() {
        String title = editName.getText().toString();

        if (title.isEmpty()) {
            confirmeQuitNoSave();
            return false;
        }
        else
        {
            presenter.setName(editName.getText().toString());
            presenter.setDescription(editDescription.getText().toString());
            presenter.saveObjectDAO();
            return true;
        }
    }

    /* ============================================ */
    // DATA MANIPULATION
    /* ============================================ */

    public void setName(String name) {
        editName.setText(name);
    }

    public void setDescription(String description) {
        editDescription.setText(description);
        mode(false);
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void mode(boolean edition)
    {
        if (edition) {
            editDescription.setVisibility(View.VISIBLE);
            display.setVisibility(View.GONE);
        } else {
            editDescription.setVisibility(View.GONE);
            display.setVisibility(View.VISIBLE);
            String convertedData = converter.markdownToHtml(editDescription.getText().toString());
            display.loadData(convertedData, "text/html", "UTF-8");
        }
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static WikiEditionPresenter presenter;
    private ToggleButton button;
    private WebView display;
    private EditText editName;
    private EditText editDescription;
    private AndDown converter;
}

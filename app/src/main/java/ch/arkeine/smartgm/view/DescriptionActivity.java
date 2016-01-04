package ch.arkeine.smartgm.view;

import android.app.Activity;
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

import static ch.arkeine.smartgm.Constants.getOrDefault;

public class DescriptionActivity extends DataEditionActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_description);
        super.onCreate(savedInstanceState);

        // Init and load components
        button = (ToggleButton) findViewById(R.id.switch_button);
        display = (WebView) findViewById(R.id.display_view);
        display.setBackgroundColor(Color.TRANSPARENT);
        edit = (EditText) findViewById(R.id.edit_view);
        converter = new AndDown();

        // Get the parameter from the intent
        Intent intent = getIntent();
        edit.setText(getOrDefault(intent.getStringExtra(Constants.KEY_DESCRIPTION_CONTENT), ""));
        mode(false);

        // Init listeners
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mode (isChecked);
            }
        });
    }

    @Override
    protected boolean onSave() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.KEY_DESCRIPTION_CONTENT, edit.getText().toString());
        setResult(Activity.RESULT_OK, returnIntent);
        return true;
    }

    @Override
    protected boolean onCancel() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        return true;
    }

    private void mode(boolean edition)
    {
        if (edition) {
            edit.setVisibility(View.VISIBLE);
            display.setVisibility(View.GONE);
        } else {
            edit.setVisibility(View.GONE);
            display.setVisibility(View.VISIBLE);
            String convertedData = converter.markdownToHtml(edit.getText().toString());
            display.loadData(convertedData, "text/html", "UTF-8");
        }
    }

    private ToggleButton button;
    private WebView display;
    private EditText edit;
    private AndDown converter;
}

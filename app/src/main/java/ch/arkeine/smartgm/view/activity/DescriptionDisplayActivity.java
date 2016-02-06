package ch.arkeine.smartgm.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.view.fragment.DataEditionButtons;
import ch.arkeine.smartgm.view.fragment.WikiContent;

public class DescriptionDisplayActivity extends AppCompatActivity implements
        DataEditionButtons.OnDataEditionButtonClickedListener{

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_display);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        wikiContentDescription = (WikiContent) fragmentManager.findFragmentById(R.id.description);

        Intent intent = getIntent();
        content = Constants.getOrDefault(intent.getStringExtra(Constants.KEY_DESCRIPTION_CONTENT), "");
        wikiContentDescription.setContent(content);
    }

    @Override
    public void onButtonSaveClicked() {
        Intent returnIntent = new Intent();
        returnIntent.putExtra(Constants.KEY_DESCRIPTION_CONTENT,
                wikiContentDescription.getContent());
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onButtonCancelClicked() {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void onButtonReloadClicked() {
        wikiContentDescription.setContent(content);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private WikiContent wikiContentDescription;
    private String content;
}

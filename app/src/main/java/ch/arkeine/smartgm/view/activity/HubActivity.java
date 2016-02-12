package ch.arkeine.smartgm.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.view.activity.editiondb.DiceEditionActivity;
import ch.arkeine.smartgm.view.activity.editiondb.EditionListActivity;
import ch.arkeine.smartgm.view.activity.editiondb.UniverseEditionActivity;

public class HubActivity extends AppCompatActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //loadContentPage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_hub, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Constants.displaySoon(this);
            return true;
        } else if (id == R.id.action_edition) {
            //Switch to the listing edition activity
            Intent intent = new Intent(this, EditionListActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_game_selection) {
            //Switch to the listing edition activity
            Intent intent = new Intent(this, DiceEditionActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void loadContentPage() {
        if(SmartGmApplication.filterManager.isGameSelected()) {

        } else {
            ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
            //stub.setLayoutResource(layoutStub);
            stub.inflate();
        }
    }
}

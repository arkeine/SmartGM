package ch.arkeine.smartgm.view;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.view.activity.edition.DataEditionActivity;

import static ch.arkeine.smartgm.Constants.getOrDefault;

/**
 * Created by Arkeine on 15.11.2015.
 */
public abstract class DescribableEditionActivity extends DataEditionActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.describable_edition, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_describe) {

            Intent intent = new Intent(DescribableEditionActivity.this, DescriptionActivity.class);
            intent.putExtra(Constants.KEY_DESCRIPTION_CONTENT, getDescription());
            startActivityForResult(intent, 1);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String result = getOrDefault(intent.getStringExtra(Constants.KEY_DESCRIPTION_CONTENT), "");
                setDescription(result);
            }
        }
    }

    /* ============================================ */
    // SHOULD OVERRIDE
    /* ============================================ */

    protected String getDescription(){
        return "";
    }

    protected void setDescription(String s){

    }
}

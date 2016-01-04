package ch.arkeine.smartgm.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import ch.arkeine.smartgm.R;

/**
 * Created by Arkeine on 15.11.2015.
 */
public abstract class DataEditionActivity extends AppCompatActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_edition);

        if (layoutStub != 0) {
            ViewStub stub = (ViewStub) findViewById(R.id.layout_stub);
            stub.setLayoutResource(layoutStub);
            View inflated = stub.inflate();

            // Get components from view
            Button buttonOk = (Button) findViewById(R.id.button_save);
            buttonOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onSave())
                        finish();
                }
            });

            Button buttonCancel = (Button) findViewById(R.id.button_cancel);
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onCancel())
                        finish();
                }
            });
        }
    }

    /* ============================================ */
    // MUST CALL
    /* ============================================ */

    protected void setLayoutStub(@LayoutRes int layoutStub) {
        this.layoutStub = layoutStub;
    }

    /* ============================================ */
    // SHOULD OVERRIDE
    /* ============================================ */

    protected boolean onSave() {
        return true;
    }

    protected boolean onCancel() {
        return true;
    }

    public void confirmeQuitNoSave()
    {
        // Create a dialog with the item the option list (delete and edit)
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.activity_data_edition_dialog_save_title)
                .setMessage(R.string.activity_data_edition_dialog_save_message);

        builder.setNegativeButton(R.string.button_close_anyway, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataEditionActivity.this.finish();
            }
        });
        builder.setPositiveButton(R.string.button_cancel, null);

        builder.show();
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private int layoutStub;
}

package ch.arkeine.smartgm.view;

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
                    onSave();
                    finish();
                }
            });

            Button buttonCancel = (Button) findViewById(R.id.button_cancel);
            buttonCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCancel();
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

    protected void onSave() {
    }

    ;

    protected void onCancel() {
    }

    ;

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private int layoutStub;
}

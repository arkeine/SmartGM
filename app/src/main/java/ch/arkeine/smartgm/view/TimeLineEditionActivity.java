package ch.arkeine.smartgm.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.database.dal.TimeLinePersistence;
import ch.arkeine.smartgm.presenter.TimeLineEditionPresenter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

public class TimeLineEditionActivity extends DescribableEditionActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_timeline_edition);
        super.onCreate(savedInstanceState);
        this.dateFormatter = new SimpleDateFormat(Constants.DATE_PATTERN);

        // Get components from view
        editTextName = (EditText) findViewById(R.id.name);
        buttonDate = (Button) findViewById(R.id.button_date);
        textDate = (TextView) findViewById(R.id.text_date);

        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar date = presenter.getDate();

                DatePickerDialog dpd = new DatePickerDialog(TimeLineEditionActivity.this,
                        createDateListener(), date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
                dpd.show();
            }
        });

        SmartGmApplication app = (SmartGmApplication) getApplication();

        // Create the presenter and load data
        if (presenter == null)
            presenter = new TimeLineEditionPresenter(new TimeLinePersistence(this));
        presenter.onTakeView(this);

        // Get the parameter from the intent
        Intent intent = getIntent();
        String mode = getOrDefault(intent.getStringExtra(Constants.KEY_MODE_CONTENT), Constants.MODE_CREATE);

        if (Constants.MODE_MODIFY.equals(mode)) {
            long id = intent.getLongExtra(Constants.KEY_ID_CONTENT, Constants.INVALID_ID);
            presenter.loadObjectDAO(id);
        }else if (Constants.MODE_CREATE.equals(mode)) {
            presenter.setGameId(app.getFilterManager().getGameId());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_NAME_BUNDLE, editTextName.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        editTextName.setText(savedInstanceState.getString(KEY_NAME_BUNDLE));
    }

    @Override
    protected boolean onSave() {
        String title = editTextName.getText().toString();

        if (title.isEmpty()) {
            confirmeQuitNoSave();
            return false;
        }
        else
        {
            presenter.setName(editTextName.getText().toString());
            presenter.saveObjectDAO();
            return true;
        }
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
    public void setDate(Calendar c) {
        textDate.setText(dateFormatter.format(c.getTime()));
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private DatePickerDialog.OnDateSetListener createDateListener() {
        return new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, monthOfYear, dayOfMonth);
                presenter.setDate(c);
            }
        };
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static TimeLineEditionPresenter presenter;
    private TextView textDate;
    private EditText editTextName;
    private Button buttonDate;
    private SimpleDateFormat dateFormatter;

    private static final String KEY_NAME_BUNDLE = "NAME";
}

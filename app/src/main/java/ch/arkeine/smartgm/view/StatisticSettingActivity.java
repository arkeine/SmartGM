package ch.arkeine.smartgm.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.component.NumberPickerView;
import ch.arkeine.smartgm.model.database.daoimplementation.StatisticDAO;
import ch.arkeine.smartgm.presenter.StatisticSettingActivityPresenter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

public class StatisticSettingActivity extends DataEditionActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_statistic_setting);
        super.onCreate(savedInstanceState);

        textName = (EditText) findViewById(R.id.text);
        npMinValue = (NumberPickerView) findViewById(R.id.minimum_value);
        npMaxValue = (NumberPickerView) findViewById(R.id.maximum_value);
        npCurrentValue = (NumberPickerView) findViewById(R.id.current_value);

        SmartGmApplication app = (SmartGmApplication) getApplication();

        // Create the presenter and load data
        if (presenter == null)
            presenter = new StatisticSettingActivityPresenter(new StatisticDAO(this));
        presenter.onTakeView(this);

        // Get the parameter from the intent
        Intent intent = getIntent();
        String mode = getOrDefault(intent.getStringExtra(Constants.MODE_TITLE), Constants.MODE_CREATE);

        if (Constants.MODE_MODIFY.equals(mode)) {
            long id = intent.getLongExtra(Constants.ID_TITLE, -1);
            presenter.loadObjectDAO(id);
        } else if (Constants.MODE_CREATE.equals(mode)) {
            presenter.setCharacterId(app.getFilterManager().getCharacterId());
        }
    }

    @Override
    protected void onSave() {
        String title = textName.getText().toString();
        if (!title.isEmpty()) {
            presenter.setName(title);
            presenter.setMax(npMaxValue.getCurrent());
            presenter.setMin(npMinValue.getCurrent());
            presenter.setCurrent(npCurrentValue.getCurrent());
            presenter.saveObjectDAO();
        }
    }

    /* ============================================ */
    // DATA MANIPULATION
    /* ============================================ */

    public void setName(String name) {
        textName.setText(name);
    }
    public void setMax(int value) {
        npMaxValue.setCurrent(value);
    }
    public void setMin(int value) {
        npMinValue.setCurrent(value);
    }
    public void setCurrent(int value) {
        npCurrentValue.setCurrent(value);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static StatisticSettingActivityPresenter presenter;
    private EditText textName;
    private NumberPickerView npMinValue;
    private NumberPickerView npMaxValue;
    private NumberPickerView npCurrentValue;
}

package ch.arkeine.smartgm.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.database.daoimplementation.DiceDAO;
import ch.arkeine.smartgm.model.object.Dice;
import ch.arkeine.smartgm.presenter.DiceSettingActivityPresenter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

public class DiceSettingActivity extends DataEditionActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_dice_setting);
        super.onCreate(savedInstanceState);

        // Get components from view
        textNbFace = (TextView) findViewById(R.id.text_nb_face);
        textNbFace.setText(String.valueOf(Dice.MINIMAL_FACE_NUMBER));

        ImageButton increase = (ImageButton) findViewById(R.id.button_increase);
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekbarNbFace.setProgress(seekbarNbFace.getProgress() + 1);
            }
        });
        ImageButton decrease = (ImageButton) findViewById(R.id.button_decrease);
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekbarNbFace.setProgress(seekbarNbFace.getProgress() - 1);
            }
        });

        seekbarNbFace = (SeekBar) findViewById(R.id.seekbar_nb_face);
        seekbarNbFace.setMax(Dice.MAXIMAL_FACE_NUMBER - Dice.MINIMAL_FACE_NUMBER);
        seekbarNbFace.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int value = seekbarNbFace.getProgress() + Dice.MINIMAL_FACE_NUMBER;
                textNbFace.setText(String.valueOf(value));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        SmartGmApplication app = (SmartGmApplication) getApplication();

        // Create the presenter and load data
        if (presenter == null)
            presenter = new DiceSettingActivityPresenter(new DiceDAO(this));
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
        presenter.setNbFace(seekbarNbFace.getProgress() + Dice.MINIMAL_FACE_NUMBER);
        presenter.saveObjectDAO();
    }

    /* ============================================ */
    // DATA MANIPULATION
    /* ============================================ */

    public void setNbFace(int nbFace) {
        seekbarNbFace.setProgress(nbFace - Dice.MINIMAL_FACE_NUMBER);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static DiceSettingActivityPresenter presenter;
    private TextView textNbFace;
    private SeekBar seekbarNbFace;
}
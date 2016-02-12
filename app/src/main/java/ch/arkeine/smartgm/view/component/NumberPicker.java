package ch.arkeine.smartgm.view.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ch.arkeine.smartgm.R;

/**
 * This is a number picker component for android api v10.
 *
 * The interface is created by code inside the geometry method. It's just an exercise in style.
 */
public class NumberPicker extends FrameLayout {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public NumberPicker(Context context) {
        this(context, null, 0);
    }

    public NumberPicker(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberPicker(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        geometry(attrs, defStyle);
        control(attrs, defStyle);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public Integer getMax() {
        return max;
    }

    public boolean setMax(Integer max) {
        if (max == null || max >= current) {
            this.max = max;
            return true;
        } else {
            return false;
        }
    }

    public Integer getCurrent() {
        return current;
    }

    public boolean setCurrent(Integer current) {
        if (current != null) {

            if ((max == null || current <= max) ){

                if(min == null || current >= min) {
                    this.current = current;
                    updateView();
                }
                else
                {
                    this.current = min;
                    String message = getResources().getString(
                            R.string.components_numberpicker_minimal_value) + " " + min;
                    makeToast(message);
                }
            }
            else
            {
                this.current = max;
                String message = getResources().getString(
                        R.string.components_numberpicker_maximal_value) + " " + max;
                makeToast(message);
            }
            return true;
        }
        return false;
    }

    public Integer getMin() {
        return min;
    }

    public boolean setMin(Integer min) {
        if (min == null || min <= current) {
            this.min = min;
            return true;
        } else {
            return false;
        }
    }

    public boolean setValues(int min, int current, int max)
    {
        if(min <= current && current <= max)
        {
            this.min = min;
            this.max = max;
            this.current = current;
            return true;
        }
        return false;
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void geometry(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.NumberPicker, defStyle, 0);

        int orientation = a.getInteger(R.styleable.NumberPicker_orientation, 0);

        View v = null;
        if (orientation == 0) {
            v = inflate(getContext(), R.layout.component_number_picker_horizontal, null);
        } else {
            v = inflate(getContext(), R.layout.component_number_picker_vertical, null);
        }

        text = (EditText) v.findViewById(R.id.text_number);
        up = (ImageButton) v.findViewById(R.id.button_increase);
        down = (ImageButton) v.findViewById(R.id.button_decrease);
        addView(v);

        int max = a.getInteger(R.styleable.NumberPicker_maximumValue, 0);
        int min = a.getInteger(R.styleable.NumberPicker_minimumValue, 0);
        int current = a.getInteger(R.styleable.NumberPicker_currentValue, 0);
        setValues(min, current, max);
        text.setText("" + current);

        a.recycle();
    }

    private void control(AttributeSet attrs, int defStyle) {
        up.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonUpClicked();
            }
        });
        down.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonDownClicked();
            }
        });
        text.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                        actionId == EditorInfo.IME_ACTION_DONE ||
                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                    if (!event.isShiftPressed()) {
                        // the user is done typing.
                        textChange();
                        return true; // consume.
                    }
                }
                return false; // pass on to other listeners.
            }
        });
        text.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                textChange();
            }
        });
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                text.requestFocus();
            }
        });
    }

    private void buttonUpClicked() {
        setCurrent(getCurrent() + 1);
    }

    private void buttonDownClicked() {
        setCurrent(getCurrent() - 1);
    }

    private void textChange() {
        Integer newValue;
        try {
            newValue = Integer.valueOf(text.getText().toString());
            setCurrent(newValue);
        } catch (NumberFormatException e) {
            String content = getResources().getString(R.string.components_numberpicker_numeric_only);
            makeToast(content);
        }
        updateView(); // Update in all the case, event if value is not allowed
    }

    private void updateView() {
        text.setText(String.valueOf(current));
    }

    private void makeToast(String content){
        Toast toast = Toast.makeText(getContext(), content, Toast.LENGTH_SHORT);
        toast.show();
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    // interfaces
    private ImageButton up;
    private ImageButton down;
    private EditText text;

    // data
    private Integer max;
    private Integer current = 0;
    private Integer min;
}

package ch.arkeine.smartgm.component;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import ch.arkeine.smartgm.R;

/**
 * TODO: document your custom view class.
 */
public class NumberPickerView extends LinearLayout {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public NumberPickerView(Context context) {
        this(context, null, 0);
    }

    public NumberPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberPickerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs);
        geometry(attrs, defStyle);
        control(attrs, defStyle);

        String namespace = "http://schemas.android.com/apk/res-auto";
        setCurrent(attrs.getAttributeIntValue(namespace, "currentValue", 0));
        setMax(attrs.getAttributeIntValue(namespace, "maximumValue", 0));
        setMin(attrs.getAttributeIntValue(namespace, "minimumValue", 0));
    }

    private void geometry(AttributeSet attrs, int defStyle) {
        // Load attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.NumberPickerView, defStyle, 0);

        text = new EditText(getContext());
        up = new ImageButton(getContext());
        down = new ImageButton(getContext());

        text.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        up.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        down.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));

        text.setText("0");
        text.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        text.setPadding(20, 20, 20, 20);
        text.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
        up.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.circle_shape));
        down.setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.circle_shape));

        int orientation = a.getInteger(R.styleable.NumberPickerView_orientation, 0);
        setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);

        if (orientation == 0) {
            setOrientation(LinearLayout.HORIZONTAL);
            addView(down);
            addView(text);
            addView(up);

            up.setImageDrawable(getContext().getResources().getDrawable(R.drawable.rightwithe));
            down.setImageDrawable(getContext().getResources().getDrawable(R.drawable.leftwithe));
        } else {
            setOrientation(LinearLayout.VERTICAL);
            addView(up);
            addView(text);
            addView(down);

            up.setImageDrawable(getContext().getResources().getDrawable(R.drawable.upwithe));
            down.setImageDrawable(getContext().getResources().getDrawable(R.drawable.downwithe));
        }

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
                    String message = getContext().getResources().getString(
                            R.string.components_numberpicker_minimal_value) + " " + min;
                    Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
            else
            {
                this.current = max;
                String message = getContext().getResources().getString(
                        R.string.components_numberpicker_maximal_value) + " " + max;
                Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
                toast.show();
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


    /* ============================================ */
    // PRIVATE
    /* ============================================ */

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
            Toast toast = Toast.makeText(getContext(),
                    R.string.components_numberpicker_numeric_only, Toast.LENGTH_SHORT);
            toast.show();
        }
        updateView(); // Update in all the case, event if value is not allowed
    }

    private void updateView() {
        text.setText(String.valueOf(current));
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private ImageButton up;
    private ImageButton down;
    private EditText text;

    private Integer max;
    private Integer current = 0;
    private Integer min;
}

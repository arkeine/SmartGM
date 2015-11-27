package ch.arkeine.smartgm.view.edittextfilter;

import android.text.InputFilter;
import android.text.Spanned;

/**
 * Created by Arkeine on 15.11.2015.
 */
public class InputFilterMinMax implements InputFilter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public InputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public InputFilterMinMax(String min, String max) {
        this.min = Integer.parseInt(min);
        this.max = Integer.parseInt(max);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            int input = Integer.parseInt(dest.toString() + source.toString());
            input = uniforming(Integer.valueOf(input));
            return String.valueOf(input);

        } catch (NumberFormatException e) {
            return String.valueOf(min);
        }
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private int uniforming(int value) {
        value = value < min ? min : value;
        value = value > max ? max : value;
        return value;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private final int min;
    private final int max;
}

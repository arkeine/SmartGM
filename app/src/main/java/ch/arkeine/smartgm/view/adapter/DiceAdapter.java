package ch.arkeine.smartgm.view.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.object.Dice;

/**
 * Created by arkeine on 11/9/15.
 */
public class DiceAdapter extends ArrayAdapter<Dice> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DiceAdapter(Context context, List<Dice> content) {
        super(context, resource, content);
        this.content = content;
        this.context = context;
        this.suffix = context.getString(R.string.adapter_dice_suffix);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        Holder holder = new Holder();

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(resource, parent, false);

            TextView nbFaceDice = (TextView) view.findViewById(R.id.text);
            Button roll = (Button) view.findViewById(R.id.button);
            roll.setText(R.string.button_roll);

            holder.nbFaceDice = nbFaceDice;
            holder.roll = roll;

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        Dice toDraw = content.get(position);

        holder.nbFaceDice.setText(String.valueOf(toDraw.getNbFaces()) + " " + suffix);
        // Add listener on the button click
        holder.roll.setOnClickListener(createButtonRollListener(toDraw));

        return view;
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private View.OnClickListener createButtonRollListener(final Dice value) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                StringBuilder sb = new StringBuilder();
                sb.append(context.getString(R.string.adapter_dice_prefix));
                sb.append(" ");
                sb.append(value.getNbFaces());
                sb.append(" ");
                sb.append(context.getString(R.string.adapter_dice_suffix));
                builder.setTitle(sb.toString())
                        .setMessage(String.valueOf(value.getRandomValue()))
                        .setNeutralButton(R.string.button_close, null);
                builder.show();
            }
        };
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private List<Dice> content;
    private Context context;
    private final String suffix;

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static final int resource = R.layout.listitem_text_button;

    /**
     * View Holder pattern
     */
    private static class Holder {
        public TextView nbFaceDice;
        public Button roll;
    }
}
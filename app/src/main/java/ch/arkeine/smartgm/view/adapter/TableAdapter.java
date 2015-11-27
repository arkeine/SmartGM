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
import ch.arkeine.smartgm.model.object.Table;
import ch.arkeine.smartgm.model.object.TableItem;

/**
 * Created by arkeine on 11/9/15.
 */
public class TableAdapter extends ArrayAdapter<Table> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TableAdapter(Context context, List<Table> content) {
        super(context, resource, content);
        this.content = content;
        this.context = context;
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

            TextView name = (TextView) view.findViewById(R.id.text);
            Button roll = (Button) view.findViewById(R.id.button);
            roll.setText(R.string.button_roll);

            holder.name = name;
            holder.roll = roll;

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        Table toDraw = content.get(position);
        holder.name.setText(String.valueOf(toDraw.getName()));
        // Add listener on the button click
        holder.roll.setOnClickListener(createButtonRollListener(toDraw));

        return view;
    }


    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private View.OnClickListener createButtonRollListener(final Table table) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TableItem item = table.getRandomValue();

                if(item != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(item.getTitle())
                            .setMessage(item.getDescription())
                            .setNeutralButton(R.string.button_close, null);
                    builder.show();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(R.string.adapter_table_error_title)
                            .setMessage(R.string.adapter_table_error_content)
                            .setNeutralButton(R.string.button_close, null);
                    builder.show();
                }
            }
        };
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private List<Table> content;
    private Context context;

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static final int resource = R.layout.listitem_text_button;

    /**
     * View Holder pattern
     */
    private static class Holder {
        public TextView name;
        public Button roll;
    }

}
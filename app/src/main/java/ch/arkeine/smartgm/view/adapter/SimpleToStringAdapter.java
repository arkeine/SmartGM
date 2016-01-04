package ch.arkeine.smartgm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.arkeine.smartgm.R;

/**
 * Created by arkeine on 11/9/15.
 */
public class SimpleToStringAdapter extends ArrayAdapter<Object> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public SimpleToStringAdapter(Context context, List content) {
        super(context, resource, content);
        this.content = content;
        this.context = context;
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder;

        if (view == null) {
            holder = new Holder();

            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(resource, parent, false);

            TextView text = (TextView) view.findViewById(R.id.text_view);

            holder.title = text;

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        // Item to draw
        Object toDraw = content.get(position);
        holder.title.setText(toDraw.toString());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private List content;
    private Context context;

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static final int resource = R.layout.listitem_text_simple;

    /**
     * View Holder pattern
     */
    private static class Holder {
        public TextView title;
    }
}
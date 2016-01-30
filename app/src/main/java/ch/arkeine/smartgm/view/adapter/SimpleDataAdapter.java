package ch.arkeine.smartgm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.handler.IdentifiedDataObject;

/**
 * Created by Arkeine on 29.01.2016.
 */
public class SimpleDataAdapter extends ArrayAdapter<IdentifiedDataObject> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public SimpleDataAdapter(Context context, List content) {
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

            LayoutInflater layoutInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(resource, parent, false);

            TextView text = (TextView) view.findViewById(R.id.text_view);

            holder.title = text;

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        // Item to draw
        IdentifiedDataObject toDraw = content.get(position);
        holder.title.setText(toDraw.getListingText());

        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private List<IdentifiedDataObject> content;
    private Context context;

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static final int resource = R.layout.adapter_simple_data;

    /**
     * View Holder pattern
     */
    private static class Holder {
        public TextView title;
    }
}

package ch.arkeine.smartgm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.dao.object.EntityValue;

/**
 * Created by arkeine on 11/9/15.
 */
public class EntityValueAdapter extends ArrayAdapter<EntityValue> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public EntityValueAdapter(Context context, List<EntityValue> content) {
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

            TextView title = (TextView) view.findViewById(R.id.left);
            TextView weight = (TextView) view.findViewById(R.id.right);

            holder.name = title;
            holder.weight = weight;

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        EntityValue toDraw = content.get(position);
        holder.name.setText(toDraw.getName());
        holder.weight.setText(String.valueOf(toDraw.getValue()));

        return view;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private List<EntityValue> content;
    private Context context;

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static final int resource = R.layout.listitem_opposite_text;

    /**
     * View Holder pattern
     */
    private static class Holder {
        public TextView name;
        public TextView weight;
    }

}
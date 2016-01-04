package ch.arkeine.smartgm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.dao.object.Game;

/**
 * Created by arkeine on 11/9/15.
 */
public class GameAdapter extends ArrayAdapter<Game> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public GameAdapter(Context context, List<Game> content) {
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
        Holder holder = new Holder();

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(resource, parent, false);

            TextView name = (TextView) view.findViewById(R.id.title);
            TextView note = (TextView) view.findViewById(R.id.subtitle);

            holder.name = name;
            holder.note = note;

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        // Item to draw
        Game toDraw = content.get(position);

        holder.name.setText(toDraw.getName());
        holder.note.setText(toDraw.getNote());

        return view;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private List<Game> content;
    private Context context;

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static final int resource = R.layout.listitem_title_subtitle;

    /**
     * View Holder pattern
     */
    private static class Holder {
        public TextView name;
        public TextView note;
    }
}
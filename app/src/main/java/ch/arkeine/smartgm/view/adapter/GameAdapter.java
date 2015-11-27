package ch.arkeine.smartgm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.object.Game;

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

            TextView title = (TextView) view.findViewById(R.id.title);
            TextView description = (TextView) view.findViewById(R.id.subtitle);

            holder.title = title;
            holder.description = description;

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        // Item to draw
        Game toDraw = content.get(position);

        holder.title.setText(toDraw.getTitle());
        holder.description.setText(toDraw.getDescription());

        return view;
    }


    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private View.OnClickListener getSimleClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }

    private View.OnLongClickListener getLongClickListener() {
        return new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        };
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
        public TextView title;
        public TextView description;
    }
}
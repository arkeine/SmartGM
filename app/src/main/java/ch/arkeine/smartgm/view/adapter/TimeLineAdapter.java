package ch.arkeine.smartgm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.dao.object.TimeLine;

/**
 * Created by arkeine on 11/9/15.
 */
public class TimeLineAdapter extends ArrayAdapter<TimeLine> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TimeLineAdapter(Context context, List<TimeLine> content) {
        super(context, resource, content);
        this.content = content;
        this.context = context;
        this.dateFormatter = new SimpleDateFormat(Constants.DATE_PATTERN);
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
            holder.date = note;

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        // Item to draw
        TimeLine toDraw = content.get(position);

        holder.name.setText(toDraw.getName());
        holder.date.setText(dateFormatter.format(toDraw.getDate().getTime()));

        return view;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private List<TimeLine> content;
    private Context context;
    private SimpleDateFormat dateFormatter;

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static final int resource = R.layout.listitem_title_subtitle;

    /**
     * View Holder pattern
     */
    private static class Holder {
        public TextView name;
        public TextView date;
    }
}
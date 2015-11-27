package ch.arkeine.smartgm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.object.Statistic;

/**
 * Created by arkeine on 11/9/15.
 */
public class StatisticAdapter extends ArrayAdapter<Statistic> {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public StatisticAdapter(Context context, List<Statistic> content) {
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

            TextView title = (TextView) view.findViewById(R.id.title);
            TextView subtitle = (TextView) view.findViewById(R.id.subtitle);
            ProgressBar progress = (ProgressBar) view.findViewById(R.id.progress_bar);

            holder.title = title;
            holder.subtitle = subtitle;
            holder.progress = progress;

            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        Statistic toDraw = content.get(position);
        holder.title.setText(toDraw.getName());

        int top = toDraw.getCurrent() >= 0 ? toDraw.getMax() : toDraw.getMin();

        holder.subtitle.setText(Integer.valueOf(toDraw.getCurrent()) + " / " +Integer.valueOf(top));

        holder.progress.setMax(top);
        holder.progress.setProgress(toDraw.getCurrent());

        return view;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private List<Statistic> content;
    private Context context;

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static final int resource = R.layout.listitem_title_progress;

    /**
     * View Holder pattern
     */
    private static class Holder {
        public TextView title;
        public TextView subtitle;
        public ProgressBar progress;
    }

}
package ch.arkeine.smartgm.view.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.arkeine.smartgm.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class NoGameSelectedFragment extends Fragment {


    public NoGameSelectedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_game_selected, container, false);
    }

}

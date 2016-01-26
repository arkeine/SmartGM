package ch.arkeine.smartgm.view.fragment;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ch.arkeine.smartgm.R;

public class DataEditionButtons extends Fragment {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DataEditionButtons() {
        // Required empty public constructor
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_data_edition_buttons, container, false);

        // Get components from view
        Button buttonOk = (Button) v.findViewById(R.id.button_save);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onButtonSaveClicked();
                }
            }
        });

        Button buttonCancel = (Button) v.findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onButtonCancelClicked();
                }
            }
        });

        Button buttonReload = (Button) v.findViewById(R.id.button_reload);
        buttonReload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onButtonReloadClicked();
                }
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnDataEditionButtonClickedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private OnDataEditionButtonClickedListener listener;

    /* ============================================ */
    // INTERFACE
    /* ============================================ */

    public interface OnDataEditionButtonClickedListener {

        void onButtonSaveClicked();
        void onButtonCancelClicked();
        void onButtonReloadClicked();
    }
}

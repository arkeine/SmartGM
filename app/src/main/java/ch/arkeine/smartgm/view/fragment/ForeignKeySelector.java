package ch.arkeine.smartgm.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;

import java.util.List;

import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.model.handler.IdentifiedDataObject;
import ch.arkeine.smartgm.view.adapter.SimpleDataAdapter;


public class ForeignKeySelector extends Fragment {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public ForeignKeySelector() {

    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_foreign_key_selector, container, false);

        ImageButton buttonAdd = (ImageButton) v.findViewById(R.id.button);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onButtonAddClicked();
                }
            }
        });

        spinner = (Spinner) v.findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null) {
                    Log.d("TEST ADAPTER", parent.toString());
                    Log.d("TEST ADAPTER", view.toString());
                    listener.onItemSelected();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (listener != null) {
                    Log.d("TEST ADAPTER", parent.toString());
                    listener.onItemSelected();
                }
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    /* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public IdentifiedDataObject getSelectedItem(){
        return (IdentifiedDataObject)spinner.getSelectedItem();
    }

    public void setSelectedItem(IdentifiedDataObject selected){
        int selectedIndex = adapter.getPosition(selected);
        spinner.setSelection(selectedIndex);
    }

    public void setItemList(List<? extends IdentifiedDataObject> l){
        adapter = new SimpleDataAdapter(this.getContext(), l);
        spinner.setAdapter(adapter);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private OnFragmentInteractionListener listener;
    private Spinner spinner;
    private SimpleDataAdapter adapter;

    /* ============================================ */
    // INTERFACE
    /* ============================================ */

    public interface OnFragmentInteractionListener {
        void onButtonAddClicked();
        void onItemSelected();
    }
}

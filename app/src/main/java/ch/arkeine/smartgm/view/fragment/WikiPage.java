package ch.arkeine.smartgm.view.fragment;

import android.app.Activity;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.commonsware.cwac.anddown.AndDown;

import ch.arkeine.smartgm.R;

/**
 * Show a wiki page with a action bar button for enter in edition mode
 */
public class WikiPage extends Fragment {

    /* ============================================ */
    // STATIC
    /* ============================================ */

    private static final String ARG_CONTENT = "ARG_CONTENT";

    /**
     * Factory which build fragment with parameters
     */
    public static WikiPage newInstance(String content) {
        WikiPage fragment = new WikiPage();
        Bundle args = new Bundle();
        args.putString(ARG_CONTENT, content);
        fragment.setArguments(args);
        return fragment;
    }

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public WikiPage() {
        // Required empty public constructor
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        if (getArguments() != null) {
            content = getArguments().getString(ARG_CONTENT);
        }

        converter = new AndDown();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wiki_page, container, false);

        editDescription = (EditText) v.findViewById(R.id.description);
        editDescription.setText(content);
        display = (WebView) v.findViewById(R.id.display_view);
        display.setBackgroundColor(Color.TRANSPARENT);

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_wiki_page_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit_wiki_page:
                mode();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    public boolean isContentModified()
    {
        return isEditionModeEnableOnce;
    }

    public String getContent()
    {
        return editDescription.getText().toString();
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void mode()
    {
        if (isEditionMode) {
            editDescription.setVisibility(View.VISIBLE);
            display.setVisibility(View.GONE);
            isEditionModeEnableOnce = true;
            isEditionMode = false;
        } else {
            editDescription.setVisibility(View.GONE);
            display.setVisibility(View.VISIBLE);
            String convertedData = converter.markdownToHtml(editDescription.getText().toString());
            display.loadData(convertedData, "text/html", "UTF-8");
            isEditionMode = true;
        }
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    //input
    private String content;

    //tool
    private boolean isEditionMode;
    private boolean isEditionModeEnableOnce;
    private AndDown converter;

    //gui
    private WebView display;
    private EditText editDescription;
}

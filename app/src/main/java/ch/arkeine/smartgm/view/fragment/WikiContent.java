package ch.arkeine.smartgm.view.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.EditText;

import com.commonsware.cwac.anddown.AndDown;

import ch.arkeine.smartgm.R;

/**
 * Show a wiki content page with a action bar button for enter in edition mode
 */
public class WikiContent extends Fragment {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public WikiContent() {
        // Required empty public constructor
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        dummyContent = getResources().getString(R.string.frag_wiki_content_dummy);
        converter = new AndDown();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_wiki_content, container, false);

        editDescription = (EditText) v.findViewById(R.id.description);
        display = (WebView) v.findViewById(R.id.display_view);
        display.setBackgroundColor(Color.TRANSPARENT);

        updateComponent();

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
                isEditionMode = !isEditionMode;
                updateComponent();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public boolean isContentModified()
    {
        return isEditionModeEnableOnce;
    }

    public String getContent(){
        return editDescription.getText().toString();
    }

    public void setContent(String content) {
        editDescription.setText(content);
        updateComponent();
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    private void updateComponent(){
        String content = editDescription.getText().toString();

        if (isEditionMode) {
            editDescription.setVisibility(View.VISIBLE);
            display.setVisibility(View.GONE);
            isEditionModeEnableOnce = true;
        } else {
            editDescription.setVisibility(View.GONE);
            display.setVisibility(View.VISIBLE);

            String convertedData;
            if(content.isEmpty()){
                convertedData = converter.markdownToHtml(dummyContent);
            }else{
                convertedData = converter.markdownToHtml(content);
            }

            display.loadData(convertedData, "text/html", "UTF-8");
        }
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    //tool
    private String dummyContent;
    private boolean isEditionMode;
    private boolean isEditionModeEnableOnce;
    private AndDown converter;

    //gui
    private WebView display;
    private EditText editDescription;
}

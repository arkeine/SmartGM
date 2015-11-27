package ch.arkeine.smartgm.view;

import android.os.Bundle;
import android.widget.EditText;

import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.presenter.WikiActivityPresenter;

public class WikiEditionActivity extends DataEditionActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_wiki_edition);
        super.onCreate(savedInstanceState);

        pageContent = (EditText) findViewById(R.id.page_content);

    }

    @Override
    protected void onSave() {
        presenter.saveObjectDAO();
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static WikiActivityPresenter presenter;
    private EditText pageContent;
}

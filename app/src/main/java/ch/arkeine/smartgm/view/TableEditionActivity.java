package ch.arkeine.smartgm.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.R;
import ch.arkeine.smartgm.SmartGmApplication;
import ch.arkeine.smartgm.model.database.dal.TablePersistence;
import ch.arkeine.smartgm.presenter.TableEditionPresenter;
import ch.arkeine.smartgm.view.adapter.TableItemAdapter;

import static ch.arkeine.smartgm.Constants.getOrDefault;

public class TableEditionActivity extends DescribableEditionActivity {

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setLayoutStub(R.layout.content_table_edition);
        super.onCreate(savedInstanceState);

        textName = (EditText) findViewById(R.id.text);
        listItems = (ListView) findViewById(R.id.list_items);
        listItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                displayItemOption(position);
                return false;
            }
        });

        SmartGmApplication app = (SmartGmApplication) getApplication();

        // Create the presenter and load data
        if (presenter == null)
            presenter = new TableEditionPresenter(new TablePersistence(this));
        presenter.onTakeView(this);

        // Get the parameter from the intent
        Intent intent = getIntent();
        String mode = getOrDefault(intent.getStringExtra(Constants.KEY_MODE_CONTENT), Constants.MODE_CREATE);

        if (Constants.MODE_MODIFY.equals(mode)) {
            long id = intent.getLongExtra(Constants.KEY_ID_CONTENT, -1);
            presenter.loadObjectDAO(id);
        } else if (Constants.MODE_CREATE.equals(mode)) {
            presenter.setUniverseId(app.getFilterManager().getUniverseId());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.table_setting, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            addNewContent();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean onSave() {
        String title = textName.getText().toString();

        if (title.isEmpty()) {
            confirmeQuitNoSave();
            return false;
        }
        else
        {
            presenter.setName(title);
            presenter.saveObjectDAO();
            return true;
        }
    }

    @Override
    protected String getDescription(){
        return presenter.getDescription();
    }

    @Override
    protected void setDescription(String s){
        presenter.setDescription(s);
    }

    /* ============================================ */
    // DATA MANIPULATION
    /* ============================================ */

    public void setName(String name) {
        textName.setText(name);
    }

    public void setListItems(List list) {
        itemAdapter = new TableItemAdapter(this, list);
        listItems.setAdapter(itemAdapter);
    }

    public void onListItemChange() {
        itemAdapter.notifyDataSetChanged();
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    /**
     * Add a new tuple to the data
     */
    private void addNewContent() {
        presenter.addNewItem(getString(R.string.activity_table_setting_item_default_name));
        editName(listItems.getCount()-1);
    }

    /**
     * Display the edit or delete option on a tuple
     *
     * @param position the item's position in the list
     */
    private void displayItemOption(final int position) {

        // Create a dialog with the item the option list (delete and edit)
        AlertDialog.Builder builder = new AlertDialog.Builder(TableEditionActivity.this);
        builder.setTitle(R.string.activity_table_setting_dialog_edit_delete_title)
                .setItems(R.array.action_list_table_setting_activity, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Add an action on user choice
                        switch (which) {
                            // Edit name
                            case 0:
                                editName(position);
                                break;
                            // Edit weight
                            case 1:
                                editWeight(position);
                                break;
                            // Delete
                            case 2:
                                removeItem(position);
                                break;
                        }
                    }
                });
        builder.show();
    }

    private void editName(final int index) {
        // Set up the input
        final EditText input = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.activity_table_setting_dialog_set_name_title);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String textInput = input.getText().toString();
                if (!textInput.isEmpty()) {
                    presenter.setItemTitle(index, input.getText().toString());
                }
            }
        });
        builder.setNegativeButton(R.string.button_cancel, null);
        builder.show();
    }

    private void editWeight(final int index) {
        // Set up the input
        final EditText input = new EditText(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.activity_table_setting_dialog_set_weight_title);

        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String textInput = input.getText().toString();
                if (!textInput.isEmpty()) {
                    presenter.setItemWeight(index, Integer.valueOf(input.getText().toString()));
                }
            }
        });
        builder.setNegativeButton(R.string.button_cancel, null);
        builder.show();
    }

    private void removeItem(final int index)
    {
        presenter.removeItem(index);
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private static TableEditionPresenter presenter;
    private EditText textName;
    private ListView listItems;
    private TableItemAdapter itemAdapter;
}

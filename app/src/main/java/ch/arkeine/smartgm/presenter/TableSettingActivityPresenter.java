package ch.arkeine.smartgm.presenter;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.TableDAOInterface;
import ch.arkeine.smartgm.model.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.model.object.Table;
import ch.arkeine.smartgm.model.object.TableItem;
import ch.arkeine.smartgm.view.DataEditionActivity;
import ch.arkeine.smartgm.view.TableSettingActivity;


/**
 * Created by Arkeine on 12.11.2015.
 */
public class TableSettingActivityPresenter extends DataEditionActivityPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TableSettingActivityPresenter(TableDAOInterface tableDAO) {
        super(tableDAO);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        String name = ((Table) getObject()).getName();
        List items = ((Table) getObject()).getItemList();

        ((TableSettingActivity) view).setName(name);
        ((TableSettingActivity) view).setListItems(items);
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new Table(Constants.invalidId, Constants.invalidId);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setName(String name) {
        ((Table) getObject()).setName(name);
    }

    public void setItemTitle(int index, String name) {
        ((Table) getObject()).getItemList().get(index).setTitle(name);
        ((TableSettingActivity) getView()).onListItemChange();
    }

    public void setItemWeight(int index, int weight) {
        ((Table) getObject()).getItemList().get(index).setWeight(weight);
        ((TableSettingActivity) getView()).onListItemChange();
    }

    public void setItemDescription(int index, String description) {
        ((Table) getObject()).getItemList().get(index).setDescription(description);
        ((TableSettingActivity) getView()).onListItemChange();
    }

    public void setGameId(long gameId) {
        ((Table) getObject()).setGameId(gameId);
    }

    public void addNewItem() {
        addNewItem("");
    }

    public void addNewItem(String defaultName) {
        TableItem item = new TableItem(Constants.invalidId, Constants.invalidId);
        item.setTitle(defaultName);
        ((Table) getObject()).add(item);
        ((TableSettingActivity) getView()).onListItemChange();
    }

    public void removeItem(ObjectWithIdentifier item) {
        ((Table) getObject()).remove((TableItem) item);
        ((TableSettingActivity) getView()).onListItemChange();
    }

    public void removeItem(int index) {
        ((Table) getObject()).remove(index);
        ((TableSettingActivity) getView()).onListItemChange();
    }
}

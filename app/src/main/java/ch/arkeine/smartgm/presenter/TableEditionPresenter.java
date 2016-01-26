package ch.arkeine.smartgm.presenter;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.model.dao.object.Table;
import ch.arkeine.smartgm.model.dao.object.TableItem;
import ch.arkeine.smartgm.model.dao.persistenceinterface.TablePersistenceInterface;
import ch.arkeine.smartgm.view.activity.DataEditionActivity;
import ch.arkeine.smartgm.view.TableEditionActivity;


/**
 * Presenter for the table edition
 */
public class TableEditionPresenter extends DescribableEditionPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public TableEditionPresenter(TablePersistenceInterface dao) {
        super(dao);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        String name = ((Table) getObject()).getName();
        List items = ((Table) getObject()).getItemList();

        ((TableEditionActivity) view).setName(name);
        ((TableEditionActivity) view).setListItems(items);
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new Table(Constants.INVALID_ID, Constants.INVALID_ID);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setItemTitle(int index, String name) {
        ((Table) getObject()).getItemList().get(index).setName(name);
        ((TableEditionActivity) getView()).onListItemChange();
    }

    public void setItemWeight(int index, int weight) {
        ((Table) getObject()).getItemList().get(index).setWeight(weight);
        ((TableEditionActivity) getView()).onListItemChange();
    }

    public void setUniverseId(long universeId) {
        ((Table) getObject()).setUniverseId(universeId);
    }

    public void addNewItem() {
        addNewItem("");
    }

    public void addNewItem(String defaultName) {
        TableItem item = new TableItem(Constants.INVALID_ID, Constants.INVALID_ID);
        item.setName(defaultName);
        ((Table) getObject()).getItemList().add(item);
        ((TableEditionActivity) getView()).onListItemChange();
    }

    public void removeItem(ObjectWithIdentifier item) {
        ((Table) getObject()).getItemList().remove(item);
        ((TableEditionActivity) getView()).onListItemChange();
    }

    public void removeItem(int index) {
        ((Table) getObject()).getItemList().remove(index);
        ((TableEditionActivity) getView()).onListItemChange();
    }

}

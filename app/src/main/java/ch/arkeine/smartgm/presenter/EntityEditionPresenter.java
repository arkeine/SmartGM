package ch.arkeine.smartgm.presenter;

import java.util.List;

import ch.arkeine.smartgm.Constants;
import ch.arkeine.smartgm.model.dao.object.Entity;
import ch.arkeine.smartgm.model.dao.object.EntityValue;
import ch.arkeine.smartgm.model.dao.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.model.database.dal.EntityPersistence;
import ch.arkeine.smartgm.view.DataEditionActivity;
import ch.arkeine.smartgm.view.EntityEditionActivity;

/**
 * Presenter for the entity edition
 */
public class EntityEditionPresenter extends DescribableEditionPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public EntityEditionPresenter(EntityPersistence dao) {
        super(dao);
    }

    /* ============================================ */
    // OVERRIDE
    /* ============================================ */

    @Override
    protected void updateView(DataEditionActivity view) {
        String name = ((Entity) getObject()).getName();
        List value = ((Entity) getObject()).getListValues();

        ((EntityEditionActivity) view).setName(name);
        ((EntityEditionActivity) view).setListItems(value);
    }

    @Override
    protected ObjectWithIdentifier getNewObject() {
        return new Entity(Constants.INVALID_ID, Constants.INVALID_ID, null);
    }

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    public void setItemTitle(int index, String name) {
        ((Entity) getObject()).getListValues().get(index).setName(name);
        ((EntityEditionActivity) getView()).onListItemChange();
    }

    public void setType(Entity.Type type) {
        ((Entity) getObject()).setType(type);
        ((EntityEditionActivity) getView()).onListItemChange();
    }

    public void setValue(int index, int weight) {
        ((Entity) getObject()).getListValues().get(index).setValue(weight);
        ((EntityEditionActivity) getView()).onListItemChange();
    }

    public void setUniverseId(long universeId) {
        ((Entity) getObject()).setUniverseId(universeId);
    }

    public void addNewItem() {
        addNewItem("");
    }

    public void addNewItem(String defaultName) {
        EntityValue item = new EntityValue(Constants.INVALID_ID, Constants.INVALID_ID);
        item.setName(defaultName);
        ((Entity) getObject()).getListValues().add(item);
        ((EntityEditionActivity) getView()).onListItemChange();
    }

    public void removeItem(ObjectWithIdentifier item) {
        ((Entity) getObject()).getListValues().remove(item);
        ((EntityEditionActivity) getView()).onListItemChange();
    }

    public void removeItem(int index) {
        ((Entity) getObject()).getListValues().remove(index);
        ((EntityEditionActivity) getView()).onListItemChange();
    }
}

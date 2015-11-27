package ch.arkeine.smartgm.presenter;

import ch.arkeine.smartgm.model.dao.DAOInterface;
import ch.arkeine.smartgm.model.object.ObjectWithIdentifier;
import ch.arkeine.smartgm.view.DataEditionActivity;


/**
 * Created by Arkeine on 12.11.2015.
 */
public abstract class DataEditionActivityPresenter {

    /* ============================================ */
    // CONSTRUCTOR
    /* ============================================ */

    public DataEditionActivityPresenter(DAOInterface dao) {
        this.dao = dao;
        this.objExist = false;
    }

    /* ============================================ */
    // ABSTRACT
    /* ============================================ */

    protected abstract void updateView(DataEditionActivity view);

    protected abstract ObjectWithIdentifier getNewObject();

	/* ============================================ */
    // ASSESSOR / MUTATOR
    /* ============================================ */

    /**
     * This method is called by the view to register with the presenter
     *
     * @param view the view that the presenter will update
     */
    public void onTakeView(DataEditionActivity view) {
        this.obj = getNewObject();
        this.objExist = false;
        this.view = view;
        publish();
    }

    public void saveObjectDAO() {
        if (!objExist) {
            dao.create(obj);
        } else {
            dao.update(obj);
        }

        publish();
    }

    public void loadObjectDAO(long id) {
        ObjectWithIdentifier tmp = dao.find(id);
        if (tmp != null) {
            obj = tmp;
            objExist = true;
            publish();
        }
    }

    protected ObjectWithIdentifier getObject() {
        return obj;
    }

    protected void updateView() {
        publish();
    }

    protected DataEditionActivity getView() {
        return view;
    }

    /* ============================================ */
    // PRIVATE
    /* ============================================ */

    /**
     * Load the data from model to the view
     */
    private void publish() {
        if (view != null) {
            updateView(view);
        }
    }

    /* ============================================ */
    // FIELD
    /* ============================================ */

    private boolean objExist;
    private ObjectWithIdentifier obj;
    private DataEditionActivity view;
    private final DAOInterface<ObjectWithIdentifier> dao;
}

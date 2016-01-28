package ch.arkeine.smartgm.model.handler;

/**
 * Created by Arkeine on 27.01.2016.
 */
public interface IdentifiedDataObject {
    long getUniqueId();
    Class getDataType();
}

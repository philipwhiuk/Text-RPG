package com.whiuk.philip.rpg.core;

/**
 * @author Philip
 */
public abstract class DataSource {
    /**
     * Returns the data associated with a unique identifier.
     * @param identifier The unique ID
     * @return The data
     */
    public abstract Data getData(Identifier identifier);
}

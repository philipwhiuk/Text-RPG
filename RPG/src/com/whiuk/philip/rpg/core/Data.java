package com.whiuk.philip.rpg.core;

import java.util.Collection;
/**
 * Represents stored data.
 * @author Philip
 *
 */
public interface Data {
    /**
     * Retrieves the unique identifier of the data.
     * @return The identifier
     */
    Identifier getIdentifier();
    /**
     * Retrieves the String value set for a given property.
     * @param property The property to retrieve
     * @return The value
     */
    String getPropertyString(String property);
    /**
     * Retrieves the collection of IDs related to the object for
     * a given relationship.
     * @param string The relationship
     * @return The collection of IDs
     */
    Collection<Identifier> getRelationOneToManyIDs(String string);

}

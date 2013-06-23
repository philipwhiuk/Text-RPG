package com.whiuk.philip.rpg.core;

/**
 * Base class for an object that is stored and retrieved.
 * @author Philip
 *
 */
public abstract class Storable {
    /**
     * Loads data into an existed stored object.
     * @param storable The existing object
     * @param data The data to load from
     */
    public static void loadInto(final Storable storable,
            final Data data) {
        storable.identifier = data.getIdentifier();
    }

    /**
     * The unique identifier for the storable object.
     */
    private Identifier identifier;

    /**
     * Blank constructor.
     */
    Storable() {
    }

    /**
     * Retrieves the identifier for the object.
     * @return the identifier
     */
    public final Identifier getIdentifier() {
        return identifier;
    }

}

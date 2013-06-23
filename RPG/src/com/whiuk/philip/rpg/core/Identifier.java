package com.whiuk.philip.rpg.core;

/**
 * Represents a unique identifier.
 * @author Philip
 *
 */
public abstract class Identifier {

    /**
     * Returns an identifier from a string.
     * @param value The string to parse
     * @return The identifier
     */
    public static Identifier parseID(final String value) {
        return DataSourceManager.parseIdentifier(value);
    }
}

package com.whiuk.philip.rpg.core;

/**
 * Indicates there was an error manipulating a data source.
 * @author Philip
 *
 */
public class DataSourceException extends Exception {

    /**
     * Serialization version unique ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Message-based constructor.
     * @param message The message to pass
     */
    public DataSourceException(final String message) {
        super(message);
    }

}

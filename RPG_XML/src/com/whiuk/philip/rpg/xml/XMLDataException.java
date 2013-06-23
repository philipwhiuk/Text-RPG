package com.whiuk.philip.rpg.xml;

/**
 * Indicates the XML Data is invalid.
 * @author Philip
 *
 */
public class XMLDataException extends RuntimeException {

    /**
     * Generated serialization ID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param cause Cause of the exception
     */
    public XMLDataException(final Exception cause) {
        super(cause);
    }

}

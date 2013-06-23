package com.whiuk.philip.rpg.core;

/**
 * Thrown when unable to format an identifier.
 * @author Philip
 *
 */
public class IdentifierFormatException extends RuntimeException {
    /**
     * Serialisation value.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor.
     * @param e Cause
     */
    public IdentifierFormatException(final Exception e) {
        super(e);
    }

}

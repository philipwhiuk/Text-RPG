package com.whiuk.philip.rpg.xml;

import com.whiuk.philip.rpg.core.Identifier;

/**
 * Represents an identifier based on a unique number.
 * @author Philip
 *
 */
public class NumericIdentifier extends Identifier {
    /**
     * Value of the identifier.
     */
    private final int id;

    /**
     * Constructor.
     * @param value The identifier
     */
    public NumericIdentifier(final int value) {
        this.id = value;
    }
    /**
     * Returns the ID.
     * @return value of identifier
     */
    public final int getId() {
        return id;
    }
    @Override
    public final String toString() {
        return Integer.toString(id);
    }
    @Override
    public final boolean equals(final Object obj) {
        if (!(obj instanceof NumericIdentifier)) {
            return false;
        } else {
            return this.id == ((NumericIdentifier) obj).id;
        }
    }
    @Override
    public final int hashCode() {
        return id;
    }
}

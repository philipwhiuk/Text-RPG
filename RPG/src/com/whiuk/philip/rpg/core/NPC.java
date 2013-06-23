package com.whiuk.philip.rpg.core;

import java.util.Collection;

/**
 * A non-player character.
 * @author Philip
 */
public final class NPC {
    /**
     * 
     */
    public static Conversation lastConversation;
    /**
     * Loads a collection of NPCs from unique identifiers.
     * @param relationOneToManyIDs
     *            A collection of ids
     * @return The NPCs
     */
    public static Collection<? extends NPC> loadAll(
            final Collection<Identifier> relationOneToManyIDs) {
        // TODO Auto-generated method stub
        return null;
    }
    /**
     * Blank constructor.
     */
    private NPC() {
    }
}

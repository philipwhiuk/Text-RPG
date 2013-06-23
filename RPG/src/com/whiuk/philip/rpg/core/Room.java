package com.whiuk.philip.rpg.core;

/**
 * @author Philip
 */
public final class Room extends Location {

    /**
     * Returns a room with a given identifier.
     * @param identifier
     *            The unique identifier
     * @return The room (or null if it doesn't exist;
     */
    public static Room load(final Identifier identifier) {
        final Data data = DataSourceManager.getData(Room.class, identifier);
        if (data == null) {
            return null;
        }
        final Room room = new Room();
        Location.loadInto(room, data);
        return room;
    }

    /**
     * Blank constructor.
     */
    private Room() {
    }
}

package com.whiuk.philip.rpg.core;

import java.util.Collection;
import java.util.Map;

/**
 * A location in the game.
 * @author Philip
 *
 */
public class Location extends Storable {
    /**
     * The exits available from this location.
     */
    private Map<Direction, Identifier> exits;
    /**
     * The name of the location.
     */
    private String name;
    /**
     * The description of the location.
     */
    private String description;
    /**
     * Text shown on entering.
     */
    private String onEnterText;
    /**
     * Text shown on leaving.
     */
    private String onExitText;
    /**
     * NPCs present at this location.
     */
    private Collection<Identifier> npcs;
    /**
     * Items present at this location.
     */
    private Collection<Identifier> items;
    /**
     * Blank constructor.
     */
    Location() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * Loads data from a source into an existing object.
     * @param location The object to load into
     * @param data The data to use as a source
     */
    public static void loadInto(final Location location, final Data data) {
        Storable.loadInto(location, data);
        location.setName(data.getPropertyString("name"));
        location.setDescription(data.getPropertyString("description"));
        location.setOnEnterText(data.getPropertyString("onEnterText"));
        location.setOnExitText(data.getPropertyString("onExitText"));
        location.npcs = data.getRelationOneToManyIDs("npc");
        location.items = data.getRelationOneToManyIDs("item");
    }
    /**
     * @return the description
     */
    public final String getDescription() {
        return description;
    }
    /**
     * Set the description.
     * @param text the text to set as the description
     */
    public final void setDescription(final String text) {
        this.description = text;
    }
    /**
     * Get the name.
     * @return the name
     */
    public final String getName() {
        return name;
    }
    /**
     * Set the name.
     * @param value the value to set the name to
     */
    public final void setName(final String value) {
        this.name = value;
    }
    /**
     * Get the text to display on entering.
     * @return the onEnterText
     */
    public final String getOnEnterText() {
        return onEnterText;
    }
    /**
     * Set the text to display on entering.
     * @param text the onEnterText to set
     */
    public final void setOnEnterText(final String text) {
        this.onEnterText = text;
    }
    /**
     * Get the text to display on exiting.
     * @return the onExitText
     */
    public final String getOnExitText() {
        return onExitText;
    }
    /**
     * Set the text to display on exiting.
     * @param text the onExitText to set
     */
    public final void setOnExitText(final String text) {
        this.onExitText = text;
    }
    /**
     * Get the exit in a selected direction.
     * @param d The direction
     * @return The exit (or null)
     */
    public final Identifier getExit(final Direction d) {
        return exits.get(d);
    }
    /**
     * Set the exit in a selected direction.
     * @param d The direction
     * @param e The exit
     */
    public final void setExit(final Direction d, final Exit e) {
        exits.put(d, e.getIdentifier());
    }
    /**
     * Loads a location from it's identifier.
     * @param identifier the unique id
     * @return the location
     */
    public static Location load(Identifier identifier) {
        final Data data = DataSourceManager.getData(Location.class, identifier);
        if (data == null) {
            return null;
        }
        final Location location = new Location();
        Location.loadInto(location, data);
        return location;
    }
}

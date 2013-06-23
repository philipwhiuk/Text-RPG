package com.whiuk.philip.rpg.core;

/**
 * Recieves events from the game.
 * @author Philip
 *
 */
public interface GameEventListener {
    /**
     * Fired when the location the player is in changes.
     * @param name The new location
     */
    void locationChanged(String name);

}

package com.whiuk.philip.rpg.core;

/**
 * Indicates the object can be used to send output to regarding game events.
 * @author Philip
 *
 */
public interface Output extends GameEventListener {

    /**
     * Sends an error message.
     * @param message The error
     */
    void sendErrorMessage(String message);

    /**
     * Sends a single message.
     * @param message The message
     */
    void sendMessage(String message);

    /**
     * Sends multiple messages at once.
     * @param list The messages to send
     */
    void sendMultiMessage(String[] list);

}

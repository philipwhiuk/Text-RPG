package com.whiuk.philip.rpg.core;

import com.whiuk.philip.rpg.actionHandlers.ActionHandler;

/**
 * @author Philip
 *
 */
public final class CommandProcessor {
    /**
     * @param input Input string
     * @param handler Handler
     * @param output Output string
     */
    public static void process(final String input, final ActionHandler handler,
            final Output output) {
        String[] inputData = input.split(" ", 1);
        if (inputData.length == 1) {
            process(inputData[0], null, handler, output);
        } else {
            process(inputData[0], inputData[1], handler, output);
        }
    }
    /**
     * @param command Command
     * @param more Any further data
     * @param handler Handler
     * @param output Output string
     */
    public static void process(final String command, final String more,
            final ActionHandler handler, final Output output) {
        switch(command) {
            //Movement
            case "MOVE":
                handler.getMovementHandler().move(more); break;
            //Communication & Trade
            case "TALK":
                handler.getNPCHandler().talk(more); break;
            case "BUY":
                handler.getNPCHandler().buy(more); break;
            case "SELL":
                handler.getNPCHandler().sell(more); break;
            case "ACCEPT":
                handler.getNPCHandler().accept(more); break;
            case "DECLINE":
                handler.getNPCHandler().decline(more); break;

            //Combat
            case "ATTACK":
                handler.getCombatHandler().attack(more); break;
            case "FIGHT":
                handler.getCombatHandler().fight(more); break;
                
            //Spells
            case "CAST":
                handler.getMagicHandler().cast(more); break;

            //ITEMS
            case "USE":
                handler.getItemHandler().use(more); break;
            case "SHOW":
                handler.getItemHandler().show(more); break;
            case "TAKE":
                handler.getItemHandler().take(more); break;
            case "DROP":
                handler.getItemHandler().drop(more); break;
            case "EQUIP":
                handler.getItemHandler().equip(more); break;
            case "UNEQUIP":
                handler.getItemHandler().unequip(more); break;
            case "REMOVE":
                handler.getItemHandler().remove(more); break;
            case "INVENTORY":
                handler.getItemHandler().inventory(more); break;
            case "EXAMINE":
                handler.getItemHandler().examine(more); break;

            //Info
            case "Quests":
                handler.getGameHandler().quests(more); break;
            case "Stats":
                handler.getGameHandler().stats(more); break;
                
            //Game State
            case "HELP":
                handler.getGameHandler().help(more); break;
            case "SAVE":
                handler.getGameHandler().save(more); break;
            case "EXIT":
                handler.getGameHandler().exit(more); break;
            default:
                output.sendErrorMessage("Unknown action."); break;
        }
    }
    /**
     * Utility class.
     */
    private CommandProcessor() {
    }
}

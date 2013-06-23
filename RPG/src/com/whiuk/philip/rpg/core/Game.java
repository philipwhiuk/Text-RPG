package com.whiuk.philip.rpg.core;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * A game in the system.
 * @author Philip
 *
 */
public class Game extends Thread {
    /**
     * Class logger.
     */
    private static Logger logger = Logger.getLogger(Game.class);
    /**
     * The game currently running.
     */
    private static Game game;
    /**
     * Options available when no game is loaded.
     */
    private static final String[] GAME_OPTIONS = {"NEW", "LOAD", "QUIT"};
    /**
     * The output stream used to send messages.
     */
    private Output output;
    /**
     * The action handler to respond to commands.
     */
    private ActionHandler handler;
    /**
     * Options list.
     */
    private String[] options = {"MOVE", "TALK", "BUY", "SELL", "FIGHT",
            "USE", "SHOW", "CAST", "TAKE", "DROP", "EQUIP", "REMOVE",
            "ACCEPT", "DECLINE", "SAVE", "EXIT"};
    /**
     * Scenario.
     */
    private Scenario scenario;
    /**
     * Objects listening to game events.
     */
    private List<GameEventListener> listeners =
            new ArrayList<GameEventListener>();
    /**
     * Current player location.
     */
    private Location location;
    /**
     * Constructor.
     * @param s The scenario
     */
    public Game(final Scenario s) {
        super("Game");
        this.scenario = s;
        // TODO Auto-generated constructor stub
    }
    @Override
    public void run() {
        logger.info("Sending start text");
        output.sendMessage(scenario.getStartText());
        logger.info("Updating location");
        setLocation(Location.load(scenario.getStartLocation()));
    }
    /**
     * @param location The new location
     */
    private void setLocation(Location location) {
        this.location = location;
        fireLocationChanged(location.getName());
        output.sendMessage(location.getOnEnterText());
    }
    /**
     * Fired when the location changes
     * @param name
     */
    private void fireLocationChanged(String name) {
        for (GameEventListener l : listeners) {
            l.locationChanged(name);
        }
    }
    /**
     * Processes game input.
     * @param input The input to the game
     */
    private void processInput(final String input) {
        String[] inputData = input.split(" ", 1);
        switch(inputData[0]) {
            case "MOVE":
                handler.move(inputData[1]); break;
            case "TALK":
                handler.talk(inputData[1]); break;
            case "BUY":
                handler.buy(inputData[1]); break;
            case "SELL":
                handler.sell(inputData[1]); break;
            case "FIGHT":
                handler.fight(inputData[1]); break;
            case "USE":
                handler.use(inputData[1]); break;
            case "SHOW":
                handler.show(inputData[1]); break;
            case "CAST":
                handler.cast(inputData[1]); break;
            case "TAKE":
                handler.talk(inputData[1]); break;
            case "DROP":
                handler.drop(inputData[1]); break;
            case "EQUIP":
                handler.equip(inputData[1]); break;
            case "REMOVE":
                handler.remove(inputData[1]); break;
            case "ACCEPT":
                handler.accept(inputData[1]); break;
            case "DECLINE":
                handler.decline(inputData[1]); break;
            case "SAVE":
                handler.save(inputData[1]); break;
            case "EXIT":
                handler.exit(inputData[1]); break;
            default:
                output.sendErrorMessage("Unknown action."); break;
        }
    }
    /**
     * Retrieves the output target.
     * @return The target for output
     */
    public final Output getOutput() {
        return output;
    }
    /**
     * Changes the output destination.
     * @param target The target for output.
     */
    private void setOutput(final Output target) {
        output = target;
    }
    /**
     * Adds a game event listener.
     * @param l the listener
     */
    public void addGameEventListener(GameEventListener l) {
        listeners.add(l);
    }
    /**
     * Removes a game event listener.
     * @param l the listener
     */
    public void removeGameEventListener(GameEventListener l) {
        listeners.remove(l);
    }    
    /**
     * The options available.
     * @return An array of options
     */
    public static String[] getOptions() {
        if (game != null) {
            return game.options;
        } else {
            return GAME_OPTIONS;
        }
    }
    /**
     * Processes input to the game.
     * @param input The input message
     * @param output The output target
     */
    public static void processInput(final String input, final Output output) {
        String[] commands = input.trim().split(" ", 2);
        switch(commands[0].toUpperCase()) {
            case "NEW":
                if (commands.length > 1) {
                    newGame(commands[1], output);
                } else {
                    newGame(null, output);
                }
                return;
            case "LOAD":
                if (commands.length > 1) {
                        loadGame(commands[1], output);
                } else {
                    newGame(null, output);
                }
                return;
            default:
                //
                break;
        }
        if (game != null) {
            game.processInput(input.trim());
        } else {
            output.sendErrorMessage("Unknown action.");
        }
    }
    /**
     * Load an existing game.
     * @param parameters Information on the source of the game data.
     * @param output The output stream to send information to.
     */
    private static void loadGame(final String parameters, final Output output) {
        if (game != null) {
            output.sendErrorMessage("A game is already in progress");
        } else {
            game = load(parameters);
            game.setOutput(output);
        }
    }
    /**
     * Loads an existing game.
     * @param parameters Parameters to load the game
     * @return The game
     */
    private static Game load(final String parameters) {
        // TODO Auto-generated method stub
        return new Game(Scenario.load(Identifier.parseID(parameters)));
    }
    /**
     * Creates a new game.
     * @param identifier The id of the scenario
     * @param output The place to direct game output
     */
    private static void newGame(final String identifier, final Output output) {
        if (game != null) {
            output.sendErrorMessage("A game is already in progress");
        } else {
            if (identifier != null) {
                game = new Game(Scenario.load(Identifier.parseID(identifier)));
                game.addGameEventListener(output);
                game.setOutput(output);
                game.start();
            } else {
                output.sendMessage("Please select the id of a scenario "
                        + "when starting a new game");
                output.sendMessage("Available scenarios:");
                output.sendMultiMessage(Scenario.list());
            }
        }
    }
}

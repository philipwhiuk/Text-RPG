package com.whiuk.philip.rpg.core;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * A generated world and environment to explore.
 * @author Philip
 *
 */
public class Scenario extends Storable {
    /**
     * The class logger.
     */
    private static Logger logger = Logger.getLogger(Scenario.class);
    /**
     * The name of the scenario.
     */
    private String name;
    /**
     * Text shown when starting the scenario.
     */
    private String startText;
    /**
     * ID of the starting location.
     */
    private Identifier startLocation;

    /**
     * Loads a scenario corresponding to a given ID.
     * @param identifier The unique ID
     * @return The scenario.
     */
    public static Scenario load(final Identifier identifier) {
        final Data data = DataSourceManager.getData(Scenario.class, identifier);
        if (data == null) {
            logger.info("Null data object for identifier: " + identifier);
            return null;
        }
        final Scenario scenario = new Scenario();
        Storable.loadInto(scenario, data);
        scenario.name = data.getPropertyString("name");
        scenario.startText = data.getPropertyString("startText");
        logger.info("Start Text:" + scenario.startText);
        scenario.startLocation = Identifier.parseID(data.getPropertyString("startLocation"));        
        return scenario;
    }
    /**
     * Produce a list of the scenarios.
     * @return List of scenarios.
     */
    public static String[] list() {
        Data data = DataSourceManager.getData(Scenario.class, null);
        logger.info("Data: " + data);
        Collection<Scenario> scenarios = loadAll(
                data.getRelationOneToManyIDs("scenario"));
        logger.info("Found " + scenarios.size() + " scenarios.");
        String[] titles = new String[scenarios.size()];
        int i = 0;
        for (Scenario s: scenarios) {
            logger.info("Processing Scenario: " + s);
            titles[i] = ""+s.getIdentifier()+". "+s.getName();
            i++;
        }
        return titles;
    }
    /**
     * Loads all the scenarios from their IDs.
     * @param ids The collection of identifiers.
     * @return The collection of scenarios
     */
    private static Collection<Scenario> loadAll(
            final Collection<Identifier> ids) {
        Collection<Scenario> scenarios = new ArrayList<Scenario>();
        for (Identifier id : ids) {
            scenarios.add(load(id));
        }
        return scenarios;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param value the name to set
     */
    public final void setName(final String value) {
        this.name = value;
    }

    /**
     * @return the startText
     */
    public final String getStartText() {
        return startText;
    }

    /**
     * @param text the startText to set
     */
    public final void setStartText(final String text) {
        this.startText = text;
    }
    /**
     * @return the start location
     */
    public Identifier getStartLocation() {
        return startLocation;
    }

}

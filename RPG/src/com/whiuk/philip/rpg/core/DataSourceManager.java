package com.whiuk.philip.rpg.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages access to @link{DataSource}s.
 * @author Philip
 *
 */
public final class DataSourceManager {
    /**
     * The list of data sources.
     */
    private static List<Class<? extends DataSource>> dataSources =
            new ArrayList<Class<? extends DataSource>>();
    /**
     * The default source.
     */
    private static Class<? extends DataSource> defaultDataSource;

    /**
     * De-register a data source. Can't deregister the default
     * @param dataSource The source to de-register
     * @throws DataSourceException Thrown if the source is the default
     */
    public static void deRegisterDataSource(final DataSource dataSource)
            throws DataSourceException {
        if (defaultDataSource.equals(dataSource)) {
            throw new DataSourceException("Data source is default source.");
        } else {
            dataSources.remove(dataSource);
        }
    }
    /**
     * Retrieves data for an object of a given class with a provided ID.
     * @param clazz The class
     * @param identifier The identifier of the object to fetch.
     * @return The data
     */
    public static Data getData(final Class<? extends Storable> clazz,
            final Identifier identifier) {

        Data data;
        Method m;
        try {
            m = defaultDataSource.getMethod("getSourceFor", Class.class);
            data = ((DataSource) m.invoke(null, clazz)).getData(identifier);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (data != null) {
            return data;
        }
        for (final Class<? extends DataSource> dataSource : dataSources) {
            try {
                m = dataSource.getMethod("getSourceFor", Class.class);
                data = ((DataSource) m.invoke(null, clazz)).getData(identifier);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (data != null) {
                return data;
            }
        }
        return null;
    }

    /**
     * Retrieve the default data source.
     * @return The data source
     */
    public static Class<? extends DataSource> getDataSource() {
        return defaultDataSource;
    }
    /**
     * Get a data source by entry number.
     * @param source The number of entry
     * @return The source
     */
    public static DataSource getDataSource(final int source) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Register a data source and optionally set it as default.
     * @param clazz The source to register
     * @param defaultSource Whether to set it as the default
     */
    public static void registerDataSource(
            final Class<? extends DataSource> clazz,
            final boolean defaultSource) {
        dataSources.add(clazz);
        if (defaultSource) {
            defaultDataSource = clazz;
        }
    }

    /**
     * Removing public access to utility class.
     */
    private DataSourceManager() {
    }
    /**
     * Parse a given identifier from a string.
     * Expected to potientially throw a {@link RuntimeException} -
     * {@link IdentifierFormatException}
     * @param idString The identifier as a string
     * @return the identifier
     */
    public static Identifier parseIdentifier(final String idString) {
        Method m;
        try {
            m = defaultDataSource.getMethod("parseIdentifier", String.class);
            return (Identifier) m.invoke(null, idString);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

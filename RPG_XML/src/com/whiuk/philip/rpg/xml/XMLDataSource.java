package com.whiuk.philip.rpg.xml;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.whiuk.philip.rpg.core.Data;
import com.whiuk.philip.rpg.core.DataSource;
import com.whiuk.philip.rpg.core.Identifier;
import com.whiuk.philip.rpg.core.IdentifierFormatException;
import com.whiuk.philip.rpg.core.Storable;

/**
 * @author Philip
 */
public class XMLDataSource extends DataSource {
    /**
     * Sources map.
     */
    private static HashMap<Class<? extends Storable>, XMLDataSource> sources =
            new HashMap<Class<? extends Storable>, XMLDataSource>();
    /**
     * Logger.
     */
    private Logger logger = Logger.getLogger(XMLDataSource.class);
    /**
     * XMLData elements.
     */
    private Map<Identifier, XMLData> elements =
            new HashMap<Identifier, XMLData>();
    /**
     * XML Document object.
     */
    private Document doc;

    /**
     * Root element as an XML Data Object.
     */
    private XMLData root;

    /**
     * Constructor based on filename.
     * @param file
     *            The file to load from
     */
    public XMLDataSource(final File file) {
        try {
            doc = DocumentBuilderFactory.
                    newInstance().newDocumentBuilder().parse(file);
            doc.getDocumentElement().normalize();
            NodeList list = doc.getDocumentElement().getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) list.item(i);
                    logger.info("Parsing: " + e.getTagName());
                    XMLData d = new XMLData(e);
                    logger.info("Putting: "
                            + d.getIdentifier() + ", " + d);
                    elements.put(d.getIdentifier(), d);
                }
            }
            root = new XMLData(doc.getDocumentElement());
        } catch (IOException | SAXException | ParserConfigurationException e) {
            throw new XMLDataException(e);
        }

    }

    /**
     * Constructor based on object name.
     * @param name
     *            The name object to load the data source for
     */
    public XMLDataSource(final String name) {
        this(new File(name + ".xml"));
    }

    @Override
    public final Data getData(final Identifier identifier) {
        logger.info("Getting data for: " + identifier);
        if (identifier != null) {
            logger.info("Returning " + elements.get(identifier));
            return elements.get(identifier);
        } else {
            return root;
        }
    }

    /**
     * Retrieves the source for a class.
     * @param clazz The storable object class
     * @return The data source
     */
    public static DataSource getSourceFor(
            final Class<? extends Storable> clazz) {
        if (!sources.containsKey(clazz)) {
            sources.put(clazz, new XMLDataSource(clazz.getSimpleName()));
        }
        return sources.get(clazz);
    }
    /**
     * Turns a string into the represented ID.
     * @param id The string to parse
     * @return The corresponding ID.
     */
    public static final Identifier parseIdentifier(final String id) {
        try {
            return new NumericIdentifier(Integer.parseInt(id));
        } catch (NumberFormatException e) {
            throw new IdentifierFormatException(e);
        }
    }

}

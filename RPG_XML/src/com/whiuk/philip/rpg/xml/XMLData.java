package com.whiuk.philip.rpg.xml;

import java.util.Collection;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.whiuk.philip.rpg.core.Data;
import com.whiuk.philip.rpg.core.Identifier;

/**
 * @author Philip
 */
public class XMLData implements Data {
    /**
     * Class logger.
     */
    private static Logger logger = Logger.getLogger(XMLData.class);
    /**
     * Element to retrieve information from.
     */
    private final Element element;
    /**
     * Unique identifier for the data.
     */
    private final Identifier identifier;

    /**
     * Constructor.
     * @param e The element for this data.
     */
    XMLData(final Element e) {
        element = e;
        logger.info("ID: " + element.getAttribute("id"));
        identifier = NumericIdentifier.parseID(element.getAttribute("id"));
    }

    @Override
    public final Identifier getIdentifier() {
        return identifier;
    }

    @Override
    public final String getPropertyString(final String name) {
        if(element.hasAttribute(name)) {
            return element.getAttribute(name);
        } else if(element.getElementsByTagName(name).getLength() > 0) {
            return element.getElementsByTagName(name).item(0).getTextContent();
        } else {
            return null;
        }
    }

    @Override
    public final Collection<Identifier> getRelationOneToManyIDs(
            final String tag) {
        NodeList list = element.getElementsByTagName(tag);
        ArrayList<Identifier> ids = new ArrayList<Identifier>();
        for (int i = 0; i < list.getLength(); i++) {
            if (list.item(i).getNodeType() == Node.ELEMENT_NODE) {
                ids.add(
                        NumericIdentifier.parseID(
                                ((Element) list.item(i)).getAttribute("id")
                        )
                );
            }
        }
        return ids;
    }

}

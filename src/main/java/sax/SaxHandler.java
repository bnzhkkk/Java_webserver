package sax;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import reflection.ReflectionHelp;

public class SaxHandler extends DefaultHandler {
    private static final String CLASSNAME = "class";
    private String string = null;
    private Object object = null;

    public Object getObject() { return object; }

    public void startDocument() throws SAXException {
        System.out.println("Start document");
    }

    public void endDocument() throws SAXException {
        System.out.println("End document ");
    }

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (!qName.equals(CLASSNAME)) {
            string = qName;
        } else {
            String className = attributes.getValue(0);
            System.out.println("Class name: " + className);
            object = ReflectionHelp.createInstance(className);
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        string = null;
    }

    public void characters(char ch[], int start, int length) throws SAXException {
        if (string != null) {
            String value = new String(ch, start, length);
            System.out.println(string + " = " + value);
            ReflectionHelp.setFieldValue(object, string, value);
        }
    }
}
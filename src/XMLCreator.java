import com.jcabi.xml.XML;
import com.jcabi.xml.XSL;
import com.jcabi.xml.XSLDocument;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.InputStream;


public class XMLCreator {

    private int iteration;

    public void setIteration(int iteration) {
        this.iteration = iteration;
    }

    public int getIteration() {
        return iteration;
    }

    public void entriesElement() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        DBConnect dbConnect = new DBConnect();
        int [] number = dbConnect.querySelect();
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElementNS("", "entries");
            doc.appendChild(rootElement);
            for (int i = 1; i <= number.length; i++) {
                this.setIteration(i);
                rootElement.appendChild(getEntry(doc, this.getIteration()));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult file = new StreamResult(new File("/1.xml")); // Если используется  ОС windows, то файл создается в корне диска C:
            transformer.transform(source, file);

            System.out.println("Создание файла 1.xml закончено");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Node getEntry(Document doc, int number) {
        Element entry = doc.createElement("entry");
        entry.appendChild(getEntryElements(doc, entry, "field", number));
        return entry;
    }

    private static Node getEntryElements(Document doc, Element element, String name, int value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(String.valueOf(value)));
        return node;
    }
}

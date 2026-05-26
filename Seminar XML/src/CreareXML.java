import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.w3c.dom.Comment;
import java.io.File;

public class CreareXML {

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        Comment comentariu = doc.createComment(" Lista de studenti - generata cu DOM/JAXP ");
        doc.appendChild(comentariu);

        Element radacina = doc.createElement("studenti");
        doc.appendChild(radacina);

        radacina.appendChild(creeazaStudent(doc, "1", "Ionescu", "Ana", "Informatica", 9.75));
        radacina.appendChild(creeazaStudent(doc, "2", "Popescu", "Mihai", "Cibernetica", 8.50));
        radacina.appendChild(creeazaStudent(doc, "3", "Georgescu", "Elena", "Informatica", 10.00));
        radacina.appendChild(creeazaStudent(doc, "4", "Constantin", "Andrei", "Statistica", 7.80));

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        transformer.setOutputProperty(OutputKeys.VERSION, "1.0");

        DOMSource sursa = new DOMSource(doc);

        File fisierIesire = new File("studenti.xml");
        StreamResult rezultat = new StreamResult(fisierIesire);

        transformer.transform(sursa, rezultat);

        System.out.println("Fisierul XML a fost creat cu succes: " + fisierIesire.getAbsolutePath());

        StreamResult consola = new StreamResult(System.out);
        transformer.transform(sursa, consola);
    }

    private static Element creeazaStudent(Document doc, String id,
                                          String nume, String prenume, String specializare, double medie) {

        Element student = doc.createElement("student");
        student.setAttribute("id", id);

        Element eNume = doc.createElement("nume");
        Text tNume = doc.createTextNode(nume);
        eNume.appendChild(tNume);
        student.appendChild(eNume);

        Element ePrenume = doc.createElement("prenume");
        ePrenume.appendChild(doc.createTextNode(prenume));
        student.appendChild(ePrenume);

        Element eSpec = doc.createElement("specializare");
        eSpec.appendChild(doc.createTextNode(specializare));
        student.appendChild(eSpec);

        Element eMedie = doc.createElement("medie");
        eMedie.appendChild(doc.createTextNode(String.valueOf(medie)));
        student.appendChild(eMedie);

        return student;
    }
}
package gmConverter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.GoalModel;

public class XmlParser {

	public void start(GoalModel goalModel) {
		
		String filePath = "C:\\Users\\user\\OneDrive\\Tesi\\SimPlant_Reports\\XMLReport.xml";
		
		// Instantiate the Factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			
			// optional, but recommended
	        // process XML securely, avoid attacks like XML External Entities (XXE)
	        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
	        
	        // parse XML file
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        
	        Document doc = db.parse(new File(filePath));
	        
	        // optional, but recommended
	        // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	        doc.getDocumentElement().normalize();
	        
	        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
	        System.out.println("------");
	        
	        // get <task>
	        NodeList list = doc.getElementsByTagName("task");
	        
	        for (int i=0; i < list.getLength(); i++) {
	        	Node node = list.item(i);
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		Element element = (Element) node;
	        		
	        		// get task's attribute
	        		String id = element.getAttribute("xmlns");
	        		
	        		// get text
	        		String taskName = element.getElementsByTagName("Task_name").item(0).getTextContent();
	        		String description = element.getElementsByTagName("Description").item(0).getTextContent();
	        		String energyConsumption = element.getElementsByTagName("Energy_consumption").item(0).getTextContent();
	        		
	        		System.out.println("Current element: " + node.getNodeName());
	        		System.out.println("Task id: " + id);
	        		System.out.println("Task name: " + taskName);
	        		System.out.println("Description: " + description);
	        		System.out.println("Energy consumption: " + energyConsumption);
	        		System.out.println();
	        		
	        	}
	        }
	        
	        // get <Total>
	        NodeList total = doc.getElementsByTagName("Total");

	        for (int i=0; i < total.getLength(); i++) {
	        	Node node = total.item(i);
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		Element element = (Element) node;
	        		
	        		// get Totals's attribute
	        		String id = element.getAttribute("xmlns");
	        		
	        		// get text
	        		String description = element.getElementsByTagName("Description").item(0).getTextContent();
	        		String consumption = element.getElementsByTagName("Consumption").item(0).getTextContent();
	        		
	        		System.out.println("Current element: " + node.getNodeName());
	        		System.out.println("Total id: " + id);
	        		System.out.println("Description: " + description);
	        		System.out.println("Consumption: " + consumption);
	        		System.out.println();
	        		
	        	}
	        }
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}

}

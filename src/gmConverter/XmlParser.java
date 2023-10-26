package gmConverter;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import model.GmSecurityMeasure;
import model.GoalModel;

/**
 * This class parses the xml file coming from Plant Simulation
 */
public class XmlParser {
	/**
	 * This method starts the parsing procedure
	 * @param goalModel is the goal model we need to update
	 */
	public void start(GoalModel goalModel) {
		
		String filePath = "Plant Simulation XML/demo.xml";
		
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
	        
//	        System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
//	        System.out.println("------");
	        
	        // get <task>
	        NodeList list = doc.getElementsByTagName("task");
	        
	        for (int i=0; i < list.getLength(); i++) {
	        	Node node = list.item(i);
	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
	        		taskRoutine(node, goalModel);
	        	}
	        }
	        
//	        // get <Total>
//	        NodeList total = doc.getElementsByTagName("Total");
//
//	        for (int i=0; i < total.getLength(); i++) {
//	        	Node node = total.item(i);
//	        	if (node.getNodeType() == Node.ELEMENT_NODE) {
//	        		totalRoutine(node);
//	        	}
//	        }
			
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * This method actually update the consumptions of security measures
	 * @param smName is the measure name to update
	 * @param floatText is the text to be converted in actual float value
	 * @param goalModel
	 */
	private static void updateSmConsumption(String smName, String floatText, GoalModel goalModel) {
		//First we convert the string of consumption in an actual float
		float consumption = floatExtractor(floatText);
		
		for (GmSecurityMeasure measure : goalModel.getSmArray()) {
			//if we find a measure in goalmodel with same name of that of a task, then we increment the energy consumption with that of the task
			if (measure.getDescription().equals(smName)) {
				measure.updateEnergyConsumption(consumption);
			}
		}
	}
	
	/**
	 * This method deals with the task nodes found in the xml
	 * @param node
	 */
	private static void taskRoutine(Node node, GoalModel goalModel) {
		Element element = (Element) node;
		
		// get task's attribute
		//String id = element.getAttribute("xmlns");
		
		// get text
//		String taskName = element.getElementsByTagName("Task_name").item(0).getTextContent();
//		String description = element.getElementsByTagName("Description").item(0).getTextContent();
		String energyConsumption = element.getElementsByTagName("Energy_consumption").item(0).getTextContent();
		String securityMeasure = element.getElementsByTagName("Security_measure").item(0).getTextContent();
		
		updateSmConsumption(securityMeasure, energyConsumption, goalModel);
		
//		System.out.println("Current element: " + node.getNodeName());
//		System.out.println("Task id: " + id);
//		System.out.println("Task name: " + taskName);
//		System.out.println("Description: " + description);
//		System.out.println("Energy consumption: " + energyConsumption);
//		System.out.println("Security measure: " + securityMeasure);
//		System.out.println();
	}
	
//	/**
//	 * This method deals with the Total node found in the xml
//	 * @param node
//	 */
//	private static void totalRoutine(Node node) {
//		Element element = (Element) node;
//		
//		// get Totals's attribute
//		String id = element.getAttribute("xmlns");
//		
//		// get text
//		String description = element.getElementsByTagName("Description").item(0).getTextContent();
//		String consumption = element.getElementsByTagName("Consumption").item(0).getTextContent();
//		
//		System.out.println("Current element: " + node.getNodeName());
//		System.out.println("Total id: " + id);
//		System.out.println("Description: " + description);
//		System.out.println("Consumption: " + consumption);
//		System.out.println();
//	}
	
	/**
	 * This method extracts the first float value from a string
	 * @param text is the string given in input
	 * @return is the float value extracted
	 */
	private static Float floatExtractor(String text) {
		// Define a regular expression pattern to match float values
        String floatPattern = "[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?";
        
        // Create a Pattern object
        Pattern pattern = Pattern.compile(floatPattern);
        
        // Create a Matcher to find the first match in the input string
        Matcher matcher = pattern.matcher(text);

        // Check if a match was found
        if (matcher.find()) {
            // Extract the matched string and convert it to a float
            String matchedValue = matcher.group();
            try {
                Float floatValue = Float.parseFloat(matchedValue);
                return floatValue;
            } catch (NumberFormatException e) {
                // Handle parsing errors, if any
                e.printStackTrace();
            }
        }

        // If no float value was found, return null or any other suitable value
        return null;
	}

}

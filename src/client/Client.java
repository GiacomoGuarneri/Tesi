package client;

import java.util.ArrayList;
import java.util.Scanner;
import gmConverter.JsonParser;
import gmConverter.JsonRiskParser;
import gmConverter.KnowledgeBaseUtility;
import gmConverter.MeasureFromTextToId;
import gmConverter.XmlParser;
import gmPropagation.EnergyPropagation;
import gmPropagation.MeasureExcluder;
import gmPropagation.RiskPropagation;
import model.GmKnowledgeBase;
import model.GoalModel;
import utilityPrinters.AssetEnergyPrinter;
import utilityPrinters.GoalEnergyPrinter;
import utilityPrinters.ModelPrinter;

/**
 * This is the main class of the program and the starting point
 */
public class Client {

	public static void main(String[] args) {
		
		GoalModel goalModel = new GoalModel();
		ModelPrinter modelPrinter = new ModelPrinter();
		GoalEnergyPrinter goalEnergyPrinter = new GoalEnergyPrinter();
		AssetEnergyPrinter assetEnergyPrinter = new AssetEnergyPrinter();
		Scanner scanner = new Scanner(System.in);
		
		JsonParser jsonParser = new JsonParser();
		jsonParser.start(goalModel);
		
		//System.out.println("-------------------------RISK-----------------------------------");
		//System.out.println();
		
		JsonRiskParser jsonRiskParser = new JsonRiskParser();
		jsonRiskParser.start(goalModel);
		
//		System.out.println("--------------------------XML-----------------------------------");
//		System.out.println();
		
		XmlParser xmlParser = new XmlParser();
		xmlParser.start(goalModel);
		
		//DEBUG ZONE
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("--------------------------------GOAL MODEL ACTORS--------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();	
				
		modelPrinter.printModel(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-----------------------------------KNOWLEDGE BASE--------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();	
		
		GmKnowledgeBase knowledgeBase = new GmKnowledgeBase();
		knowledgeBase.putEntry("AES", "wfp1", null);
		knowledgeBase.putEntry("AES", "wfp2", null);
		
		KnowledgeBaseUtility kbUtility = new KnowledgeBaseUtility();
		kbUtility.printKB(knowledgeBase);
		
		//If you want to serialize kb or deserialize it you can do it here
		//kbSerializer.serialize("serializedKB", knowledgeBase);
		//kbSerializer.deserialize("serializedKB", knowledgeBase);
		
		ConflictChecker conflictChecker = new ConflictChecker();
		conflictChecker.start(goalModel, knowledgeBase);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("--------------------------------PROPAGATION EXCLUSION----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		MeasureExcluder measureExcluder = new MeasureExcluder();
		ArrayList<String> toExclude = measureExcluder.startExcluder(goalModel, scanner);
		if (toExclude.size() == 0) {
			System.out.println("No measures will be excluded from the analysis");
		} else {
			System.out.println("The following measures will be excluded from the analysis:");
			for (String string : toExclude) {
				System.out.println(string);
			}
		}
		System.out.println();
		
		MeasureFromTextToId converter = new MeasureFromTextToId();
		toExclude = converter.startConversion(goalModel, toExclude);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-----------------------------------ENERGY PROPAGATION----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		EnergyPropagation enPropagator = new EnergyPropagation();
		enPropagator.startPropagation(goalModel, scanner, toExclude);
		modelPrinter.printModel(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-------------------------------------RISK PROPAGATION----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();	
		
		RiskPropagation riskPropagator = new RiskPropagation();
		riskPropagator.startPropagation(goalModel, scanner, toExclude);
		modelPrinter.printModel(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-----------------------------ENERGY PER GOAL ANALYSIS----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		goalEnergyPrinter.printGoalsByEnergyDesc(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-----------------------------ENERGY PER ASSET ANALYSIS---------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		assetEnergyPrinter.printAssetsByEnergyDesc(goalModel);
		
	}
	
}
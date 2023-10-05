package client;

import java.util.List;
import java.util.function.Function;

import gmConverter.JsonParser;
import gmConverter.JsonRiskParser;
import gmConverter.KnowledgeBaseSerializer;
import gmConverter.XmlParser;
import gmPropagation.EnergyPropagation;
import gmPropagation.RiskPropagation;
import model.GmActor;
import model.GmGoal;
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
		JsonParser jsonParser = new JsonParser();
		JsonRiskParser jsonRiskParser = new JsonRiskParser();
		XmlParser xmlParser = new XmlParser();
		GmKnowledgeBase knowledgeBase = new GmKnowledgeBase();
		ConflictChecker conflictChecker = new ConflictChecker();
		KnowledgeBaseSerializer kbSerializer = new KnowledgeBaseSerializer();
		EnergyPropagation enPropagator = new EnergyPropagation();
		RiskPropagation riskPropagator = new RiskPropagation();
		ModelPrinter modelPrinter = new ModelPrinter();
		GoalEnergyPrinter goalEnergyPrinter = new GoalEnergyPrinter();
		AssetEnergyPrinter assetEnergyPrinter = new AssetEnergyPrinter();
		
		jsonParser.start(goalModel);
		
		//System.out.println("-------------------------RISK-----------------------------------");
		//System.out.println();
		
		jsonRiskParser.start(goalModel);
		
//		System.out.println("--------------------------XML-----------------------------------");
//		System.out.println();
		
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
		
		knowledgeBase.putEntry("AES", "wfp1", null);
		knowledgeBase.putEntry("AES", "wfp2", null);
		
		kbSerializer.printKB(knowledgeBase);
		
		//kbSerializer.serialize("serializedKB", knowledgeBase);
		//kbSerializer.deserialize("serializedKB", knowledgeBase);
		
		conflictChecker.start(goalModel, knowledgeBase);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-----------------------------------ENERGY PROPAGATION----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();	
		
		enPropagator.startPropagation(goalModel);
		modelPrinter.printModel(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-------------------------------------RISK PROPAGATION----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();	
		
		riskPropagator.startPropagation(goalModel);
		modelPrinter.printModel(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-----------------------------ENERGY PER GOAL ANALYSIS----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		goalEnergyPrinter.printGoalsByEnergyDesc(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-----------------------------ENERGY PER ASSET ANALYSIS----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		assetEnergyPrinter.printAssetsByEnergyDesc(goalModel);
		
	}
	
}

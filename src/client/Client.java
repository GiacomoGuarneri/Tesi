package client;

import java.util.ArrayList;
import java.util.Scanner;

import gmConverter.JsonGoalModelExporter;
import gmConverter.JsonGoalModelParser;
import gmConverter.JsonRiskParser;
import gmConverter.KnowledgeBaseUtility;
import gmConverter.MeasureFromTextToId;
import gmConverter.XmlParser;
import gmPropagation.EnergyPropagation;
import gmPropagation.MeasureExcluder;
import gmPropagation.RiskPropagation;
import model.GmKnowledgeBase;
import model.GoalModel;
import utilityPrinters.ActorsEnergyPrinter;
import utilityPrinters.AssetEnergyPrinter;
import utilityPrinters.AssetRiskPrinter;
import utilityPrinters.GoalEnergyPrinter;
import utilityPrinters.ModelPrinter;
import utilityPrinters.SmEnergyPrinter;
import utilityPrinters.WfpEnergyPrinter;

/**
 * This is the main class of the program and the starting point
 */
public class Client {

	public static void main(String[] args) {
		
		GoalModel goalModel = new GoalModel();
		GmKnowledgeBase knowledgeBase = new GmKnowledgeBase();
		ModelPrinter modelPrinter = new ModelPrinter();
		GoalEnergyPrinter goalEnergyPrinter = new GoalEnergyPrinter();
		ActorsEnergyPrinter actorsEnergyPrinter = new ActorsEnergyPrinter();
		SmEnergyPrinter smEnergyPrinter = new SmEnergyPrinter();
		WfpEnergyPrinter wfpEnergyPrinter = new WfpEnergyPrinter();
		AssetEnergyPrinter assetEnergyPrinter = new AssetEnergyPrinter();
		AssetRiskPrinter assetRiskPrinter = new AssetRiskPrinter();
		Scanner scanner = new Scanner(System.in);
		
		JsonGoalModelParser jsonParser = new JsonGoalModelParser();
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
		
		KnowledgeBaseUtility kbUtility = new KnowledgeBaseUtility();
		kbUtility.setUpKnowledgeBase(knowledgeBase);
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
		System.out.println("----------------------------ENERGY PER ACTORS ANALYSIS---------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		actorsEnergyPrinter.printActorsByEnergyDesc(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("------------------------ENERGY PER SECURITY MEASURE ANALYSIS---------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		smEnergyPrinter.printSmByEnergyDesc(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("------------------------ENERGY PER WORKFLOW PATTERNS ANALYSIS--------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		wfpEnergyPrinter.printWfpByEnergyDesc(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-----------------------------ENERGY PER ASSET ANALYSIS---------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		assetEnergyPrinter.printAssetsByEnergyDesc(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("-------------------------------RISK PER ASSET ANALYSIS---------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		assetRiskPrinter.printAssetsByRiskDesc(goalModel);
		
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("------------------------------------JSON EXPORT----------------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		
		JsonGoalModelExporter jsonGoalModelExporter = new JsonGoalModelExporter();
		jsonGoalModelExporter.start(goalModel);
		
	}
	
}

package client;

import gmConverter.JsonParser;
import gmConverter.JsonRiskParser;
import gmConverter.XmlParser;
import model.GmGoal;
import model.GoalModel;

/**
 * This is the main class of the program and the starting point
 */
public class Client {

	public static void main(String[] args) {
		
		GoalModel goalModel = new GoalModel();
		JsonParser jsonParser = new JsonParser();
		JsonRiskParser jsonRiskParser = new JsonRiskParser();
		XmlParser xmlParser = new XmlParser();
		
		jsonParser.start(goalModel);
		
		System.out.println("-------------------------RISK-----------------------------------");
		System.out.println();
		
		jsonRiskParser.start(goalModel);
		
		System.out.println("--------------------------XML-----------------------------------");
		System.out.println();
		
		xmlParser.start(goalModel);
		
		//DEBUG ZONE
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("----------------------------AFTER GETTING COMPONENTS-----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();	
		
		System.out.println(goalModel.getActorsArray().get(1).getRootNode().toString());
		System.out.println();
		for (GmGoal goal : goalModel.getActorsArray().get(1).getRootNode().getChildren()) {
			System.out.println(goal.toString());
		}
		System.out.println();
		for (GmGoal goal : goalModel.getActorsArray().get(1).getRootNode().getChildren().get(0).getChildren()) {
			System.out.println(goal.toString());
		}
		System.out.println();
		for (GmGoal goal : goalModel.getActorsArray().get(1).getRootNode().getChildren().get(1).getChildren()) {
			System.out.println(goal.toString());
		}
		System.out.println();
		for (GmGoal goal : goalModel.getActorsArray().get(1).getRootNode().getChildren().get(2).getChildren()) {
			System.out.println(goal.toString());
		}
	}

}

package client;

import gmConverter.JsonParser;
import gmConverter.JsonRiskParser;
import model.GoalModel;

/**
 * This is the main class of the program and the starting point
 */
public class Client {

	public static void main(String[] args) {
		
		GoalModel goalModel = new GoalModel();
		JsonParser jsonParser = new JsonParser();
		JsonRiskParser jsonRiskParser = new JsonRiskParser();
		
		jsonParser.start(goalModel);
		
		System.out.println("-------------------------RISK-----------------------------------");
		System.out.println();
		
		jsonRiskParser.start(goalModel);
		
		//DEBUG ZONE
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("----------------------------AFTER GETTING COMPONENTS-----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();		
	}

}

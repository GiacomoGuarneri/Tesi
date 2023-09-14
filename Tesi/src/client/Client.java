package client;

import gmConverter.JsonParser;
import model.GoalModel;

/**
 * This is the main class of the program and the starting point
 */
public class Client {

	public static void main(String[] args) {
		
		GoalModel goalModel = new GoalModel();
		JsonParser jsonParser = new JsonParser();;
		
		jsonParser.start(goalModel);
		
		//DEBUG ZONE
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println("----------------------------AFTER GETTING COMPONENTS-----------------------------------");
		System.out.println("---------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println(goalModel.getActorsArray().get(0).toString());
		System.out.println(goalModel.getActorsArray().get(1).toString());
	}

}

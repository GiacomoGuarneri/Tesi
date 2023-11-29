package gmConverter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.GmActor;
import model.GmAsset;
import model.GmDelegationRelationship;
import model.GmEnforceRelationship;
import model.GmGoal;
import model.GmIsARelationship;
import model.GmPartOfRelationship;
import model.GmProtectRelationship;
import model.GmRelationship;
import model.GmSecurityMeasure;
import model.GmWfp;
import model.GoalModel;

/**
 * This class is responsible of getting out of the gm JSON the info to then create all the necessary objects
 */
public class JsonGoalModelParser {
	/**
	 * This method starts the json parser
	 * @param goalModel
	 */
    public void start(GoalModel goalModel){
    	
    	//JSON parser object to parse read file
    	JSONParser jsonParser = new JSONParser();
    	
    	String filePath = "Goal models/demo.json";
    	
    	try (FileReader reader = new FileReader(filePath)) {
    		
    		//Read JSON file
    		Object obj = jsonParser.parse(reader);
    		
    		JSONObject jsonObj = (JSONObject) obj;
    		
    		// Gets wfp jsonArray
    		JSONArray wfp = new JSONArray();
    		wfp = (JSONArray) jsonObj.get("wfp");
    		JsonGoalModelParser.wfpRoutine(wfp, goalModel);
    		
    		// Gets sm jsonArray
    		JSONArray sm = new JSONArray();
    		sm = (JSONArray) jsonObj.get("sm");
    		JsonGoalModelParser.smRoutine(sm, goalModel);
    		
    		// Gets asset jsonArray
    		JSONArray asset = new JSONArray();
    		asset = (JSONArray) jsonObj.get("asset");
    		JsonGoalModelParser.assetRoutine(asset, goalModel);
    		
    		// Gets actors jsonArray
    		JSONArray actors = new JSONArray();
    		actors = (JSONArray) jsonObj.get("actors");
    		JsonGoalModelParser.actorsRoutine(actors, goalModel);
    		
    		//debug separator
    		//System.out.println("-------------------------------------------------------------------------------------------");
    		//System.out.println();
    		
    		// Gets relationships jsonArray: it contains relationships PART-OF, PROTECT, ENFORCE, IS-A
    		JSONArray relationships = new JSONArray();
    		relationships = (JSONArray) jsonObj.get("relationships");
    		JsonGoalModelParser.getRelationshipInfo(relationships, goalModel);
    		
    		//debug separator
    		//System.out.println("-------------------------------------------------------------------------------------------");
    		//System.out.println();
    		
    		// Gets links jsonArray: it contains ANDlinks among goals
    		JSONArray links = new JSONArray();
    		links = (JSONArray) jsonObj.get("links");
    		JsonGoalModelParser.getLinksInfo(links, goalModel);
    		
    		// Sets the root nodes of actors which are the goals in an actor the participate in AndRefinement only as Target, never as Source
    		for (GmActor actor : goalModel.getActorsArray()) {
//    			GmGoal rootToSet = actor.getGoals().get(0);
//    			actor.setRootNode(rootToSet);
    			for (GmGoal goal : actor.getGoals()) {
    				if (goal.isRoot()) {
    					actor.setRootNode(goal);
    				}
    			}
    		}
    		
    	} catch (FileNotFoundException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * This method is responsible of getting out the wfp info and creating the corresponding wfp objects
     * @param array is the json array of wfp
     * @param goalModel
     */
    private static void wfpRoutine(JSONArray array, GoalModel goalModel) {
    	for (int i=0; i < array.size(); i++) {
        	
    		JSONObject object = new JSONObject();
			object = (JSONObject) array.get(i);
    		
	    	//Get wfp id
	        String wfpId = (String) object.get("id");    
	        //System.out.println("wfpId: " + wfpId);    			       
	        //Get wfp text
	        String wfpText = (String) object.get("text");  
	        //System.out.println("wfpText: " + wfpText);
			//System.out.println();
			
			GmWfp wfp = new GmWfp(wfpId, wfpText);
			goalModel.getWfpArray().add(wfp);
    	}
	}
    
    /**
     * This method is responsible of getting out the sm info and creating the corresponding sm objects
     * @param array is the json array of sm
     * @param goalModel
     */
    private static void smRoutine(JSONArray array, GoalModel goalModel) {
    	for (int i=0; i < array.size(); i++) {
        	
    		JSONObject object = new JSONObject();
			object = (JSONObject) array.get(i);
    		
	    	//Get sm id
	        String smId = (String) object.get("id");    
	        //System.out.println("smId: " + smId);    			       
	        //Get sm text
	        String smText = (String) object.get("text");  
	        //System.out.println("smText: " + smText);
			//System.out.println();
			
			GmSecurityMeasure sm = new GmSecurityMeasure(smId, smText);
			goalModel.getSmArray().add(sm);
    	}
	}
    
    /**
     * This method is responsible of getting out the asset info and creating the corresponding asset objects
     * @param array is the json array of assets
     * @param goalModel
     */
    private static void assetRoutine(JSONArray array, GoalModel goalModel) {
    	for (int i=0; i < array.size(); i++) {
        	
    		JSONObject object = new JSONObject();
			object = (JSONObject) array.get(i);
    		
	    	//Get asset id
	        String assetId = (String) object.get("id");    
	        //System.out.println("assetId: " + assetId);    			       
	        //Get asset text
	        String assetText = (String) object.get("text");  
	        //System.out.println("assetText: " + assetText);
			//System.out.println();
			
			GmAsset asset = new GmAsset(assetId, assetText);
			goalModel.getAssetArray().add(asset);
    	}
	}
        
	/**
	 * This method is responsible of getting out the goal info and creating the corresponding goal objects
	 * @param object is the goal json object
	 * @param goalModel
	 * @param actor is the actor in whose scope we are in
	 */
    private static void getGoalInfo (JSONObject object, GoalModel goalModel, GmActor actor) {
    	//Get goal id
        String goalId = (String) object.get("id");    
        //System.out.println("goalId: " + goalId);    			       
        //Get goal text
        String goalText = (String) object.get("text");  
        //System.out.println("goalText: " + goalText);
		//System.out.println();
		
		GmGoal goal = new GmGoal(goalId, goalText);
		for(GmActor a : goalModel.getActorsArray()) {
			if(a.getId() != null && a.getId().equals(actor.getId()))
				a.getGoals().add(goal);
		}
    }
    
    /**
     * This method receive an array of goals and it gets all goals info by invoking getGoalInfo method
     * @param object is the json array of goals
     * @param goalModel
     * @param actor is the actor in whose scope we are in
     */
    private static void getGoalInfoFromActor (JSONObject object, GoalModel goalModel, GmActor actor) {
    	JSONArray tempArray = new JSONArray();
		tempArray = (JSONArray) object.get("nodes");
		for (int j=0; j < tempArray.size(); j++) {
			JSONObject element = new JSONObject();
			element = (JSONObject) tempArray.get(j);
			JsonGoalModelParser.getGoalInfo(element, goalModel, actor);
		}
    }
    
    /**
     * This method is responsible of getting out the actor info and relative goals info by invoking the specific support function
     * @param actors is the json array of actors
     * @param goalModel
     */
    private static void actorsRoutine (JSONArray actors, GoalModel goalModel) {
    	for (int i=0; i < actors.size(); i++) {
			
			JSONObject temp = new JSONObject();
			temp = (JSONObject) actors.get(i);
			
			//Get actor id
	        String actorId = (String) temp.get("id");    
	        //System.out.println("actorId: " + actorId);    			       
	        //Get actor text
	        String actorText = (String) temp.get("text");  
	        //System.out.println("actorText: " + actorText);
			//System.out.println();
			
			GmActor actor = new GmActor(actorId, actorText);
			goalModel.getActorsArray().add(actor);
			
			JsonGoalModelParser.getGoalInfoFromActor(temp, goalModel, actor);    				
			
		}
    }
    
    /**
     * This method is responsible of getting out the relationships info and creating the corresponding relationships objects
     * @param array is the json array of relationships
     * @param goalModel
     */
    private static void getRelationshipInfo (JSONArray array, GoalModel goalModel) {
    	for (int i=0; i < array.size(); i++) {
    	
    		JSONObject object = new JSONObject();
			object = (JSONObject) array.get(i);
    		
	    	//Get relationship id
	        String relationshipId = (String) object.get("id");    
	        //System.out.println("relationshipId: " + relationshipId);    			       
	        //Get relationship text ---------------------------------------------> not needed!
	        //String relationshipText = (String) object.get("text");  
	        //System.out.println("relationshipText: " + relationshipText);    			         
	        //Get relationship type
	        String relationshipType = (String) object.get("type");  
	        //System.out.println("relationshipType: " + relationshipType);
	        //Get relationship source
	        String relationshipSource = (String) object.get("source");  
	        //System.out.println("relationshipSource: " + relationshipSource);
	        //Get relationship target
	        String relationshipTarget = (String) object.get("target");  
	        //System.out.println("relationshipTarget: " + relationshipTarget);
			//System.out.println();
			
			GmRelationship relationship;
			switch(relationshipType) {
				case "SecureEnergy.Part-Of-relationship":
					relationship = new GmPartOfRelationship(relationshipId, relationshipSource, relationshipTarget);
					break;
				case "SecureEnergy.Protect-relationship":
					relationship = new GmProtectRelationship(relationshipId, relationshipSource, relationshipTarget);
					break;
				case "SecureEnergy.Enforce-relationship":
					relationship = new GmEnforceRelationship(relationshipId, relationshipSource, relationshipTarget);
					break;
				case "SecureEnergy.Is-A-relationship":
					relationship = new GmIsARelationship(relationshipId, relationshipSource, relationshipTarget);
					break;
				case "SecureEnergy.Delegation-relationship":
					relationship = new GmDelegationRelationship(relationshipId, relationshipSource, relationshipTarget);
					break;
				default:
					relationship = new GmRelationship(relationshipId, relationshipSource, relationshipTarget);
			}
			
			goalModel.getRelationshipsArray().add(relationship);
    	}
    }
    
    /**
     * This method is responsible of getting out the AND-links info and organizing the goal in the respective actor goal-tree
     * @param array is the json array of links
     * @param goalModel
     */
    private static void getLinksInfo (JSONArray array, GoalModel goalModel) {
    	for (int i=0; i < array.size(); i++) {
        	
    		JSONObject object = new JSONObject();
			object = (JSONObject) array.get(i);
    		
	    	//Get link id --------------------------------------------------> not needed!
	        //String linkId = (String) object.get("id");    
	        //System.out.println("linkId: " + linkId); 
	        
	        //REMEMBER that in iStar when we specify an AND-link the target is the parent in the tree structure and the source is the child
	        
	        //Get link source
	        String linkSource = (String) object.get("source");  
	        //System.out.println("linkSource: " + linkSource);
	        //Get link target
	        String linkTarget = (String) object.get("target");  
	        //System.out.println("linkTarget: " + linkTarget);
			//System.out.println();
			
			//Adjust links within goals children and parents
			for (GmActor actor : goalModel.getActorsArray()) {
				GmGoal parentGoal = actor.getSpecificGoal(linkTarget);
				GmGoal childGoal = actor.getSpecificGoal(linkSource);
				if (parentGoal != null && childGoal != null) {
					parentGoal.addChild(childGoal);
				}
			}
    	}
    }
    
}

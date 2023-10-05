package utilityPrinters;

import java.util.ArrayList;
import java.util.Collections;

import model.GmAsset;
import model.GoalModel;

/**
 * This class is devoted to the asset energy consumption analysis
 */
public class AssetEnergyPrinter {

	/**
	 * Prints assets ordered by energy consumption in descending order
	 * @param goalModel
	 */
	public void printAssetsByEnergyDesc (GoalModel goalModel) {
		//First I group all the assets in the model in a single array
		ArrayList<GmAsset> totalAssets = new ArrayList<GmAsset>();
		for (GmAsset asset : goalModel.getAssetArray()) {
			totalAssets.add(asset);
		}
		
		//Ordering goals by energy descending
		Collections.sort(totalAssets, (a1, a2) -> Float.compare(a2.getEnergyConsumption(), a1.getEnergyConsumption()));
		
		//ANSI escape codes to color the output
		final String ANSI_RESET = "\u001B[0m";
//		final String ANSI_BLACK = "\u001B[30m";
//		final String ANSI_RED = "\u001B[31m";
//		final String ANSI_GREEN = "\u001B[32m";
		final String ANSI_YELLOW = "\u001B[33m";
//		final String ANSI_BLUE = "\u001B[34m";
//		final String ANSI_PURPLE = "\u001B[35m";
		final String ANSI_CYAN = "\u001B[36m";
//		final String ANSI_WHITE = "\u001B[37m";
		
		System.out.println("Assets ordered by energy consumption DESCENDING");
		System.out.println();
		
		for (GmAsset asset : totalAssets) {
			System.out.println("Asset: " + ANSI_CYAN + asset.getDescription() + ANSI_RESET + " with energy consumption of " + ANSI_YELLOW + asset.getEnergyConsumption() + ANSI_RESET);
		}
		
		System.out.println();
	}
	
}

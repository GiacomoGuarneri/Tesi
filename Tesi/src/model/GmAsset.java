package model;

/**
 * This class represents an Asset
 */
public class GmAsset extends GmComponent{

	public GmAsset(String id, String description) {
		super(id, description);
	}
	
	@Override
	public String toString() {
		return "GmAsset [id=" + super.getId() + ", description=" + super.getDescription() + ", energyConsumption=" + super.getEnergyConsumption()
				+ ", residualRisk=" + super.getResidualRisk() + "]";
	}
}

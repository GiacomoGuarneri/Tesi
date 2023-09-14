package model;

/**
 * This class represents a Wfp
 */
public class GmWfp extends GmComponent {
	
	public GmWfp(String id, String description) {
		super(id, description);
	}
	
	@Override
	public String toString() {
		return "GmWfp [id=" + super.getId() + ", description=" + super.getDescription() + ", energyConsumption=" + super.getEnergyConsumption()
				+ ", residualRisk=" + super.getResidualRisk() + "]";
	}
}

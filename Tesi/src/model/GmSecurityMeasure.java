package model;

/**
 * This class represents a Security Measure
 */
public class GmSecurityMeasure extends GmComponent {
	
	public GmSecurityMeasure(String id, String description) {
		super(id, description);
	}
	
	@Override
	public String toString() {
		return "GmSm [id=" + super.getId() + ", description=" + super.getDescription() + ", energyConsumption=" + super.getEnergyConsumption()
				+ ", residualRisk=" + super.getResidualRisk() + "]";
	}
}
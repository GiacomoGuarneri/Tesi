package model;

/**
 * This class represents a Wfp
 */
public class GmWfp extends GmComponent {
	// Security measures and Wfp are provided with a risk mitigation impact
	private float riskMitigationImpact;
	
	public GmWfp(String id, String description) {
		super(id, description);
	}
	
	@Override
	public String toString() {
		return "GmWfp [id=" + super.getId() + ", description=" + super.getDescription() + ", energyConsumption=" + super.getEnergyConsumption()
				+ ", riskMitigationImpact=" + this.getRiskMitigationImpact() + "]";
	}
	

	public float getRiskMitigationImpact() {
		return riskMitigationImpact;
	}

	public void setRiskMitigationImpact(float riskMitigationImpact) {
		this.riskMitigationImpact = riskMitigationImpact;
	}

}

package model;

/**
 * This class represents an Asset
 */
public class GmAsset extends GmComponent{
	// Assets have a starting value of inherent risk that will be mitigated by wfp and sm
	private float inherentRisk;
	
	public GmAsset(String id, String description) {
		super(id, description);
	}
	
	@Override
	public String toString() {
		return "GmAsset [id=" + super.getId() + ", description=" + super.getDescription() + ", energyConsumption=" + super.getEnergyConsumption()
				+ ", inherentRisk=" + this.getInherentRisk() + ", residualRisk=" + super.getResidualRisk() + "]";
	}

	public float getInherentRisk() {
		return inherentRisk;
	}

	public void setInherentRisk(float inherentRisk) {
		this.inherentRisk = inherentRisk;
	}
}

package model;

/**
 * This class represents an Asset
 */
public class GmAsset extends GmComponent{
	// Assets have a starting value of inherent risk that will be mitigated by wfp and sm
	private int inherentRisk;
	
	public GmAsset(String id, String description) {
		super(id, description);
	}
	
	@Override
	public String toString() {
		return "GmAsset [id=" + super.getId() + ", description=" + super.getDescription() + ", energyConsumption=" + super.getEnergyConsumption()
				+ ", inherentRisk=" + this.getInherentRisk() + ", residualRisk=" + super.getResidualRisk() + "]";
	}

	public int getInherentRisk() {
		return inherentRisk;
	}

	public void setInherentRisk(int inherentRisk) {
		this.inherentRisk = inherentRisk;
	}
}

package model;

/**
 * This class represents a general component in the Goal Model
 */
public class GmComponent {
	private String id;
	private String description;
	private float energyConsumption;
	private float residualRisk;
	
	public GmComponent(String id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getEnergyConsumption() {
		return energyConsumption;
	}

	public void setEnergyConsumption(float energyConsumption) {
		this.energyConsumption = energyConsumption;
	}
	
	/**
	 * Updates energyConsumption by arbitrary value
	 * @param increment
	 */
	public void updateEnergyConsumption(float increment) {
		this.energyConsumption = this.energyConsumption + increment;
	}
	
	/**
	 * Updates residualRisk by arbitrary value
	 * @param decrement
	 */
	public void updateResidualRisk(float decrement) {
		//residual risk CANNOT go under 0
		if (this.residualRisk - decrement < 0) {
			this.residualRisk = 0;
		} else {
			this.residualRisk = this.residualRisk - decrement;
		}
	}

	public float getResidualRisk() {
		return residualRisk;
	}

	public void setResidualRisk(float residualRisk) {
		this.residualRisk = residualRisk;
	}

}

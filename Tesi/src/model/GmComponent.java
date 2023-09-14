package model;

/**
 * This class represents a general component in the Goal Model
 */
public class GmComponent {
	private String id;
	private String description;
	private int energyConsumption;
	private int residualRisk;
	
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

	public int getEnergyConsumption() {
		return energyConsumption;
	}

	public void setEnergyConsumption(int energyConsumption) {
		this.energyConsumption = energyConsumption;
	}

	public int getResidualRisk() {
		return residualRisk;
	}

	public void setResidualRisk(int residualRisk) {
		this.residualRisk = residualRisk;
	}

	@Override
	public String toString() {
		return "GmComponent [id=" + id + ", description=" + description + ", energyConsumption=" + energyConsumption
				+ ", residualRisk=" + residualRisk + "]";
	}
}

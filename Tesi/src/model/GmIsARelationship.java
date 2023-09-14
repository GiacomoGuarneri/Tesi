package model;

/**
 * This class represents a is-a relationship between security measure -> security measure
 */
public class GmIsARelationship extends GmRelationship {

	public GmIsARelationship(String id, String source_id, String target_id) {
		super(id, source_id, target_id);
	}
	
	@Override
	public String toString() {
		return "GmIsARelationship [id=" + super.getId() + ", source_id=" + super.getSource_id() + ", target_id=" + super.getTarget_id() + "]";
	}

}

package model;

/**
 * This class represents an enforce relationship between goal -> security measure
 */
public class GmEnforceRelationship extends GmRelationship {

	public GmEnforceRelationship(String id, String source_id, String target_id) {
		super(id, source_id, target_id);
	}

	@Override
	public String toString() {
		return "GmEnforceRelationship [id=" + super.getId() + ", source_id=" + super.getSource_id() + ", target_id=" + super.getTarget_id() + "]";
	}
	
}

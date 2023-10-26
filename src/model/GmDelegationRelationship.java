package model;

/**
 * This class represents a delegation relationship between actor -> actor
 */
public class GmDelegationRelationship extends GmRelationship{

	public GmDelegationRelationship(String id, String source_id, String target_id) {
		super(id, source_id, target_id);
	}
	
	@Override
	public String toString() {
		return "GmDelegationRelationship [id=" + super.getId() + ", source_id=" + super.getSource_id() + ", target_id=" + super.getTarget_id() + "]";
	}

}

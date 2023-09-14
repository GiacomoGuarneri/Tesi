package model;

/**
 * This class represents a protect relationship between goal -> asset
 */
public class GmProtectRelationship extends GmRelationship {

	public GmProtectRelationship(String id, String source_id, String target_id) {
		super(id, source_id, target_id);
	}
	
	@Override
	public String toString() {
		return "GmProtectRelationship [id=" + super.getId() + ", source_id=" + super.getSource_id() + ", target_id=" + super.getTarget_id() + "]";
	}

}

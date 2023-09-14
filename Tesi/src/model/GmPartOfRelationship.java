package model;

/**
 * This class represents a part-of relationship between asset -> asset
 */
public class GmPartOfRelationship extends GmRelationship {

	public GmPartOfRelationship(String id, String source_id, String target_id) {
		super(id, source_id, target_id);
	}
	
	@Override
	public String toString() {
		return "GmPartOfRelationship [id=" + super.getId() + ", source_id=" + super.getSource_id() + ", target_id=" + super.getTarget_id() + "]";
	}
	
}

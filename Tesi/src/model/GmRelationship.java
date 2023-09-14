package model;

/**
 * This class represents a general relationship in the Goal Model
 */
public class GmRelationship {
	private String id;
	private String source_id;
	private String target_id;
	
	public GmRelationship(String id, String source_id, String target_id) {
		this.id = id;
		this.source_id = source_id;
		this.target_id = target_id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getSource_id() {
		return source_id;
	}

	public void setSource_id(String source_id) {
		this.source_id = source_id;
	}

	public String getTarget_id() {
		return target_id;
	}

	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}

	@Override
	public String toString() {
		return "GmRelationship [id=" + id + ", source_id=" + source_id + ", target_id=" + target_id + "]";
	}
	
}

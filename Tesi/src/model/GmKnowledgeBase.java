package model;

import java.io.Serializable;
import java.util.Map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

/**
 * This class is our KB that holds entries of all conflicts pairs of wfp and security measures
 */
public class GmKnowledgeBase implements Serializable {
	/**
	 * Attribute to remember version of this Serializable class
	 */
	private static final long serialVersionUID = -6140834410911873988L;
	private Table<String, String, String> knowledgeBase;
	
	public GmKnowledgeBase() {
		this.knowledgeBase = HashBasedTable.create();
	}

	public Table<String, String, String> getKnowledgeBase() {
		return knowledgeBase;
	}

	public void setKnowledgeBase(Table<String, String, String> knowledgeBase) {
		this.knowledgeBase = knowledgeBase;
	}
	
	public void putEntry(String sm, String conflictingWfp, String description) {
		// If a parameter is null then I pass the actual string "null"
		if (sm == null) {
			sm = "null";
		}
		if (conflictingWfp == null) {
			conflictingWfp = "null";
		}
		if (description == null) {
			description = "null";
		}
		this.knowledgeBase.put(sm, conflictingWfp, description);
	}
	
	/**
	 * This method returns rows of the KB containing the input sm
	 * @param sm
	 * @return
	 */
	public Map<String, String> getConflictingWfp(String sm) {
		return this.knowledgeBase.row(sm);
	}
	
	/**
	 * This method returns rows of the KB containing the input wfp
	 * @param wfp
	 * @return
	 */
	public Map<String, String> getConflictingSm(String wfp) {
		return this.knowledgeBase.column(wfp);
	}
	
}

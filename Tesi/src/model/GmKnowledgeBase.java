package model;

import java.util.Map;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class GmKnowledgeBase {
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
		this.knowledgeBase.put(sm, conflictingWfp, description);
	}
	
	public Map<String, String> getConflictingWfp(String sm) {
		return this.knowledgeBase.row(sm);
	}
	
	public Map<String, String> getConflictingSm(String wfp) {
		return this.knowledgeBase.row(wfp);
	}
	
	//TODO method that given a goalModel recognizes if there are conflicting pairs sm-wfp
	
}

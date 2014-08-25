package edu.arizona.biosemantics.bioportal.model;

import java.util.List;

public class Search {

	private String query = "";
	private List<Ontology> ontologies;
	private boolean exactMatch = false;
	private boolean includeViews = false;
	private boolean requiresDefinition = false;
	private boolean includeProperties = false;
	private boolean includeObsolete = false;
	
	public boolean hasOntologies() {
		return ontologies != null && !ontologies.isEmpty();
	}
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public List<Ontology> getOntologies() {
		return ontologies;
	}
	public void setOntologies(List<Ontology> ontologies) {
		this.ontologies = ontologies;
	}
	public boolean isExactMatch() {
		return exactMatch;
	}
	public void setExactMatch(boolean exactMatch) {
		this.exactMatch = exactMatch;
	}
	public boolean isIncludeViews() {
		return includeViews;
	}
	public void setIncludeViews(boolean includeViews) {
		this.includeViews = includeViews;
	}
	public boolean isRequiresDefinition() {
		return requiresDefinition;
	}
	public void setRequiresDefinition(boolean requiresDefinition) {
		this.requiresDefinition = requiresDefinition;
	}
	public boolean isIncludeProperties() {
		return includeProperties;
	}
	public void setIncludeProperties(boolean includeProperties) {
		this.includeProperties = includeProperties;
	}
	public boolean isIncludeObsolete() {
		return includeObsolete;
	}
	public void setIncludeObsolete(boolean includeObsolete) {
		this.includeObsolete = includeObsolete;
	}

	public String getOntologiesString() {
		String result = "";
		for(Ontology ontology : ontologies)
			result += ontology.getId() + ",";
		return result.substring(0, result.length() - 1);
	}
	
	

}

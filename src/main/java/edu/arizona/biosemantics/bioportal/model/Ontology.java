package edu.arizona.biosemantics.bioportal.model;

import java.io.IOException;

import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

public class Ontology {

	private static ObjectMapper objectMapper = new ObjectMapper();
	private String acronym;
	private String name;
	private String id;
	
	public Ontology() { }
	
	public Ontology(String acronym, String name, String id) {
		super();
		this.acronym = acronym;
		this.name = name;
		this.id = id;
	}
	
	public String getAcronym() {
		return acronym;
	}
	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	@JsonProperty("@id")
	public void setId(String id) {
		this.id = id;
	}
	@JsonAnySetter
	public void handleUnknown(String key, Object value) {}
	
	@Override
	public String toString() {
		try {
			return objectMapper.writeValueAsString(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}

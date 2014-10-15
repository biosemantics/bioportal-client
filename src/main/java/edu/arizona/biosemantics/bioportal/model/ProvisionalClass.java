package edu.arizona.biosemantics.bioportal.model;

import java.util.List;

import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

public class ProvisionalClass {

	private String label;
	private List<String> synonym;
	private List<String> definition;
	private String subclassOf;
	private String creator;
	private String created;
	private String permanentId;
	private String noteId;
	private List<String> ontology;
	private String id;
	private String type;
	
	public ProvisionalClass() { }

	
	public ProvisionalClass(String label, List<String> synonym,
			List<String> definition, String subclassOf, String creator,
			String created, String permanentId, String noteId,
			List<String> ontology, String id, String type) {
		super();
		this.label = label;
		this.synonym = synonym;
		this.definition = definition;
		this.subclassOf = subclassOf;
		this.creator = creator;
		this.created = created;
		this.permanentId = permanentId;
		this.noteId = noteId;
		this.ontology = ontology;
		this.id = id;
		this.type = type;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<String> getSynonym() {
		return synonym;
	}

	public void setSynonym(List<String> synonym) {
		this.synonym = synonym;
	}

	public List<String> getDefinition() {
		return definition;
	}

	public void setDefinition(List<String> definition) {
		this.definition = definition;
	}

	public String getSubclassOf() {
		return subclassOf;
	}

	public void setSubclassOf(String subclassOf) {
		this.subclassOf = subclassOf;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getPermanentId() {
		return permanentId;
	}

	public void setPermanentId(String permanentId) {
		this.permanentId = permanentId;
	}

	public String getNoteId() {
		return noteId;
	}

	public void setNoteId(String noteId) {
		this.noteId = noteId;
	}

	public List<String> getOntology() {
		return ontology;
	}

	public void setOntology(List<String> ontology) {
		this.ontology = ontology;
	}

	public String getId() {
		return id;
	}
	
	@JsonIgnore
	public String getShortId() {
		return this.id.replace("http://data.bioontology.org/provisional_classes/", "");
	}

	@JsonProperty("@id")
	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	@JsonProperty("@type")
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonAnySetter
	public void handleUnknown(String key, Object value) { }
	
}

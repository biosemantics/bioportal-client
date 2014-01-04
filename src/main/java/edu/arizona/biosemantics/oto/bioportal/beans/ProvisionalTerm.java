package edu.arizona.biosemantics.oto.bioportal.beans;

public class ProvisionalTerm {

	private String term;
	private String definition;
	private String superclass;
	private String synonyms;
	private String ontologyids;
	private String submittedby;
	private String temporaryid;
	private String permanentid;

	public ProvisionalTerm() { }

	public ProvisionalTerm(String term, String definition, String superclass, String synonyms, String ontologyids, String submittedby,
			String temporaryid, String permanentid) {
		super();
		this.term = term;
		this.definition = definition;
		this.superclass = superclass;
		this.synonyms = synonyms;
		this.ontologyids = ontologyids;
		this.submittedby = submittedby;
		this.temporaryid = temporaryid;
		this.permanentid = permanentid;
	}

	public String getPermanentid() {
		return permanentid;
	}

	public void setPermanentid(String permanentid) {
		this.permanentid = permanentid;
	}

	public String getSuperclass() {
		return superclass;
	}

	public void setSuperclass(String superclass) {
		this.superclass = superclass;
	}

	public String getSubmittedby() {
		return submittedby;
	}

	public void setSubmittedby(String submittedby) {
		this.submittedby = submittedby;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}
	
	public String getOntologyids() {
		return ontologyids;
	}

	public void setOntologyids(String ontologyids) {
		this.ontologyids = ontologyids;
	}

	public String getTerm() {
		return term;
	}

	public void setTerm(String term) {
		this.term = term;
	}
	
	public boolean hasPermanentId() {
		return this.permanentid != null && !this.permanentid.isEmpty();
	}
	
	public boolean hasSuperClass() {
		return this.superclass != null && !this.superclass.isEmpty();
	}
	
	public boolean hasSubmittedBy() {
		return this.submittedby != null && !this.submittedby.isEmpty();
	}
	
	public boolean hasDefinition() {
		return this.definition != null && !this.definition.isEmpty();
	}
	
	public boolean hasOntologyIds() {
		return this.ontologyids != null && !this.ontologyids.isEmpty();
	}
	
	public boolean hasTerm() {
		return this.term != null && !this.term.isEmpty();
	}

	public String getSynonyms() {
		return this.synonyms;
	}
	
	public boolean hasSynonyms() {
		return this.synonyms != null && !this.synonyms.isEmpty();
	}
	
	public boolean hasTemporaryId() {
		return this.temporaryid != null && !this.temporaryid.isEmpty();
	}

	public String getTemporaryid() {
		return temporaryid;
	}

	public void setTemporaryid(String temporaryid) {
		this.temporaryid = temporaryid;
	}

	public void setSynonyms(String synonyms) {
		this.synonyms = synonyms;
	}
	
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("term: " + this.term + "\n");
		stringBuilder.append("definition: " + this.definition + "\n");
		stringBuilder.append("superclass: " + this.superclass + "\n");
		stringBuilder.append("synonyms: " + this.synonyms + "\n");
		stringBuilder.append("ontologyids: " + this.ontologyids + "\n");
		stringBuilder.append("submittedby: " + this.submittedby + "\n");
		stringBuilder.append("temporaryid: " + this.temporaryid + "\n");
		stringBuilder.append("permanentid: " + this.permanentid + "\n");
		return stringBuilder.toString();
	}
}

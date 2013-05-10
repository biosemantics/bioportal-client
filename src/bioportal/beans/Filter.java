package bioportal.beans;

public class Filter {

	private String pageSize;
	private String pageNum;
	private String submittedBy;
	private String createdStartDate;
	private String createdEndDate;
	private String updatedStartDate;
	private String updatedEndDate;
	private String ontologyIds;
	private String implementedTermsOnly;
	
	public Filter() { }
	
	public Filter(String pageSize, String pageNum, String submittedBy,
			String createdStartDate, String createdEndDate,
			String updatedStartDate, String updatedEndDate, String ontologyIds,
			String implementedTermsOnly) {
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.submittedBy = submittedBy;
		this.createdStartDate = createdStartDate;
		this.createdEndDate = createdEndDate;
		this.updatedStartDate = updatedStartDate;
		this.updatedEndDate = updatedEndDate;
		this.ontologyIds = ontologyIds;
		this.implementedTermsOnly = implementedTermsOnly;
	}

	public boolean hasPageSize() {
		return this.pageSize != null;
	}
	
	public boolean hasPageNum() {
		return this.pageNum != null;
	}
	
	public boolean hasSubmittedBy() {
		return this.submittedBy != null;
	}
	
	public boolean hasCreatedEndDate() {
		return this.createdEndDate != null;
	}
	
	public boolean hasUpdatedStartDate() {
		return this.updatedStartDate != null;
	}
	
	public boolean hasUpdatedEndDate() {
		return this.updatedEndDate != null;
	}
	
	public boolean hasOntologyIds() {
		return this.ontologyIds != null;
	}

	public boolean hasImplementedTermsOnly() {
		return this.implementedTermsOnly != null;
	}
	
	public String getPageSize() {
		return pageSize;
	}


	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}


	public String getPageNum() {
		return pageNum;
	}


	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}


	public String getSubmittedBy() {
		return submittedBy;
	}


	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}


	public String getCreatedStartDate() {
		return createdStartDate;
	}


	public void setCreatedStartDate(String createdStartDate) {
		this.createdStartDate = createdStartDate;
	}


	public String getCreatedEndDate() {
		return createdEndDate;
	}


	public void setCreatedEndDate(String createdEndDate) {
		this.createdEndDate = createdEndDate;
	}


	public String getUpdatedStartDate() {
		return updatedStartDate;
	}


	public void setUpdatedStartDate(String updatedStartDate) {
		this.updatedStartDate = updatedStartDate;
	}


	public String getUpdatedEndDate() {
		return updatedEndDate;
	}


	public void setUpdatedEndDate(String updatedEndDate) {
		this.updatedEndDate = updatedEndDate;
	}


	public String getOntologyIds() {
		return ontologyIds;
	}


	public void setOntologyIds(String ontologyIds) {
		this.ontologyIds = ontologyIds;
	}


	public String getImplementedTermsOnly() {
		return implementedTermsOnly;
	}


	public void setImplementedTermsOnly(String implementedTermsOnly) {
		this.implementedTermsOnly = implementedTermsOnly;
	}
	
}

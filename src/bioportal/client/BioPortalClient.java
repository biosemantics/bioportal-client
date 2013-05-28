package bioportal.client;

import java.io.IOException;
import java.util.Properties;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBException;

import bioportal.beans.Filter;
import bioportal.beans.ProvisionalTerm;
import bioportal.beans.response.Success;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * BioPortalClient provides access to BioPortals REST Web Services 
 * e.g. at the moment the part of services available to provide provisional ontology terms
 * @author rodenhausen
 */
public class BioPortalClient {

	private String userId;
	private String apiKey;
	private String apiUrl;
	private Client client;
	private String superclassPrefix = "http://purl.obolibrary.org/obo/";
	
	/**
	 * @param apiUrl
	 * @param apiKey
	 */
	public BioPortalClient(String apiUrl, String userId, String apiKey) {
		this.apiUrl = apiUrl;
		this.userId = userId;
		this.apiKey = apiKey;
		ClientConfig clientConfig = new DefaultClientConfig();
		client = Client.create(clientConfig);
		//client.addFilter(new LoggingFilter(System.out));
	}
	
	/**
	 * Get a single provisional term for the given provisional term id.
	 * @param termid
	 * @return Success
	 * @throws JAXBException when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success getProvisionalTerm(String termId) throws JAXBException {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("termid", termId);
	    queryParams.add("apikey", this.apiKey);
	    return webResource.queryParams(queryParams).get(Success.class);
	}
	
	public String getProvisionalTermReturnString(String termId) throws JAXBException {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("termid", termId);
	    queryParams.add("apikey", this.apiKey);
	    return webResource.queryParams(queryParams).get(String.class);
	}
	
	/**
	 * Get all available provisional terms using a paged interface.
	 * @throws JAXBException when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success getProvisionalTerms() throws JAXBException {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);	    
	    return webResource.queryParams(queryParams).get(Success.class);
	}
	
	public String getProvisionalTermsReturnString() throws JAXBException {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);	    
	    return webResource.queryParams(queryParams).get(String.class);
	}
	
	/**
	 * Get all available provisional terms using a paged interface.
	 * @param filter
	 * @return Success
	 * @throws JAXBException when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success getProvisionalTerms(Filter filter) throws JAXBException {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);
	    
	    if(filter.hasCreatedEndDate())
	    	queryParams.add("createdenddate", filter.getCreatedEndDate());
	    if(filter.hasImplementedTermsOnly())
	    	queryParams.add("implementedtermsonly", filter.getImplementedTermsOnly());
	    if(filter.hasOntologyIds())
	    	queryParams.add("ontologyids", filter.getOntologyIds());
	    if(filter.hasPageNum())
	    	queryParams.add("pagenum", filter.getPageNum());
	    if(filter.hasPageSize())
	    	queryParams.add("pagesize", filter.getPageSize());
	    if(filter.hasSubmittedBy())
	    	queryParams.add("submittedby", filter.getSubmittedBy());
	    if(filter.hasUpdatedEndDate())
	    	queryParams.add("updatedenddate", filter.getUpdatedEndDate());
	    if(filter.hasUpdatedStartDate())
	    	queryParams.add("updatedstartdate", filter.getUpdatedStartDate());
	    
	    return webResource.queryParams(queryParams).get(Success.class);
	}
	
	public String getProvisionalTermsReturnString(Filter filter) throws JAXBException {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);
	    
	    if(filter.hasCreatedEndDate())
	    	queryParams.add("createdenddate", filter.getCreatedEndDate());
	    if(filter.hasImplementedTermsOnly())
	    	queryParams.add("implementedtermsonly", filter.getImplementedTermsOnly());
	    if(filter.hasOntologyIds())
	    	queryParams.add("ontologyids", filter.getOntologyIds());
	    if(filter.hasPageNum())
	    	queryParams.add("pagenum", filter.getPageNum());
	    if(filter.hasPageSize())
	    	queryParams.add("pagesize", filter.getPageSize());
	    if(filter.hasSubmittedBy())
	    	queryParams.add("submittedby", filter.getSubmittedBy());
	    if(filter.hasUpdatedEndDate())
	    	queryParams.add("updatedenddate", filter.getUpdatedEndDate());
	    if(filter.hasUpdatedStartDate())
	    	queryParams.add("updatedstartdate", filter.getUpdatedStartDate());
	    
	    return webResource.queryParams(queryParams).get(String.class);
	}
	
	/**
	 * Create a provisional term.
	 * @return Success
	 * @param provisionalTerm
	 * @throws JAXBException when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success createProvisionalTerm(ProvisionalTerm provisionalTerm) throws JAXBException, IllegalArgumentException {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);
	    
	    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
	    formData.add("submittedby", this.userId);
	    if(provisionalTerm.hasTerm() && provisionalTerm.hasDefinition()) {
	    	formData.add("preferredname", provisionalTerm.getTerm());
	    	formData.add("definition", provisionalTerm.getDefinition());
	    } else {
	    	throw new IllegalArgumentException();
	    }
	    if(provisionalTerm.hasOntologyIds())
	    	formData.add("ontologyids", provisionalTerm.getOntologyids());
	    if(provisionalTerm.hasSynonyms())
	    	formData.add("synonyms", provisionalTerm.getSynonyms());
	    if(provisionalTerm.hasSuperClass())
	    	formData.add("superclass", superclassPrefix + provisionalTerm.getSuperclass());
	    
	    return webResource.queryParams(queryParams).type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(Success.class, formData);
	}
	
	public String createProvisionalTermReturnString(ProvisionalTerm provisionalTerm) throws JAXBException, IllegalArgumentException {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);
	    
	    MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
	    formData.add("submittedby", this.userId);
	    if(provisionalTerm.hasTerm() && provisionalTerm.hasDefinition()) {
	    	formData.add("preferredname", provisionalTerm.getTerm());
	    	formData.add("definition", provisionalTerm.getDefinition());
	    } else {
	    	throw new IllegalArgumentException();
	    }
	    if(provisionalTerm.hasOntologyIds())
	    	formData.add("ontologyids", provisionalTerm.getOntologyids());
	    if(provisionalTerm.hasSynonyms())
	    	formData.add("synonyms", provisionalTerm.getSynonyms());
	    if(provisionalTerm.hasSuperClass())
	    	formData.add("superclass", superclassPrefix + provisionalTerm.getSuperclass());
	    
	    return webResource.queryParams(queryParams).type(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(String.class, formData);
	}
	
	/**
	 * Update fields for a provisional term.
	 * @param termId
	 * @return Success
	 * @param provisionalTerm
	 * @throws JAXBException when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success updateProvisionalTerm(String termId, ProvisionalTerm provisionalTerm) throws JAXBException {	    
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);
	    queryParams.add("termid", termId);
	    queryParams.add("submittedby", this.userId);
	    if(provisionalTerm.hasTerm())
	    	queryParams.add("preferredname", provisionalTerm.getTerm());
	    if(provisionalTerm.hasDefinition()) 
	    	queryParams.add("definition", provisionalTerm.getDefinition());
	    if(provisionalTerm.hasOntologyIds())
	    	queryParams.add("ontologyids", provisionalTerm.getOntologyids());
	    if(provisionalTerm.hasPermanentId())
	    	queryParams.add("permanentid", provisionalTerm.getPermanentid());
	    if(provisionalTerm.hasSuperClass())
	    	queryParams.add("superclass", superclassPrefix + provisionalTerm.getSuperclass());
	    if(provisionalTerm.hasSynonyms())
	    	queryParams.add("synonyms", provisionalTerm.getSynonyms());
	    
	    return webResource.queryParams(queryParams).put(Success.class);
	}
	
	public String updateProvisionalTermReturnString(String termId, ProvisionalTerm provisionalTerm) throws JAXBException {	    
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("apikey", this.apiKey);
	    queryParams.add("termid", termId);
	    queryParams.add("submittedby", this.userId);
	    if(provisionalTerm.hasTerm())
	    	queryParams.add("preferredname", provisionalTerm.getTerm());
	    if(provisionalTerm.hasDefinition()) 
	    	queryParams.add("definition", provisionalTerm.getDefinition());
	    if(provisionalTerm.hasOntologyIds())
	    	queryParams.add("ontologyids", provisionalTerm.getOntologyids());
	    if(provisionalTerm.hasPermanentId())
	    	queryParams.add("permanentid", provisionalTerm.getPermanentid());
	    if(provisionalTerm.hasSuperClass())
	    	queryParams.add("superclass", superclassPrefix + provisionalTerm.getSuperclass());
	    if(provisionalTerm.hasSynonyms())
	    	queryParams.add("synonyms", provisionalTerm.getSynonyms());
	    
	    return webResource.queryParams(queryParams).put(String.class);
	}
	
	/**
	 * Delete a provisional term.
	 * @return Success
	 * @param termId
	 * @throws JAXBException when returned XML cannot be parsed into schema, 
	 * e.g. because no success but error response was returned by web service
	 */
	public Success deleteProvisionalTerm(String termId) throws JAXBException {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("termid", termId);
	    queryParams.add("apikey", this.apiKey);
	    return webResource.queryParams(queryParams).delete(Success.class);
	}
	
	public String deleteProvisionalTermReturnString(String termId) throws JAXBException {
		String url = this.apiUrl + "provisional";
	    WebResource webResource = client.resource(url);
	    MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
	    queryParams.add("termid", termId);
	    queryParams.add("apikey", this.apiKey);
	    return webResource.queryParams(queryParams).delete(String.class);
	}
	
	public static void main(String[] args) throws JAXBException, IOException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties(); 
		properties.load(loader.getResourceAsStream("config.properties"));
		String url = properties.getProperty("bioportalUrl");
		String userId = properties.getProperty("bioportalUserId");
		String apiKey = properties.getProperty("bioportalApiKey");
		BioPortalClient bioPortalClient = new BioPortalClient(url, userId, apiKey);	
		
		ProvisionalTerm provisionalTerm = new ProvisionalTerm();
		provisionalTerm.setTerm("test name");
		provisionalTerm.setDefinition("this is a test definition");
		String response = bioPortalClient.createProvisionalTermReturnString(provisionalTerm);
		
		System.out.println("here: " + response);
		
		/*String[] ids = new String[] { 
				"http://purl.bioontology.org/ontology/provisional/5325b90e-0334-48a1-98a5-3f414c146276"
			}; 
				
		for(String id : ids) {
			bioPortalClient.deleteProvisionalTerm(id);
		} */
		
		
		//for(int i=0; i<56; i++) {
		/*	Filter filter = new Filter();
			filter.setSubmittedBy(userId);
			//filter.setPageSize("279");
			//filter.setPageNum(String.valueOf(i));
			//filter.setImplementedTermsOnly("true");
			Success success = bioPortalClient.getProvisionalTerms(filter);
		*/	
			//System.out.println("-----------------done-------------------");
			//System.out.println(success.getData().getList());
			//System.out.println(success.getData().getClassBean());
		//}
		
		/*System.out.println("Provide a new term: ");
		ProvisionalTerm provisionalTerm = new ProvisionalTerm();
		provisionalTerm.setPreferredname("test name");
		provisionalTerm.setDefinition("this is a test definition");
		Success createSuccess = bioPortalClient.createProvisionalTerm(provisionalTerm);
		
		System.out.println("All the terms provided by this userId: ");
		Filter filter = new Filter();
		filter.setSubmittedBy(userId);
		Success getSuccess = bioPortalClient.getProvisionalTerms(filter);
		
		List<Object> fullIdOrIdOrLabel = createSuccess.getData().getClassBean().getFullIdOrIdOrLabel();
		for(Object object : fullIdOrIdOrLabel) {
			if(object instanceof JAXBElement) {
				JAXBElement<String> possibleIdElement = (JAXBElement<String>)object;
				if(possibleIdElement.getName().toString().equals("id")) {
					ProvisionalTerm updatedProvisionalTerm = new ProvisionalTerm();
					updatedProvisionalTerm.setPreferredname("test name 2");
					System.out.println("update the provided term with a new preferredname");
					bioPortalClient.updateProvisionalTerm(possibleIdElement.getValue(), updatedProvisionalTerm);
					System.out.println("view the updated term:");
					Success getSuccess2 = bioPortalClient.getProvisionalTerm(possibleIdElement.getValue());
					
					System.out.println("delete provided term");
					bioPortalClient.deleteProvisionalTerm(possibleIdElement.getValue());
				}
			}
		}
		
		System.out.println("All the terms provided by this userId. The provided term should have disappeared.");
		Success getSuccess3 = bioPortalClient.getProvisionalTerms(filter); */
	}
}

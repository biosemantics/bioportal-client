package edu.arizona.biosemantics.bioportal.client;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Logger;

import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.jackson.JacksonFeature;

import edu.arizona.biosemantics.bioportal.model.Ontology;
import edu.arizona.biosemantics.bioportal.model.ProvisionalClass;
import edu.arizona.biosemantics.bioportal.model.Search;
import edu.arizona.biosemantics.bioportal.model.SearchResultPage;

public class BioPortalClient {

	private String apiKey;
	private String apiUrl;
	private Client client;
	private WebTarget target;
	
	/**
	 * @param apiUrl
	 * @param apiKey
	 */
	public BioPortalClient(String apiUrl, String apiKey) {
		this.apiUrl = apiUrl;
		this.apiKey = apiKey;
	}
	
	public void open() {		
		client = ClientBuilder.newBuilder().withConfig(new ClientConfig()).register(JacksonFeature.class).build();
		client.register(new LoggingFilter(Logger.getAnonymousLogger(), true));
		
		//this doesn't seem to work for posts (among others), even though it is documented as such, use authentication header instead there
		//target = client.target(this.apiUrl).queryParam("apikey", this.apiKey);
		target = client.target(this.apiUrl);
	}
	
	public void close() {
		client.close();
	}
	
	
	
	public Future<List<Ontology>> getOntologies() {
		return this.getOntologiesInvoker().get(new GenericType<List<Ontology>>() {});
	}
	
	public void getOntologies(InvocationCallback<List<Ontology>> callback) {
		this.getOntologiesInvoker().get(callback);
	}
	
	public Future<SearchResultPage> searchClasses(Search search) {
		return this.getSearchInvoker(search).get(SearchResultPage.class);
	}
	
	public void searchClasses(Search search, InvocationCallback<SearchResultPage> callback) {
		this.getSearchInvoker(search).get(callback);
	}
	
	public Future<SearchResultPage> getSearchResultPage(String url) {
		WebTarget target = client.target(url);
		return getSearchResultPageInvoker(target).get(SearchResultPage.class);
	}
	
	public void getSearchResultPage(String url, InvocationCallback<SearchResultPage> callback) {
		WebTarget target = client.target(url);
		getSearchResultPageInvoker(target).get(callback);
	}
	
	private AsyncInvoker getSearchResultPageInvoker(WebTarget target) {
		return target.request(MediaType.APPLICATION_JSON).header("Authorization", "apikey token=" + this.apiKey).async();
	}

	private AsyncInvoker getSearchInvoker(Search search) {
		WebTarget result = target.path("search").queryParam("q", search.getQuery());
		if(search.hasOntologies())
			result = result.queryParam("ontologies", search.getOntologiesString());
		return result.queryParam("exact_match", search.isExactMatch())
				.queryParam("include_views", search.isIncludeViews())
				.queryParam("required_definition", search.isRequiresDefinition())
				.queryParam("include_properties", search.isIncludeProperties())
				.queryParam("include_obsolete", search.isIncludeObsolete())				
				.request(MediaType.APPLICATION_JSON).header("Authorization", "apikey token=" + this.apiKey).async();
	}

	private AsyncInvoker getOntologiesInvoker() {
		return target.path("ontologies").request(MediaType.APPLICATION_JSON).header("Authorization", "apikey token=" + this.apiKey).async();
	}
	
	
		
	public Future<List<ProvisionalClass>> getProvisionalClasses() {
		return this.getGetInvoker().get(new GenericType<List<ProvisionalClass>>() {});
	}
	
	public void getProvisionalClasses(InvocationCallback<List<ProvisionalClass>> callback) {
		this.getGetInvoker().get(callback);
	}
	
	public Future<ProvisionalClass> getProvisionalClass(String id) {
		return this.getGetInvoker(id).get(ProvisionalClass.class);
	}
	
	public void getProvisionalClass(String id, InvocationCallback<ProvisionalClass> callback) {
		this.getGetInvoker(id).get(callback);
	}
	
	public Future<ProvisionalClass> postProvisionalClass(ProvisionalClass provisionalClass) {
		return this.getPostInvoker(provisionalClass).post(Entity.entity(provisionalClass, MediaType.APPLICATION_JSON), ProvisionalClass.class);
	}
	
	public void postProvisionalClass(ProvisionalClass provisionalClass, InvocationCallback<ProvisionalClass> callback) {
		this.getPostInvoker(provisionalClass).post(Entity.entity(provisionalClass, MediaType.APPLICATION_JSON), callback);
	}
	
	public Future<ProvisionalClass> deleteProvisionalClass(String id) {
	    return this.getDeleteInvoker(id).delete(ProvisionalClass.class);
	}

	public void deleteProvisionalClass(String id, InvocationCallback<ProvisionalClass> callback) {
		this.getDeleteInvoker(id).delete(callback);
	}
	
	public Future<ProvisionalClass> patchProvisionalClass(ProvisionalClass provisionalClass) {
	    return this.getPatchInvoker(provisionalClass).method("POST", Entity.entity(provisionalClass, MediaType.APPLICATION_JSON), ProvisionalClass.class);
	}
	
	public void patchProvisionalClass(ProvisionalClass provisionalClass, InvocationCallback<ProvisionalClass> callback) {
		this.getPatchInvoker(provisionalClass).method("POST", Entity.entity(provisionalClass, MediaType.APPLICATION_JSON), callback);
	}
	
	private AsyncInvoker getGetInvoker() {
		return target.path("provisional_classes").request(MediaType.APPLICATION_JSON).header("Authorization", "apikey token=" + this.apiKey).async();
	}
	
	private AsyncInvoker getGetInvoker(String id) {
		//id = URLEncoder.encode(id,"UTF-8"); 
		return target.path("provisional_classes").path(id).request(MediaType.APPLICATION_JSON).header("Authorization", "apikey token=" + this.apiKey).async();
	}
	
	private AsyncInvoker getPatchInvoker(ProvisionalClass provisionalClass) {
		return target.path("provisional_classes").path(provisionalClass.getShortId()).request(MediaType.APPLICATION_JSON).header("Authorization", "apikey token=" + this.apiKey)
				.header("X-HTTP-Method-Override", "PATCH").async();
	}
	
	private AsyncInvoker getPostInvoker(ProvisionalClass provisionalClass) {
		return target.path("provisional_classes").request(MediaType.APPLICATION_JSON).header("Authorization", "apikey token=" + this.apiKey).async();
	}
	
	private AsyncInvoker getDeleteInvoker(String id) {
		//id = URLEncoder.encode(id,"UTF-8"); 
		return  target.path("provisional_classes").path(id).request(MediaType.APPLICATION_JSON).header("Authorization", "apikey token=" + this.apiKey).async();	
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ExecutionException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Properties properties = new Properties(); 
		properties.load(loader.getResourceAsStream("config.properties"));
		String url = properties.getProperty("bioportalUrl");
		String apiKey = properties.getProperty("bioportalApiKey");
		BioPortalClient bioPortalClient = new BioPortalClient(url, apiKey);
		bioPortalClient.open();
		
		Search search = new Search();
		List<Ontology> ontologies = new LinkedList<Ontology>();
		Ontology ontology = new Ontology();
		//ontology.setId("http://data.bioontology.org/ontologies/PATO");
		//ontologies.add(ontology);
		//search.setOntologies(ontologies);
		search.setQuery("stem");
		search.setExactMatch(true);
		search.setRequiresDefinition(true);
		search.setIncludeObsolete(false);
		SearchResultPage result = bioPortalClient.searchClasses(search).get();
		Files.write( Paths.get("out.txt"), (result.toString()+"\n").getBytes(), StandardOpenOption.APPEND);
		
		System.out.println(result.toString());
		while(result.getNextPage() != null) {
			String nextPage = result.getNextPage();
			result = bioPortalClient.getSearchResultPage(nextPage).get();
			System.out.println(result.toString());
			Files.write( Paths.get("out.txt"), (result.toString()+"\n").getBytes(), StandardOpenOption.APPEND);
		}
		bioPortalClient.close();		
		
		/*
			
		Future<List<ProvisionalClass>> result = bioPortalClient.getProvisionalClasses();
		List<ProvisionalClass> list = result.get();
		
		ProvisionalClassFilter filter = new ProvisionalClassFilter(new FilterCriteria<ProvisionalClass>() {
			@Override
			public boolean filter(ProvisionalClass t) {
				return !t.getCreator().equals(userId);
			}
		});
		filter.filter(list);

		System.out.println(list);
		*/
		
		//Future<ProvisionalClass> result0 = bioPortalClient.getProvisionalClass("37179c50-65ec-0131-7e69-005056010073");
		//result0.get();
		
		
		/*ProvisionalClass submission = new ProvisionalClass();
		submission.setLabel("test2");
		submission.setCreator(userId);
		Future<ProvisionalClass> postRes = bioPortalClient.postProvisionalClass(submission);
		postRes.get();
		*/
		
		//for(ProvisionalClass entry : list) {
		//	Future<ProvisionalClass> res = bioPortalClient.deleteProvisionalClass(entry.getId());
		//	res.get();
		//}
		
		
		/*Future<ProvisionalClass> result2 = bioPortalClient.getProvisionalClass("737af2a0-6751-0131-e6d9-005056010074");
		ProvisionalClass resultClass = result2.get();
		System.out.println(resultClass);
		resultClass.setLabel("test3");
		*/
		
		/*ProvisionalClass patch = new ProvisionalClass();
		patch.setId("http://data.bioontology.org/provisional_classes/737af2a0-6751-0131-e6d9-005056010074");
		patch.setCreator(userId);
		patch.setLabel("test3");
		*/
		//Future<ProvisionalClass> patchRes = bioPortalClient.patchProvisionalClass(resultClass);
		//patchRes.get();
	
		
		//Future<ProvisionalClass> result3 = bioPortalClient.deleteProvisionalClass("f7e6d4e0-8130-0131-9589-005056010073");
		//System.out.println(result3.get());
		
		/*ProvisionalClass test3 = list.get(0);
		test3.setLabel("test334");
		System.out.println(bioPortalClient.patchProvisionalClass(test3).get());
		*/
		bioPortalClient.close();
	}

}

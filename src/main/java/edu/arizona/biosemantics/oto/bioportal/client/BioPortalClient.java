package edu.arizona.biosemantics.oto.bioportal.client;

import java.io.IOException;
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

import edu.arizona.biosemantics.oto.bioportal.beans.ProvisionalClass;

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
		final String userId = properties.getProperty("bioportalUserId");
		String apiKey = properties.getProperty("bioportalApiKey");
		BioPortalClient bioPortalClient = new BioPortalClient(url, apiKey);
		bioPortalClient.open();
			
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
		
		ProvisionalClass patch = new ProvisionalClass();
		patch.setId("http://data.bioontology.org/provisional_classes/737af2a0-6751-0131-e6d9-005056010074");
		patch.setCreator(userId);
		patch.setLabel("test3");
		Future<ProvisionalClass> patchRes = bioPortalClient.patchProvisionalClass(patch);
		patchRes.get();
		
		//Future<ProvisionalClass> result2 = bioPortalClient.getProvisionalClass("4e3b6790-65eb-0131-7e69-005056010073");
		//System.out.println(result2.get());
		
		//Future<ProvisionalClass> result = bioPortalClient.deleteProvisionalClass("4e3b6790-65eb-0131-7e69-005056010073");
		//System.out.println(result.get());
		
		bioPortalClient.close();
	}

}

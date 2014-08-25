package edu.arizona.biosemantics.bioportal.model;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.annotate.JsonAnySetter;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

public class SearchResultPage {
	
	public static class SearchResult {
		
		private String id;
		private String label;
		private String definition;
		private String url;
		
		public SearchResult() { }
		
		public String getId() {
			return id;
		}

		@JsonProperty("@id")
		public void setId(String id) {
			this.id = id;
		}

		public String getLabel() {
			return label;
		}

		@JsonProperty("prefLabel")
		public void setLabel(String label) {
			this.label = label;
		}
		
		public String getDefinition() {
			return definition;
		}

		public void setDefinition(String definition) {
			this.definition = definition;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@JsonAnySetter
		public void handleUnknown(String key, Object value) {}
	}
		
	private static ObjectMapper objectMapper = new ObjectMapper();
	private String nextPage;
	private List<SearchResult> searchResults;
	
	public SearchResultPage() { }
	
	public SearchResultPage(String nextPage, List<SearchResult> searchResults) {
		super();
		this.nextPage = nextPage;
		this.searchResults = searchResults;
	}
	
	public String getNextPage() {
		return nextPage;
	}
	
	public List<SearchResult> getSearchResults() {
		return searchResults;
	}
	
	@JsonProperty("collection")
	public void setSearchResults(List<SearchResult> searchResults) {
		this.searchResults = searchResults;
	}
	
	@JsonAnySetter
	public void handleUnknown(String key, Object value) { 		
		if(key.equals("links") && value instanceof Map) {
			Map valueMap = (Map)value;
			this.nextPage = (String)valueMap.get("nextPage");
		}
	}
	
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

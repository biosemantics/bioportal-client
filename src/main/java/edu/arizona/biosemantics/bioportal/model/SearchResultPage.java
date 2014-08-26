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
		private List<String> definitions;
		private String ontology;
		
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
		
		public List<String> getDefinitions() {
			return definitions;
		}

		@JsonProperty("definition")
		public void setDefinitions(List<String> definitions) {
			this.definitions = definitions;
		}


		public String getOntology() {
			return ontology;
		}

		@JsonAnySetter
		public void handleUnknown(String key, Object value) {
			if(key.equals("links") && value instanceof Map) {
				Map valueMap = (Map)value;
				Object object = valueMap.get("ontology");
				if(object != null && object instanceof String)
					this.ontology = (String)object;
			}
		}
	}
		
	private static ObjectMapper objectMapper = new ObjectMapper();
	private String nextPage;
	private int pageCount;
	private List<SearchResult> searchResults;
	
	public SearchResultPage() { }
	
	public SearchResultPage(String nextPage, int pageCount, List<SearchResult> searchResults) {
		super();
		this.nextPage = nextPage;
		this.pageCount = pageCount;
		this.searchResults = searchResults;
	}
		
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
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
			Object object = valueMap.get("nextPage");
			if(object != null && object instanceof String)
				this.nextPage = (String)object;
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

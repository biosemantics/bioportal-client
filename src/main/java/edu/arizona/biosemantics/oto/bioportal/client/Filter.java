package edu.arizona.biosemantics.oto.bioportal.client;

import java.util.Collection;

public interface Filter<T> {
	
	public void filter(Collection<T> collection);
	
}

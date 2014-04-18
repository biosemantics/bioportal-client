package edu.arizona.biosemantics.bioportal.client;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

import edu.arizona.biosemantics.bioportal.model.ProvisionalClass;

public class ProvisionalClassFilter implements Filter<ProvisionalClass> {

	private Collection<FilterCriteria<ProvisionalClass>> filterCriterias;

	public ProvisionalClassFilter(FilterCriteria<ProvisionalClass> filterCriteria) {
		this.filterCriterias = new LinkedList<FilterCriteria<ProvisionalClass>>();
		filterCriterias.add(filterCriteria);
	}
	
	public ProvisionalClassFilter(Collection<FilterCriteria<ProvisionalClass>> filterCriterias) {
		this.filterCriterias = filterCriterias;
	}
	
	@Override
	public void filter(Collection<ProvisionalClass> provisionalClasses) {
		Iterator<ProvisionalClass> iterator = provisionalClasses.iterator();
		while(iterator.hasNext()) {
			ProvisionalClass provisionalClass = iterator.next();
			for(FilterCriteria<ProvisionalClass> filterCriteria : filterCriterias) {
				if(filterCriteria.filter(provisionalClass)) {
					iterator.remove();
				}
			}
		}
	}

}

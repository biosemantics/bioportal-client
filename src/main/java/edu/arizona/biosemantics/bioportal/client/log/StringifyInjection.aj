package edu.arizona.biosemantics.bioportal.client.log;

import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;

import edu.arizona.biosemantics.common.log.AbstractStringifyInjection;
import edu.arizona.biosemantics.common.log.IPrintable;

public aspect StringifyInjection extends AbstractStringifyInjection {
	
	declare parents : edu.arizona.biosemantics.bioportal.client.model..* implements IPrintable;
	
}

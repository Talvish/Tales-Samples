package com.tales.samples.complexservice;

import com.tales.contracts.data.DataContract;
import com.tales.contracts.data.DataMember;

/**
 * A class showing inheritance and polymorphic types being used
 * as a result or input in resource contracts.
 * @author jmolnar
 *
 */
@DataContract( name="inheritance_container" )
public class InheritanceContainer {
	@DataMember( name="poly_value", valueTypes={ InheritanceSuperclass.class, InheritanceSubclass.class } )
	private InheritanceSuperclass polyValue;
	
	protected InheritanceContainer( ) {
		polyValue =  null;
	}

	protected InheritanceContainer( InheritanceSuperclass theValue  ) {
		polyValue =  theValue;
	}

	public InheritanceSuperclass getValue( ) {
		return polyValue;
	}
}

package com.tales.samples.complexservice;

import java.util.List;

import com.tales.contracts.data.DataContract;
import com.tales.contracts.data.DataMember;

/**
 * A class showing inheritance and polymorphic types being used
 * as a result or input in resource contracts.
 * @author jmolnar
 *
 */
@DataContract( name="inheritance_superclass" )
public class InheritanceSuperclass {
	@DataMember( name="member_super_a")
	private String memberSuperA;
	@DataMember( name="member_super_b")
	private boolean memberSuperB;
	@DataMember( name="member_super_c")
	private String memberSuperC;
	@DataMember( name="member_super_d")
	private boolean memberSuperD;
	@DataMember( name="member_super_e")
	private String memberSuperE;
    @DataMember( name="member_super_f")
	private List<String> memberSubDs;

	/**
	 * Constructor for serialization.
	 */
	protected InheritanceSuperclass( ) {		
	}
	
	public InheritanceSuperclass( String theA, boolean theB ) {
		memberSuperA = theA;
		memberSuperB = theB;
	}
}

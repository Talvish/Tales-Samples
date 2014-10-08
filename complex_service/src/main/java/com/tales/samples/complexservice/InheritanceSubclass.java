package com.tales.samples.complexservice;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.tales.contracts.data.DataContract;
import com.tales.contracts.data.DataMember;

/**
 * A class showing inheritance and polymorphic types being used
 * as a result or input in resource contracts.
 * @author jmolnar
 *
 */
@DataContract( name="inheritance_subclass" )
public class InheritanceSubclass extends InheritanceSuperclass {
	@DataMember( name="member_sub_a")
	private SortedSet<InheritanceSuperclass> memberSubA	= new TreeSet<InheritanceSuperclass>( );
	@DataMember( name="member_sub_b")
	private SortedSet<InheritanceSuperclass> memberSubB	= new TreeSet<InheritanceSuperclass>( );
	@DataMember( name="member_sub_c")
	private SortedSet<String> memberSubC	= new TreeSet<String>( );
    @DataMember( name="member_sub_d")
	private List<String> memberSubDs;

	/**
	 * Constructor for serialization.
	 */
	protected InheritanceSubclass( ) {		
	}
	
	public InheritanceSubclass( String theA, boolean theB ) {
		super( theA, theB );
	}
}

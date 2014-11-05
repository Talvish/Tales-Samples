// ***************************************************************************
// *  Copyright 2014 Joseph Molnar
// *
// *  Licensed under the Apache License, Version 2.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License.
// ***************************************************************************
package com.talvish.tales.samples.complexservice;

import java.util.SortedSet;
import java.util.TreeSet;

import com.talvish.tales.contracts.data.DataContract;
import com.talvish.tales.contracts.data.DataMember;

/**
 * A class showing inheritance and polymorphic types being used
 * as a result or input in resource contracts.
 * @author jmolnar
 *
 */
@DataContract( name="self_looping_class" )
public class SelfLoopingClass {
	@DataMember( name="member_a")
	private String memberA;
	@DataMember( name="member_b")
	private SortedSet<SelfLoopingClass> memberSubA	= new TreeSet<SelfLoopingClass>( );

	/**
	 * Constructor for serialization.
	 */
	protected SelfLoopingClass( ) {		
	}
	
	public SelfLoopingClass( String theA ) {
		memberA = theA;
	}
}

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
package com.tales.samples.complexservice;

import com.tales.contracts.data.DataContract;
import com.tales.contracts.data.DataMember;

/**
 * This is an example of a structure that isn't supported by Tales
 * at this time. It is a generic class extended a generic class.
 * @author Joseph Molnar
 *
 */
@DataContract( name="com.tales.data.generic_structure" )
public class GenericStructureTwo<A,B> extends GenericStructure<A,B>{
	@DataMember( name = "generic_member_z" ) A genericMemberZ;
	@DataMember( name = "generic_member_Y" ) B genericMemberY;
	
	protected GenericStructureTwo( ) {		
	}
	
	public GenericStructureTwo( A theMemberZ, B theMemberY) {
		genericMemberZ = theMemberZ;
		genericMemberY = theMemberY;
	}
}

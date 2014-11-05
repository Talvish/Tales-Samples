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

import com.talvish.tales.services.Service;
import com.talvish.tales.services.http.HttpInterface;
import com.talvish.tales.services.http.ServiceConstants;

/**
 * A service demonstrating a lot of different abilities of the tales service framework.
 * Various contracts and interface are created.
 * <br>
 * For browsing samples, this should the LAST to look at.
 * @author Joseph Molnar
 *
 */
public class ComplexService extends Service {

	public ComplexService( ) {
		super( "complex_service", "Complex Service", "A service demonstrating a lot of different abilities of the tales service framework." );
	}
	
	@Override
	protected void onStart() {
		super.onStart();

		// going to bind different resources against different interfaces as a demonstration
		this.interfaceManager.getInterface( ServiceConstants.PUBLIC_INTERFACE_NAME, HttpInterface.class ).bind( new DataStructureResource( ), "/data_structure_contract" );
		this.interfaceManager.getInterface( ServiceConstants.INTERNAL_INTERFACE_NAME, HttpInterface.class ).bind( new ResponseResource( ), "/response_contract" );
		this.interfaceManager.getInterface( ServiceConstants.MANAGEMENT_INTERFACE_NAME, HttpInterface.class ).bind( new RequestResource( ),  "/request_contract" );
		
	}
}

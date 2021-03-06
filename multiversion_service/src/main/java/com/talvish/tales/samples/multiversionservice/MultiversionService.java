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
package com.talvish.tales.samples.multiversionservice;

import com.talvish.tales.services.Service;
import com.talvish.tales.services.http.HttpInterface;
import com.talvish.tales.services.http.ServiceConstants;

/**
 * A public http service demonstrating contracts with the same method having more than one version available.
 * <br>
 * For browsing samples, this should be the SECOND to look at.
 * @author Joseph Molnar
 *
 */
public class MultiversionService extends Service {

	public MultiversionService( ) {
		super( "multiversion_service", "Multiversion Service", "A public http service demonstrating contracts with the same method having more than one version available." );
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		this.interfaceManager.getInterface( ServiceConstants.PUBLIC_INTERFACE_NAME, HttpInterface.class ).bind( new MultiversionResource( ), "/multiversion_contract" );
	}
}

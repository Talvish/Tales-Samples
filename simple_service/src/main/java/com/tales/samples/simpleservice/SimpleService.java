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
package com.tales.samples.simpleservice;

import com.tales.services.Service;
import com.tales.services.http.HttpInterface;
import com.tales.services.http.ServiceConstants;

/**
 * A simple http service that is designed to be public facing.
 * <br>
 * For browsing samples, this should the FIRST to look at.
 * @author Joseph Molnar
 *
 */
public class SimpleService extends Service {
	public SimpleService( ) {
		super( "simple_service", "Simple Service", "A public tales service show a very simple contract." );
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		this.interfaceManager.getInterface( ServiceConstants.PUBLIC_INTERFACE_NAME, HttpInterface.class ).bind( new SimpleResource( ), "/simple_contract" );
	}
}

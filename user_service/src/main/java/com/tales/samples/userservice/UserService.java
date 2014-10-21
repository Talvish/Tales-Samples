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
package com.tales.samples.userservice;

import com.tales.services.StandardService;
import com.tales.services.http.HttpInterface;
import com.tales.services.http.ServiceConstants;
import com.tales.services.http.servlets.EnableHeaderOverridesFilter;

/**
 * The is an example of a user service that is built using many of the patterns
 * I've tried to use when building up service while taking advantage of the
 * capabilities of the tales framework.
 * This is nearly a fully working sample. While it runs, it currently does not
 * do any real persistence.
 * <br>
 * For browsing samples, this should the LAST to look at.
 * @author Joseph Molnar
 *
 */
public class UserService extends StandardService {
	private UserEngine userEngine;

	public UserService( ) {
		super( "user_service", "User Service", "A simple sample service showing a functioning user service." );
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		HttpInterface httpInterface = this.interfaceManager.getInterface( ServiceConstants.PUBLIC_INTERFACE_NAME, HttpInterface.class );

		userEngine = new UserEngine( this.getConfigurationManager( ) );
		httpInterface.bind( new EnableHeaderOverridesFilter(), "/user" ); // we are in debug mode, so let's allow header overrides
		httpInterface.bind( new UserResource( userEngine ), "/user" );

		// engine's typically have their own status block and those need to be  registered with a status manager
		this.statusManager.register( "user_engine_status", userEngine.getStatus( ) );
	}
}

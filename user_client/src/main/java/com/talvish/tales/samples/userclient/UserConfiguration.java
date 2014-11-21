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
package com.talvish.tales.samples.userclient;

import com.google.common.base.Preconditions;
import com.talvish.tales.client.http.ResourceConfiguration;
import com.talvish.tales.system.configuration.ConfigurationManager;

/**
 * Configuration related items for the user client.
 * @author jmolnar
 *
 */
public class UserConfiguration extends ResourceConfiguration {
	/**
	 * Simple constructor taking the needed endpoint.
	 * @param theEndpoint the endpoint value to use
	 */
	public UserConfiguration( String theEndpoint ) {
		super( theEndpoint, false );
	}
	
	public static final String SERVICE_NAME					= "user_service";	
	public static final String ENDPOINT_SETTING				= SERVICE_NAME + ".endpoint";

	/**
	 * Helper method that will load the configuration from the configuration manager.
	 * @param theConfigurationManager the configuration manager to use
	 * @return the user configuration to use to talk to the service
	 */
	public static UserConfiguration loadConfiguration( ConfigurationManager theConfigurationManager ) {
		Preconditions.checkNotNull( theConfigurationManager, "need a configuration manager" );
		return new UserConfiguration( theConfigurationManager.getStringValue( ENDPOINT_SETTING ) );
	}
}

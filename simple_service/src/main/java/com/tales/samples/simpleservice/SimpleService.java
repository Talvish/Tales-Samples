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

import com.google.common.base.Strings;
import com.tales.services.http.HttpInterface;
import com.tales.services.http.HttpService;
import com.tales.system.configuration.PropertySource;

/**
 * A simple http service that is designed to be public facing.
 * <br>
 * For browsing samples, this should the FIRST to look at.
 * @author Joseph Molnar
 *
 */
public class SimpleService extends HttpService {

	protected SimpleService( ) {
		super( "simple_service", "Simple Service", "A public tales service show a very simple contract." );
	}
	
	@Override
	protected void onInitializeConfiguration() {
		String filename = this.getConfigurationManager( ).getStringValue( "settings.file", null ); // get a config filename	 from command-line, if available
		
		if( !Strings.isNullOrEmpty( filename ) ) {
			this.getConfigurationManager( ).addSource( new PropertySource( filename) );
		}
	};
	
	@Override
	protected void onStart() {
		super.onStart();
		
		HttpInterface httpInterface = new HttpInterface( "public", this );
		
		this.interfaceManager.register( httpInterface );
		httpInterface.bind( new SimpleResource( ), "/simple_contract" );
	}
	
    public static void main( String[ ] args ) throws Exception {
    	SimpleService service = new SimpleService( );
    	
    	service.start( args );
    	service.run( );
    	service.stop( );
	}
}

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
package com.tales.samples.customstartupservice;

import java.util.HashMap;
import java.util.Map;

import com.tales.services.Service;
import com.tales.services.ServiceHost;
import com.tales.services.http.HttpInterface;
import com.tales.services.http.ServiceConstants;
import com.tales.system.configuration.ConfigurationManager;
import com.tales.system.configuration.MapSource;

/**
 * A simple service that demonstrates how to do a complete custom start-up from
 * providing your own main, to creating your own configuration manager, to 
 * creating your own interfaces.
 * @author Joseph Molnar
 *
 */
public class CustomStartupService extends Service { 

	protected CustomStartupService( ) {
		super( "custom_startup_service", "Custom Startup Service", "A sample tales service showing how to do complete custom startup." );
	}
	
	@Override
	protected void onInitializeConfiguration() {
		// the following demonstrates the creation of custom configuration sources instead of 
		// relying on the built-in source provided using ServiceHost or if you don't want to
		// customize the configuration manager ... this is example is pretty arbitrary but
		// you can create your own source and add it to the configuration manager here
		Map<String,String> customSettings = new HashMap<String,String>( );
		customSettings.put( "custom_one", "value_one" );
		customSettings.put( "custom_two", "value_two" );
		customSettings.put( "custom_three", "3" );
		
		this.getConfigurationManager( ).addSource( new MapSource( "custom", customSettings ) );
	};

	@Override
	protected void onStart() {
		super.onStart();
		
		// the following demonstrates the creation of interfaces instead of relying on using
		// the built-in mechanism which looks for 'service.interfaces' from configuration.
		
		// setup the public interface
		HttpInterface publicHttpInterface = new HttpInterface( ServiceConstants.PUBLIC_INTERFACE_NAME, this );
		
		this.interfaceManager.register( publicHttpInterface );
		publicHttpInterface.bind( new SimpleResource( ), "/simple_contract" );
		
		// setup the management interface
		// (yes it is the same resource, but this is just demonstrating the ability to create additional interfaces)
		HttpInterface managementHttpInterface = new HttpInterface( ServiceConstants.MANAGEMENT_INTERFACE_NAME, this );
		
		this.interfaceManager.register( managementHttpInterface );
		managementHttpInterface.bind( new SimpleResource( ), "/simple_contract" );
	}
	
    public static void main( String[ ] theArgs ) throws Exception {
    	// the following demonstrates a custom main instead of relying on using
    	// ServiceHost and the use of the 'service.type' configuration entry
    	// NOTE: If you do a custom main you will need to update the POM or
    	//       whatever mechanism will generate the jar to use your main
    	//		 class for starting
    	
    	// configuration managers and their default sources can be setup any way you like
    	// for the sample we are simply using some helper methods on the ServiceHost
    	// which gives us command-line and property file configuration but you can
    	// create your own sources and hand create the configuration manager
    	ConfigurationManager configuration = ServiceHost.instantiateConfiguration( theArgs );
    	
    	// this example isn't using an separate service, but creating the the service 
    	// directly ... if a service was to be used some additional helper methods on
    	// ServiceHost can be used to load and instantiate the service
    	CustomStartupService service = new CustomStartupService( );
    	
    	service.start( configuration );
    	service.run( );
    	service.stop( );
	}
}

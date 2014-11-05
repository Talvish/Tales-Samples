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
package com.talvish.tales.samples.websiteservice;

import java.util.HashMap;

import com.talvish.tales.serialization.Readability;
import com.talvish.tales.services.OperationContext.Details;
import com.talvish.tales.services.Service;
import com.talvish.tales.services.http.ServiceConstants;
import com.talvish.tales.services.http.WebsiteInterface;

/**
 * A simple website running as a service.
 * @author Joseph Molnar
 *
 */
public class WebsiteService extends Service {

	public WebsiteService( ) {
		super( "website_service", "Website Service", "A public tales service show a very simple website calling a service." );
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		
		WebsiteInterface siteInterface = this.interfaceManager.getInterface( ServiceConstants.PUBLIC_INTERFACE_NAME, WebsiteInterface.class );
		HashMap<String,String> jspInitParameters = new HashMap<String, String>( );

		jspInitParameters.put( "keepgenerated", "TRUE" );

		siteInterface.bind( "website", jspInitParameters);
		siteInterface.bind( new SimpleResource( ), "/simple_contract" );
		
		siteInterface.setDefaultResponseDetails( Details.ALL );
		siteInterface.setDefaultResponseReadability( Readability.HUMAN );
	}
}

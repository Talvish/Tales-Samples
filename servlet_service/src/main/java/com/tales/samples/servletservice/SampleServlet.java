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
package com.tales.samples.servletservice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonPrimitive;

import com.tales.communication.Status;
import com.tales.contracts.services.http.ServletContract;
import com.tales.services.http.ResponseHelper;

/**
 * A very simple servlet being used in a Tales Service.
 * The only really main difference between Tales-based servlet and 
 * a standard servlet is the use of the ServletContract
 * annotation and registration in the main service class.
 * @author jmolnar
 *
 */
@ServletContract(name="com.tales.sample.servlet", versions="20141001")
public class SampleServlet extends HttpServlet{

	/**
	 * Used for serialization (and to get rid of a warning)  
	 */
	private static final long serialVersionUID = -4974167447356481286L;
	
	/**
	 * Default constructor.
	 */
	public SampleServlet( ) {
	}

	/**
	 * This sample oonly handles get request.
	 */
	@Override
	protected void doGet( HttpServletRequest theRequest, HttpServletResponse theResponse ) throws ServletException, IOException {

		try {
			// since this is a servlet, you and can do what you like and write back 
			// what you like, this is just to demonstrate options and helpers
			ResponseHelper.writeSuccess( theRequest, theResponse, new JsonPrimitive( "Hello World" ) );

		} catch( Exception e ) {
			ResponseHelper.writeFailure( theRequest, theResponse, Status.LOCAL_ERROR, String.format( "An exception of type '%s' with message '%s' was thrown.", e.getClass().getSimpleName(), e.getMessage() ) ) ;
		}
	}	
}

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
package com.talvish.tales.samples.userservice;

import java.util.Collection;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.talvish.tales.businessobjects.ObjectId;
import com.talvish.tales.communication.Status;
import com.talvish.tales.contracts.services.http.HeaderParam;
import com.talvish.tales.contracts.services.http.PathParam;
import com.talvish.tales.contracts.services.http.RequestParam;
import com.talvish.tales.contracts.services.http.ResourceContract;
import com.talvish.tales.contracts.services.http.ResourceOperation;
import com.talvish.tales.validation.Conditions;


/**
 * This is the resource used to the HTTP contracts. The job of 
 * the resource is to translate from an HTTP request, into
 * the needs of an engine where work is performed, and then
 * translate the output from the engine to an HTTP response.
 * <br>
 * The engine and resource use different data structures for 
 * a few reasons, including, a) not wanting to return all the data
 * stored to the outside world (e.g. security, soft deletes),
 * b) the storage shape may need to change over time, but the shape
 * of the for a version of a contract must stay the same, etc.
 * <br>
 * This example shows how the back-end/persistence doesn't actually
 * do real deletions, but soft-deletes. Since this interface
 * doesn't want to return soft deleted entities, it filters them
 * out of any result.
 * <br>
 * This example uses the Tales Conditions class which throws
 * exceptions that Tales will automatically turn into certain
 * HTTP status codes. If you prefer to not use exceptions
 * then using ResourceResult as outlined in the 
 * DataStructureResource in the ComplexService sample.
 * @author Joseph Molnar
 *
 */
@ResourceContract( name="com.tales.user_contract", versions={ "20140124" } )
public class UserResource {
	private final UserEngine engine;
	
	public UserResource( UserEngine theEngine ) {
		Preconditions.checkArgument( theEngine != null, "need an engine" );
		engine = theEngine;
	}
	
	/**
	 * The HTTP request to get a particular user.
	 */
	@ResourceOperation( name="get_user", path="GET : users/{id}" )
	public User getUser( 
			@PathParam( name="id" )ObjectId theId, 
			@HeaderParam( name="Authorization" )String theAuthToken ) {
		Conditions.checkAuthorization( validateAuthorization( theAuthToken ), "Sample", "unauthorized attempt to access resource" ); // if invalid, throws AuthorizationException which turns into HTTP status code 401
		Conditions.checkParameterNotNull( theId , "id", "an id must be given" ); // if invalid, throws InvalidParameterException, which turns into HTTP status code 400
		
		User user = engine.getUser( theId );
		// public interface doesn't support sending soft-deleted entities
		Conditions.checkFound( user != null && !user.isDeleted(), "id", "Could not find the user with id '%s'.", theId );  // if invalid, throws NotFoundException, which turns into HTTP status code 404
		return user;
	}
	
	/**
	 * The HTTP request to get all users.
	 */
	@ResourceOperation( name="get_users", path="GET : users"  )
	public Collection<User> getUsers( /*@HeaderParam( name="Authorization" )String theAuthToken*/ ) { // TODO: generally you don't want to get a list of them all, but allow filters or limiting how many, continuation tokens, etc
		//Conditions.checkAuthorization( validateAuthorization( theAuthToken ), "Sample", "unauthorized attempt to access resource" ); 
		return engine.getUsers( );
	}
	
	/**
	 * The HTTP request to create a new user in the system and showing an easy
	 * way to set the status to return on a successful call (operation created).
	 */
	@ResourceOperation( name="create_user", path="GET | POST : users/create", status=Status.OPERATION_CREATED ) // supporting GET just so a browser can easily be used for manual testing
	public User createUser( 
			 @RequestParam( name="user" )User theUser, 
			 @HeaderParam( name="Authorization" )String theAuthToken ) {
		Conditions.checkAuthorization( validateAuthorization( theAuthToken ), "Sample", "unauthorized attempt to access resource" ); 
		Conditions.checkParameterNotNull( theUser, "user", "first name must be provided" );
		Conditions.checkParameter( theUser.getId() == null, "user.id", "user id must be null" );
		Conditions.checkParameter( !Strings.isNullOrEmpty( theUser.getFirstName() ), "user.first_name", "first name must be provided" );
		
		return engine.createUser( theUser );
	}
	
	/**
	 * The HTTP request to update a user.
	 */
	@ResourceOperation( name="update_user", path="GET | POST : users/{id}/update" ) // supporting GET just so a browser can easily be used for manual testing
	public User updateUser( 
			@PathParam( name="id" )ObjectId theId, 
			@RequestParam( name="user" )User theUser,
			@HeaderParam( name="Authorization" )String theAuthToken ) {
		Conditions.checkAuthorization( validateAuthorization( theAuthToken ), "Sample", "unauthorized attempt to access resource" ); 
		Conditions.checkParameterNotNull( theId, "id", "an id must be given" );
		Conditions.checkParameterNotNull( theUser, "user", "a user must be given" );
		Conditions.checkParameter( theId.equals( theUser.getId( ) ), "id", "path id '%s' does not match the given user id '%s'", theId, theUser.getId() );
		
		User user = engine.updateUser( theUser );
		Conditions.checkFound( user != null, "id", "Could not find the user with id '%s'.", theId );
		return user;
	}

	/**
	 * The HTTP request to delete a user.
	 */
	@ResourceOperation( name="delete_user", path="GET | POST : users/{id}/delete" ) // supporting GET just so a browser can easily be used for manual testing
	public void deleteUser(
			@PathParam( name="id" )ObjectId theId, 
			@HeaderParam( name="Authorization" )String theAuthToken ) {
		Conditions.checkAuthorization( validateAuthorization( theAuthToken ), "Sample", "unauthorized attempt to access resource" ); 
		Conditions.checkParameter( theId != null, "id", "an id must be given" );
		
		boolean deleted = engine.deleteUser( theId );
		Conditions.checkFound( deleted, "id", "Could not find the user with id '%s'.", theId );
	}
	

	/**
	 * Validate the HTTP based auth header.
	 * @param theAuthorization the string to very
	 * @return th
	 */
	private boolean validateAuthorization( String theAuthorization ) {
		if( !Strings.isNullOrEmpty( theAuthorization ) ) {
			return theAuthorization.startsWith( "Sample" ); 
		} else {
			return false;
		}
	}
}

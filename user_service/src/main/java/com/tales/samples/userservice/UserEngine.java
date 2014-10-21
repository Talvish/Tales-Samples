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

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;
import com.tales.businessobjects.ObjectId;
import com.tales.communication.CommunicationException;
import com.tales.rigs.objectid.client.ObjectIdManager;
import com.tales.services.DependencyException;
import com.tales.services.DependencyException.Problem;
import com.tales.system.configuration.ConfigurationManager;

/**
 * The engine is the component that actually does the work
 * this includes any logic, persistence, etc. It is 
 * independent of the transports/communication mechanisms used.
 * This allows for a few possibilities including, a) having
 * more than one communication mechanism but same underlying
 * logic/control, b) a great entity to write tests againsts
 * since this is the pure logic/workhorse of the component.
 * @author Joseph Molnar
 */
public class UserEngine {
	private UserEngineStatus status = new UserEngineStatus( );
	private Map<ObjectId, User> storage = new HashMap<ObjectId, User>( ); // yes, fake storage
	private ObjectIdManager oidManager = null;
	
	public UserEngine( ConfigurationManager theConfigManager ) {
		Preconditions.checkNotNull( theConfigManager, "need a config manager to set things up" );
		
		String serviceEndpoint = theConfigManager.getStringValue( "id_service.endpoint" ); // no default, since we need it to run
		Long idRequestAmount = theConfigManager.getLongValue( "id_service.request_amount", 100l );
		Long idRequestThreshold = theConfigManager.getLongValue( "id_service.request_threshold", 20l );
    	
    	oidManager = new ObjectIdManager( idRequestAmount, idRequestThreshold, serviceEndpoint, "UserService/1.0");

		// since we aren't building a real storage system
		// we are faking a storage system by using a map
		// and adding a few existing users
		User user;
		
		user = new User( new ObjectId( 1, 1, 100 ) );
		user.setFirstName( "John" );		
		user.setLastName( "Doe" );	
		user.setBirthdate( LocalDate.of( 1980,  12,  14) );
		storage.put( user.getId(), user );
		
		user = new User( new ObjectId( 2, 1, 100 ) );
		user.setFirstName( "Jane" );
		user.setMiddleName( "Mildred" );
		user.setLastName( "Smith" );		
		storage.put( user.getId(), user );

	}
	
	/**
	 * The status block for the engine. This tracks
	 * engine specific states/status.
	 */
	public UserEngineStatus getStatus( ) {
		return status;
	}
	
	/**
	 * Returns a particular user, if it can be found.
	 */
	public User getUser( ObjectId theId ) {
		Preconditions.checkArgument( theId != null, "an id must be given" );
		return storage.get( theId );
	}
	
	/**
	 * Gets the users from storage.
	 */
	public Collection<User> getUsers( ) {
		// TODO: this should have a continuation token, maybe some filters, to minimize 
		//       the data returned.
		return storage.values();
	}

	/**
	 * Creates the user in storage. The parameters are not forced.
	 */
	public User createUser( User theUser ) {
		try {
			theUser.setId( oidManager.generateObjectId( User.USER_TYPE_NAME ) );
		
			storage.put( theUser.getId(), theUser);
			status.recordCreatedUser(); // update our status block
			return theUser;
		} catch( CommunicationException e ) {
			throw new DependencyException( Problem.CANNOT_COMMUNICATE, "could not generate an object id for the user", e ); // TODO: not exactly accurate (may want to move this upstream)

		} catch (InterruptedException e) {
			// this shouldn't have happened, no threads are killing things
			throw new IllegalStateException( "being interrupted" );
		}
	}

	/**
	 * Updates the user's information, though not all fields.
	 */
	public User updateUser( User theUser ) {
		Preconditions.checkArgument( theUser != null, "a user must be given if it is to be updated" );
		User user = getUser( theUser.getId() ); // makes sure we have an id
		
		if( user != null && ! user.isDeleted() ) { // we don't allow updates to soft-deleted users
			user.setFirstName( theUser.getFirstName( ) );
			user.setLastName(  theUser.getLastName( ) );
			// we don't reset the creation/modification time stamps
			storage.put( user.getId( ),  user ); // yes not needed, but pretend we are storing back into persistence

			return user;
		} else {
			return null;		
		}

	}
	
	/**
	 * Performs the removal of the user from the storage system.
	 * This actually performs a soft-delete.
	 */
	public boolean deleteUser( ObjectId theId ) {
		Preconditions.checkArgument( theId != null, "an id must be given" );
		
		User user = getUser( theId );
		if( user != null ) {
			user.indicateDeleted();
			storage.put( user.getId( ),  user ); // yes not needed, but pretend we are storing back into persistence
			status.recordDeletedUser(); // update our status block
			return true;
		} else {
			return false;
		}
	}

}

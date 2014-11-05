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

import java.time.LocalDate;

import com.google.common.base.Preconditions;
import com.talvish.tales.businessobjects.BusinessObjectBase;
import com.talvish.tales.businessobjects.ObjectId;
import com.talvish.tales.contracts.data.DataContract;
import com.talvish.tales.contracts.data.DataMember;


@DataContract( name ="com.tales.transport.user")
public class TransportUser extends BusinessObjectBase {
	@DataMember( name = "first_name" ) private String firstName;
	@DataMember( name = "middle_name" ) private String middleName;
	@DataMember( name = "last_name" ) private String lastName;
	@DataMember( name = "birthdate" ) private LocalDate birthdate;
	@DataMember( name = "email_address" ) public String emailAddress;
	
	/**
	 * A constructor used for serialization purposes.
	 */
	@SuppressWarnings("unused") 
	private TransportUser( ) {
	}
	
	/**
	 * A transforming copy constructor from the internal
	 * representation to the wire representation.
	 */
	protected TransportUser( User theUser ) {
		super( theUser );
		firstName = theUser.getFirstName();
		lastName = theUser.getLastName();
		middleName = theUser.getMiddleName();
		birthdate = theUser.getBirthdate();
	}
	
	/**
	 * Constructor taking the required id.
	 */
	public TransportUser( ObjectId theId ) {
		super( theId );
	}
	
	/**
	 * Returns the first name;
	 */
	public String getFirstName( ) {
		return firstName;
	}
	
	/**
	 * Gets the middle name.
	 * @return the middle name
	 */
	public String getMiddleName( ) {
		return middleName;
	}
	
	/**
	 * Returns the last name.
	 */
	public String getLastName( ) {
		return lastName;
	}
	
	/**
	 * Gets the birthdate.
	 * @return the birthdate 
	 */
	public LocalDate getBirthdate( ) {
		return birthdate;
	}
	
	/**
	 * Returns the primary email address of the user.
	 * @return the user's email address
	 */
	public String getEmailAddress( ) {
		return emailAddress;
	}
	
	/**
	 * Sets the email address.
	 * @param theEmailAddress the email address to set
	 */
	public void setEmailAddress( String theEmailAddress ) {
		// TODO: ideally this does some validation of the email address
		emailAddress = theEmailAddress;
	}
	
	public static User toEngineUser( TransportUser theUser ) {
		Preconditions.checkNotNull( theUser, "need a user" );
		// TODO: need to do parameter validation
		User storageUser;
		if( theUser.getId( ) == null ) {
			// means it was created
			storageUser = new User( );
		} else {
			// means it was updated
			storageUser = new User( theUser.getId( ) );
		}
		storageUser.setFirstName( theUser.getFirstName());
		storageUser.setMiddleName( theUser.getMiddleName());
		storageUser.setLastName(theUser.getLastName());
		storageUser.setBirthdate( theUser.getBirthdate());
		
		// the timestamp objects are not set here in part because
		// we opt not to trust the outside world, the engine loads
		// the user and updates the fields it wants to update
		return storageUser;
	}
	
	public static TransportUser toTransportUser( User theUser ) {
		TransportUser transportUser = new TransportUser( theUser );
		return transportUser;
	}
}

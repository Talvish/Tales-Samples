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

import com.tales.businessobjects.BusinessObjectBase;
import com.tales.businessobjects.ObjectId;


/**
 * The representation of the User entity in the engine/persistence.
 * @author Joseph Molnar
 *
 */
public class User extends BusinessObjectBase {
	public final static String TYPE_NAME = "user";
	
	// TODO: this needs to be updated to make use of the storage system
	private String firstName;
	private String middleName;
	private String lastName;
	private String emailAddress;
	
	public LocalDate birthdate;
	
	private boolean deleted = false;
	
	/**
	 * A constructor used for serialization
	 * and for updating users.
	 */
	public User( ) {
	}
	
	/**
	 * Constructor taking the required id.
	 */
	public User( ObjectId theId ) {
		super( theId );
	}
	
	/**
	 * Returns the first name;
	 */
	public String getFirstName( ) {
		return firstName;
	}
	
	/**
	 * Sets the first name;
	 */
	public void setFirstName( String theFirstName ) {
		firstName = theFirstName;
		this.indicateModified();
	}
	
	/**
	 * Gets the middle name.
	 * @return the middle name
	 */
	public String getMiddleName( ) {
		return middleName;
	}
	
	/**
	 * Sets the middle name
	 * @param theMiddleName the value to set the middle name to
	 */
	public void setMiddleName( String theMiddleName ) {
		middleName = theMiddleName;
		this.indicateModified( );
	}
	
	/**
	 * Returns the last name.
	 */
	public String getLastName( ) {
		return lastName;
	}
	
	/**
	 * Sets the last name.
	 */
	public void setLastName( String theLastName ) {
		lastName = theLastName;
		this.indicateModified();
	}
	
	/**
	 * Gets the primary email address of the user. 
	 * @return the primary email address
	 */
	public String getEmailAddress( ) {
		return this.emailAddress;
	}
	
	/**
	 * Sets the email address.
	 * @param theEmailAddress the email address for the user
	 */
	public void setEmailAddress( String theEmailAddress ){
		emailAddress = theEmailAddress;
		this.indicateModified();
	}
	
	/**
	 * Gets the birthdate.
	 * @return the birthdate 
	 */
	public LocalDate getBirthdate( ) {
		return birthdate;
	}
	
	/**
	 * Sets the birthdate.
	 * @param theBirthdate the birthdate
	 */
	public void setBirthdate( LocalDate theBirthdate ) {
		birthdate = theBirthdate;
		this.indicateModified();
	}
	
	/**
	 * Indicates if an entity is soft-deleted.
	 * @return
	 */
	public boolean isDeleted( ) {
		return deleted;
	}
	
	/**
	 * A mechanism for soft-deletion. Pretty common
	 * to not actually delete entities in a system
	 * but instead to have them 'soft deleted'.
	 */
	public void indicateDeleted( ) {
		deleted = true;
		indicateModified();
	}
}

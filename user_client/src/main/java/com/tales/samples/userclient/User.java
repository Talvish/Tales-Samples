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
package com.tales.samples.userclient;

import java.time.LocalDate;
import java.time.ZonedDateTime;




import com.tales.businessobjects.ObjectId;
import com.tales.contracts.data.DataContract;
import com.tales.contracts.data.DataMember;


@DataContract( name ="com.tales.transport.user")
public class User {
	@DataMember( name = "id") private ObjectId id;
	@DataMember( name = "creation_timestamp" ) private ZonedDateTime creationTimestamp;
	@DataMember( name = "modification_timestamp" ) private ZonedDateTime modificationTimestamp;
	@DataMember( name = "first_name" ) private String firstName;
	@DataMember( name = "middle_name" ) private String middleName;
	@DataMember( name = "last_name" ) private String lastName;
	@DataMember( name = "birthdate" ) public LocalDate birthdate;
	
	/**
	 * A constructor used for serialization purposes.
	 */
	public User( ) {
	}
	
	/**
	 * The unique id given to this object.
	 */
	public ObjectId getId( ) {
		return id;
	}
	
	/**
	 * The date, in UTC, when the object was created.
	 */
	public ZonedDateTime getCreationTimestamp( ) {
		return creationTimestamp;
	}	
	
	/**
	 * The date, in UTC when the object was last modified.
	 */
	public ZonedDateTime getModificationTimestamp( ) {
		return modificationTimestamp;
	}

	/**
	 * Returns the first name;
	 */
	public String getFirstName( ) {
		return firstName;
	}
	
	/**
	 * Sets the first name.
	 */
	public void setFirstName( String theName ) {
		firstName = theName;
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
	public void setLastName( String theName ) {
		lastName = theName;
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
	}
}

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

import java.time.LocalDate;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.talvish.tales.businessobjects.BusinessObjectBase;
import com.talvish.tales.contracts.data.DataContract;
import com.talvish.tales.contracts.data.DataMember;


@DataContract( name ="com.tales.transport.user")
public class User extends BusinessObjectBase {
	@DataMember( name = "first_name" ) private String firstName;
	@DataMember( name = "middle_name" ) private String middleName;
	@DataMember( name = "last_name" ) private String lastName;
	@DataMember( name = "birthdate" ) public LocalDate birthdate;
	@DataMember( name = "email_address" ) public String emailAddress;
	
	/**
	 * A constructor used for serialization purposes.
	 */
	public User( ) {
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
		Preconditions.checkArgument( !Strings.isNullOrEmpty( theName ), "user needs a first name" );
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
}

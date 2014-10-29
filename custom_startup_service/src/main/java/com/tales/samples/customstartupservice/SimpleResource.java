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

import com.tales.contracts.services.http.ResourceContract;
import com.tales.contracts.services.http.ResourceOperation;
import com.tales.contracts.services.http.ResourceOperation.Mode;

/***
 * This a very simple contract that returns 'hello world', though is running in non-blocking mode.
 * Normally non-blocking would be for calls that do more work than this.
 * @author Joseph Molnar
 *
 */
@ResourceContract( name="com.tales.simple_contract", versions={ "20140124" }, mode=Mode.NONBLOCKING )
public class SimpleResource {
	/**
	 * An HTTP GET operation that simple returns the string 'hello world'.
	 */
	@ResourceOperation( name="hello_world", path="GET : hello" )
	public String hello( ) {
		return "hello world";
	}
}

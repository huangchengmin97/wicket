/*
 * $Id: org.eclipse.jdt.ui.prefs 5004 2006-03-17 20:47:08 -0800 (Fri, 17 Mar
 * 2006) eelco12 $ $Revision: 5004 $ $Date: 2006-03-17 20:47:08 -0800 (Fri, 17
 * Mar 2006) $
 * 
 * ==============================================================================
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package wicket.examples.wizard;

import java.util.HashSet;
import java.util.Set;

import wicket.IClusterable;

/**
 * Domain class for the new user wizard example.
 * 
 * @author Eelco Hillenius
 */
public final class User implements IClusterable
{
	private String department = "";
	private String email;

	private String firstName;
	private String lastName;

	private Set<String> roles = new HashSet<String>();

	private String rolesSetName;

	private String userName;

	/**
	 * Gets departement.
	 * 
	 * @return departement
	 */
	public String getDepartment()
	{
		return department;
	}

	/**
	 * Gets email.
	 * 
	 * @return email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * Gets firstName.
	 * 
	 * @return firstName
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * Gets lastName.
	 * 
	 * @return lastName
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * Gets roles.
	 * 
	 * @return roles
	 */
	public Set<String> getRoles()
	{
		return roles;
	}

	/**
	 * Gets rolesSetName.
	 * 
	 * @return rolesSetName
	 */
	public String getRolesSetName()
	{
		return rolesSetName;
	}

	/**
	 * Gets userName.
	 * 
	 * @return userName
	 */
	public String getUserName()
	{
		return userName;
	}

	/**
	 * Sets departement.
	 * 
	 * @param departement
	 *            departement
	 */
	public void setDepartment(String departement)
	{
		if (departement == null)
		{
			departement = "";
		}
		this.department = departement;
	}

	/**
	 * Sets email.
	 * 
	 * @param email
	 *            email
	 */
	public void setEmail(final String email)
	{
		this.email = email;
	}

	/**
	 * Sets firstName.
	 * 
	 * @param firstName
	 *            firstName
	 */
	public void setFirstName(final String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * Sets lastName.
	 * 
	 * @param lastName
	 *            lastName
	 */
	public void setLastName(final String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * Sets roles.
	 * 
	 * @param roles
	 *            roles
	 */
	public void setRoles(final Set<String> roles)
	{
		this.roles = roles;
	}

	/**
	 * Sets rolesSetName.
	 * 
	 * @param rolesSetName
	 *            rolesSetName
	 */
	public void setRolesSetName(final String rolesSetName)
	{
		this.rolesSetName = rolesSetName;
	}

	/**
	 * Sets userName.
	 * 
	 * @param userName
	 *            userName
	 */
	public void setUserName(final String userName)
	{
		this.userName = userName;
	}
}

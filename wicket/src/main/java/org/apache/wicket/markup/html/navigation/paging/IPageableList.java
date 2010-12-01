/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.markup.html.navigation.paging;

/**
 * An pageable interface that presents items in an page rather and need to provide items count an
 * offset meta data for pagination.
 */
public interface IPageableList extends IPageable
{
	/**
	 * Gets the total number of items this object has.
	 * 
	 * @return The total number of items this object has.
	 */
	int getItemCount();

	/**
	 * gets the index of the first visible row
	 * 
	 * @return first visible row
	 */
	int getItemOffset();

	/**
	 * maximum number of visible items per page
	 * 
	 * @return number of items
	 */
	int getItemsPerPage();

}

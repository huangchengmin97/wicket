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
package org.apache.wicket.cdi;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.wicket.cdi.testapp.TestAppScope;
import org.apache.wicket.cdi.testapp.TestApplication;
import org.apache.wicket.cdi.testapp.TestConversationBean;
import org.apache.wicket.cdi.testapp.TestConversationPage;
import org.apache.wicket.cdi.testapp.TestQualifier;

import org.apache.wicket.markup.html.WebComponent;
import org.apache.wicket.util.tester.WicketTester;
import org.jglue.cdiunit.AdditionalClasses;
import org.jglue.cdiunit.CdiRunner;
import org.jglue.cdiunit.ContextController;
import org.junit.After;
import org.junit.Assert;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests for ComponentInjector
 */
@RunWith(CdiRunner.class)
@AdditionalClasses({
		BehaviorInjector.class,
		CdiConfiguration.class,
		CdiShutdownCleaner.class,
		ComponentInjector.class,
		ConversationExpiryChecker.class,
		ConversationPropagator.class,
		DetachEventEmitter.class,
		NonContextualManager.class,
		SessionInjector.class,
		MockContainer.class,
		TestAppScope.class,
		TestConversationBean.class})
public class ComponentInjectorTest extends Assert
{

	private WicketTester tester;
	@Inject
	ContextController contextController;
	@Inject
	ComponentInjector componentInjector;

	@Before
	public void before()
	{
		tester = new WicketTester(new TestApplication());
		prepareRequest(tester.getRequest());
	}

	@After
	public void after()
	{
		tester.destroy();
		tester = null;
	}

	public void prepareRequest(HttpServletRequest request)
	{
		contextController.openRequest(request);
		contextController.openSession(request);
		contextController.openConversation(request);
	}

	/**
	 * https://issues.apache.org/jira/browse/WICKET-5226
	 */
	@Test
	public void innerNonStaticClass()
	{

		TestNonStaticComponent component = new TestNonStaticComponent("someId");
		assertNull(component.dependency);
		componentInjector.onInstantiation(component);
		assertNull(component.dependency);
	}

	/**
	 * https://issues.apache.org/jira/browse/WICKET-5226
	 */
	@Test
	public void innerStaticClass()
	{
		TestStaticComponent component = new TestStaticComponent("someId");
		componentInjector.onInstantiation(component);
		assertEquals(component.dependency, "Test String");
	}

	@Test
	public void anonymousInnerClass()
	{

		WebComponent component = new WebComponent("someId")
		{
			@Inject
			private String dependency;

			@Override
			public String toString()
			{
				return dependency;
			}
		};
		componentInjector.onInstantiation(component);
		assertNull(component.toString());
	}

	private class TestNonStaticComponent extends WebComponent
	{

		@Inject
		private String dependency;

		public TestNonStaticComponent(String id)
		{
			super(id);
		}
	}

	private static class TestStaticComponent extends WebComponent
	{

		@Inject
		@TestQualifier
		private String dependency;

		public TestStaticComponent(String id)
		{
			super(id);
		}
	}
}

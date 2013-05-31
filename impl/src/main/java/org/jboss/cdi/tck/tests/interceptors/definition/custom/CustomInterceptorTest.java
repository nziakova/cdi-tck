/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.cdi.tck.tests.interceptors.definition.custom;

import static javax.enterprise.inject.spi.InterceptionType.AROUND_INVOKE;
import static javax.enterprise.inject.spi.InterceptionType.AROUND_TIMEOUT;
import static javax.enterprise.inject.spi.InterceptionType.POST_ACTIVATE;
import static javax.enterprise.inject.spi.InterceptionType.POST_CONSTRUCT;
import static javax.enterprise.inject.spi.InterceptionType.PRE_DESTROY;
import static javax.enterprise.inject.spi.InterceptionType.PRE_PASSIVATE;
import static org.jboss.cdi.tck.cdi.Sections.ABD;
import static org.jboss.cdi.tck.cdi.Sections.INTERCEPTOR_RESOLUTION;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.cdi.tck.AbstractTest;
import org.jboss.cdi.tck.shrinkwrap.WebArchiveBuilder;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecAssertions;
import org.jboss.test.audit.annotations.SpecVersion;
import org.testng.annotations.Test;

@SpecVersion(spec = "cdi", version = "1.1 Final Release")
public class CustomInterceptorTest extends AbstractTest {

    @Deployment
    public static WebArchive createTestArchive() {
        return new WebArchiveBuilder().withTestClassPackage(CustomInterceptorTest.class).withBeansXml("beans.xml")
                .withExtension(AfterBeanDiscoveryObserver.class).build();
    }

    @Test
    @SpecAssertions({ @SpecAssertion(section = INTERCEPTOR_RESOLUTION, id = "ca"), @SpecAssertion(section = ABD, id = "dd") })
    // WELD-238
    public void testCustomPostConstructInterceptor() {
        assert !getCurrentManager().resolveInterceptors(POST_CONSTRUCT, new SecureLiteral(), new TransactionalLiteral())
                .isEmpty();
        assert AfterBeanDiscoveryObserver.POST_CONSTRUCT_INTERCEPTOR.isGetInterceptorBindingsCalled();
        assert AfterBeanDiscoveryObserver.POST_CONSTRUCT_INTERCEPTOR.isInterceptsCalled();
    }

    @Test
    @SpecAssertions({ @SpecAssertion(section = INTERCEPTOR_RESOLUTION, id = "cb"), @SpecAssertion(section = ABD, id = "dd") })
    // WELD-238
    public void testCustomPreDestroyInterceptor() {
        assert !getCurrentManager().resolveInterceptors(PRE_DESTROY, new SecureLiteral(), new TransactionalLiteral()).isEmpty();
        assert AfterBeanDiscoveryObserver.PRE_DESTROY_INTERCEPTOR.isGetInterceptorBindingsCalled();
        assert AfterBeanDiscoveryObserver.PRE_DESTROY_INTERCEPTOR.isInterceptsCalled();
    }

    @Test
    @SpecAssertions({ @SpecAssertion(section = INTERCEPTOR_RESOLUTION, id = "cc"), @SpecAssertion(section = ABD, id = "dd") })
    // WELD-238
    public void testCustomPostActivateInterceptor() {
        assert !getCurrentManager().resolveInterceptors(POST_ACTIVATE, new SecureLiteral(), new TransactionalLiteral())
                .isEmpty();
        assert AfterBeanDiscoveryObserver.POST_ACTIVATE_INTERCEPTOR.isGetInterceptorBindingsCalled();
        assert AfterBeanDiscoveryObserver.POST_ACTIVATE_INTERCEPTOR.isInterceptsCalled();
    }

    @Test
    @SpecAssertions({ @SpecAssertion(section = INTERCEPTOR_RESOLUTION, id = "cd"), @SpecAssertion(section = ABD, id = "dd") })
    // WELD-238
    public void testCustomPrePassivateInterceptor() {
        assert !getCurrentManager().resolveInterceptors(PRE_PASSIVATE, new SecureLiteral(), new TransactionalLiteral())
                .isEmpty();
        assert AfterBeanDiscoveryObserver.PRE_PASSIVATE_INTERCEPTOR.isGetInterceptorBindingsCalled();
        assert AfterBeanDiscoveryObserver.PRE_PASSIVATE_INTERCEPTOR.isInterceptsCalled();
    }

    @Test
    @SpecAssertions({ @SpecAssertion(section = INTERCEPTOR_RESOLUTION, id = "ce"), @SpecAssertion(section = ABD, id = "dd") })
    // WELD-238
    public void testCustomAroundInvokeInterceptor() {
        assert !getCurrentManager().resolveInterceptors(AROUND_INVOKE, new SecureLiteral(), new TransactionalLiteral())
                .isEmpty();
        assert AfterBeanDiscoveryObserver.AROUND_INVOKE_INTERCEPTOR.isGetInterceptorBindingsCalled();
        assert AfterBeanDiscoveryObserver.AROUND_INVOKE_INTERCEPTOR.isInterceptsCalled();
    }

    @Test
    @SpecAssertions({ @SpecAssertion(section = INTERCEPTOR_RESOLUTION, id = "cf"), @SpecAssertion(section = ABD, id = "dd") })
    // WELD-238
    public void testCustomAroundTimeoutInterceptor() {
        assert !getCurrentManager().resolveInterceptors(AROUND_TIMEOUT, new SecureLiteral(), new TransactionalLiteral())
                .isEmpty();
        assert AfterBeanDiscoveryObserver.AROUND_TIMEOUT_INTERCEPTOR.isGetInterceptorBindingsCalled();
        assert AfterBeanDiscoveryObserver.AROUND_TIMEOUT_INTERCEPTOR.isInterceptsCalled();
    }
}

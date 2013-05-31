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
package org.jboss.cdi.tck.tests.extensions.lifecycle.processBeanAttributes.synthetic;

import static org.jboss.cdi.tck.TestGroups.INTEGRATION;
import static org.jboss.cdi.tck.cdi.Sections.BM_OBTAIN_BEANATTRIBUTES;
import static org.jboss.cdi.tck.cdi.Sections.PAT;
import static org.jboss.cdi.tck.cdi.Sections.PBA;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;

import java.util.Set;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanAttributes;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.cdi.tck.AbstractTest;
import org.jboss.cdi.tck.literals.AnyLiteral;
import org.jboss.cdi.tck.shrinkwrap.WebArchiveBuilder;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecAssertions;
import org.jboss.test.audit.annotations.SpecVersion;
import org.testng.annotations.Test;

/**
 * <p>
 * This test was originally part of Weld test suite.
 * <p>
 *
 * @author Jozef Hartinger
 * @author Martin Kouba
 */
@SpecVersion(spec = "cdi", version = "1.1 Final Release")
public class ProcessBeanAttributesNotFiredForSyntheticBeanTest extends AbstractTest {

    @Deployment
    public static WebArchive createTestArchive() {
        return new WebArchiveBuilder().withTestClassPackage(ProcessBeanAttributesNotFiredForSyntheticBeanTest.class)
                .withExtension(BicycleExtension.class).build();
    }

    @Inject
    BicycleExtension bicycleExtension;

    @Test(groups = INTEGRATION)
    @SpecAssertions({ @SpecAssertion(section = BM_OBTAIN_BEANATTRIBUTES, id = "a"), @SpecAssertion(section = PBA, id = "ae"),
            @SpecAssertion(section = PAT, id = "bc") })
    public void testProcessBeanAttributesNotFired() {

        assertTrue(bicycleExtension.isVetoed());

        BeanAttributes<Bicycle> attributesBeforeRegistering = bicycleExtension.getBicycleAttributesBeforeRegistering();
        assertEquals(attributesBeforeRegistering.getScope(), ApplicationScoped.class);
        assertTrue(typeSetMatches(attributesBeforeRegistering.getTypes(), Object.class, Vehicle.class, Bicycle.class));
        assertFalse(attributesBeforeRegistering.isAlternative());

        assertNull(bicycleExtension.getBicycleAttributesBeforeModifying());

        Set<Bean<Bicycle>> beans = getBeans(Bicycle.class, AnyLiteral.INSTANCE);
        assertEquals(beans.size(), 1);
        Bean<Bicycle> bean = beans.iterator().next();
        assertEquals(bean.getScope(), ApplicationScoped.class);
        assertTrue(typeSetMatches(bean.getTypes(), Object.class, Vehicle.class, Bicycle.class));
        assertFalse(bean.isAlternative());
    }
}

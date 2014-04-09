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
package org.jboss.cdi.tck.tests.extensions.container.event;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.BeforeShutdown;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;

public class ProcessAnnotatedTypeObserver implements Extension {
    private static AnnotatedType<Sheep> statelessSessionBeanType = null;
    private static AnnotatedType<Cow> statefulSessionBeanType = null;
    private static AnnotatedType<SheepInterceptor> sessionBeanInterceptorType = null;
    private static AnnotatedType<Farm> managedBeanType = null;

    public void cleanup(@Observes BeforeShutdown shutdown) {
        statefulSessionBeanType = null;
        statelessSessionBeanType = null;
        sessionBeanInterceptorType = null;
        managedBeanType = null;
    }

    public void observeStatelessSessionBean(@Observes ProcessAnnotatedType<Sheep> event) {
        statelessSessionBeanType = event.getAnnotatedType();
    }

    public void observeStatefulSessionBean(@Observes ProcessAnnotatedType<Cow> event) {
        statefulSessionBeanType = event.getAnnotatedType();
    }

    public void observeSessionBeanInterceptor(@Observes ProcessAnnotatedType<SheepInterceptor> event) {
        sessionBeanInterceptorType = event.getAnnotatedType();
    }

    public void observeManagedBean(@Observes ProcessAnnotatedType<Farm> event) {
        managedBeanType = event.getAnnotatedType();
    }

    public static AnnotatedType<Sheep> getStatelessSessionBeanType() {
        return statelessSessionBeanType;
    }

    public static AnnotatedType<Cow> getStatefulSessionBeanType() {
        return statefulSessionBeanType;
    }

    public static AnnotatedType<SheepInterceptor> getSessionBeanInterceptorType() {
        return sessionBeanInterceptorType;
    }

    public static AnnotatedType<Farm> getManagedBeanType() {
        return managedBeanType;
    }
}

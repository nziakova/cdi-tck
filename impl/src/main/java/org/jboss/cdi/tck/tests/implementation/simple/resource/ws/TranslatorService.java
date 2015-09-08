/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc., and individual contributors
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
package org.jboss.cdi.tck.tests.implementation.simple.resource.ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2014-11-14T15:13:41.569+01:00
 * Generated source version: 2.7.11
 * 
 */
@WebServiceClient(name = "Translator", 
                  targetNamespace = "http://ws.resource.simple.implementation.tests.tck.cdi.jboss.org/")
public class TranslatorService extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.resource.simple.implementation.tests.tck.cdi.jboss.org/", "Translator");
    public final static QName TranslatorEndpointPort = new QName("http://ws.resource.simple.implementation.tests.tck.cdi.jboss.org/", "TranslatorEndpointPort");
    static {
        URL url = null;
        try {
            url = new URL("");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(TranslatorService.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "WEB-INF/Translator.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public TranslatorService(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public TranslatorService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TranslatorService() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public TranslatorService(WebServiceFeature... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public TranslatorService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public TranslatorService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns Translator
     */
    @WebEndpoint(name = "TranslatorEndpointPort")
    public Translator getTranslatorEndpointPort() {
        return super.getPort(TranslatorEndpointPort, Translator.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Translator
     */
    @WebEndpoint(name = "TranslatorEndpointPort")
    public Translator getTranslatorEndpointPort(WebServiceFeature... features) {
        return super.getPort(TranslatorEndpointPort, Translator.class, features);
    }

}
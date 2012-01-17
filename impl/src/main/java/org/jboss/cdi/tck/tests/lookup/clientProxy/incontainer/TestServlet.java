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
package org.jboss.cdi.tck.tests.lookup.clientProxy.incontainer;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TestServlet", loadOnStartup = 1, urlPatterns = { "/Test/*" })
public class TestServlet extends HttpServlet {
    private static final long serialVersionUID = -4722487503814381947L;
    @Inject
    private Car car;
    @Inject
    private Garage garage;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (car.getMake().equals("unknown")) {
            // set the make of the car
            car.setMake(req.getParameter("make"));
            // make sure that the garage contains the current instance
            resp.getWriter().append(garage.getMakeOfTheParkedCar());
            resp.setContentType("text/plain");
            resp.setStatus(200);
        } else {
            resp.setStatus(500);
        }
    }
}

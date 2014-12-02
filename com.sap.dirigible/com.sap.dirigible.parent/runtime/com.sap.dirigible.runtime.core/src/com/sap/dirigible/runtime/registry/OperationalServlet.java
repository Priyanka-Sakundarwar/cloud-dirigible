/*******************************************************************************
 * Copyright (c) 2014 SAP AG or an SAP affiliate company. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 *******************************************************************************/

package com.sap.dirigible.runtime.registry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OperationalServlet extends HttpServlet {

	private static final String LOGGED_OUT = "logged out"; //$NON-NLS-1$

	private static final long serialVersionUID = -1668088260723897990L;

	private static final String PARAMETER_USER = "user"; //$NON-NLS-1$
	private static final String PARAMETER_LOGOUT = "logout"; //$NON-NLS-1$

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (req.getParameter(PARAMETER_USER) != null) {
			String userName = "Guest"; //$NON-NLS-1$
			if (req.getUserPrincipal() != null) {
				userName = req.getUserPrincipal().getName();
			}
			resp.getWriter().write(userName);
			resp.getWriter().flush();
			resp.getWriter().close();
		} else if (req.getParameter(PARAMETER_LOGOUT) != null) {
			resp.setHeader("Cache-Control", "no-cache, no-store"); //$NON-NLS-1$ //$NON-NLS-2$
			resp.setHeader("Pragma", "no-cache"); //$NON-NLS-1$ //$NON-NLS-2$
			req.getSession().invalidate();
			resp.getWriter().write(LOGGED_OUT);
			resp.getWriter().flush();
			resp.getWriter().close();
		}
	}

}
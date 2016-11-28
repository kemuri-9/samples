package net.kemuri9.samples.dsannotations.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@SuppressWarnings("serial")
@Component(service={Servlet.class},
		property={
				"sling.servlet.methods=GET",
				"sling.servlet.paths=/system/mypath1",
				"sling.servlet.paths=/system/mypath2",
		},
		immediate = true)
public class SimpleSlingServlet extends GenericServlet {

	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setCharacterEncoding(StandardCharsets.UTF_8.name());
		PrintWriter writer = httpResponse.getWriter();
		writer.print("METHOD: ");
		writer.print(httpRequest.getMethod());
		writer.print("\nURI: ");
		writer.print(httpRequest.getPathInfo());
	}
}

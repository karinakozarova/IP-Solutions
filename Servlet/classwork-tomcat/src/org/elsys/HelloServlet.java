package org.elsys;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloServlet
 */
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		response.getOutputStream().println("<html><body><font size=30>" +
				"<form method='POST'>" +
					"<input type='text' name='key' placeholder='Key' />" +
					"<input type='text' name='value' placeholder='Value' /> " +
					"<input type='submit' />" +
				"</form>" +	
				"</font></body></html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		
		response.getOutputStream().println("<html><body><font size=30><b>Hello</b> " + name
				+ " " + surname + "</font></body></html>");
	}

}

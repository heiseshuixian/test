package com.java;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Vdownloadservlet
 */
@WebServlet("/Vdownloadservlet")
public class Vdownloadservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Vdownloadservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 File file = new File(request.getSession().getServletContext().getRealPath("/img/"),"img_"+0+".mp4");
	     for (int imgnum = 0;file.exists();imgnum++)
	     {
	      file  = new File(request.getSession().getServletContext().getRealPath("/img/"),"img_"+imgnum+".mp4");
	     }
	     
	}
	public void init() throws ServletException {
		// Put your code here
	}
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

}

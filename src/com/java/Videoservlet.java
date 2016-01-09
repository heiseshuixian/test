package com.java;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.DBHelp;

public class Videoservlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Videoservlet() {
		super();
	}


	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡ����������HTTPЭ���е�ʵ������
		ServletInputStream sis = request.getInputStream();
	    String name="";
	     File file = new File(request.getSession().getServletContext().getRealPath("/img/"),"img_"+0+".mp4");
	     for (int imgnum = 0;file.exists();imgnum++)
	     {
	      file  = new File(request.getSession().getServletContext().getRealPath("/img/"),"img_"+imgnum+".mp4");
	      name="img_"+(imgnum+1)+".mp4";
	     }
	     
//	  

	          //������
	          byte buffer[]=new byte[1024];
	          FileOutputStream fos=new FileOutputStream(file);
	          int len=sis.read(buffer, 0, 1024);
	          //���������Ϣѭ�����뵽�ļ���
	          while( len!=-1 )
	          {
	              fos.write(buffer, 0, len);
	              len=sis.readLine(buffer, 0, 1024);
	          }
	          fos.close();
	          sis.close();

	}

	public void init() throws ServletException {
		// Put your code here
	}

}

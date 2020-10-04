package com.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.entities.Note;
import com.helper.FactoryProvider;

public class SaveNoteServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try 
		{
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			Note note = new Note(title, content, new Date());
		
			Session session = FactoryProvider.getFactory().openSession();
			
			session.beginTransaction();
			session.save(note);
			session.getTransaction().commit();
			
			session.close();
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<h1 style='text-align:center;'>Note Added</h1>");
			out.print("<h1 style='text-align:center;'><a href='all_notes.jsp'>View All Notes</a></h1>");
			
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

}

package com.module.controller;


import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.module.papago.papago;




@WebServlet("/papago.do")
public class papagoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		
		if(command.equals("translation")) {
			String text = request.getParameter("msg");
			String source = request.getParameter("source");
			String target = request.getParameter("target");
			String result = papago.getTransSentence(text, source, target);

			response.getWriter().append(result);
		}

	}
	

}

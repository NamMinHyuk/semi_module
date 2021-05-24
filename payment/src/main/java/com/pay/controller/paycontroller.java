package com.pay.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/paycontroller")
public class paycontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		
		if (command.equals("pay")) {
			String pay_method = request.getParameter("pay_method");
			String product = request.getParameter("product");
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			
			request.setAttribute("pay_method", pay_method);
			request.setAttribute("product", product);
			request.setAttribute("name", name);
			request.setAttribute("price", price);
			
			dispatch(response, request, "payment.jsp");
		} else if (command.equals("paysuccess")) {
			jsResponse(response, "결제 성공", "index.jsp");
		} else if (command.equals("payfail")) {
			jsResponse(response, "결제 실패", "index.jsp");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void dispatch(HttpServletResponse response, HttpServletRequest request, String path)
			throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}
	
	private void jsResponse(HttpServletResponse response, String msg, String url) throws IOException {
		String responseText = "<script type='text/javascript'>" + "alert('" + msg + "');" + "location.href='" + url
				+ "';" + "</script>";
		response.getWriter().print(responseText);
	}

}

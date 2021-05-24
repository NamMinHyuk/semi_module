package com.page.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.page.biz.pageBiz;
import com.page.biz.pageBizImpl;
import com.page.dto.pageDto;

@WebServlet("/page.do")
public class pageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		pageBiz biz = new pageBizImpl();
		
		if(command.equals("list")) {
			int nowPage = 1;
			if (request.getParameter("nowPage") != null) {
				nowPage = Integer.parseInt(request.getParameter("nowPage"));
			}
			int count = biz.AllCount();
			
			pageDto dto = new pageDto(count, nowPage);
			List<pageDto> list = biz.selectList(dto);
			request.setAttribute("list", list);
			request.setAttribute("dto", dto);
			request.setAttribute("BoardCommand", command);
			dispatch(request, response, "list.jsp");
		}

	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
		request.getRequestDispatcher(path).forward(request, response);
	}

}

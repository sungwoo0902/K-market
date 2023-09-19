package kr.co.kmarket.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.kmarket.dto.MemberDTO;

@WebServlet(value= {"", "/index.do"})
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = 5783589879373746478L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");

		req.setAttribute("sessUser", sessUser);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
		dispatcher.forward(req, resp);
	}
}

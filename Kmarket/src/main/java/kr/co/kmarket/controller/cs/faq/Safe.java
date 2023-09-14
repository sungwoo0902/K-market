package kr.co.kmarket.controller.cs.faq;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/cs/faq/faq_list_safe.do")
public class Safe extends HttpServlet{

	private static final long serialVersionUID = 7394493901438517535L;

	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/faq/faq_list_safe_jsp");
		dispatcher.forward(req, resp);
	}
}

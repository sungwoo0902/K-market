package kr.co.kmarket.controller.cs.notice;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/cs/notice/notice_list_danger.do")
public class DangerService extends HttpServlet{

	private static final long serialVersionUID = 5831689631897970934L;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher distpatcher = req.getRequestDispatcher("/cs/notice/notice_list_danger.jsp");
		distpatcher.forward(req, resp);
	}
}

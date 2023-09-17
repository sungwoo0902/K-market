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

@WebServlet("/cs/notice/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = -479618598042292094L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");
		req.setAttribute("board", "list");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/notice/list.jsp");
		dispatcher.forward(req, resp);
	}
}
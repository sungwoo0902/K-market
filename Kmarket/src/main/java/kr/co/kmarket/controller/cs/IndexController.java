package kr.co.kmarket.controller.cs;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.BoardDTO;
import kr.co.kmarket.service.BoardService;

@WebServlet("/cs/index.do")
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = 7250034913434587988L;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private BoardService service = BoardService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					
			RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/index.jsp");
			dispatcher.forward(req, resp);	

	}
}

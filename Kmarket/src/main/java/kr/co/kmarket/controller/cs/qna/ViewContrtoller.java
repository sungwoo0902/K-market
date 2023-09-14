package kr.co.kmarket.controller.cs.qna;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.BoardDTO;
import kr.co.kmarket.service.BoardService;

@WebServlet("/cs/qna/qna_view.do")
public class ViewContrtoller extends HttpServlet{

	private static final long serialVersionUID = 64605479721014960L;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	private BoardService service = BoardService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		
		// 글 조회
		BoardDTO board = service.selectBoard(no);
		logger.debug(board.toString());
				
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/qna/qna_view.jsp");
	}
}

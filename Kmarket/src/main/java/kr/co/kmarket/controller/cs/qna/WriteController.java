package kr.co.kmarket.controller.cs.qna;

import java.io.IOException;
import java.security.Provider.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oreilly.servlet.MultipartRequest;

import kr.co.kmarket.dto.BoardDTO;
import kr.co.kmarket.service.BoardService;

@WebServlet("/cs/qna/qna_write.do")
public class WriteController extends HttpServlet{

	private static final long serialVersionUID = 7313779117121587822L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private BoardService service = BoardService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/qna/qna_write.jsp");		
		dispatcher.forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 폼 데이터 수신
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String uid = req.getParameter("uid");
		
		logger.debug("title : " + title);
		logger.debug("content : " + title);
		logger.debug("uid : " + uid);

		// DTO 생성
		BoardDTO dto = new BoardDTO();
		dto.setTitle(title);
		dto.setContent(content);
		dto.setUid(uid);
		
		// 글 Insert 
		int no = service.insertBoard(dto);
		
		// 리다이렉트
		resp.sendRedirect("/Kmarket/cs/qna/qna_list.do");
	}
	
}

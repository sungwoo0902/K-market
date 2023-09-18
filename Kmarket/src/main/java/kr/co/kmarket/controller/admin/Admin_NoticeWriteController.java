package kr.co.kmarket.controller.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.service.CsService;

@WebServlet("/admin/notice/write.do")
public class Admin_NoticeWriteController extends HttpServlet {
	
	private static final long serialVersionUID = 4487506858897269883L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String boardCate1 = req.getParameter("boardCate1");
		String boardCate2 = req.getParameter("boardCate2");
		String boardCate3 = req.getParameter("boardCate3");
		
		req.setAttribute("boardCate1", boardCate1);
		req.setAttribute("boardCate2", boardCate2);
		req.setAttribute("boardCate3", boardCate3);
		
		logger.info("doGet()...");
		req.setAttribute("board", "write");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/admin/notice/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		String boardCate1 = req.getParameter("boardCate1");
		String boardCate2 = req.getParameter("boardCate2");
		String boardCate3 = req.getParameter("boardCate3");
		String uid = req.getParameter("uid");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		CsDTO cdto = new CsDTO();
		cdto.setBoardCate1(boardCate1);
		cdto.setBoardCate2(boardCate2);
		cdto.setBoardCate3(boardCate3);
		cdto.setUid(uid);
		cdto.setTitle(title);
		cdto.setContent(content);
		
		service.insertBoard(cdto);
		
		resp.sendRedirect("/Kmarket/cs/notice/list.do");
		
	}
}
package kr.co.kmarket.controller.cs.faq;

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

import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.service.CsService;

@WebServlet("/cs/faq/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = 5258674197025893926L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");
		req.setAttribute("board", "list");
		
		String pg   = req.getParameter("pg");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		logger.debug("cate1");
		
		
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
		
		// 전체 게시물 갯수 
		int total = service.selectCountBoard(cate1, cate2);
		
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
		
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
		
		// 글 조회
		List<CsDTO> faq_list = service.selectBoards("2", null, null, start);
		logger.debug("faq_list :" +faq_list.toString());
		
		// cate1 이름, 설명 조회
		CsDTO faq_name_dis = service.selectBoard_list("2", cate1);
		logger.debug("faq_name_dis :" +faq_name_dis.toString());
		
		req.setAttribute("board", "list");
		
		String succcess = req.getParameter("success");
		req.setAttribute("succcess", succcess);
		req.setAttribute("faq_lists", faq_list);
		req.setAttribute("faq_name_dis", faq_name_dis);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/faq/list.jsp");
		dispatcher.forward(req, resp);
	}
}
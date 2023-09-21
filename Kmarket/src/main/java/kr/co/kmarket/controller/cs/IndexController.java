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

import org.apache.catalina.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.service.CsService;

@WebServlet(value ={"/cs", "/cs/index.do"})
public class IndexController extends HttpServlet{

	private static final long serialVersionUID = 7250034913434587988L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE; 

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");
		
		// 현재 세션 가져오기
		HttpSession session = req.getSession();
		MemberDTO sessMember = (MemberDTO) session.getAttribute("sessUser");
	
		// 데이터 수신
		String pg = req.getParameter("pg");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		String group = req.getParameter("group");
					
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
					
		// 전체 게시물 갯수 
		int total = service.selectCountBoard(group, cate1, cate2);
						
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
				
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
					
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
						
		// 글 조회
		List<CsDTO> article_notice_list = service.selectBoards("1", null, null, start);
		List<CsDTO> article_faq_list = service.selectBoards("2", null, null, start);
		List<CsDTO> article_qna_list = service.selectBoards("3", null, null, start);
		logger.debug("123 :" +article_notice_list.toString());
		req.setAttribute("board", "list");
		
		String succcess = req.getParameter("success");
		req.setAttribute("succcess", succcess);
		req.setAttribute("articles_notice_lists", article_notice_list);
		req.setAttribute("articles_faq_lists", article_faq_list);
		req.setAttribute("articles_qna_lists", article_qna_list);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/index.jsp");
		dispatcher.forward(req, resp);
	}
}

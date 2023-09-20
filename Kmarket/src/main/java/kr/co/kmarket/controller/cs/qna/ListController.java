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


import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.service.CsService;

@WebServlet("/cs/qna/list.do")
public class ListController extends HttpServlet {

	private static final long serialVersionUID = -4092848915441871453L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");

		// 데이터 수신
		String pg = req.getParameter("pg");
		String group = req.getParameter("group");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		
		logger.debug("cate1 : " + cate1);
		logger.debug("cate2 : " + cate2);
					
		// 현재 페이지 번호
		int currentPage = service.getCurrentPage(pg);
					
		// 전체 게시물 갯수 
		int total = service.selectCountBoard("3", cate1);
						
		// 마지막 페이지 번호
		int lastPageNum = service.getLastPageNum(total);
				
		// 페이지 그룹 start, end 번호
		int[] result = service.getPageGroupNum(currentPage, lastPageNum);
		
		// 페이지 시작번호
		int pageStartNum = service.getPageStartNum(total, currentPage);
		logger.debug("pageStartNum :" +pageStartNum);
					
		// 시작 인덱스
		int start = service.getStartNum(currentPage);
						
		// 글 조회
		List<CsDTO> article_qna_list = service.selectBoards("3", null, null, start);
		logger.debug("qna_list :" +article_qna_list.toString());
		
		// cate1 이름, 설명 조회
		CsDTO qna_name_dis = service.selectBoard_list("3", cate1);
		logger.debug("qna_list_name_dis :" +qna_name_dis.toString());
		
		String succcess = req.getParameter("success");
		req.setAttribute("succcess", succcess);
		req.setAttribute("board", "list");
		req.setAttribute("articles_qna_lists", article_qna_list);
		req.setAttribute("qna_name_dis", qna_name_dis);
		
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("lastPageNum", lastPageNum);
		req.setAttribute("pageGroupStart", result[0]);
		req.setAttribute("pageGroupEnd", result[1]);
		req.setAttribute("pageStartNum", pageStartNum+1);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/qna/list.jsp");
		dispatcher.forward(req, resp);
	}
}
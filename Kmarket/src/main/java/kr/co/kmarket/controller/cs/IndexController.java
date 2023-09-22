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
						
		// 글 조회
		List<CsDTO> notice_list = service.selectBoards("1", null, null, 0);
		List<CsDTO> faq_list = service.selectBoards("2", null, null, 0);
		List<CsDTO> qna_list = service.selectBoards("3", null, null, 0);
		logger.debug("notice_list : " + notice_list);
		String succcess = req.getParameter("success");
		req.setAttribute("succcess", succcess);
		req.setAttribute("notice_lists", notice_list);
		req.setAttribute("faq_lists", faq_list);
		req.setAttribute("qna_lists", qna_list);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/index.jsp");
		dispatcher.forward(req, resp);
	}
}

package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dao.TermsDAO;
import kr.co.kmarket.dto.TermsDTO;
import kr.co.kmarket.service.TermsService;

/**
 *	작업시작일 : 2023/09/13
 *	작업종료일 : 2023/09/15
 *	작업자 : 한상민
 *  내용 : type(normal, seller)에 따라 다른 약관이 출력되도록 설계.
 */

@WebServlet("/member/signup.do")
public class SignupController extends HttpServlet {

	private static final long serialVersionUID = -6142797205401709301L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private TermsService service = TermsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...1");
		
		String type = req.getParameter("type");
		logger.debug("type : " + type);
		req.setAttribute("type", type);
		
		TermsDTO dto = service.selectTerms();
		req.setAttribute("member_terms", dto);
		logger.debug("terms : " + dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/signup.jsp");
		dispatcher.forward(req, resp);
	}

}

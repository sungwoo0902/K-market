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

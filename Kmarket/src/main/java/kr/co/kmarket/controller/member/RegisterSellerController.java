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

import kr.co.kmarket.service.MemberService;

@WebServlet("/member/registerSeller.do")
public class RegisterSellerController extends HttpServlet {

	private static final long serialVersionUID = 514041069461979546L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService service = MemberService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...1");

		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/registerSeller.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost()...1");
		
		String uid       = req.getParameter("km_uid");
		String pass      = req.getParameter("km_pass");
		String company   = req.getParameter("kms_company");
		String ceo       = req.getParameter("kms_ceo");
		String bizRegNum = req.getParameter("kms_corp_reg");
		String comRegNum = req.getParameter("kms_online_reg");
		String Tel       = req.getParameter("kms_tel");
		String Fax       = req.getParameter("kms_fax");
		String email     = req.getParameter("kms_email");
		String zip       = req.getParameter("kms_zip");
		String addr1     = req.getParameter("kms_addr1");
		String addr2     = req.getParameter("kms_addr2");
		String manager   = req.getParameter("kms_name");
		String managerHp = req.getParameter("kms_hp");
	}

}

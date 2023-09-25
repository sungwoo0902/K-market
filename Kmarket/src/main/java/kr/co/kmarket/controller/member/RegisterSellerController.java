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

import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.service.MemberService;

/**
 *	작업시작일 : 2023/09/13
 *	작업종료일 : 2023/09/15
 *	작업자 : 한상민
 *  내용 : 유효성 검사 및 중복체크 기능 구현 및 우편번호API 적용.
 *   하이픈 자동 입력 js 기능 추가.
 */

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
		String pass      = req.getParameter("km_pass2");
		String company   = req.getParameter("kms_company");
		String ceo       = req.getParameter("kms_ceo");
		String bizRegNum = req.getParameter("kms_corp_reg");
		String comRegNum = req.getParameter("kms_online_reg");
		String tel       = req.getParameter("kms_tel");
		String fax       = req.getParameter("kms_fax");
		String email     = req.getParameter("km_email");
		String zip       = req.getParameter("km_zip");
		String addr1     = req.getParameter("km_addr1");
		String addr2     = req.getParameter("km_addr2");
		String manager   = req.getParameter("km_name");
		String managerHp = req.getParameter("km_hp");
		String regip     = req.getRemoteAddr();
		
		logger.debug("uid       : " + uid);
		logger.debug("pass      : " + pass);
		logger.debug("company   : " + company);
		logger.debug("ceo       : " + ceo);
		logger.debug("bizRegNum : " + bizRegNum);
		logger.debug("comRegNum : " + comRegNum);
		logger.debug("tel       : " + tel);
		logger.debug("fax       : " + fax);
		logger.debug("email     : " + email);
		logger.debug("zip       : " + zip);
		logger.debug("addr1     : " + addr1);
		logger.debug("addr2     : " + addr2);
		logger.debug("manager   : " + manager);
		logger.debug("managerHp : " + managerHp);
		logger.debug("regip     : " + regip);
		
		MemberDTO dto = new MemberDTO();
		dto.setUid(uid);
		dto.setPass(pass);
		dto.setCompany(company);
		dto.setCeo(ceo);
		dto.setBizRegNum(bizRegNum);
		dto.setComRegNum(comRegNum);
		dto.setTel(tel);
		dto.setFax(fax);
		dto.setEmail(email);
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setManager(manager);
		dto.setManagerHp(managerHp);
		dto.setRegip(regip);
		
		int result = service.insertSeller(dto);
		
		if(result > 0) {
			resp.sendRedirect("./login.do?success=200");
		}else {
			resp.sendRedirect("./login.do?success=100");
		}
	}
}

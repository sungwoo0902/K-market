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
 *   이전 signup.do 페이지에서 위치정보 이용약관을 동의하지 않았을 시, 
 *   DB `km_member`의 `location` 컬럼에 0으로 저장
 *   (동의한 경우엔 1로 저장 됨.)
 */

@WebServlet("/member/register.do")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 3675864518370345348L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService service = MemberService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...1");
		
		String location = req.getParameter("lc");
		logger.debug("location : " + location);
		req.setAttribute("location", location);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/register.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost()...1");
		
		String uid    = req.getParameter("km_uid");
		String pass   = req.getParameter("km_pass2");
		String name   = req.getParameter("km_name");
		String gender = req.getParameter("km_gender");
		String email  = req.getParameter("km_email");
		String hp     = req.getParameter("km_hp");
		String zip    = req.getParameter("km_zip");
		String addr1  = req.getParameter("km_addr1");
		String addr2  = req.getParameter("km_addr2");
		String location  = req.getParameter("location");
		String regip  = req.getRemoteAddr();
		
		logger.debug("uid    : " + uid);
		logger.debug("pass   : " + pass);
		logger.debug("name   : " + name);
		logger.debug("gender : " + gender);
		logger.debug("email  : " + email);
		logger.debug("hp     : " + hp);
		logger.debug("zip    : " + zip);
		logger.debug("addr1  : " + addr1);
		logger.debug("addr2  : " + addr2);
		logger.debug("regip  : " + regip);
		logger.debug("location  : " + location);
		
		MemberDTO dto = new MemberDTO();
		dto.setUid(uid);
		dto.setPass(pass);
		dto.setName(name);
		dto.setGender(gender);
		dto.setEmail(email);
		dto.setHp(hp);
		dto.setZip(zip);
		dto.setAddr1(addr1);
		dto.setAddr2(addr2);
		dto.setRegip(regip);
		dto.setLocation(location);
		
		int result = service.insertMember(dto);
		
		if(result > 0) {
			resp.sendRedirect("./login.do?success=200");
		}else {
			resp.sendRedirect("./login.do?success=100");
		}
	}
}
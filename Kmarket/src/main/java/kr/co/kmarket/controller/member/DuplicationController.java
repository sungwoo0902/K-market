package kr.co.kmarket.controller.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.service.MemberService;

/**
  *	작업시작일 : 2023/09/13
  *	작업종료일 : 2023/09/15
  *	작업자 : 한상민
  * 내용 : register.do 및 registerSeller.do 페이지에서 데이터 입력시 중복체크
  */

@WebServlet("/member/duplication.do")
public class DuplicationController extends HttpServlet {

	private static final long serialVersionUID = 2896063967254503555L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService service = MemberService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");
		
		int result = 0;
		String uid       = req.getParameter("uid");
		String hp        = req.getParameter("hp");
		String email     = req.getParameter("email");
		String bizRegNum = req.getParameter("bizRegNum");

		if(uid != null) {
			result = service.selectCheckUid(uid);
		}
		
		if(hp != null) {
			result = service.selectCheckHp(hp);
		}
		
		if(email != null) {
			result = service.selectCheckEmail(email);
		}
		
		if(bizRegNum != null) {
			result = service.selectCheckBizRegNum(bizRegNum);
		}
		
		logger.debug("result : " + result + "(테이블에 존재하는 데이터 개수)");
		
		// JSON 생성 및 출력
		JsonObject json = new JsonObject();
		json.addProperty("result", result);
		
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
	}
	
	
}

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

/**
 *	작업시작일 : 2023/09/13
 *	작업종료일 : 2023/09/13
 *	작업자 : 한상민
 *  내용 : 회원가입시, 일반회원/판매자회원 구분 페이지
 */

@WebServlet("/member/join.do")
public class JoinController extends HttpServlet {

	private static final long serialVersionUID = -9165570067205844583L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...1");
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/join.jsp");
		dispatcher.forward(req, resp);
	}

}

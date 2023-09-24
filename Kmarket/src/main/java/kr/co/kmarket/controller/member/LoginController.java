package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.service.MemberService;

@WebServlet("/member/login.do")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = -6384759540801999824L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private MemberService service = MemberService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...1");

		String success = req.getParameter("success");
		req.setAttribute("success", success);
		
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		
		if(cookies != null) {
			for(Cookie cookie : cookies) {
				
				if(cookie.getName().equals("sessUid")) {
					String sessUid = cookie.getValue();
					MemberDTO sessUser = service.selectMember(sessUid);
					
					if(sessUser != null) {
						logger.info("auto login : " + sessUser.getUid() + "(" + req.getRemoteAddr() + ")");
						session.invalidate();
						session = req.getSession(true);
						session.setAttribute("sessUser", sessUser);
						resp.sendRedirect(req.getContextPath());
						return;
					}
				}
			}
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/member/login.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost()...1");
		
		String uid  = req.getParameter("uid");
		String pass = req.getParameter("pass");
		String auto = req.getParameter("auto"); // 자동로그인 체크
		logger.debug("uid  : " + uid);
		logger.debug("pass : " + pass);
		logger.debug("auto : " + auto);
		
		MemberDTO member = service.selectMember(uid, pass);
		logger.debug("member : " + member);
		
		if(member != null) {
			logger.info("LOGIN SUCEES : " + member.getUid() + " (regip : " + req.getRemoteAddr() + ")");
			HttpSession session = req.getSession();
			session.setAttribute("sessUser", member);
			
			// 자동로그인 체크 시, 쿠키 추가.
			if(auto != null) {
				Cookie cookie = new Cookie("sessUid", member.getUid());
				cookie.setPath(req.getContextPath());
				cookie.setMaxAge(60*60*24*14); // 단위(초)
				resp.addCookie(cookie);
			}
			
			resp.sendRedirect("../");
		}else {
			logger.info("LOGIN FAILED : " + " (regip : " + req.getRemoteAddr() + ")");
			resp.sendRedirect("./login.do?success=101");
		}
	}
}
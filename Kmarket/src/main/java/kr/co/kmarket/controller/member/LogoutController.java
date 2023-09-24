package kr.co.kmarket.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.kmarket.dto.MemberDTO;

@WebServlet("/member/logout.do")
public class LogoutController extends HttpServlet {

	private static final long serialVersionUID = -5922397494151458422L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");
		
		HttpSession session = req.getSession();
		Cookie[] cookies = req.getCookies();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		
		if (cookies != null) {
			logger.debug("cookie is not null");
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals("sessUid")) {
					logger.debug("cookie setMaxAge 0...");
					cookie.setMaxAge(0);
					cookie.setPath(req.getContextPath());
					resp.addCookie(cookie);
					break;
				}
			}
		}
		
		logger.info("LOGOUT SUCCESS : " + sessUser + " (regip : " + req.getRemoteAddr() + ")");
		session.invalidate();

		resp.sendRedirect(req.getContextPath() + "?success=200");
	}
}
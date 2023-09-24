package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.service.CsService;

@WebServlet("/admin/qna/answer.do")
public class Admin_QnaAnswerController extends HttpServlet {
	
	private static final long serialVersionUID = 4487506858897269883L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String no = req.getParameter("no");
		String group = req.getParameter("group");
		String cate1 = req.getParameter("cate1");
		String cate2 = req.getParameter("cate2");
		String uid = req.getParameter("uid");
		String title = req.getParameter("title");
		String content    = req.getParameter("content");

		logger.debug("no    : " + no);
		logger.debug("content    : " + content);
		
		CsDTO dto = new CsDTO();
		dto.setGroup(group);
		dto.setParent(no);
		dto.setCate1(cate1);
		dto.setCate2(cate2);
		dto.setUid(uid);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setNo(no);
		logger.debug("dto : " + dto);
		
		// 작성한 글 DB 등록
		service.insertComment(dto);
		
		resp.sendRedirect("/Kmarket/admin/qna/list.do?group=3&pg=1");

	}
}
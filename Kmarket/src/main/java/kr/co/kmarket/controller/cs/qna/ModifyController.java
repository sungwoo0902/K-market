package kr.co.kmarket.controller.cs.qna;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import kr.co.kmarket.dto.CsDTO;
import kr.co.kmarket.dto.MemberDTO;
import kr.co.kmarket.service.CsService;

/**
 *	작업시작일 : 2023/09/24
 *	작업종료일 : 2023/09/24
 *	작업자 : 한상민
 *  내용 : cs 페이지 모듈화 및 view페이지 구현,
 *   - 문의게시글 수정 페이지 구현
 */

@WebServlet("/cs/qna/modify.do")
public class ModifyController extends HttpServlet {
	
	private static final long serialVersionUID = -1947808715089187709L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("doGet()...");
		
		// 게시글 내용 가져오기
		String no = req.getParameter("no");
		CsDTO qna = service.selectBoard(no);
		req.setAttribute("qna", qna);
		req.setAttribute("group", qna.getGroup());
		req.setAttribute("cate1", qna.getCate1());
		req.setAttribute("cate2", qna.getCate2());
		req.setAttribute("board", "write");
		req.setAttribute("no", no);
		
		// 본인 게시글인지 확인
		HttpSession session = req.getSession();
		MemberDTO sessUser = (MemberDTO) session.getAttribute("sessUser");
		String uid = sessUser.getUid();
		
		// 글 작성시 대분류 리스트 가져오기.
		List<CsDTO> cate1List = service.selectCate1ListWhenGroupChoose("3");
		req.setAttribute("cate1List", cate1List);
		
		// 소분류 리스트 가져오기
		List<CsDTO> cate2List = service.selectCate2ListWhenCate1Choose(qna.getCate1()+"");
		req.setAttribute("cate2List", cate2List);

		if(!uid.equals(qna.getUid())) {
			logger.info("sessUser ne uid : " + sessUser.getUid() + "(" + req.getRemoteAddr() + ")");
			resp.sendRedirect("./view.do?cate1="+qna.getCate1()+"&no="+no+"&success=102");
			
		}else {
			RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/qna/modify.jsp");
			dispatcher.forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.debug("doPost()...");
		
		String type = req.getParameter("type");
		logger.debug("type : " + type);
		
		switch(type) {
			case "json": 
				// 대분류 선택시 소분류 가져오기.
				String jsonCate2 = req.getParameter("jsonCate2");

				List<CsDTO> jsonCate3 = service.selectCate2ListWhenCate1Choose(jsonCate2);

				logger.debug("jsonCate2 : " + jsonCate2);
				logger.debug("jsonCate3 : " + jsonCate3.toString());
				
				Gson gson = new Gson();
				String json = gson.toJson(jsonCate3);
				
				JsonObject jsonObject = new JsonObject();
				jsonObject.add("categorys", gson.toJsonTree(jsonCate3));
				
				resp.setContentType("application/json; charset=UTF-8");
				
				resp.getWriter().write(jsonObject.toString());
				// --------------------------------------- json end...
				break;
				
			case "modify":
				String no         = req.getParameter("no");
				String cate1      = req.getParameter("boardCate2");
				String cate2      = req.getParameter("boardCate3");
				String title      = req.getParameter("title");
				String content    = req.getParameter("content");
				logger.debug("boardCate2 : " + cate1);
				logger.debug("boardCate3 : " + cate2);
				logger.debug("title      : " + title);
				logger.debug("content    : " + content);
				
				CsDTO dto = new CsDTO();
				dto.setNo(no);
				dto.setCate1(cate1);
				dto.setCate2(cate2);
				dto.setTitle(title);
				dto.setContent(content);
				logger.debug("dto : " + dto);
				
				// 작성한 글 DB 등록
				int result = service.updateBoard(dto);
				
				if(result > 0) {
					resp.sendRedirect("./view.do?cate1="+cate1+"&no="+no+"&success=201");
					
				}else {
					resp.sendRedirect("./list.do?cate1="+cate1+"&no="+no+"&success=101");
				}
		}
	}

}

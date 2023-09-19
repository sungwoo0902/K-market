package kr.co.kmarket.controller.cs.qna;

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

@WebServlet("/cs/qna/write.do")
public class WriteController extends HttpServlet {
	
	private static final long serialVersionUID = 4487506858897269883L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");
		
		String cate1 = req.getParameter("cate1");
		logger.debug("cate1 : " + cate1);
		
		req.setAttribute("cate1", cate1);
		req.setAttribute("board", "write");
		
		// 글 작성시 대분류 리스트 가져오기.
		List<CsDTO> cate2 = service.selectGroupListWhenGroupChoose("3");
		logger.debug("List_cate2 : " + cate2);
		req.setAttribute("cate2", cate2);
		
		// 소분류 리스트 가져오기
		List<CsDTO> cate3 = service.selectCate1ListWhenCate1Choose(cate1);
		logger.debug("List_cate3 : " + cate3);
		req.setAttribute("sub_cate", cate3);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/cs/qna/write.jsp");
		dispatcher.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doPost()...");
		
		String type = req.getParameter("type");
		logger.debug("type : " + type);
		
		switch(type) {
			case "json": 
				// 대분류 선택시 소분류 가져오기.
				String jsonCate2 = req.getParameter("jsonCate2");
				List<CsDTO> jsonCate3 = service.selectCate2ListWhenCate2Choose(jsonCate2);
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
				
			case "write":
				String boardCate2 = req.getParameter("boardCate2");
				String boardCate3 = req.getParameter("boardCate3");
				String uid        = req.getParameter("uid");
				String title      = req.getParameter("title");
				String content    = req.getParameter("content");
				logger.debug("boardCate2 : " + boardCate2);
				logger.debug("boardCate3 : " + boardCate3);
				logger.debug("uid        : " + uid);
				logger.debug("title      : " + title);
				logger.debug("content    : " + content);
				
				CsDTO dto = new CsDTO();
				dto.setGroup(3);
				dto.setCate1(boardCate2);
				dto.setCate2(boardCate3);
				dto.setUid(uid);
				dto.setTitle(title);
				dto.setContent(content);
				logger.debug("dto : " + dto);
				
				// 작성한 글 DB 등록
				int result = service.insertBoard(dto);
				
				if(result > 0) {
					resp.sendRedirect("./list.do?cate1="+boardCate2+"success=200");
					
				}else {
					resp.sendRedirect("./list.do?cate1="+boardCate2+"success=100");
				}
		}
	}
}
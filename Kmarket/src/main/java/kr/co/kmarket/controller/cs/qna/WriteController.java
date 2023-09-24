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

/**
 *	작업시작일 : 2023/09/18
 *	작업종료일 : 2023/09/18
 *	작업자 : 한상민
 *  내용 : cs 페이지 모듈화 및 view페이지 구현, write페이지 구현
 *   - 문의하기 글 작성 insert 구현
 *   - 카테고리 선택시 새로고침 없이, 카테고리 목록 불러오기.
 *   - 현재 들어와 있는 cate에 맞게 자동으로 카테고리 선택된 상태로 게시글 작성 시작.
 *   - 비회원일시 list 페이지에서 '문의하기' 버튼 출력x
 *   
 *   
 *   
 *   http://kor.pe.kr/util/4/charmap2.htm 참고하여 html 탈출문자 적용해보기.
 */

@WebServlet("/cs/qna/write.do")
public class WriteController extends HttpServlet {
	
	private static final long serialVersionUID = 4487506858897269883L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CsService service = CsService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doGet()...");
		
		String cate1 = req.getParameter("cate1");
		String no    = req.getParameter("no");
		logger.debug("cate1 : " + cate1);
		
		req.setAttribute("cate1", cate1);
		req.setAttribute("board", "write");
		
		// 글 작성시 대분류 리스트 가져오기.
		List<CsDTO> cate1List = service.selectCate1ListWhenGroupChoose("3");
		logger.debug("cate1List : " + cate1List.toString());
		req.setAttribute("cate1List", cate1List);
		
		// 소분류 리스트 가져오기
		List<CsDTO> cate2List = service.selectCate2ListWhenCate1Choose(cate1);
		logger.debug("cate2List : " + cate2List);
		req.setAttribute("cate2List", cate2List);
		
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
					resp.sendRedirect("./list.do?cate1="+boardCate2+"&success=200");
					
				}else {
					resp.sendRedirect("./list.do?cate1="+boardCate2+"&success=100");
				}
		}
	}
}
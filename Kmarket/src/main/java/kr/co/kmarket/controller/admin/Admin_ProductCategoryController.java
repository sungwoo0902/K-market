package kr.co.kmarket.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.service.CategoryService;
@WebServlet("/category.do")
public class Admin_ProductCategoryController extends HttpServlet{

	private static final long serialVersionUID = 43760110844428014L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CategoryService service = CategoryService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int result = 0;
		
		String category1 = req.getParameter("category1");
		
		System.out.println("cateController category1 : "+category1);
		
		List<CategoryDTO> cate2 = service.selectCate2s(category1);
		
		Gson gson = new Gson();
		String json = gson.toJson(cate2);
		
		// JSON 배열에 이름 지정
		JsonObject jsonObject = new JsonObject();
		jsonObject.add("cate2s", gson.toJsonTree(cate2));
		
		// 컨텐트 타입을 JSON으로 설정 + 한글 인코딩 설정해야 register에서 한글로 받음
		resp.setContentType("application/json; charset=UTF-8");
		
		// JSON 응답을 작성
		resp.getWriter().write(jsonObject.toString());
	}
}

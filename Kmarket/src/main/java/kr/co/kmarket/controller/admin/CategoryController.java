package kr.co.kmarket.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;

import kr.co.kmarket.dto.CategoryDTO;
import kr.co.kmarket.service.CategoryService;
@WebServlet("/category.do")
public class CategoryController extends HttpServlet{

	private static final long serialVersionUID = 43760110844428014L;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private CategoryService service = CategoryService.INSTANCE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String category1 = req.getParameter("category1");
		
		CategoryDTO cate1 = service.selectCate1(category1);
		
		
		// Json 출력
		JsonObject json = new JsonObject();
		//json.addProperty("", );
		resp.getWriter().print(json);
		
		
	}
}

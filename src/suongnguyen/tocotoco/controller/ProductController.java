package suongnguyen.tocotoco.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import suongnguyen.tocotoco.dao.CategoryDAO;
import suongnguyen.tocotoco.dao.ProductDAO;
import suongnguyen.tocotoco.model.Category;
import suongnguyen.tocotoco.model.Product;

public class ProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo == null) {
			viewAll(request, response);
		} else {
			viewDetail(request, response, pathInfo);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	
	public void viewAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDAO categoryDao = new CategoryDAO();
		ProductDAO productDao = new ProductDAO();
		request.setAttribute("categories", categoryDao.selectAll());
		request.setAttribute("productDao", productDao);
		request.getRequestDispatcher("/view/product.jsp").forward(request, response);
	}
	
	public void viewDetail(HttpServletRequest request, HttpServletResponse response, String pathInfo) throws ServletException, IOException {
		ProductDAO productDao = new ProductDAO();
		
		String[] path =pathInfo.split("/");
		int id = Integer.parseInt(path[path.length-1]);
		
		Product product = productDao.getById(id);
		request.setAttribute("product", product);
		request.getRequestDispatcher("/view/detail.jsp").forward(request, response);
	}
}

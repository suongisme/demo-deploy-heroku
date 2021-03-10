package suongnguyen.tocotoco.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import suongnguyen.tocotoco.dao.CategoryDAO;
import suongnguyen.tocotoco.dao.ProductDAO;
import suongnguyen.tocotoco.dao.ToppingDAO;
import suongnguyen.tocotoco.model.Item;
import suongnguyen.tocotoco.model.Product;

/**
 * Servlet implementation class OrderController
 */
@WebServlet("/order/*")
public class OrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		
		ProductDAO productDao = new ProductDAO();
		CategoryDAO categoryDao = new CategoryDAO();
		ToppingDAO toppingDao = new ToppingDAO();
		
		if (id == null) {
			request.setAttribute("product", null);
		} else {
			int productID = Integer.parseInt(id);
			
			request.setAttribute("product", productDao.getById(productID));
		}

		request.setAttribute("products", productDao.selectAll());
		request.setAttribute("categories", categoryDao.selectAll());
		request.setAttribute("toppings", toppingDao.selectAll());
		

		request.getRequestDispatcher("/view/order.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}

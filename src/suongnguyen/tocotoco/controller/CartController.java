package suongnguyen.tocotoco.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import suongnguyen.tocotoco.dao.ProductDAO;
import suongnguyen.tocotoco.dao.ToppingDAO;
import suongnguyen.tocotoco.model.Item;
import suongnguyen.tocotoco.model.Product;
import suongnguyen.tocotoco.model.Topping;


/**
 * Servlet implementation class AddToCart
 */
@WebServlet(urlPatterns = { "/cart/add", "/cart/clear", "/cart/subtract" })
public class CartController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	ProductDAO productDao;
	ToppingDAO toppingDao;
	
	@Override
	public void init() throws ServletException {
		productDao = new ProductDAO();
		toppingDao = new ToppingDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Origin", "*");
		String servletPath = request.getServletPath();

		HttpSession session = request.getSession();
		List<Item> itemList = (List<Item>) session.getAttribute("carts");
		if (itemList == null) {
			itemList = new ArrayList<Item>();
		}
		switch (servletPath) {
		case "/cart/clear":
			clearCart(request);
			break;
		case "/cart/subtract":
			subtractItem(request);
			break;
		case "/cart/add":
			addItem(request);
			break;
		default:
			break;
		}
		session.setAttribute("carts", itemList);
		response.sendRedirect(request.getContextPath()+"/order/");

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		int quantity = Integer.parseInt(req.getParameter("quantity"));
		Product product = productDao.getById(Integer.parseInt(req.getParameter("id")));
		
		Item item = new Item();
		item.setId(product.getId());
		item.setProduct(product);
		item.setPrice(product.getPrice());
		item.setQuantity(quantity);
		item.setIce(req.getParameter("ice"));
		item.setSugar(req.getParameter("sugar"));
		
		String[] toppingId = req.getParameterValues("topping");
		int length = toppingId == null ? 0 : toppingId.length;
		Topping[] toppingArray = new Topping[length];
		for (int i = 0; i < toppingArray.length; i++) {
			toppingArray[i] = toppingDao.getById(Integer.parseInt(toppingId[i]));
		}
		
		item.setTopping(toppingArray);
		List<Item> itemList = (List<Item>) session.getAttribute("carts");
		System.out.println(itemList);
		if (itemList == null) {
			itemList = new ArrayList<Item>();
		}
		itemList.add(item);
		session.setAttribute("carts", itemList);
		resp.sendRedirect(req.getContextPath()+"/order/");
		
	}
	
	public void addItem(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Item> itemList = (List<Item>) session.getAttribute("carts");
		int productID = Integer.parseInt(request.getParameter("id"));
		Product product = productDao.getById(productID);
		for (Item x : itemList) {
			if (x.getId() == product.getId()) {
				x.setQuantity(x.getQuantity()+1);
			}
		}
	}

	public void subtractItem(HttpServletRequest request) {
		HttpSession session = request.getSession();
		List<Item> itemList = (List<Item>) session.getAttribute("carts");
		int productID = Integer.parseInt(request.getParameter("id"));
		Product product = productDao.getById(productID);
		
		Iterator<Item> inter = itemList.iterator();
		while (inter.hasNext()) {
			Item x = inter.next();
			if (x.getId() == product.getId()) {
				x.setQuantity(x.getQuantity()-1);
				
				if (x.getQuantity() < 1) {
					inter.remove();
				}
			}
		}
	}

	public void clearCart(HttpServletRequest request)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Item> itemList = (List<Item>) session.getAttribute("carts");
		itemList.clear();
	}
}

package suongnguyen.tocotoco.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import suongnguyen.tocotoco.model.Product;
import suongnguyen.tocotoco.utilities.JDBCHelper;

public class ProductDAO {
	private final String INSERT = "INSERT INTO products(name, price, image, description, sale, categoryId) VALUES(?,?,?,?,?,?)";
	private final String SELECT_ALL = "SELECT * FROM products";
	private final String SELECT_BY_CATEGORYID = "SELECT * FROM products where categoryId = ?";
	private final String SELECT_BY_ID = "SELECT * FROM products where id = ?";
	
	public void insert(Product product) {
		try {
			JDBCHelper.update(INSERT, product.getName(), product.getPrice(), product.getImage(), product.getDescription(), product.getSale(),product.getCategoryID());
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public List<Product> selectByCategoryID(int cateId) {
		return action(SELECT_BY_CATEGORYID, cateId);
	}
	
	public Product getById(int id) {
		List<Product> productList = action(SELECT_BY_ID, id);
		return productList.isEmpty() ? null : productList.get(0);
	}
	
	public List<Product> selectAll() {
		return action(SELECT_ALL);
	}
	
	public List<Product> action(String sql, Object...arguments) {
		List<Product> listProduct = new ArrayList<Product>();
		try {
			ResultSet result = JDBCHelper.query(sql, arguments);
			while (result.next()) {
				Product product = new Product();
				product.setId(result.getInt("ID"));
				product.setName(result.getString("name"));
				product.setDescription(result.getString("description"));
				product.setPrice(result.getInt("price"));
				product.setImage(result.getString("image"));
				product.setCategoryID(result.getInt("categoryid"));
				product.setSale(result.getInt("sale"));
				listProduct.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBCHelper.closeConnection();
		return listProduct;
	}
}

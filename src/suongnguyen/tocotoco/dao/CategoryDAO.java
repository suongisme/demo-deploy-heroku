package suongnguyen.tocotoco.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import suongnguyen.tocotoco.model.Category;
import suongnguyen.tocotoco.utilities.JDBCHelper;

public class CategoryDAO {
	private final String SELECT_ALL = "SELECT * FROM Category";
	private final String SELECT_BY_ID = "SELECT * FROM Category where id = ?";
	
	public void insert() {
		
	}
	
	public Category getById(int id) {
		return action(SELECT_BY_ID, id).isEmpty() ? null : action(SELECT_BY_ID, id).get(0);
	}
	
	public List<Category> selectAll() {
		return action(SELECT_ALL);
	}
	
	public List<Category> action(String sql, Object...arguments) {
		List<Category> listProduct = new ArrayList<Category>();
		try {
			ResultSet result = JDBCHelper.query(sql, arguments);
			while (result.next()) {
				Category category = new Category();
				category.setId(result.getInt("ID"));
				category.setName(result.getString("name"));
				listProduct.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBCHelper.closeConnection();
		return listProduct;
	}
}

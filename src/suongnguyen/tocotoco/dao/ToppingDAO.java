package suongnguyen.tocotoco.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import suongnguyen.tocotoco.model.Topping;
import suongnguyen.tocotoco.utilities.JDBCHelper;

public class ToppingDAO {
	private final String SELECT_ALL = "SELECT * FROM Topping";
	private final String SELECT_BY_ID = "SELECT * FROM Topping where id = ?";
	public void insert() {
		
	}
	
	public Topping getById(int id) {
		List<Topping> productList = action(SELECT_BY_ID, id);
		return productList.isEmpty() ? null : productList.get(0);
	}
	
	public List<Topping> selectAll() {
		return action(SELECT_ALL);
	}
	
	public List<Topping> action(String sql, Object...arguments) {
		List<Topping> listProduct = new ArrayList<Topping>();
		try {
			ResultSet result = JDBCHelper.query(sql, arguments);
			while (result.next()) {
				Topping product = new Topping();
				product.setId(result.getInt("ID"));
				product.setName(result.getString("name"));
				product.setPrice(result.getInt("price"));
				listProduct.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		JDBCHelper.closeConnection();
		return listProduct;
	}
}

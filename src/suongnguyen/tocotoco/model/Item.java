package suongnguyen.tocotoco.model;

public class Item {
	private int id;
	private Product product;
	private String ice;
	private String sugar;
	private Topping[] topping;
	private int price;
	private int quantity;
	
	public Item() {}
	
	public Item(int id, Product product, int price, String ice, String sugar, Topping[] topping) {
		super();
		this.id = id;
		this.product = product;
		this.price = price;
		this.ice = ice;
		this.sugar = sugar;
		this.topping = topping;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getPrice() {
		int pr = this.price;
		for (Topping x : topping) {
			pr += x.getPrice();
		}
		return pr;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getIce() {
		return ice;
	}

	public void setIce(String ice) {
		this.ice = ice;
	}

	public String getSugar() {
		return sugar;
	}

	public void setSugar(String sugar) {
		this.sugar = sugar;
	}

	public Topping[] getTopping() {
		return topping;
	}

	public void setTopping(Topping[] topping) {
		this.topping = topping;
	}
}

package entities;

public class OrderItem {
	
	private Integer quantity;
	private Double price;
	
	private Product product;
	
	public OrderItem(Integer quantity, double price, Product product) {
		this.quantity = quantity;
		this.price = price;
		this.product = product;
	}

	public double subTotal() {
		return quantity*price;
	}

	@Override
	public String toString() {
		return product + " $" + price + ", Quantity: "+ quantity + ", subtotal: $" + subTotal();
	}
	

}

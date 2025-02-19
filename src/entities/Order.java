package entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import entitiesEnum.OrderStatus;


public class Order {
	//DEF. FORMATO DE DATA
	private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	private OrderStatus status;
	private LocalDateTime moment;	
	private Client client;
	
	//LISTA ORDEM ITEM
	private List <OrderItem> item = new ArrayList<>();
	
	//CONSTRUTOR PARA INICIALIZAR
	public Order(LocalDateTime moment, OrderStatus status, Client client) {
		this.moment = moment;
		this.status = status;
		this.client = client;
	}
	
	//ORDER ITEM
	public List<OrderItem> getItem() {
		return item;
	}
	public void addItem (OrderItem OrderItem) {
		item.add(OrderItem);	
	}
	public void removeItem (OrderItem OrderItem) {
		item.remove(OrderItem);
	}
	
	//STATUS
		public OrderStatus getStatus() {
			return status;
		}

		public void setStatus(OrderStatus status) {
			this.status = status;
		}

	//TOTAL DO PEDIDO
	public double total() {
		double sum=0.0;
		for(OrderItem i:item) {
			sum=sum+i.subTotal();
		}
		return sum;
	}
	//TOSTRING
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Order moment: ");
		sb.append(fmt.format(moment) + "\n");
		sb.append("Order status: ");
		sb.append(status + "\n");
		sb.append("Client: ");
		sb.append(client + "\n");
		sb.append("Order items:\n");
		for (OrderItem item : item) {
			sb.append(item + "\n");
		}
		sb.append("Total price: $");
		sb.append(String.format("%.2f", total()));
		return sb.toString();
	}
}

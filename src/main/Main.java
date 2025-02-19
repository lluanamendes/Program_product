package main;

import java.time.LocalDateTime;
import java.util.Scanner;

import entities.Client;
import entities.Order;
import entities.OrderItem;
import entities.Product;
import entitiesEnum.OrderStatus;

public class Main {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		//ENTRADA DO USUARIO COM AS INFORMAÇÕES DE CLIENTE
		System.out.println("Enter cliente data:");
		System.out.print("Name: ");
		String name=sc.nextLine();
		System.out.print("E-mail: ");
		String email=sc.nextLine();
		System.out.print("Birth date (DD/MM/YYYY): ");
		String birthDate=sc.nextLine();
		
		Client dataClient= new Client(name, email, birthDate); // INSTANCIANDO AS INFORMAÇÕES COM A CLASSE CLIENTE
		
		System.out.println("\nEnter order data:");
		System.out.print("Status: ");
		OrderStatus status = OrderStatus.valueOf(sc.next());//INSTANCIANDO O STATUS NA CLASSE ORDER STATUS
		
		LocalDateTime now=LocalDateTime.now();
		Order order = new Order(now, status, dataClient); // INSTANCIANDO DADOS DO CLIENTE, STATUS E DATA NO ORDER
		
		System.out.print("How many items to this order? ");
		int manyItems=sc.nextInt();
		sc.nextLine();
		
		for(int i=0; i<manyItems; i++) { //CRIAÇÃO DE LOOP PARA LANÇAR PEDIDOS
			System.out.println("\nEnter #"+(i+1)+"  item data:");
			System.out.println("Product name: ");
			String productName=sc.next();
			System.out.println("Product price: ");
			double productPrice=sc.nextDouble();
			Product product=new Product(productName, productPrice);//INSTANCIANDO NA CLASSE PRODUCT
			
			System.out.println("Quantity: ");
			int quantity=sc.nextInt();
			OrderItem orderItem = new OrderItem(quantity, productPrice, product); //INSTANCIANDO NO ORDER ITEM
			
			order.addItem(orderItem);//ADICIONAMOS NA LISTA
		}
		
		System.out.println();
		System.out.println("ORDER SUMMARY:");
		System.out.println(order);
		
		sc.close();	
	}

}

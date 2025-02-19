package ui;

import entities.*;
import entitiesEnum.OrderStatus;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderApp extends Application {

    private TextField nameField, emailField, birthDateField, productNameField, productPriceField, quantityField;
    private ComboBox<OrderStatus> statusComboBox;
    private TextArea orderSummary;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Order order;

    @Override
    public void start(Stage primaryStage) {
        // Campos para dados do cliente
        nameField = new TextField();
        nameField.setPromptText("Name");
        emailField = new TextField();
        emailField.setPromptText("E-mail");
        birthDateField = new TextField();
        birthDateField.setPromptText("Birth date (DD/MM/YYYY)");

        // Seleção do status do pedido
        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().setAll(OrderStatus.values());
        statusComboBox.setValue(OrderStatus.PENDING_PAYMENT);

        // Campos para adicionar produtos
        productNameField = new TextField();
        productNameField.setPromptText("Product Name");
        productPriceField = new TextField();
        productPriceField.setPromptText("Product Price");
        quantityField = new TextField();
        quantityField.setPromptText("Quantity");

        // Botões
        Button addItemButton = new Button("Add Item");
        addItemButton.setOnAction(e -> addItem());
        Button submitOrderButton = new Button("Submit Order");
        submitOrderButton.setOnAction(e -> submitOrder());

        // Área de saída para mostrar resumo do pedido
        orderSummary = new TextArea();
        orderSummary.setEditable(false);

        // Layout
        VBox layout = new VBox(10, nameField, emailField, birthDateField, statusComboBox,
                productNameField, productPriceField, quantityField, addItemButton, submitOrderButton, orderSummary);
        layout.setPadding(new Insets(15));

        // Configuração da cena
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Order Application");
        primaryStage.show();
    }

    private void addItem() {
        try {
            String productName = productNameField.getText();
            double productPrice = Double.parseDouble(productPriceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());
            
            if (productName.isEmpty() || quantity <= 0 || productPrice <= 0) {
                showAlert("Invalid input", "Please enter valid product details.");
                return;
            }
            
            Product product = new Product(productName, productPrice);
            OrderItem orderItem = new OrderItem(quantity, productPrice, product);
            orderItems.add(orderItem);
            
            orderSummary.appendText("Added: " + orderItem + "\n");
            
            // Limpar campos após adicionar
            productNameField.clear();
            productPriceField.clear();
            quantityField.clear();
        } catch (NumberFormatException e) {
            showAlert("Invalid input", "Please enter valid numeric values for price and quantity.");
        }
    }

    private void submitOrder() {
        if (orderItems.isEmpty()) {
            showAlert("Empty Order", "No items added to the order.");
            return;
        }
        
        String name = nameField.getText();
        String email = emailField.getText();
        String birthDate = birthDateField.getText();
        
        if (name.isEmpty() || email.isEmpty() || birthDate.isEmpty()) {
            showAlert("Missing Information", "Please fill in all client details.");
            return;
        }

        // Criar cliente e pedido
        Client client = new Client(name, email, birthDate);
        order = new Order(LocalDateTime.now(), statusComboBox.getValue(), client);
        
        for (OrderItem item : orderItems) {
            order.addItem(item);
        }

        // Mostrar resumo do pedido
        orderSummary.setText("ORDER SUMMARY:\n" + order);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}



package gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.collections.*;

import java.util.Map;

public class CheckoutScreen {

    private Map<String, Integer> cart;

    public CheckoutScreen(Map<String, Integer> cart) {
        this.cart = cart; // receive the cart from MenuScreen
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Checkout");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label title = new Label("Your Order");
        ListView<String> cartListView = new ListView<>();
        cartListView.setPrefHeight(200);

        double total = 0;
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String item = entry.getKey();
            int qty = entry.getValue();
            double price = getPrice(item); // set price per item
            total += price * qty;
            cartListView.getItems().add(item + " x" + qty + " = " + (price * qty) + " SAR");
        }

        Label totalLabel = new Label("Total: " + total + " SAR");

        Button payBtn = new Button("Pay Now");
        double finalTotal = total;
        payBtn.setOnAction(e -> {
            Alert receipt = new Alert(Alert.AlertType.INFORMATION);
            receipt.setTitle("Payment Successful");
            receipt.setHeaderText("Thank you!");
            receipt.setContentText("Your total was " + finalTotal + " SAR.\nYour order will be served shortly.");
            receipt.showAndWait();
            //stage.close();
        });

        Button receiptBtn = new Button("Receipt");
        receiptBtn.setOnAction(e -> {
            ReceiptScreen receiptScreen = new ReceiptScreen(cart, finalTotal);
            receiptScreen.show();
            stage.close();
        });

        layout.getChildren().addAll(title, cartListView, totalLabel, payBtn, receiptBtn);

        Scene scene = new Scene(layout, 500, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private double getPrice(String item) {
        switch (item) {
            case "Tea": return 3.0;
            case "Coffee": return 5.0;
            case "Knafah": return 7.0;
            case "Bsbosh": return 6.0;
            default: return 0;
        }
    }
}

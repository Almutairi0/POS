package gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.collections.*;
import java.util.*;

public class MenuScreen {

    private ObservableList<String> cartViewList = FXCollections.observableArrayList();
    private Map<String, Integer> cart = new HashMap<>(); // store name + quantity

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Menu");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label menuLabel = new Label("Select items to add to your order:");

        // Menu buttons
        Button tea = new Button("Tea");
        tea.setPrefWidth(120);
        tea.setPrefHeight(50);
        Button coffee = new Button("Coffee");
        Button knafah = new Button("Knafah");
        Button bsbosh = new Button("Bsbosh");

        // Cart view
        ListView<String> cartList = new ListView<>(cartViewList);
        cartList.setPrefHeight(150);

        // Button actions
        tea.setOnAction(e -> addToCart("Tea"));
        coffee.setOnAction(e -> addToCart("Coffee"));
        knafah.setOnAction(e -> addToCart("Knafah"));
        bsbosh.setOnAction(e -> addToCart("Bsbosh"));


        Button cartButton = new Button("View Cart");
        cartButton.setOnAction(e -> {
            CheckoutScreen checkout = new CheckoutScreen(cart);
            checkout.show();
        });

        Button checkoutBtn = new Button("Proceed to Checkout");
        checkoutBtn.setOnAction(e -> {
            if (cart.isEmpty()) {
                showAlert("Cart is empty", "Please add at least one item before checkout!");
            } else {
                CheckoutScreen checkout = new CheckoutScreen(cart);
                checkout.show();
            }
        });



        layout.getChildren().addAll(menuLabel, tea, coffee, knafah, bsbosh, new Label("Your Cart:"), cartList, checkoutBtn);

        Scene scene = new Scene(layout, 500, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    private void addToCart(String item) {
        // Increase quantity if exists
        cart.put(item, cart.getOrDefault(item, 0) + 1);
        updateCartView();
    }

    private void updateCartView() {
        cartViewList.clear();
        for (Map.Entry<String, Integer> entry : cart.entrySet()) {
            String display = entry.getKey() + " x" + entry.getValue();
            cartViewList.add(display);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

package gui;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.collections.*;
import javafx.scene.image.Image;

import java.util.*;

public class MenuScreen {

    private ObservableList<String> cartViewList = FXCollections.observableArrayList();
    private Map<String, Integer> cart = new HashMap<>(); // store name + quantity

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Menu");

        BorderPane root = new BorderPane();
        root.setPadding(new Insets(20));
        //layout.setAlignment(Pos.CENTER);

        Label menuLabel = new Label("Select items to add to your order:");

        // Menu buttons
        Button tea = new Button("Tea");
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

        //HBox leftBox = new HBox();
        //leftBox.setAlignment(Pos.CENTER_LEFT);
        Image teaImage = new Image("file:Images/10792446.png");
        ImageView leftImage = new ImageView(teaImage);
        //leftBox.setPadding(new Insets(150));
        leftImage.setFitWidth(150);
        //leftImage.setFitHeight(150);
        leftImage.setPreserveRatio(true);
        root.setLeft(leftImage);
        BorderPane.setAlignment(leftImage, Pos.CENTER_LEFT);
        //leftBox.getChildren().add(leftImage);


        //HBox RightBox = new HBox();
        //RightBox.setPadding(new Insets(150));
        //RightBox.setAlignment(Pos.CENTER_RIGHT);
        Image coffeeImage = new Image("file:Images/9612855.png");
        ImageView rightImage = new ImageView(coffeeImage);
        rightImage.setFitWidth(150);
        //rightImage.setFitHeight(150);
        rightImage.setPreserveRatio(true);
        root.setRight(rightImage);
        BorderPane.setAlignment(rightImage, Pos.CENTER_RIGHT);
        //RightBox.getChildren().add(rightImage);

        VBox centerBox = new VBox(10);
        BorderPane.setAlignment(centerBox, Pos.CENTER);
        centerBox.getChildren().addAll(menuLabel, tea, coffee, knafah, bsbosh, new Label("Your Cart:"), cartList, checkoutBtn);

        root.setCenter(centerBox);

        for (Node node : centerBox.getChildren()) {
            if (node instanceof Button button) {
                button.setMaxWidth(800);
                button.setMaxHeight(800);
            }
        }

        Scene scene = new Scene(root, 1280, 720);
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

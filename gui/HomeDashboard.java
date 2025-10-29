package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeDashboard extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Restaurant Management System - Dashboard");

        
        Button bookTableBtn = new Button("Book Table");
        Button menuBtn = new Button("Menu");
        //Button ordersBtn = new Button("Orders");
        Button inventoryBtn = new Button("Inventory");

        
        bookTableBtn.setOnAction(e -> {
            BookingScreen bookingScreen = new BookingScreen();
            try {
                bookingScreen.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        menuBtn.setOnAction(e -> {
            MenuScreen menuScreen = new MenuScreen();
            try {
                menuScreen.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
/*
        ordersBtn.setOnAction(e -> {
            OrderSummary orderSummary = new OrderSummary();
            try {
                orderSummary.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });*/

        inventoryBtn.setOnAction(e -> {
            InventoryScreen inventoryScreen = new InventoryScreen();
            try {
                inventoryScreen.show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

       VBox layout = new VBox(10);
        layout.getChildren().addAll(bookTableBtn, menuBtn, inventoryBtn);

        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

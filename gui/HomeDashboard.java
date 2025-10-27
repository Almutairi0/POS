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

        // أزرار الشاشات
        Button bookTableBtn = new Button("Book Table");
        Button menuBtn = new Button("Menu");
        Button ordersBtn = new Button("Orders");
        Button inventoryBtn = new Button("Inventory");

        // ربط الزر بشاشة الحجز
        bookTableBtn.setOnAction(e -> {
            BookingScreen bookingScreen = new BookingScreen();
            try {
                bookingScreen.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // ربط الزر بشاشة القائمة
       /* menuBtn.setOnAction(e -> {
            MenuScreen menuScreen = new MenuScreen();
            try {
                menuScreen.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // ربط الزر بشاشة الطلبات
        ordersBtn.setOnAction(e -> {
            OrderSummary orderSummary = new OrderSummary();
            try {
                orderSummary.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        // ربط الزر بشاشة المخزون
        inventoryBtn.setOnAction(e -> {
            InventoryScreen inventoryScreen = new InventoryScreen();
            try {
                inventoryScreen.start(new Stage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });*/

        // تنظيم الأزرار في VBox
        VBox layout = new VBox(10); // 10px مسافة بين الأزرار
        layout.getChildren().addAll(bookTableBtn, menuBtn, ordersBtn, inventoryBtn);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // لتشغيل هذا الكلاس مباشرة
    public static void main(String[] args) {
        launch(args);
    }
}

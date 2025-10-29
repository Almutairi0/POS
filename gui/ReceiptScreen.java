package gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import java.time.LocalDateTime;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ReceiptScreen {

    private Map<String, Integer> items;
    private double total;

    public ReceiptScreen(Map<String, Integer> items, double total) {
        this.items = items;
        this.total = total;
    }

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Receipt");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label header = new Label("Restaurant Receipt");
        Label date = new Label("Date: " + LocalDateTime.now());

        // Convert map to list
        ObservableList<String> itemsList = FXCollections.observableArrayList();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            itemsList.add(entry.getKey() + " x" + entry.getValue());
        }

        ListView<String> itemsView = new ListView<>(itemsList);
        Label totalLabel = new Label("Total: " + total + " SAR");

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> stage.close());

        layout.getChildren().addAll(header, date, itemsView, totalLabel, closeBtn);

        Scene scene = new Scene(layout, 1920, 1080);
        stage.setScene(scene);
        stage.show();
    }
}

package gui;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.collections.*;

public class InventoryScreen {

    private ObservableList<String> inventory = FXCollections.observableArrayList(
            "Tea Leaves - 50 units",
            "Coffee Beans - 30 units",
            "Knafah - 20 trays",
            "Bsbosh - 25 trays"
    );

    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Inventory Management");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        Label label = new Label("Current Inventory:");
        ListView<String> list = new ListView<>(inventory);

        TextField newItem = new TextField();
        newItem.setPromptText("Enter new item...");

        Button addBtn = new Button("Add Item");
        addBtn.setOnAction(e -> {
            if (!newItem.getText().isEmpty()) {
                inventory.add(newItem.getText());
                newItem.clear();
            }
        });

        Button removeBtn = new Button("Remove Selected");
        removeBtn.setOnAction(e -> {
            String selected = list.getSelectionModel().getSelectedItem();
            if (selected != null) inventory.remove(selected);
        });

        layout.getChildren().addAll(label, list, newItem, addBtn, removeBtn);

        Scene scene = new Scene(layout, 500, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
}

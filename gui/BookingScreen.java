package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.time.LocalDataTime;
import java.util.List;

public class BookingScreen {

    
    public void show() {
        Stage stage = new Stage();
        stage.setTitle("Book a Table");

        // Grid layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20));
        grid.setVgap(10);
        grid.setHgap(10);

        // Customer Name
        Label nameLabel = new Label("Customer Name:");
        TextField nameField = new TextField();
        grid.add(nameLabel, 0, 0);
        grid.add(nameField, 1, 0);

        // Number of Guests
        Label guestsLabel = new Label("Number of Guests:");
        Spinner<Integer> guestsSpinner = new Spinner<>(1, 6, 1);
        grid.add(guestsLabel, 0, 1);
        grid.add(guestsSpinner, 1, 1);

        // Table Selection
        Label tableLabel = new Label("Select Table ID:");
        ComboBox<Integer> tableCombo = new ComboBox<>();
        tableCombo.getItems().addAll(1, 2, 3, 4, 5); // placeholder
        grid.add(tableLabel, 0, 2);
        grid.add(tableCombo, 1, 2);

        // Load available tables from DB
        refreshAvailableTables(tableCombo);

        // Book Button
        Button bookBtn = new Button("Book Table");
        grid.add(bookBtn, 1, 3);

        // Message Label
        Label messageLabel = new Label();
        grid.add(messageLabel, 1, 4);

        // Button Action
        bookBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            int guests = guestsSpinner.getValue();
            Table selectedTable = tableCombo.getValue();

            if (name.isEmpty() || selectedTable == null) {
                messageLabel.setText("Please fill all fields and select a table.");
                return;
            }

            Booking booking = new Booking(name, selectedTable.getId(), LocalDataTime.now());
            boolean success = BookingDatabase.createBooking(Booking);
            if (success) {
              messageLabel.setText("Table" + selectedTable.getId() + "booked for " + name + "(" + guests + "guests).");
              nameField.clear();
              tableCombo.getItems().remove(selectedTable); // remove from available List
            } else {
              messageLabel.setText("Failed to create booking. Try again");

            }
        });
        // Scene
        Scene scene = new Scene(grid, 500, 400);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    private void refreshAvailableTables(ComboBox<Table> comboBox) {
        List<Table> allTables = Table.loadTables();
        ObservableList<Table> availableTables = FXCollections.observableArrayList();

        for (Table table : allTables) {
            if (table.isAvailable()) {
                availableTables.add(table);
            }
        }

        comboBox.setItems(availableTables);
    }
}


package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BookingScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Book a Table");

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
        // TODO: Populate table IDs from DB where status = 'available'
        tableCombo.getItems().addAll(1, 2, 3, 4, 5); // placeholder
        grid.add(tableLabel, 0, 2);
        grid.add(tableCombo, 1, 2);

        // Book Button
        Button bookBtn = new Button("Book Table");
        grid.add(bookBtn, 1, 3);

        // Message Label
        Label messageLabel = new Label();
        grid.add(messageLabel, 1, 4);

        // Button Action
        bookBtn.setOnAction(e -> {
            String name = nameField.getText();
            int guests = guestsSpinner.getValue();
            Integer tableId = tableCombo.getValue();

            if (name.isEmpty() || tableId == null) {
                messageLabel.setText("Please fill all fields and select a table.");
            } else {
                // TODO: Add JDBC logic to insert booking and update table status
                messageLabel.setText("Table " + tableId + " booked for " + name + " (" + guests + " guests).");
            }
        });

        // Scene
        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

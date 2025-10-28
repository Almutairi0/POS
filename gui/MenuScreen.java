package gui;

import javafx.application.*;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;

public class MenuScreen {

    
    public void show() {
    Stage stage = new Stage();
    stage.setTitle("Menu");

    HBox hbox = new HBox(10);
    hbox.setPadding(new Insets(20));
    Button tea = new Button("tea");
    tea.setPrefWidth(120);  
    tea.setPrefHeight(50);
    Button coffee = new Button("coffee");
    coffee.setPrefWidth(120);  
    coffee.setPrefHeight(50);
    Button knafah = new Button("knafah");
    knafah.setPrefWidth(120);  
    knafah.setPrefHeight(50);
    Button bsbosh = new Button("bsbosh");
    bsbosh.setPrefWidth(120);
    bsbosh.setPrefHeight(50);

    hbox.getChildren().addAll(tea, coffee, knafah, bsbosh);
    Scene scene = new Scene (hbox, 400, 300);
    stage.setScene(scene);
    stage.show();
    }
}


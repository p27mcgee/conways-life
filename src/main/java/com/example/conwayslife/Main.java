package com.example.conwayslife;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main screen area
        Pane mainScreen = new Pane();
        mainScreen.setStyle("-fx-background-color: lightgray; -fx-border-color: black;");
        mainScreen.setPrefSize(800, 600);

        // Control panel
        HBox controlPanel = new HBox(10);
        controlPanel.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0; -fx-border-color: black;");
        Button backButton = new Button("Back");
        Button forwardButton = new Button("Forward");
        controlPanel.getChildren().addAll(backButton, forwardButton);

        // Hamburger menu
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem helpItem = new MenuItem("Help");
        MenuItem aboutItem = new MenuItem("About");
        MenuItem exitItem = new MenuItem("Exit");
        menu.getItems().addAll(helpItem, aboutItem, exitItem);
        menuBar.getMenus().add(menu);

        // Exit functionality
        exitItem.setOnAction(event -> primaryStage.close());

        // Layout
        BorderPane layout = new BorderPane();
        layout.setTop(menuBar);
        layout.setCenter(mainScreen);
        layout.setBottom(controlPanel);

        // Scene and Stage
        Scene scene = new Scene(layout, 800, 650);
        primaryStage.setTitle("Conway's Life");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
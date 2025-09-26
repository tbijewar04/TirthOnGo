package com.tirthongo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Tirupati {

    public static Scene getScene(Stage primaryStage) {
        // Title
        Label title = new Label("Welcome to Tirupati");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-family: 'Segoe UI Semibold';");

        // Description
        Label description = new Label(
                "Tirupati is a major pilgrimage city in Andhra Pradesh, India.\n\n" +
                "It is home to the world-famous Tirumala Venkateswara Temple, visited by millions of devotees annually. " +
                "The city is surrounded by scenic hills and offers spiritual peace, culture, and history."
        );
        description.setWrapText(true);
        description.setMaxWidth(420);
        description.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-family: 'Segoe UI';");

        VBox descriptionBox = new VBox(20, title, description);
        descriptionBox.setAlignment(Pos.TOP_LEFT);
        descriptionBox.setPadding(new Insets(20));
        descriptionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-background-radius: 15px;");

        // Main image
        Image mainImage = new Image(Tirupati.class.getResource("/Assets/Images/tirupati.jpg").toExternalForm());
        ImageView mainImageView = new ImageView(mainImage);
        mainImageView.setFitWidth(480);
        mainImageView.setPreserveRatio(true);
        mainImageView.setStyle("-fx-effect: dropshadow(three-pass-box, white, 12, 0, 0, 0);");

        VBox imageBox = new VBox(mainImageView);
        imageBox.setAlignment(Pos.CENTER);
        imageBox.setPadding(new Insets(20));

        // HBox for main content
        HBox mainContent = new HBox(50, descriptionBox, imageBox);
        mainContent.setStyle("-fx-background-color: linear-gradient(to right, #0f2027, #203a43, #2c5364);");
        mainContent.setAlignment(Pos.CENTER);
        mainContent.setPadding(new Insets(30));

        // Back button
        Button backButton = new Button("← Back to Home");
        backButton.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-border-color: white;" +
                "-fx-border-radius: 20px;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 5 15 5 15;"
        );
        backButton.setOnAction(e -> primaryStage.setScene(new Homeload().gethomescene(primaryStage)));
        backButton.setAlignment(Pos.TOP_LEFT);

        // "Things to Do" label
        Label thingsToDoLabel = new Label("Things to Do in Tirupati");
        thingsToDoLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white; -fx-font-family: 'Segoe UI Semibold';");

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: white;");

        // Things to do image cards
        HBox thingsToDoImages = new HBox(20);
        thingsToDoImages.setAlignment(Pos.CENTER);
        thingsToDoImages.setPadding(new Insets(20));

        String[] imageFiles = { "tirumala.jpg", "talakondawaterfal.jpg", "horsleyhills.jpg" };
        String[] captions = {
            "Tirumala Temple",
            "TalakondaWaterfalls",
            "Horsley Hills"
        };
        String[] descriptions = {
            "One of the richest and most visited temples in the world, dedicated to Lord Venkateswara.",
            "A sacred waterfall near the Tirumala temple, believed to provide holy water for rituals.",
            "A rare natural rock formation and heritage site, symbolizing geological wonder."
        };

        for (int i = 0; i < imageFiles.length; i++) {
            VBox card = new VBox(10);
            card.setAlignment(Pos.TOP_CENTER);
            card.setStyle(
                    "-fx-background-color: rgba(255, 255, 255, 0.08); " +
                    "-fx-background-radius: 15px; " +
                    "-fx-padding: 12px;" +
                    "-fx-effect: dropshadow(gaussian, #222222, 10, 0, 0, 4);"
            );

            try {
                Image img = new Image(Tirupati.class.getResource("/Assets/Images/" + imageFiles[i]).toExternalForm());
                ImageView iv = new ImageView(img);
                iv.setFitWidth(250);
                iv.setFitHeight(160);
                iv.setPreserveRatio(true);
                iv.setStyle("-fx-background-radius: 10px;");

                iv.setOnMouseEntered(e -> {
                    iv.setScaleX(1.05);
                    iv.setScaleY(1.05);
                });
                iv.setOnMouseExited(e -> {
                    iv.setScaleX(1.0);
                    iv.setScaleY(1.0);
                });

                Label caption = new Label(captions[i]);
                caption.setStyle("-fx-text-fill: white; -fx-font-size: 15px; -fx-font-weight: bold; -fx-font-family: 'Segoe UI';");

                Label desc = new Label(descriptions[i]);
                desc.setWrapText(true);
                desc.setStyle("-fx-text-fill: white; -fx-font-size: 13px; -fx-font-family: 'Segoe UI Light';");
                desc.setMaxWidth(250);

                card.getChildren().addAll(iv, caption, desc);
            } catch (Exception e) {
                System.out.println("Image missing: " + imageFiles[i]);
            }

            thingsToDoImages.getChildren().add(card);
        }

        VBox content = new VBox(30, backButton, mainContent, thingsToDoLabel, separator, thingsToDoImages);
        content.setStyle("-fx-background-color: black;");
        content.setPadding(new Insets(20));
        content.setAlignment(Pos.TOP_CENTER);

        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: black;");

        return new Scene(scrollPane,1300, 700);
    }
}
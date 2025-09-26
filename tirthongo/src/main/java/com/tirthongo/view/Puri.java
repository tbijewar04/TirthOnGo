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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Puri {

     public static Scene getScene(Stage primaryStage) {
        // Title
        Label title = new Label("Welcome to Puri");
        title.setStyle("-fx-font-size: 28px; -fx-text-fill: white; -fx-font-family: 'Segoe UI Semibold';");
                                    
        // Description
        Label description = new Label(

            "It is home to the 12th-century Jagannath Temple and is one of the original Char Dham pilgrimage sites for Hindus. Puri has been known by several names since ancient times and was locally known as Sri Kshetra and the Jagannath temple is known as Badadeula.");
        description.setWrapText(true);
        description.setMaxWidth(420);
        description.setStyle("-fx-font-size: 16px; -fx-text-fill: white; -fx-font-family: 'Segoe UI';");

        VBox descriptionBox = new VBox(20, title, description);
        descriptionBox.setAlignment(Pos.TOP_LEFT);
        descriptionBox.setPadding(new Insets(20));
        descriptionBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1); -fx-background-radius: 15px;");

        // Main image
        Image mainImage = new Image(Puri.class.getResource("/Assets/Images/puri.jpg").toExternalForm());
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
        Label thingsToDoLabel = new Label("Things to visit in Puri");
        thingsToDoLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: white; -fx-font-family: 'Segoe UI Semibold';");

        Separator separator = new Separator();
        separator.setStyle("-fx-background-color: white;");

        // Things to do image cards
        HBox thingsToDoImages = new HBox(20);
        thingsToDoImages.setAlignment(Pos.CENTER);
        thingsToDoImages.setPadding(new Insets(20));

        String[] imageFiles = { "puribeach.jpg", "konark.jpg", "kaljijaim.jpg" };
        String[] captions = {
            "Puri Beach",
            "Konark Temple",
            " Kalijai Temple"
        };
        String[] descriptions = {
                "Puri Beach is called the Golden Beach due to its golden-colored sand, which shimmers and sparkles in the sunlight, creating a beautiful and memorable sight. This golden hue is especially prominent during sunrises and sunsets, enhancing the beach's natural beauty.",
                "It was one of the earliest centres of Sun worship in India. Built around 1250 in the reign of King Narasingha Deva (1238-64), it marks the apogee of the wave of foundations dedicated to the Sun God Surya; the entire temple was conceived as a chariot of the Sun God with a set of spokes and elaborate carvings.",
                "This temple is situated on an island where the presiding deity is Goddess Kalijai. The localites strongly believe in this Goddess and every fisherman in Chilika bows down to her before setting out for their fishing activities. Besides regular days, during Makar Sankranti time, the temple witnesses the maximum visitors."
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
                Image img = new Image(Puri.class.getResource("/Assets/Images/" + imageFiles[i]).toExternalForm());
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

        return new Scene(scrollPane, 1300, 700);
    }
}
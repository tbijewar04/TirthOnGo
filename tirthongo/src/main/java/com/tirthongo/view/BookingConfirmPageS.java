package com.tirthongo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BookingConfirmPageS  {

  
    public Scene start(Stage primaryStage) {

        // Root layout
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F9F9F9;");

        // Top bar with title and profile - FULL WIDTH
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(20));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-text-fill: white; -fx-padding: 10 20 10 20; -fx-font-weight: bold;");
        topBar.setPrefWidth(Region.USE_COMPUTED_SIZE);
        topBar.setMaxWidth(Double.MAX_VALUE); // Ensure full width

        Label title = new Label("🛐TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#ec7208ff")); // Saffron

        topBar.getChildren().add(title);
        root.setTop(topBar);

        // MAIN CONTENT CONTAINER - CENTERED
        VBox mainContentContainer = new VBox(20);
        mainContentContainer.setAlignment(Pos.CENTER);
        mainContentContainer.setPadding(new Insets(30, 20, 30, 20));
        mainContentContainer.setMaxWidth(900); // Limit max width for better readability
        mainContentContainer.setStyle("-fx-background-color: linear-gradient(to left, #ffffffff, #f8c47bff)");

        // Confirmation Messages - CENTERED
        Label confirmMsg = new Label("✅ Your booking has been successfully confirmed!");
        confirmMsg.setStyle("-fx-font-size: 18px; -fx-text-fill: #2C3E50;");
        Label wishMsg = new Label("🙏 Hope you have a safe and healthy journey!");
        wishMsg.setStyle("-fx-font-size: 16px; -fx-text-fill: #2C3E50;");

        VBox confirmationBox = new VBox(10, confirmMsg, wishMsg);
        confirmationBox.setAlignment(Pos.CENTER);
        confirmationBox.setMaxWidth(600);
        confirmationBox.setPadding(new Insets(20));
        confirmationBox.setStyle("-fx-background-color: #FDEBD0; -fx-border-color: #E67E22; -fx-border-radius: 10; -fx-background-radius: 10;");

        // Left Section - CENTERED
         VBox leftSection = new VBox(20);
        leftSection.setPrefWidth(850);

        Label fromLabel = new Label("⚫ Chennai to Vellore");
        fromLabel.setFont(Font.font("Arial", 14));
        fromLabel.setTextFill(Color.web("#555"));

        VBox itineraryCard = new VBox(0);
        itineraryCard.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);");
        itineraryCard.setMaxWidth(800);

        // Header
        Label velloreLabel = new Label("Vellore - 2 Nights Stay");
        velloreLabel.setStyle("-fx-background-color: #f3d9c4; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 15;");

        VBox daysBox = new VBox();
        daysBox.setPadding(new Insets(10));

        // DAY 1
        daysBox.getChildren().add(createDayRow("Day 1", "Sep 3, Wed", "🏨", "Check in to Rangalaya royal, 3 Star"));

        // DAY 2
        daysBox.getChildren().add(createDayRow("Day 2", "Sep 4, Thu", "🚗", "Private AC Sedan - AC for sightseeing in & around Vellore"));
        daysBox.getChildren().add(createDayRow("", "", "🌄", "Sightseeing In Vellore"));
        daysBox.getChildren().add(createDayRow("", "", "🍝", "Day Meals: Breakfast : Included at Rangalaya royal, Vellore"));

        // DAY 3
        daysBox.getChildren().add(createDayRow("Day 3", "Sep 5, Fri", "🍝", "Day Meals: Breakfast : Included at Rangalaya royal, Vellore"));
        daysBox.getChildren().add(createDayRow("", "", "🏨", "Checkout from Hotel in Vellore"));

        itineraryCard.getChildren().addAll(velloreLabel, daysBox);

        Label toLabel = new Label("⚫ Vellore to Chennai");
        toLabel.setFont(Font.font("Arial", 14));
        toLabel.setTextFill(Color.web("#555"));

        leftSection.getChildren().addAll(fromLabel, itineraryCard, toLabel);

        // Info Section - CENTERED
        String infoText = """
                👤 Tour planner: Mr. Rajesh Sharma
                📞 Contact: +91-9876543210
                🚨 Emergency: +91-112
                👮 Local Police: +91-100
                """;

        Label infoLabel = new Label(infoText);
        infoLabel.setWrapText(true);
        infoLabel.setStyle("-fx-padding: 15; -fx-font-size: 13px;");

        ScrollPane infoScroll = new ScrollPane(infoLabel);
        infoScroll.setFitToWidth(true);
        infoScroll.setPrefHeight(180);
        infoScroll.setStyle("-fx-background-color: transparent;");

        TitledPane infoPane = new TitledPane("📘Tour Planner", infoScroll);
        infoPane.setExpanded(false);
        infoPane.setMaxWidth(800);
        infoPane.setStyle("""
                -fx-font-size: 13px;
                -fx-background-radius: 8;
                -fx-text-fill: #34495E;
        """);

        VBox itineraryBox = new VBox(20, leftSection, infoPane);
        itineraryBox.setAlignment(Pos.CENTER);
        itineraryBox.setPadding(new Insets(20));

        // Buttons
        Button downloadBtn = new Button("🎫 Download Ticket");
        downloadBtn.setPrefWidth(280); // Use preferred width instead of max width
        downloadBtn.setMinHeight(50);
        downloadBtn.setPrefHeight(50);
        downloadBtn.setMaxHeight(50);
        downloadBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: black; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");

        Button whatsappBtn = new Button("💬 WhatsApp Group");
        whatsappBtn.setPrefWidth(280); // Use preferred width instead of max width
        whatsappBtn.setMinHeight(50);
        whatsappBtn.setPrefHeight(50);
        whatsappBtn.setMaxHeight(50);
        whatsappBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: black; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");

        Button homeBtn = new Button("🏠 Home Page");
        homeBtn.setPrefWidth(280); // Use preferred width instead of max width
        homeBtn.setMinHeight(50);
        homeBtn.setPrefHeight(50);
        homeBtn.setMaxHeight(50);
        homeBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: black; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");

        homeBtn.setOnAction(e -> {

        primaryStage.setScene(new Homeload().gethomescene(primaryStage));

        });


        VBox buttonBox = new VBox(15, downloadBtn, whatsappBtn, homeBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20));

        // Add all content to main container
        mainContentContainer.getChildren().addAll(confirmationBox, itineraryBox, buttonBox);

        // Wrap main content in a container for centering
        HBox centerWrapper = new HBox();
        centerWrapper.setAlignment(Pos.CENTER);
        centerWrapper.getChildren().add(mainContentContainer);

        // ScrollPane for the centered content
        ScrollPane scrollPane = new ScrollPane(centerWrapper);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        root.setCenter(scrollPane);

        Scene scene = new Scene(root,1300, 620);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        return scene;
    }

    private HBox createDayRow(String day, String date, String icon, String desc) {
        // Day and date column
        VBox dayColumn = new VBox(2);
        if (!day.isEmpty()) {
            Label dayLabel = new Label(day);
            dayLabel.setFont(Font.font("Arial", FontWeight.BOLD, 13));
            dayLabel.setTextFill(Color.web("#2C3E50"));
            dayColumn.getChildren().add(dayLabel);
        }
        if (!date.isEmpty()) {
            Label dateLabel = new Label(date);
            dateLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
            dateLabel.setTextFill(Color.web("#7F8C8D"));
            dayColumn.getChildren().add(dateLabel);
        }
        dayColumn.setPrefWidth(120);
        dayColumn.setMinWidth(120);

        // Icon column
        Label iconLabel = new Label(icon);
        iconLabel.setPrefWidth(40);
        iconLabel.setMinWidth(40);
        iconLabel.setAlignment(Pos.CENTER);
        iconLabel.setFont(Font.font(16));

        // Description column
        Label descLabel = new Label(desc);
        descLabel.setWrapText(true);
        descLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
        descLabel.setTextFill(Color.web("#2C3E50"));
        HBox.setHgrow(descLabel, Priority.ALWAYS);

        HBox row = new HBox(15, dayColumn, iconLabel, descLabel);
        row.setAlignment(Pos.CENTER_LEFT);
        row.setPadding(new Insets(12, 20, 12, 20));
        row.setStyle("-fx-border-color: #E8E8E8; -fx-border-width: 0 0 1 0;");
        
        return row;
    }
}


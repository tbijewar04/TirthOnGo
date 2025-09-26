package com.tirthongo.view;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class MyTrips {

    Label emptyMsg;
    Label hintMsg;

    public Scene mytripScene (Stage primaryStage) {
        // Top bar with title and profile
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-text-fill: white; -fx-padding: 10 20 10 20; -fx-font-weight: bold;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("ॐ TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#ec7208ff")); // Saffron

        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, Priority.ALWAYS);

        Circle profileCircle = new Circle(15);
        Image profileImg = new Image("Assets\\Images\\profileicon.jpg"); // Update path
        profileCircle.setFill(new ImagePattern(profileImg));

        MenuItem myProfile = new MenuItem("My Profile");
        MenuItem myTrips = new MenuItem("My Trips");
        MenuButton profileMenu = new MenuButton("", null, myProfile, myTrips);
        profileMenu.setGraphic(profileCircle);

        topBar.getChildren().addAll(title, spacerTop, profileMenu);

        // Breadcrumb + search
        HBox header = new HBox();
        header.setPadding(new Insets(10, 20, 10, 20));
        header.setStyle("-fx-background-color: #eaf2f8;");
        header.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ec7208; -fx-font-size: 16px; -fx-font-weight: bold;");
       backButton.setOnAction(e -> {
    try {
        // --- NEW: Directly navigate to the Homeload (User Dashboard) page ---
        Homeload homePage = new Homeload(); // Instantiate Homeload          

        // Get the home scene and set it on the primary stage
        Scene homeScene = homePage.gethomescene(primaryStage); // Assuming getHomeScene method exists in Homeload
        primaryStage.setScene(homeScene); 
        primaryStage.setTitle("TirthOnGo - Home"); // Update window title

    } catch (Exception ex) {
        System.err.println("Error navigating back to Home Page: " + ex.getMessage());
        ex.printStackTrace();
        // You might want a more user-friendly alert here
        showAlert("Navigation Error", "Failed to go back to the Home page. Please try again.", Alert.AlertType.ERROR);
    }
});

        

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        TextField searchBox = new TextField();
        searchBox.setPromptText("Search for a booking");
        searchBox.setPrefWidth(300); // wider
        searchBox.setPrefHeight(40); // taller

        Button searchBtn = new Button("🔍");
        searchBtn.setMinHeight(40);
        searchBtn.setMaxWidth(40);
        searchBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white;");

        HBox searchContainer = new HBox(searchBox, searchBtn);
        searchContainer.setSpacing(5);
        searchContainer.setAlignment(Pos.CENTER_RIGHT);

        header.getChildren().addAll(backButton, spacer, searchContainer);

        // Tab bar
        HBox tabBar = new HBox();
        tabBar.setPadding(new Insets(15, 20, 15, 50));
        tabBar.setSpacing(80);
        tabBar.setStyle("-fx-background-color: #ffffffff; -fx-border-color: #ddd;");
        tabBar.setAlignment(Pos.CENTER_LEFT);

        ToggleGroup tabGroup = new ToggleGroup();

        ToggleButton upcoming = createTab("📅 UPCOMING", tabGroup, true);
        ToggleButton cancelled = createTab("❌ CANCELLED", tabGroup, false);
        ToggleButton completed = createTab("✅ COMPLETED", tabGroup, false);

        tabBar.getChildren().addAll(upcoming, cancelled, completed);

        // Empty state
        VBox emptyState = new VBox(10);
        emptyState.setPadding(new Insets(100));
        emptyState.setAlignment(Pos.CENTER);
        emptyState.setStyle("-fx-background-color: #ffffff; -fx-border-color: #eee; -fx-border-radius: 10;");

        emptyMsg = new Label("Looks empty, you've no upcoming bookings.");
        emptyMsg.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        emptyMsg.setTextFill(Color.web("#333"));

        hintMsg = new Label("When you book a trip, you will see your itinerary here.");
        hintMsg.setTextFill(Color.web("#666"));

        Button planTrip = new Button("PLAN A TRIP");
        planTrip.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold;");
        planTrip.setFont(Font.font("Arial", 14));

        emptyState.getChildren().addAll( emptyMsg, hintMsg, planTrip);

        // Load background image
        ImageView backgroundImage = new ImageView(new Image("Assets\\Images\\image1.jpg"));
        backgroundImage.setPreserveRatio(false); // Allow full stretch
        backgroundImage.setOpacity(100); // Fully visible

        // Make image auto-resize with scene
        backgroundImage.fitWidthProperty().bind(primaryStage.widthProperty());
        backgroundImage.fitHeightProperty().bind(primaryStage.heightProperty());

        // Main content
        VBox root = new VBox(topBar, header, tabBar, emptyState);

        // Layer background and content
        StackPane layeredPane = new StackPane(backgroundImage, root);

        // Set scene
        Scene scene = new Scene(layeredPane,1300, 700);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        
        primaryStage.show();
        return scene;
    }

    private ToggleButton createTab(String text, ToggleGroup group, boolean selected) {
    ToggleButton tab = new ToggleButton(text);
    tab.setToggleGroup(group);
    tab.setSelected(selected);
    tab.setFont(Font.font("Arial", FontWeight.BOLD, 16)); // Bigger font
    tab.setStyle("-fx-background-color: transparent; -fx-text-fill: #444; -fx-padding: 10 20 10 20;"); // Bigger buttons
    tab.setOnAction(e -> {
        if (tab.isSelected()) {
            tab.setStyle("-fx-border-color: #4facfe; -fx-border-width: 0 0 3 0; -fx-text-fill: #000000ff; -fx-padding: 10 20 10 20;");
        }
    });
    return tab;

    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null); // No header text
    alert.setContentText(message);
    alert.showAndWait(); // Show the alert and wait for user to close it
}

}


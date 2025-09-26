package com.tirthongo.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ContactUsPage { 
    
    public Scene getcontactus(Stage primaryStage) {
        // Main container
        HBox mainContainer = new HBox();

        mainContainer.setPrefSize(1300, 700);
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");
        
        // Left side - Image with passport
        VBox leftSide = createLeftSide();
        leftSide.setPrefWidth(650);
        
        // Right side - Contact information
        VBox rightSide = createRightSide();
        rightSide.setPrefWidth(650);
        rightSide.setPadding(new Insets(60, 60, 60, 40));

        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-color: #34495e; -fx-border-width: 0 0 0 0; -fx-cursor: hand;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #ec7208ff; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-color: #ec7208ff; -fx-border-width: 0 0 0 0; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-color: #34495e; -fx-border-width: 0 0 0 0; -fx-cursor: hand;"));
        backButton.setOnAction(e -> primaryStage.setScene(new Homeload().gethomescene(primaryStage)));
        
        mainContainer.getChildren().addAll(leftSide, rightSide);

        VBox top = new VBox(10);
        top.getChildren().addAll(backButton,mainContainer);
        top.setPrefSize(1300, 700);
        
        Scene scene = new Scene(top,1300, 700);
        primaryStage.setMaximized(true);

        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }
    
    // FIXED createLeftSide() method
    private VBox createLeftSide() {
        VBox leftSide = new VBox();
        
        leftSide.setAlignment(Pos.CENTER);
        
        // Background with rounded corners
        leftSide.setStyle("-fx-background-color: linear-gradient(to left, #ffffffff, #f9970dff)");
        
        // Create a stack pane for layering
        StackPane imageStack = new StackPane();
        //imageStack.setPrefSize(600, 400); // Set proper size
        
        // Add the contact form to the stack pane
        VBox contactForm = createContactForm();
        StackPane.setAlignment(contactForm, Pos.CENTER);
        
        // FIXED: Only add the contact form, not leftSide itself
        imageStack.getChildren().add(contactForm);
        
        // Add the stack pane to leftSide
        leftSide.getChildren().add(imageStack);
        
        return leftSide;
    }
    
    private VBox createRightSide() {
        VBox rightSide = new VBox(30);
        rightSide.setAlignment(Pos.TOP_LEFT);
        rightSide.setStyle("-fx-background-color: white;" +
                          "-fx-background-radius: 0 20 20 0;");
        
        // Header section
        VBox headerSection = new VBox(10);
        
        Label contactLabel = new Label("🛐TirthOnGo");
        contactLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
        contactLabel.setTextFill(Color.web("#ec7208ff")); // Saffron
        contactLabel.setStyle("-fx-letter-spacing: 2px;");
        
        Label titleLabel = new Label("Our Contact");
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 41));
        titleLabel.setTextFill(Color.web("#000000ff"));

        Label descriptionLabel = new Label("Please feel free to contact us on the registered\ninformation board for more information about\nour company");
        descriptionLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        descriptionLabel.setTextFill(Color.web("#888888"));
        descriptionLabel.setLineSpacing(5);
        
        headerSection.getChildren().addAll(contactLabel, titleLabel, descriptionLabel);
        
        // Contact information grid
        HBox contactGrid = new HBox(60);
        contactGrid.setAlignment(Pos.TOP_LEFT);
        
        // Office Hours
        VBox officeHours = createContactSection("OFFICE HOURS", 
            "Monday - Saturday\n08:00 - 17:30");
        
        // Get in Touch
        VBox getInTouch = createContactSection("GET IN TOUCH", 
            "(+62) 8233 1602 770\n(+62) 8212 7431 5462");
        
        contactGrid.getChildren().addAll(officeHours, getInTouch);
        
        // Second row
        HBox contactGrid2 = new HBox(30);
        contactGrid2.setAlignment(Pos.TOP_LEFT);
        
        // Follow Us
        VBox followUs = createContactSection("FOLLOW US", 
            "www.tirthongo.com\noffice@mytour.com\n@tirthongoTours");
        
        // Our Address
        VBox ourAddress = createContactSection("OUR ADDRESS", 
            "1234 tirthongo Tours\nlocated in Navi Mumbai \nMaharastra");
        
        contactGrid2.getChildren().addAll(followUs, ourAddress);
        
        rightSide.getChildren().addAll(headerSection, contactGrid, contactGrid2);
        
        return rightSide;
    }
    
    private VBox createContactSection(String title, String content) {
        VBox section = new VBox(15);
        
        Label titleLabel = new Label(title);
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        titleLabel.setTextFill(Color.web("#000000ff"));
        titleLabel.setStyle("-fx-letter-spacing: 1px;");
        
        Label contentLabel = new Label(content);
        contentLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        contentLabel.setTextFill(Color.web("#888888"));
        contentLabel.setLineSpacing(8);
        
        section.getChildren().addAll(titleLabel, contentLabel);
        
        return section;
    }

    private VBox createContactForm() {
        VBox contactForm = new VBox(15);
        contactForm.setAlignment(Pos.CENTER);
        contactForm.setPadding(new Insets(30, 25, 30, 25));
        contactForm.setPrefWidth(350);
        contactForm.setMaxWidth(350);
        
        // Form background with rounded corners and shadow effect
        contactForm.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 10;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 5);" +
            "-fx-border-color: #e0e0e0;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 10;"
        );
        
        // Form header
        Label formTitle = new Label("Contact Us");
        formTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        formTitle.setTextFill(Color.web("#2F2F2F"));
        
        Label formSubtitle = new Label("Send us your message");
        formSubtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        formSubtitle.setTextFill(Color.web("#888888"));
        
        // Input fields
        TextField nameField = createStyledTextField("Your Name");
        TextField phoneField = createStyledTextField("Phone Number");
        TextField emailField = createStyledTextField("Email Address");
        
        // Subject/Query text area
        TextArea queryArea = new TextArea();
        queryArea.setPromptText("Your Message or Query");
        queryArea.setPrefRowCount(4);
        queryArea.setWrapText(true);
        queryArea.setStyle(
             "-fx-border-width: 0.5;" +
            "-fx-background-color: #ffffffff;" +
            "-fx-border-color:  #f9970dff;" +
            "-fx-border-radius: 5;" +
            "-fx-font-size: 14;" +
            "-fx-prompt-text-fill: #999999;" +
            "-fx-text-fill: #333333;"
        );
        
        // Send button
        Button sendButton = new Button("Send Message");
        sendButton.setPrefWidth(200);
        sendButton.setPrefHeight(40);
        sendButton.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        sendButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;-fx-cursor: hand;" 
        );
    
        
        // Button click handler
        sendButton.setOnAction(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String query = queryArea.getText();
            
            if (name.isEmpty() || email.isEmpty() || query.isEmpty()) {
                showAlert("Please fill in all required fields!");
            } else {
                handleContactSubmission(name, phone, email, query);
                // Clear form after submission
                nameField.clear();
                phoneField.clear();
                emailField.clear();
                queryArea.clear();
            }
        });
        
        contactForm.getChildren().addAll(
            formTitle, 
            formSubtitle, 
            nameField, 
            phoneField, 
            emailField, 
            queryArea, 
            sendButton
        );
        
        return contactForm;
    }

    private TextField createStyledTextField(String promptText) {
        TextField textField = new TextField();
        textField.setPromptText(promptText);
        textField.setPrefHeight(40);
        textField.setStyle(
            "-fx-background-color: #ffffffff;" +
            "-fx-border-color: #f9970dff;" +
            "-fx-border-width: 0.5;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 8;" +
            "-fx-font-size: 14;" +
            "-fx-prompt-text-fill: #999999;" +
            "-fx-text-fill: #333333;" +
            "-fx-padding: 0 10 0 10;"
        );
        return textField;
    }

    private void handleContactSubmission(String name, String phone, String email, String query) {
        // Handle form submission here
        System.out.println("Contact Form Submitted:");
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Query: " + query);
        
        // You can add email sending logic, database storage, etc. here
        showAlert("Thank you! Your message has been sent successfully.");
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

package com.tirthongo.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.tirthongo.dao.ProfileDao;

public class UserProfile {

    private static String loggedInUserUid;
    private static String userEmail;
    private static String userRole;
     private File selectedProfileImageFile;

     

    public void setLoggedInUserUid(String uid) {
        this.loggedInUserUid = uid;
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
    }

    public void setUserRole(String role) {
        this.userRole = role;
    }
    
    public Scene getProfilScene (Stage primaryStage) {

        System.out.println("UserProfile loaded. Received UID: " + this.loggedInUserUid);
    // ... rest of your method ...

        // Enhanced Top bar with gradient and better spacing
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 3);");
        topBar.setAlignment(Pos.CENTER_LEFT);

        // Enhanced title with shadow effect
        Label title = new Label("ॐ TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#ec7208ff")); 

        topBar.getChildren().addAll(title);

        // Enhanced welcome section with modern styling
        Label heading = new Label("Welcome to Your Profile");
        heading.setStyle("-fx-font-weight: bold; -fx-font-size: 32px; -fx-text-fill: #2c3e50;");
        heading.setAlignment(Pos.CENTER);
        
        Label content = new Label("Complete your profile to unlock personalized travel experiences");
        content.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");
        content.setAlignment(Pos.CENTER);

       
        // Profile Image Upload Section
        ImageView profileImageView = new ImageView();

        // Load default image with proper resource handling
        try {
            InputStream imageStream = getClass().getResourceAsStream("/Assets/Images/profileicon.jpg");
            if (imageStream != null) {
                Image defaultImage = new Image(imageStream);
                profileImageView.setImage(defaultImage);
            } else {
                System.out.println("Default image not found at: /Assets/Images/profileicon.jpg");
            }
        } catch (Exception e) {
            System.out.println("Error loading default image: " + e.getMessage());
        }

        // CRITICAL: Set image properties correctly
        profileImageView.setFitWidth(150);
        profileImageView.setFitHeight(150);
        profileImageView.setPreserveRatio(false); // Allow image to fill entire circle
        profileImageView.setSmooth(true);

        // FIXED: Create clip with proper center positioning
        Circle clip = new Circle(75, 75, 75); // centerX, centerY, radius
        profileImageView.setClip(clip);

        // Border circle
        Circle border = new Circle(75); // Only radius needed in StackPane
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.web("#319fffff"));
        border.setStrokeWidth(1);

        // Add hover effect to border
        DropShadow imageHoverEffect = new DropShadow();
        imageHoverEffect.setColor(Color.web("#37ebffff"));
        imageHoverEffect.setRadius(5);
        imageHoverEffect.setSpread(0.3);

        // StackPane setup
        StackPane imageStack = new StackPane();
        imageStack.getChildren().addAll(profileImageView, border);
        imageStack.setMaxSize(150, 150);
        imageStack.setPrefSize(150, 150);
        imageStack.setStyle("-fx-cursor: hand;");

        // Hover effects for image
        imageStack.setOnMouseEntered(e -> {
            imageStack.setEffect(imageHoverEffect);
            border.setStroke(Color.web(" #f9970dff"));
        });

        imageStack.setOnMouseExited(e -> {
            imageStack.setEffect(null);
            border.setStroke(Color.web(" #f9970dff"));
        });

    
        // Inside your getProfilScene method, find this block:
        imageStack.setOnMouseClicked(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Profile Image");
            fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
            );
            File file = fileChooser.showOpenDialog(primaryStage); // Renamed to 'file' for clarity in this scope
            if (file != null && file.exists()) {
                try {
                    Image newImage = new Image(new FileInputStream(file), 150, 150, false, true);
                    profileImageView.setImage(newImage);
                    
                    // Create new clip with proper center coordinates
                    Circle newClip = new Circle(75, 75, 75); 
                    profileImageView.setClip(newClip);
                    
                    // --- NEW: Store the selected file in the class-level field ---
                    this.selectedProfileImageFile = file; // Store the selected File object here
                    System.out.println("Image selected successfully: " + selectedProfileImageFile.getName());
                    
                    // NOTE: The actual Firebase Storage upload happens when the 'Save' button is clicked.
                    // This listener just handles displaying the selected image locally and storing its File object.

                } catch (Exception ex) {
                    System.out.println("Error loading image: " + ex.getMessage());
                    showAlert("Image Load Failed", "Couldn't load selected image. Please try a different file.", Alert.AlertType.ERROR);
                    this.selectedProfileImageFile = null; // Clear the stored file if loading fails
                }
            } else {
                // If no file is selected or the selected file doesn't exist, clear the stored file.
                this.selectedProfileImageFile = null; 
            }
        });

        Label uploadLabel = new Label("📷 Click to upload profile photo");
        uploadLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #667eea; -fx-cursor: hand;");
        uploadLabel.setOnMouseClicked(imageStack.getOnMouseClicked());

        VBox imageSection = new VBox(10, imageStack, uploadLabel);
        imageSection.setAlignment(Pos.CENTER);
        
        // Enhanced form fields with consistent styling
        String fieldStyle = "-fx-background-color: #f8f9fa; -fx-border-color: #e9ecef; -fx-border-width: 1; " +
                           "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 12; " +
                           "-fx-font-size: 14px; -fx-pref-height: 45;";
        
        String labelStyle = "-fx-font-weight: 600; -fx-text-fill: #2c3e50; -fx-font-size: 14px;";

        // Personal Information Section
        Label personalInfoHeader = new Label("👤 Personal Information");
        personalInfoHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #34495e; -fx-padding: 0 0 10 0;");

        Label firstName = new Label("First Name *");
        firstName.setStyle(labelStyle);
        TextField firstNameField = new TextField();
        firstNameField.setStyle(fieldStyle);
        firstNameField.setPromptText("Enter your first name");
        
        Label lastName = new Label("Last Name *");
        lastName.setStyle(labelStyle);
        TextField lastNameField = new TextField();
        lastNameField.setStyle(fieldStyle);
        lastNameField.setPromptText("Enter your last name");

        Label gender = new Label("Gender *");
        gender.setStyle(labelStyle);
        ComboBox<String> genderField = new ComboBox<>();
        genderField.getItems().addAll("Male", "Female", "Other");
        genderField.setStyle(fieldStyle + "-fx-background-color: white;");
        genderField.setPromptText("Select your gender");
        genderField.setPrefHeight(45);
        genderField.setMaxWidth(Double.MAX_VALUE);

        Label dob = new Label("Date of Birth *");
        dob.setStyle(labelStyle);
        DatePicker dobField = new DatePicker();
        dobField.setStyle(fieldStyle + "-fx-background-color: white;");
        dobField.setPromptText("Select your date of birth");
        dobField.setPrefHeight(45);
        dobField.setMaxWidth(Double.MAX_VALUE);

        Label email = new Label("Email Address *");
        email.setStyle(labelStyle);
        TextField emailField = new TextField();
        emailField.setStyle(fieldStyle);
        emailField.setPromptText("your.email@example.com");

        Label address = new Label("Address");
        address.setStyle(labelStyle);
        TextField addressField = new TextField();
        addressField.setStyle(fieldStyle);
        addressField.setPromptText("Enter your full address");

        Label state = new Label("State *");
        state.setStyle(labelStyle);
        ComboBox<String> stateField = new ComboBox<>();
        stateField.getItems().addAll(
            "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
            "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka",
            "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram",
            "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu",
            "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal",
            "Delhi", "Jammu and Kashmir", "Ladakh", "Puducherry", "Chandigarh",
            "Dadra and Nagar Haveli and Daman and Diu", "Lakshadweep",
            "Andaman and Nicobar Islands"
        );
        stateField.setStyle(fieldStyle + "-fx-background-color: white;");
        stateField.setPromptText("Select your state");
        stateField.setPrefHeight(45);
        stateField.setMaxWidth(Double.MAX_VALUE);

        Label pincode = new Label("Pincode *");
        pincode.setStyle(labelStyle);
        TextField pincodeField = new TextField();
        pincodeField.setStyle(fieldStyle);
        pincodeField.setPromptText("6-digit pincode");

        // Enhanced form layout with better spacing
        VBox vbx1 = new VBox(8, firstName, firstNameField);
        vbx1.setPrefWidth(250);
        VBox vbx2 = new VBox(8, lastName, lastNameField);
        vbx2.setPrefWidth(250);
        HBox hbx1 = new HBox(30, vbx1, vbx2);
        hbx1.setAlignment(Pos.TOP_CENTER);

        VBox vbx3 = new VBox(8, gender, genderField);
        vbx3.setPrefWidth(250);
        VBox vbx4 = new VBox(8, dob, dobField);
        vbx4.setPrefWidth(250);

        HBox hbx2 = new HBox(30, vbx3, vbx4);
        hbx2.setAlignment(Pos.TOP_CENTER);

        VBox vbx5 = new VBox(8, email, emailField);
        vbx5.setPrefWidth(250);
        VBox vbx6 = new VBox(8, address, addressField);
        vbx6.setPrefWidth(250);
        HBox hbx3 = new HBox(30, vbx5, vbx6);
        hbx3.setAlignment(Pos.TOP_CENTER);

        VBox vbx7 = new VBox(8, state, stateField);
        vbx7.setPrefWidth(250);
        VBox vbx8 = new VBox(8, pincode, pincodeField);
        vbx8.setPrefWidth(250);
        HBox hbx4 = new HBox(30, vbx7, vbx8);
        hbx4.setAlignment(Pos.TOP_CENTER);

        // Enhanced button with hover effects and better styling
        Button save = new Button("💾 Save & Continue");
        save.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);" + "-fx-text-fill: black;" +
                     "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                     "-fx-font-weight: bold; " +
                     "-fx-font-size: 15px; " +
                     "-fx-background-radius: 10; " +
                     "-fx-cursor: hand;");
        save.setPrefWidth(200);
        save.setPrefHeight(50);
        save.setMinHeight(50);
        save.setMaxHeight(50);
        
        // Add hover effect
        save.setOnMouseEntered(e -> save.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                 "-fx-text-fill: black; " +
                                                 "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                                 "-fx-font-weight: bold; " +
                                                 "-fx-font-size: 15px; " +
                                                 "-fx-background-radius: 10; " +
                                                 "-fx-cursor: hand; " +
                                                 "-fx-scale-y: 1.05;"));
        
        save.setOnMouseExited(e -> save.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                "-fx-text-fill: black; " +
                                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                                                "-fx-font-weight: bold; " +
                                                "-fx-font-size: 15px; " +
                                                "-fx-background-radius: 10; " +
                                                "-fx-cursor: hand; " +
                                                "-fx-scale-y: 1.0;"));
    
        // Inside your getProfilScene method, find this block and replace it:
save.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent arg0) {
        // Collect data from UI fields
        Map<String, Object> userData = new HashMap<>();
        userData.put("firstName", firstNameField.getText());
        userData.put("lastName", lastNameField.getText());
        userData.put("gender", genderField.getValue());
        userData.put("dob", (dobField.getValue() != null) ? dobField.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE) : null);
        userData.put("address", addressField.getText());
        userData.put("state", stateField.getValue());
        userData.put("pincode", pincodeField.getText());
        
        // --- NEW: Handle Image Upload to Firebase Storage ---
        String profileImageUrl = null;
        if (loggedInUserUid != null && selectedProfileImageFile != null) {
            profileImageUrl = com.tirthongo.dao.StorageDao.uploadProfileImage(loggedInUserUid, selectedProfileImageFile);
            if (profileImageUrl == null) {
                showAlert("Image Upload Failed", "Failed to upload profile image. Profile data will be saved without image.", Alert.AlertType.WARNING);
            }
        }
        
        // Add the image URL to userData if available
        if (profileImageUrl != null) {
            userData.put("profileImageUrl", profileImageUrl);
        } else {
            // OPTIONAL: If profileImageUrl is null, and you want to explicitly remove an existing image,
            // or ensure it's not set if no new image was uploaded:
            // userData.put("profileImageUrl", null); // Set to null to remove from Firestore
        }

        // --- Use userRole field to determine the collection ---
        String collectionToUpdate;
        if ("admin".equals(userRole)) {
            collectionToUpdate = "admins";
        } else {
            collectionToUpdate = "Travellers";
        }

        // --- Use loggedInUserUid field for the document ID ---
        if (loggedInUserUid != null) {
            boolean success = ProfileDao.updateProfileData(collectionToUpdate, loggedInUserUid, userData);
            if (success) {
                showAlert("Profile Saved", "Your profile information has been updated successfully!", Alert.AlertType.INFORMATION);
                // Optional: Navigate to next page, e.g., home dashboard
                primaryStage.setScene(new Homeload().gethomescene(primaryStage));
            } else {
                showAlert("Save Failed", "There was an error saving your profile. Please try again.", Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Error", "User ID is missing. Cannot save profile. Please log in again.", Alert.AlertType.ERROR);
        }
    }
});


        // Required fields notice
        Label requiredNote = new Label("* Required fields");
        requiredNote.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c; -fx-font-style: italic;");
        
        // Enhanced form container with modern card design
        VBox roundBox = new VBox(25, personalInfoHeader, hbx1, hbx2, hbx3, hbx4, requiredNote, save);
        roundBox.setMaxWidth(650);
        roundBox.setPadding(new Insets(35, 40, 35, 40));
        roundBox.setAlignment(Pos.TOP_CENTER);

        // Modern card styling with subtle gradient
        roundBox.setStyle(
            "-fx-background-color: linear-gradient(to left, #ffffffff, #f8c47bff) ; " +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 8);" +
            "-fx-border-color: rgba(102, 126, 234, 0.1);" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 15;"
        );

        // Background with subtle pattern
        VBox mainContainer = new VBox(20, topBar,heading, content, imageSection, roundBox);
        mainContainer.setPadding(new Insets(0, 0, 30, 0));
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setStyle("-fx-background-color: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);");

        ScrollPane scroll = new ScrollPane(mainContainer);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setPannable(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color: transparent;");

        Scene sc = new Scene(scroll,1300, 700);
        primaryStage.setMaximized(true);

        // --- FIX: Attach setOnShown to the Stage, not the Scene ---
        // The event handler will be added directly to the primaryStage that this scene is set upon.
        // This ensures the logic runs when the window (stage) containing this scene is shown.
        primaryStage.setOnShown(event -> {
            if (loggedInUserUid != null && userRole != null) {
                String collectionToFetch;
                if ("admin".equals(userRole)) {
                    collectionToFetch = "admins";
                } else {
                    collectionToFetch = "Travellers";
                }

                Map<String, Object> existingData = ProfileDao.fetchProfileData(collectionToFetch, loggedInUserUid);
                if (existingData != null) {
                    populateFields(existingData, firstNameField, lastNameField, genderField, dobField, emailField, addressField, stateField, pincodeField, profileImageView);
                } else {
                    if (this.userEmail != null && !this.userEmail.isEmpty()) {
                        emailField.setText(this.userEmail);
                    }
                    System.out.println("No existing profile found in Firestore. Fields left for user input.");
                }
            } else if (this.userEmail != null && !this.userEmail.isEmpty()) {
                emailField.setText(this.userEmail);
                System.err.println("Warning: UserProfile loaded without full UID/Role context. Pre-filling email only.");
            } else {
                System.err.println("Warning: UserProfile loaded without UID or Email. Cannot pre-populate fields.");
            }
        });
        
        return sc;
    }

    // Inside your UserProfile.java class, typically after getProfilScene and before showAlert (or at the end of the class)

    // Helper method to populate fields
    private void populateFields(Map<String, Object> data, TextField firstNameField, TextField lastNameField, ComboBox<String> genderField, DatePicker dobField, TextField emailField, TextField addressField, ComboBox<String> stateField, TextField pincodeField, ImageView profileImageView) {
        // Email field is pre-filled from userEmail setter or fetched data
        if (data.containsKey("email") && data.get("email") != null) {
            emailField.setText(data.get("email").toString());
        } else if (this.userEmail != null) { // Fallback to email passed during login if not in Firestore
            emailField.setText(this.userEmail);
        }
        
        if (data.containsKey("firstName") && data.get("firstName") != null) {
            firstNameField.setText(data.get("firstName").toString());
        }
        if (data.containsKey("lastName") && data.get("lastName") != null) {
            lastNameField.setText(data.get("lastName").toString());
        }
        if (data.containsKey("gender") && data.get("gender") != null) {
            genderField.setValue(data.get("gender").toString());
        }
        if (data.containsKey("dob") && data.get("dob") != null) {
            try {
                // Assuming DOB is stored as ISO_LOCAL_DATE (YYYY-MM-DD)
                // Firestore's 'timestampValue' is for full timestamps. If you store just date strings, ensure format.
                dobField.setValue(LocalDate.parse(data.get("dob").toString()));
            } catch (Exception e) {
                System.err.println("Error parsing DOB: " + data.get("dob") + " -> " + e.getMessage());
            }
        }
        if (data.containsKey("address") && data.get("address") != null) {
            addressField.setText(data.get("address").toString());
        }
        if (data.containsKey("state") && data.get("state") != null) {
            stateField.setValue(data.get("state").toString());
        }
        if (data.containsKey("pincode") && data.get("pincode") != null) {
            pincodeField.setText(data.get("pincode").toString());
        }
        
        // TODO: Load profile image from URL if 'profileImageUrl' exists in data
        // This will require Firebase Storage download functionality in a real application.
        // For now, this just tries to load it if it's a URL.
        if (data.containsKey("profileImageUrl") && data.get("profileImageUrl") != null && !data.get("profileImageUrl").toString().isEmpty()) {
            try {
                // Assuming profileImageUrl is a direct URL to the image
                Image profileImage = new Image(data.get("profileImageUrl").toString(), 150, 150, false, true);
                profileImageView.setImage(profileImage);
            } catch (Exception e) {
                System.err.println("Error loading profile image from URL: " + e.getMessage());
            }
        }
    }

    // Helper method to show alerts (copy from UserProfile)
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
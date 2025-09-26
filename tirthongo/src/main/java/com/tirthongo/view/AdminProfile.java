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
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.tirthongo.dao.ProfileDao;

public class AdminProfile{

    private String loggedInAdminUid; // Or loggedInUserUid if you use a generic name
    private String adminEmail; // Or userEmail
    private String adminRole;
     private File selectedProfileImageFile;

    public void setLoggedInAdminUid(String uid) { // Adjust method name if different
        this.loggedInAdminUid = uid;
    }

    public void setAdminEmail(String email) { // Adjust method name if different
        this.adminEmail = email;
    }

    public void setAdminRole(String role) { // Adjust method name if different
        this.adminRole = role;
    }
    
    public Scene getAdminProfilScene (Stage primaryStage) {

         System.out.println("AdminProfile setup loaded. Received UID: " + this.loggedInAdminUid);

        // Enhanced Top bar with gradient and better spacing (same as user profile)
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
        Label heading = new Label("Admin Profile Setup");
        heading.setStyle("-fx-font-weight: bold; -fx-font-size: 32px; -fx-text-fill: #2c3e50;");
        heading.setAlignment(Pos.CENTER);
        
        Label content = new Label("Complete Profile Setup");
        content.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");
        content.setAlignment(Pos.CENTER);

        // Profile Image Upload Section (same as user profile)
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

        // Set image properties correctly
        profileImageView.setFitWidth(150);
        profileImageView.setFitHeight(150);
        profileImageView.setPreserveRatio(false);
        profileImageView.setSmooth(true);

        // Create clip with proper center positioning
        Circle clip = new Circle(75, 75, 75);
        profileImageView.setClip(clip);

        // Border circle
        Circle border = new Circle(75);
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

        // // Image upload handling
        // imageStack.setOnMouseClicked(event -> {
        //     FileChooser fileChooser = new FileChooser();
        //     fileChooser.setTitle("Choose Admin Profile Image");
        //     fileChooser.getExtensionFilters().addAll(
        //         new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
        //     );
        //     File selectedFile = fileChooser.showOpenDialog(primaryStage);
        //     if (selectedFile != null && selectedFile.exists()) {
        //         try {
        //             Image newImage = new Image(new FileInputStream(selectedFile), 150, 150, false, true);
        //             profileImageView.setImage(newImage);
                    
        //             Circle newClip = new Circle(75, 75, 75);
        //             profileImageView.setClip(newClip);
                    
        //             System.out.println("Admin image uploaded successfully: " + selectedFile.getName());
                    
        //         } catch (Exception ex) {
        //             System.out.println("Error loading image: " + ex.getMessage());
        //             Alert alert = new Alert(Alert.AlertType.ERROR);
        //             alert.setTitle("Image Load Failed");
        //             alert.setHeaderText(null);
        //             alert.setContentText("Couldn't load selected image. Please try a different file.");
        //             alert.showAndWait();
        //         }
        //     }
        // });
        // Inside your getAdminProfilScene method, find the imageStack.setOnMouseClicked block:
    imageStack.setOnMouseClicked(event -> {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Admin Profile Image"); // Adjust title
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.bmp", "*.gif")
    );
    File file = fileChooser.showOpenDialog(primaryStage); // Renamed to 'file' for clarity in this scope
    if (file != null && file.exists()) {
        try {
            Image newImage = new Image(new FileInputStream(file), 150, 150, false, true);
            profileImageView.setImage(newImage);

            Circle newClip = new Circle(75, 75, 75); 
            profileImageView.setClip(newClip);

            // --- NEW: Store the selected file in the class-level field ---
            this.selectedProfileImageFile = file; // Store the file
            System.out.println("Admin image selected successfully: " + selectedProfileImageFile.getName());

        } catch (Exception ex) {
            System.out.println("Error loading image: " + ex.getMessage());
            showAlert("Image Load Failed", "Couldn't load selected image. Please try a different file.", Alert.AlertType.ERROR);
            this.selectedProfileImageFile = null; // Clear if loading fails
        }
    } else {
        this.selectedProfileImageFile = null; // Clear if no file selected or exists
    }
});

        Label uploadLabel = new Label("📷 Click to upload admin photo");
        uploadLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #667eea; -fx-cursor: hand;");
        uploadLabel.setOnMouseClicked(imageStack.getOnMouseClicked());

        VBox imageSection = new VBox(10, imageStack, uploadLabel);
        imageSection.setAlignment(Pos.CENTER);
        
        // Enhanced form fields with consistent styling (same as user profile)
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
        emailField.setPromptText("admin.email@tirthongo.com");

        Label mobile = new Label("Mobile Number *");
        mobile.setStyle(labelStyle);
        TextField mobileField = new TextField();
        mobileField.setStyle(fieldStyle);
        mobileField.setPromptText("Enter 10-digit mobile number");

        // Administrative Information Section
        Label adminInfoHeader = new Label("🔧 Administrative Information");
        adminInfoHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #34495e; -fx-padding: 15 0 10 0;");

        Label gst = new Label("GST Number");
        gst.setStyle(labelStyle);
        TextField gstField = new TextField();
        gstField.setStyle(fieldStyle);
        gstField.setPromptText("Enter GST number (if applicable)");

        // Location Information Section
        Label address = new Label("Address *");
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

        Label officeLocation = new Label("Office Location");
        officeLocation.setStyle(labelStyle);
        ComboBox<String> officeLocationField = new ComboBox<>();
        officeLocationField.getItems().addAll("Head Office - Mumbai", "Regional Office - Delhi", "Regional Office - Bangalore", "Regional Office - Chennai", "Remote");
        officeLocationField.setStyle(fieldStyle + "-fx-background-color: white;");
        officeLocationField.setPromptText("Select office location");
        officeLocationField.setPrefHeight(45);
        officeLocationField.setMaxWidth(Double.MAX_VALUE);

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
        VBox vbx6 = new VBox(8, mobile, mobileField);
        vbx6.setPrefWidth(250);
        HBox hbx3 = new HBox(30, vbx5, vbx6);
        hbx3.setAlignment(Pos.TOP_CENTER);

        VBox vbx12 = new VBox(8, gst, gstField);
        vbx12.setPrefWidth(250);
        HBox hbx6 = new HBox(30, vbx12);
        hbx6.setAlignment(Pos.TOP_CENTER);

        VBox vbx13 = new VBox(8, address, addressField);
        vbx13.setPrefWidth(250);
        VBox vbx14 = new VBox(8, state, stateField);
        vbx14.setPrefWidth(250);
        HBox hbx7 = new HBox(30, vbx13, vbx14);
        hbx7.setAlignment(Pos.TOP_CENTER);

        VBox vbx15 = new VBox(8, pincode, pincodeField);
        vbx15.setPrefWidth(250);
        VBox vbx16 = new VBox(8, officeLocation, officeLocationField);
        vbx16.setPrefWidth(250);
        HBox hbx8 = new HBox(30, vbx15, vbx16);
        hbx8.setAlignment(Pos.TOP_CENTER);

        // Enhanced button with hover effects and better styling (same as user profile)
        Button save = new Button("💾 Save Admin Profile");
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
                                                 "-fx-text-fill: white; " +
                                                 "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                                 "-fx-font-weight: bold; " +
                                                 "-fx-font-size: 15px; " +
                                                 "-fx-background-radius: 10; " +
                                                 "-fx-cursor: hand; " +
                                                 "-fx-scale-y: 1.05;"));
        
        save.setOnMouseExited(e -> save.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                "-fx-text-fill: white; " +
                                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                                                "-fx-font-weight: bold; " +
                                                "-fx-font-size: 15px; " +
                                                "-fx-background-radius: 10; " +
                                                "-fx-cursor: hand; " +
                                                "-fx-scale-y: 1.0;"));
        
        // ... (inside your getAdminProfilScene method, after the 'save' Button declaration and styling) ...

        // ... (inside your getAdminProfilScene method, after the 'save' Button declaration and styling) ...

        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                // Collect data from UI fields into a Map for Firestore
                Map<String, Object> adminProfileData = new HashMap<>();
                
                // Personal Information
                adminProfileData.put("firstName", firstNameField.getText());
                adminProfileData.put("lastName", lastNameField.getText());
                adminProfileData.put("gender", genderField.getValue());
                adminProfileData.put("dob", (dobField.getValue() != null) ? dobField.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE) : null);
                adminProfileData.put("email", emailField.getText()); 
                adminProfileData.put("mobile", mobileField.getText());
                adminProfileData.put("gstNumber", gstField.getText());

                // Location Information
                adminProfileData.put("address", addressField.getText());
                adminProfileData.put("state", stateField.getValue());
                adminProfileData.put("pincode", pincodeField.getText());
                adminProfileData.put("officeLocation", officeLocationField.getValue());

                // Handle Image Upload to Firebase Storage for Admin
                String profileImageUrl = null;
                if (loggedInAdminUid != null && selectedProfileImageFile != null) {
                    profileImageUrl = com.tirthongo.dao.StorageDao.uploadProfileImage(loggedInAdminUid, selectedProfileImageFile);
                    if (profileImageUrl == null) {
                        showAlert("Image Upload Failed", "Failed to upload admin profile image. Profile data will be saved without image.", Alert.AlertType.WARNING);
                    }
                }
                
                // Add the image URL to adminProfileData if available
                if (profileImageUrl != null) {
                    adminProfileData.put("profileImageUrl", profileImageUrl);
                } 
                // Optional: To explicitly remove an existing image URL from Firestore if no new image is selected:
                // else { adminProfileData.put("profileImageUrl", null); }

                // The collection for admin profiles is "admins"
                String collectionToUpdate = "admins";

                // Ensure the logged-in admin's UID is available before attempting to save
                if (loggedInAdminUid != null) {
                    boolean success = ProfileDao.updateProfileData(collectionToUpdate, loggedInAdminUid, adminProfileData);
                    if (success) {
                        showAlert("Admin Profile Saved", "Your admin profile information has been updated successfully!", Alert.AlertType.INFORMATION);
                        
                        // --- NEW: Navigation to AdminDashboardPage after successful save ---
                        try {
                            AdminDashboardPage adminDashboard = new AdminDashboardPage(); // Instantiate the AdminDashboardPage
                            // Pass the current admin's context to the dashboard
                            adminDashboard.setLoggedInAdminUid(loggedInAdminUid); 
                            adminDashboard.setAdminEmail(adminEmail);           
                            adminDashboard.setAdminRole(adminRole);             

                            // Get the dashboard scene and set it on the primary stage
                            Scene adminDashboardScene = adminDashboard.getAdminDashboardScene(primaryStage); 
                            primaryStage.setScene(adminDashboardScene); 
                            primaryStage.setTitle("TirthOnGo - Admin Dashboard"); // Update window title

                        } catch (Exception ex) {
                            System.err.println("Error navigating to Admin Dashboard: " + ex.getMessage());
                            ex.printStackTrace();
                            showAlert("Navigation Error", "Admin profile saved, but failed to load dashboard. Please restart app.", Alert.AlertType.ERROR);
                        }
                        // --- END NEW NAVIGATION ---

                    } else {
                        showAlert("Save Failed", "There was an error saving your admin profile. Please try again.", Alert.AlertType.ERROR);
                    }
                } else {
                    showAlert("Error", "Admin UID is missing. Cannot save profile. Please ensure you are logged in correctly.", Alert.AlertType.ERROR);
                }
            }
        });

        // save.setOnAction(new EventHandler<ActionEvent>() {
        //     @Override
        //     public void handle(ActionEvent arg0) {
        //         // Collect data from UI fields into a Map for Firestore
        //         Map<String, Object> adminProfileData = new HashMap<>();
                
        //         // Personal Information
        //         adminProfileData.put("firstName", firstNameField.getText());
        //         adminProfileData.put("lastName", lastNameField.getText());
        //         adminProfileData.put("gender", genderField.getValue());
        //         adminProfileData.put("dob", (dobField.getValue() != null) ? dobField.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE) : null);
        //         adminProfileData.put("email", emailField.getText()); 
        //         adminProfileData.put("mobile", mobileField.getText());
        //         adminProfileData.put("gstNumber", gstField.getText());

        //         // Location Information
        //         adminProfileData.put("address", addressField.getText());
        //         adminProfileData.put("state", stateField.getValue());
        //         adminProfileData.put("pincode", pincodeField.getText());
        //         adminProfileData.put("officeLocation", officeLocationField.getValue());

        //         // --- NEW: Handle Image Upload to Firebase Storage for Admin ---
        //         String profileImageUrl = null;
        //         if (loggedInAdminUid != null && selectedProfileImageFile != null) {
        //             // Attempt to upload the image *synchronously* before saving profile data
        //             // Call the StorageDao to upload the image
        //             profileImageUrl = com.tirthongo.dao.StorageDao.uploadProfileImage(loggedInAdminUid, selectedProfileImageFile);
        //             if (profileImageUrl == null) {
        //                 showAlert("Image Upload Failed", "Failed to upload admin profile image. Profile data will be saved without image.", Alert.AlertType.WARNING);
        //             }
        //         }
                
        //         // Add the image URL to adminProfileData if available
        //         if (profileImageUrl != null) {
        //             adminProfileData.put("profileImageUrl", profileImageUrl);
        //         } else {
        //             // Optional: To explicitly remove an existing image URL from Firestore if no new image is selected,
        //             // and you don't want to preserve the old one.
        //             // adminProfileData.put("profileImageUrl", null); 
        //         }

        //         // The collection for admin profiles is "admins"
        //         String collectionToUpdate = "admins";

        //         // Ensure the logged-in admin's UID is available before attempting to save
        //         if (loggedInAdminUid != null) {
        //             boolean success = ProfileDao.updateProfileData(collectionToUpdate, loggedInAdminUid, adminProfileData);
        //             if (success) {
        //                 showAlert("Admin Profile Saved", "Your admin profile information has been updated successfully!", Alert.AlertType.INFORMATION);
        //                 // Optional: Add navigation to the admin dashboard here, e.g.:
        //                 // primaryStage.setScene(new AdminDashboardPage().getAdminDashboardScene(primaryStage, loggedInAdminUid, adminRole));
        //             } else {
        //                 showAlert("Save Failed", "There was an error saving your admin profile. Please try again.", Alert.AlertType.ERROR);
        //             }
        //         } else {
        //             showAlert("Error", "Admin UID is missing. Cannot save profile. Please ensure you are logged in correctly.", Alert.AlertType.ERROR);
        //         }
        //     }
        // });
       
        // Required fields notice
        Label requiredNote = new Label("* Required fields");
        requiredNote.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c; -fx-font-style: italic;");
        
        // Enhanced form container with modern card design (same as user profile)
        VBox roundBox = new VBox(25, 
            personalInfoHeader, hbx1, hbx2, hbx3,
            hbx7, hbx8, hbx6,
            requiredNote, save);
        roundBox.setMaxWidth(650);
        roundBox.setPadding(new Insets(35, 40, 35, 40));
        roundBox.setAlignment(Pos.TOP_CENTER);

        // Modern card styling with subtle gradient (same as user profile)
        roundBox.setStyle(
            "-fx-background-color: linear-gradient(to left, #ffffffff, #f8c47bff) ; " +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 8);" +
            "-fx-border-color: rgba(102, 126, 234, 0.1);" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 15;"
        );

        // Background with subtle pattern (same as user profile)
        VBox mainContainer = new VBox(20, topBar, heading, content, imageSection, roundBox);
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
         // --- Use loggedInAdminUid, adminEmail, and adminRole fields when stage is shown to fetch data ---
        primaryStage.setOnShown(event -> { // Attach to primaryStage, not scene
            if (loggedInAdminUid != null && adminRole != null) { // Accessing the fields (private members of AdminProfile)
                String collectionToFetch;
                if ("admin".equals(adminRole)) { // Accessing the 'adminRole' field
                    collectionToFetch = "admins";
                } else {
                    // This scenario should ideally not happen if this page is only for admins.
                    collectionToFetch = "Travellers"; // Fallback, implies a logical issue
                    System.err.println("Warning: AdminProfile trying to load with non-admin role: " + adminRole);
                }

                Map<String, Object> existingData = ProfileDao.fetchProfileData(collectionToFetch, loggedInAdminUid); // Accessing loggedInAdminUid and calling DAO
                if (existingData != null) {
                    // IMPORTANT: Replace this with your actual admin-specific UI fields
                    // Example:
                    firstNameField.setText(existingData.getOrDefault("firstName", "").toString());
                    lastNameField.setText(existingData.getOrDefault("lastName", "").toString());
                    mobileField.setText(existingData.getOrDefault("contactNumber", "").toString());
                    addressField.setText(existingData.getOrDefault("address", "").toString());
                    stateField.setValue(existingData.getOrDefault("state", "").toString());
                    pincodeField.setText(existingData.getOrDefault("pincode", "").toString());
                    gstField.setText(existingData.getOrDefault("gst", "").toString());
                    emailField.setText(existingData.getOrDefault("email", "").toString());


                    // Email field (assuming you have one for display, but it's non-editable)
                    if (existingData.containsKey("email") && existingData.get("email") != null) {
                        emailField.setText(existingData.get("email").toString());
                    } else if (this.adminEmail != null) { // Fallback to email passed during login
                        emailField.setText(this.adminEmail);
                    }
                    
                    System.out.println("Existing admin profile data loaded.");
                } else {
                    // If no existing data found, pre-fill email from the adminEmail field
                    if (this.adminEmail != null && !this.adminEmail.isEmpty()) { 
                        emailField.setText(this.adminEmail);
                    }
                    System.out.println("No existing admin profile found in Firestore. Fields left empty for input.");
                }
            } else if (this.adminEmail != null && !this.adminEmail.isEmpty()) {
                // Fallback to pre-fill email if UID or Role isn't fully propagated yet
                emailField.setText(this.adminEmail);
                System.err.println("Warning: AdminProfile loaded without full UID/Role context. Pre-filling email only.");
            } else {
                System.err.println("Warning: AdminProfile loaded without UID or Email. Cannot pre-populate fields.");
            }
        });
        
        return sc; 
    }

   
    // Helper method to show alerts
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

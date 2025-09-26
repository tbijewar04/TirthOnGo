package com.tirthongo.view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Map;
import java.util.regex.Pattern;
import com.tirthongo.controller.FirebaseInitializer;
import com.tirthongo.controller.LoginController;
import com.tirthongo.dao.ProfileDao;
import com.tirthongo.dao.StorageDao;
import com.tirthongo.dao.userdoc;
import com.tirthongo.model.AdminProfileModel;
import com.tirthongo.model.UserProfileModel;

public class LoginPage extends Application {


    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    // Password validation patterns
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");


    @Override
    public void start(Stage primaryStage) {

        FirebaseInitializer.initialize();
        StorageDao.initializeFirebaseAdminSDKForStorage();

        
        Label heading = new Label("Welcome Back to TirthOnGo");
        heading.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: #ffffffff;");
        heading.setAlignment(Pos.CENTER_LEFT);
        
        Label content = new Label("Sign in to continue your spiritual journey with us");
        content.setStyle("-fx-font-size: 18px; -fx-text-fill: #ffffffff; -fx-wrap-text: true;");
        content.setAlignment(Pos.CENTER_LEFT);
        content.setMaxWidth(400);

        // Enhanced form fields styling
        String fieldStyle = "-fx-background-color: #f8f9fa; -fx-border-color: #e9ecef; -fx-border-width: 1; " +
                            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 12; " +
                            "-fx-font-size: 14px; -fx-pref-height: 45;";
        
        String errorFieldStyle = "-fx-background-color: #f8f9fa; -fx-border-color: #e74c3c; -fx-border-width: 2; " +
                                 "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 12; " +
                                 "-fx-font-size: 14px; -fx-pref-height: 45;";
        
        String successFieldStyle = "-fx-background-color: #f8f9fa; -fx-border-color: #27ae60; -fx-border-width: 2; " +
                                   "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 12; " +
                                   "-fx-font-size: 14px; -fx-pref-height: 45;";
        
        String labelStyle = "-fx-font-weight: 600; -fx-text-fill: #2c3e50; -fx-font-size: 14px;";

        // Login Form Section Header
        Label loginHeader = new Label("Sign In to Your Account");
        loginHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #34495e; -fx-padding: 0 0 10 0;");

        // Email Field with validation
        Label emailLabel = new Label("Email *");
        emailLabel.setStyle(labelStyle);
        TextField emailField = new TextField();
        emailField.setStyle(fieldStyle);
        emailField.setPromptText("Enter your email (e.g., user@example.com)");
        
        // Email error message
        Label emailErrorLabel = new Label();
        emailErrorLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 12px;");
        emailErrorLabel.setVisible(false);

        // Password Field with validation
        Label passwordLabel = new Label("Password *");
        passwordLabel.setStyle(labelStyle);

        // --- START: Password visibility feature ---
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle(fieldStyle);
        passwordField.setPromptText("Enter your password");

        TextField passwordVisibleField = new TextField();
        passwordVisibleField.setStyle(fieldStyle);
        passwordVisibleField.setPromptText("Enter your password");
        passwordVisibleField.setVisible(false); // Initially hidden

        // Bind the text properties so they always have the same content
        passwordField.textProperty().bindBidirectional(passwordVisibleField.textProperty());
        
        ToggleButton showHidePasswordButton = new ToggleButton();
        // You can use a FontAwesome icon if you have it set up, or a small image
        // For demonstration, using simple text:
        showHidePasswordButton.setText("👁️"); // Eye icon (Unicode character)
        showHidePasswordButton.setTooltip(new Tooltip("Show/Hide Password"));
        showHidePasswordButton.setStyle("-fx-background-color: transparent; -fx-padding: 0 5 0 0;");
        showHidePasswordButton.setFont(Font.font("Segoe UI Emoji", 16)); // Use an emoji font for the icon
        
        // Listener to toggle visibility and icon
        showHidePasswordButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                passwordField.setVisible(false);
                passwordVisibleField.setVisible(true);
                showHidePasswordButton.setText("🔒"); // Eye-slash icon (Unicode character)
                passwordVisibleField.requestFocus(); // Keep focus on the visible field
            } else {
                passwordField.setVisible(true);
                passwordVisibleField.setVisible(false);
                showHidePasswordButton.setText("👁️");
                passwordField.requestFocus(); // Keep focus on the password field
            }
        });

        // StackPane to layer PasswordField, TextField, and the toggle button
        StackPane passwordStack = new StackPane();
        passwordStack.getChildren().addAll(passwordVisibleField, passwordField, showHidePasswordButton);
        // Position the button on the right inside the StackPane
        StackPane.setAlignment(showHidePasswordButton, Pos.CENTER_RIGHT);
        // Add padding to the right of the password fields to prevent text from going under the button
        passwordField.setPadding(new Insets(12, 35, 12, 12));
        passwordVisibleField.setPadding(new Insets(12, 35, 12, 12));
        // Ensure the StackPane itself takes the preferred width
        passwordStack.setPrefWidth(400); 
        passwordStack.setPrefHeight(45); // Match the height of the fields

        // --- END: Password visibility feature ---
        
        // Password error message
        Label passwordErrorLabel = new Label();
        passwordErrorLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 12px;");
        passwordErrorLabel.setVisible(false);

        // Email validation method
        Runnable validateEmail = () -> {
            String email = emailField.getText().trim();
            if (email.isEmpty()) {
                emailErrorLabel.setText("Email is required");
                emailErrorLabel.setVisible(true);
                emailField.setStyle(errorFieldStyle);
            } else if (!EMAIL_PATTERN.matcher(email).matches()) {
                emailErrorLabel.setText("Please enter a valid email format (e.g., user@example.com)");
                emailErrorLabel.setVisible(true);
                emailField.setStyle(errorFieldStyle);
            } else {
                emailErrorLabel.setVisible(false);
                emailField.setStyle(successFieldStyle);
            }
        };

        // Password validation method - now uses the common textProperty from either field
        Runnable validatePassword = () -> {
            // Get text from the visible field, as it's bound to passwordField
            String password = passwordVisibleField.getText(); 
            StringBuilder errorMessage = new StringBuilder();
            
            if (password.isEmpty()) {
                errorMessage.append("Password is required");
            } else {
                if (password.length() < 8) {
                    errorMessage.append("• At least 8 characters\n");
                }
                if (!UPPERCASE_PATTERN.matcher(password).matches()) {
                    errorMessage.append("• One uppercase letter\n");
                }
                if (!LOWERCASE_PATTERN.matcher(password).matches()) {
                    errorMessage.append("• One lowercase letter\n");
                }
                if (!DIGIT_PATTERN.matcher(password).matches()) {
                    errorMessage.append("• One digit\n");
                }
                if (!SPECIAL_CHAR_PATTERN.matcher(password).matches()) {
                    errorMessage.append("• One special character (!@#$%^&*)\n");
                }
            }
            
            if (errorMessage.length() > 0) {
                passwordErrorLabel.setText("Password missing:\n" + errorMessage.toString().trim());
                passwordErrorLabel.setVisible(true);
                passwordField.setStyle(errorFieldStyle); // Apply style to both fields
                passwordVisibleField.setStyle(errorFieldStyle);
            } else {
                passwordErrorLabel.setVisible(false);
                passwordField.setStyle(successFieldStyle); // Apply style to both fields
                passwordVisibleField.setStyle(successFieldStyle);
            }
        };

        // Real-time validation listeners
        emailField.textProperty().addListener((observable, oldValue, newValue) -> validateEmail.run());
        // Listener for password should be on the *common* textProperty (passwordField or passwordVisibleField)
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> validatePassword.run());

        // Focus effects with validation
        emailField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                emailField.setStyle(fieldStyle + "-fx-border-color: #f9970dff; -fx-border-width: 2;");
            } else {
                validateEmail.run();
            }
        });

        // Add focus listener for both password fields
        passwordField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                passwordField.setStyle(fieldStyle + "-fx-border-color: #f9970dff; -fx-border-width: 2;");
                passwordVisibleField.setStyle(fieldStyle + "-fx-border-color: #f9970dff; -fx-border-width: 2;");
            } else {
                validatePassword.run();
            }
        });
        passwordVisibleField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                passwordField.setStyle(fieldStyle + "-fx-border-color: #f9970dff; -fx-border-width: 2;");
                passwordVisibleField.setStyle(fieldStyle + "-fx-border-color: #f9970dff; -fx-border-width: 2;");
            } else {
                validatePassword.run();
            }
        });

        // Form layout with error messages
        VBox emailBox = new VBox(5, emailLabel, emailField, emailErrorLabel);
        emailBox.setPrefWidth(400);
        
        // Use the StackPane for password input
        VBox passwordBox = new VBox(5, passwordLabel, passwordStack, passwordErrorLabel);
        passwordBox.setPrefWidth(400);

        // Remember me and forgot password section
        HBox optionsBox = new HBox();
        optionsBox.setAlignment(Pos.CENTER_LEFT);
        optionsBox.setSpacing(10);
        optionsBox.setPrefWidth(400);

        CheckBox rememberMe = new CheckBox("Remember me");
        rememberMe.setStyle("-fx-text-fill: #000000ff; -fx-font-size: 13px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Hyperlink forgotPassword = new Hyperlink("Forgot Password?");
        forgotPassword.setStyle("-fx-text-fill: #000000ff; -fx-font-size: 13px; -fx-underline: false;");
        forgotPassword.setOnMouseEntered(e -> forgotPassword.setStyle("-fx-text-fill: #000000ff; -fx-font-size: 13px; -fx-underline: true;"));
        forgotPassword.setOnMouseExited(e -> forgotPassword.setStyle("-fx-text-fill: #000000ff; -fx-font-size: 13px; -fx-underline: false;"));

        optionsBox.getChildren().addAll(rememberMe, spacer, forgotPassword);

        // Enhanced Login button with validation
        Button loginBtn = new Button("Sign In as User");
        loginBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);" + 
                            "-fx-text-fill: black;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-size: 15px; " +
                            "-fx-background-radius: 10; " +
                            "-fx-cursor: hand;");
        loginBtn.setPrefWidth(150);
        loginBtn.setPrefHeight(50);
        loginBtn.setMinHeight(50);
        loginBtn.setMaxHeight(50);

        ProgressIndicator loader = new ProgressIndicator();
        loader.setMaxSize(20, 20);
        loader.setVisible(false); // Initially hidden

        // // Login button validation and action
        // loginBtn.setOnAction(e -> {
        //     // Re-run validation for email and password fields
        //     validateEmail.run();
        //     validatePassword.run();
            
        //     // Get the current text from the fields
        //     String email = emailField.getText().trim();
        //     String password = passwordField.getText(); 
            
        //     // Check if client-side validation has passed (using the results from the runnables)
        //     // Updated check to also consider if error labels are visible
        //     boolean isEmailValid = !email.isEmpty() && !emailErrorLabel.isVisible(); 
        //     boolean isPasswordValid = isValidPassword(password) && !passwordErrorLabel.isVisible(); 
            
        //     if (!isEmailValid || !isPasswordValid) {
        //         showAlert("Validation Error", "Please correct the errors in the form before proceeding.", Alert.AlertType.ERROR);
        //         return; // Stop execution if client-side validation fails
        //     }
            
        //     // Proceed with Firebase Authentication login
        //     // Call the updated LoginController method that returns LoginResult
        //     LoginController.LoginResult result = LoginController.signInWithEmailAndPassword(email, password);

        //     if (result.success) {
        //         // Firebase Authentication was successful. Now, determine the user's role from Firestore.
        //         String userRole = null;
        //         if (result.localId != null) { // Ensure we have a UID from the successful login
        //             // Corrected collection names: "Travellers" for users, "admins" for admins
        //             boolean isTraveller = LoginController.checkIfDocumentExists("Travellers", result.localId);
        //             // Also check 'admins' collection to handle potential role conflicts or misclicks
        //             boolean isAdmin = LoginController.checkIfDocumentExists("admins", result.localId);

        //             if (isTraveller && !isAdmin) { 
        //                 // The user exists in the 'Travellers' collection AND NOT in 'admins'. Confirmed regular user.
        //                 userRole = "user";
        //             } else if (isAdmin && !isTraveller) { 
        //                 // The user exists in the 'admins' collection AND NOT in 'Travellers'. This is an admin.
        //                 // Since they clicked "Sign In as User", we deny access to the user profile.
        //                 showAlert("Access Denied", "This account is identified as an Admin. Please use the 'Sign In as Admin' button to log in.", Alert.AlertType.ERROR);
        //                 return; // Stop here, do not proceed to user profile
        //             } else if (isTraveller && isAdmin) { 
        //                 // The user exists in BOTH 'Travellers' and 'admins' collections. Ambiguous role.
        //                 // For "Sign In as User" button, we prioritize the user role.
        //                 showAlert("Role Ambiguity", "This account has both Traveller and Admin roles. Proceeding as Traveller. Please use 'Sign In as Admin' for administrative access.", Alert.AlertType.WARNING);
        //                 userRole = "user";
        //             } else { 
        //                 // Authenticated with Firebase Auth, but no corresponding profile document found in either collection.
        //                 // This might happen if a user was just created, but their profile document in Firestore wasn't yet populated.
        //                 showAlert("Profile Missing", "Your account is authenticated but no profile found. Please complete your profile.", Alert.AlertType.INFORMATION);
        //                 userRole = "user"; // Assume it's a regular user and proceed to profile setup for data entry
        //             }
        //         } else {
        //             // This case should ideally not happen after a successful Firebase Auth login (result.localId should be present)
        //             showAlert("Login Error", "Failed to retrieve user ID from authentication. Please try again.", Alert.AlertType.ERROR);
        //             return;
        //         }

        //         // If the determined role is "user", navigate to the UserProfile page
        //         if ("user".equals(userRole)) {
        //             showAlert("Login Successful", "Welcome back to TirthOnGo, Traveller!", Alert.AlertType.INFORMATION);
        //             UserProfile userp = new UserProfile();
        //             userp.setLoggedInUserUid(result.localId); // Pass the UID to UserProfile
        //             userp.setUserEmail(result.email);         // Pass the email to UserProfile
        //             userp.setUserRole("user");                // Explicitly set the role for UserProfile to 'user'
        //             Scene profileScene = userp.getProfilScene(primaryStage); // Get the UserProfile scene
        //             Stage currentStage = (Stage) loginBtn.getScene().getWindow();
        //             currentStage.setScene(profileScene); // Set the new scene
        //         } else {
        //             // This `else` block catches scenarios where the `userRole` was not "user",
        //             // which means it was either "admin" (and denied above) or some other unhandled case.
        //             // An alert would have already been shown if it was "admin".
        //             // You might want a generic fallback here if no specific alert was given.
        //             System.err.println("Login failed or role not matched for user login button. Determined role: " + userRole);
        //             // showAlert("Login Failed", "Account could not be validated for Traveller access.", Alert.AlertType.ERROR);
        //         }

        //     } else {
        //         // Firebase Authentication failed (e.g., incorrect password, user not found)
        //         showAlert("Login Failed", result.errorMessage, Alert.AlertType.ERROR);
        //     }
        // });

        // // Action for "Sign In as User" button (loginBtn)
        // loginBtn.setOnAction(e -> {
        //     validateEmail.run();
        //     validatePassword.run();
            
        //     String email = emailField.getText().trim();
        //     String password = passwordField.getText(); 
            
        //     boolean isEmailValid = !email.isEmpty() && !emailErrorLabel.isVisible(); 
        //     boolean isPasswordValid = isValidPassword(password) && !passwordErrorLabel.isVisible(); 
            
        //     if (!isEmailValid || !isPasswordValid) {
        //         showAlert("Validation Error", "Please correct the errors in the form before proceeding.", Alert.AlertType.ERROR);
        //         return; 
        //     }
            
        //     LoginController.LoginResult result = LoginController.signInWithEmailAndPassword(email, password);

        //     if (result.success) {
        //         String userRole = null;
        //         if (result.localId != null) { 
        //             boolean isTraveller = LoginController.checkIfDocumentExists("Travellers", result.localId);
        //             boolean isAdmin = LoginController.checkIfDocumentExists("admins", result.localId);

        //             if (isTraveller && !isAdmin) { 
        //                 userRole = "user";
        //             } else if (isAdmin && !isTraveller) { 
        //                 showAlert("Access Denied", "This account is identified as an Admin. Please use the 'Sign In as Admin' button to log in.", Alert.AlertType.ERROR);
        //                 return; 
        //             } else if (isTraveller && isAdmin) { 
        //                 showAlert("Role Ambiguity", "This account has both Traveller and Admin roles. Proceeding as Traveller. Please use 'Sign In as Admin' for administrative access.", Alert.AlertType.WARNING);
        //                 userRole = "user";
        //             } else { 
        //                 // Authenticated but no profile document found, assume user and force profile setup
        //                 showAlert("Profile Missing", "Your account is authenticated but no profile found. Please complete your profile.", Alert.AlertType.INFORMATION);
        //                 userRole = "user"; 
        //             }
        //         } else {
        //             showAlert("Login Error", "Failed to retrieve user ID from authentication. Please try again.", Alert.AlertType.ERROR);
        //             return;
        //         }

        //         // If the determined role is "user", proceed with checking profile completion and navigation
        //         if ("user".equals(userRole)) {
                   
        //             // --- NEW: Fetch user profile data to check for completion ---
        //             Map<String, Object> profileData = ProfileDao.fetchProfileData("Travellers", result.localId);
        //             boolean isProfileComplete = false;

        //             System.out.println("--- Debugging Profile Completion for UID: " + result.localId + " ---");
        //             System.out.println("Fetched profileData from Firestore: " + (profileData == null ? "NULL" : profileData.toString()));
                    
        //             if (profileData != null) {
        //                 // Customize these checks based on your mandatory fields for Traveller profiles.
        //                 String firstName = (String) profileData.get("firstName");
        //                 String lastName = (String) profileData.get("lastName");
        //                 String gender = (String) profileData.get("gender");
        //                 String address = (String) profileData.get("address");
        //                 String state = (String) profileData.get("state");
        //                 String pincode = (String) profileData.get("pincode");
        //                 Object dobObject = profileData.get("dob"); // Date of Birth is an object

        //                 // Print values being checked
        //                 System.out.println("  Checking firstName: " + firstName + " (is " + (firstName != null && !firstName.isEmpty() ? "OK" : "MISSING/EMPTY") + ")");
        //                 System.out.println("  Checking lastName: " + lastName + " (is " + (lastName != null && !lastName.isEmpty() ? "OK" : "MISSING/EMPTY") + ")");
        //                 System.out.println("  Checking gender: " + gender + " (is " + (gender != null && !gender.isEmpty() ? "OK" : "MISSING/EMPTY") + ")");
        //                 System.out.println("  Checking address: " + address + " (is " + (address != null && !address.isEmpty() ? "OK" : "MISSING/EMPTY") + ")");
        //                 System.out.println("  Checking state: " + state + " (is " + (state != null && !state.isEmpty() ? "OK" : "MISSING/EMPTY") + ")");
        //                 System.out.println("  Checking pincode: " + pincode + " (is " + (pincode != null && !pincode.isEmpty() ? "OK" : "MISSING/EMPTY") + ")");
        //                 System.out.println("  Checking dob: " + dobObject + " (is " + (dobObject != null ? "OK" : "MISSING") + ")");


        //                 if (firstName != null && !firstName.isEmpty() && 
        //                     lastName != null && !lastName.isEmpty() &&
        //                     gender != null && !gender.isEmpty() && 
        //                     address != null && !address.isEmpty() && 
        //                     state != null && !state.isEmpty() && 
        //                     pincode != null && !pincode.isEmpty() && 
        //                     dobObject != null ) { 
        //                     isProfileComplete = true;
        //                 }
        //             } else {
        //                 System.out.println("  profileData is NULL. Profile is incomplete.");
        //             }
        //             System.out.println("  Final isProfileComplete: " + isProfileComplete);
        //             System.out.println("-------------------------------------------------");


        //             if (isProfileComplete) {
        //                 showAlert("Login Successful", "Welcome back to TirthOnGo!", Alert.AlertType.INFORMATION);
        //                 // --- Navigate to User Dashboard Page ---
        //                 try {
        //                     Homeload userDashboard = new Homeload(); // Using Homeload as per your previous request
        //                     userDashboard.setLoggedInUserUid(result.localId);
        //                     userDashboard.setUserEmail(result.email);
        //                     userDashboard.setUserRole("user"); 
        //                     Scene dashboardScene = userDashboard.gethomescene(primaryStage); // Using getHomeScene
        //                     primaryStage.setScene(dashboardScene);
        //                     primaryStage.setTitle("TirthOnGo - User Dashboard");
        //                 } catch (Exception ex) {
        //                     System.err.println("Error navigating to User Dashboard: " + ex.getMessage());
        //                     ex.printStackTrace();
        //                     showAlert("Navigation Error", "Login successful, but failed to load dashboard.", Alert.AlertType.ERROR);
        //                 }
        //             } else {
        //                 // Profile is incomplete, direct to UserProfile setup page
        //                 showAlert("Profile Incomplete", "Please complete your profile to continue.", Alert.AlertType.WARNING);
        //                 UserProfile userp = new UserProfile();
        //                 userp.setLoggedInUserUid(result.localId);
        //                 userp.setUserEmail(result.email);
        //                 userp.setUserRole("user");
        //                 Scene profileScene = userp.getProfilScene(primaryStage);
        //                 primaryStage.setScene(profileScene);
        //                 primaryStage.setTitle("TirthOnGo - Complete Profile");
        //             }

        //         } else {
        //             System.err.println("Login failed or role not matched for user login button. Determined role: " + userRole);
        //             showAlert("Login Failed", "Account could not be validated for Traveller access.", Alert.AlertType.ERROR);
        //         }

        //     } else {
        //         showAlert("Login Failed", result.errorMessage, Alert.AlertType.ERROR);
        //     }
        // });

    loginBtn.setOnAction(e -> {
    validateEmail.run();
    validatePassword.run();

    String email = emailField.getText().trim();
    String password = passwordField.getText(); 

    boolean isEmailValid = !email.isEmpty() && !emailErrorLabel.isVisible(); 
    boolean isPasswordValid = isValidPassword(password) && !passwordErrorLabel.isVisible(); 

    if (!isEmailValid || !isPasswordValid) {
        showAlert("Validation Error", "Please correct the errors in the form before proceeding.", Alert.AlertType.ERROR);
        return; 
    }

    loader.setVisible(true); // Show loader

    Task<Void> loginTask = new Task<Void>() {
        @Override
        protected Void call() {
            LoginController.LoginResult result = LoginController.signInWithEmailAndPassword(email, password);

            Platform.runLater(() -> {
                loader.setVisible(false); // Hide loader

                if (result.success) {
                    System.out.println("Local ID : " + result.localId);
                    UserProfileModel userProfile = userdoc.getUserProfileModel(email);
                    System.out.println("User Email" + userProfile.getEmail());
                    System.out.println(userProfile.getFirstName());
                    Homeload.setUserProfile(userProfile);
                    String userRole = null;

                    System.out.println("--- Login Success Debugging ---");
                    System.out.println("Logged in UID from Firebase Auth: " + result.localId);
                    System.out.println("Logged in Email from Firebase Auth: " + result.email);

                    if (result.localId != null) { 
                        boolean isTraveller = LoginController.checkIfDocumentExists("Travellers", result.localId);
                        boolean isAdmin = LoginController.checkIfDocumentExists("admins", result.localId);

                        if (isTraveller && !isAdmin) { 
                            userRole = "user";
                        } else if (isAdmin && !isTraveller) { 
                            showAlert("Access Denied", "This account is identified as an Admin. Please use the 'Sign In as Admin' button to log in.", Alert.AlertType.ERROR);
                            return;
                        } else if (isTraveller && isAdmin) { 
                            showAlert("Role Ambiguity", "This account has both Traveller and Admin roles. Proceeding as Traveller. Please use 'Sign In as Admin' for administrative access.", Alert.AlertType.WARNING);
                            userRole = "user";
                        } else { 
                            showAlert("Profile Missing", "Your account is authenticated but no profile found. Please complete your profile.", Alert.AlertType.INFORMATION);
                            userRole = "user"; 
                        }
                    } else {
                        showAlert("Login Error", "Failed to retrieve user ID from authentication. Please try again.", Alert.AlertType.ERROR);
                        return;
                    }

                    if ("user".equals(userRole)) {
                        Map<String, Object> profileData = ProfileDao.fetchProfileData("Travellers", result.localId);
                        boolean isProfileComplete = false;

                        if (profileData != null) {
                            String firstName = (String) profileData.get("firstName");
                            String lastName = (String) profileData.get("lastName");
                            String gender = (String) profileData.get("gender");
                            String address = (String) profileData.get("address");
                            String state = (String) profileData.get("state");
                            String pincode = (String) profileData.get("pincode");
                            Object dobObject = profileData.get("dob");

                            if (firstName != null && !firstName.isEmpty() && 
                                lastName != null && !lastName.isEmpty() &&
                                gender != null && !gender.isEmpty() && 
                                address != null && !address.isEmpty() && 
                                state != null && !state.isEmpty() && 
                                pincode != null && !pincode.isEmpty() && 
                                dobObject != null ) { 
                                isProfileComplete = true;
                            }
                        }

                        if (isProfileComplete) {
                            showAlert("Login Successful", "Welcome back to TirthOnGo!", Alert.AlertType.INFORMATION);
                            try {
                                Homeload userDashboard = new Homeload(); 
                                userDashboard.setLoggedInUserUid(result.localId);
                                userDashboard.setUserEmail(result.email);
                                userDashboard.setUserRole("user"); 
                                Scene dashboardScene = userDashboard.gethomescene(primaryStage); 
                                primaryStage.setScene(dashboardScene);
                                primaryStage.setTitle("TirthOnGo - User Dashboard");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                showAlert("Navigation Error", "Login successful, but failed to load dashboard.", Alert.AlertType.ERROR);
                            }
                        } else {
                            showAlert("Profile Incomplete", "Please complete your profile to continue.", Alert.AlertType.WARNING);
                            UserProfile userp = new UserProfile();
                            userp.setLoggedInUserUid(result.localId);
                            userp.setUserEmail(result.email);
                            userp.setUserRole("user");
                            Scene profileScene = userp.getProfilScene(primaryStage);
                            primaryStage.setScene(profileScene);
                            primaryStage.setTitle("TirthOnGo - Complete Profile");
                        }
                    } else {
                        showAlert("Login Failed", "Account could not be validated for Traveller access.", Alert.AlertType.ERROR);
                    }
                } else {
                    showAlert("Login Failed", result.errorMessage, Alert.AlertType.ERROR);
                }
            });

            return null;
        }
    };

    

    Thread loginThread = new Thread(loginTask);
    loginThread.setDaemon(true);
    loginThread.start();
 });

 StackPane loginButtonStack = new StackPane();
loginButtonStack.getChildren().addAll(loginBtn, loader);

        
        // Add hover effect
        loginBtn.setOnMouseEntered(e -> loginBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                            "-fx-text-fill: black; " +
                                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                            "-fx-font-weight: bold; " +
                                            "-fx-font-size: 15px; " +
                                            "-fx-background-radius: 10; " +
                                            "-fx-cursor: hand; " +
                                            "-fx-scale-y: 1.05;"));

        loginBtn.setOnMouseExited(e -> loginBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                            "-fx-text-fill: black; " +
                                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                                            "-fx-font-weight: bold; " +
                                            "-fx-font-size: 15px; " +
                                            "-fx-background-radius: 10; " +
                                            "-fx-cursor: hand; " +
                                            "-fx-scale-y: 1.0;"));

        //////////////////////////////////////////////////////////////////////////////////
        



         Button loginBtn2 = new Button("Sign In as Admin");
        loginBtn2.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);" + 
                            "-fx-text-fill: black;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-size: 15px; " +
                            "-fx-background-radius: 10; " +
                            "-fx-cursor: hand;");
        loginBtn2.setPrefWidth(150);
        loginBtn2.setPrefHeight(50);
        loginBtn2.setMinHeight(50);
        loginBtn2.setMaxHeight(50);

        
        ProgressIndicator loader2 = new ProgressIndicator();
        loader2.setMaxSize(20, 20);
        loader2.setVisible(false); // Initially hidden


        // // Action for "Sign In as Admin" button (loginBtn2)
        // // Action for "Sign In as Admin" button (loginBtn2)
        // loginBtn2.setOnAction(e -> {
        //     validateEmail.run();
        //     validatePassword.run();
            
        //     String email = emailField.getText().trim();
        //     String password = passwordField.getText();
            
        //     boolean isEmailValid = !email.isEmpty() && !emailErrorLabel.isVisible();
        //     boolean isPasswordValid = isValidPassword(password) && !passwordErrorLabel.isVisible();
            
        //     if (!isEmailValid || !isPasswordValid) {
        //         showAlert("Validation Error", "Please correct the errors in the form before proceeding.", Alert.AlertType.ERROR);
        //         return;
        //     }
            
        //     LoginController.LoginResult result = LoginController.signInWithEmailAndPassword(email, password);

        //     if (result.success) {
        //         // --- ADD THESE PRINT STATEMENTS ---
        //         AdminProfileModel adminProfile = AdminDao.getAdminProfileModel(email);
        //         System.out.println(adminProfile.getEmail());
        //         System.out.println(adminProfile.getFirstName());
        //         AdminDashboardPage.setAdminProfile(adminProfile);

        //         System.out.println("--- Login Success Debugging ---");
        //         System.out.println("Logged in UID from Firebase Auth: " + result.localId);
        //         System.out.println("Logged in Email from Firebase Auth: " + result.email);
        //         if (result.localId == null || result.localId.isEmpty()) {
        //             System.err.println("CRITICAL: Login successful but result.localId is NULL or EMPTY!");
        //         }
        //         System.out.println("-----------------------------");
        //         String userRole = null;
        //         if (result.localId != null) {
        //             boolean isAdmin = LoginController.checkIfDocumentExists("admins", result.localId);
        //             boolean isTraveller = LoginController.checkIfDocumentExists("Travellers", result.localId);

        //             if (isAdmin && !isTraveller) { 
        //                 userRole = "admin";
        //             } else if (isTraveller && !isAdmin) { 
        //                 showAlert("Access Denied", "This account is identified as a Traveller. Please use the 'Sign In as User' button to log in.", Alert.AlertType.ERROR);
        //                 return; 
        //             } else if (isTraveller && isAdmin) { 
        //                 showAlert("Role Ambiguity", "This account has both Traveller and Admin roles. Proceeding as Admin.", Alert.AlertType.WARNING);
        //                 userRole = "admin";
        //             } else { 
        //                 // Authenticated but no profile document found, assume admin and force profile setup
        //                 showAlert("Profile Missing", "Your account is authenticated but no admin profile found. Please complete your admin profile.", Alert.AlertType.INFORMATION);
        //                 userRole = "admin";
        //             }
        //         } else {
        //             showAlert("Login Error", "Failed to retrieve user ID from authentication. Please try again.", Alert.AlertType.ERROR);
        //             return;
        //         }

        //         // If the determined role is "admin", proceed with checking profile completion and navigation
        //         if ("admin".equals(userRole)) {
        //             // --- NEW: Fetch admin profile data to check for completion ---
        //             Map<String, Object> adminProfileData = ProfileDao.fetchProfileData("admins", result.localId);
        //             boolean isAdminProfileComplete = false;
        //             if (adminProfileData != null) {
        //                 // Define what constitutes a "complete" admin profile.
        //                 // You can add more checks for specific admin fields like mobile, GST, office location.
        //                 String firstName = (String) adminProfileData.get("firstName");
        //                 String lastName = (String) adminProfileData.get("lastName");
        //                 String mobile = (String) adminProfileData.get("mobile");
        //                 // String gstNumber = (String) adminProfileData.get("gstNumber"); // Example for optional fields
        //                 // String officeLocation = (String) adminProfileData.get("officeLocation"); // Example

        //                 if (firstName != null && !firstName.isEmpty() && 
        //                     lastName != null && !lastName.isEmpty() &&
        //                     mobile != null && !mobile.isEmpty() ) { // Example: firstName, lastName, and mobile are mandatory
        //                     isAdminProfileComplete = true;
        //                 }
        //             }

        //             if (isAdminProfileComplete) {
        //                 // Admin profile is complete, navigate to Admin Dashboard
        //                 showAlert("Login Successful", "Welcome back, Admin!", Alert.AlertType.INFORMATION);
        //                 try {
        //                     AdminDashboardPage adminDashboard = new AdminDashboardPage();
        //                     adminDashboard.setLoggedInAdminUid(result.localId);
        //                     adminDashboard.setAdminEmail(result.email);
        //                     adminDashboard.setAdminRole("admin");
        //                     Scene adminDashboardScene = adminDashboard.getAdminDashboardScene(primaryStage);
        //                     primaryStage.setScene(adminDashboardScene);
        //                     primaryStage.setTitle("TirthOnGo - Admin Dashboard");
        //                 } catch (Exception ex) {
        //                     System.err.println("Error navigating to Admin Dashboard: " + ex.getMessage());
        //                     ex.printStackTrace();
        //                     showAlert("Navigation Error", "Login successful, but failed to load admin dashboard.", Alert.AlertType.ERROR);
        //                 }
        //             } else {
        //                 // Admin profile is incomplete, direct to AdminProfile setup page
        //                 showAlert("Admin Profile Incomplete", "Please complete your admin profile to continue.", Alert.AlertType.WARNING);
        //                 AdminProfile adminp = new AdminProfile();
        //                 adminp.setLoggedInAdminUid(result.localId);
        //                 adminp.setAdminEmail(result.email);
        //                 adminp.setAdminRole("admin");
        //                 Scene adminProfileScene;
        //                 try {
        //                     adminProfileScene = adminp.getAdminProfilScene(primaryStage); // Assuming getAdminProfilScene exists
        //                     primaryStage.setScene(adminProfileScene);
        //                     primaryStage.setTitle("TirthOnGo - Complete Admin Profile");
        //                 } catch (Exception e1) {
        //                     e1.printStackTrace();
        //                     showAlert("Error", "Could not load Admin Profile setup.", Alert.AlertType.ERROR);
        //                 }
        //             }

        //         } else {
        //             // This `else` block catches scenarios where the `userRole` was not "admin" (e.g., "user" and denied above)
        //             // or where the role could not be determined for some other reason.
        //             System.err.println("Login failed or role not matched for admin login button. Determined role: " + userRole);
        //             showAlert("Login Failed", "Account could not be validated for Admin access.", Alert.AlertType.ERROR);
        //         }

        //     } else {
        //         // Firebase Authentication failed
        //         showAlert("Login Failed", result.errorMessage, Alert.AlertType.ERROR);
        //     }
        // });



        // Action for "Sign In as Admin" button (loginBtn2)
loginBtn2.setOnAction(e -> {
    validateEmail.run();
    validatePassword.run();

    String email = emailField.getText().trim();
    String password = passwordField.getText();

    System.out.println("DEBUG: emailField.getText() value BEFORE LoginController call: '" + email + "'");

    boolean isEmailValid = !email.isEmpty() && !emailErrorLabel.isVisible();
    boolean isPasswordValid = isValidPassword(password) && !passwordErrorLabel.isVisible();

    if (!isEmailValid || !isPasswordValid) {
        showAlert("Validation Error", "Please correct the errors in the form before proceeding.", Alert.AlertType.ERROR);
        return;
    }

    loader.setVisible(true); // Show loader before login starts

    Task<Void> adminLoginTask = new Task<Void>() {
        @Override
        protected Void call() {
            LoginController.LoginResult result = LoginController.signInWithEmailAndPassword(email, password);

            Platform.runLater(() -> {
                loader.setVisible(false); // Hide loader after login finishes

                if (result.success) {
                    System.out.println("--- LoginPage Debugging result object ---");
                    System.out.println("Logged in UID from Firebase Auth: " + result.localId);
                    System.out.println("Logged in Email from Firebase Auth: " + result.email);
                    if (result.localId == null || result.localId.isEmpty()) {
                        System.err.println("CRITICAL: Login successful but result.localId is NULL or EMPTY!");
                    }
                    System.out.println("-----------------------------");

                    String userRole = null;
                    AdminProfileModel adminProfile = null;
                    if (result.localId != null) {
                        adminProfile = ProfileDao.getAdminProfile(result.localId);
                    }

                    if (adminProfile != null) {
                        System.out.println("DEBUG: Fetched AdminProfileModel Email: " + adminProfile.getEmail());
                        System.out.println("DEBUG: Fetched AdminProfileModel FirstName: " + adminProfile.getFirstName());
                    } else {
                        System.out.println("DEBUG: AdminProfileModel not found or null for UID: " + result.localId);
                    }

                    AdminDashboardPage.setAdminProfile(adminProfile);

                    boolean isAdmin = (adminProfile != null);
                    boolean isTraveller = LoginController.checkIfDocumentExists("Travellers", result.localId);

                    if (isAdmin && !isTraveller) {
                        userRole = "admin";
                    } else if (isTraveller && !isAdmin) {
                        showAlert("Access Denied", "This account is identified as a Traveller. Please use the 'Sign In as User' button to log in.", Alert.AlertType.ERROR);
                        return;
                    } else if (isTraveller && isAdmin) {
                        showAlert("Role Ambiguity", "This account has both Traveller and Admin roles. Proceeding as Admin.", Alert.AlertType.WARNING);
                        userRole = "admin";
                    } else {
                        showAlert("Profile Missing", "Your account is authenticated but no admin profile found. Please complete your admin profile.", Alert.AlertType.INFORMATION);
                        userRole = "admin";
                    }

                    if ("admin".equals(userRole)) {
                        boolean isAdminProfileComplete = false;
                        if (adminProfile != null) {
                            if (adminProfile.getFirstName() != null && !adminProfile.getFirstName().isEmpty() &&
                                adminProfile.getLastName() != null && !adminProfile.getLastName().isEmpty() &&
                                adminProfile.getMobile() != null && !adminProfile.getMobile().isEmpty()) {
                                isAdminProfileComplete = true;
                            }
                        }

                        if (isAdminProfileComplete) {
                            showAlert("Login Successful", "Welcome back, Admin!", Alert.AlertType.INFORMATION);
                            try {
                                AdminDashboardPage adminDashboard = new AdminDashboardPage();
                                adminDashboard.setAdminProfile(adminProfile);
                                adminDashboard.setLoggedInAdminUid(adminProfile.getDocumentId());
                                adminDashboard.setAdminEmail(adminProfile.getEmail());
                                adminDashboard.setAdminRole("admin");

                                Scene adminDashboardScene = adminDashboard.getAdminDashboardScene(primaryStage);
                                primaryStage.setScene(adminDashboardScene);
                                primaryStage.setTitle("TirthOnGo - Admin Dashboard");
                            } catch (Exception ex) {
                                System.err.println("Error navigating to Admin Dashboard: " + ex.getMessage());
                                ex.printStackTrace();
                                showAlert("Navigation Error", "Login successful, but failed to load admin dashboard.", Alert.AlertType.ERROR);
                            }
                        } else {
                            showAlert("Admin Profile Incomplete", "Please complete your admin profile to continue.", Alert.AlertType.WARNING);
                            AdminProfile adminp = new AdminProfile();
                            adminp.setLoggedInAdminUid(result.localId);
                            adminp.setAdminEmail(result.email);
                            adminp.setAdminRole("admin");
                            try {
                                Scene adminProfileScene = adminp.getAdminProfilScene(primaryStage);
                                primaryStage.setScene(adminProfileScene);
                                primaryStage.setTitle("TirthOnGo - Complete Admin Profile");
                            } catch (Exception e1) {
                                e1.printStackTrace();
                                showAlert("Error", "Could not load Admin Profile setup.", Alert.AlertType.ERROR);
                            }
                        }
                    } else {
                        System.err.println("Login failed or role not matched for admin login button. Determined role: " + userRole);
                        showAlert("Login Failed", "Account could not be validated for Admin access.", Alert.AlertType.ERROR);
                    }
                } else {
                    showAlert("Login Failed", result.errorMessage, Alert.AlertType.ERROR);
                }
            });
            return null;
        }
    };

    Thread loginThread = new Thread(adminLoginTask);
    loginThread.setDaemon(true);
    loginThread.start();
});

        StackPane loginButtonStack2 = new StackPane();
        loginButtonStack2.getChildren().addAll(loginBtn2, loader2);


        // Add hover effect
        loginBtn2.setOnMouseEntered(e -> loginBtn2.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                            "-fx-text-fill: black; " +
                                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                            "-fx-font-weight: bold; " +
                                            "-fx-font-size: 15px; " +
                                            "-fx-background-radius: 10; " +
                                            "-fx-cursor: hand; " +
                                            "-fx-scale-y: 1.05;"));

        loginBtn2.setOnMouseExited(e -> loginBtn2.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                            "-fx-text-fill: black; " +
                                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                                            "-fx-font-weight: bold; " +
                                            "-fx-font-size: 15px; " +
                                            "-fx-background-radius: 10; " +
                                            "-fx-cursor: hand; " +
                                            "-fx-scale-y: 1.0;"));


        //////////////////////////////////////////////////////////////////////////////////
        
        HBox Buttons = new HBox(20);
        Buttons.getChildren().addAll(loginButtonStack, loginButtonStack2);

        // Sign up section
        HBox signUpSection = new HBox(5);
        signUpSection.setAlignment(Pos.CENTER);

        Text signUpText = new Text("Don't have an account?");
        signUpText.setFont(Font.font("System", FontWeight.NORMAL, 14));
        signUpText.setFill(Color.web("#000000ff"));

        Hyperlink signUpLink = new Hyperlink("Sign Up Here");
        signUpLink.setStyle("-fx-text-fill: #000000ff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-underline: false;");
        signUpLink.setOnMouseEntered(e -> signUpLink.setStyle("-fx-text-fill: #000000ff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-underline: true;"));
        signUpLink.setOnMouseExited(e -> signUpLink.setStyle("-fx-text-fill: #000000ff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-underline: false;"));
        signUpLink.setOnAction(e -> {
        SignUpPage signUpPage = new SignUpPage();
        signUpPage.showSignUpPage(primaryStage);
        primaryStage.setMaximized(true);
        });

        signUpSection.getChildren().addAll(signUpText, signUpLink);

        // Required fields notice
        Label requiredNote = new Label("* Required fields");
        requiredNote.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c; -fx-font-style: italic;");

        // Enhanced form container
        VBox loginBox = new VBox(20, loginHeader, emailBox, passwordBox, optionsBox, requiredNote, Buttons, signUpSection);
        loginBox.setMaxWidth(500);
        loginBox.setPadding(new Insets(35, 40, 35, 40));
        loginBox.setAlignment(Pos.TOP_CENTER);

        // Card styling
        loginBox.setStyle(
            "-fx-background-color: linear-gradient(to left, #ffffffff, #f8c47bff) ; " +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 8);" +
            "-fx-border-color: rgba(102, 126, 234, 0.1);" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 15;"
        );

        // Left side welcome container
        VBox welcomeContainer = new VBox(30);
        welcomeContainer.getChildren().addAll(heading, content);
        welcomeContainer.setAlignment(Pos.CENTER_LEFT);
        welcomeContainer.setPadding(new Insets(0, 35, 0, 50));
        welcomeContainer.setMaxWidth(500);
        welcomeContainer.setMaxHeight(200);
        welcomeContainer.setStyle("-fx-background-color: linear-gradient(to bottom, rgba(38, 38, 38, 0.7), rgba(0, 0, 0, 0.3)) ; " +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 8);" +
            "-fx-border-color: rgba(102, 126, 234, 0.1);" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 15;");

        // Right side login container
        VBox rightContainer = new VBox(loginBox);
        rightContainer.setAlignment(Pos.CENTER);
        rightContainer.setPadding(new Insets(50, 80, 50, 30));
        rightContainer.setMaxWidth(500);

        // Main horizontal layout
        HBox mainLayout = new HBox(100);
        mainLayout.getChildren().addAll(welcomeContainer, rightContainer);
        mainLayout.setAlignment(Pos.CENTER);

        // Create background with image
        BackgroundImage backgroundImage = null;
        try {
            Image bgImage = new Image("Assets\\Images\\image1.jpg", 1200, 600, false, true);
            BackgroundPosition backgroundPosition = new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true);
            BackgroundRepeat backgroundRepeat = BackgroundRepeat.NO_REPEAT;
            BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, false, true);
            backgroundImage = new BackgroundImage(bgImage, backgroundRepeat, backgroundRepeat, backgroundPosition, backgroundSize);
        } catch (Exception e) {
            System.out.println("Background image not found, using gradient background");
        }

        // Root container with background
        StackPane root = new StackPane();
        
        if (backgroundImage != null) {
            root.setBackground(new Background(backgroundImage));
        } else {
            root.setStyle("-fx-background-color: linear-gradient(135deg, #667eea 0%, #764ba2 100%);");
        }
        
        // Add overlay
        VBox overlay = new VBox();
        overlay.setStyle("-fx-background-color: rgba(255, 255, 255, 0.1);");
        overlay.getChildren().add(mainLayout);
        overlay.setAlignment(Pos.CENTER);
        
        root.getChildren().addAll(overlay);

        Scene scene = new Scene(root, 1300, 700);
        primaryStage.setTitle("TirthOnGo - Login");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Makes the window fill the screen
        primaryStage.show();
    }

    // Helper method to validate password
    private boolean isValidPassword(String password) {
        return password.length() >= 8 &&
               UPPERCASE_PATTERN.matcher(password).matches() &&
               LOWERCASE_PATTERN.matcher(password).matches() &&
               DIGIT_PATTERN.matcher(password).matches() &&
               SPECIAL_CHAR_PATTERN.matcher(password).matches();
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


package com.tirthongo.view;

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
import java.util.regex.Pattern;
import com.tirthongo.controller.SignUpController;

public class SignUpPage {
    private String email;
    private String password;
    private String confirmPassword;

    // Email validation pattern
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );

    // Password validation patterns
    private static final Pattern UPPERCASE_PATTERN = Pattern.compile(".*[A-Z].*");
    private static final Pattern LOWERCASE_PATTERN = Pattern.compile(".*[a-z].*");
    private static final Pattern DIGIT_PATTERN = Pattern.compile(".*\\d.*");
    private static final Pattern SPECIAL_CHAR_PATTERN = Pattern.compile(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*");

    public void showSignUpPage(Stage primaryStage) {
        // Welcome section - Left side
        Label heading = new Label("Join TirthOnGo Today");
        heading.setStyle("-fx-font-weight: bold; -fx-font-size: 30px; -fx-text-fill: #ffffffff;");
        heading.setAlignment(Pos.CENTER_LEFT);
        
        Label content = new Label("Create your account to begin your spiritual journey with us");
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

        // Sign Up Form Section Header
        Label signUpHeader = new Label("Create Your Account");
        signUpHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #34495e; -fx-padding: 0 0 10 0;");

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
        PasswordField passwordField = new PasswordField();
        passwordField.setStyle(fieldStyle);
        passwordField.setPromptText("Enter your password");
        
        // Password error message
        Label passwordErrorLabel = new Label();
        passwordErrorLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 12px;");
        passwordErrorLabel.setVisible(false);

        // Password requirements info
        Label passwordRequirements = new Label("Password must contain:");
        passwordRequirements.setStyle("-fx-text-fill: #7f8c8d; -fx-font-size: 11px;");
        passwordRequirements.setVisible(false);

        // Confirm Password Field with validation
        Label confirmPasswordLabel = new Label("Confirm Password *");
        confirmPasswordLabel.setStyle(labelStyle);
        PasswordField confirmPasswordField = new PasswordField();
        confirmPasswordField.setStyle(fieldStyle);
        confirmPasswordField.setPromptText("Re-enter your password");
        
        // Confirm Password error message
        Label confirmPasswordErrorLabel = new Label();
        confirmPasswordErrorLabel.setStyle("-fx-text-fill: #e74c3c; -fx-font-size: 12px;");
        confirmPasswordErrorLabel.setVisible(false);

        // Validation methods
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

        Runnable validatePassword = () -> {
            String password = passwordField.getText();
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
                passwordErrorLabel.setText(errorMessage.toString().trim());
                passwordErrorLabel.setVisible(true);
                passwordField.setStyle(errorFieldStyle);
            } else {
                passwordErrorLabel.setVisible(false);
                passwordField.setStyle(successFieldStyle);
            }
        };

        Runnable validateConfirmPassword = () -> {
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
            
            if (confirmPassword.isEmpty()) {
                confirmPasswordErrorLabel.setText("Please confirm your password");
                confirmPasswordErrorLabel.setVisible(true);
                confirmPasswordField.setStyle(errorFieldStyle);
            } else if (!password.equals(confirmPassword)) {
                confirmPasswordErrorLabel.setText("Passwords do not match");
                confirmPasswordErrorLabel.setVisible(true);
                confirmPasswordField.setStyle(errorFieldStyle);
            } else {
                confirmPasswordErrorLabel.setVisible(false);
                confirmPasswordField.setStyle(successFieldStyle);
            }
        };

        // Real-time validation listeners
        emailField.textProperty().addListener((observable, oldValue, newValue) -> validateEmail.run());
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            validatePassword.run();
            if (!confirmPasswordField.getText().isEmpty()) {
                validateConfirmPassword.run();
            }
        });
        confirmPasswordField.textProperty().addListener((observable, oldValue, newValue) -> validateConfirmPassword.run());

        // Focus effects with validation
        emailField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                emailField.setStyle(fieldStyle + "-fx-border-color: #f9970dff; -fx-border-width: 2;");
            } else {
                validateEmail.run();
            }
        });

        passwordField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                passwordField.setStyle(fieldStyle + "-fx-border-color: #f9970dff; -fx-border-width: 2;");
                passwordRequirements.setVisible(true);
            } else {
                passwordRequirements.setVisible(false);
                validatePassword.run();
            }
        });

        confirmPasswordField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                confirmPasswordField.setStyle(fieldStyle + "-fx-border-color: #f9970dff; -fx-border-width: 2;");
            } else {
                validateConfirmPassword.run();
            }
        });

       

        // Form layout with error messages
        VBox emailBox = new VBox(emailLabel, emailField, emailErrorLabel);
        emailBox.setPrefWidth(300);
        
        VBox passwordBox = new VBox( passwordLabel, passwordField, passwordRequirements, passwordErrorLabel);
        passwordBox.setPrefWidth(300);

        VBox confirmPasswordBox = new VBox( confirmPasswordLabel, confirmPasswordField, confirmPasswordErrorLabel);
        confirmPasswordBox.setPrefWidth(300);

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // Enhanced Register button for user
        Button registerBtn = new Button("Sign Up as User");
        registerBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);" + 
                            "-fx-text-fill: black;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-size: 15px; " +
                            "-fx-background-radius: 10; " +
                            "-fx-cursor: hand;");
        registerBtn.setPrefWidth(150);
        registerBtn.setPrefHeight(50);
        registerBtn.setMinHeight(50);
        registerBtn.setMaxHeight(50);

        Text output = new Text();

        // Register button validation and action
        registerBtn.setOnAction(e -> {
            // Run all validations
            validateEmail.run();
            validatePassword.run();
            validateConfirmPassword.run();
     
            
            this.email = emailField.getText().trim();
            this.password = passwordField.getText();
            this.confirmPassword = confirmPasswordField.getText();
            
            
            // Check if all fields are valid
            boolean isEmailValid = EMAIL_PATTERN.matcher(email).matches() && !email.isEmpty();
            boolean isPasswordValid = isValidPassword(password);
            boolean isConfirmPasswordValid = password.equals(confirmPassword) && !confirmPassword.isEmpty();
           
            
            if (!isEmailValid || !isPasswordValid || !isConfirmPasswordValid ) {
                showAlert("Validation Error", "Please correct the errors in the form before proceeding.", Alert.AlertType.ERROR);
                return;
            }else {// If validation passes, proceed with registration
            showAlert("Registration Successful", "Welcome to TirthOnGo! You can now sign in.", Alert.AlertType.INFORMATION);
            boolean success = SignUpController.signUpWithEmailAndPasswordUser(email, password);
            if(success){
                output.setText("login success");
            }else{
                 output.setText("invalid username or password");
            }
            }
            // Navigate back to login page
            LoginPage loginPage = new LoginPage();
            loginPage.start(primaryStage);
        });

        // Add hover effect to register button
        registerBtn.setOnMouseEntered(e -> registerBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                               "-fx-text-fill: black; " +
                                                               "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                                               "-fx-font-weight: bold; " +
                                                               "-fx-font-size: 15px; " +
                                                               "-fx-background-radius: 10; " +
                                                               "-fx-cursor: hand; " +
                                                               "-fx-scale-y: 1.05;"));

        registerBtn.setOnMouseExited(e -> registerBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                              "-fx-text-fill: black; " +
                                                              "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                                                              "-fx-font-weight: bold; " +
                                                              "-fx-font-size: 15px; " +
                                                              "-fx-background-radius: 10; " +
                                                              "-fx-cursor: hand; " +
                                                              "-fx-scale-y: 1.0;"));
        
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        

         // Enhanced Register button for admin
        Button registerBtn2 = new Button("Sign Up as Admin");
        registerBtn2.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);" + 
                            "-fx-text-fill: black;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                            "-fx-font-weight: bold; " +
                            "-fx-font-size: 15px; " +
                            "-fx-background-radius: 10; " +
                            "-fx-cursor: hand;");
        registerBtn2.setPrefWidth(150);
        registerBtn2.setPrefHeight(50);
        registerBtn2.setMinHeight(50);
        registerBtn2.setMaxHeight(50);

        Text result = new Text();

        // Register button validation and action
        registerBtn2.setOnAction(e -> {
            // Run all validations
            validateEmail.run();
            validatePassword.run();
            validateConfirmPassword.run();
     
            
            this.email = emailField.getText().trim();
            this.password = passwordField.getText();
            this.confirmPassword = confirmPasswordField.getText();
            
            
            // Check if all fields are valid
            boolean isEmailValid = EMAIL_PATTERN.matcher(email).matches() && !email.isEmpty();
            boolean isPasswordValid = isValidPassword(password);
            boolean isConfirmPasswordValid = password.equals(confirmPassword) && !confirmPassword.isEmpty();
           
            
            if (!isEmailValid || !isPasswordValid || !isConfirmPasswordValid ) {
                showAlert("Validation Error", "Please correct the errors in the form before proceeding.", Alert.AlertType.ERROR);
                return;
            }else {// If validation passes, proceed with registration
            showAlert("Registration Successful", "Welcome to TirthOnGo! You can now sign in.", Alert.AlertType.INFORMATION);
            boolean success = SignUpController.signUpAdminWithEmailAndPasswordAdmin(email, password);
            if(success){
                result.setText("login success");
            }else{
                 result.setText("invalid username or password");
            }
            }
            // Navigate back to login page
            LoginPage loginPage = new LoginPage();
            loginPage.start(primaryStage);
        });

        // Add hover effect to register button
        registerBtn2.setOnMouseEntered(e -> registerBtn2.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                               "-fx-text-fill: black; " +
                                                               "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                                               "-fx-font-weight: bold; " +
                                                               "-fx-font-size: 15px; " +
                                                               "-fx-background-radius: 10; " +
                                                               "-fx-cursor: hand; " +
                                                               "-fx-scale-y: 1.05;"));

        registerBtn2.setOnMouseExited(e -> registerBtn2.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                              "-fx-text-fill: black; " +
                                                              "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                                                              "-fx-font-weight: bold; " +
                                                              "-fx-font-size: 15px; " +
                                                              "-fx-background-radius: 10; " +
                                                              "-fx-cursor: hand; " +
                                                              "-fx-scale-y: 1.0;"));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                                                      

        HBox Buttons = new HBox(20);
        Buttons.getChildren().addAll(registerBtn, registerBtn2);

        // Back to Login section
        HBox loginSection = new HBox(5);
        loginSection.setAlignment(Pos.CENTER);

        Text loginText = new Text("Already have an account?");
        loginText.setFont(Font.font("System", FontWeight.NORMAL, 14));
        loginText.setFill(Color.web("#000000ff"));

        Hyperlink loginLink = new Hyperlink("Sign In Here");
        loginLink.setStyle("-fx-text-fill: #000000ff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-underline: false;");
        loginLink.setOnMouseEntered(e -> loginLink.setStyle("-fx-text-fill: #000000ff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-underline: true;"));
        loginLink.setOnMouseExited(e -> loginLink.setStyle("-fx-text-fill: #000000ff; -fx-font-size: 14px; -fx-font-weight: bold; -fx-underline: false;"));
        loginLink.setOnAction(e -> {
            LoginPage loginPage = new LoginPage();
            loginPage.start(primaryStage);
        });

        loginSection.getChildren().addAll(loginText, loginLink);

        // Required fields notice
        Label requiredNote = new Label("* Required fields");
        requiredNote.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c; -fx-font-style: italic;");

        // Enhanced form container
        VBox signUpBox = new VBox(10,signUpHeader, emailBox, passwordBox, confirmPasswordBox, requiredNote, Buttons, loginSection);
        signUpBox.setMaxWidth(500);
        signUpBox.setPadding(new Insets(20, 40, 20, 40));
        signUpBox.setAlignment(Pos.TOP_CENTER);

        // ScrollPane scrollPane = new ScrollPane(signUpBox);
        // scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        // scrollPane.setStyle(
        //     "-fx-background-color: linear-gradient(to left, #ffffffff, #f8c47bff) ; " +
        //     "-fx-background-radius: 15;" +
        //     "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 8);" +
        //     "-fx-border-color: rgba(102, 126, 234, 0.1);" +
        //     "-fx-border-width: 1;" +
        //     "-fx-border-radius: 15;"
        // );


        //Card styling matching LoginPage
        signUpBox.setStyle(
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

        // Right side sign up container
        VBox rightContainer = new VBox(signUpBox);
        rightContainer.setAlignment(Pos.CENTER);
        rightContainer.setMaxWidth(500);

        // Main horizontal layout
        HBox mainLayout = new HBox(100);
        mainLayout.getChildren().addAll(welcomeContainer, rightContainer);
        mainLayout.setAlignment(Pos.CENTER);
        rightContainer.setPadding(new Insets(30, 0, 30, 0));

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

        Scene scene = new Scene(root,1300, 700);
        primaryStage.setTitle("Sign Up - TirthOnGo");
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
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

    // Getters and setters
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getConfirmPassword() { return confirmPassword; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setConfirmPassword(String confirmPassword) { this.confirmPassword = confirmPassword; }
}
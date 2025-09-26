// package com.tirthongo.view;


// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.*;
// import javafx.scene.layout.*;
// import javafx.stage.Stage;

// import java.util.Optional;

// import javafx.animation.KeyFrame;
// import javafx.animation.ParallelTransition;
// import javafx.animation.ScaleTransition;
// import javafx.animation.Timeline;
// import javafx.animation.TranslateTransition;
// import javafx.application.Platform;
// import javafx.util.Duration;

// public class PaymentGateway {
    
//     private VBox mainContainer;
//     private VBox upiSection;
//     private VBox cardSection;
//     private RadioButton upiRadio;
//     private RadioButton cardRadio;
//     private TextField upiIdField;
//     private TextField cardNumberField;
//     private TextField cardHolderNameField;
//     private TextField expiryMonthField;
//     private TextField expiryYearField;
//     private TextField cvvField;
//     private PasswordField pinField;
//     private Label amountLabel;
//     private Button proceedButton;
//     private Button backButton;
//     private ProgressIndicator progressIndicator;
    
//     private double totalAmount;
//     private Stage primaryStage;
//     private Scene previousScene; // To navigate back
    
//     public PaymentGateway(Stage primaryStage, double amount) {
//         this.primaryStage = primaryStage;
//         this.totalAmount = amount;
//         createMainLayout();
//     }
    
//     // Constructor with previous scene for navigation
//     public PaymentGateway(Stage primaryStage, double amount, Scene previousScene) {
//         this.primaryStage = primaryStage;
//         this.totalAmount = amount;
//         this.previousScene = previousScene;
//         createMainLayout();
        
//     }
    
//     public Scene getScene() {
//     // Create a container to center the main content
//     HBox centerContainer = new HBox();
//     centerContainer.setAlignment(Pos.CENTER);
//     centerContainer.setPadding(new Insets(20));
//     centerContainer.setStyle("-fx-background-color: #ffffffff;");
    
//     // Set the main container properties
//     mainContainer.setMaxWidth(600);
//     mainContainer.setPrefWidth(600);
    
//     // Add main container to center container
//     centerContainer.getChildren().add(mainContainer);
    
//     // Create ScrollPane with the centered content
//     ScrollPane scrollPane = new ScrollPane(centerContainer);
//     scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//     scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//     scrollPane.setFitToWidth(true);
//     scrollPane.setStyle("-fx-background: #ffffffff; -fx-background-color: #f5f5f5;");
    
//     // Create scene with proper dimensions
//     Scene scene = new Scene(scrollPane);
//     primaryStage.setMaximized(true);
//     return scene;
// }
    
//     public VBox getRoot() {
//         return mainContainer;
//     }
    
//     private void createMainLayout() {
//         mainContainer = new VBox(10);
//         mainContainer.setPadding(new Insets(20));
//         mainContainer.setStyle("-fx-background-color: transparent;");
//         mainContainer.setAlignment(Pos.TOP_CENTER);
        
//         // Payment amount section
//         createAmountSection();
        
//         // Payment method selection
//         createPaymentMethodSelection();
        
//         // UPI Section
//         createUPISection();
        
//         // Card Section
//         createCardSection();
        
//         // PIN Section
//         createPINSection();
        
//         // Buttons section
//         createButtonsSection();
        
//         // Progress indicator (initially hidden)
//         createProgressIndicator();
        
//         // Set initial visibility
//         updateSectionVisibility();
//     }

//     private void createAmountSection() {
//         VBox amountBox = new VBox(10);
//         amountBox.setPadding(new Insets(15));
//         amountBox.setMaxWidth(Double.MAX_VALUE);
//         amountBox.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        
//         Label amountTitle = new Label("Payment Details");
//         amountTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
//         HBox amountRow = new HBox();
//         amountRow.setAlignment(Pos.CENTER_LEFT);
//         Label itemLabel = new Label("Pilgrimage Package:");
//         itemLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
//         Region spacer = new Region();
//         HBox.setHgrow(spacer, Priority.ALWAYS);
        
//         amountLabel = new Label("₹" + String.format("%.2f", totalAmount));
//         amountLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #4a6fa5;");
        
//         amountRow.getChildren().addAll(itemLabel, spacer, amountLabel);
        
//         amountBox.getChildren().addAll(amountTitle, new Separator(), amountRow);
//         mainContainer.getChildren().add(amountBox);
//     }
    
//     private void createPaymentMethodSelection() {
//         VBox methodBox = new VBox(10);
//         methodBox.setPadding(new Insets(15));
//         methodBox.setMaxWidth(Double.MAX_VALUE);
//         methodBox.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        
//         Label methodTitle = new Label("Select Payment Method");
//         methodTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
//         ToggleGroup paymentGroup = new ToggleGroup();
        
//         upiRadio = new RadioButton("UPI Payment");
//         upiRadio.setToggleGroup(paymentGroup);
//         upiRadio.setSelected(true);
//         upiRadio.setStyle("-fx-font-size: 14px;");
        
//         cardRadio = new RadioButton("Credit/Debit Card");
//         cardRadio.setToggleGroup(paymentGroup);
//         cardRadio.setStyle("-fx-font-size: 14px;");
        
//         // Event handlers for radio buttons
//         upiRadio.setOnAction(e -> updateSectionVisibility());
//         cardRadio.setOnAction(e -> updateSectionVisibility());
        
//         methodBox.getChildren().addAll(methodTitle, new Separator(), upiRadio, cardRadio);
//         mainContainer.getChildren().add(methodBox);
//     }
    
//     private void createUPISection() {
//         upiSection = new VBox(10);
//         upiSection.setMaxWidth(Double.MAX_VALUE);
//         upiSection.setPadding(new Insets(15));
//         upiSection.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        
//         Label upiTitle = new Label("UPI Details");
//         upiTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
//         Label upiIdLabel = new Label("UPI ID:");
//         upiIdLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
//         upiIdField = new TextField();
//         upiIdField.setPromptText("Enter your UPI ID (e.g., user@paytm)");
//         upiIdField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
//         // Add UPI validation
//         upiIdField.textProperty().addListener((obs, oldText, newText) -> {
//             if (newText.contains("@") && newText.length() > 5) {
//                 upiIdField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px; -fx-border-color: green;");
//             } else {
//                 upiIdField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px; -fx-border-color: red;");
//             }
//         });
        
//         upiSection.getChildren().addAll(upiTitle, new Separator(), upiIdLabel, upiIdField);
//         mainContainer.getChildren().add(upiSection);
//     }
    
//     private void createCardSection() {
//         cardSection = new VBox(10);
//         cardSection.setPadding(new Insets(15));
//         cardSection.setMaxWidth(Double.MAX_VALUE);
//         cardSection.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        
//         Label cardTitle = new Label("Card Details");
//         cardTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
//         // Card holder name
//         Label nameLabel = new Label("Card Holder Name:");
//         nameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
//         cardHolderNameField = new TextField();
//         cardHolderNameField.setPromptText("Enter cardholder name");
//         cardHolderNameField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
//         // Card number
//         Label cardNumberLabel = new Label("Card Number:");
//         cardNumberLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
//         cardNumberField = new TextField();
//         cardNumberField.setPromptText("1234 5678 9012 3456");
//         cardNumberField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
//         // Format card number input
//         cardNumberField.textProperty().addListener((obs, oldText, newText) -> {
//             if (newText.length() > 19) return; // Prevent input beyond 16 digits + 3 spaces
            
//             String formatted = newText.replaceAll("[^\\d]", "");
//             if (formatted.length() > 16) formatted = formatted.substring(0, 16);
            
//             StringBuilder sb = new StringBuilder();
//             for (int i = 0; i < formatted.length(); i++) {
//                 if (i > 0 && i % 4 == 0) sb.append(" ");
//                 sb.append(formatted.charAt(i));
//             }
            
//             if (!sb.toString().equals(newText)) {
//                 cardNumberField.setText(sb.toString());
//                 cardNumberField.positionCaret(sb.length());
//             }
//         });
        
//         // Expiry and CVV
//         HBox expiryAndCvvBox = new HBox(10);
        
//         VBox expiryBox = new VBox(5);
//         Label expiryLabel = new Label("Expiry Date:");
//         expiryLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
//         HBox expiryFields = new HBox(5);
//         expiryMonthField = new TextField();
//         expiryMonthField.setPromptText("MM");
//         expiryMonthField.setPrefWidth(60);
//         expiryMonthField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
//         Label slashLabel = new Label("/");
//         slashLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #666;");
        
//         expiryYearField = new TextField();
//         expiryYearField.setPromptText("YY");
//         expiryYearField.setPrefWidth(60);
//         expiryYearField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
//         // Limit expiry field inputs
//         expiryMonthField.textProperty().addListener((obs, oldText, newText) -> {
//             if (newText.length() > 2) expiryMonthField.setText(oldText);
//             if (!newText.matches("\\d*")) expiryMonthField.setText(oldText);
//         });
        
//         expiryYearField.textProperty().addListener((obs, oldText, newText) -> {
//             if (newText.length() > 2) expiryYearField.setText(oldText);
//             if (!newText.matches("\\d*")) expiryYearField.setText(oldText);
//         });
        
//         expiryFields.getChildren().addAll(expiryMonthField, slashLabel, expiryYearField);
//         expiryBox.getChildren().addAll(expiryLabel, expiryFields);
        
//         VBox cvvBox = new VBox(5);
//         Label cvvLabel = new Label("CVV:");
//         cvvLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
//         cvvField = new TextField();
//         cvvField.setPromptText("123");
//         cvvField.setPrefWidth(80);
//         cvvField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
//         // Limit CVV to 3 digits
//         cvvField.textProperty().addListener((obs, oldText, newText) -> {
//             if (newText.length() > 3) cvvField.setText(oldText);
//             if (!newText.matches("\\d*")) cvvField.setText(oldText);
//         });
        
//         cvvBox.getChildren().addAll(cvvLabel, cvvField);
        
//         expiryAndCvvBox.getChildren().addAll(expiryBox, cvvBox);
        
//         cardSection.getChildren().addAll(cardTitle, new Separator(), nameLabel, cardHolderNameField, 
//                                        cardNumberLabel, cardNumberField, expiryAndCvvBox);
//         mainContainer.getChildren().add(cardSection);
//     }
    
//     private void createPINSection() {
//         VBox pinSection = new VBox(10);
//         pinSection.setPadding(new Insets(15));
//         pinSection.setMaxWidth(Double.MAX_VALUE);
//         pinSection.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        
//         Label pinTitle = new Label("Enter PIN");
//         pinTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
//         Label pinLabel = new Label("UPI PIN / Card PIN:");
//         pinLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
//         pinField = new PasswordField();
//         pinField.setPromptText("Enter 4-6 digit PIN");
//         pinField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
//         // Limit PIN input
//         pinField.textProperty().addListener((obs, oldText, newText) -> {
//             if (newText.length() > 6) pinField.setText(oldText);
//             if (!newText.matches("\\d*")) pinField.setText(oldText);
//         });
        
//         pinSection.getChildren().addAll(pinTitle, new Separator(), pinLabel, pinField);
//         mainContainer.getChildren().add(pinSection);
//     }
    
//     private void createButtonsSection() {
//         HBox buttonBox = new HBox(10);
//         buttonBox.setAlignment(Pos.CENTER);
        
//         // Back button
//         backButton = new Button("BACK");
//         backButton.setPrefWidth(100);
//         backButton.setPrefHeight(45);
//         backButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");
//         backButton.setOnAction(e -> navigateBack());
        
//         // Clear/Reset button
//         Button clearButton = new Button("CLEAR");
//         clearButton.setPrefWidth(100);
//         clearButton.setPrefHeight(45);
//         clearButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");
//         clearButton.setOnAction(e -> resetForm());

//         // Proceed button
//         proceedButton = new Button("Confirm & Pay");
//         proceedButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");
//         HBox.setHgrow(proceedButton, Priority.ALWAYS);
//         proceedButton.setMaxWidth(300);
//         proceedButton.setPrefHeight(50);
        
//         // Add hover effects
//         // Back Button Enhanced Effects
//         clearButton.setOnMouseEntered(e -> {
//             // Color and style change
//             clearButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 14px; " +
//                             "-fx-font-weight: bold;  " +
                        
//                             "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Enhanced shadow
            
//             // Float up animation
//             TranslateTransition translateUp = new TranslateTransition(Duration.millis(150), clearButton);
//             translateUp.setToY(-3); // Move up by 3 pixels
            
//             // Slight scale up
//             ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), clearButton);
//             scaleUp.setToX(1.05);
//             scaleUp.setToY(1.05);
            
//             // Combine animations
//             ParallelTransition parallelTransition = new ParallelTransition(translateUp, scaleUp);
//             parallelTransition.play();
//         });

//         clearButton.setOnMouseExited(e -> {
//             // Restore original style
//             clearButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 14px; " +
//                             "-fx-font-weight: bold;  " +
                            
//                             "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Original shadow
            
//             // Float down animation
//             TranslateTransition translateDown = new TranslateTransition(Duration.millis(150), clearButton);
//             translateDown.setToY(0); // Return to original position
            
//             // Scale back to normal
//             ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), clearButton);
//             scaleDown.setToX(1.0);
//             scaleDown.setToY(1.0);
            
//             // Combine animations
//             ParallelTransition parallelTransition = new ParallelTransition(translateDown, scaleDown);
//             parallelTransition.play();
//         });

//         backButton.setOnMouseEntered(e -> {
//             // Color and style change
//             backButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 14px; " +
//                             "-fx-font-weight: bold; -fx-pref-height: 45px; -fx-pref-width: 100px; " +
                            
//                             "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Enhanced shadow
            
//             // Float up animation
//             TranslateTransition translateUp = new TranslateTransition(Duration.millis(150), backButton);
//             translateUp.setToY(-3); // Move up by 3 pixels
            
//             // Slight scale up
//             ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), backButton);
//             scaleUp.setToX(1.05);
//             scaleUp.setToY(1.05);
            
//             // Combine animations
//             ParallelTransition parallelTransition = new ParallelTransition(translateUp, scaleUp);
//             parallelTransition.play();
//         });

//         backButton.setOnMouseExited(e -> {
//             // Restore original style
//             backButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 14px; " +
//                             "-fx-font-weight: bold; -fx-pref-height: 45px; -fx-pref-width: 100px; " +
                           
//                             "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Original shadow
            
//             // Float down animation
//             TranslateTransition translateDown = new TranslateTransition(Duration.millis(150), backButton);
//             translateDown.setToY(0); // Return to original position
            
//             // Scale back to normal
//             ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), backButton);
//             scaleDown.setToX(1.0);
//             scaleDown.setToY(1.0);
            
//             // Combine animations
//             ParallelTransition parallelTransition = new ParallelTransition(translateDown, scaleDown);
//             parallelTransition.play();
//         });

//         // Proceed Button Enhanced Effects
//         proceedButton.setOnMouseEntered(e -> {
//             // Color and style change
//             proceedButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 16px; " +
//                                 "-fx-font-weight: bold; -fx-pref-height: 45px; " +
//                                 "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Blue shadow
            
//             // Float up animation
//             TranslateTransition translateUp = new TranslateTransition(Duration.millis(150), proceedButton);
//             translateUp.setToY(-4); // Move up by 4 pixels (more prominent)
            
//             // Slight scale up
//             ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), proceedButton);
//             scaleUp.setToX(1.03);
//             scaleUp.setToY(1.03);
            
//             // Combine animations
//             ParallelTransition parallelTransition = new ParallelTransition(translateUp, scaleUp);
//             parallelTransition.play();
//         });

//         proceedButton.setOnMouseExited(e -> {
//             // Restore original style
//             proceedButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 16px; " +
//                                 "-fx-font-weight: bold; -fx-pref-height: 45px;  " +
//                                 "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Original blue shadow
            
//             // Float down animation
//             TranslateTransition translateDown = new TranslateTransition(Duration.millis(150), proceedButton);
//             translateDown.setToY(0); // Return to original position
            
//             // Scale back to normal
//             ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), proceedButton);
//             scaleDown.setToX(1.0);
//             scaleDown.setToY(1.0);
            
//             // Combine animations
//             ParallelTransition parallelTransition = new ParallelTransition(translateDown, scaleDown);
//             parallelTransition.play();
//         });

        

//         // Optional: Add a subtle pulse effect on button press
//         // Add these to your buttons for even more interactivity:

//         clearButton.setOnMousePressed(e -> {
//             ScaleTransition clearscale = new ScaleTransition(Duration.millis(100), backButton);
//             clearscale.setToX(0.98);
//             clearscale.setToY(0.98);
//             clearscale.play();
//         });

//         clearButton.setOnMouseReleased(e -> {
//             ScaleTransition clearscale = new ScaleTransition(Duration.millis(100), backButton);
//             clearscale.setToX(1.05); // Back to hover scale
//             clearscale.setToY(1.05);
//             clearscale.play();
//         });

//         backButton.setOnMousePressed(e -> {
//             ScaleTransition pressScale = new ScaleTransition(Duration.millis(100), backButton);
//             pressScale.setToX(0.98);
//             pressScale.setToY(0.98);
//             pressScale.play();
//         });

//         backButton.setOnMouseReleased(e -> {
//             ScaleTransition releaseScale = new ScaleTransition(Duration.millis(100), backButton);
//             releaseScale.setToX(1.05); // Back to hover scale
//             releaseScale.setToY(1.05);
//             releaseScale.play();
//         });

//         proceedButton.setOnMousePressed(e -> {
//             ScaleTransition pressScale = new ScaleTransition(Duration.millis(100), proceedButton);
//             pressScale.setToX(0.99);
//             pressScale.setToY(0.99);
//             pressScale.play();
//         });

//         proceedButton.setOnMouseReleased(e -> {
//             ScaleTransition releaseScale = new ScaleTransition(Duration.millis(100), proceedButton);
//             releaseScale.setToX(1.03); // Back to hover scale
//             releaseScale.setToY(1.03);
//             releaseScale.play();
//         });


//         proceedButton.setOnAction(e -> processPayment());
        
//         buttonBox.getChildren().addAll(backButton, clearButton, proceedButton);
//         mainContainer.getChildren().add(buttonBox);
//     }
    
//     private void createProgressIndicator() {
//         progressIndicator = new ProgressIndicator();
//         progressIndicator.setVisible(false);
//         progressIndicator.setPrefSize(50, 50);
        
//         VBox progressBox = new VBox(10);
//         progressBox.setAlignment(Pos.CENTER);
//         progressBox.getChildren().add(progressIndicator);
        
//         mainContainer.getChildren().add(progressBox);
//     }
    
//     private void updateSectionVisibility() {
//         boolean isUPI = upiRadio.isSelected();
//         upiSection.setVisible(isUPI);
//         upiSection.setManaged(isUPI);
//         cardSection.setVisible(!isUPI);
//         cardSection.setManaged(!isUPI);
//     }
    
//     private void navigateBack() {
//         if (previousScene != null) {
//             primaryStage.setScene(previousScene);
//         } else {
//             // If no previous scene provided, you can handle this case
//             // For example, close the stage or navigate to main menu
//             showAlert("Navigation", "No previous screen to go back to.", Alert.AlertType.INFORMATION);
//         }
//     }

//     private void navigateToConfirmationPage() {
//     try {

//         // Create new instance of BookingConfirmPage
//         BookingConfirmPage confirmPage = new BookingConfirmPage();
//         Scene coScene = confirmPage.start(primaryStage); // Using getHomeScene
//         primaryStage.setScene(coScene); 
        
//     } catch (Exception ex) {
//         ex.printStackTrace();
//         showAlert("Navigation Error", "Could not open confirmation page.", Alert.AlertType.ERROR);
//     }
// }
    
//     private void processPayment() {
//     if (!validateInput()) {
//         showAlert("Validation Error", "Please fill all required fields correctly.", Alert.AlertType.ERROR);
//         return;
//     }
    
//     // Show progress
//     proceedButton.setDisable(true);
//     backButton.setDisable(true);
//     proceedButton.setText("PROCESSING...");
//     progressIndicator.setVisible(true);
    
//     // Simulate payment processing with Timeline
//     Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
//         // Ensure UI updates happen on JavaFX Application Thread
//         Platform.runLater(() -> {
//             // Hide progress indicators
//             progressIndicator.setVisible(false);
//             proceedButton.setDisable(false);
//             backButton.setDisable(false);
//             proceedButton.setText("PAY");
            
//             // Show success dialog
//             showPaymentSuccess();
//         });
//     }));
//     timeline.play();
// }
    
//    private boolean validateInput() {
//     if (upiRadio.isSelected()) {
//         return upiIdField.getText().trim().length() > 0 &&
//                upiIdField.getText().contains("@") &&
//                pinField.getText().length() >= 4;
//     } else {
//         return cardHolderNameField.getText().trim().length() > 0 &&
//                cardNumberField.getText().replaceAll("\\s", "").length() == 16 &&
//                expiryMonthField.getText().length() == 2 &&
//                expiryYearField.getText().length() == 2 &&
//                cvvField.getText().length() == 3 &&
//                pinField.getText().length() >= 4;
//     }
// }
    
//     private void showPaymentSuccess() {
//     try {
//         Alert alert = new Alert(Alert.AlertType.INFORMATION);
//         alert.setTitle("Payment Successful!");
//         alert.setHeaderText("Your payment has been processed successfully");
        
//         String paymentMethod = upiRadio.isSelected() ? 
//             "UPI ID: " + upiIdField.getText() :
//             "Card ending with: ****" + cardNumberField.getText().replaceAll("\\s", "").substring(12);
        
//         alert.setContentText("Payment Method: " + paymentMethod + "\n" +
//                            "Amount: ₹" + String.format("%.2f", totalAmount) + "\n" +
//                            "Transaction ID: TRN" + System.currentTimeMillis() + "\n" +
//                            "Status: SUCCESS\n\n" +
//                            "Thank you for choosing TirthOnGo!\n" +
//                            "Your booking has been confirmed.");
        
//         // Make alert modal and wait for user to close it
//         alert.setResizable(true);
//         alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        
//         // Show alert and wait for user response
//         Optional<ButtonType> result = alert.showAndWait();
        
//         // Only proceed after user closes the alert
//         if (result.isPresent()) {
//             // Reset form after user acknowledges success
//             resetForm();
            
//             // Navigate back only after user closes the alert
//             navigateToConfirmationPage();
//         }
        
//     } catch (Exception ex) {
//         ex.printStackTrace();
//         showAlert("Error", "An error occurred while displaying payment confirmation.", Alert.AlertType.ERROR);
//     }
// }


    
//     private void resetForm() {
//         upiIdField.clear();
//         cardHolderNameField.clear();
//         cardNumberField.clear();
//         expiryMonthField.clear();
//         expiryYearField.clear();
//         cvvField.clear();
//         pinField.clear();
//         upiRadio.setSelected(true);
//         updateSectionVisibility();
//     }
    
//     private void showAlert(String title, String message, Alert.AlertType type) {
//         Alert alert = new Alert(type);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }
    
//     // Method to update amount if needed
//     public void setAmount(double amount) {
//         this.totalAmount = amount;
//         if (amountLabel != null) {
//             amountLabel.setText("₹" + String.format("%.2f", totalAmount));
//         }
//     }
    
//     // Method to set previous scene for navigation
//     public void setPreviousScene(Scene scene) {
//         this.previousScene = scene;
//     }
// }



package com.tirthongo.view;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Optional;

import com.tirthongo.model.DestinationModel;

import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.util.Duration;

public class PaymentGateway {
    
    private VBox mainContainer;
    private VBox upiSection;
    private VBox cardSection;
    private RadioButton upiRadio;
    private RadioButton cardRadio;
    private TextField upiIdField;
    private TextField cardNumberField;
    private TextField cardHolderNameField;
    private TextField expiryMonthField;
    private TextField expiryYearField;
    private TextField cvvField;
    private PasswordField pinField;
    private Label amountLabel;
    private Button proceedButton;
    private Button backButton;
    private ProgressIndicator progressIndicator;
    
    private double totalAmount;
    private Stage primaryStage;
    private Scene previousScene; // To navigate back
      public static DestinationModel model;
    public static void setModel(DestinationModel model1){
        model=model1;
    }
    public PaymentGateway(Stage primaryStage, double amount) {
        this.primaryStage = primaryStage;
        this.totalAmount = amount;
        createMainLayout();
    }
    
    // Constructor with previous scene for navigation
    public PaymentGateway(Stage primaryStage, double amount, Scene previousScene) {
        this.primaryStage = primaryStage;
        this.totalAmount = amount;
        this.previousScene = previousScene;
        createMainLayout();
        
    }
    
    public Scene getScene() {
    // Create a container to center the main content
    HBox centerContainer = new HBox();
    centerContainer.setAlignment(Pos.CENTER);
    centerContainer.setPadding(new Insets(20));
    centerContainer.setStyle("-fx-background-color: #ffffffff;");
    
    // Set the main container properties
    mainContainer.setMaxWidth(600);
    mainContainer.setPrefWidth(600);
    
    // Add main container to center container
    centerContainer.getChildren().add(mainContainer);
    
    // Create ScrollPane with the centered content
    ScrollPane scrollPane = new ScrollPane(centerContainer);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-background: #ffffffff; -fx-background-color: #f5f5f5;");
    
    // Create scene with proper dimensions
    Scene scene = new Scene(scrollPane,1300,700);
    primaryStage.setMaximized(true);
    return scene;
}
    
    public VBox getRoot() {
        return mainContainer;
    }
    
    private void createMainLayout() {
        mainContainer = new VBox(10);
        mainContainer.setPadding(new Insets(20));
        mainContainer.setStyle("-fx-background-color: transparent;");
        mainContainer.setAlignment(Pos.TOP_CENTER);
        
        // Payment amount section
        createAmountSection();
        
        // Payment method selection
        createPaymentMethodSelection();
        
        // UPI Section
        createUPISection();
        
        // Card Section
        createCardSection();
        
        // PIN Section
        createPINSection();
        
        // Buttons section
        createButtonsSection();
        
        // Progress indicator (initially hidden)
        createProgressIndicator();
        
        // Set initial visibility
        updateSectionVisibility();
    }

    private void createAmountSection() {
        VBox amountBox = new VBox(10);
        amountBox.setPadding(new Insets(15));
        amountBox.setMaxWidth(Double.MAX_VALUE);
        amountBox.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        
        Label amountTitle = new Label("Payment Details");
        amountTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
        HBox amountRow = new HBox();
        amountRow.setAlignment(Pos.CENTER_LEFT);
        Label itemLabel = new Label("Pilgrimage Package:");
        itemLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        amountLabel = new Label("₹" + String.format("%.2f", totalAmount));
        amountLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #4a6fa5;");
        
        amountRow.getChildren().addAll(itemLabel, spacer, amountLabel);
        
        amountBox.getChildren().addAll(amountTitle, new Separator(), amountRow);
        mainContainer.getChildren().add(amountBox);
    }
    
    private void createPaymentMethodSelection() {
        VBox methodBox = new VBox(10);
        methodBox.setPadding(new Insets(15));
        methodBox.setMaxWidth(Double.MAX_VALUE);
        methodBox.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        
        Label methodTitle = new Label("Select Payment Method");
        methodTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
        ToggleGroup paymentGroup = new ToggleGroup();
        
        upiRadio = new RadioButton("UPI Payment");
        upiRadio.setToggleGroup(paymentGroup);
        upiRadio.setSelected(true);
        upiRadio.setStyle("-fx-font-size: 14px;");
        
        cardRadio = new RadioButton("Credit/Debit Card");
        cardRadio.setToggleGroup(paymentGroup);
        cardRadio.setStyle("-fx-font-size: 14px;");
        
        // Event handlers for radio buttons
        upiRadio.setOnAction(e -> updateSectionVisibility());
        cardRadio.setOnAction(e -> updateSectionVisibility());
        
        methodBox.getChildren().addAll(methodTitle, new Separator(), upiRadio, cardRadio);
        mainContainer.getChildren().add(methodBox);
    }
    
    private void createUPISection() {
        upiSection = new VBox(10);
        upiSection.setMaxWidth(Double.MAX_VALUE);
        upiSection.setPadding(new Insets(15));
        upiSection.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        
        Label upiTitle = new Label("UPI Details");
        upiTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
        Label upiIdLabel = new Label("UPI ID:");
        upiIdLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
        upiIdField = new TextField();
        upiIdField.setPromptText("Enter your UPI ID (e.g., user@paytm)");
        upiIdField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
        // Add UPI validation
        upiIdField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.contains("@") && newText.length() > 5) {
                upiIdField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px; -fx-border-color: green;");
            } else {
                upiIdField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px; -fx-border-color: red;");
            }
        });
        
        upiSection.getChildren().addAll(upiTitle, new Separator(), upiIdLabel, upiIdField);
        mainContainer.getChildren().add(upiSection);
    }
    
    private void createCardSection() {
        cardSection = new VBox(10);
        cardSection.setPadding(new Insets(15));
        cardSection.setMaxWidth(Double.MAX_VALUE);
        cardSection.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        
        Label cardTitle = new Label("Card Details");
        cardTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
        // Card holder name
        Label nameLabel = new Label("Card Holder Name:");
        nameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
        cardHolderNameField = new TextField();
        cardHolderNameField.setPromptText("Enter cardholder name");
        cardHolderNameField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
        // Card number
        Label cardNumberLabel = new Label("Card Number:");
        cardNumberLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
        cardNumberField = new TextField();
        cardNumberField.setPromptText("1234 5678 9012 3456");
        cardNumberField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
        // Format card number input
        cardNumberField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > 19) return; // Prevent input beyond 16 digits + 3 spaces
            
            String formatted = newText.replaceAll("[^\\d]", "");
            if (formatted.length() > 16) formatted = formatted.substring(0, 16);
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < formatted.length(); i++) {
                if (i > 0 && i % 4 == 0) sb.append(" ");
                sb.append(formatted.charAt(i));
            }
            
            if (!sb.toString().equals(newText)) {
                cardNumberField.setText(sb.toString());
                cardNumberField.positionCaret(sb.length());
            }
        });
        
        // Expiry and CVV
        HBox expiryAndCvvBox = new HBox(10);
        
        VBox expiryBox = new VBox(5);
        Label expiryLabel = new Label("Expiry Date:");
        expiryLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
        HBox expiryFields = new HBox(5);
        expiryMonthField = new TextField();
        expiryMonthField.setPromptText("MM");
        expiryMonthField.setPrefWidth(60);
        expiryMonthField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
        Label slashLabel = new Label("/");
        slashLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: #666;");
        
        expiryYearField = new TextField();
        expiryYearField.setPromptText("YY");
        expiryYearField.setPrefWidth(60);
        expiryYearField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
        // Limit expiry field inputs
        expiryMonthField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > 2) expiryMonthField.setText(oldText);
            if (!newText.matches("\\d*")) expiryMonthField.setText(oldText);
        });
        
        expiryYearField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > 2) expiryYearField.setText(oldText);
            if (!newText.matches("\\d*")) expiryYearField.setText(oldText);
        });
        
        expiryFields.getChildren().addAll(expiryMonthField, slashLabel, expiryYearField);
        expiryBox.getChildren().addAll(expiryLabel, expiryFields);
        
        VBox cvvBox = new VBox(5);
        Label cvvLabel = new Label("CVV:");
        cvvLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
        cvvField = new TextField();
        cvvField.setPromptText("123");
        cvvField.setPrefWidth(80);
        cvvField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
        // Limit CVV to 3 digits
        cvvField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > 3) cvvField.setText(oldText);
            if (!newText.matches("\\d*")) cvvField.setText(oldText);
        });
        
        cvvBox.getChildren().addAll(cvvLabel, cvvField);
        
        expiryAndCvvBox.getChildren().addAll(expiryBox, cvvBox);
        
        cardSection.getChildren().addAll(cardTitle, new Separator(), nameLabel, cardHolderNameField, 
                                       cardNumberLabel, cardNumberField, expiryAndCvvBox);
        mainContainer.getChildren().add(cardSection);
    }
    
    private void createPINSection() {
        VBox pinSection = new VBox(10);
        pinSection.setPadding(new Insets(15));
        pinSection.setMaxWidth(Double.MAX_VALUE);
        pinSection.setStyle("-fx-background-color: white; -fx-background-radius: 8; -fx-border-color: #ddd; -fx-border-radius: 8;");
        
        Label pinTitle = new Label("Enter PIN");
        pinTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
        Label pinLabel = new Label("UPI PIN / Card PIN:");
        pinLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666;");
        
        pinField = new PasswordField();
        pinField.setPromptText("Enter 4-6 digit PIN");
        pinField.setStyle("-fx-font-size: 14px; -fx-pref-height: 35px;");
        
        // Limit PIN input
        pinField.textProperty().addListener((obs, oldText, newText) -> {
            if (newText.length() > 6) pinField.setText(oldText);
            if (!newText.matches("\\d*")) pinField.setText(oldText);
        });
        
        pinSection.getChildren().addAll(pinTitle, new Separator(), pinLabel, pinField);
        mainContainer.getChildren().add(pinSection);
    }
    
    private void createButtonsSection() {
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        
        // Back button
        backButton = new Button("BACK");
        backButton.setPrefWidth(100);
        backButton.setPrefHeight(45);
        backButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");
        backButton.setOnAction(e -> navigateBack());
        
        // Clear/Reset button
        Button clearButton = new Button("CLEAR");
        clearButton.setPrefWidth(100);
        clearButton.setPrefHeight(45);
        clearButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");
        clearButton.setOnAction(e -> resetForm());

        // Proceed button
        proceedButton = new Button("Confirm & Pay");
        proceedButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");
        HBox.setHgrow(proceedButton, Priority.ALWAYS);
        proceedButton.setMaxWidth(300);
        proceedButton.setPrefHeight(50);
        
        // Add hover effects
        // Back Button Enhanced Effects
        clearButton.setOnMouseEntered(e -> {
            // Color and style change
            clearButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 14px; " +
                            "-fx-font-weight: bold;  " +
                        
                            "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Enhanced shadow
            
            // Float up animation
            TranslateTransition translateUp = new TranslateTransition(Duration.millis(150), clearButton);
            translateUp.setToY(-3); // Move up by 3 pixels
            
            // Slight scale up
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), clearButton);
            scaleUp.setToX(1.05);
            scaleUp.setToY(1.05);
            
            // Combine animations
            ParallelTransition parallelTransition = new ParallelTransition(translateUp, scaleUp);
            parallelTransition.play();
        });

        clearButton.setOnMouseExited(e -> {
            // Restore original style
            clearButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 14px; " +
                            "-fx-font-weight: bold;  " +
                            
                            "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Original shadow
            
            // Float down animation
            TranslateTransition translateDown = new TranslateTransition(Duration.millis(150), clearButton);
            translateDown.setToY(0); // Return to original position
            
            // Scale back to normal
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), clearButton);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            
            // Combine animations
            ParallelTransition parallelTransition = new ParallelTransition(translateDown, scaleDown);
            parallelTransition.play();
        });

        backButton.setOnMouseEntered(e -> {
            // Color and style change
            backButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 14px; " +
                            "-fx-font-weight: bold; -fx-pref-height: 45px; -fx-pref-width: 100px; " +
                            
                            "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Enhanced shadow
            
            // Float up animation
            TranslateTransition translateUp = new TranslateTransition(Duration.millis(150), backButton);
            translateUp.setToY(-3); // Move up by 3 pixels
            
            // Slight scale up
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), backButton);
            scaleUp.setToX(1.05);
            scaleUp.setToY(1.05);
            
            // Combine animations
            ParallelTransition parallelTransition = new ParallelTransition(translateUp, scaleUp);
            parallelTransition.play();
        });

        backButton.setOnMouseExited(e -> {
            // Restore original style
            backButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 14px; " +
                            "-fx-font-weight: bold; -fx-pref-height: 45px; -fx-pref-width: 100px; " +
                           
                            "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Original shadow
            
            // Float down animation
            TranslateTransition translateDown = new TranslateTransition(Duration.millis(150), backButton);
            translateDown.setToY(0); // Return to original position
            
            // Scale back to normal
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), backButton);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            
            // Combine animations
            ParallelTransition parallelTransition = new ParallelTransition(translateDown, scaleDown);
            parallelTransition.play();
        });

        // Proceed Button Enhanced Effects
        proceedButton.setOnMouseEntered(e -> {
            BookingConfirmPage.setModel(model);
            // Color and style change
            proceedButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 16px; " +
                                "-fx-font-weight: bold; -fx-pref-height: 45px; " +
                                "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Blue shadow
            
            // Float up animation
            TranslateTransition translateUp = new TranslateTransition(Duration.millis(150), proceedButton);
            translateUp.setToY(-4); // Move up by 4 pixels (more prominent)
            
            // Slight scale up
            ScaleTransition scaleUp = new ScaleTransition(Duration.millis(150), proceedButton);
            scaleUp.setToX(1.03);
            scaleUp.setToY(1.03);
            
            // Combine animations
            ParallelTransition parallelTransition = new ParallelTransition(translateUp, scaleUp);
            parallelTransition.play();
        });

        proceedButton.setOnMouseExited(e -> {
            // Restore original style
            proceedButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-size: 16px; " +
                                "-fx-font-weight: bold; -fx-pref-height: 45px;  " +
                                "-fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"); // Original blue shadow
            
            // Float down animation
            TranslateTransition translateDown = new TranslateTransition(Duration.millis(150), proceedButton);
            translateDown.setToY(0); // Return to original position
            
            // Scale back to normal
            ScaleTransition scaleDown = new ScaleTransition(Duration.millis(150), proceedButton);
            scaleDown.setToX(1.0);
            scaleDown.setToY(1.0);
            
            // Combine animations
            ParallelTransition parallelTransition = new ParallelTransition(translateDown, scaleDown);
            parallelTransition.play();
        });

        

        // Optional: Add a subtle pulse effect on button press
        // Add these to your buttons for even more interactivity:

        clearButton.setOnMousePressed(e -> {
            ScaleTransition clearscale = new ScaleTransition(Duration.millis(100), backButton);
            clearscale.setToX(0.98);
            clearscale.setToY(0.98);
            clearscale.play();
        });

        clearButton.setOnMouseReleased(e -> {
            ScaleTransition clearscale = new ScaleTransition(Duration.millis(100), backButton);
            clearscale.setToX(1.05); // Back to hover scale
            clearscale.setToY(1.05);
            clearscale.play();
        });

        backButton.setOnMousePressed(e -> {
            ScaleTransition pressScale = new ScaleTransition(Duration.millis(100), backButton);
            pressScale.setToX(0.98);
            pressScale.setToY(0.98);
            pressScale.play();
        });

        backButton.setOnMouseReleased(e -> {
            ScaleTransition releaseScale = new ScaleTransition(Duration.millis(100), backButton);
            releaseScale.setToX(1.05); // Back to hover scale
            releaseScale.setToY(1.05);
            releaseScale.play();
        });

        proceedButton.setOnMousePressed(e -> {
            ScaleTransition pressScale = new ScaleTransition(Duration.millis(100), proceedButton);
            pressScale.setToX(0.99);
            pressScale.setToY(0.99);
            pressScale.play();
        });

        proceedButton.setOnMouseReleased(e -> {
            ScaleTransition releaseScale = new ScaleTransition(Duration.millis(100), proceedButton);
            releaseScale.setToX(1.03); // Back to hover scale
            releaseScale.setToY(1.03);
            releaseScale.play();
        });


        proceedButton.setOnAction(e -> processPayment());
        
        buttonBox.getChildren().addAll(backButton, clearButton, proceedButton);
        mainContainer.getChildren().add(buttonBox);
    }
    
    private void createProgressIndicator() {
        progressIndicator = new ProgressIndicator();
        progressIndicator.setVisible(false);
        progressIndicator.setPrefSize(50, 50);
        
        VBox progressBox = new VBox(10);
        progressBox.setAlignment(Pos.CENTER);
        progressBox.getChildren().add(progressIndicator);
        
        mainContainer.getChildren().add(progressBox);
    }
    
    private void updateSectionVisibility() {
        boolean isUPI = upiRadio.isSelected();
        upiSection.setVisible(isUPI);
        upiSection.setManaged(isUPI);
        cardSection.setVisible(!isUPI);
        cardSection.setManaged(!isUPI);
    }
    
    private void navigateBack() {
        if (previousScene != null) {
            primaryStage.setScene(previousScene);
        } else {
            // If no previous scene provided, you can handle this case
            // For example, close the stage or navigate to main menu
            showAlert("Navigation", "No previous screen to go back to.", Alert.AlertType.INFORMATION);
        }
    }

    private void navigateToConfirmationPage() {
    try {

        // Create new instance of BookingConfirmPage
        BookingConfirmPage confirmPage = new BookingConfirmPage();
        Scene coScene = confirmPage.start(primaryStage); // Using getHomeScene
        primaryStage.setScene(coScene); 
        
    } catch (Exception ex) {
        ex.printStackTrace();
        showAlert("Navigation Error", "Could not open confirmation page.", Alert.AlertType.ERROR);
    }
}
    
    private void processPayment() {
    if (!validateInput()) {
        showAlert("Validation Error", "Please fill all required fields correctly.", Alert.AlertType.ERROR);
        return;
    }
    
    // Show progress
    proceedButton.setDisable(true);
    backButton.setDisable(true);
    proceedButton.setText("PROCESSING...");
    progressIndicator.setVisible(true);
    
    // Simulate payment processing with Timeline
    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> {
        // Ensure UI updates happen on JavaFX Application Thread
        Platform.runLater(() -> {
            // Hide progress indicators
            progressIndicator.setVisible(false);
            proceedButton.setDisable(false);
            backButton.setDisable(false);
            proceedButton.setText("PAY");
            
            // Show success dialog
            showPaymentSuccess();
        });
    }));
    timeline.play();
}
    
   private boolean validateInput() {
    if (upiRadio.isSelected()) {
        return upiIdField.getText().trim().length() > 0 &&
               upiIdField.getText().contains("@") &&
               pinField.getText().length() >= 4;
    } else {
        return cardHolderNameField.getText().trim().length() > 0 &&
               cardNumberField.getText().replaceAll("\\s", "").length() == 16 &&
               expiryMonthField.getText().length() == 2 &&
               expiryYearField.getText().length() == 2 &&
               cvvField.getText().length() == 3 &&
               pinField.getText().length() >= 4;
    }
}
    
    private void showPaymentSuccess() {
    try {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Payment Successful!");
        alert.setHeaderText("Your payment has been processed successfully");
        
        String paymentMethod = upiRadio.isSelected() ? 
            "UPI ID: " + upiIdField.getText() :
            "Card ending with: ****" + cardNumberField.getText().replaceAll("\\s", "").substring(12);
        
        alert.setContentText("Payment Method: " + paymentMethod + "\n" +
                           "Amount: ₹" + model.getPricePerAdult() + "\n" +
                           "Transaction ID: TRN" + System.currentTimeMillis() + "\n" +
                           "Status: SUCCESS\n\n" +
                           "Thank you for choosing TirthOnGo!\n" +
                           "Your booking has been confirmed.");
        
        // Make alert modal and wait for user to close it
        alert.setResizable(true);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        
        // Show alert and wait for user response
        Optional<ButtonType> result = alert.showAndWait();
        
        // Only proceed after user closes the alert
        if (result.isPresent()) {
            // Reset form after user acknowledges success
            resetForm();
            
            // Navigate back only after user closes the alert
            navigateToConfirmationPage();
        }
        
    } catch (Exception ex) {
        ex.printStackTrace();
        showAlert("Error", "An error occurred while displaying payment confirmation.", Alert.AlertType.ERROR);
    }
}


    
    private void resetForm() {
        upiIdField.clear();
        cardHolderNameField.clear();
        cardNumberField.clear();
        expiryMonthField.clear();
        expiryYearField.clear();
        cvvField.clear();
        pinField.clear();
        upiRadio.setSelected(true);
        updateSectionVisibility();
    }
    
    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    // Method to update amount if needed
    public void setAmount(double amount) {
        this.totalAmount = amount;
        if (amountLabel != null) {
            amountLabel.setText("₹" + String.format("%.2f", totalAmount));
        }
    }
    
    // Method to set previous scene for navigation
    public void setPreviousScene(Scene scene) {
        this.previousScene = scene;
    }
}

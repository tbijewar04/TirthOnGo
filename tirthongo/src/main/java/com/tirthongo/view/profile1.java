// package com.tirthongo.view;

// import java.io.File;
// import java.io.InputStream;

// import javafx.application.Application;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.ComboBox;
// import javafx.scene.control.DatePicker;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextField;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.FileChooser;
// import javafx.stage.Stage;

// public class profile1 extends Application {

//     @Override
//     public void start(Stage stage) throws Exception {
//             // Enhanced Top bar with gradient and better spacing
//         HBox topBar = new HBox();
//         topBar.setPadding(new Insets(15));
//         topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 3);");
//         topBar.setAlignment(Pos.CENTER_LEFT);

//         // Enhanced title with shadow effect
//         Label title = new Label("🛐 TirthOnGo");
//         title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
//         title.setTextFill(Color.web("#ec7208ff")); 

//         topBar.getChildren().addAll(title);

//         // Enhanced welcome section with modern styling
//         Label heading = new Label("Admin Profile");
//         heading.setStyle("-fx-font-weight: bold; -fx-font-size: 32px; -fx-text-fill: #2c3e50;");
//         heading.setAlignment(Pos.CENTER);
        
//         // Label content = new Label("Complete your profile to unlock personalized travel experiences");
//         // content.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");
//         // content.setAlignment(Pos.CENTER);

       
//         // Profile Image Upload Section
//         ImageView profileImageView = new ImageView();

//         // Load default image with proper resource handling
//         try {
//             InputStream imageStream = getClass().getResourceAsStream("/Assets/Images/6337001401326092247.jpg");
//             if (imageStream != null) {
//                 Image defaultImage = new Image(imageStream);
//                 profileImageView.setImage(defaultImage);
//             } else {
//                 System.out.println("Default image not found at: /Assets/Images/6337001401326092247.jpg");
//             }
//         } catch (Exception e) {
//             System.out.println("Error loading default image: " + e.getMessage());
//         }

//         // CRITICAL: Set image properties correctly
//         profileImageView.setFitWidth(150);
//         profileImageView.setFitHeight(150);
//         profileImageView.setPreserveRatio(false); // Allow image to fill entire circle
//         profileImageView.setSmooth(true);

//         // FIXED: Create clip with proper center positioning
//         Circle clip = new Circle(75, 75, 75); // centerX, centerY, radius
//         profileImageView.setClip(clip);

//         // Border circle
//         Circle border = new Circle(75); // Only radius needed in StackPane
//         border.setFill(Color.TRANSPARENT);
//         border.setStroke(Color.web("#319fffff"));
//         border.setStrokeWidth(1);

//         // Add hover effect to border
//         DropShadow imageHoverEffect = new DropShadow();
//         imageHoverEffect.setColor(Color.web("#37ebffff"));
//         imageHoverEffect.setRadius(5);
//         imageHoverEffect.setSpread(0.3);

//         // StackPane setup
//         StackPane imageStack = new StackPane();
//         imageStack.getChildren().addAll(profileImageView, border);
//         imageStack.setMaxSize(150, 150);
//         imageStack.setPrefSize(150, 150);
//         imageStack.setStyle("-fx-cursor: hand;");

//         // Hover effects for image
//         imageStack.setOnMouseEntered(e -> {
//             imageStack.setEffect(imageHoverEffect);
//             border.setStroke(Color.web(" #f9970dff"));
//         });

//         imageStack.setOnMouseExited(e -> {
//             imageStack.setEffect(null);
//             border.setStroke(Color.web(" #f9970dff"));
//         });

//         // FIXED: Proper image upload handling with correct clip positioning
//         imageStack.setOnMouseClicked(event -> {
//             FileChooser fileChooser = new FileChooser();
//             fileChooser.setTitle("Choose Profile Image");
//             fileChooser.getExtensionFilters().addAll(
//                 new FileChooser.ExtensionFilter("Image Files", ".png", ".jpg", ".jpeg", ".bmp", "*.gif")
//             );
//             File selectedFile = fileChooser.showOpenDialog(stage);
//             if (selectedFile != null && selectedFile.exists()) {
//                 try {
//                     Image newImage = new Image("");
//                     profileImageView.setImage(newImage);
                    
//                     // CRITICAL FIX: Create new clip with proper center coordinates
//                     Circle newClip = new Circle(75, 75, 75); // centerX=75, centerY=75, radius=75
//                     profileImageView.setClip(newClip);
                    
//                     System.out.println("Image uploaded successfully: " + selectedFile.getName());
                    
//                 } catch (Exception ex) {
//                     System.out.println("Error loading image: " + ex.getMessage());
//                     Alert alert = new Alert(Alert.AlertType.ERROR);
//                     alert.setTitle("Image Load Failed");
//                     alert.setHeaderText(null);
//                     alert.setContentText("Couldn't load selected image. Please try a different file.");
//                     alert.showAndWait();
//                 }
//             }
//         });

//         Label uploadLabel = new Label("📷 Click to upload profile photo");
//         uploadLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #667eea; -fx-cursor: hand;");
//         uploadLabel.setOnMouseClicked(imageStack.getOnMouseClicked());

//         VBox imageSection = new VBox(10, imageStack, uploadLabel);
//         imageSection.setAlignment(Pos.CENTER);
        
//         // Enhanced form fields with consistent styling
//         String fieldStyle = "-fx-background-color: #f8f9fa; -fx-border-color: #e9ecef; -fx-border-width: 1; " +
//                            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 12; " +
//                            "-fx-font-size: 14px; -fx-pref-height: 45;";
        
//         String labelStyle = "-fx-font-weight: 600; -fx-text-fill: #2c3e50; -fx-font-size: 14px;";

//         // Personal Information Section
//         Label personalInfoHeader = new Label("👤 Personal Information");
//         personalInfoHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #34495e; -fx-padding: 0 0 10 0;");

//         Label firstName = new Label("First Name *");
//         firstName.setStyle(labelStyle);
//         TextField firstNameField = new TextField();
//         firstNameField.setStyle(fieldStyle);
//         firstNameField.setPromptText("Enter your first name");
        
//         Label lastName = new Label("Last Name *");
//         lastName.setStyle(labelStyle);
//         TextField lastNameField = new TextField();
//         lastNameField.setStyle(fieldStyle);
//         lastNameField.setPromptText("Enter your last name");

//         Label gender = new Label("Gender *");
//         gender.setStyle(labelStyle);
//         ComboBox<String> genderField = new ComboBox<>();
//         genderField.getItems().addAll("Male", "Female", "Other");
//         genderField.setStyle(fieldStyle + "-fx-background-color: white;");
//         genderField.setPromptText("Select your gender");
//         genderField.setPrefHeight(45);
//         genderField.setMaxWidth(Double.MAX_VALUE);

//         Label dob = new Label("Date of Birth *");
//         dob.setStyle(labelStyle);
//         DatePicker dobField = new DatePicker();
//         dobField.setStyle(fieldStyle + "-fx-background-color: white;");
//         dobField.setPromptText("Select your date of birth");
//         dobField.setPrefHeight(45);
//         dobField.setMaxWidth(Double.MAX_VALUE);

//         Label email = new Label("Email Address *");
//         email.setStyle(labelStyle);
//         TextField emailField = new TextField();
//         emailField.setStyle(fieldStyle);
//         emailField.setPromptText("your.email@example.com");

//         Label address = new Label("Address");
//         address.setStyle(labelStyle);
//         TextField addressField = new TextField();
//         addressField.setStyle(fieldStyle);
//         addressField.setPromptText("Enter your full address");

//         Label state = new Label("State *");
//         state.setStyle(labelStyle);
//         ComboBox<String> stateField = new ComboBox<>();
//         stateField.getItems().addAll(
//             "Andhra Pradesh", "Arunachal Pradesh", "Assam", "Bihar", "Chhattisgarh",
//             "Goa", "Gujarat", "Haryana", "Himachal Pradesh", "Jharkhand", "Karnataka",
//             "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", "Mizoram",
//             "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu",
//             "Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal",
//             "Delhi", "Jammu and Kashmir", "Ladakh", "Puducherry", "Chandigarh",
//             "Dadra and Nagar Haveli and Daman and Diu", "Lakshadweep",
//             "Andaman and Nicobar Islands"
//         );
//         stateField.setStyle(fieldStyle + "-fx-background-color: white;");
//         stateField.setPromptText("Select your state");
//         stateField.setPrefHeight(45);
//         stateField.setMaxWidth(Double.MAX_VALUE);

//         Label pincode = new Label("Pincode *");
//         pincode.setStyle(labelStyle);
//         TextField pincodeField = new TextField();
//         pincodeField.setStyle(fieldStyle);
//         pincodeField.setPromptText("6-digit pincode");

//         Label phone = new Label("Mobile No *");
//         pincode.setStyle(labelStyle);
//         TextField phoneField = new TextField();
//         phoneField.setStyle(fieldStyle);
//         phoneField.setPromptText("enter valid number");

//         Label gst = new Label("Pincode *");
//         gst.setStyle(labelStyle);
//         TextField gstField = new TextField();
//         gstField.setStyle(fieldStyle);
//         gstField.setPromptText("enter valid gst");

//         // Enhanced form layout with better spacing
//         VBox vbx1 = new VBox(8, firstName, firstNameField);
//         vbx1.setPrefWidth(250);
//         VBox vbx2 = new VBox(8, lastName, lastNameField);
//         vbx2.setPrefWidth(250);
//         HBox hbx1 = new HBox(30, vbx1, vbx2);
//         hbx1.setAlignment(Pos.TOP_CENTER);

//         VBox vbx3 = new VBox(8, gender, genderField);
//         vbx3.setPrefWidth(250);
//         VBox vbx4 = new VBox(8, dob, dobField);
//         vbx4.setPrefWidth(250);

//         HBox hbx2 = new HBox(30, vbx3, vbx4);
//         hbx2.setAlignment(Pos.TOP_CENTER);

//         VBox vbx5 = new VBox(8, email, emailField);
//         vbx5.setPrefWidth(250);
//         VBox vbx6 = new VBox(8, address, addressField);
//         vbx6.setPrefWidth(250);
//         HBox hbx3 = new HBox(30, vbx5, vbx6);
//         hbx3.setAlignment(Pos.TOP_CENTER);

//         VBox vbx7 = new VBox(8, state, stateField);
//         vbx7.setPrefWidth(250);
//         VBox vbx8 = new VBox(8, pincode, pincodeField);
//         vbx8.setPrefWidth(250);
//         HBox hbx4 = new HBox(30, vbx7, vbx8);
//         hbx4.setAlignment(Pos.TOP_CENTER);

//         VBox vbx9 = new VBox(8, phone, phoneField);
//         vbx9.setPrefWidth(250);
//         VBox vbx10 = new VBox(8, gst, gstField);
//         vbx10.setPrefWidth(250);
//         HBox hbx5 = new HBox(30, vbx9, vbx10);
//         hbx3.setAlignment(Pos.TOP_CENTER);

//         Button edit = new Button("Edit");
//          edit.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);" + "-fx-text-fill: black;" +
//                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
//                      "-fx-font-weight: bold; " +
//                      "-fx-font-size: 15px; " +
//                      "-fx-background-radius: 10; " +
//                      "-fx-cursor: hand;");
//         edit.setPrefWidth(200);
//         edit.setPrefHeight(50);
//         edit.setMinHeight(50);
//         edit.setMaxHeight(50);
        

//         Button save = new Button("Save");
//          save.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);" + "-fx-text-fill: black;" +
//                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
//                      "-fx-font-weight: bold; " +
//                      "-fx-font-size: 15px; " +
//                      "-fx-background-radius: 10; " +
//                      "-fx-cursor: hand;");
//         save.setPrefWidth(200);
//         save.setPrefHeight(50);
//         save.setMinHeight(50);
//         save.setMaxHeight(50);
        

//         // // Enhanced button with hover effects and better styling
//         // Button save = new Button("💾 Save & Continue");
//         // save.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);" + "-fx-text-fill: black;" +
//         //              "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
//         //              "-fx-font-weight: bold; " +
//         //              "-fx-font-size: 15px; " +
//         //              "-fx-background-radius: 10; " +
//         //              "-fx-cursor: hand;");
//         // save.setPrefWidth(200);
//         // save.setPrefHeight(50);
//         // save.setMinHeight(50);
//         // save.setMaxHeight(50);
        
//         // // Add hover effect
//         // save.setOnMouseEntered(e -> save.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
//         //                                          "-fx-text-fill: white; " +
//         //                                          "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
//         //                                          "-fx-font-weight: bold; " +
//         //                                          "-fx-font-size: 15px; " +
//         //                                          "-fx-background-radius: 10; " +
//         //                                          "-fx-cursor: hand; " +
//         //                                          "-fx-scale-y: 1.05;"));
        
//         // save.setOnMouseExited(e -> save.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
//         //                                         "-fx-text-fill: white; " +
//         //                                         "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
//         //                                         "-fx-font-weight: bold; " +
//         //                                         "-fx-font-size: 15px; " +
//         //                                         "-fx-background-radius: 10; " +
//         //                                         "-fx-cursor: hand; " +
//         //                                         "-fx-scale-y: 1.0;"));
        
//         // save.setOnAction(new EventHandler<ActionEvent>() {
//         //     @Override
//         //     public void handle(ActionEvent arg0) {
//         //         // Enhanced data handling
//         //         StringBuilder userData = new StringBuilder();
//         //         userData.append("User Profile Saved:\n");
//         //         userData.append("Name: ").append(firstNameField.getText()).append(" ").append(lastNameField.getText()).append("\n");
//         //         userData.append("Gender: ").append(genderField.getValue() != null ? genderField.getValue() : "").append("\n");
                
//         //         // Format date properly
//         //         String dobText = "";
//         //         if (dobField.getValue() != null) {
//         //             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//         //             dobText = dobField.getValue().format(formatter);
//         //         }
//         //         userData.append("DOB: ").append(dobText).append("\n");
                
//         //         userData.append("Email: ").append(emailField.getText()).append("\n");
//         //         userData.append("Address: ").append(addressField.getText()).append("\n");
//         //         userData.append("State: ").append(stateField.getValue() != null ? stateField.getValue() : "").append("\n");
//         //         userData.append("Pincode: ").append(pincodeField.getText());
                
//         //         text.setText(userData.toString());
//         //         System.out.println(userData.toString());
//         //     }
//         // });

//         // Required fields notice
//         Label requiredNote = new Label("* Required fields");
//         requiredNote.setStyle("-fx-font-size: 12px; -fx-text-fill: #e74c3c; -fx-font-style: italic;");
        
//         // Enhanced form container with modern card design
//         VBox roundBox = new VBox(25, personalInfoHeader, hbx1, hbx2, hbx3, hbx4,hbx5, requiredNote,edit, save);
//         roundBox.setMaxWidth(650);
//         roundBox.setPadding(new Insets(35, 40, 35, 40));
//         roundBox.setAlignment(Pos.TOP_CENTER);

//         // Modern card styling with subtle gradient
//         roundBox.setStyle(
//             "-fx-background-color: linear-gradient(to left, #ffffffff, #f8c47bff) ; " +
//             "-fx-background-radius: 15;" +
//             "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 8);" +
//             "-fx-border-color: rgba(102, 126, 234, 0.1);" +
//             "-fx-border-width: 1;" +
//             "-fx-border-radius: 15;"
//         );

//         // Background with subtle pattern
//         VBox mainContainer = new VBox(20, topBar,heading, imageSection, roundBox);
//         mainContainer.setPadding(new Insets(0, 0, 30, 0));
//         mainContainer.setAlignment(Pos.TOP_CENTER);
//         mainContainer.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
//         mainContainer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
//         mainContainer.setStyle("-fx-background-color: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);");
//         VBox.setVgrow(mainContainer, Priority.ALWAYS);

//         ScrollPane scroll = new ScrollPane(mainContainer);
//         scroll.setFitToWidth(true);
//         scroll.setFitToHeight(true);
//         scroll.setPannable(true);
//         scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scroll.setStyle("-fx-background-color: transparent;");
//         scroll.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
//         VBox.setVgrow(scroll, Priority.ALWAYS);


//         Scene sc = new Scene(scroll, stage.getWidth(), stage.getHeight());

//         stage.setScene(sc);
//         stage.setResizable(true);
//         stage.setMaximized(true);  // THIS will actually make the window fullscreen
//         stage.sizeToScene();

// }
    
// }


package com.tirthongo.view;
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
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File; 
import java.time.LocalDate;
import java.util.Map;
import com.tirthongo.controller.FirebaseInitializer;
import com.tirthongo.dao.ProfileDao;

// This class will be the read-only view of the Admin Profile
public class profile1 { 

    
    // Context fields (same as AdminProfile setup page)
    private static String loggedInAdminUid;
    private static String adminEmail;
    private static String adminRole;
    
    // To allow image update from *this* page, if user clicks the image view
    private File selectedProfileImageFile; 

    // UI Field References (needed for populateFields)
    private TextField firstNameField;
    private TextField lastNameField;
    private ComboBox<String> genderField;
    private DatePicker dobField;
    private TextField emailField;
    private TextField addressField;
    private ComboBox<String> stateField;
    private TextField pincodeField;
    private TextField phoneField;
    private TextField gstField;
    private ImageView profileImageView; // To display the profile image

    // Setters to receive context
    public void setLoggedInAdminUid(String uid) { this.loggedInAdminUid = uid; }
    public void setAdminEmail(String email) { this.adminEmail = email; }
    public void setAdminRole(String role) { this.adminRole = role; }

    // Main method to return the Scene for this page
    public Scene getAdminProfileViewScene(Stage primaryStage) { // Specific name for VIEW page

        FirebaseInitializer.initialize();

        System.out.println("Received UID: " + this.loggedInAdminUid);
        // --- Top Bar (with Back button) ---
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 3);");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("ॐ TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#ec7208ff")); 

        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-color: #34495e; -fx-border-width: 0 0 0 0; -fx-cursor: hand;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #ec7208ff; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-color: #ec7208ff; -fx-border-width: 0 0 0 0; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-color: #34495e; -fx-border-width: 0 0 0 0; -fx-cursor: hand;"));

        Region spacerForTitle = new Region();
        HBox.setHgrow(spacerForTitle, Priority.ALWAYS);

        topBar.getChildren().addAll(title, spacerForTitle);
        VBox VB = new VBox(10);
        VB.getChildren().addAll(topBar,backButton);

        // --- Back Button Action: Navigate to AdminDashboardPage ---
        backButton.setOnAction(e -> {
            try {
                AdminDashboardPage adminDashboard = new AdminDashboardPage();
                adminDashboard.setLoggedInAdminUid(loggedInAdminUid);
                adminDashboard.setAdminEmail(adminEmail);
                adminDashboard.setAdminRole(adminRole);
                Scene adminDashboardScene = adminDashboard.getAdminDashboardScene(primaryStage);
                primaryStage.setScene(adminDashboardScene);
            } catch (Exception ex) {
                System.err.println("Error navigating back to Admin Dashboard from AdminProfileViewPage: " + ex.getMessage());
                ex.printStackTrace();
                showAlert("Navigation Error", "Failed to go back to dashboard.", Alert.AlertType.ERROR);
            }
        });

        Label heading = new Label("Admin Profile"); // Consistent heading
        heading.setStyle("-fx-font-weight: bold; -fx-font-size: 32px; -fx-text-fill: #2c3e50;");
        heading.setAlignment(Pos.CENTER);
        
        // --- Profile Image Display Section ---
        profileImageView = new ImageView(); // Initialize here
        try {
            String imageStream = AdminDashboardPage.getAdminProfileModel().getProfileImageUrl().toString();
            if (imageStream != null) {
                Image defaultImage = new Image(AdminDashboardPage.getAdminProfileModel().getProfileImageUrl());
                profileImageView.setImage(defaultImage);
            } else {
                System.out.println("Default image not found at: /Assets/Images/ProfileImageIcon.jpg");
            }
        } catch (Exception e) {
            System.out.println("Error loading default image: " + e.getMessage());
        }

        profileImageView.setFitWidth(150);
        profileImageView.setFitHeight(150);
        profileImageView.setPreserveRatio(false);
        profileImageView.setSmooth(true);

        Circle clip = new Circle(75, 75, 75);
        profileImageView.setClip(clip);

        Circle border = new Circle(75);
        border.setFill(Color.TRANSPARENT);
        border.setStroke(Color.web("#319fffff"));
        border.setStrokeWidth(1);

        DropShadow imageHoverEffect = new DropShadow();
        imageHoverEffect.setColor(Color.web("#37ebffff"));
        imageHoverEffect.setRadius(5);
        imageHoverEffect.setSpread(0.3);

        StackPane imageStack = new StackPane();
        imageStack.getChildren().addAll(profileImageView, border);
        imageStack.setMaxSize(150, 150);
        imageStack.setPrefSize(150, 150);
        imageStack.setStyle("-fx-cursor: hand;");

        imageStack.setOnMouseClicked(event -> {
            showAlert("Info", "Click 'Edit' to change profile details including photo.", Alert.AlertType.INFORMATION);
            // OR you could put FileChooser here to allow direct image change without other edits
        });


        Label uploadLabel = new Label("📷 Profile Photo"); // Changed text for view page
        uploadLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #667eea;"); // No cursor hand as not directly clickable for upload
        // uploadLabel.setOnMouseClicked(imageStack.getOnMouseClicked()); // Removed as it's not directly uploading here

        VBox imageSection = new VBox(10, imageStack, uploadLabel);
        imageSection.setAlignment(Pos.CENTER);
        
        // --- Form Fields (Labels & Read-Only TextFields/ComboBoxes) ---
        String fieldStyle = "-fx-background-color: #f8f9fa; -fx-border-color: #e9ecef; -fx-border-width: 1; " +
                            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 12; " +
                            "-fx-font-size: 14px; -fx-pref-height: 45;";
        
        String labelStyle = "-fx-font-weight: 600; -fx-text-fill: #2c3e50; -fx-font-size: 14px;";

        Label personalInfoHeader = new Label("👤 Personal Information");
        personalInfoHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #34495e; -fx-padding: 0 0 10 0;");

        Label firstNameLabel_ui = new Label("First Name:"); 
        firstNameLabel_ui.setStyle(labelStyle);
        firstNameField = new TextField(AdminDashboardPage.getAdminProfileModel().getFirstName()); 
        firstNameField.setStyle(fieldStyle);
        firstNameField.setEditable(false); 
        
        Label lastNameLabel_ui = new Label("Last Name:");
        lastNameLabel_ui.setStyle(labelStyle);
        lastNameField = new TextField(AdminDashboardPage.getAdminProfileModel().getLastName()); 
        lastNameField.setStyle(fieldStyle);
        lastNameField.setEditable(false); 

        Label genderLabel_ui = new Label("Gender:");
        genderLabel_ui.setStyle(labelStyle);
        System.out.println(AdminDashboardPage.getAdminProfileModel().getGender());
        genderField = new ComboBox<>(); 
        genderField.setStyle(fieldStyle + "-fx-background-color: white;");

        TextField gender = new TextField(AdminDashboardPage.getAdminProfileModel().getGender());
        gender.setEditable(false);
        gender.setStyle(fieldStyle + "-fx-background-color: white;");
        
        Label dobLabel_ui = new Label("Date of Birth:");
        System.out.println(AdminDashboardPage.getAdminProfileModel().getDob());
        dobLabel_ui.setStyle(labelStyle);
        dobField = new DatePicker(); 
        dobField.setStyle(fieldStyle + "-fx-background-color: white;");
        dobField.setDisable(true); 

        TextField dateob = new TextField(AdminDashboardPage.getAdminProfileModel().getState());
        dateob.setEditable(false);
        dateob.setStyle(fieldStyle + "-fx-background-color: white;");

        Label emailLabel_ui = new Label("Email Address:"); 
        emailLabel_ui.setStyle(labelStyle);
        emailField = new TextField(AdminDashboardPage.getAdminProfileModel().getEmail()); 
        emailField.setStyle(fieldStyle + "-fx-background-color: white;");
        emailField.setEditable(false);

        Label addressLabel_ui = new Label("Address:"); 
        addressLabel_ui.setStyle(labelStyle);
        addressField = new TextField(AdminDashboardPage.getAdminProfileModel().getAddress()); 
        addressField.setStyle(fieldStyle);
        addressField.setEditable(false); 

        Label stateLabel_ui = new Label("State:"); 
        stateLabel_ui.setStyle(labelStyle);
        stateField = new ComboBox<>(); 
        stateField.setStyle(fieldStyle + "-fx-background-color: white;");
        stateField.setDisable(true); 

        TextField state = new TextField(AdminDashboardPage.getAdminProfileModel().getState());
        state.setEditable(false);
        state.setStyle(fieldStyle + "-fx-background-color: white;");

        Label pincodeLabel_ui = new Label("Pincode:"); 
        pincodeLabel_ui.setStyle(labelStyle);
        pincodeField = new TextField(AdminDashboardPage.getAdminProfileModel().getPincode()); 
        pincodeField.setStyle(fieldStyle);
        pincodeField.setEditable(false); 

        Label phoneLabel_ui = new Label("Mobile No:");
        phoneLabel_ui.setStyle(labelStyle);
        phoneField = new TextField(AdminDashboardPage.getAdminProfileModel().getMobile()); 
        phoneField.setStyle(fieldStyle);
        phoneField.setEditable(false);

        Label gstLabel_ui = new Label("GST Number:");
        gstLabel_ui.setStyle(labelStyle);
        gstField = new TextField(AdminDashboardPage.getAdminProfileModel().getGstNumber()); 
        gstField.setStyle(fieldStyle);
        gstField.setEditable(false);

        // --- Layouts for form fields (use renamed labels) ---
        VBox vbx1 = new VBox(8, firstNameLabel_ui, firstNameField);
        vbx1.setPrefWidth(250);
        VBox vbx2 = new VBox(8, lastNameLabel_ui, lastNameField);
        vbx2.setPrefWidth(250);
        HBox hbx1 = new HBox(30, vbx1, vbx2);
        hbx1.setAlignment(Pos.TOP_CENTER);

        VBox vbx3 = new VBox(8, genderLabel_ui, gender);
        vbx3.setPrefWidth(250);
        VBox vbx4 = new VBox(8, dobLabel_ui, dateob);
        vbx4.setPrefWidth(250);
        HBox hbx2 = new HBox(30, vbx3, vbx4);
        hbx2.setAlignment(Pos.TOP_CENTER);

        VBox vbx5 = new VBox(8, emailLabel_ui, emailField);
        vbx5.setPrefWidth(250);
        VBox vbx6 = new VBox(8, addressLabel_ui, addressField);
        vbx6.setPrefWidth(250);
        HBox hbx3 = new HBox(30, vbx5, vbx6);
        hbx3.setAlignment(Pos.TOP_CENTER);

        VBox vbx7 = new VBox(8, stateLabel_ui, state);
        vbx7.setPrefWidth(250);
        VBox vbx8 = new VBox(8, pincodeLabel_ui, pincodeField);
        vbx8.setPrefWidth(250);
        HBox hbx4 = new HBox(30, vbx7, vbx8);
        hbx4.setAlignment(Pos.TOP_CENTER);

        VBox vbx9 = new VBox(8, phoneLabel_ui, phoneField);
        vbx9.setPrefWidth(250);
        VBox vbx10 = new VBox(8, gstLabel_ui, gstField); // Use gstLabel_ui
        vbx10.setPrefWidth(250);
        HBox hbx5 = new HBox(30, vbx9, vbx10);
        hbx5.setAlignment(Pos.TOP_CENTER);

        // --- EDIT Button (Only "Edit" for this View Page) ---
        Button editButton = new Button("Edit Profile"); // Renamed from 'edit' to 'editButton' for clarity
        editButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);" + "-fx-text-fill: black;" +
                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                      "-fx-font-weight: bold; " +
                      "-fx-font-size: 15px; " +
                      "-fx-background-radius: 10; " +
                      "-fx-cursor: hand;");
        editButton.setPrefWidth(200);
        editButton.setPrefHeight(50);
        editButton.setMinHeight(50);
        editButton.setMaxHeight(50);

        // Hover effects for Edit button
        editButton.setOnMouseEntered(e -> editButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                "-fx-text-fill: white; " +
                                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                                "-fx-font-weight: bold; " +
                                                "-fx-font-size: 15px; " +
                                                "-fx-background-radius: 10; " +
                                                "-fx-cursor: hand; " +
                                                "-fx-scale-y: 1.05;"));
        
        editButton.setOnMouseExited(e -> editButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                "-fx-text-fill: white; " +
                                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                                                "-fx-font-weight: bold; " +
                                                "-fx-font-size: 15px; " +
                                                "-fx-background-radius: 10; " +
                                                "-fx-cursor: hand; " +
                                                "-fx-scale-y: 1.0;"));


        // --- "Edit Profile" Button Action: Navigate to AdminProfile (the setup/edit page) ---
        editButton.setOnAction(e -> {
            try {
                AdminProfile adminProfileSetupPage = new AdminProfile(); // Instantiate the AdminProfile setup page
                adminProfileSetupPage.setLoggedInAdminUid(loggedInAdminUid); // Pass context
                adminProfileSetupPage.setAdminEmail(adminEmail);
                adminProfileSetupPage.setAdminRole(adminRole);

                Scene adminProfileSetupScene = adminProfileSetupPage.getAdminProfilScene(primaryStage); // Get scene from the setup page
                primaryStage.setScene(adminProfileSetupScene); // Set the scene
                primaryStage.setTitle("TirthOnGo - Edit Admin Profile"); // Update title

            } catch (Exception ex) {
                System.err.println("Error navigating to Admin Profile setup page: " + ex.getMessage());
                ex.printStackTrace();
                showAlert("Navigation Error", "Failed to load admin profile edit page.", Alert.AlertType.ERROR);
            }
        });


        // Required fields notice (not really applicable for a view page, but can be kept)
        Label requiredNote = new Label("* Fields populated from saved profile data.");
        requiredNote.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d; -fx-font-style: italic;");
        
        // Enhanced form container with modern card design
        // Make sure to include hbx5 in this VBox
        VBox roundBox = new VBox(25, personalInfoHeader, hbx1, hbx2, hbx3, hbx4, hbx5, requiredNote, editButton); // Only editButton here
        roundBox.setMaxWidth(650);
        roundBox.setPadding(new Insets(35, 40, 35, 40));
        roundBox.setAlignment(Pos.TOP_CENTER);

        roundBox.setStyle(
            "-fx-background-color: linear-gradient(to left, #ffffffff, #f8c47bff) ; " +
            "-fx-background-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 20, 0, 0, 8);" +
            "-fx-border-color: rgba(102, 126, 234, 0.1);" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 15;"
        );

        VBox mainContainer = new VBox(20, VB, heading, imageSection, roundBox);
        mainContainer.setPadding(new Insets(0, 0, 30, 0));
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        mainContainer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        mainContainer.setStyle("-fx-background-color: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);");
        VBox.setVgrow(mainContainer, Priority.ALWAYS);

        ScrollPane scroll = new ScrollPane(mainContainer);
        scroll.setFitToWidth(true);
        scroll.setFitToHeight(true);
        scroll.setPannable(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color: transparent;");
        scroll.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(scroll, Priority.ALWAYS);


        Scene sc = new Scene(scroll, 1300, 700); 
        
        // --- NEW: Load existing profile data when scene is shown ---
        primaryStage.setOnShown(event -> {
            if (loggedInAdminUid != null && adminRole != null) { 
                String collectionToFetch = "admins"; 

                Map<String, Object> existingData = ProfileDao.fetchProfileData(collectionToFetch, loggedInAdminUid);
                if (existingData != null) {
                    populateFields(existingData, firstNameField, lastNameField, genderField, dobField, emailField, addressField, stateField, pincodeField, phoneField, gstField, profileImageView);
                } else {
                    if (this.adminEmail != null && !this.adminEmail.isEmpty()) { 
                        emailField.setText(this.adminEmail);
                    }
                    System.out.println("No existing admin profile found in Firestore for UID: " + loggedInAdminUid + ". Fields left empty.");
                }
            } else if (this.adminEmail != null && !this.adminEmail.isEmpty()) { 
                emailField.setText(this.adminEmail);
                System.err.println("Warning: AdminProfileViewPage loaded without full UID/Role context. Pre-filling email only.");
            } else {
                System.err.println("Warning: AdminProfileViewPage loaded without UID or Email. Cannot pre-populate fields.");
            }
            // All fields are read-only on this view page, so no need to call setFieldsEditable(false, ...) here
            // The text fields are set to editable=false directly in their declarations
        });

        // Set the scene on the primary stage only once the scene is fully built
        primaryStage.setScene(sc);
        primaryStage.setMaximized(true);
        primaryStage.sizeToScene(); 

        return sc; 
    }
    
    // --- Helper method to populate fields (now specifically for AdminProfileViewPage's read-only display) ---
    private void populateFields(Map<String, Object> data, TextField firstNameField, TextField lastNameField, ComboBox<String> genderField, DatePicker dobField, TextField emailField, TextField addressField, ComboBox<String> stateField, TextField pincodeField, TextField phoneField, TextField gstField, ImageView profileImageView) {
        if (data.containsKey("email") && data.get("email") != null) {
            emailField.setText(data.get("email").toString());
        } else if (this.adminEmail != null) { 
            emailField.setText(this.adminEmail);
        }
        
        if (data.containsKey("firstName") && data.get("firstName") != null) {
            firstNameField.setText(data.get("firstName").toString());
        }
        if (data.containsKey("lastName") && data.get("lastName") != null) {
            lastNameField.setText(data.get("lastName").toString());
        }
        // For ComboBoxes in a view-only context, if not editable, you just set the value if it's in the items
        if (data.containsKey("gender") && data.get("gender") != null) {
            genderField.getSelectionModel().select(data.get("gender").toString());
        }
        if (data.containsKey("dob") && data.get("dob") != null) {
            try {
                dobField.setValue(LocalDate.parse(data.get("dob").toString()));
            } catch (Exception e) {
                System.err.println("Error parsing DOB: " + data.get("dob") + " -> " + e.getMessage());
            }
        }
        if (data.containsKey("address") && data.get("address") != null) {
            addressField.setText(data.get("address").toString());
        }
        // For ComboBoxes in a view-only context, if not editable, you just set the value if it's in the items
        if (data.containsKey("state") && data.get("state") != null) {
            stateField.getSelectionModel().select(data.get("state").toString());
        }
        if (data.containsKey("pincode") && data.get("pincode") != null) {
            pincodeField.setText(data.get("pincode").toString());
        }
        if (data.containsKey("mobile") && data.get("mobile") != null) {
            phoneField.setText(data.get("mobile").toString());
        }
        if (data.containsKey("gstNumber") && data.get("gstNumber") != null) { 
            gstField.setText(data.get("gstNumber").toString());
        }
        
        // Image loading logic
        if (data.containsKey("profileImageUrl") && data.get("profileImageUrl") != null && !data.get("profileImageUrl").toString().isEmpty()) {
            try {
                String imageUrl = data.get("profileImageUrl").toString();
                Image profileImage = new Image(imageUrl, 150, 150, false, true); 
                profileImageView.setImage(profileImage);
            } catch (Exception e) {
                System.err.println("Error loading profile image from URL: " + e.getMessage());
            }
        }
    }

    // Helper method to set fields editable/non-editable (REMOVED from this class as fields are always read-only)
    // You set editable(false) directly in the TextField/ComboBox declarations for this view page
    private void setFieldsEditable(boolean editable, TextField firstNameField, TextField lastNameField, ComboBox<String> genderField, DatePicker dobField, TextField emailField, TextField addressField, ComboBox<String> stateField, TextField pincodeField, TextField phoneField, TextField gstField) {
        firstNameField.setEditable(editable);
        lastNameField.setEditable(editable);
        genderField.setDisable(!editable); // ComboBox disable
        dobField.setDisable(!editable);   // DatePicker disable
        emailField.setEditable(false); // Email always non-editable
        addressField.setEditable(editable);
        stateField.setDisable(!editable);
        pincodeField.setEditable(editable);
        phoneField.setEditable(editable);
        gstField.setEditable(editable); 
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
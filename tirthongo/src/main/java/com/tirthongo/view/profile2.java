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
// import javafx.scene.image.Image;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextField;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// //import javafx.scene.text.Text;
// import javafx.stage.FileChooser;
// import javafx.stage.Stage;

// public class profile2 {

    

//     public Scene getUserProfileViewScene(Stage primaryStage) throws Exception {
        
//                 // Enhanced Top bar with gradient and better spacing
//         HBox topBar = new HBox();
//         topBar.setPadding(new Insets(15));
//         topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 3);");
//         topBar.setAlignment(Pos.CENTER_LEFT);

//         // Enhanced title with shadow effect
//         Label title = new Label("🛐 TirthOnGo");
//         title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
//         title.setTextFill(Color.web("#ec7208ff")); 

//         topBar.getChildren().addAll(title);

//         HBox backRow = new HBox();
//         backRow.setPadding(new Insets(10, 30, 0, 30));
//         backRow.setAlignment(Pos.CENTER_LEFT);

//         Button backButton = new Button("← Back");
//         backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ec7208; -fx-font-size: 16px; -fx-font-weight: bold;");
//        backButton.setOnAction(e -> {
//     try {
//         // --- NEW: Directly navigate to the Homeload (User Dashboard) page ---
//         Homeload homePage = new Homeload(); // Instantiate Homeload          

//         // Get the home scene and set it on the primary stage
//         Scene homeScene = homePage.gethomescene(primaryStage); // Assuming getHomeScene method exists in Homeload
//         primaryStage.setScene(homeScene); 
//         primaryStage.setTitle("TirthOnGo - Home"); // Update window title

//     } catch (Exception ex) {
//         System.err.println("Error navigating back to Home Page: " + ex.getMessage());
//         ex.printStackTrace();
//         // You might want a more user-friendly alert here
//         showAlert("Navigation Error", "Failed to go back to the Home page. Please try again.", Alert.AlertType.ERROR);
//     }
// });

//         backRow.getChildren().add(backButton);

//         VBox VB = new VBox(10);
//         VB.getChildren().addAll(topBar,backButton);

//         // Enhanced welcome section with modern styling
//         Label heading = new Label("User Profile");
//         heading.setStyle("-fx-font-weight: bold; -fx-font-size: 32px; -fx-text-fill: #2c3e50;");
//         heading.setAlignment(Pos.CENTER);
        
//         // Label content = new Label("Complete your profile to unlock personalized travel experiences");
//         // content.setStyle("-fx-font-size: 16px; -fx-text-fill: #7f8c8d;");
//         // content.setAlignment(Pos.CENTER);

       
//         // Profile Image Upload Section
//         ImageView profileImageView = new ImageView();

//         // Load default image with proper resource handling
//         try {
//             InputStream imageStream = getClass().getResourceAsStream("/Assets/Images/ProfileImageIcon.jpg");
//             if (imageStream != null) {
//                 Image defaultImage = new Image(imageStream);
//                 profileImageView.setImage(defaultImage);
//             } else {
//                 System.out.println("Default image not found at: /Assets/Images/ProfileImageIcon.jpg");
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
//             File selectedFile = fileChooser.showOpenDialog(primaryStage);
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

//         //Text text = new Text();
        
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
//         VBox roundBox = new VBox(25, personalInfoHeader, hbx1, hbx2, hbx3, hbx4, requiredNote, edit,save);
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
//         VBox mainContainer = new VBox(20,VB,heading, imageSection, roundBox);
//         mainContainer.setPadding(new Insets(0, 0, 30, 0));
//         mainContainer.setAlignment(Pos.TOP_CENTER);
//         mainContainer.setStyle("-fx-background-color: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);");

//         ScrollPane scroll = new ScrollPane(mainContainer);
//         scroll.setFitToWidth(true);
//         scroll.setFitToHeight(true);
//         scroll.setPannable(true);
//         scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scroll.setStyle("-fx-background-color: transparent;");

//         Scene sc = new Scene(scroll, 1300, 700);
//         primaryStage.setScene(sc);
//         primaryStage.show();
//         return sc;
// }

// private void showAlert(String title, String message, Alert.AlertType alertType) {
//     Alert alert = new Alert(alertType);
//     alert.setTitle(title);
//     alert.setHeaderText(null); // No header text
//     alert.setContentText(message);
//     alert.showAndWait(); // Show the alert and wait for user to close it
// }
    
// }









package com.tirthongo.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import java.io.InputStream;
import java.util.Map;

import com.tirthongo.dao.ProfileDao;

public class profile2 { 

    private static String loggedInUserUid;
    private static String userEmail;
    private static String userRole;

    // UI Field References
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField genderField;
    private TextField dobField;
    private TextField emailField;
    private TextField addressField;
    private TextField stateField;
    private TextField pincodeField;
    private TextField phoneField;
    private ImageView profileImageView; 

    public void setLoggedInUserUid(String uid) { 
        this.loggedInUserUid = uid; 
        System.out.println("Profile2: Set UID = " + uid);
    }
    
    public void setUserEmail(String email) { 
        this.userEmail = email; 
        System.out.println("Profile2: Set Email = " + email);
    }
    
    public void setUserRole(String role) { 
        this.userRole = role; 
        System.out.println("Profile2: Set Role = " + role);
    }

    public Scene getUserProfileViewScene(Stage primaryStage) {
        System.out.println("=== Profile2 Scene Creation Started ===");
        System.out.println("UID: " + loggedInUserUid);
        System.out.println("Email: " + userEmail);
        System.out.println("Role: " + userRole);

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
        VB.getChildren().addAll(topBar, backButton);

        backButton.setOnAction(e -> {
            try {
                Homeload userDashboard = new Homeload();
                userDashboard.setLoggedInUserUid(loggedInUserUid);
                userDashboard.setUserEmail(userEmail);
                userDashboard.setUserRole(userRole);
                Scene dashboardScene = userDashboard.gethomescene(primaryStage);
                primaryStage.setScene(dashboardScene);
                primaryStage.setTitle("TirthOnGo - User Dashboard");
            } catch (Exception ex) {
                System.err.println("Error navigating back to User Dashboard: " + ex.getMessage());
                ex.printStackTrace();
                showAlert("Navigation Error", "Failed to go back to dashboard.", Alert.AlertType.ERROR);
            }
        });

        Label heading = new Label("My Profile");
        heading.setStyle("-fx-font-weight: bold; -fx-font-size: 32px; -fx-text-fill: #2c3e50;");
        heading.setAlignment(Pos.CENTER);
        
        // Initialize profile image
        profileImageView = new ImageView();
        try {
            InputStream imageStream = getClass().getResourceAsStream("Assets\\Images\\profileicon.png");
            if (imageStream != null) {
                Image defaultImage = new Image(imageStream);
                profileImageView.setImage(defaultImage);
            } else {
                System.out.println("Default image not found");
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

        StackPane imageStack = new StackPane();
        imageStack.getChildren().addAll(profileImageView, border);
        imageStack.setMaxSize(150, 150);
        imageStack.setPrefSize(150, 150);
        imageStack.setStyle("-fx-cursor: hand;");

        imageStack.setOnMouseClicked(event -> {
            showAlert("Info", "Click 'Edit Profile' to change your profile details including photo.", Alert.AlertType.INFORMATION);
        });

        Label uploadLabel = new Label("📷 Profile Photo"); 
        uploadLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #667eea;"); 

        VBox imageSection = new VBox(10, imageStack, uploadLabel);
        imageSection.setAlignment(Pos.CENTER);
        
        String fieldStyle = "-fx-background-color: #f8f9fa; -fx-border-color: #e9ecef; -fx-border-width: 1; " +
                            "-fx-border-radius: 8; -fx-background-radius: 8; -fx-padding: 12; " +
                            "-fx-font-size: 14px; -fx-pref-height: 45;";
        
        String labelStyle = "-fx-font-weight: 600; -fx-text-fill: #2c3e50; -fx-font-size: 14px;";

        Label personalInfoHeader = new Label("👤 Personal Information");
        personalInfoHeader.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; -fx-text-fill: #34495e; -fx-padding: 0 0 10 0;");

        // Initialize all fields
        Label firstNameLabel_ui = new Label("First Name:"); 
        firstNameLabel_ui.setStyle(labelStyle);
        firstNameField = new TextField(Homeload.getUserProfileModel().getFirstName());
        firstNameField.setStyle(fieldStyle);
        firstNameField.setEditable(false); 
        
        Label lastNameLabel_ui = new Label("Last Name:");
        lastNameLabel_ui.setStyle(labelStyle);
        lastNameField = new TextField(Homeload.getUserProfileModel().getLastName());
        lastNameField.setStyle(fieldStyle);
        lastNameField.setEditable(false); 

        Label genderLabel_ui = new Label("Gender:");
        genderLabel_ui.setStyle(labelStyle);
        genderField = new TextField(Homeload.getUserProfileModel().getGender());
        genderField.setEditable(false);
        genderField.setStyle(fieldStyle + "-fx-background-color: white;");
        
        Label dobLabel_ui = new Label("Date of Birth:");
        dobLabel_ui.setStyle(labelStyle);
        dobField = new TextField(Homeload.getUserProfileModel().getDob());
        dobField.setEditable(false);
        dobField.setStyle(fieldStyle + "-fx-background-color: white;");

        Label emailLabel_ui = new Label("Email Address:"); 
        emailLabel_ui.setStyle(labelStyle);
        emailField = new TextField(Homeload.getUserProfileModel().getEmail());
        emailField.setStyle(fieldStyle);
        emailField.setEditable(false);

        Label addressLabel_ui = new Label("Address:"); 
        addressLabel_ui.setStyle(labelStyle);
        addressField = new TextField(Homeload.getUserProfileModel().getAddress());
        addressField.setStyle(fieldStyle);
        addressField.setEditable(false); 

        Label stateLabel_ui = new Label("State:"); 
        stateLabel_ui.setStyle(labelStyle);
        stateField = new TextField(Homeload.getUserProfileModel().getState());
        stateField.setEditable(false);
        stateField.setStyle(fieldStyle + "-fx-background-color: white;");

        Label pincodeLabel_ui = new Label("Pincode:"); 
        pincodeLabel_ui.setStyle(labelStyle);
        pincodeField = new TextField(Homeload.getUserProfileModel().getPincode());
        pincodeField.setStyle(fieldStyle);
        pincodeField.setEditable(false); 

        Label phoneLabel_ui = new Label("Mobile No:");
        phoneLabel_ui.setStyle(labelStyle);
        phoneField = new TextField(Homeload.getUserProfileModel().getMobile());
        phoneField.setStyle(fieldStyle);
        phoneField.setEditable(false);

        // Create layout boxes
        VBox vbx1 = new VBox(8, firstNameLabel_ui, firstNameField);
        vbx1.setPrefWidth(250);
        VBox vbx2 = new VBox(8, lastNameLabel_ui, lastNameField);
        vbx2.setPrefWidth(250);
        HBox hbx1 = new HBox(30, vbx1, vbx2);
        hbx1.setAlignment(Pos.TOP_CENTER);

        VBox vbx3 = new VBox(8, genderLabel_ui, genderField);
        vbx3.setPrefWidth(250);
        VBox vbx4 = new VBox(8, dobLabel_ui, dobField);
        vbx4.setPrefWidth(250);
        HBox hbx2 = new HBox(30, vbx3, vbx4);
        hbx2.setAlignment(Pos.TOP_CENTER);

        VBox vbx5 = new VBox(8, emailLabel_ui, emailField); 
        vbx5.setPrefWidth(250);
        VBox vbx6 = new VBox(8, addressLabel_ui, addressField); 
        vbx6.setPrefWidth(250);
        HBox hbx3 = new HBox(30, vbx5, vbx6);
        hbx3.setAlignment(Pos.TOP_CENTER);

        VBox vbx7 = new VBox(8, stateLabel_ui, stateField); 
        vbx7.setPrefWidth(250);
        VBox vbx8 = new VBox(8, pincodeLabel_ui, pincodeField); 
        vbx8.setPrefWidth(250);
        HBox hbx4 = new HBox(30, vbx7, vbx8);
        hbx4.setAlignment(Pos.TOP_CENTER);

        VBox vbx9 = new VBox(8, phoneLabel_ui, phoneField);
        vbx9.setPrefWidth(250);
        HBox hbx5 = new HBox(30, vbx9); 
        hbx5.setAlignment(Pos.TOP_CENTER);

        Button editProfileButton = new Button("Edit Profile"); 
        editProfileButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);" + "-fx-text-fill: black;" +
                      "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                      "-fx-font-weight: bold; " +
                      "-fx-font-size: 15px; " +
                      "-fx-background-radius: 10; " +
                      "-fx-cursor: hand;");
        editProfileButton.setPrefWidth(200);
        editProfileButton.setPrefHeight(50);

        editProfileButton.setOnMouseEntered(e -> editProfileButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                "-fx-text-fill: white; " +
                                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 10, 0, 0, 4); " +
                                                "-fx-font-weight: bold; " +
                                                "-fx-font-size: 15px; " +
                                                "-fx-background-radius: 10; " +
                                                "-fx-cursor: hand; " +
                                                "-fx-scale-y: 1.05;"));
        
        editProfileButton.setOnMouseExited(e -> editProfileButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); " +
                                                "-fx-text-fill: white; " +
                                                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 8, 0, 0, 3); " +
                                                "-fx-font-weight: bold; " +
                                                "-fx-font-size: 15px; " +
                                                "-fx-background-radius: 10; " +
                                                "-fx-cursor: hand; " +
                                                "-fx-scale-y: 1.0;"));

        editProfileButton.setOnAction(e -> {
            try {
                UserProfile userProfileSetupPage = new UserProfile();
                userProfileSetupPage.setLoggedInUserUid(loggedInUserUid);
                userProfileSetupPage.setUserEmail(userEmail);
                userProfileSetupPage.setUserRole(userRole);

                Scene userProfileSetupScene = userProfileSetupPage.getProfilScene(primaryStage);
                primaryStage.setScene(userProfileSetupScene);
                primaryStage.setTitle("TirthOnGo - Edit Profile");

            } catch (Exception ex) {
                System.err.println("Error navigating to User Profile setup page: " + ex.getMessage());
                ex.printStackTrace();
                showAlert("Navigation Error", "Failed to load user profile edit page.", Alert.AlertType.ERROR);
            }
        });

        Label requiredNote = new Label("* All fields are read-only. Click 'Edit Profile' to modify.");
        requiredNote.setStyle("-fx-font-size: 12px; -fx-text-fill: #7f8c8d; -fx-font-style: italic;");
        
        VBox roundBox = new VBox(25, personalInfoHeader, hbx1, hbx2, hbx3, hbx4, hbx5, requiredNote, editProfileButton);
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

        Scene sc = new Scene(scroll, primaryStage.getWidth(), primaryStage.getHeight()); 
        
        // Load profile data when scene is shown
        primaryStage.setOnShown(event -> {
            System.out.println("=== Profile2 Scene Shown Event ===");
            System.out.println("Loading profile data for UID: " + loggedInUserUid);
            loadProfileData();
        });

        primaryStage.setScene(sc);
        primaryStage.setMaximized(true);
        primaryStage.sizeToScene(); 

        return sc; 
    }
    
    // NEW METHOD: Load profile data from Firestore
    private void loadProfileData() {
        System.out.println("=== Loading Profile Data ===");
        
        // Set email first if available
        if (this.userEmail != null && !this.userEmail.isEmpty()) {
            emailField.setText(this.userEmail);
            System.out.println("Set email field: " + this.userEmail);
        }

        if (loggedInUserUid != null && !loggedInUserUid.isEmpty() && userRole != null) { 
            String collectionToFetch = "Travellers"; // Default to Travellers collection
            
            System.out.println("Fetching from collection: " + collectionToFetch);
            System.out.println("Document ID (UID): " + loggedInUserUid);

            try {
                // Use the ProfileDao to fetch data
                Map<String, Object> existingData = ProfileDao.fetchProfileData(collectionToFetch, loggedInUserUid);
                
                if (existingData != null && !existingData.isEmpty()) {
                    System.out.println("Profile data fetched successfully!");
                    System.out.println("Data keys: " + existingData.keySet());
                    populateFields(existingData);
                } else {
                    System.out.println("No profile data found for UID: " + loggedInUserUid);
                    showAlert("No Profile Data", 
                             "No profile information found. Please update your profile.", 
                             Alert.AlertType.INFORMATION);
                }
            } catch (Exception e) {
                System.err.println("Error loading profile data: " + e.getMessage());
                e.printStackTrace();
                showAlert("Data Load Error", 
                         "Failed to load profile data. Please check your connection.", 
                         Alert.AlertType.ERROR);
            }
        } else {
            System.err.println("Missing required user context:");
            System.err.println("  UID: " + loggedInUserUid);
            System.err.println("  Role: " + userRole);
            showAlert("Missing Data", 
                     "User session information is missing. Please log in again.", 
                     Alert.AlertType.WARNING);
        }
    }

    // UPDATED METHOD: Populate fields with fetched data
    private void populateFields(Map<String, Object> data) {
        System.out.println("=== Populating Profile Fields ===");
        
        if (data == null) {
            System.err.println("Data map is null!");
            return;
        }

        // Populate each field
        populateField("firstName", data, firstNameField);
        populateField("lastName", data, lastNameField);
        populateField("gender", data, genderField);
        populateField("dob", data, dobField);
        populateField("email", data, emailField);
        populateField("address", data, addressField);
        populateField("state", data, stateField);
        populateField("pincode", data, pincodeField);
        populateField("mobile", data, phoneField);
        
        // Handle profile image
        if (data.containsKey("profileImageUrl") && data.get("profileImageUrl") != null && 
            !data.get("profileImageUrl").toString().isEmpty()) {
            try {
                String imageUrl = data.get("profileImageUrl").toString();
                System.out.println("Loading profile image from: " + imageUrl);
                Image profileImage = new Image(imageUrl, 150, 150, false, true); 
                profileImageView.setImage(profileImage);
                // Reapply clip after setting new image
                Circle newClip = new Circle(75, 75, 75);
                profileImageView.setClip(newClip);
            } catch (Exception e) {
                System.err.println("Error loading profile image: " + e.getMessage());
            }
        }
        
        System.out.println("=== Field Population Complete ===");
    }
    
    // HELPER METHOD: Populate individual field
    private void populateField(String key, Map<String, Object> data, TextField field) {
        if (data.containsKey(key) && data.get(key) != null) {
            String value = data.get(key).toString();
            field.setText(value);
            System.out.println("Set " + key + ": " + value);
        } else {
            System.out.println("No data found for key: " + key);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
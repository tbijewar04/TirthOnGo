// package com.tirthongo.view;

// import java.io.File;
// import java.util.ArrayList;
// import java.util.List;
// import javafx.animation.FadeTransition;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// // import javafx.scene.control.MenuButton;
// // import javafx.scene.control.MenuItem;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextArea;
// import javafx.scene.control.TextField;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// // import javafx.scene.paint.ImagePattern;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.FileChooser;
// import javafx.stage.Stage;
// import javafx.util.Duration;

// public class add_destination {

//      private ImageView mainImageView = new ImageView();
//     private HBox galleryBox = new HBox(10);
//     private VBox itineraryBox = new VBox(10);
//     private List<TextField> dayFields = new ArrayList<>();

//     private Stage primaryStage;



//     public Scene getDScene(Stage primarysStage) throws Exception {

//         this.primaryStage = primarysStage;

//         HBox topBar = new HBox(10);
//         topBar.setPadding(new Insets(15));
//         topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffff, #f9970d); -fx-padding: 10 20 10 20;");
//         topBar.setAlignment(Pos.CENTER_LEFT);

//         Label title = new Label("TirthOnGo");
//         title.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
//         title.setTextFill(Color.web("#ff7e0eff"));
//         //title.setEffect(new DropShadow(5, Color.GREY));

//         Region spacerTop = new Region();
//         HBox.setHgrow(spacerTop, Priority.ALWAYS);

//         // 
        
//         Image profileImg = new Image("Assests\\Images\\vecteezy_user-icon-on-transparent-background_19879198.png", 30, 30, true, true);
//         ImageView profileIcon = new ImageView(profileImg);
//         profileIcon.setFitWidth(40);
//         profileIcon.setFitHeight(40);
//         Circle clip = new Circle(20, 20, 20);
//         profileIcon.setClip(clip);

//         topBar.getChildren().addAll(title, spacerTop, profileIcon);

//         // Back button row
//         HBox backRow = new HBox();
//         backRow.setPadding(new Insets(10, 30, 0, 30));
//         backRow.setAlignment(Pos.CENTER_LEFT);

//         Button backButton = new Button("← Back");
//         backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ec7208; -fx-font-size: 16px; -fx-font-weight: bold;");
//         backButton.setOnAction(e -> {
//             if (primaryStage.getUserData() instanceof Runnable) {
//                 ((Runnable) primaryStage.getUserData()).run();
//             }
//         });

//         backRow.getChildren().add(backButton);

//         Label heading = new Label("Add New Destination");
//         heading.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
//         heading.setTextFill(Color.web("#1f3a93"));
//         heading.setEffect(new DropShadow(5, Color.LIGHTGRAY));

//         FadeTransition fade = new FadeTransition(Duration.seconds(2), heading);
//         fade.setFromValue(0.6);
//         fade.setToValue(1.0);
//         fade.setCycleCount(FadeTransition.INDEFINITE);
//         fade.setAutoReverse(true);
//         fade.play();

//         VBox pageWrapper = new VBox(20);
//         pageWrapper.setAlignment(Pos.TOP_CENTER);
//         pageWrapper.setPadding(new Insets(30));
//         pageWrapper.setStyle("-fx-background-color: #f3f7fb;");

//         VBox formCard = new VBox(20);
//         formCard.setPadding(new Insets(30));
//         formCard.setAlignment(Pos.CENTER);
//         formCard.setMaxWidth(780);
//         formCard.setStyle("-fx-background-color: linear-gradient(to top left, #ffffff, #ffe4b5); -fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #d8b57f; -fx-border-width: 2;");
//         formCard.setEffect(new DropShadow(10, Color.LIGHTGRAY));

//         TextField fromField = styledTextField("From (e.g. Pune) *");
//         TextField toField = styledTextField("To (e.g. Ayodhya) *");
//         TextField stayDays = styledTextField("e.g. Kashi - 2 Nights Stay");
//         TextArea infoField = styledTextArea("Enter information about destination *");

//         Button uploadMainImage = styledButton("\uD83D\uDCF7 Upload Main Image");
//         uploadMainImage.setOnAction(e -> {
//             File file = showImageChooser(primarysStage);
//             if (file != null) {
//                 mainImageView.setImage(new Image(file.toURI().toString()));
//                 mainImageView.setFitWidth(600);
//                 mainImageView.setFitHeight(300);
//                 mainImageView.setPreserveRatio(false);
//                 mainImageView.setStyle("-fx-background-radius: 10;");
//             }
//         });

//         mainImageView.setStyle("-fx-background-color: #ececec; -fx-background-radius: 10;");
//         mainImageView.setEffect(new DropShadow(10, Color.LIGHTGRAY));

//         Label galleryLabel = sectionLabel("Gallery Images:");
//         Button addGalleryImage = styledButton("➕ Add Gallery Image");
//         addGalleryImage.setOnAction(e -> {
//             File file = showImageChooser(primarysStage);
//             if (file != null) {
//                 ImageView img = new ImageView(new Image(file.toURI().toString()));
//                 img.setFitWidth(100);
//                 img.setFitHeight(80);
//                 img.setPreserveRatio(false);
//                 img.setStyle("-fx-background-radius: 6;");
//                 img.setEffect(new DropShadow(5, Color.GRAY));
//                 galleryBox.getChildren().add(img);
//             }
//         });
//         galleryBox.setStyle("-fx-padding: 10;");

//         Label itineraryLabel = sectionLabel("Itinerary Days:");
//         Button addDay = styledButton("➕ Add Day");
//         addDay.setOnAction(e -> {
//             int dayNumber = dayFields.size() + 1;
//          TextField dayField = styledTextField("Day " + dayNumber + " - Description *");
//             itineraryBox.getChildren().add(dayField);
//             dayFields.add(dayField);
//         });

//         TextField priceField = styledTextField("Enter Price per adult *");
//         TextField payToBookField = styledTextField("Enter Pay to Book amount *");
//         TextField guideInfoField = styledTextField("Enter Tour Guide Name ,contact*");

//         Button submitButton = new Button("SUBMIT");
//         submitButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-radius: 10;");
//         HBox.setHgrow(submitButton, Priority.ALWAYS);
//         submitButton.setMaxWidth(300);
//         submitButton.setPrefHeight(50);
//         submitButton.setEffect(new DropShadow(8, Color.DARKGRAY));

//         submitButton.setOnAction(e -> {
//             if (fromField.getText().isEmpty() || toField.getText().isEmpty() || stayDays.getText().isEmpty() || infoField.getText().isEmpty() ||
//                 priceField.getText().isEmpty() || payToBookField.getText().isEmpty() || guideInfoField.getText().isEmpty() ||
//                 dayFields.stream().anyMatch(field -> field.getText().isEmpty())) {

//                 Alert alert = new Alert(Alert.AlertType.WARNING);
//                 alert.setTitle("Missing Fields");
//                 alert.setHeaderText(null);
//                 alert.setContentText("Please fill in all required fields marked with *");
//                 alert.showAndWait();
//                 return;
//             }
//         });

//         formCard.getChildren().addAll(
//                 fromField,
//                 toField,
//                 stayDays,
//                 infoField,
//                 uploadMainImage,
//                 mainImageView,
//                 galleryLabel,
//                 addGalleryImage,
//                 galleryBox,
//                 itineraryLabel,
//                 addDay,
//                 itineraryBox,
//                 priceField,
//                 payToBookField,
//                 guideInfoField,
//                 submitButton
//         );

//         pageWrapper.getChildren().addAll(backRow, heading, formCard);
//         VBox root = new VBox(topBar, pageWrapper);

//         ScrollPane scrollPane = new ScrollPane(root);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setFitToHeight(true);
//         scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scrollPane.setStyle("-fx-background: #f3f7fb;");
//         scrollPane.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
//         VBox.setVgrow(scrollPane, Priority.ALWAYS);

//         Scene scene = new Scene(scrollPane, primarysStage.getWidth(), primarysStage.getHeight());
//         primaryStage.show();
//         return scene; 
//     }

//     private File showImageChooser(Stage stage) {
//         FileChooser fileChooser = new FileChooser();
//         fileChooser.setTitle("Select Image");
//         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", ".jpg", ".jpeg", "*.png"));
//         return fileChooser.showOpenDialog(stage);
//     }

//     private TextField styledTextField(String prompt) {
//         TextField tf = new TextField();
//         tf.setPromptText(prompt);
//         tf.setStyle("-fx-font-size: 14px; -fx-padding: 10; -fx-background-radius: 10; -fx-border-color: #cccccc; -fx-border-radius: 10;");
//         tf.setPrefWidth(600);
//         return tf;
//     }

//     private TextArea styledTextArea(String prompt) {
//         TextArea ta = new TextArea();
//         ta.setPromptText(prompt);
//         ta.setWrapText(true);
//         ta.setPrefRowCount(4);
//         ta.setPrefWidth(600);
//         ta.setStyle("-fx-font-size: 14px; -fx-padding: 10; -fx-background-radius: 10; -fx-border-color: #cccccc; -fx-border-radius: 10;");
//         return ta;
//     }

//     private Button styledButton(String text) {
//         Button btn = new Button(text);
//         btn.setStyle("-fx-background-color: #ffffff; -fx-border-color: #00bcd4; -fx-text-fill: #00bcd4; -fx-font-weight: bold; -fx-padding: 8 20; -fx-background-radius: 10;");
//         btn.setEffect(new DropShadow(5, Color.LIGHTGRAY));
//         return btn;
//     }

//     private Label sectionLabel(String text) {
//         Label label = new Label(text);
//         label.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
//         label.setTextFill(Color.web("#333"));
//         return label;
//     }
// }

// package com.tirthongo.view;

// import java.io.File;
// import java.io.FileInputStream; // Added for image loading if needed later
// import java.io.InputStream; // Added for resource loading
// import java.util.ArrayList;
// import java.util.List;
// import javafx.animation.FadeTransition;
// import javafx.geometry.Insets;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.TextArea;
// import javafx.scene.control.TextField;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.FileChooser;
// import javafx.stage.Stage;
// import javafx.util.Duration;

// import com.tirthongo.dao.DestinationDao;
// import com.tirthongo.model.DestinationModel;
// // --- NEW IMPORTS ---
// import com.tirthongo.view.AdminDashboardPage; // For back navigation


// public class add_destination {

//     private ImageView mainImageView = new ImageView();
//     private HBox galleryBox = new HBox(10);
//     private VBox itineraryBox = new VBox(10);
//     private List<TextField> dayFields = new ArrayList<>();

//     private Stage primaryStage; // Renamed from primarysStage for consistency


//     // Context from AdminDashboardPage (if you need it for back button or saving)
//     private String loggedInAdminUid;
//     private String adminEmail;
//     private String adminRole;

//     public void setLoggedInAdminUid(String uid) { this.loggedInAdminUid = uid; }
//     public void setAdminEmail(String email) { this.adminEmail = email; }
//     public void setAdminRole(String role) { this.adminRole = role; }


//     public Scene getDestinationScene(Stage primaryStage) throws Exception { // Renamed parameter to primaryStage

//         this.primaryStage = primaryStage; // Assign to class member

//         HBox topBar = new HBox(10);
//         topBar.setPadding(new Insets(15));
//         topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffff, #f9970d); -fx-padding: 10 20 10 20;");
//         topBar.setAlignment(Pos.CENTER_LEFT);

//         Label title = new Label("TirthOnGo");
//         title.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
//         title.setTextFill(Color.web("#ff7e0eff"));
//         //title.setEffect(new DropShadow(5, Color.GREY));

//         Region spacerTop = new Region();
//         HBox.setHgrow(spacerTop, Priority.ALWAYS);

//         // --- FIXED IMAGE PATH for profile icon ---
//         Image profileImg = null;
//         try {
//             // Corrected typo "Assests" to "Assets", and used forward slashes with getResource
//             profileImg = new Image(getClass().getResource("Assets\\Images\\profileicon.png").toExternalForm(), 30, 30, true, true);
//         } catch (NullPointerException e) {
//             System.err.println("ERROR: Profile icon image not found in add_destination. Verify path: /Assets/Images/vecteezy_user-icon-on-transparent-background_19879198.png");
//             profileImg = new Image("data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs="); // Fallback transparent image
//         }
//         ImageView profileIcon = new ImageView(profileImg);
//         profileIcon.setFitWidth(40);
//         profileIcon.setFitHeight(40);
//         Circle clip = new Circle(20, 20, 20);
//         profileIcon.setClip(clip);

//         topBar.getChildren().addAll(title, spacerTop, profileIcon);

//         // Back button row
//         HBox backRow = new HBox();
//         backRow.setPadding(new Insets(10, 30, 0, 30));
//         backRow.setAlignment(Pos.CENTER_LEFT);

//         Button backButton = new Button("← Back");
//         backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ec7208; -fx-font-size: 16px; -fx-font-weight: bold;");
//         // --- FIXED BACK BUTTON ACTION: Navigate directly to AdminDashboardPage ---
//         backButton.setOnAction(e -> {
//             try {
//                 AdminDashboardPage adminDashboard = new AdminDashboardPage();
//                 // Pass back any necessary admin context
//                 adminDashboard.setLoggedInAdminUid(loggedInAdminUid);
//                 adminDashboard.setAdminEmail(adminEmail);
//                 adminDashboard.setAdminRole(adminRole);

//                 Scene adminDashboardScene = adminDashboard.getAdminDashboardScene(primaryStage);
//                 primaryStage.setScene(adminDashboardScene);
//                 primaryStage.setTitle("TirthOnGo - Admin Dashboard");
//             } catch (Exception ex) {
//                 System.err.println("Error navigating back to Admin Dashboard from add_destination: " + ex.getMessage());
//                 ex.printStackTrace();
//                 showAlert("Navigation Error", "Failed to go back to dashboard.", Alert.AlertType.ERROR);
//             }
//         });

//         backRow.getChildren().add(backButton);

//         Label heading = new Label("Add New Destination");
//         heading.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
//         heading.setTextFill(Color.web("#1f3a93"));
//         heading.setEffect(new DropShadow(5, Color.LIGHTGRAY));

//         FadeTransition fade = new FadeTransition(Duration.seconds(2), heading);
//         fade.setFromValue(0.6);
//         fade.setToValue(1.0);
//         fade.setCycleCount(FadeTransition.INDEFINITE);
//         fade.setAutoReverse(true);
//         fade.play();

//         VBox pageWrapper = new VBox(20);
//         pageWrapper.setAlignment(Pos.TOP_CENTER);
//         pageWrapper.setPadding(new Insets(30));
//         pageWrapper.setStyle("-fx-background-color: #f3f7fb;");

//         VBox formCard = new VBox(20);
//         formCard.setPadding(new Insets(30));
//         formCard.setAlignment(Pos.CENTER);
//         formCard.setMaxWidth(780);
//         formCard.setStyle("-fx-background-color: linear-gradient(to top left, #ffffff, #ffe4b5); -fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #d8b57f; -fx-border-width: 2;");
//         formCard.setEffect(new DropShadow(10, Color.LIGHTGRAY));

//         TextField fromField = styledTextField("From (e.g. Pune) *");
//         TextField toField = styledTextField("To (e.g. Ayodhya) *");
//         TextField stayDays = styledTextField("e.g. Kashi - 2 Nights Stay");
//         TextArea infoField = styledTextArea("Enter information about destination *");

//         Button uploadMainImage = styledButton("\uD83D\uDCF7 Upload Main Image");
//         uploadMainImage.setOnAction(e -> {
//             File file = showImageChooser(primaryStage); // Use primaryStage
//             if (file != null) {
//                 mainImageView.setImage(new Image(file.toURI().toString()));
//                 mainImageView.setFitWidth(600);
//                 mainImageView.setFitHeight(300);
//                 mainImageView.setPreserveRatio(false); // This is fine if you want it to stretch to these dimensions
//                 mainImageView.setStyle("-fx-background-radius: 10;");
//             }
//         });

//         mainImageView.setStyle("-fx-background-color: #ececec; -fx-background-radius: 10;");
//         mainImageView.setEffect(new DropShadow(10, Color.LIGHTGRAY));

//         Label galleryLabel = sectionLabel("Gallery Images:");
//         Button addGalleryImage = styledButton("➕ Add Gallery Image");
//         addGalleryImage.setOnAction(e -> {
//             File file = showImageChooser(primaryStage); // Use primaryStage
//             if (file != null) {
//                 ImageView img = new ImageView(new Image(file.toURI().toString()));
//                 img.setFitWidth(100);
//                 img.setFitHeight(80);
//                 img.setPreserveRatio(false); // This is fine
//                 img.setStyle("-fx-background-radius: 6;");
//                 img.setEffect(new DropShadow(5, Color.GRAY));
//                 galleryBox.getChildren().add(img);
//             }
//         });
//         galleryBox.setStyle("-fx-padding: 10;");

//         Label itineraryLabel = sectionLabel("Itinerary Days:");
//         Button addDay = styledButton("➕ Add Day");
//         addDay.setOnAction(e -> {
//             int dayNumber = dayFields.size() + 1;
//             TextField dayField = styledTextField("Day " + dayNumber + " - Description *");
//             itineraryBox.getChildren().add(dayField);
//             dayFields.add(dayField);
//         });

//         TextField priceField = styledTextField("Enter Price per adult *");
//         TextField payToBookField = styledTextField("Enter Pay to Book amount *");
//         TextField guideInfoField = styledTextField("Enter Tour Guide Name ,contact*");

//         Button submitButton = new Button("SUBMIT");
//         submitButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-radius: 10;");
//         HBox.setHgrow(submitButton, Priority.ALWAYS);
//         submitButton.setMaxWidth(300);
//         submitButton.setPrefHeight(50);
//         submitButton.setEffect(new DropShadow(8, Color.DARKGRAY));

//         submitButton.setOnAction(e -> {
//             if (fromField.getText().isEmpty() || toField.getText().isEmpty() || stayDays.getText().isEmpty() || infoField.getText().isEmpty() ||
//                 priceField.getText().isEmpty() || payToBookField.getText().isEmpty() || guideInfoField.getText().isEmpty() ||
//                 dayFields.stream().anyMatch(field -> field.getText().isEmpty())) {

//                 Alert alert = new Alert(Alert.AlertType.WARNING);
//                 alert.setTitle("Missing Fields");
//                 alert.setHeaderText(null);
//                 alert.setContentText("Please fill in all required fields marked with *");
//                 alert.showAndWait();
//                 return;
//             }
//             DestinationModel model = new DestinationModel();
//                 model.setBookAmount(payToBookField.getText());

//                 List<String> list = new ArrayList();
//                 for(int i = 0;i<dayFields.size();i++){
//                     list.add(dayFields.get(i).getText());
//                 }
//                 model.setDaysDescription(list);
//                 model.setDescription(infoField.getText());
//                 model.setEndDestination(toField.getText());
//                 model.setGalleryImageUrl(imageUrls);
//                 model.setMainImageUrl(imageUrl);
//                 model.setGuideName(guideInfoField.getText());
//                 model.setPricePerAdult(priceField.getText());
//                 model.setStartDestination(fromField.getText());

//                 DestinationDao.addDesitnationToFirestore(model);
//                 System.out.println(); 
//             // TODO: Implement data saving for new destination to Firestore
//             showAlert("Success", "Destination added successfully! (Data not yet saved to Firestore)", Alert.AlertType.INFORMATION);
//         });
//         //     // TODO: Implement data saving for new destination to Firestore
//         //     showAlert("Success", "Destination added successfully! (Data not yet saved to Firestore)", Alert.AlertType.INFORMATION);
//         // });

//         formCard.getChildren().addAll(
//                 fromField,
//                 toField,
//                 stayDays,
//                 infoField,
//                 uploadMainImage,
//                 mainImageView,
//                 galleryLabel,
//                 addGalleryImage,
//                 galleryBox,
//                 itineraryLabel,
//                 addDay,
//                 itineraryBox,
//                 priceField,
//                 payToBookField,
//                 guideInfoField,
//                 submitButton
//         );

//         pageWrapper.getChildren().addAll(backRow, heading, formCard);
//         VBox root = new VBox(topBar, pageWrapper);

//         ScrollPane scrollPane = new ScrollPane(root);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setFitToHeight(true);
//         scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scrollPane.setStyle("-fx-background: #f3f7fb;");
//         scrollPane.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
//         VBox.setVgrow(scrollPane, Priority.ALWAYS);

//         Scene scene = new Scene(scrollPane, primaryStage.getWidth(), primaryStage.getHeight()); // Use primaryStage
//         // No need for primaryStage.show() here, as it's handled by the calling page (AdminDashboardPage)
//         return scene; 
//     }

//     private File showImageChooser(Stage stage) {
//         FileChooser fileChooser = new FileChooser();
//         fileChooser.setTitle("Select Image");
//         fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg", "*.png")); // Corrected filter string
//         return fileChooser.showOpenDialog(stage);
//     }

//     private TextField styledTextField(String prompt) {
//         TextField tf = new TextField();
//         tf.setPromptText(prompt);
//         tf.setStyle("-fx-font-size: 14px; -fx-padding: 10; -fx-background-radius: 10; -fx-border-color: #cccccc; -fx-border-radius: 10;");
//         tf.setPrefWidth(600);
//         return tf;
//     }

//     private TextArea styledTextArea(String prompt) {
//         TextArea ta = new TextArea();
//         ta.setPromptText(prompt);
//         ta.setWrapText(true);
//         ta.setPrefRowCount(4);
//         ta.setPrefWidth(600);
//         ta.setStyle("-fx-font-size: 14px; -fx-padding: 10; -fx-background-radius: 10; -fx-border-color: #cccccc; -fx-border-radius: 10;");
//         return ta;
//     }

//     private Button styledButton(String text) {
//         Button btn = new Button(text);
//         btn.setStyle("-fx-background-color: #ffffff; -fx-border-color: #00bcd4; -fx-text-fill: #00bcd4; -fx-font-weight: bold; -fx-padding: 8 20; -fx-background-radius: 10;");
//         btn.setEffect(new DropShadow(5, Color.LIGHTGRAY));
//         return btn;
//     }

//     private Label sectionLabel(String text) {
//         Label label = new Label(text);
//         label.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
//         label.setTextFill(Color.web("#333"));
//         return label;
//     }

//     // Helper method to show alerts (added as it was missing)
//     private void showAlert(String title, String message, Alert.AlertType alertType) {
//         Alert alert = new Alert(alertType);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }
// }


package com.tirthongo.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import com.tirthongo.dao.DestinationDao;
import com.tirthongo.model.DestinationModel;


public class add_destination {

    private ImageView mainImageView = new ImageView();
    private HBox galleryBox = new HBox(10);
    private VBox itineraryBox = new VBox(10);
    private List<TextField> dayFields = new ArrayList<>();

    private Stage primaryStage; // Renamed from primarysStage for consistency
    List<String> imageUrls = new ArrayList<>();
    private String imageUrl = "";


    // Context from AdminDashboardPage (if you need it for back button or saving)
    private String loggedInAdminUid;
    private String adminEmail;
    private String adminRole;

    public void setLoggedInAdminUid(String uid) { this.loggedInAdminUid = uid; }
    public void setAdminEmail(String email) { this.adminEmail = email; }
    public void setAdminRole(String role) { this.adminRole = role; }


    public Scene getDestinationScene(Stage primaryStage) throws Exception { // Renamed parameter to primaryStage

        this.primaryStage = primaryStage; // Assign to class member

        HBox topBar = new HBox(10);
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffff, #f9970d); -fx-padding: 10 20 10 20;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("ॐ TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
        title.setTextFill(Color.web("#ff7e0eff"));
        //title.setEffect(new DropShadow(5, Color.GREY));

        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, Priority.ALWAYS);

        // --- FIXED IMAGE PATH for profile icon ---
        Image profileImg = null;
        try {
            // Corrected typo "Assests" to "Assets", and used forward slashes with getResource
            profileImg = new Image(getClass().getResource("/Assets/Images/vecteezy_user-icon-on-transparent-background_19879198.png").toExternalForm(), 30, 30, true, true);
        } catch (NullPointerException e) {
            System.err.println("ERROR: Profile icon image not found in add_destination. Verify path: /Assets/Images/vecteezy_user-icon-on-transparent-background_19879198.png");
            profileImg = new Image("data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs="); // Fallback transparent image
        }
        ImageView profileIcon = new ImageView(profileImg);
        profileIcon.setFitWidth(40);
        profileIcon.setFitHeight(40);
        Circle clip = new Circle(20, 20, 20);
        profileIcon.setClip(clip);

        topBar.getChildren().addAll(title, spacerTop, profileIcon);

        // Back button row
        HBox backRow = new HBox();
        backRow.setPadding(new Insets(10, 30, 0, 30));
        backRow.setAlignment(Pos.CENTER_LEFT);

        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #ec7208; -fx-font-size: 16px; -fx-font-weight: bold;");
        // --- FIXED BACK BUTTON ACTION: Navigate directly to AdminDashboardPage ---
        backButton.setOnAction(e -> {
            try {
                AdminDashboardPage adminDashboard = new AdminDashboardPage();
                // Pass back any necessary admin context
                adminDashboard.setLoggedInAdminUid(loggedInAdminUid);
                adminDashboard.setAdminEmail(adminEmail);
                adminDashboard.setAdminRole(adminRole);

                Scene adminDashboardScene = adminDashboard.getAdminDashboardScene(primaryStage);
                primaryStage.setScene(adminDashboardScene);
                primaryStage.setTitle("TirthOnGo - Admin Dashboard");
            } catch (Exception ex) {
                System.err.println("Error navigating back to Admin Dashboard from add_destination: " + ex.getMessage());
                ex.printStackTrace();
                showAlert("Navigation Error", "Failed to go back to dashboard.", Alert.AlertType.ERROR);
            }
        });

        backRow.getChildren().add(backButton);

        Label heading = new Label("Add New Destination");
        heading.setFont(Font.font("Segoe UI", FontWeight.BOLD, 32));
        heading.setTextFill(Color.web("#1f3a93"));
        heading.setEffect(new DropShadow(5, Color.LIGHTGRAY));

        FadeTransition fade = new FadeTransition(Duration.seconds(2), heading);
        fade.setFromValue(0.6);
        fade.setToValue(1.0);
        fade.setCycleCount(FadeTransition.INDEFINITE);
        fade.setAutoReverse(true);
        fade.play();

        VBox pageWrapper = new VBox(20);
        pageWrapper.setAlignment(Pos.TOP_CENTER);
        pageWrapper.setPadding(new Insets(30));
        pageWrapper.setStyle("-fx-background-color: #f3f7fb;");

        VBox formCard = new VBox(20);
        formCard.setPadding(new Insets(30));
        formCard.setAlignment(Pos.CENTER);
        formCard.setMaxWidth(780);
        formCard.setStyle("-fx-background-color: linear-gradient(to top left, #ffffff, #ffe4b5); -fx-background-radius: 15; -fx-border-radius: 15; -fx-border-color: #d8b57f; -fx-border-width: 2;");
        formCard.setEffect(new DropShadow(10, Color.LIGHTGRAY));

        TextField fromField = styledTextField("From (e.g. Pune) *");
        TextField toField = styledTextField("To (e.g. Ayodhya) *");
        TextField stayDays = styledTextField("e.g. Kashi - 2 Nights Stay");
        TextArea infoField = styledTextArea("Enter information about destination *");

        Button uploadMainImage = styledButton("\uD83D\uDCF7 Upload Main Image");
        uploadMainImage.setOnAction(e -> {
            File file = showImageChooser(primaryStage); // Use primaryStage
            if (file != null) {
                try {
                    imageUrl = DestinationDao.uploadImage(file);
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                mainImageView.setImage(new Image(file.toURI().toString()));
                mainImageView.setFitWidth(600);
                mainImageView.setFitHeight(300);
                mainImageView.setPreserveRatio(false); // This is fine if you want it to stretch to these dimensions
                mainImageView.setStyle("-fx-background-radius: 10;");
            }
        });

        mainImageView.setStyle("-fx-background-color: #ececec; -fx-background-radius: 10;");
        mainImageView.setEffect(new DropShadow(10, Color.LIGHTGRAY));

        Label galleryLabel = sectionLabel("Gallery Images:");
        Button addGalleryImage = styledButton("➕ Add Gallery Image");
        addGalleryImage.setOnAction(e -> {
            File file = showImageChooser(primaryStage); // Use primaryStage
            if (file != null) {
                try {
                    imageUrls.add(DestinationDao.uploadImage(file));
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                ImageView img = new ImageView(new Image(file.toURI().toString()));
                img.setFitWidth(100);
                img.setFitHeight(80);
                img.setPreserveRatio(false); // This is fine
                img.setStyle("-fx-background-radius: 6;");
                img.setEffect(new DropShadow(5, Color.GRAY));
                galleryBox.getChildren().add(img);
            }
        });
        galleryBox.setStyle("-fx-padding: 10;");

        Label itineraryLabel = sectionLabel("Itinerary Days:");
        Button addDay = styledButton("➕ Add Day");
        addDay.setOnAction(e -> {
            int dayNumber = dayFields.size() + 1;
            TextField dayField = styledTextField("Day " + dayNumber + " - Description *");
            itineraryBox.getChildren().add(dayField);
            dayFields.add(dayField);
        });

        TextField priceField = styledTextField("Enter Price per adult *");
        TextField payToBookField = styledTextField("Enter Pay to Book amount *");
        TextField guideInfoField = styledTextField("Enter Tour Guide Name ,contact*");

        Button submitButton = new Button("SUBMIT");
        submitButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-radius: 10;");
        HBox.setHgrow(submitButton, Priority.ALWAYS);
        submitButton.setMaxWidth(300);
        submitButton.setPrefHeight(50);
        submitButton.setEffect(new DropShadow(8, Color.DARKGRAY));

        submitButton.setOnAction(e -> {
            if (fromField.getText().isEmpty() || toField.getText().isEmpty() || stayDays.getText().isEmpty() || infoField.getText().isEmpty() ||
                priceField.getText().isEmpty() || payToBookField.getText().isEmpty() || guideInfoField.getText().isEmpty() ||
                dayFields.stream().anyMatch(field -> field.getText().isEmpty())) {

                

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Missing Fields");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all required fields marked with*");
                alert.showAndWait();
                return;
            }

            DestinationModel model = new DestinationModel();
                model.setBookAmount(payToBookField.getText());

                List<String> list = new ArrayList();

                for(int i = 0;i<dayFields.size();i++){
                    list.add(dayFields.get(i).getText());
                }

                model.setDaysDescription(list);
                model.setDescription(infoField.getText());
                model.setEndDestination(toField.getText());
                model.setGalleryImageUrl(imageUrls);
                model.setMainImageUrl(imageUrl);
                model.setGuideName(guideInfoField.getText());
                model.setPricePerAdult(priceField.getText());
                model.setStartDestination(fromField.getText());

                DestinationDao.addDesitnationToFirestore(model);
                System.out.println(); 
            // TODO: Implement data saving for new destination to Firestore
            showAlert("Success", "Destination added successfully!", Alert.AlertType.INFORMATION);
        });

        formCard.getChildren().addAll(
                fromField,
                toField,
                stayDays,
                infoField,
                uploadMainImage,
                mainImageView,
                galleryLabel,
                addGalleryImage,
                galleryBox,
                itineraryLabel,
                addDay,
                itineraryBox,
                priceField,
                payToBookField,
                guideInfoField,
                submitButton
        );

        pageWrapper.getChildren().addAll(backRow, heading, formCard);
        VBox root = new VBox(topBar, pageWrapper);

        ScrollPane scrollPane = new ScrollPane(root);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background: #f3f7fb;");
        scrollPane.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);

        Scene scene = new Scene(scrollPane, primaryStage.getWidth(), primaryStage.getHeight()); // Use primaryStage
        // No need for primaryStage.show() here, as it's handled by the calling page (AdminDashboardPage)
        return scene; 
    }

    private File showImageChooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", ".jpg", ".jpeg", "*.png")); // Corrected filter string
        return fileChooser.showOpenDialog(stage);
    }

    private TextField styledTextField(String prompt) {
        TextField tf = new TextField();
        tf.setPromptText(prompt);
        tf.setStyle("-fx-font-size: 14px; -fx-padding: 10; -fx-background-radius: 10; -fx-border-color: #cccccc; -fx-border-radius: 10;");
        tf.setPrefWidth(600);
        return tf;
    }

    private TextArea styledTextArea(String prompt) {
        TextArea ta = new TextArea();
        ta.setPromptText(prompt);
        ta.setWrapText(true);
        ta.setPrefRowCount(4);
        ta.setPrefWidth(600);
        ta.setStyle("-fx-font-size: 14px; -fx-padding: 10; -fx-background-radius: 10; -fx-border-color: #cccccc; -fx-border-radius: 10;");
        return ta;
    }

    private Button styledButton(String text) {
        Button btn = new Button(text);
        btn.setStyle("-fx-background-color: #ffffff; -fx-border-color: #00bcd4; -fx-text-fill: #00bcd4; -fx-font-weight: bold; -fx-padding: 8 20; -fx-background-radius: 10;");
        btn.setEffect(new DropShadow(5, Color.LIGHTGRAY));
        return btn;
    }

    private Label sectionLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, 16));
        label.setTextFill(Color.web("#333"));
        return label;
    }

    // Helper method to show alerts (added as it was missing)
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
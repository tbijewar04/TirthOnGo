package com.tirthongo.view;

// import java.io.File;
// import java.util.List;
// import javafx.application.Application;
// import javafx.geometry.Insets;    
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.Separator;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableRow;
// import javafx.scene.control.TableView;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.MouseEvent;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.Popup;
// import javafx.stage.Stage;


// public class AdminDashboardPage extends Application {
//   public class SupportRequest {
//     private String name;
//     private String email;
//     private String phone;
//     private String message;

//     public SupportRequest(String name, String email, String phone, String message) {
//         this.name = name;
//         this.email = email;
//         this.phone = phone;
//         this.message = message;
//     }

//     public String getName() { return name; }
//     public String getEmail() { return email; }
//     public String getPhone() { return phone; }
//     public String getMessage() { return message; }
// }

// public class UserRequest {
//     private String name;
//     private String email;
//     private String phone;
   

//     public UserRequest(String name, String email, String phone) {
//         this.name = name;
//         this.email = email;
//         this.phone = phone;
       
//     }

//     public String getName() { return name; }
//     public String getEmail() { return email; }
//     public String getPhone() { return phone; }
    
// }


//     @Override
//     public void start(Stage primaryStage) throws Exception {
        
//         HBox topBar = new HBox();
//         topBar.setPadding(new Insets(15));
//         topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-text-fill: white; -fx-padding: 10 20 10 20; -fx-font-weight: bold;");
//         topBar.setAlignment(Pos.CENTER_LEFT);

//         Label title = new Label("🛐 TirthOnGo");
//         title.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
//         title.setTextFill(Color.web("#ec7208ff")); // Saffron

//         Region spacerTop = new Region();
//         HBox.setHgrow(spacerTop, Priority.ALWAYS);


//         Image profileImg = new Image("Assets\\Images\\ProfileImageIcon.jpg", 30, 30, true, true);
//         ImageView profileIcon = new ImageView(profileImg);
//         profileIcon.setFitWidth(40);
//         profileIcon.setFitHeight(40);
//         Circle clip = new Circle(20, 20, 20);
//         profileIcon.setClip(clip);


//         Popup popup = new Popup();
//         VBox popupContent = createProfilePopup(primaryStage);
//         popup.getContent().add(popupContent);
//         popup.setAutoHide(true);

//         // Show popup when icon is clicked
//         profileIcon.setOnMouseClicked((MouseEvent e) -> {
//             if (!popup.isShowing()) {
//                 popup.show(primaryStage, e.getScreenX(), e.getScreenY());
//             }
//         });

//         topBar.getChildren().addAll(title, spacerTop,profileIcon);

    

//         // Sidebar
//         VBox sidebar = new VBox(30);
//         sidebar.maxHeight(680);
//         sidebar.setPadding(new Insets(20));
//         sidebar.setPrefWidth(350);
//         sidebar.setStyle("-fx-background-color: #e99851ff ");//#e3bba7ff
//         sidebar.setAlignment(Pos.TOP_CENTER);
        
        
//         Label logo = new Label("Welcome Admin");
//         logo.setStyle("-fx-font-size: 18px; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 22px");
//         sidebar.getChildren().addAll(logo);
        
        
//         Button homeBtn = new Button("Home"); //#00f2fe   #4facfe
//         homeBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe,  #4facfe);-fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size:15px");
//         homeBtn.setMaxWidth(200);
//         sidebar.getChildren().addAll(homeBtn);
        
//         Button userBtn = new Button("All Users"); //#00f2fe   #4facfe
//         userBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe,  #4facfe);-fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
//         userBtn.setMaxWidth(200);
//         sidebar.getChildren().addAll(userBtn);
        

//         Button bookingBtn = new Button("All Bookings");
//         bookingBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe, #4facfe);-fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
//         bookingBtn.setMaxWidth(200);
//         sidebar.getChildren().addAll(bookingBtn);

//         Button supportBtn = new Button("Support and Requests");
//         supportBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe, #4facfe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
//         supportBtn.setMaxWidth(200);
//         sidebar.getChildren().addAll(supportBtn);
        

//         Button addPackageButton = new Button("Add Packages");
//         addPackageButton.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe, #4facfe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
//         addPackageButton.setMaxWidth(200);
//         sidebar.getChildren().addAll(addPackageButton);
//         VBox.setVgrow(sidebar, Priority.ALWAYS);

        
//         //space to push image at bottom of sidebar
//         Region spacer = new Region();
//         VBox.setVgrow(spacer, Priority.ALWAYS);
//         sidebar.getChildren().add(spacer);

//         //Image to be added
//         Image bottoImage = new Image("Assets\\Images\\IndianTemples.jpg");
//         ImageView bottoImageView = new ImageView(bottoImage);
//         bottoImageView.setFitWidth(200);
//         bottoImageView.setPreserveRatio(true);

//         DropShadow imageDropShadow = new DropShadow();
//         imageDropShadow.setRadius(10);
//         imageDropShadow.setOffsetX(0);
//         imageDropShadow.setOffsetY(0);
//         imageDropShadow.setColor(Color.rgb(0,0,0,0.5));
//         bottoImageView.setEffect(imageDropShadow);
//         sidebar.getChildren().addAll(bottoImageView);
        
//         // ---- Right region layout ----
//         VBox rightContent = new VBox();
//         rightContent.setPadding(new Insets(20));
//         rightContent.setSpacing(10);
//         rightContent.setStyle("-fx-background-color: white;");
//         HBox.setHgrow(rightContent, Priority.ALWAYS);

        
        
//         //blurr image with welcom text on screen
//         rightContent.getChildren().add(createHomeView());

//     homeBtn.setOnAction(e -> {
//         rightContent.getChildren().clear();
//         rightContent.getChildren().add(createHomeView());
        
//     });

    
//     //Support buttton action
//     supportBtn.setOnAction(e -> {
//     rightContent.getChildren().clear();

//     Label label = new Label("Support & Requests");
//     label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

//     VBox list = new VBox(15);
//     list.setPadding(new Insets(10));

//     // Sample data
//     List<SupportRequest> requests = List.of(
//         new SupportRequest("Ram Kumar", "ram@gmail.com", "9876543210", "Unable to book a package."),
//         new SupportRequest("Sita Verma", "sita@gmail.com", "9123456789", "Booking confirmation not received."),
//         new SupportRequest("Lakshman Yadav", "lakshman@gmail.com", "9988776655", "Please add more destinations.")
//     );

//     for (SupportRequest req : requests) {
//         list.getChildren().add(createSupportCard(req));
//     }

//     ScrollPane scrollPane = new ScrollPane(list);
//     scrollPane.setFitToWidth(true);

//     rightContent.getChildren().addAll(label, scrollPane);
// });


//      //user button action
//         userBtn.setOnAction(e -> {
//             rightContent.getChildren().clear();

//     Label label = new Label("All Users");
//     label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

//     VBox list = new VBox(15);
//     list.setPadding(new Insets(10));

//     // Sample data
//     List<UserRequest> requests = List.of(
//         new UserRequest("Ram", "ram123@email.com", "123456789"),
//         new UserRequest("Karan", "karan@email.com", "123456789"),
//         new UserRequest("Riya", "riya223@email.com", "123456789")
//     );

//     for (UserRequest req : requests) {
//         list.getChildren().add(createUserCard(req));
//     }

//     ScrollPane scrollPane = new ScrollPane(list);
//     scrollPane.setFitToWidth(true);

//     rightContent.getChildren().addAll(label, scrollPane);
// });

//     //booking button action
//     bookingBtn.setOnAction(e -> {
//     rightContent.getChildren().clear();

//     Label heading = new Label("All Booking Information");
//     heading.setFont(Font.font("Arial", FontWeight.BOLD, 30));

//     Label supportLabel = new Label("Booking List");
//     supportLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

//     TableView<String[]> supportTable = new TableView<>();
//     supportTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//     supportTable.setStyle("-fx-font-size: 18px;"); // Change font size of table content

//     // Increase row height using rowFactory
//     supportTable.setRowFactory(tv -> {
//         TableRow<String[]> row = new TableRow<>();
//         row.setPrefHeight(40); // Set desired row height
//         return row;
//     });

//     TableColumn<String[], String> userCol = new TableColumn<>("UserName");
//     userCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[0]));

//     TableColumn<String[], String> packageCol = new TableColumn<>("PackageName");
//     packageCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[1]));

//     TableColumn<String[], String> emailCol = new TableColumn<>("Email-Id");
//     emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[2]));

//     TableColumn<String[], String> statusCol = new TableColumn<>("PaymentStatus");
//     statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[3]));

//     supportTable.getColumns().addAll(userCol, packageCol, emailCol, statusCol);

//     supportTable.getItems().addAll(
//         new String[]{"Ram", "Tirupati Balaji", "ram@gmail.com", "Done"},
//         new String[]{"Sita", "Kedarnath Dham", "sita@gmail.com", "Done"},
//         new String[]{"Bharat", "Ayodhya Dham", "bharat@gmail.com", "Unpaid"}
//     );

//     VBox content = new VBox(10, heading, supportLabel, supportTable);
//     content.setPadding(new Insets(20));
//     rightContent.getChildren().add(content);
// });



//     //addpackages button action
//    addPackageButton.setOnAction(e -> {
//     add_destination destinationPage = new add_destination();

//     try {
//         // Pass the same stage (replace the current scene)
//         destinationPage.start(primaryStage);

//         // Optional: for back button to come back here later
//         primaryStage.setUserData((Runnable) () -> {
//             try {
//                 // Reload adminprofile UI
//                 start(primaryStage); // assuming you're in Application subclass
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         });

//     } catch (Exception ex) {
//         ex.printStackTrace();
//     }
// });




//         // Root layout
//     HBox hBox = new HBox();
//     hBox.getChildren().addAll(sidebar, rightContent);
//     HBox.setHgrow(rightContent, Priority.ALWAYS);

//     VBox main = new VBox(topBar, hBox);
//     VBox.setVgrow(hBox, Priority.ALWAYS);

//     ScrollPane scrollPane = new ScrollPane(main);
//     scrollPane.setFitToWidth(true);
//     scrollPane.setFitToHeight(true);
//     scrollPane.setPannable(true);
//     scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
//     scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

//     Scene sc = new Scene(scrollPane);
//     primaryStage.setScene(sc);
//     primaryStage.setResizable(true);
//     primaryStage.setMaximized(true);
//     primaryStage.show();
//     }



// private VBox createItem(String title, String subtitle, Stage mainStage) {
//     Label titleLabel = new Label(title);
//     titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");

//     Label subtitleLabel = new Label(subtitle);
//     subtitleLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");

//     VBox box = new VBox(2);
//     box.getChildren().addAll(titleLabel, subtitleLabel);
//     box.setStyle("-fx-padding: 5px; -fx-background-radius: 5;");

//     box.setOnMouseEntered(e -> box.setStyle("-fx-padding: 5px; -fx-background-radius: 5; -fx-background-color: #eeeeee;"));
//     box.setOnMouseExited(e -> box.setStyle("-fx-padding: 5px; -fx-background-radius: 5;"));

//     box.setOnMouseClicked(e -> {
//         System.out.println("Clicked: " + title);

//         if (title.equals("👤 My Profile")) {
//             try {
//                 profile1 profilePage = new profile1();
//                 profilePage.start(mainStage); // Use passed-in Stage
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//             }
//         }
//     });

//     return box;
// }





//     private <Node> VBox createProfilePopup(Stage mainStage) {
//     VBox box = new VBox(20);
//     box.setPadding(new Insets(15));
//     box.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; "
//                + "-fx-border-color: #ccc; -fx-border-radius: 8;");
//     box.setPrefWidth(260);
//     box.setPrefHeight(300);

//     Label greeting = new Label("Hello, Admin");
//     greeting.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

//     box.getChildren().addAll(
//         greeting,
//         new Separator(),
//         createItem("👤 My Profile", "Manage your profile. ", mainStage),
//         new Separator(),
//         createItem("🚌 My Trips", "View all bookings.", mainStage),
//         new Separator(),
//         createItem("🔓 Sign Out", "Logout from your account", mainStage)
//     );
//     return box;
//     }

   
//     //Home button and default screen display
//     private StackPane createHomeView() {
//     // Load image
//     Image bgImage = new Image("Assets\\Images\\ProfileImageIcon.jpg"); // update with your image path
//     ImageView imageView = new ImageView(bgImage);
//     imageView.setFitWidth(800); // adjust as needed
//     imageView.setFitHeight(680);
//     imageView.setPreserveRatio(true);
//     imageView.setOpacity(0.5); // slight transparency

//     // Add blur effect
//     javafx.scene.effect.GaussianBlur blur = new javafx.scene.effect.GaussianBlur(20);
//     imageView.setEffect(blur);

//     // Welcome text
//     Label welcomeText = new Label("Welcome Admin - TirthOnGo");
//     welcomeText.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
//     welcomeText.setTextFill(Color.web("#333"));
//     welcomeText.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-padding: 20; -fx-background-radius: 10;");

//     StackPane.setAlignment(welcomeText, Pos.CENTER);

//     return new StackPane(imageView, welcomeText);
// }



// //Support button data
// private VBox createSupportCard(SupportRequest request) {
//     VBox card = new VBox(5);
//     card.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: #f9f9f9;");
//     card.setPrefWidth(300);

//     String imagePath = getAdminProfileImagePath();
//     Image profileImage;
    
//     try {
//         profileImage = new Image(imagePath, true); // true to load async
//     } catch (Exception e) {
//         profileImage = new Image("Assets\\Images\\ProfileImageIcon.jpg"); // fallback
//     }

//     ImageView profileIcon = new ImageView(new Image("Assets\\Images\\ProfileImageIcon.jpg"));
//     profileIcon.setFitHeight(60);
//     profileIcon.setFitWidth(60);

//      // Labels + Normal values (only labels are bold)
//     Label nameLabel = new Label();
//     nameLabel.setText("Name: ");
//     nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//     nameLabel.setFont(Font.font("Arial"));

//     Label nameValue = new Label(request.getName());
//     nameValue.setStyle("-fx-font-size: 15");
//     nameValue.setFont(Font.font("Arial"));

//     Label emailLabel = new Label("Email: ");
//     emailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//     emailLabel.setFont(Font.font("Arial"));
//     Label emailValue = new Label(request.getEmail());
//     emailValue.setStyle("-fx-font-size: 15");
//     emailValue.setFont(Font.font("Arial"));

//     Label phoneLabel = new Label("Phone: ");
//     phoneLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//     phoneLabel.setFont(Font.font("Italic"));
//     Label phoneValue = new Label(request.getPhone());
//     phoneValue.setStyle("-fx-font-size: 15");
//     phoneValue.setFont(Font.font("Italic"));

//     Label messageLabel = new Label("Message: ");
//     messageLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//     messageLabel.setFont(Font.font("Georgia"));
//     Label messageValue = new Label(request.getMessage());
//     messageValue.setStyle("-fx-font-size: 15");
//     messageValue.setFont(Font.font("Georgia"));
//     messageValue.setWrapText(true);

//     // Grouping each label+value horizontally
//     HBox nameBox = new HBox(5, nameLabel, nameValue);
//     HBox emailBox = new HBox(5, emailLabel, emailValue);
//     HBox phoneBox = new HBox(5, phoneLabel, phoneValue);
//     HBox messageBox = new HBox(5, messageLabel, messageValue);

//     VBox textBox = new VBox(3);
//     textBox.getChildren().addAll(nameBox, emailBox, phoneBox, messageBox);

//     HBox contentBox = new HBox(10);
//     contentBox.getChildren().addAll(profileIcon, textBox);
//     contentBox.setAlignment(Pos.TOP_LEFT);



//     card.getChildren().addAll(contentBox);
//     return card;
// }



// private String getAdminProfileImagePath() {
    
//     // You can replace this with a database call, file config, or user session value
//     File profileFile = new File("Assets\\Images\\ProfileImageIcon.jpg");

//     if (profileFile.exists()) {
//         return profileFile.toURI().toString(); // Convert to URI for Image constructor
//     } else {
//         return new File("Assets\\Images\\ProfileImageIcon.jpg").toURI().toString();
//     }
// }




// private VBox createUserCard(UserRequest req) {
//     VBox card2 = new VBox(5);
//     card2.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: #f9f9f9;");
//     card2.setPrefWidth(300);

//     ImageView profile = new ImageView(new Image("Assets\\Images\\ProfileImageIcon.jpg"));
//     profile.setFitHeight(60);
//     profile.setFitWidth(60);

//      // Labels + Normal values (only labels are bold)
//     Label nameLabel = new Label();
//     nameLabel.setText("Name: ");
//     nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//     nameLabel.setFont(Font.font("Arial"));

//     Label nameValue = new Label(req.getName());
//     nameValue.setStyle("-fx-font-size: 15");
//     nameValue.setFont(Font.font("Arial"));

//     Label emailLabel = new Label("Email: ");
//     emailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//     emailLabel.setFont(Font.font("Arial"));
//     Label emailValue = new Label(req.getEmail());
//     emailValue.setStyle("-fx-font-size: 15");
//     emailValue.setFont(Font.font("Arial"));

//     Label phoneLabel = new Label("Phone: ");
//     phoneLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//     phoneLabel.setFont(Font.font("Italic"));
//     Label phoneValue = new Label(req.getPhone());
//     phoneValue.setStyle("-fx-font-size: 15");
//     phoneValue.setFont(Font.font("Italic"));

   

//     // Grouping each label+value horizontally
//     HBox nameBox = new HBox(5, nameLabel, nameValue);
//     HBox emailBox = new HBox(5, emailLabel, emailValue);
//     HBox phoneBox = new HBox(5, phoneLabel, phoneValue);
    

//     VBox textBox = new VBox(3);
//     textBox.getChildren().addAll(nameBox, emailBox, phoneBox);

//     HBox contentBox = new HBox(10);
//     contentBox.getChildren().addAll(profile, textBox);
//     contentBox.setAlignment(Pos.TOP_LEFT);



//     card2.getChildren().addAll(contentBox);
//     return card2;
// }



// }



// import java.io.File;
// import java.util.List;
// // Removed javafx.application.Application import as it no longer extends Application
// import javafx.geometry.Insets;    
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.Label;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.Separator;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableRow;
// import javafx.scene.control.TableView;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.input.MouseEvent;
// import javafx.scene.layout.HBox;
// import javafx.scene.layout.Priority;
// import javafx.scene.layout.Region;
// import javafx.scene.layout.StackPane;
// import javafx.scene.layout.VBox;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.Popup;
// import javafx.stage.Stage;

// // No longer extends Application
// public class AdminDashboardPage {

//     // --- NEW: Fields to receive logged-in admin's context ---
//     private String loggedInAdminUid;
//     private String adminEmail;
//     private String adminRole;

//     // --- NEW: Setters to receive context from AdminProfile ---
//     public void setLoggedInAdminUid(String uid) {
//         this.loggedInAdminUid = uid;
//         System.out.println("AdminDashboardPage: Received UID: " + loggedInAdminUid); // For debugging
//     }

//     public void setAdminEmail(String email) {
//         this.adminEmail = email;
//         System.out.println("AdminDashboardPage: Received Email: " + adminEmail); // For debugging
//     }

//     public void setAdminRole(String role) {
//         this.adminRole = role;
//         System.out.println("AdminDashboardPage: Received Role: " + adminRole); // For debugging
//     }

//     // Nested classes (SupportRequest, UserRequest) are fine here
//     public class SupportRequest {
//         private String name;
//         private String email;
//         private String phone;
//         private String message;

//         public SupportRequest(String name, String email, String phone, String message) {
//             this.name = name;
//             this.email = email;
//             this.phone = phone;
//             this.message = message;
//         }

//         public String getName() { return name; }
//         public String getEmail() { return email; }
//         public String getPhone() { return phone; }
//         public String getMessage() { return message; }
//     }

//     public class UserRequest {
//         private String name;
//         private String email;
//         private String phone;
       
//         public UserRequest(String name, String email, String phone) {
//             this.name = name;
//             this.email = email;
//             this.phone = phone;
//         }

//         public String getName() { return name; }
//         public String getEmail() { return email; }
//         public String getPhone() { return phone; }
//     }

//     // --- REPLACED: public void start(Stage primaryStage) throws Exception { ---
//     // This is the new entry point for other classes to get this dashboard's Scene
//     public Scene getAdminDashboardScene(Stage primaryStage) { // Removed 'throws Exception' for simplicity, handle internally

//         HBox topBar = new HBox();
//         topBar.setPadding(new Insets(15));
//         topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-text-fill: white; -fx-padding: 10 20 10 20; -fx-font-weight: bold;");
//         topBar.setAlignment(Pos.CENTER_LEFT);

//         Label title = new Label("🛐 TirthOnGo");
//         title.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
//         title.setTextFill(Color.web("#ec7208ff")); // Saffron

//         Region spacerTop = new Region();
//         HBox.setHgrow(spacerTop, Priority.ALWAYS);


//         Image profileImg = new Image("Assets\\Images\\ProfileImageIcon.jpg", 30, 30, true, true);
//         ImageView profileIcon = new ImageView(profileImg);
//         profileIcon.setFitWidth(40);
//         profileIcon.setFitHeight(40);
//         Circle clip = new Circle(20, 20, 20);
//         profileIcon.setClip(clip);


//         Popup popup = new Popup();
//         // Pass primaryStage to the popup content creator to allow it to change scenes
//         VBox popupContent = createProfilePopup(primaryStage); // Pass primaryStage
//         popup.getContent().add(popupContent);
//         popup.setAutoHide(true);

//         // Show popup when icon is clicked
//         profileIcon.setOnMouseClicked((MouseEvent e) -> {
//             if (!popup.isShowing()) {
//                 popup.show(primaryStage, e.getScreenX(), e.getScreenY());
//             }
//         });

//         topBar.getChildren().addAll(title, spacerTop,profileIcon);

    

//         // Sidebar
//         VBox sidebar = new VBox(30);
//         sidebar.maxHeight(680); // Consider setting prefHeight instead for consistency
//         sidebar.setPadding(new Insets(20));
//         sidebar.setPrefWidth(350);
//         sidebar.setStyle("-fx-background-color: #e99851ff ");//#e3bba7ff
//         sidebar.setAlignment(Pos.TOP_CENTER);

//         // Use adminEmail from received context for welcome message
//         Label logo = new Label("Welcome Admin" + (adminEmail != null ? "\n" +  adminEmail.split("@")[0]: ""));
//         logo.setStyle("-fx-font-size: 18px; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 22px");
//         logo.setAlignment(Pos.CENTER); // Center the text within the label
//         sidebar.getChildren().addAll(logo);
        
//         // Sidebar Buttons
//         Button homeBtn = new Button("Home");
//         homeBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe,  #4facfe);-fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size:15px");
//         homeBtn.setMaxWidth(200);
//         sidebar.getChildren().addAll(homeBtn);
        
//         Button userBtn = new Button("All Users");
//         userBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe,  #4facfe);-fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
//         userBtn.setMaxWidth(200);
//         sidebar.getChildren().addAll(userBtn);
        

//         Button bookingBtn = new Button("All Bookings");
//         bookingBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe, #4facfe);-fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
//         bookingBtn.setMaxWidth(200);
//         sidebar.getChildren().addAll(bookingBtn);

//         Button supportBtn = new Button("Support and Requests");
//         supportBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe, #4facfe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
//         supportBtn.setMaxWidth(200);
//         sidebar.getChildren().addAll(supportBtn);
        

//         Button addPackageButton = new Button("Add Packages");
//         addPackageButton.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe, #4facfe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
//         addPackageButton.setMaxWidth(200);
//         sidebar.getChildren().addAll(addPackageButton);
//         VBox.setVgrow(sidebar, Priority.ALWAYS);

        
//         //space to push image at bottom of sidebar
//         Region spacer = new Region();
//         VBox.setVgrow(spacer, Priority.ALWAYS);
//         sidebar.getChildren().add(spacer);

//         //Image to be added
//         Image bottoImage = new Image("Assets\\Images\\temple.png");
//         ImageView bottoImageView = new ImageView(bottoImage);
//         bottoImageView.setFitWidth(200);
//         bottoImageView.setPreserveRatio(true);

//         DropShadow imageDropShadow = new DropShadow();
//         imageDropShadow.setRadius(10);
//         imageDropShadow.setOffsetX(0);
//         imageDropShadow.setOffsetY(0);
//         imageDropShadow.setColor(Color.rgb(0,0,0,0.5));
//         bottoImageView.setEffect(imageDropShadow);
//         sidebar.getChildren().addAll(bottoImageView);
        
//         // ---- Right region layout ----
//         VBox rightContent = new VBox();
//         //rightContent.setPadding(new Insets(20));
//         //rightContent.setSpacing(10);
//         rightContent.setStyle("-fx-background-color: white;");
//         HBox.setHgrow(rightContent, Priority.ALWAYS);

//         // Default view on dashboard load
//         rightContent.getChildren().add(createHomeView());

//         homeBtn.setOnAction(e -> {
//             rightContent.getChildren().clear();
//             rightContent.getChildren().add(createHomeView());
//         });

//         // Support buttton action
//         supportBtn.setOnAction(e -> {
//             rightContent.getChildren().clear();
//             Label label = new Label("Support & Requests");
//             label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

//             VBox list = new VBox(15);
//             list.setPadding(new Insets(10));

//             // Sample data - You'll replace this with data fetched from Firestore
//             List<SupportRequest> requests = List.of(
//                 new SupportRequest("Ram Kumar", "ram@gmail.com", "9876543210", "Unable to book a package."),
//                 new SupportRequest("Sita Verma", "sita@gmail.com", "9123456789", "Booking confirmation not received."),
//                 new SupportRequest("Lakshman Yadav", "lakshman@gmail.com", "9988776655", "Please add more destinations.")
//             );

//             for (SupportRequest req : requests) {
//                 list.getChildren().add(createSupportCard(req));
//             }

//             ScrollPane scrollPane = new ScrollPane(list);
//             scrollPane.setFitToWidth(true);

//             rightContent.getChildren().addAll(label, scrollPane);
//         });

//         // User button action
//         userBtn.setOnAction(e -> {
//             rightContent.getChildren().clear();
//             Label label = new Label("All Users");
//             label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

//             VBox list = new VBox(15);
//             list.setPadding(new Insets(10));

//             // Sample data - You'll replace this with data fetched from Firestore
//             List<UserRequest> requests = List.of(
//                 new UserRequest("Ram", "ram123@email.com", "123456789"),
//                 new UserRequest("Karan", "karan@email.com", "123456789"),
//                 new UserRequest("Riya", "riya223@email.com", "123456789")
//             );

//             for (UserRequest req : requests) {
//                 list.getChildren().add(createUserCard(req));
//             }

//             ScrollPane scrollPane = new ScrollPane(list);
//             scrollPane.setFitToWidth(true);

//             rightContent.getChildren().addAll(label, scrollPane);
//         });

//         // Booking button action
//         bookingBtn.setOnAction(e -> {
//             rightContent.getChildren().clear();
//             Label heading = new Label("All Booking Information");
//             heading.setFont(Font.font("Arial", FontWeight.BOLD, 30));

//             Label supportLabel = new Label("Booking List");
//             supportLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

//             TableView<String[]> supportTable = new TableView<>();
//             supportTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
//             supportTable.setStyle("-fx-font-size: 18px;");

//             supportTable.setRowFactory(tv -> {
//                 TableRow<String[]> row = new TableRow<>();
//                 row.setPrefHeight(40);
//                 return row;
//             });

//             TableColumn<String[], String> userCol = new TableColumn<>("UserName");
//             userCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[0]));

//             TableColumn<String[], String> packageCol = new TableColumn<>("PackageName");
//             packageCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[1]));

//             TableColumn<String[], String> emailCol = new TableColumn<>("Email-Id");
//             emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[2]));

//             TableColumn<String[], String> statusCol = new TableColumn<>("PaymentStatus");
//             statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[3]));

//             supportTable.getColumns().addAll(userCol, packageCol, emailCol, statusCol);

//             supportTable.getItems().addAll(
//                 new String[]{"Ram", "Tirupati Balaji", "ram@gmail.com", "Done"},
//                 new String[]{"Sita", "Kedarnath Dham", "sita@gmail.com", "Done"},
//                 new String[]{"Bharat", "Ayodhya Dham", "bharat@gmail.com", "Unpaid"}
//             );

//             VBox content = new VBox(10, heading, supportLabel, supportTable);
//             content.setPadding(new Insets(20));
//             rightContent.getChildren().add(content);
//         });

//         // Add packages button action
//         addPackageButton.setOnAction(e -> {
            
//             try {
//                 add_destination destinationPage = new add_destination();
//                 Scene dScene = destinationPage.getDScene(primaryStage);
//                 primaryStage.setScene(dScene);
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//                 showAlert("Navigation Error", "Could not load Add Destination page.", Alert.AlertType.ERROR);
//             }
//         });

//         // Root layout
//         HBox hBox = new HBox();
//         hBox.getChildren().addAll(sidebar, rightContent);
//         HBox.setHgrow(rightContent, Priority.ALWAYS);

//         VBox main = new VBox(topBar, hBox);
//         VBox.setVgrow(hBox, Priority.ALWAYS);

//         ScrollPane scrollPane = new ScrollPane(main);
//         scrollPane.setFitToWidth(true);
//         scrollPane.setFitToHeight(true);
//         scrollPane.setPannable(true);
//         scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
//         scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

//         Scene sc = new Scene(scrollPane,1300, 700);
        
//         primaryStage.setScene(sc);
//         primaryStage.setMaximized(true);
//         primaryStage.show();


//         return sc; // Return the created Scene
//     } // End of getAdminDashboardScene

//     // Helper methods (as provided by you)
//     private VBox createItem(String title, String subtitle, Stage mainStage) {
//     Label titleLabel = new Label(title);
//     titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");

//     Label subtitleLabel = new Label(subtitle);
//     subtitleLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");

//     VBox box = new VBox(2);
//     box.getChildren().addAll(titleLabel, subtitleLabel);
//     box.setStyle("-fx-padding: 5px; -fx-background-radius: 5;");

//     box.setOnMouseEntered(e -> box.setStyle("-fx-padding: 5px; -fx-background-radius: 5; -fx-background-color: #eeeeee;"));
//     box.setOnMouseExited(e -> box.setStyle("-fx-padding: 5px; -fx-background-radius: 5;"));

//     box.setOnMouseClicked(e -> {
//         System.out.println("Clicked: " + title);

//        if (title.equals("🔓 Sign Out")) { // Handle "Sign Out"
//             // --- START: Sign Out Logic ---
//             // 1. Clear local session data (AdminDashboardPage's fields)
//             this.loggedInAdminUid = null;
//             this.adminEmail = null;
//             this.adminRole = null;
//             System.out.println("Admin session data cleared.");

//             // 2. Inform the user
//             showAlert("Signed Out", "You have been successfully signed out.", Alert.AlertType.INFORMATION);

//             // 3. Navigate back to the LoginPage
//             try {
//                 // Ensure you have: import com.tirthongo.view.LoginPage;
//                 LoginPage loginPage = new LoginPage();
//                 loginPage.start(mainStage); // Assuming LoginPage still extends Application and has a start(Stage) method
//                 mainStage.setTitle("TirthOnGo - Login"); // Update stage title
//             } catch (Exception ex) {
//                 System.err.println("Error navigating to Login Page after sign out: " + ex.getMessage());
//                 ex.printStackTrace();
//                 showAlert("Navigation Error", "Failed to load login page after sign out.", Alert.AlertType.ERROR);
//             }
//             // --- END: Sign Out Logic ---
//         }
//     });

//     return box;
// }

//      private <Node> VBox createProfilePopup(Stage mainStage) {
//     VBox box = new VBox(20);
//     box.setPadding(new Insets(15));
//     box.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; "
//                + "-fx-border-color: #ccc; -fx-border-radius: 8;");
//     box.setPrefWidth(260);
//     box.setPrefHeight(300);

//     Label greeting = new Label("Hello" + " " + adminEmail.split("@")[0]);
//     greeting.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

//     box.getChildren().addAll(
//         greeting,
//         new Separator(),
//         createItem("👤 My Profile", "Manage your profile. ", mainStage),
//         new Separator(),
//         createItem("🔓 Sign Out", "Logout from your account", mainStage)
//     );
//     return box;
//     }


//     // Home button and default screen display
//     private StackPane createHomeView() {
//         // Load image
//         Image bgImage = new Image("Assets\\Images\\welcome.jpg"); // update with your image path
//         ImageView imageView = new ImageView(bgImage);
//         imageView.setFitWidth(1300);
//         imageView.setFitHeight(700);
//         imageView.setPreserveRatio(true);
       

//         // Add blur effect
//         javafx.scene.effect.GaussianBlur blur = new javafx.scene.effect.GaussianBlur(5);
//         imageView.setEffect(blur);

//         // Welcome text - now uses the class-level adminEmail
//         Label welcomeText = new Label("Welcome Admin - TirthOnGo");
//         if (adminEmail != null) {
//             welcomeText.setText("Welcome " + adminEmail.split("@")[0] + " - TirthOnGo");
//         }
//         welcomeText.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
//         welcomeText.setTextFill(Color.web("#333"));
//         welcomeText.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-padding: 20; -fx-background-radius: 10;");

//         StackPane.setAlignment(welcomeText, Pos.CENTER);

//         return new StackPane(imageView, welcomeText);
//     }

//     // Support button data
//     private VBox createSupportCard(SupportRequest request) {
//         // ... (This method is fine as is, assuming data is passed correctly) ...
//         VBox card = new VBox(5);
//         card.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: #f9f9f9;");
//         card.setPrefWidth(300);

//         // Image loading logic - make sure getAdminProfileImagePath is robust
//         Image profileImage;
//         try {
//             profileImage = new Image(getAdminProfileImagePath(), true); // true to load async
//         } catch (Exception e) {
//             profileImage = new Image("Assets\\Images\\ProfileImageIcon.jpg"); // fallback
//         }
//         ImageView profileIcon = new ImageView(profileImage); // Use the loaded image
//         profileIcon.setFitHeight(60);
//         profileIcon.setFitWidth(60);

//         // ... (rest of createSupportCard) ...
//         Label nameLabel = new Label();
//         nameLabel.setText("Name: ");
//         nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//         nameLabel.setFont(Font.font("Arial"));

//         Label nameValue = new Label(request.getName());
//         nameValue.setStyle("-fx-font-size: 15");
//         nameValue.setFont(Font.font("Arial"));

//         Label emailLabel = new Label("Email: ");
//         emailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//         emailLabel.setFont(Font.font("Arial"));
//         Label emailValue = new Label(request.getEmail());
//         emailValue.setStyle("-fx-font-size: 15");
//         emailValue.setFont(Font.font("Arial"));

//         Label phoneLabel = new Label("Phone: ");
//         phoneLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//         phoneLabel.setFont(Font.font("Italic"));
//         Label phoneValue = new Label(request.getPhone());
//         phoneValue.setStyle("-fx-font-size: 15");
//         phoneValue.setFont(Font.font("Italic"));

//         Label messageLabel = new Label("Message: ");
//         messageLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//         messageLabel.setFont(Font.font("Georgia"));
//         Label messageValue = new Label(request.getMessage());
//         messageValue.setStyle("-fx-font-size: 15");
//         messageValue.setFont(Font.font("Georgia"));
//         messageValue.setWrapText(true);

//         // Grouping each label+value horizontally
//         HBox nameBox = new HBox(5, nameLabel, nameValue);
//         HBox emailBox = new HBox(5, emailLabel, emailValue);
//         HBox phoneBox = new HBox(5, phoneLabel, phoneValue);
//         HBox messageBox = new HBox(5, messageLabel, messageValue);

//         VBox textBox = new VBox(3);
//         textBox.getChildren().addAll(nameBox, emailBox, phoneBox, messageBox);

//         HBox contentBox = new HBox(10);
//         contentBox.getChildren().addAll(profileIcon, textBox);
//         contentBox.setAlignment(Pos.TOP_LEFT);

//         card.getChildren().addAll(contentBox);
//         return card;
//     }

//     private String getAdminProfileImagePath() {
//         // This method should ultimately fetch the admin's actual profile image URL from Firestore
//         // (using loggedInAdminUid and ProfileDao.fetchProfileData)
//         // For now, it's a fallback.
//         // If profileImageUrl is stored in Firestore, fetch it here:
//         // Map<String, Object> adminProfile = ProfileDao.fetchProfileData("admins", loggedInAdminUid);
//         // if (adminProfile != null && adminProfile.containsKey("profileImageUrl")) {
//         //     return adminProfile.get("profileImageUrl").toString();
//         // }
        
//         File profileFile = new File("Assets\\Images\\ProfileImageIcon.jpg");
//         if (profileFile.exists()) {
//             return profileFile.toURI().toString();
//         } else {
//             return new File("Assets\\Images\\ProfileImageIcon.jpg").toURI().toString(); // Fallback if local file not found
//         }
//     }

//     private VBox createUserCard(UserRequest req) {
//         // ... (This method is fine as is, assuming data is passed correctly) ...
//         VBox card2 = new VBox(5);
//         card2.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: #f9f9f9;");
//         card2.setPrefWidth(300);

//         ImageView profile = new ImageView(new Image("Assets\\Images\\ProfileImageIcon.jpg"));
//         profile.setFitHeight(60);
//         profile.setFitWidth(60);

//         Label nameLabel = new Label();
//         nameLabel.setText("Name: ");
//         nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//         nameLabel.setFont(Font.font("Arial"));

//         Label nameValue = new Label(req.getName());
//         nameValue.setStyle("-fx-font-size: 15");
//         nameValue.setFont(Font.font("Arial"));

//         Label emailLabel = new Label("Email: ");
//         emailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//         emailLabel.setFont(Font.font("Arial"));
//         Label emailValue = new Label(req.getEmail());
//         emailValue.setStyle("-fx-font-size: 15");
//         emailValue.setFont(Font.font("Arial"));

//         Label phoneLabel = new Label("Phone: ");
//         phoneLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
//         phoneLabel.setFont(Font.font("Italic"));
//         Label phoneValue = new Label(req.getPhone());
//         phoneValue.setStyle("-fx-font-size: 15");
//         phoneValue.setFont(Font.font("Italic"));
       
//         HBox nameBox = new HBox(5, nameLabel, nameValue);
//         HBox emailBox = new HBox(5, emailLabel, emailValue);
//         HBox phoneBox = new HBox(5, phoneLabel, phoneValue);
        
//         VBox textBox = new VBox(3);
//         textBox.getChildren().addAll(nameBox, emailBox, phoneBox);

//         HBox contentBox = new HBox(10);
//         contentBox.getChildren().addAll(profile, textBox);
//         contentBox.setAlignment(Pos.TOP_LEFT);

//         card2.getChildren().addAll(contentBox);
//         return card2;
//     }

//     // New helper method for alerts (copied from UserProfile)
//     private void showAlert(String title, String message, Alert.AlertType alertType) {
//         Alert alert = new Alert(alertType);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }

//     // Removed the main method and 'extends Application'
// }

import java.util.List;
import javafx.geometry.Insets;    
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
import com.tirthongo.model.AdminProfileModel;

public class AdminDashboardPage {

    private static String loggedInAdminUid;
    private static String adminEmail;
    private static String adminRole;
    private static AdminProfileModel profile;

    public static void setAdminProfile(AdminProfileModel profileModel){
        profile =profileModel;
    }
    public static AdminProfileModel getAdminProfileModel(){
        return profile;
    }

    public void setLoggedInAdminUid(String uid) {
        this.loggedInAdminUid = uid;
        System.out.println("AdminDashboardPage: Received UID: " + loggedInAdminUid);
    }

    public void setAdminEmail(String email) {
        this.adminEmail = email;
        System.out.println("AdminDashboardPage: Received Email: " + adminEmail);
    }

    public void setAdminRole(String role) {
        this.adminRole = role;
        System.out.println("AdminDashboardPage: Received Role: " + adminRole);
    }

    public class SupportRequest {
        private String name;
        private String email;
        private String phone;
        private String message;

        public SupportRequest(String name, String email, String phone, String message) {
            this.name = name;
            this.email = email;
            this.phone = phone;
            this.message = message;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
        public String getMessage() { return message; }
    }

    public class UserRequest {
        private String name;
        private String email;
        private String phone;
       
        public UserRequest(String name, String email, String phone) {
            this.name = name;
            this.email = email;
            this.phone = phone;
        }

        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhone() { return phone; }
    }

    public Scene getAdminDashboardScene(Stage primaryStage) { 

        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-text-fill: white; -fx-padding: 10 20 10 20; -fx-font-weight: bold;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("ॐ TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 25));
        title.setTextFill(Color.web("#ec7208ff")); 

        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, Priority.ALWAYS);

        // --- FIXED IMAGE PATH FOR PROFILE ICON ---
        Image profileImg = null;
        try {
            profileImg = new Image(getClass().getResource("/Assets/Images/ProfileImageIcon.jpg").toExternalForm(), 30, 30, true, true);
        } catch (NullPointerException e) {
            System.err.println("ERROR: ProfileImageIcon.jpg not found in AdminDashboardPage top bar.");
            profileImg = new Image("data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs="); // Fallback
        }
        ImageView profileIcon = new ImageView(profileImg);
        profileIcon.setFitWidth(40);
        profileIcon.setFitHeight(40);
        Circle clip = new Circle(20, 20, 20);
        profileIcon.setClip(clip);

        Popup popup = new Popup();
        VBox popupContent = createProfilePopup(primaryStage); 
        popup.getContent().add(popupContent);
        popup.setAutoHide(true);

        profileIcon.setOnMouseClicked((MouseEvent e) -> {
            if (!popup.isShowing()) {
                popup.show(primaryStage, e.getScreenX(), e.getScreenY());
            }
        });

        topBar.getChildren().addAll(title, spacerTop, profileIcon);

        // Sidebar
        VBox sidebar = new VBox(30);
        sidebar.maxHeight(680); 
        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(350);
        sidebar.setStyle("-fx-background-color: #e99851ff ");
        sidebar.setAlignment(Pos.TOP_CENTER);

        // Use adminEmail from received context for welcome message (FIXED NULL CHECK)
        Label logo = new Label("Welcome Admin");
        if (adminEmail != null) { // Check for null before split
            logo.setText("Welcome " + adminEmail.split("@")[0]);
        }
        logo.setStyle("-fx-font-size: 18px; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 22px");
        logo.setAlignment(Pos.CENTER);
        sidebar.getChildren().addAll(logo);
        
        Button homeBtn = new Button("Home");
        homeBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe,  #4facfe);-fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size:15px");
        homeBtn.setMaxWidth(200);
        sidebar.getChildren().addAll(homeBtn);

        homeBtn.setOnAction(e -> {
        primaryStage.setScene(new Homeload().gethomescene(primaryStage));
        });
        
        Button userBtn = new Button("All Users");
        userBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe,  #4facfe);-fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
        userBtn.setMaxWidth(200);
        sidebar.getChildren().addAll(userBtn);
        
        Button bookingBtn = new Button("All Bookings");
        bookingBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe, #4facfe);-fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
        bookingBtn.setMaxWidth(200);
        sidebar.getChildren().addAll(bookingBtn);

        Button supportBtn = new Button("Support and Requests");
        supportBtn.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe, #4facfe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
        supportBtn.setMaxWidth(200);
        sidebar.getChildren().addAll(supportBtn);
        
        Button addPackageButton = new Button("Add Packages");
        addPackageButton.setStyle("-fx-background-color: linear-gradient(to right, #00f2fe, #4facfe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: bold; -fx-font-size: 15px");
        addPackageButton.setMaxWidth(200);
        sidebar.getChildren().addAll(addPackageButton);
        VBox.setVgrow(sidebar, Priority.ALWAYS);

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        sidebar.getChildren().add(spacer);

        // --- FIXED IMAGE PATH FOR SIDEBAR BOTTOM IMAGE ---
        Image bottoImage = null;
        try {
            bottoImage = new Image(getClass().getResource("/Assets/Images/temple.png").toExternalForm());
        } catch (NullPointerException e) {
            System.err.println("ERROR: sidebar bottom image 'temple.png' not found in AdminDashboardPage.");
            bottoImage = new Image("data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs="); // Fallback
        }
        ImageView bottoImageView = new ImageView(bottoImage);
        bottoImageView.setFitWidth(200);
        bottoImageView.setPreserveRatio(true);

        DropShadow imageDropShadow = new DropShadow();
        imageDropShadow.setRadius(10);
        imageDropShadow.setOffsetX(0);
        imageDropShadow.setOffsetY(0);
        imageDropShadow.setColor(Color.rgb(0,0,0,0.5));
        bottoImageView.setEffect(imageDropShadow);
        sidebar.getChildren().addAll(bottoImageView);
        
        // ---- Right region layout ----
        VBox rightContent = new VBox();
        rightContent.setPadding(new Insets(20));
        rightContent.setSpacing(10);
        rightContent.setStyle("-fx-background-color: white;");
        HBox.setHgrow(rightContent, Priority.ALWAYS);

        // Default view on dashboard load
        rightContent.getChildren().add(createHomeView());

        homeBtn.setOnAction(e -> {
            rightContent.getChildren().clear();
            rightContent.getChildren().add(createHomeView());
        });

        supportBtn.setOnAction(e -> {
            rightContent.getChildren().clear();
            Label label = new Label("Support & Requests");
            label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

            VBox list = new VBox(15);
            list.setPadding(new Insets(10));

            List<SupportRequest> requests = List.of(
                new SupportRequest("Ram Kumar", "ram@gmail.com", "9876543210", "Unable to book a package."),
                new SupportRequest("Sita Verma", "sita@gmail.com", "9123456789", "Booking confirmation not received."),
                new SupportRequest("Lakshman Yadav", "lakshman@gmail.com", "9988776655", "Please add more destinations.")
            );

            for (SupportRequest req : requests) {
                list.getChildren().add(createSupportCard(req));
            }

            ScrollPane scrollPane = new ScrollPane(list);
            scrollPane.setFitToWidth(true);

            rightContent.getChildren().addAll(label, scrollPane);
        });

        userBtn.setOnAction(e -> {
            rightContent.getChildren().clear();
            Label label = new Label("All Users");
            label.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

            VBox list = new VBox(15);
            list.setPadding(new Insets(10));

            List<UserRequest> requests = List.of(
                new UserRequest("Ram", "ram123@email.com", "123456789"),
                new UserRequest("Karan", "karan@email.com", "123456789"),
                new UserRequest("Riya", "riya223@email.com", "123456789")
            );

            for (UserRequest req : requests) {
                list.getChildren().add(createUserCard(req));
            }

            ScrollPane scrollPane = new ScrollPane(list);
            scrollPane.setFitToWidth(true);

            rightContent.getChildren().addAll(label, scrollPane);
        });

        bookingBtn.setOnAction(e -> {
            rightContent.getChildren().clear();
            Label heading = new Label("All Booking Information");
            heading.setFont(Font.font("Arial", FontWeight.BOLD, 30));

            Label supportLabel = new Label("Booking List");
            supportLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));

            TableView<String[]> supportTable = new TableView<>();
            supportTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            supportTable.setStyle("-fx-font-size: 18px;");

            supportTable.setRowFactory(tv -> {
                TableRow<String[]> row = new TableRow<>();
                row.setPrefHeight(40);
                return row;
            });

            TableColumn<String[], String> userCol = new TableColumn<>("UserName");
            userCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[0]));

            TableColumn<String[], String> packageCol = new TableColumn<>("PackageName");
            packageCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[1]));

            TableColumn<String[], String> emailCol = new TableColumn<>("Email-Id");
            emailCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[2]));

            TableColumn<String[], String> statusCol = new TableColumn<>("PaymentStatus");
            statusCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue()[3]));

            supportTable.getColumns().addAll(userCol, packageCol, emailCol, statusCol);

            supportTable.getItems().addAll(
                new String[]{"Ram", "Tirupati Balaji", "ram@gmail.com", "Done"},
                new String[]{"Sita", "Kedarnath Dham", "sita@gmail.com", "Done"},
                new String[]{"Bharat", "Ayodhya Dham", "bharat@gmail.com", "Unpaid"}
            );

            VBox content = new VBox(10, heading, supportLabel, supportTable);
            content.setPadding(new Insets(20));
            rightContent.getChildren().add(content);
        });

        addPackageButton.setOnAction(e -> {
            add_destination destinationPage = new add_destination();
            try {
                primaryStage.setScene(destinationPage.getDestinationScene(primaryStage)); 
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Navigation Error", "Could not load Add Destination page.", Alert.AlertType.ERROR);
            }
        });

        HBox hBox = new HBox();
        hBox.getChildren().addAll(sidebar, rightContent);
        HBox.setHgrow(rightContent, Priority.ALWAYS);

        VBox main = new VBox(topBar, hBox);
        VBox.setVgrow(hBox, Priority.ALWAYS);

        ScrollPane scrollPane = new ScrollPane(main);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setPannable(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Scene sc = new Scene(scrollPane,1300, 700);
        
        primaryStage.setScene(sc);
        primaryStage.setMaximized(true);
        primaryStage.show();

        return sc; 
    } 
    
    private VBox createItem(String title, String subtitle, Stage mainStage) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");

        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");

        VBox box = new VBox(2);
        box.getChildren().addAll(titleLabel, subtitleLabel);
        box.setStyle("-fx-padding: 5px; -fx-background-radius: 5;");

        box.setOnMouseEntered(e -> box.setStyle("-fx-padding: 5px; -fx-background-radius: 5; -fx-background-color: #eeeeee;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-padding: 5px; -fx-background-radius: 5;"));

        // --- CONSOLIDATED setOnMouseClicked Handler ---
        box.setOnMouseClicked(e -> {
            System.out.println("Clicked: " + title);

            if (title.equals("👤 My Profile")) {
                try {
                    profile1 adminProfileViewPage = new profile1(); // Correct class name
                    adminProfileViewPage.setLoggedInAdminUid(this.loggedInAdminUid); // Pass context
                    adminProfileViewPage.setAdminEmail(this.adminEmail);
                    adminProfileViewPage.setAdminRole(this.adminRole);
                    Scene profileViewScene = adminProfileViewPage.getAdminProfileViewScene(mainStage); // Call correct method
                    mainStage.setScene(profileViewScene); // Set the scene
                    mainStage.setTitle("TirthOnGo - Admin Profile"); // Update title
                } catch (Exception ex) {
                    ex.printStackTrace();
                    showAlert("Navigation Error", "Could not load Admin Profile View page.", Alert.AlertType.ERROR);
                }
            } else if (title.equals("🔓 Sign Out")) { // Handle "Sign Out"
                this.loggedInAdminUid = null; // Clear Admin-specific session data
                this.adminEmail = null;
                this.adminRole = null;
                System.out.println("Admin session data cleared.");
                showAlert("Signed Out", "You have been successfully signed out.", Alert.AlertType.INFORMATION);
                try {
                    LoginPage loginPage = new LoginPage();
                    loginPage.start(mainStage); // Navigate to LoginPage
                    mainStage.setTitle("TirthOnGo - Login");
                } catch (Exception ex) {
                    System.err.println("Error navigating to Login Page after sign out: " + ex.getMessage());
                    ex.printStackTrace();
                    showAlert("Navigation Error", "Failed to load login page after sign out.", Alert.AlertType.ERROR);
                }
            }
        });

        return box;
    }

    private VBox createProfilePopup(Stage mainStage) { // mainStage passed correctly
        VBox box = new VBox(20);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; "
                   + "-fx-border-color: #ccc; -fx-border-radius: 8;");
        box.setPrefWidth(260);
        box.setPrefHeight(300);

        Label greeting = new Label("Hello");
        if (adminEmail != null) {
             greeting.setText("Hello, " + adminEmail.split("@")[0]); 
        }
        greeting.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        box.getChildren().addAll(
            greeting,
            new Separator(),
            createItem("👤 My Profile", "Manage your profile.", mainStage), 
            new Separator(),
            createItem("🔓 Sign Out", "Logout from your account", mainStage) 
        );
        return box;
    }

   
    private StackPane createHomeView() {
        Image bgImage = new Image("Assets\\Images\\welcome.jpg"); // update with your image path
        ImageView imageView = new ImageView(bgImage);
        imageView.setFitWidth(1300);
        imageView.setFitHeight(700);
        imageView.setPreserveRatio(true);
        

        // Add blur effect
        javafx.scene.effect.GaussianBlur blur = new javafx.scene.effect.GaussianBlur(5);
        imageView.setEffect(blur);

        // Welcome text - now uses the class-level adminEmail
        Label welcomeText = new Label("Welcome Admin - TirthOnGo");
        if (adminEmail != null) {
            welcomeText.setText("Welcome " + adminEmail.split("@")[0] + " - TirthOnGo");
        }
        welcomeText.setFont(Font.font("Georgia", FontWeight.BOLD, 28));
        welcomeText.setTextFill(Color.web("#333"));
        welcomeText.setStyle("-fx-background-color: rgba(255,255,255,0.7); -fx-padding: 20; -fx-background-radius: 10;");

        StackPane.setAlignment(welcomeText, Pos.CENTER);

        return new StackPane(imageView, welcomeText);
    }

    // Support button data
    private VBox createSupportCard(SupportRequest request) {
        VBox card = new VBox(5);
        card.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: #f9f9f9;");
        card.setPrefWidth(300);

        String imagePath = getAdminProfileImagePath();
        Image profileImage;
        
        try {
            profileImage = new Image(imagePath, true); 
        } catch (Exception e) {
            profileImage = new Image("Assets\\Images\\ProfileImageIcon.jpg"); 
        }

        ImageView profileIcon = new ImageView(profileImage); 
        profileIcon.setFitHeight(60);
        profileIcon.setFitWidth(60);

        // Labels + Normal values (only labels are bold)
        Label nameLabel = new Label();
        nameLabel.setText("Name: ");
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
        nameLabel.setFont(Font.font("Arial"));

        Label nameValue = new Label(request.getName());
        nameValue.setStyle("-fx-font-size: 15");
        nameValue.setFont(Font.font("Arial"));

        Label emailLabel = new Label("Email: ");
        emailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
        emailLabel.setFont(Font.font("Arial"));
        Label emailValue = new Label(request.getEmail());
        emailValue.setStyle("-fx-font-size: 15");
        emailValue.setFont(Font.font("Arial"));

        Label phoneLabel = new Label("Phone: ");
        phoneLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
        phoneLabel.setFont(Font.font("Italic"));
        Label phoneValue = new Label(request.getPhone());
        phoneValue.setStyle("-fx-font-size: 15");
        phoneValue.setFont(Font.font("Italic"));

        Label messageLabel = new Label("Message: ");
        messageLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
        messageLabel.setFont(Font.font("Georgia"));
        Label messageValue = new Label(request.getMessage());
        messageValue.setStyle("-fx-font-size: 15");
        messageValue.setFont(Font.font("Georgia"));
        messageValue.setWrapText(true);

        HBox nameBox = new HBox(5, nameLabel, nameValue);
        HBox emailBox = new HBox(5, emailLabel, emailValue);
        HBox phoneBox = new HBox(5, phoneLabel, phoneValue);
        HBox messageBox = new HBox(5, messageLabel, messageValue);

        VBox textBox = new VBox(3);
        textBox.getChildren().addAll(nameBox, emailBox, phoneBox, messageBox);

        HBox contentBox = new HBox(10);
        contentBox.getChildren().addAll(profileIcon, textBox);
        contentBox.setAlignment(Pos.TOP_LEFT);

        card.getChildren().addAll(contentBox);
        return card;
    }

    private String getAdminProfileImagePath() {
        // --- FIXED IMAGE PATH ---
        String defaultImagePath = "/Assets/Images/ProfileImageIcon.jpg";
        try {
            return getClass().getResource(defaultImagePath).toExternalForm();
        } catch (NullPointerException e) {
            System.err.println("ERROR: Default ProfileImageIcon.jpg not found at " + defaultImagePath);
            return "data:image/gif;base64,R0lGODlhAQABAAD/ACwAAAAAAQABAAACADs="; // Fallback
        }
    }

    private VBox createUserCard(UserRequest req) {
        VBox card2 = new VBox(5);
        card2.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-padding: 10; -fx-background-color: #f9f9f9;");
        card2.setPrefWidth(300);

        // --- FIXED IMAGE PATH ---
        ImageView profile = new ImageView(getClass().getResource("/Assets/Images/ProfileImageIcon.jpg").toExternalForm());
        profile.setFitHeight(60);
        profile.setFitWidth(60);

        Label nameLabel = new Label();
        nameLabel.setText("Name: ");
        nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
        nameLabel.setFont(Font.font("Arial"));

        Label nameValue = new Label(req.getName());
        nameValue.setStyle("-fx-font-size: 15");
        nameValue.setFont(Font.font("Arial"));

        Label emailLabel = new Label("Email: ");
        emailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
        emailLabel.setFont(Font.font("Arial"));
        Label emailValue = new Label(req.getEmail());
        emailValue.setStyle("-fx-font-size: 15");
        emailValue.setFont(Font.font("Arial"));

        Label phoneLabel = new Label("Phone: ");
        phoneLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18");
        phoneLabel.setFont(Font.font("Italic"));
        Label phoneValue = new Label(req.getPhone());
        phoneValue.setStyle("-fx-font-size: 15");
        phoneValue.setFont(Font.font("Italic"));
       
        HBox nameBox = new HBox(5, nameLabel, nameValue);
        HBox emailBox = new HBox(5, emailLabel, emailValue);
        HBox phoneBox = new HBox(5, phoneLabel, phoneValue);
        
        VBox textBox = new VBox(3);
        textBox.getChildren().addAll(nameBox, emailBox, phoneBox);

        HBox contentBox = new HBox(10);
        contentBox.getChildren().addAll(profile, textBox);
        contentBox.setAlignment(Pos.TOP_LEFT);

        card2.getChildren().addAll(contentBox);
        return card2;
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
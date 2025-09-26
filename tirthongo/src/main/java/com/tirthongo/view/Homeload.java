package com.tirthongo.view;
import java.net.URI;
import java.util.List;
import com.tirthongo.dao.DestinationService;
import com.tirthongo.dao.userdoc;
import com.tirthongo.model.DestinationModel;
import com.tirthongo.model.UserProfileModel;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import java.awt.Desktop;


public class Homeload {
    int count;
    int count1 = 0;

    private static String loggedInUserUid;
    private static String userEmail;
    private static String userRole;

    public  List<DestinationModel> destinationList;
    private HBox hBox = new HBox();

    public Homeload(){
        destinationList = DestinationService.fetchAllDestinations();
    }

    // --- NEW: Public setters to receive the context ---
    public void setLoggedInUserUid(String uid) {
        this.loggedInUserUid = uid;
        System.out.println("UserDashboardPage: Received UID: " + loggedInUserUid); // For debugging
    }

    public void setUserEmail(String email) {
        this.userEmail = email;
        System.out.println("UserDashboardPage: Received Email: " + userEmail); // For debugging
    }

    public void setUserRole(String role) {
        this.userRole = role;
        System.out.println("UserDashboardPage: Received Role: " + userRole); // For debugging
    }


// Add these methods to your Homeload class
private static UserProfileModel profile;

public static void setUserProfile(UserProfileModel profileModel){
    profile = profileModel;
    System.out.println("Homeload: Static profile updated");
    if (profileModel != null) {
        System.out.println("Profile email: " + profileModel.getEmail());
        System.out.println("Profile name: " + profileModel.getFirstName() + " " + profileModel.getLastName());
    }
}

public static UserProfileModel getUserProfileModel(){
    System.out.println("Homeload: Getting static profile - " + (profile != null ? "Found" : "NULL"));
    return profile;
}

// NEW: Method to initialize profile when user logs in
public static void initializeUserProfile(String uid, String email) {
    System.out.println("=== Initializing User Profile in Homeload ===");
    System.out.println("UID: " + uid);
    System.out.println("Email: " + email);
    
    try {
        // Try to fetch profile by UID first
        UserProfileModel userProfile = userdoc.getUserProfileModelByUid(loggedInUserUid);
        
        if (userProfile != null) {
            System.out.println("✓ Profile found by UID, setting static profile");
            setUserProfile(userProfile);
        } else {
            System.out.println("Profile not found by UID, trying by email...");
            
            // Try to fetch by email
            userProfile = userdoc.getUserProfileModel(loggedInUserUid);
            
            if (userProfile != null) {
                System.out.println("✓ Profile found by email, setting static profile");
                setUserProfile(userProfile);
            } else {
                System.out.println("No existing profile found, creating basic profile");
                
                // Create basic profile with available info
                UserProfileModel basicProfile = new UserProfileModel();
                basicProfile.setEmail(email);
                basicProfile.setDocumentId(uid);
                setUserProfile(basicProfile);
            }
        }
        
    } catch (Exception e) {
        System.err.println("Error initializing user profile: " + e.getMessage());
        e.printStackTrace();
        
        // Create basic profile as fallback
        UserProfileModel basicProfile = new UserProfileModel();
        basicProfile.setEmail(email);
        basicProfile.setDocumentId(uid);
        setUserProfile(basicProfile);
    }
}

// NEW: Method to refresh profile data
public static void refreshUserProfile(String uid) {
    System.out.println("=== Refreshing User Profile ===");
    
    try {
        UserProfileModel updatedProfile = userdoc.getUserProfileModelByUid(loggedInUserUid);
        if (updatedProfile != null) {
            setUserProfile(updatedProfile);
            System.out.println("✓ Profile refreshed successfully");
        } else {
            System.out.println("✗ No profile found to refresh");
        }
    } catch (Exception e) {
        System.err.println("Error refreshing profile: " + e.getMessage());
        e.printStackTrace();
    }
}

// NEW: Clear profile when user logs out
public static void clearUserProfile() {
    System.out.println("=== Clearing User Profile ===");
    profile = null;
}


public Scene gethomescene(Stage primaryStage) {
        primaryStage.setTitle("TirthOnGo");

        // Topbar
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 3);");        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPrefHeight(30);

        Label title = new Label("ॐ TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#ec7208ff"));

        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, Priority.ALWAYS);

        Image profileImg = new Image("Assets\\Images\\ProfileImageIcon.jpg", 30, 30, true, true);
        ImageView profileIcon = new ImageView(profileImg);
        profileIcon.setFitWidth(40);
        profileIcon.setFitHeight(40);
        Circle clip = new Circle(20, 20, 20);
        profileIcon.setClip(clip);

        Popup popup = new Popup();
        // Pass primaryStage to the popup content creator to allow it to change scenes
        VBox popupContent = createProfilePopup(primaryStage); // Pass primaryStage
        popup.getContent().add(popupContent);
        popup.setAutoHide(true);

        // Show popup when icon is clicked
        profileIcon.setOnMouseClicked((MouseEvent e) -> {
            if (!popup.isShowing()) {
                popup.show(primaryStage, e.getScreenX(), e.getScreenY());
            }
        });

        topBar.getChildren().addAll(title, spacerTop,profileIcon);

        // Buttons
        Button filter = new Button("Filters");
        filter.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: Normal;");
        filter.setFont(Font.font("Arial", 14));

        Button sort = new Button("SortBy");
        sort.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: Normal;");
        sort.setFont(Font.font("Arial", 14));

        Button offers = new Button("Offers&More");
        offers.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: Normal;");
        offers.setFont(Font.font("Arial", 14));

        HBox buttons = new HBox(30, filter, sort, offers);
        buttons.setAlignment(Pos.CENTER);
        //buttons.setPadding(new Insets(5));

        // Filter Pane
        VBox filterContent = new VBox(10);
        filterContent.setPadding(new Insets(10));
        filterContent.setAlignment(Pos.TOP_LEFT);

        ComboBox<String> stateFilter = new ComboBox<>();
        stateFilter.getItems().addAll("Maharashtra", "Uttar Pradesh", "Tamil Nadu", "Gujarat", "Odisha", "Jammu & Kashmir");
        stateFilter.setPromptText("Select State");

        ComboBox<String> hotelType = new ComboBox<>();
        hotelType.getItems().addAll("1 Star", "2 Star", "3 Star", "4 Star", "5 Star", "Dharamshala");
        hotelType.setPromptText("Hotel Type");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Select Date");

        Label budgetLabel = new Label("Select Budget:");
        Slider budgetSlider = new Slider(10000, 100000, 50000);
        budgetSlider.setShowTickMarks(true);
        budgetSlider.setShowTickLabels(true);
        Label budgetValue = new Label("₹" + (int) budgetSlider.getValue());

        budgetSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            budgetValue.setText("₹" + newVal.intValue());
        });

        filterContent.getChildren().addAll(stateFilter, hotelType, datePicker, budgetLabel, budgetSlider, budgetValue);

        TitledPane filterPane = new TitledPane("Filter Options", filterContent);
        filterPane.setExpanded(false);
        filterPane.setMaxWidth(300);
        filterPane.setStyle("-fx-background-color: #a0c7deff");

        // Sort Pane
        VBox sortContent = new VBox(10);
        sortContent.setPadding(new Insets(10));
        sortContent.setAlignment(Pos.TOP_LEFT);

        RadioButton sortByName = new RadioButton("Low-High");
        RadioButton sortByBudget = new RadioButton("High-Low");
        ToggleGroup sortGroup = new ToggleGroup();
        sortByName.setToggleGroup(sortGroup);
        sortByBudget.setToggleGroup(sortGroup);
        sortContent.getChildren().addAll(sortByName, sortByBudget);

        TitledPane sortPane = new TitledPane("Sort Options", sortContent);
        sortPane.setExpanded(false);
        sortPane.setMaxWidth(300);
        sortPane.setStyle("-fx-background-color: #a0c7deff");

        // Offer Pane
        VBox offerContent = new VBox(10);
        offerContent.setPadding(new Insets(10));
        offerContent.setAlignment(Pos.TOP_LEFT);

        CheckBox offer1 = new CheckBox("Get 10% off on first booking!");
        CheckBox offer2 = new CheckBox("Combo Packages Available");
        offerContent.getChildren().addAll(offer1, offer2);

        TitledPane offerPane = new TitledPane("Offers Available", offerContent);
        offerPane.setExpanded(false);
        offerPane.setMaxWidth(300);
        offerPane.setStyle("-fx-background-color: #a0c7deff");


        HBox filterSortOfferRow = new HBox(40); // spacing between panes
   // filterSortOfferRow.setPadding(new Insets(5));
    filterSortOfferRow.setAlignment(Pos.TOP_CENTER); // aligns all children to top
    filterSortOfferRow.getChildren().addAll(filterPane, sortPane, offerPane);

        // Button actions
        filter.setOnAction(event -> {
            filterPane.setExpanded(!filterPane.isExpanded());
            sortPane.setExpanded(false);
            offerPane.setExpanded(false);

        });
        

        sort.setOnAction(event -> {
            sortPane.setExpanded(!sortPane.isExpanded());
            filterPane.setExpanded(false);
            offerPane.setExpanded(false);
        });

        offers.setOnAction(event -> {
            offerPane.setExpanded(!offerPane.isExpanded());
            filterPane.setExpanded(false);
            sortPane.setExpanded(false);
        });

        Button applyButton = new Button("Apply");
        applyButton.setOnAction(e -> System.out.println("Filters Applied"));
        applyButton.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: black; -fx-padding: 10 20 10 20; -fx-font-weight: Normal;");
        applyButton.setFont(Font.font("Arial", 14));

        HBox applybox = new HBox(10,applyButton);
        applybox.setAlignment (Pos.TOP_CENTER);
        applyButton.setPadding(new Insets(10));


        VBox topsection = new VBox(20,buttons,filterSortOfferRow,applybox);
        topsection.setPrefHeight(200);
        
        topsection.setPadding(new Insets(10));
        // Load background image from resources


        Label quoteText = new Label("“Faith Flows Where You GO,\nTirthOnGo Your Spiritual Travel Companion”");
        quoteText.setStyle("-fx-font-size: 21px;"
                 + " -fx-font-family: 'Georgia';"
                 + " -fx-font-weight: bold;"
                 + " -fx-line-spacing: 0.1");
        quoteText.setTextFill(Color.web("#ec7208ff"));

        HBox quBox = new HBox(10,quoteText);
        quBox.setAlignment (Pos.TOP_CENTER);

        

        // BackgroundSize backgroundSize = new BackgroundSize(
        //   100, 100, 
        //   true, true,
        //   false,true );
        //     BackgroundImage bgImage = new BackgroundImage(
        //   new Image(getClass().getResource("/images/backg.jpeg").toExternalForm()),
        //     BackgroundRepeat.NO_REPEAT,
        //     BackgroundRepeat.NO_REPEAT,
        //     BackgroundPosition.CENTER,
        //     backgroundSize
        // );

        //     // Apply background to top section
        //             topsection.setBackground(new Background(bgImage));


        // Recommendations
        Label recommendLabel = new Label("Explore Destinations");
        recommendLabel.setStyle("-fx-font-size:16px; -fx-font-weight:bold; -fx-background-colr: #c7f0db; -fx-text-fill-color: #c7f0db;");
        recommendLabel.setPadding(new Insets(12));
        HBox recommendedBox = new HBox(10);
        recommendedBox.setPadding(new Insets(10));
        recommendedBox.setAlignment(Pos.CENTER_LEFT); 

        String[] places = {"Ayodhya", "Dwarka", "Puri", "Tirupati", "Rameshwaram", "Vaishnavdevi", "Kedarnath"};

        for (String place : places) {
            final String currentPlace = place;
            VBox placeBox = new VBox(10);
            placeBox.setAlignment(Pos.CENTER);
            placeBox.setPadding(new Insets(10));
            placeBox.setStyle("-fx-background-image: url('/Assets/Images/texture.jpg');" +
    "-fx-background-size: cover;" +
    "-fx-background-radius: 20;" +
    "-fx-border-radius: 20;" +
    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5);"
  );

            try {
                Image image = new Image(getClass().getResource("/Assets/Images/" + currentPlace + ".jpg").toExternalForm());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(250);
                imageView.setFitHeight(180);
                imageView.setPreserveRatio(false);
                imageView.setSmooth(true);
                imageView.setClip(new Rectangle(250, 180));

                Button btn = new Button(currentPlace);
                btn.setStyle( "-fx-background-color: linear-gradient(to bottom, #fceabb, #f8b500);" +  // Soft golden blend
    "-fx-text-fill: #4e342e;" +                                              // Deep earthy brown text
    "-fx-font-weight: bold;" +
    "-fx-font-size: 14px;" +
    "-fx-font-family: 'Georgia';" +                                          // Traditional look
    "-fx-background-radius: 12;" +
    "-fx-border-radius: 12;" +
    "-fx-border-color: #d48806;" +
    "-fx-border-width: 2;" +
    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 6, 0, 0, 3);");

                btn.setOnAction((ActionEvent event) -> {
                    System.out.println("Button clicked: " + currentPlace);
                    switch (currentPlace) {
                        case "Ayodhya":
                            primaryStage.setScene(Ayodhya.getScene(primaryStage));
                            break;
                        case "Dwarka":
                            primaryStage.setScene(Dwarka.getScene(primaryStage));
                            break;
                        case "Puri":
                            primaryStage.setScene(Puri.getScene(primaryStage));
                            break;
                        case "Rameshwaram":
                            primaryStage.setScene(Rameshwaram.getScene(primaryStage));
                            break;
                        case "Tirupati":
                            primaryStage.setScene(Tirupati.getScene(primaryStage));
                            break;
                        case "Vaishnavdevi":
                            primaryStage.setScene(Vaishnavdevi.getScene(primaryStage));
                            break;
                        case "Kedarnath":
                            primaryStage.setScene(Kedarnath.getScene(primaryStage));
                            break;
                        default:
                            System.out.println("Navigation not set for: " + currentPlace);
                    }
                });

                placeBox.getChildren().addAll(imageView, btn);
                recommendedBox.getChildren().add(placeBox);

            } catch (Exception e) {
                System.out.println("Image missing for: " + currentPlace);
            }
        }

        ScrollPane horizontalScroll = new ScrollPane(recommendedBox);
        horizontalScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        horizontalScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        horizontalScroll.setFitToHeight(true);

        //Destination booking
         Label destinationLabel = new Label("Booking Destinations");
        destinationLabel.setStyle("-fx-font-size:16px; -fx-font-weight:bold;");
        destinationLabel.setPadding(new Insets(12));
        HBox destinationBox = new HBox(10);
        destinationBox.setPadding(new Insets(10));
        destinationBox.setAlignment(Pos.CENTER_LEFT);
       // destinationBox.setStyle("-fx-bacgrount-color:linear-gradient(to right, #01180dff, #12603cff)");

        

        String[] placesB = {"Chardham", "Ashtavinayk", "Jyotirling", "Kashi", "Tirupati_Kolhapur"};

        for (String place2 : placesB) {
            final String currentPlace = place2;
            VBox placeBox2 = new VBox(10);
            placeBox2.setAlignment(Pos.CENTER);
             placeBox2.setStyle(
    "-fx-background-image: url('/Assets/Images/texture.jpg');" +
    "-fx-background-size: cover;" +
    "-fx-background-radius: 20;" +
    "-fx-border-radius: 20;" +
    "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5);"
);

            try {
                Image image = new Image(getClass().getResource("/Assets/Images/" + currentPlace + ".jpg").toExternalForm());

                System.out.println("-----------------------------------------------------");
                System.out.println(getClass().getResource("/Assets/Images/" + currentPlace + ".jpg").toExternalForm());
                System.out.println("-----------------------------------------------------");

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(250);
                imageView.setFitHeight(180);
                imageView.setPreserveRatio(false);
                imageView.setSmooth(true);
                imageView.setClip(new Rectangle(250, 180));

                Button btn = new Button(currentPlace);
                btn.setStyle("-fx-background-color: linear-gradient(to bottom, #fceabb, #f8b500);" +  // Soft golden blend
    "-fx-text-fill: #4e342e;" +                                              // Deep earthy brown text
    "-fx-font-weight: bold;" +
    "-fx-font-size: 14px;" +
    "-fx-font-family: 'Georgia';" +                                          // Traditional look
    "-fx-background-radius: 12;" +
    "-fx-border-radius: 12;" +
    "-fx-border-color: #d48806;" +
    "-fx-border-width: 2;" +
    "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 6, 0, 0, 3);");

                btn.setOnAction((ActionEvent event) -> {
                    System.out.println("Button clicked: " + currentPlace);
                    switch (currentPlace) {
                        case "Chardham":
                            primaryStage.setScene(Chardham.getScene(primaryStage));
                            break;
                        case "Ashtavinayk":
                            primaryStage.setScene(Ashtavinayk.getScene(primaryStage));
                            break;
                        case "Jyotirling":
                            primaryStage.setScene(Jyotirling.getScene(primaryStage));
                            break;
                        case "Kashi":
                            primaryStage.setScene(DestinationPage.getDestinationScene(primaryStage));
                            break;
                            case "Tirupati_Kolhapur":
                            primaryStage.setScene(Tirupati_Kolhapur.getScene(primaryStage));
                            break;

                        default:
                            System.out.println("Navigation not set for: " + currentPlace);
                    }
                });

                placeBox2.getChildren().addAll(imageView, btn);
                destinationBox.getChildren().add(placeBox2);

            } catch (Exception e) {
                System.out.println("Image missing for: " + currentPlace);
            }
        }
         ScrollPane horizontal2Scroll = new ScrollPane(destinationBox);
        horizontal2Scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        horizontal2Scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        horizontal2Scroll.setFitToHeight(true);
        horizontal2Scroll.setStyle(
          "-fx-background: #d0e0efff ;" +
          "-fx-background-color: #f3d7d7ff; " +
          " -fx-border-color: white;"
        );

        // Recently Added Section (structure only)
        Label recentLabel = new Label("Recently Added");
        recentLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        recentLabel.setPadding(new Insets(12));

        HBox recentBox = new HBox(10);
        recentBox.setPadding(new Insets(10));
        recentBox.setAlignment(Pos.CENTER_LEFT);


        for(DestinationModel model : destinationList){
            final String currentPlace = model.getEndDestination();
            System.out.println(currentPlace);
            VBox placeBox3 = new VBox(10);
            placeBox3.setAlignment(Pos.CENTER);
            placeBox3.setStyle(
                    "-fx-background-image: url('/Assets/Images/texture.jpg');" +
                            "-fx-background-size: cover;" +
                            "-fx-background-radius: 20;" +
                            "-fx-border-radius: 20;" +
                            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 5);");

            try {
                Image image = new Image(
                        getClass().getResource("/Assets/Images/" + currentPlace + ".jpg").toExternalForm());

                System.out.println("-----------------------------------------------------");
                System.out.println(getClass().getResource("/Assets/Images/" + currentPlace + ".jpg").toExternalForm());
                System.out.println("-----------------------------------------------------");

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(250);
                imageView.setFitHeight(180);
                imageView.setPreserveRatio(false);
                imageView.setSmooth(true);
                imageView.setClip(new Rectangle(250, 180));

                Button btn = new Button(currentPlace);
                btn.setStyle("-fx-background-color: linear-gradient(to bottom, #fceabb, #f8b500);" + // Soft golden
                                                                                                     // blend
                        "-fx-text-fill: #4e342e;" + // Deep earthy brown text
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-family: 'Georgia';" + // Traditional look
                        "-fx-background-radius: 12;" +
                        "-fx-border-radius: 12;" +
                        "-fx-border-color: #d48806;" +
                        "-fx-border-width: 2;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 6, 0, 0, 3);");

                // btn.setOnAction((ActionEvent event) -> {
                //     System.out.println("Button clicked: " + currentPlace);
                //     switch (currentPlace) {
                //         case "gangotri":
                //             primaryStage.setScene(Chardham.getScene(primaryStage));
                //             break;
                       

                //         default:
                //             System.out.println("Navigation not set for: " + currentPlace);
                //     }
                // });
                btn.setOnMouseClicked(e->{
                   
    
                    recentadd.setModel(model);
                   primaryStage.setScene(recentadd.getDestinationScene(primaryStage));
                });

                placeBox3.getChildren().addAll(imageView, btn);
                recentBox.getChildren().add(placeBox3);

            } catch (Exception e) {
                System.out.println("Image missing for: " + currentPlace);
            }
            count1++;
        }
            
        
        ScrollPane horizontal3Scroll = new ScrollPane(recentBox);
        horizontal3Scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        horizontal3Scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        horizontal3Scroll.setFitToHeight(true);
        horizontal3Scroll.setStyle(
                "-fx-background: #d0e0efff ;" +
                        "-fx-background-color: #f3d7d7ff; " +
                        " -fx-border-color: white;");



        
        // for (int i = 0; i < destinationList.size(); i++) {
        //     HBox recentBox = new HBox(10, new Text(destinationList.get(i).getStartDestination()));
        //     // recentBox.

        //     recentBox.setPadding(new Insets(10));
        //     recentBox.setAlignment(Pos.CENTER_LEFT);
        
        //     // Placeholder for now (you can add places later)
        //     recentBox.setStyle("-fx-background-color: #f9f5ec; -fx-background-radius: 12;");
        //     hBox.getChildren().add(recentBox);
        // }

        // Scroll container
        // ScrollPane recentScroll = new ScrollPane(hBox);
        // recentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // recentScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // recentScroll.setFitToHeight(true);

        // Spacer between Booking Destinations and Footer
        Region spacer = new Region();
        spacer.setPrefHeight(40); // Adjust height as needed

        // Create Footer Section
        VBox footerBar = new VBox(20);
        footerBar.setStyle("-fx-background-color: black;");
        footerBar.setPadding(new Insets(20));
        footerBar.setAlignment(Pos.BOTTOM_LEFT);

        // Footer Links
        Hyperlink termsLink = new Hyperlink("Terms & Conditions");
        Hyperlink privacyLink = new Hyperlink("Privacy Policy");
        Hyperlink contactLink = new Hyperlink("Contact Us");

        termsLink.setStyle("-fx-text-fill: white; -fx-underline: true");
        privacyLink.setStyle("-fx-text-fill: white; -fx-underline: true;");
        contactLink.setStyle("-fx-text-fill: white; -fx-underline: true;");

        // Set link actions (replace with actual page opens later)
        termsLink.setOnAction(e -> {

        TermsandConditionsPage contact = new TermsandConditionsPage(); // create instance of Homeload
        Scene contactScene = contact.getTermsAndConditions(primaryStage); // get the scene
        primaryStage.setScene(contactScene); 
        });

        privacyLink.setOnAction(e -> {
        
        PrivacyPolicyPage contact = new PrivacyPolicyPage(); // create instance of Homeload
        Scene contactScene = contact.getPrivacyPolicy(primaryStage); // get the scene
        primaryStage.setScene(contactScene); 
        System.out.println("Privacy Policy clicked");
        
});

        contactLink.setOnAction(e -> {
        
        ContactUsPage contact = new ContactUsPage(); // create instance of Homeload
        Scene contactScene = contact.getcontactus(primaryStage); // get the scene
        primaryStage.setScene(contactScene); 
        System.out.println("Contact Us clicked");
    });

        Image instaImg = new Image(Homeload.class.getResource("/Assets/Images/ChatGPT.png").toExternalForm());
        ImageView instaIcon = new ImageView(instaImg);
        instaIcon.setFitWidth(24); // Adjust size
        instaIcon.setFitHeight(24);
        instaIcon.setPreserveRatio(true);
        instaIcon.setStyle("-fx-cursor: hand;");

        instaIcon.setOnMouseClicked(e -> {
            try {
                Desktop.getDesktop().browse(new URI("https://www.instagram.com/newprojectsoon2025")); // replace with your
                                                                                                  
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

       footerBar.getChildren().addAll(
            termsLink, 
            
            privacyLink, 
            
            contactLink, 
             
            instaIcon
        );

        
        VBox content = new VBox(5);
        content.setStyle("-fx-background-color: #fff5cc ");

        // content.setPadding(new Insets(10));
        content.getChildren().addAll(topBar, topsection, quBox, recommendLabel, horizontalScroll, destinationLabel,
                horizontal2Scroll, recentLabel,horizontal3Scroll, spacer, footerBar);

        ScrollPane verticalScroll = new ScrollPane(content);
        verticalScroll.setFitToWidth(true);
        verticalScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        Scene homeScene = new Scene(verticalScroll, 1300, 620);

        primaryStage.setMaximized(true);
        return homeScene;
    }

    // In AdminDashboardPage.java, replace your existing createProfilePopup method:
    private VBox createProfilePopup(Stage mainStage) { // mainStage passed correctly
        VBox box = new VBox(20);
        box.setPadding(new Insets(15));
        box.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; "
                + "-fx-border-color: #ccc; -fx-border-radius: 8;");
        box.setPrefWidth(260);
        box.setPrefHeight(300);

        Label greeting = new Label("Hello,"+ userEmail.split("@")[0]);
        // Use the class-level 'adminEmail' field for the greeting
        if (this.userEmail != null && !this.userEmail.isEmpty()) { // Access the class field 'adminEmail'
            greeting.setText("Hello, " + this.userEmail.split("@")[0]);
        }
        greeting.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

        box.getChildren().addAll(
                greeting,
                new Separator(),
                createItem("👤 My Profile", "Manage your profile.", mainStage), // Pass mainStage
                new Separator(),
                createItem("🚌 My Trips", "View all bookings.", mainStage), // Pass mainStage
                new Separator(),
                createItem("🔓 Sign Out", "Logout from your account", mainStage) // Pass mainStage
        );
        return box;
    }

    // In AdminDashboardPage.java, replace your existing createItem method:
    private VBox createItem(String title, String subtitle, Stage mainStage) {
        Label titleLabel = new Label(title);
        titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 13px;");

        Label subtitleLabel = new Label(subtitle);
        subtitleLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666;");

        VBox box = new VBox(2);
        box.getChildren().addAll(titleLabel, subtitleLabel);
        box.setStyle("-fx-padding: 5px; -fx-background-radius: 5;");

        box.setOnMouseEntered(
                e -> box.setStyle("-fx-padding: 5px; -fx-background-radius: 5; -fx-background-color: #eeeeee;"));
        box.setOnMouseExited(e -> box.setStyle("-fx-padding: 5px; -fx-background-radius: 5;"));

        // --- CONSOLIDATED AND CORRECTED setOnMouseClicked Handler ---
    box.setOnMouseClicked(e -> {
        System.out.println("Clicked: " + title);

        if (title.equals("👤 My Profile")) {
            try {
                // Navigate to AdminProfileViewPage (formerly profile1)
                profile2 adminProfileViewPage = new profile2();

                adminProfileViewPage.setLoggedInUserUid(loggedInUserUid); // Pass context
                    adminProfileViewPage.setUserEmail(userEmail);
                    adminProfileViewPage.setUserRole(userRole);

                Scene profileViewScene = adminProfileViewPage.getUserProfileViewScene(mainStage); 
                mainStage.setScene(profileViewScene); 
                mainStage.setTitle("TirthOnGo - Admin Profile"); 
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Navigation Error", "Could not load Admin Profile View page.", Alert.AlertType.ERROR);
            }
        } else if (title.equals("🚌 My Trips")) {
            try {
                
                MyTrips tripsPage = new MyTrips();
                Scene mytrips = tripsPage.mytripScene(mainStage); 
                mainStage.setScene(mytrips);
                
            } catch (Exception ex) {
                ex.printStackTrace();
                showAlert("Navigation Error", "Could not load My Trips page.", Alert.AlertType.ERROR);
            }
        } else if (title.equals("🔓 Sign Out")) {
            // Sign Out Logic
            this.loggedInUserUid = null; // Clear Admin-specific session data
            this.userEmail = null;
            this.userRole = null;
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

    // }

    // New helper method for alerts (copied from UserProfile)
    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

   
}

// // Placeholder for now (you can add places later)
// recentBox.setStyle("-fx-background-color: #f9f5ec; -fx-background-radius: 12;");

// // Scroll container
// ScrollPane recentScroll = new ScrollPane(recentBox);
// recentScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
// recentScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
// recentScroll.setFitToHeight(true);


//        // Spacer between Booking Destinations and Footer
//     Region spacer = new Region();
//     spacer.setPrefHeight(40); // Adjust height as needed


//         // Create Footer Section
//         HBox footerBar = new HBox();
//         footerBar.setStyle("-fx-background-color: black;");
//         footerBar.setPadding(new Insets(20));
//         footerBar.setAlignment(Pos.CENTER);
//       footerBar.setSpacing(40); // Space between links

//         // Footer Links
//         Hyperlink termsLink = new Hyperlink("Terms & Conditions");
//         Hyperlink privacyLink = new Hyperlink("Privacy Policy");
//         Hyperlink contactLink = new Hyperlink("Contact Us");

//         termsLink.setStyle("-fx-text-fill: white; -fx-underline: true;");
//         privacyLink.setStyle("-fx-text-fill: white; -fx-underline: true;");
//         contactLink.setStyle("-fx-text-fill: white; -fx-underline: true;");

//         // Set link actions (replace with actual page opens later)
//         termsLink.setOnAction(e -> System.out.println("Terms & Conditions clicked"));
//         privacyLink.setOnAction(e -> System.out.println("Privacy Policy clicked"));
//         contactLink.setOnAction(e -> System.out.println("Contact Us clicked"));

//         Image instaImg = new Image (Homeload.class.getResource("/Assets/Images/instagram.jpg").toExternalForm());
//         ImageView instaIcon = new ImageView(instaImg);
//         instaIcon.setFitWidth(24); // Adjust size
//         instaIcon.setFitHeight(24);
//         instaIcon.setPreserveRatio(true);
//         instaIcon.setStyle("-fx-cursor: hand;");

//         instaIcon.setOnMouseClicked(e -> {
//        try {
//          Desktop.getDesktop().browse(new URI("https://www.instagram.com/your_page_name")); // replace with your handle
//          } catch (Exception ex) {
//           ex.printStackTrace();
//           }
//         });



//         footerBar.getChildren().addAll(termsLink, privacyLink, contactLink,instaIcon);


//                 ////
//         VBox content = new VBox(5);
//        content.setStyle("-fx-background-color: #fff5cc ");

//         //content.setPadding(new Insets(20));
//         content.getChildren().addAll(topBar, topsection, quoteText, recommendLabel, horizontalScroll,destinationLabel,horizontal2Scroll,recentLabel,recentScroll, spacer,footerBar);

//         ScrollPane verticalScroll = new ScrollPane(content);
//         verticalScroll.setFitToWidth(true);
//         verticalScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    
//         Scene homeScene = new Scene(verticalScroll,1300, 720);

//         primaryStage.setMaximized(true);
//         return homeScene;
//     }

//     // In AdminDashboardPage.java, replace your existing createProfilePopup method:
// private VBox createProfilePopup(Stage mainStage) { // mainStage passed correctly
//     VBox box = new VBox(20);
//     box.setPadding(new Insets(15));
//     box.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8; "
//                + "-fx-border-color: #ccc; -fx-border-radius: 8;");
//     box.setPrefWidth(260);
//     box.setPrefHeight(300);

//     Label greeting = new Label("Hello, Admin");
//     // Use the class-level 'adminEmail' field for the greeting
//     if (this.userEmail != null && !this.userEmail.isEmpty()) { // Access the class field 'adminEmail'
//         greeting.setText("Hello, " + this.userEmail.split("@")[0]); 
//     }
//     greeting.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");

//     box.getChildren().addAll(
//         greeting,
//         new Separator(),
//         createItem("👤 My Profile", "Manage your profile.", mainStage), // Pass mainStage
//         new Separator(),
//         createItem("🚌 My Trips", "View all bookings.", mainStage), // Pass mainStage
//         new Separator(),
//         createItem("🔓 Sign Out", "Logout from your account", mainStage) // Pass mainStage
//     );
//     return box;
// }

//     // In AdminDashboardPage.java, replace your existing createItem method:
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

//     // --- CONSOLIDATED AND CORRECTED setOnMouseClicked Handler ---
//     box.setOnMouseClicked(e -> {
//         System.out.println("Clicked: " + title);

//         if (title.equals("👤 My Profile")) {
//             try {
//                 // Navigate to AdminProfileViewPage (formerly profile1)
//                 profile2 adminProfileViewPage = new profile2();

//                 adminProfileViewPage.setLoggedInUserUid(loggedInUserUid); // Pass context
//                     adminProfileViewPage.setUserEmail(userEmail);
//                     adminProfileViewPage.setUserRole(userRole);

//                 Scene profileViewScene = adminProfileViewPage.getUserProfileViewScene(mainStage); 
//                 mainStage.setScene(profileViewScene); 
//                 mainStage.setTitle("TirthOnGo - Admin Profile"); 
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//                 showAlert("Navigation Error", "Could not load Admin Profile View page.", Alert.AlertType.ERROR);
//             }
//         } else if (title.equals("🚌 My Trips")) {
//             try {
                
//                 MyTrips tripsPage = new MyTrips();
//                 Scene mytrips = tripsPage.mytripScene(mainStage); 
//                 mainStage.setScene(mytrips);
                
//             } catch (Exception ex) {
//                 ex.printStackTrace();
//                 showAlert("Navigation Error", "Could not load My Trips page.", Alert.AlertType.ERROR);
//             }
//         } else if (title.equals("🔓 Sign Out")) {
//             // Sign Out Logic
//             this.loggedInUserUid = null; // Clear Admin-specific session data
//             this.userEmail = null;
//             this.userRole = null;
//             System.out.println("Admin session data cleared.");
//             showAlert("Signed Out", "You have been successfully signed out.", Alert.AlertType.INFORMATION);
//             try {
//                 LoginPage loginPage = new LoginPage();
//                 loginPage.start(mainStage); // Navigate to LoginPage
//                 mainStage.setTitle("TirthOnGo - Login");
//             } catch (Exception ex) {
//                 System.err.println("Error navigating to Login Page after sign out: " + ex.getMessage());
//                 ex.printStackTrace();
//                 showAlert("Navigation Error", "Failed to load login page after sign out.", Alert.AlertType.ERROR);
//             }
//         }
//     });

//     return box;
// }

// // }

//     // New helper method for alerts (copied from UserProfile)
//     private void showAlert(String title, String message, Alert.AlertType alertType) {
//         Alert alert = new Alert(alertType);
//         alert.setTitle(title);
//         alert.setHeaderText(null);
//         alert.setContentText(message);
//         alert.showAndWait();
//     }
// }

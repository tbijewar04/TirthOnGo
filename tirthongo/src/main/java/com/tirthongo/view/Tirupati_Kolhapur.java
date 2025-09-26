package com.tirthongo.view;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Tirupati_Kolhapur {

    public static Scene getScene(Stage primaryStage) {

        
        // Top bar
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 3);");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("ॐ TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#ec7208ff")); 

        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, Priority.ALWAYS);
        topBar.getChildren().addAll(title, spacerTop);

        // Back button
        Button backButton = new Button("← Back to Home");
        backButton.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        backButton.setStyle("-fx-background-color: #ed8e3bff; -fx-text-fill: #040302ff; -fx-cursor: hand;");
        backButton.setOnAction(e -> primaryStage.setScene(new Homeload().gethomescene(primaryStage)));

        // Title
        Label introLabel = new Label("Let's book your Tirupati-Kolhapur tour now");
        introLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        introLabel.setTextFill(Color.web("#333"));

       // Main large image on the left
        ImageView mainImage = createImageView("/Assets/Images/Tirupati_Kolhapur.jpg", 500, 320);

                // Right side layout: Two VBoxes side-by-side
            ImageView img1 = createSmallImage("kolhapur1.jpg");
        ImageView img2 = createSmallImage("kolhapur2.jpg");
        ImageView img3 = createSmallImage("kolhapur3.jpg");
        ImageView img4 = createSmallImage("kolhapur4.jpg");

    // VBox 1: Kashi1, Kashi2
        VBox column1 = new VBox(20, img1, img2);
        column1.setAlignment(Pos.CENTER);

    // VBox 2: Kashi3, Kashi4
         VBox column2 = new VBox(20, img3, img4);
        column2.setAlignment(Pos.CENTER);

    // Right side horizontal box with two VBoxes
        HBox rightSide = new HBox(20, column1, column2);
        rightSide.setAlignment(Pos.CENTER);

    // Final gallery layout with main image left and grid right
         HBox imageGallery = new HBox(30, mainImage, rightSide);
        imageGallery.setPadding(new Insets(10, 0, 10, 0));
        imageGallery.setAlignment(Pos.CENTER);

          Label tripTitle = new Label("🕉️ Char Dham Yatra - 5 Nights");
        tripTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        tripTitle.setTextFill(Color.web("#333"));


        // LEFT: Itinerary & Cancellation
       VBox itineraryBox = new VBox(20);
        itineraryBox.setPadding(new Insets(10));
        itineraryBox.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-width: 1;");
      
        // Title label
        Label itineraryTitle = new Label("Tirupati-Kolhapur 4 Days");
        itineraryTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        Label day1 = new Label("Day 1");
day1.setFont(Font.font("Arial", FontWeight.BOLD, 14));
Label desc1 = new Label(
    "- Depart from Pune to Tirupati (by flight/train).\n" +
    "- Arrive and check-in at hotel in Tirupati.\n" +
    "- Evening: Visit Padmavathi Temple & local markets."
);
desc1.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
desc1.setWrapText(true);

Label day2 = new Label("Day 2");
day2.setFont(Font.font("Arial", FontWeight.BOLD, 14));
Label desc2 = new Label(
    "- Early morning: Visit Tirumala Balaji Temple.\n" +
    "- Darshan, laddoo prasadam collection.\n" +
    "- Visit nearby temples: Govindaraja, ISKCON.\n" +
    "- Overnight stay in Tirupati."
);
desc2.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
desc2.setWrapText(true);

Label day3 = new Label("Day 3");
day3.setFont(Font.font("Arial", FontWeight.BOLD, 14));
Label desc3 = new Label(
    "- Return to Pune from Tirupati.\n" +
    "- Travel to Kolhapur by train/car.\n" +
    "- Evening: Visit Mahalaxmi Temple.\n" +
    "- Local food and rest."
);
desc3.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
desc3.setWrapText(true);

Label day4 = new Label("Day 4");
day4.setFont(Font.font("Arial", FontWeight.BOLD, 14));
Label desc4 = new Label(
    "- Visit Rankala Lake, Jyotiba Temple (optional).\n" +
    "- Explore Kolhapuri shopping and cuisine.\n" +
    "- Return journey to Pune by evening."
);
desc4.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
desc4.setWrapText(true);


        // GridPane setup
            GridPane itineraryGrid = new GridPane();
        itineraryGrid.setVgap(20);
        itineraryGrid.setHgap(20);

                 
        

        // Add rows to GridPane
        itineraryGrid.addRow(0, day1, desc1);
            itineraryGrid.addRow(1, day2, desc2);
        itineraryGrid.addRow(2, day3, desc3);
         itineraryGrid.addRow(3, day4, desc4);

        // Add title and grid to VBox
        itineraryBox.getChildren().addAll(itineraryTitle, itineraryGrid);

        //Cancel 
        VBox cancelBox = new VBox(20); // 10px gap between children
        cancelBox.setPadding(new Insets(10));
        cancelBox.setStyle("-fx-background-color: white; -fx-border-color: #ccc; -fx-border-width: 1;");

        Label cancellationTitle = new Label("Cancellation & Date Change Policy");
        cancellationTitle.setFont(Font.font("Arial", FontWeight.MEDIUM, 20));

        // Separate labels for spacing
        Label policyLine1 = new Label("Till 02 Nov 25 - ₹2,750 Cancellation Fee");
        policyLine1.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Label policyLine2 = new Label("After 03 Nov 25 - Non-Refundable");
        policyLine2.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        // Add to VBox
        cancelBox.getChildren().addAll(cancellationTitle, policyLine1, policyLine2);

        VBox leftInfo = new VBox(25,itineraryBox,cancelBox );
        leftInfo.setPadding(new Insets(30));
        

        // RIGHT: Pricing & Booking
        VBox bookingBox = new VBox(20);
        bookingBox.setPadding(new Insets(20));
        bookingBox.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #ccc; -fx-border-width: 1;");
        bookingBox.setPrefWidth(300);

        Label priceLabel = new Label("₹25,600 / Adult");
        priceLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 24));
        Button payButton = new Button("PROCEED TO PAYMENT");
        payButton.setStyle("-fx-background-color: linear-gradient(to right, #00c6ff, #0072ff); -fx-text-fill: white;");
        payButton.setPrefWidth(250);

        payButton.setOnAction(e -> {
    double amount = 45318.00; // Replace with dynamic value if needed

    // Create the PaymentGateway and pass the current scene as 'previousScene'
    PaymentGateway paymentGateway = new PaymentGateway(primaryStage, amount, primaryStage.getScene());

    // Switch to the payment scene
    Scene paymentScene = paymentGateway.getScene();
    primaryStage.setScene(paymentScene);
});

        Label emiInfo = new Label("No cost EMI @ ₹805");
        TextField couponField = new TextField();
        couponField.setPromptText("Enter Coupon Code");

         bookingBox.getChildren().addAll(priceLabel, payButton, emiInfo);
         
         //OFFERS
          VBox offerBox = new VBox(20);
        offerBox.setPadding(new Insets(20));
        offerBox.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: #ccc; -fx-border-width: 1;");
        offerBox.setPrefWidth(300);

        Label offerTitle = new Label("Coupons & Offers");
        offerTitle.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        VBox offer1 = new VBox(10,
          new Label("💳 No cost EMI @ ₹805"),
          new Label("Book your holidays with Easy EMI options")
               );
           VBox offer2 = new VBox(
           new Label("🪙 SUPERDEAL - ₹230"),
          new Label("Navratri special-10%")
          );
        VBox offer3 = new VBox(
          new Label("🎁 MMTHLD - ₹215"),
          new Label("Grab Your Discount Before It's Gone!")
              );

      offerBox.getChildren().addAll(offerTitle,offer1,offer2,offer3);

         VBox rightinfo = new VBox(25,bookingBox,offerBox );
        rightinfo.setPadding(new Insets(30));
    // Final Layout Below Gallery
    HBox belowGallery = new HBox(50, leftInfo, rightinfo);
    belowGallery.setPadding(new Insets(20, 40, 40, 40));
    belowGallery.setAlignment(Pos.TOP_LEFT);

    // Add this HBox to your main VBox below the imageGallery

        VBox contentLayout = new VBox(15, backButton, introLabel, imageGallery,belowGallery);
        contentLayout.setPadding(new Insets(20, 40, 20, 40));
        contentLayout.setAlignment(Pos.TOP_LEFT);

        ScrollPane scrollPane = new ScrollPane(contentLayout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #ffffffff;");

        VBox mainLayout = new VBox(topBar, scrollPane);
        mainLayout.setStyle("-fx-background-color: #ffffffff;");

        return new Scene(mainLayout, 1300, 700);
    }



     private static ImageView createSmallImage(String fileName) {
        return createImageView("/Assets/Images/" + fileName, 250, 150);
     }

     private static ImageView createImageView(String resourcePath, int width, int height) {
        Image image;
        try {
            image = new Image(Kashi.class.getResource(resourcePath).toExternalForm());
        } catch (Exception e) {
            System.out.println("Error loading image: " + resourcePath);
            image = new Image("https://via.placeholder.com/" + width + "x" + height);
        }

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(false);
        imageView.setSmooth(true);
        imageView.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 4, 0, 1, 1);");

        imageView.setOnMouseClicked(e -> showImagePopup(resourcePath));
        return imageView;
        }

         private static void showImagePopup(String imagePath) {
        Stage popupStage = new Stage();
        popupStage.setTitle("Image Preview");

        Image image = new Image(Kashi.class.getResource(imagePath).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(600);
        imageView.setFitHeight(400);

        StackPane root = new StackPane(imageView);
        root.setStyle("-fx-background-color: white; -fx-padding: 20;");

        Scene scene = new Scene(root,1300, 700);
        popupStage.setScene(scene);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.showAndWait();
     }
}




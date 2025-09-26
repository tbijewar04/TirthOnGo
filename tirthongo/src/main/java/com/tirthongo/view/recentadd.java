// package com.tirthongo.view;

// public class recentadd {
    
// }

package com.tirthongo.view;
import com.tirthongo.model.DestinationModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class recentadd {

    public static DestinationModel model;
    public static void setModel(DestinationModel model1){
        model=model1;
    }
    
    static VBox couponsContainer = new VBox(10); 
     
    public static Scene getDestinationScene(Stage primaryStage) {
        
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F9F9F9;");

        // Top bar with title and profile
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-text-fill: white; -fx-padding: 10 20 10 20; -fx-font-weight: bold;");
        topBar.setAlignment(Pos.CENTER_LEFT);

         // Back button
        Button backButton = new Button("← Back to Home");
        backButton.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        backButton.setStyle("-fx-background-color: #ed8e3bff; -fx-text-fill: #040302ff; -fx-cursor: hand;");
        backButton.setOnAction(e -> primaryStage.setScene(new Homeload().gethomescene(primaryStage)));

        Label title = new Label("🛐TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#ec7208ff")); // Saffron

        topBar.getChildren().addAll(title);
        root.setTop(topBar);

        // Main content container
        VBox destinationCard = new VBox(15);
        destinationCard.setPadding(new Insets(15));
        destinationCard.setAlignment(Pos.CENTER_LEFT);
        destinationCard.setMaxWidth(Double.MAX_VALUE);
        destinationCard.setStyle("-fx-background-color: #FFFFFF;");
        destinationCard.setEffect(new DropShadow(10, Color.LIGHTGRAY));

        // 🔲 GRID IMAGE GALLERY START
        HBox imageGallery = new HBox(10);
        imageGallery.setPadding(new Insets(10, 0, 20, 0));

        // Main large image
        ImageView mainImage = new ImageView(new Image("Assets\\Images\\tirumala.jpg"));
        mainImage.setFitWidth(500);
        mainImage.setFitHeight(300);
        mainImage.setPreserveRatio(false);
        mainImage.setStyle("-fx-background-radius: 10;");

        // Right side 2x2 image grid
        VBox rightImageGrid = new VBox(20);

        HBox topRow = new HBox(10);
        // ImageView img2 = createSmallImage("Assets\\Images\\tirupati2.jpeg");
        // ImageView img3 = createSmallImage("Assets\\Images\\tirupati4.jpg");
        ImageView img4 = createSmallImage("Assets\\Images\\tirupat1.jpg");
        topRow.getChildren().addAll(img4);

        // HBox bottomRow = new HBox(10);
        // ImageView img5 = createSmallImage("Assets//Images//KashiAbcde.jpg");
        // ImageView img6 = createSmallImage("Assets//Images//KashiAbcdef.jpg");
        // ImageView img7 = createSmallImage("Assets//Images//KashiAbcdefg.jpg");
        // bottomRow.getChildren().addAll(img5, img6, img7);

        rightImageGrid.getChildren().addAll(topRow);

        imageGallery.getChildren().addAll(mainImage, rightImageGrid);
        // 🔲 GRID IMAGE GALLERY END

        // Root layout with horizontal division
        HBox HBD = new HBox(10);
        HBD.setPadding(new Insets(10, 10, 10, 25));
        HBD.setStyle("-fx-background-color: #f8f8f8;");

        // LEFT CONTENT (Itinerary)
        VBox VBM = new VBox(20);
        VBM.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);");
        VBM.setPrefWidth(800);
        VBM.setPrefHeight(800); // just for testing

        VBox policySection = new VBox(10);
        policySection.setStyle("-fx-padding: 15; -fx-background-color: #ffffffff; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);;");
        policySection.setMaxHeight(500);

        Label heading = new Label("Cancellation & Date Change Policy");
        heading.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #000000ff;");

        Label cancelTitle = new Label("Package Cancellation Policy");
        cancelTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000ff;");

        Label cancelContent = new Label(
                "Till 26 Aug 25 - ₹4,750 Cancellation Fee\n" +
                "After 26 Aug 25 - Non-Refundable\n\n" +
                "• These are non-refundable amounts as per components selected at the time of booking.\n" +
                "• TCS once collected is non-refundable under any circumstances.\n" +
                "• Tax is excluded unless mentioned."
        );
        cancelContent.setStyle("-fx-font-size: 13px; -fx-text-fill: #000000ff;");

        Label dateChangeTitle = new Label("Package Date Change Policy");
        dateChangeTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000000ff;");

        Label dateChangeContent = new Label(
                "Can cancel within 4 days after confirmation- ₹4,750 Date Change Fee\n" +
                "Cannot be cancelled after extension of due dates - Cannot be changed\n\n" +
                "• Charges are non-refundable once collected.\n" +
                "• Date change is subject to availability of components.\n" +
                "• Tax is excluded unless mentioned."
        );
        dateChangeContent.setStyle("-fx-font-size: 13px; -fx-text-fill: #000000ff;");

        policySection.getChildren().addAll(
                heading,
                cancelTitle,
                cancelContent,
                dateChangeTitle,
                dateChangeContent
        );

        VBox leftSection = new VBox(20);
        leftSection.setPrefWidth(850);

        Label fromLabel = new Label(model.getStartDestination()+ "to" +model.getEndDestination());
        
        fromLabel.setFont(Font.font("Arial", 14));
        fromLabel.setTextFill(Color.web("#555"));

        VBox itineraryCard = new VBox(0);
        itineraryCard.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);");
        itineraryCard.setMaxWidth(800);

        // Header
        Label velloreLabel = new Label(model.getEndDestination()+" - 2 Nights Stay");
        velloreLabel.setStyle("-fx-background-color: #f3d9c4; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 15;");

        VBox daysBox = new VBox();
        daysBox.setPadding(new Insets(10));

        for(int i = 0;i<model.getDaysDescription().size();i++){
            daysBox.getChildren().add(createDayRow("Day "+(i+1), "", "🏨", model.getDaysDescription().get(i) ));
        }
       

        itineraryCard.getChildren().addAll(velloreLabel, daysBox);

        Label toLabel = new Label(model.getEndDestination()+" to "+model.getStartDestination());
        toLabel.setFont(Font.font("Arial", 14));
        toLabel.setTextFill(Color.web("#555"));

        leftSection.getChildren().addAll(fromLabel, itineraryCard, toLabel);

        VBM.getChildren().addAll(leftSection,policySection);

        // RIGHT SIDE: Price and Coupons
        VBox rightSide = new VBox(15);
        rightSide.setPrefWidth(300);

        // Pricing Box
        VBox priceBox = new VBox(10);
        priceBox.setPadding(new Insets(15));
        priceBox.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);");

        Label oldPrice = new Label(model.getPricePerAdult());
        oldPrice.setStyle("-fx-strikethrough: true; -fx-text-fill: grey");

        Label discount = new Label(model.getBookAmount());
        discount.setTextFill(Color.RED);

        Label finalPrice = new Label(model.getPricePerAdult());
        finalPrice.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        finalPrice.setTextFill(Color.BLACK);

        Button payNowBtn = new Button("PROCEED TO PAYMENT");
        payNowBtn.setPrefWidth(280); // Use preferred width instead of max width
        payNowBtn.setMinHeight(50);
        payNowBtn.setPrefHeight(50);
        payNowBtn.setMaxHeight(50);
        payNowBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");

        payNowBtn.setOnAction(e -> {
            try {

                // Get current stage
                CheckOut.setModel(model);
        Stage currentStage = (Stage) payNowBtn.getScene().getWindow();
        
        // Create new instance of BookingConfirmPage
        CheckOut confirmPage = new CheckOut();
        
        // Reuse the same stage
        confirmPage.start(currentStage);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Label emi = new Label("💳 No cost EMI @ ₹3,773");
        emi.setWrapText(true);
        emi.setTextFill(Color.GRAY);

        TextField couponField = new TextField();
        couponField.setPromptText("Enter Coupon Code");
        couponField.setMaxWidth(Double.MAX_VALUE);
        couponField.setPrefHeight(80); // Increased height
        couponField.setStyle("-fx-font-size: 14px;");

        priceBox.getChildren().addAll(oldPrice, discount, finalPrice, payNowBtn, emi, couponField);

        // Offer Box (NEW)
        VBox couponSection = new VBox(10);
        couponSection.setPadding(new Insets(15));
        couponSection.setStyle("-fx-background-color: #ffffffff; -fx-background-radius: 8; -fx-border-color: #e0e0e0; -fx-border-radius: 8; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);");
        couponSection.setPrefWidth(350);

        // Title
        Label titlec = new Label("Coupons & Offers");
        titlec.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        // EMI Offer
        HBox emiBox = new HBox(10);
        emiBox.setAlignment(Pos.CENTER_LEFT);
        Label emiIcon = new Label("💳");
        emiIcon.setStyle("-fx-font-size: 18px;");
        VBox emiDetails = new VBox(2);
        Label emiTitle = new Label("No cost EMI @ ₹3,846");
        emiTitle.setStyle("-fx-font-weight: bold;");
        Hyperlink emiLink = new Hyperlink("EMI options");
        emiLink.setStyle("-fx-text-fill: #0078d4;");
        emiDetails.getChildren().addAll(emiTitle, new Label("Book your holidays with Easy "), emiLink);
        emiBox.getChildren().addAll(emiIcon, emiDetails);

        // Enter Code Section
        HBox enterCodeBox = new HBox();
        enterCodeBox.setAlignment(Pos.BASELINE_RIGHT);
        enterCodeBox.setPadding(new Insets(10, 0, 0, 0));
        Hyperlink enterCode = new Hyperlink("Enter Code");
        enterCode.setStyle("-fx-font-weight: bold; -fx-text-fill: #0078d4;");
        enterCodeBox.getChildren().addAll(new Label("Have a Coupon Code? "), enterCode);

        // Coupon 1
        VBox coupon1 = new VBox(5);
        coupon1.setPadding(new Insets(10));
        coupon1.setStyle("-fx-border-color: #1E90FF; -fx-border-radius: 8; -fx-background-radius: 8; -fx-background-color: linear-gradient(to right, #f0f8ff, #dcefff);");

        HBox c1Header = new HBox(10);
        Label c1Icon = new Label("🎁");
        Label c1Code = new Label("SUPERDEAL");
        c1Code.setStyle("-fx-font-weight: bold;");
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);
        Label c1Price = new Label("- ₹230");
        Label c1Remove = new Label("REMOVE");
        c1Remove.setStyle("-fx-text-fill: #0078d4; -fx-font-weight: bold;");
        c1Header.getChildren().addAll(c1Icon, c1Code, spacer1, c1Price, c1Remove);

        Label c1Desc = new Label("Unlock Up to 10% More Savings\npersonalized for you!");
        coupon1.getChildren().addAll(c1Header, c1Desc);

        // Coupon 2
        VBox coupon2 = new VBox(5);
        coupon2.setPadding(new Insets(10));
        coupon2.setStyle("-fx-border-color: #dcdcdc; -fx-border-radius: 8; -fx-background-radius: 8; -fx-background-color: white;");

        HBox c2Header = new HBox(10);
        Label c2Icon = new Label("🎁");
        Label c2Code = new Label("MMTHLD");
        c2Code.setStyle("-fx-font-weight: bold;");
        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, Priority.ALWAYS);
        Label c2Price = new Label("- ₹215");
        Button c2Apply = new Button("APPLY");
        c2Apply.setStyle("-fx-background-color: transparent; -fx-text-fill: #0078d4; -fx-font-weight: bold;");
        c2Header.getChildren().addAll(c2Icon, c2Code, spacer2, c2Price, c2Apply);

        Label c2Desc = new Label("Grab Your Discount Before It’s Gone!");
        coupon2.getChildren().addAll(c2Header, c2Desc);

        // "+2 More" hyperlink
        Hyperlink moreCoupons = new Hyperlink("+2 More");
        moreCoupons.setOnAction(e -> showMoreCoupons( moreCoupons));

        // Add to main layout (wherever you're building the offers section)
        VBox offersSection = new VBox(10, couponsContainer, moreCoupons);
        //offersSection.setPadding(new Insets(10));
        offersSection.setPrefWidth(400);
        couponsContainer.setPrefWidth(400);


        // Add all to section
        couponSection.getChildren().addAll(titlec, emiBox, enterCodeBox, coupon1, coupon2, offersSection);


        rightSide.getChildren().addAll(priceBox, couponSection);

        // Align left and right
       ScrollPane leftScrollPane = new ScrollPane(VBM);
        leftScrollPane.setFitToWidth(true);
        leftScrollPane.setFitToHeight(false); // Important: don't lock it to height
        VBox.setVgrow(leftScrollPane, Priority.ALWAYS);
        HBox.setHgrow(leftScrollPane, Priority.ALWAYS);
        leftScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        leftScrollPane.setStyle("-fx-background-color: transparent;");

        HBox.setHgrow(leftScrollPane, Priority.ALWAYS);

        HBD.getChildren().addAll(leftScrollPane, rightSide);


        // Add All to Card
        destinationCard.getChildren().addAll(
            backButton,
            imageGallery,
            HBD
        );

        VBox.setVgrow(destinationCard, Priority.ALWAYS);

        ScrollPane scrollPane = new ScrollPane(destinationCard);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        // to remove scrollbar
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        // to remove scrollbar
        leftScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        root.setCenter(scrollPane);

        Scene scene = new Scene(root,1300, 700);
        destinationCard.setPrefWidth(scene.getWidth()); // Initial binding

        // Dynamically adjust with scene resize:
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            destinationCard.setPrefWidth(newVal.doubleValue());
        });

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
        return scene;
    }

    private static ImageView createSmallImage(String path) {
        ImageView img = new ImageView(new Image(path));
        img.setFitWidth(190);
        img.setFitHeight(140);
        img.setPreserveRatio(false);
        img.setStyle("-fx-background-radius: 10;");
        return img;
    }

    private static HBox createDayRow(String day, String date, String icon, String desc) {
        Label dayLabel = new Label(day + "\n" + date);
        dayLabel.setPrefWidth(120);
        dayLabel.setFont(Font.font("Arial", 13));
        dayLabel.setTextFill(Color.web("#444"));

        Label iconLabel = new Label(icon);
        iconLabel.setPrefWidth(30);
        iconLabel.setAlignment(Pos.CENTER);

        Label descLabel = new Label(desc);
        descLabel.setWrapText(true);
        descLabel.setPrefWidth(500);
        descLabel.setFont(Font.font("Arial", 13));

        HBox row = new HBox(10, dayLabel, iconLabel, descLabel);
        row.setAlignment(Pos.TOP_LEFT);
        row.setPadding(new Insets(10, 15, 10, 15));
        row.setStyle("-fx-border-color: #eee; -fx-border-width: 0 0 1 0;");
        return row;
    }

    private static boolean moreCouponsAdded = false;

    private static void showMoreCoupons(Hyperlink moreCouponsLink) {
    if (!moreCouponsAdded) {
        moreCouponsAdded = true;
        moreCouponsLink.setDisable(true);
        moreCouponsLink.setText("");

        // Coupon 3
        VBox coupon3 = new VBox(5);
        coupon3.setPadding(new Insets(10));
        coupon3.setStyle("-fx-border-color: #dcdcdc; -fx-border-radius: 8; -fx-background-radius: 8; -fx-background-color: white;");

        HBox c3Header = new HBox(10);
        Label c3Icon = new Label("🎁");
        Label c3Code = new Label("XMAS100");
        c3Code.setStyle("-fx-font-weight: bold;");
        Region spacer3 = new Region();
        HBox.setHgrow(spacer3, Priority.ALWAYS);
        Label c3Price = new Label("- ₹100");
        Button c3Apply = new Button("APPLY");
        c3Apply.setStyle("-fx-background-color: transparent; -fx-text-fill: #0078d4; -fx-font-weight: bold;");
        coupon3.setPrefWidth(400);
        c3Header.getChildren().addAll(c3Icon, c3Code, spacer3, c3Price, c3Apply);

        Label c3Desc = new Label("Exclusive Xmas Offer!");
        coupon3.getChildren().addAll(c3Header, c3Desc);

        // Coupon 4
        VBox coupon4 = new VBox(5);
        coupon4.setPadding(new Insets(10));
        coupon4.setStyle("-fx-border-color: #dcdcdc; -fx-border-radius: 8; -fx-background-radius: 8; -fx-background-color: white;");

        HBox c4Header = new HBox(10);
        Label c4Icon = new Label("🎁");
        Label c4Code = new Label("FLASH50");
        c4Code.setStyle("-fx-font-weight: bold;");
        Region spacer4 = new Region();
        HBox.setHgrow(spacer4, Priority.ALWAYS);
        Label c4Price = new Label("- ₹50");
        Button c4Apply = new Button("APPLY");
        c4Apply.setStyle("-fx-background-color: transparent; -fx-text-fill: #0078d4; -fx-font-weight: bold;");
        coupon4.setPrefWidth(400);
        c4Header.getChildren().addAll(c4Icon, c4Code, spacer4, c4Price, c4Apply);

        Label c4Desc = new Label("Limited Flash Deal!");
        coupon4.getChildren().addAll(c4Header, c4Desc);
        
        couponsContainer.getChildren().addAll(coupon3, coupon4);
    }
  }

}
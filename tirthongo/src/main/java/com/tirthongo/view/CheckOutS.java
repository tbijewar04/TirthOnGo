package com.tirthongo.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class CheckOutS extends Application {
    
    // Data structures to store traveller information
    private List<TravellerDetails> travellers = new ArrayList<>();
    private ContactDetails contactDetails = new ContactDetails();
    private GSTDetails gstDetails = new GSTDetails();
    private String specialRequests = "";
    
    // UI Components for traveller details
    private RadioButton bookingForMyself, bookingForSomeoneElse;
    private TextField traveller1Name, traveller2Name;
    private TextField emailField, mobileField;
    private ComboBox<String> mobileCodeCombo;
    private TextField cityField, addressField;
    private ComboBox<String> gstStateCombo;
    private TextArea specialRequestsArea;
    private ListView<String> travellerListView;

    private double basePrice = 24228;
    private double gstfare = 1154;
    
    // Add-on charges
    private final double WHEELCHAIR_CHARGE = 4000;
    private final double PERSONAL_HELPER_CHARGE = 5000;
    
    // UI Components
    private CheckBox wheelchairCheckBox;
    private CheckBox personalHelperCheckBox;
    private Label totalPriceLabel;

    @Override
    public void start(Stage primaryStage) {

         // Root layout
        VBox mainlayout1 = new VBox();
        mainlayout1.setStyle("-fx-background-color: #cbe7ffff;");

        // Top bar with title and profile
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-text-fill: white; -fx-padding: 10 20 10 20; -fx-font-weight: bold;");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("🛐TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#ec7208ff")); // Saffron

        Region spacerTop = new Region();
        HBox.setHgrow(spacerTop, Priority.ALWAYS);

        Circle profileCircle = new Circle(15);
        Image profileImg = new Image("Assets\\Images\\6337001401326092247.jpg"); // Update path
        profileCircle.setFill(new ImagePattern(profileImg));

        MenuItem myProfile = new MenuItem("My Profile");
        MenuItem myTrips = new MenuItem("My Trips");
        MenuButton profileMenu = new MenuButton("", null, myProfile, myTrips);
        profileMenu.setGraphic(profileCircle);

        topBar.getChildren().addAll(title, spacerTop, profileMenu);

        HBox mainLayout = new HBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #f2f2f2;");

        // Left Section (Package Review)
        VBox leftSection = new VBox();
        leftSection.setSpacing(15);
        leftSection.setPrefWidth(900);

        // Title
        Label reviewTitle = new Label("Review package");
        reviewTitle.setPadding(new Insets(10));
        reviewTitle.setMaxWidth(Double.MAX_VALUE);
        reviewTitle.setStyle("-fx-background-color: #062746; -fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Package Summary Card
        VBox packageCard = new VBox();
        packageCard.setSpacing(10);
        packageCard.setPadding(new Insets(15));
        packageCard.setStyle("-fx-background-color: #e7f0f8; -fx-background-radius: 8;");

        Label nightsLabel = new Label("2 Nights In Vellore");
        nightsLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        Label dateLabel = new Label("Wed, Sep 3, 2025 - Fri, Sep 5, 2025 / From Chennai");
        dateLabel.setStyle("-fx-font-size: 14px;");
        Label roomLabel = new Label("1 Room - 2 Adults");
        roomLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        packageCard.getChildren().addAll(nightsLabel, dateLabel, roomLabel);

        // Create detailed Traveller Details content
        VBox travellerDetailsContent = createCompleteTravellerDetailsForm();
        VBox packagesaddons = createAddOnsVBox();
        VBox packItinerary = createItinerary();
        VBox cancelPolicy = createCancellationConditions();

        // Accordion Sections
        TitledPane tp1 = new TitledPane("1. Traveller Details", travellerDetailsContent);
        TitledPane tp2 = new TitledPane("2. Package Add-Ons", packagesaddons);
        TitledPane tp3 = new TitledPane("3. Package Itinerary & Inclusions", packItinerary);
        TitledPane tp4 = new TitledPane("4. Cancellation Terms & Conditions", cancelPolicy);

        Accordion accordion = new Accordion(tp1, tp2, tp3, tp4);
        accordion.setExpandedPane(tp1);

        VBox scrollContent = new VBox(reviewTitle, packageCard, accordion);
        scrollContent.setSpacing(15);
        ScrollPane leftScroll = new ScrollPane(scrollContent);
        leftScroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        leftScroll.setFitToWidth(true);
        leftScroll.setStyle("-fx-background-color:transparent;");


        // Right Section (Payment Box)
        VBox rightSection = new VBox();
        rightSection.setSpacing(15);
        rightSection.setPrefWidth(350);
        rightSection.setPadding(new Insets(20));
        rightSection.setStyle("-fx-background-color: #f9f9f9; -fx-border-color: lightgray; -fx-border-radius: 8;");

        // Price
        Label priceTitle = new Label("GRAND TOTAL - 2 Adults");
        priceTitle.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        Label price = new Label("₹24,228");
        price.setStyle("-fx-font-size: 28px; -fx-font-weight: bold;");

        // Payment options
        ToggleGroup group = new ToggleGroup();
        RadioButton payLater = new RadioButton("Book Now Pay Later");
        RadioButton payNow = new RadioButton("Pay Full Amount Now");
        payLater.setToggleGroup(group);
        payNow.setToggleGroup(group);
        payLater.setSelected(true);

        VBox paymentBreakup = new VBox(
                new Label("1. Pay to Book      ₹13,396"),
                new Label("2. Before 23 Aug 2025      ₹10,832")
        );
        paymentBreakup.setSpacing(5);
        paymentBreakup.setPadding(new Insets(10));
        paymentBreakup.setStyle("-fx-background-color: #e2f1ff; -fx-border-radius: 5;");

        VBox fareBreakup = new VBox();
        fareBreakup.setSpacing(5);
        fareBreakup.getChildren().addAll(
                createPriceSection()
        );

        Button payNowBtn = new Button("PROCEED TO PAYMENT");
        payNowBtn.setMaxWidth(300);
        payNowBtn.setPrefHeight(50); // Increased height
        payNowBtn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe); -fx-text-fill: white; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1); -fx-font-weight: bold; -fx-font-size: 15px;");

            payNowBtn.setOnAction(e -> {
        try {
            // Get current stage and scene
            Stage currentStage = (Stage) payNowBtn.getScene().getWindow();
            Scene currentScene = payNowBtn.getScene();
            
            // Calculate total amount (replace with your actual amount calculation)
            double totalAmount = 15000.0; // Replace this with your calculated amount
            
            // Create PaymentGateway instance with current stage, amount, and previous scene for back navigation
            PaymentGatewayS paymentGateway = new PaymentGatewayS(currentStage, totalAmount, currentScene);
            
            // Get the payment gateway scene
            Scene paymentScene = paymentGateway.getScene();
            
            // Set the new scene
            currentStage.setScene(paymentScene);
            currentStage.setTitle("TirthOnGo - Payment Gateway");
            
            // Optional: Center the stage on screen
            currentStage.centerOnScreen();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            // Handle error - show alert dialog
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Navigation Error");
            alert.setHeaderText("Failed to load Payment Gateway");
            alert.setContentText("An error occurred while navigating to the payment page: " + ex.getMessage());
            alert.showAndWait();
        }
    });
        // Final assemble
        rightSection.getChildren().addAll(
                priceTitle, price,
                payLater, paymentBreakup,
                payNow,
                new Separator(),
                fareBreakup,
                payNowBtn
        );

        mainLayout.getChildren().addAll(leftScroll, rightSection);
        mainlayout1.getChildren().addAll(topBar,mainLayout);

        Scene scene = new Scene(mainlayout1,1300, 700);

        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private VBox createCompleteTravellerDetailsForm() {
        VBox mainContainer = new VBox(20);
        //mainContainer.setPadding(new Insets(15));
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");
        
        // Create sections
        mainContainer.getChildren().addAll(
            createTravellerSection(),
            new Separator(),
            createContactDetailsSection(),
            new Separator(),
            createGSTDetailsSection(),
            new Separator(),
            createSpecialRequestsSection(),
            new Separator(),
            createButtonSection(),
            new Separator(),
            createDisplaySection()
        );
        
        return mainContainer;
    }
    
    private VBox createTravellerSection() {
        VBox section = new VBox(15);
        
        Label header = new Label("2 Travellers - 1 Room | 2 Adults");
        header.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        
        // Booking type radio buttons
        HBox bookingType = new HBox(20);
        ToggleGroup bookingGroup = new ToggleGroup();
        bookingForMyself = new RadioButton("I'm Booking For Myself");
        bookingForSomeoneElse = new RadioButton("I'm Booking for someone else");
        bookingForMyself.setToggleGroup(bookingGroup);
        bookingForSomeoneElse.setToggleGroup(bookingGroup);
        bookingForMyself.setSelected(true);
        bookingType.getChildren().addAll(bookingForMyself, bookingForSomeoneElse);
        
        // Traveller inputs with clickable buttons
        GridPane travellerGrid = new GridPane();
        travellerGrid.setHgap(20);
        travellerGrid.setVgap(15);
        
        // Traveller 1
        Label traveller1Label = new Label("Traveller 1");
        traveller1Label.setStyle("-fx-text-fill: #666;");
        
        Button traveller1Button = new Button("Add Traveller");
        traveller1Button.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 8px 15px; -fx-cursor: hand;");
        traveller1Button.setOnAction(e -> showTravellerDialog(1));
        
        traveller1Name = new TextField();
        traveller1Name.setPromptText("Click 'Add Traveller' to enter details");
        traveller1Name.setPrefWidth(250);
        traveller1Name.setEditable(false);
        traveller1Name.setStyle("-fx-background-color: #f8f9fa;");
        
        Label adult1Label = new Label("*ADULT - SHOULD BE ABOVE 18 YEARS");
        adult1Label.setStyle("-fx-text-fill: #999; -fx-font-size: 12px;");
        
        // Traveller 2
        Label traveller2Label = new Label("Traveller 2");
        traveller2Label.setStyle("-fx-text-fill: #666;");
        
        Button traveller2Button = new Button("Add Traveller");
        traveller2Button.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 8px 15px; -fx-cursor: hand;");
        traveller2Button.setOnAction(e -> showTravellerDialog(2));
        
        traveller2Name = new TextField();
        traveller2Name.setPromptText("Click 'Add Traveller' to enter details");
        traveller2Name.setPrefWidth(250);
        traveller2Name.setEditable(false);
        traveller2Name.setStyle("-fx-background-color: #f8f9fa;");
        
        Label adult2Label = new Label("*ADULT - SHOULD BE ABOVE 12 YEARS");
        adult2Label.setStyle("-fx-text-fill: #999; -fx-font-size: 12px;");
        
        travellerGrid.add(traveller1Label, 0, 0);
        travellerGrid.add(traveller1Button, 1, 0);
        travellerGrid.add(traveller1Name, 2, 0);
        travellerGrid.add(adult1Label, 3, 0);
        travellerGrid.add(traveller2Label, 0, 1);
        travellerGrid.add(traveller2Button, 1, 1);
        travellerGrid.add(traveller2Name, 2, 1);
        travellerGrid.add(adult2Label, 3, 1);
        
        section.getChildren().addAll(header, bookingType, travellerGrid);
        return section;
    }
    
    private VBox createContactDetailsSection() {
        VBox section = new VBox(15);
        
        Label header = new Label("Please enter contact details");
        header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        GridPane contactGrid = new GridPane();
        contactGrid.setHgap(20);
        contactGrid.setVgap(15);
        
        Label emailLabel = new Label("Email*");
        emailField = new TextField();
        emailField.setPromptText("xyz@gmail.com");
        emailField.setPrefWidth(200);
        
        Label mobileCodeLabel = new Label("Mobile Code*");
        mobileCodeCombo = new ComboBox<>();
        mobileCodeCombo.getItems().addAll("+91", "+92", "+44", "+61", "+33");
        mobileCodeCombo.setValue("+91");
        mobileCodeCombo.setPrefWidth(70);
        
        Label mobileLabel = new Label("Mobile*");
        mobileField = new TextField();
        mobileField.setPromptText("Eg. 98012 38910");
        mobileField.setPrefWidth(200);
        
        contactGrid.add(emailLabel, 0, 0);
        contactGrid.add(emailField, 1, 0);
        contactGrid.add(mobileCodeLabel, 2, 0);
        contactGrid.add(mobileCodeCombo, 3, 0);
        contactGrid.add(mobileLabel, 4, 0);
        contactGrid.add(mobileField, 5, 0);
        
        section.getChildren().addAll(header, contactGrid);
        return section;
    }
    
    private VBox createGSTDetailsSection() {
        VBox section = new VBox(15);
        
        Label header = new Label("Please enter GST details");
        header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        GridPane gstGrid = new GridPane();
        gstGrid.setHgap(20);
        gstGrid.setVgap(15);
        
        Label cityLabel = new Label("City");
        cityField = new TextField();
        cityField.setPromptText("Eg. Bangalore");
        cityField.setPrefWidth(200);
        
        Label gstStateLabel = new Label("Gst State*");
        gstStateCombo = new ComboBox<>();
        gstStateCombo.getItems().addAll(
            "Karnataka", "Maharashtra", "Tamil Nadu", "Delhi", "Gujarat", 
            "Rajasthan", "Uttar Pradesh", "West Bengal", "Andhra Pradesh", "Other"
        );
        gstStateCombo.setPrefWidth(150);
        
        Label addressLabel = new Label("Address");
        addressField = new TextField();
        addressField.setPromptText("Eg. House Number, Street Name, etc.");
        addressField.setPrefWidth(300);
        
        gstGrid.add(cityLabel, 0, 0);
        gstGrid.add(cityField, 1, 0);
        gstGrid.add(gstStateLabel, 2, 0);
        gstGrid.add(gstStateCombo, 3, 0);
        gstGrid.add(addressLabel, 0, 1);
        gstGrid.add(addressField, 1, 1, 3, 1);
        
        section.getChildren().addAll(header, gstGrid);
        return section;
    }
    
    private VBox createSpecialRequestsSection() {
        VBox section = new VBox(10);
        
        Label header = new Label("Special Requests");
        header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        specialRequestsArea = new TextArea();
        specialRequestsArea.setPromptText("Enter Special Requests");
        specialRequestsArea.setPrefRowCount(4);
        specialRequestsArea.setPrefWidth(600);
        specialRequestsArea.setMaxHeight(50);
        
        section.getChildren().addAll(header, specialRequestsArea);
        return section;
    }
    
    private HBox createButtonSection() {
        HBox buttonSection = new HBox(15);
        buttonSection.setAlignment(Pos.CENTER);
        
        Button saveButton = new Button("Save Details");
        saveButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-font-weight:bold");
        saveButton.setOnAction(e -> saveTravellerDetails());
        
        Button clearButton = new Button("Clear All");
        clearButton.setStyle("-fx-background-color: #6c757d; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-font-weight:bold");
        clearButton.setOnAction(e -> clearAllFields());
        
        Button displayButton = new Button("Display Saved Details");
        displayButton.setStyle("-fx-background-color: #28a745; -fx-text-fill: white; -fx-padding: 10px 20px; -fx-font-weight:bold");
        displayButton.setOnAction(e -> displaySavedDetails());
        
        buttonSection.getChildren().addAll(saveButton, clearButton, displayButton);
        return buttonSection;
    }
    
    private VBox createDisplaySection() {
        VBox section = new VBox(10);
        
        Label header = new Label("Saved Traveller Details");
        header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        travellerListView = new ListView<>();
        travellerListView.setPrefHeight(200);
        
        section.getChildren().addAll(header, travellerListView);
        return section;
    }
    
    private void saveTravellerDetails() {
        try {
            // Clear previous data
            travellers.clear();
            
            // Save traveller names
            if (!traveller1Name.getText().trim().isEmpty()) {
                travellers.add(new TravellerDetails("Traveller 1", traveller1Name.getText().trim(), "Adult (18+ years)"));
            }
            if (!traveller2Name.getText().trim().isEmpty()) {
                travellers.add(new TravellerDetails("Traveller 2", traveller2Name.getText().trim(), "Adult (12+ years)"));
            }
            
            // Save contact details
            contactDetails = new ContactDetails(
                emailField.getText().trim(),
                mobileCodeCombo.getValue(),
                mobileField.getText().trim()
            );
            
            // Save GST details
            gstDetails = new GSTDetails(
                cityField.getText().trim(),
                gstStateCombo.getValue(),
                addressField.getText().trim()
            );
            
            // Save special requests
            specialRequests = specialRequestsArea.getText().trim();
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Traveller details saved successfully!");
            alert.showAndWait();
            
            displaySavedDetails();
            
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Error saving details: " + e.getMessage());
            alert.showAndWait();
        }
    }
    
    private void clearAllFields() {
        traveller1Name.clear();
        traveller2Name.clear();
        emailField.clear();
        mobileField.clear();
        mobileCodeCombo.setValue("+91");
        cityField.clear();
        gstStateCombo.setValue(null);
        addressField.clear();
        specialRequestsArea.clear();
        bookingForMyself.setSelected(true);
        travellerListView.getItems().clear();
    }
    
    private void displaySavedDetails() {
        ObservableList<String> items = FXCollections.observableArrayList();
        
        items.add("=== BOOKING DETAILS ===");
        items.add("Booking Type: " + (bookingForMyself.isSelected() ? "For Myself" : "For Someone Else"));
        items.add("");
        
        items.add("=== TRAVELLERS ===");
        for (TravellerDetails traveller : travellers) {
            if (traveller.getName() != null && !traveller.getName().trim().isEmpty()) {
                items.add(traveller.getPosition() + ": " + traveller.getName() + " (" + traveller.getAgeCategory() + ")");
                items.add("  Gender: " + traveller.getGender());
                items.add("  Date of Birth: " + traveller.getDateOfBirth());
            }
        }
        items.add("");
        
        items.add("=== CONTACT DETAILS ===");
        items.add("Email: " + contactDetails.getEmail());
        items.add("Mobile: " + contactDetails.getMobileCode() + " " + contactDetails.getMobile());
        items.add("");
        
        items.add("=== GST DETAILS ===");
        items.add("City: " + gstDetails.getCity());
        items.add("State: " + gstDetails.getState());
        items.add("Address: " + gstDetails.getAddress());
        items.add("");
        
        if (!specialRequests.isEmpty()) {
            items.add("=== SPECIAL REQUESTS ===");
            items.add(specialRequests);
        }
        
        travellerListView.setItems(items);
    }
    
    private void showTravellerDialog(int travellerNumber) {
        Stage dialog = new Stage();
        dialog.setTitle("Add Traveller Details");
        dialog.setResizable(false);
        dialog.initModality(javafx.stage.Modality.APPLICATION_MODAL);
        
        VBox mainLayout = new VBox(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: white;");
        
        // Header
        HBox headerBox = new HBox();
        headerBox.setAlignment(Pos.CENTER);
        
        Label headerLabel = new Label("Traveller " + travellerNumber + "/2");
        headerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
        Button closeButton = new Button("×");
        closeButton.setStyle("-fx-background-color: transparent; -fx-font-size: 20px; -fx-text-fill: #666; -fx-cursor: hand;");
        closeButton.setOnAction(e -> dialog.close());
        
        headerBox.getChildren().addAll(headerLabel, closeButton);
        
        
        // Mandatory Information Section
        Label mandatoryLabel = new Label("Mandatory Information");
        mandatoryLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333;");
        
        Label pleaseEnterLabel = new Label("Please Enter Mandatory Information");
        pleaseEnterLabel.setStyle("-fx-text-fill: #666;");
        
        // Form fields
        GridPane formGrid = new GridPane();
        formGrid.setHgap(15);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(10, 0, 0, 0));
        
        // First Name
        Label firstNameLabel = new Label("First Name*");
        firstNameLabel.setStyle("-fx-text-fill: #333;");
        TextField firstNameField = new TextField();
        firstNameField.setPromptText("Enter a First Name");
        firstNameField.setPrefWidth(200);
        
        // Last Name
        Label lastNameLabel = new Label("Last Name*");
        lastNameLabel.setStyle("-fx-text-fill: #333;");
        TextField lastNameField = new TextField();
        lastNameField.setPromptText("Enter a Last Name");
        lastNameField.setPrefWidth(200);
        
        // Date of Birth
        Label dobLabel = new Label("Date Of Birth*");
        dobLabel.setStyle("-fx-text-fill: #333;");
        
        HBox dobBox = new HBox(5);
        ComboBox<String> dayCombo = new ComboBox<>();
        ComboBox<String> monthCombo = new ComboBox<>();
        ComboBox<String> yearCombo = new ComboBox<>();
        
        // Populate day combo
        for (int i = 1; i <= 31; i++) {
            dayCombo.getItems().add(String.format("%02d", i));
        }
        dayCombo.setPromptText("DD");
        dayCombo.setPrefWidth(70);
        
        // Populate month combo
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        monthCombo.getItems().addAll(months);
        monthCombo.setPromptText("MM");
        monthCombo.setPrefWidth(70);
        
        // Populate year combo
        for (int i = 2024; i >= 1950; i--) {
            yearCombo.getItems().add(String.valueOf(i));
        }
        yearCombo.setPromptText("YYYY");
        yearCombo.setPrefWidth(80);
        
        dobBox.getChildren().addAll(dayCombo, monthCombo, yearCombo);
        
        // Gender
        Label genderLabel = new Label("Gender*");
        genderLabel.setStyle("-fx-text-fill: #333;");
        ComboBox<String> genderCombo = new ComboBox<>();
        genderCombo.getItems().addAll("Male", "Female", "Other");
        genderCombo.setPromptText("Select Gender");
        genderCombo.setPrefWidth(200);
        
        // Add to grid
        formGrid.add(firstNameLabel, 0, 0);
        formGrid.add(firstNameField, 1, 0);
        formGrid.add(lastNameLabel, 2, 0);
        formGrid.add(lastNameField, 3, 0);
        formGrid.add(dobLabel, 0, 1);
        formGrid.add(dobBox, 1, 1, 3, 1);
        formGrid.add(genderLabel, 0, 2);
        formGrid.add(genderCombo, 1, 2);
        
        // Add Traveller Button
        Button addTravellerButton = new Button("ADD TRAVELLER");
        addTravellerButton.setStyle("-fx-background-color: #007bff; -fx-text-fill: white; -fx-padding: 12px 30px; -fx-font-weight: bold; -fx-cursor: hand;");
        addTravellerButton.setPrefWidth(200);
        
        addTravellerButton.setOnAction(e -> {
            // Validate fields
            if (firstNameField.getText().trim().isEmpty() || 
                lastNameField.getText().trim().isEmpty() ||
                dayCombo.getValue() == null || 
                monthCombo.getValue() == null || 
                yearCombo.getValue() == null ||
                genderCombo.getValue() == null) {
                
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Validation Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all mandatory fields!");
                alert.showAndWait();
                return;
            }
            
            // Create traveller details
            String fullName = firstNameField.getText().trim() + " " + lastNameField.getText().trim();
            String dateOfBirth = dayCombo.getValue() + "/" + monthCombo.getValue() + "/" + yearCombo.getValue();
            String gender = genderCombo.getValue();
            String ageCategory = travellerNumber == 1 ? "Adult (18+ years)" : "Adult (12+ years)";
            
            TravellerDetails traveller = new TravellerDetails(
                "Traveller " + travellerNumber, 
                fullName, 
                ageCategory, 
                gender, 
                dateOfBirth
            );
            
            // Update the appropriate traveller in the list
            if (travellerNumber == 1) {
                traveller1Name.setText(fullName);
                // Remove existing traveller 1 if present
                travellers.removeIf(t -> t.getPosition().equals("Traveller 1"));
                travellers.add(0, traveller);
            } else {
                traveller2Name.setText(fullName);
                // Remove existing traveller 2 if present
                travellers.removeIf(t -> t.getPosition().equals("Traveller 2"));
                // Ensure we have at least one element for traveller 2
                while (travellers.size() < 2) {
                    travellers.add(new TravellerDetails("", "", "", "", ""));
                }
                if (travellers.size() == 1) {
                    travellers.add(traveller);
                } else {
                    travellers.set(1, traveller);
                }
            }
            
            dialog.close();
            
            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Success");
            successAlert.setHeaderText(null);
            successAlert.setContentText("Traveller " + travellerNumber + " details added successfully!");
            successAlert.showAndWait();
        });
        
        VBox buttonBox = new VBox();
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().add(addTravellerButton);
        
        mainLayout.getChildren().addAll(
            headerBox,
            new Separator(),
            mandatoryLabel,
            pleaseEnterLabel,
            formGrid,
            buttonBox
        );
        
        Scene scene = new Scene(mainLayout, 600, 400);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
    
    // Data classes
    public static class TravellerDetails {
        private String position;
        private String name;
        private String ageCategory;
        private String gender;
        private String dateOfBirth;
        
        public TravellerDetails(String position, String name, String ageCategory) {
            this.position = position;
            this.name = name;
            this.ageCategory = ageCategory;
            this.gender = "";
            this.dateOfBirth = "";
        }
        
        public TravellerDetails(String position, String name, String ageCategory, String gender, String dateOfBirth) {
            this.position = position;
            this.name = name;
            this.ageCategory = ageCategory;
            this.gender = gender;
            this.dateOfBirth = dateOfBirth;
        }
        
        // Getters
        public String getPosition() { return position; }
        public String getName() { return name; }
        public String getAgeCategory() { return ageCategory; }
        public String getGender() { return gender != null ? gender : ""; }
        public String getDateOfBirth() { return dateOfBirth != null ? dateOfBirth : ""; }
    }
    
    public static class ContactDetails {
        private String email;
        private String mobileCode;
        private String mobile;
        
        public ContactDetails() {}
        
        public ContactDetails(String email, String mobileCode, String mobile) {
            this.email = email;
            this.mobileCode = mobileCode;
            this.mobile = mobile;
        }
        
        // Getters
        public String getEmail() { return email != null ? email : ""; }
        public String getMobileCode() { return mobileCode != null ? mobileCode : ""; }
        public String getMobile() { return mobile != null ? mobile : ""; }
    }
    
    public static class GSTDetails {
        private String city;
        private String state;
        private String address;
        
        public GSTDetails() {}
        
        public GSTDetails(String city, String state, String address) {
            this.city = city;
            this.state = state;
            this.address = address;
        }
        
        // Getters
        public String getCity() { return city != null ? city : ""; }
        public String getState() { return state != null ? state : ""; }
        public String getAddress() { return address != null ? address : ""; }
    }

    
    private VBox createAddOnsVBox() {
        VBox addOnsVBox = new VBox(15);
        addOnsVBox.setPadding(new Insets(15));
        
        // Header for add-ons section
        Label headerLabel = new Label("Select Additional Services:");
        headerLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");
        
        // Wheelchair option
        HBox wheelchairBox = createWheelchairOption();
        
        // Personal helper option
        HBox personalHelperBox = createPersonalHelperOption();
        
        // Separator
        Separator separator = new Separator();
        
        // Info label
        Label infoLabel = new Label("Additional charges will be added to your total.");
        infoLabel.setStyle("-fx-font-size: 11px; -fx-text-fill: #666666; -fx-font-style: italic;");
        
        addOnsVBox.getChildren().addAll(
            headerLabel,
            wheelchairBox,
            personalHelperBox,
            separator,
            infoLabel
        );
        
        return addOnsVBox;
    }
    
    private HBox createWheelchairOption() {
        HBox wheelchairBox = new HBox(10);
        wheelchairBox.setAlignment(Pos.CENTER_LEFT);
        
        wheelchairCheckBox = new CheckBox();
        wheelchairCheckBox.setOnAction(e -> updateTotalPrice());
        
        VBox wheelchairInfo = new VBox(2);
        Label wheelchairLabel = new Label("Need Wheelchair");
        wheelchairLabel.setStyle("-fx-font-weight: bold;");
        Label wheelchairPrice = new Label(String.format("+ %.2f", WHEELCHAIR_CHARGE));
        wheelchairPrice.setStyle("-fx-text-fill: #d32f2f; -fx-font-size: 11px;");
        
        wheelchairInfo.getChildren().addAll(wheelchairLabel, wheelchairPrice);
        wheelchairBox.getChildren().addAll(wheelchairCheckBox, wheelchairInfo);
        
        return wheelchairBox;
    }
    
    private HBox createPersonalHelperOption() {
        HBox personalHelperBox = new HBox(10);
        personalHelperBox.setAlignment(Pos.CENTER_LEFT);
        
        personalHelperCheckBox = new CheckBox();
        personalHelperCheckBox.setOnAction(e -> updateTotalPrice());
        
        VBox helperInfo = new VBox(2);
        Label helperLabel = new Label("Need Personal Helper");
        helperLabel.setStyle("-fx-font-weight: bold;");
        Label helperPrice = new Label(String.format("+ %.2f", PERSONAL_HELPER_CHARGE));
        helperPrice.setStyle("-fx-text-fill: #d32f2f; -fx-font-size: 11px;");
        
        helperInfo.getChildren().addAll(helperLabel, helperPrice);
        personalHelperBox.getChildren().addAll(personalHelperCheckBox, helperInfo);
        
        return personalHelperBox;
    }
    
    private VBox createPriceSection() {
        VBox priceSection = new VBox(10);
        priceSection.setPadding(new Insets(15));
        priceSection.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 5px;");
        
        Label priceTitleLabel = new Label("Price Summary:");
        priceTitleLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        totalPriceLabel = new Label();
        totalPriceLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #1976d2;");
        
        priceSection.getChildren().addAll(priceTitleLabel, totalPriceLabel);
        
        return priceSection;
    }
    
    private void updateTotalPrice() {
        double total = basePrice;
        double gst = gstfare;
        
        if (wheelchairCheckBox != null && wheelchairCheckBox.isSelected()) {
            total = total + WHEELCHAIR_CHARGE + gst;
        }
        
        if (personalHelperCheckBox != null && personalHelperCheckBox.isSelected()) {
            total = total + PERSONAL_HELPER_CHARGE + gst;
        }
        
        if (totalPriceLabel != null) {
            totalPriceLabel.setText(String.format("Total: %.2f", total));
        }
    }

    private VBox createItinerary() {

        VBox leftSection = new VBox(20);
        leftSection.setPrefWidth(850);

        Label fromLabel = new Label("⚫ Chennai to Vellore");
        fromLabel.setFont(Font.font("Arial", 14));
        fromLabel.setTextFill(Color.web("#555"));

        VBox itineraryCard = new VBox(0);
        itineraryCard.setStyle("-fx-background-color: white; -fx-background-radius: 10; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.05), 8, 0, 0, 2);");
        itineraryCard.setMaxWidth(800);

        // Header
        Label velloreLabel = new Label("Vellore - 2 Nights Stay");
        velloreLabel.setStyle("-fx-background-color: #f3d9c4; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 10 15;");

        VBox daysBox = new VBox();
        daysBox.setPadding(new Insets(10));

        // DAY 1
        daysBox.getChildren().add(createDayRow("Day 1", "Sep 3, Wed", "🏨", "Check in to Rangalaya royal, 3 Star"));

        // DAY 2
        daysBox.getChildren().add(createDayRow("Day 2", "Sep 4, Thu", "🚗", "Private AC Sedan - AC for sightseeing in & around Vellore"));
        daysBox.getChildren().add(createDayRow("", "", "🌄", "Sightseeing In Vellore"));
        daysBox.getChildren().add(createDayRow("", "", "🍝", "Day Meals: Breakfast : Included at Rangalaya royal, Vellore"));

        // DAY 3
        daysBox.getChildren().add(createDayRow("Day 3", "Sep 5, Fri", "🍝", "Day Meals: Breakfast : Included at Rangalaya royal, Vellore"));
        daysBox.getChildren().add(createDayRow("", "", "🏨", "Checkout from Hotel in Vellore"));

        itineraryCard.getChildren().addAll(velloreLabel, daysBox);

        Label toLabel = new Label("⚫ Vellore to Chennai");
        toLabel.setFont(Font.font("Arial", 14));
        toLabel.setTextFill(Color.web("#555"));

        leftSection.getChildren().addAll(fromLabel, itineraryCard, toLabel);
        return leftSection;

    }

    private HBox createDayRow(String day, String date, String icon, String desc) {
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

    private VBox createCancellationConditions() {
    VBox cancellationVBox = new VBox(15);
    cancellationVBox.setPadding(new Insets(20));
    cancellationVBox.setStyle("-fx-background-color: #fafafa; -fx-background-radius: 8; -fx-border-color: #e0e0e0; -fx-border-radius: 8; -fx-border-width: 1;");
    
    // Header
    Label headerLabel = new Label("🚫 Cancellation Policy & Conditions");
    headerLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #d32f2f;");
    
    // Separator
    Separator separator = new Separator();
    separator.setStyle("-fx-background-color: #e0e0e0;");
    
    // Cancellation conditions
    VBox conditionsBox = new VBox(12);
    
    // Free cancellation period
    conditionsBox.getChildren().add(createConditionItem(
        "✅ Free Cancellation",
        "Cancel your booking without any charges if done before the specified time period prior to travel."
    ));
    
    // Partial refund conditions
    conditionsBox.getChildren().add(createConditionItem(
        "⚠️ Partial Refund",
        "Cancellations made within the moderate notice period will incur cancellation fees as per the booking terms."
    ));
    
    // No refund policy
    conditionsBox.getChildren().add(createConditionItem(
        "❌ No Refund",
        "Last-minute cancellations or no-shows are non-refundable. Processing fees and third-party charges are non-refundable."
    ));
    
    // Modification policy
    conditionsBox.getChildren().add(createConditionItem(
        "📝 Modifications",
        "Date changes and itinerary modifications are subject to availability and may incur additional charges."
    ));
    
    // Force majeure
    conditionsBox.getChildren().add(createConditionItem(
        "🌪️ Force Majeure",
        "Cancellations due to natural disasters, government restrictions, or unforeseen circumstances will be handled case by case."
    ));
    
    // Payment conditions
    conditionsBox.getChildren().add(createConditionItem(
        "💳 Refund Processing",
        "Approved refunds will be processed back to the original payment method within 7-10 business days."
    ));
    
    // Contact information
    Label contactLabel = new Label("📞 For cancellation requests, please contact our customer service team immediately.");
    contactLabel.setStyle("-fx-font-size: 12px; -fx-font-style: italic; -fx-text-fill: #555555;");
    contactLabel.setWrapText(true);
    
    // Add all components to main VBox
    cancellationVBox.getChildren().addAll(
        headerLabel,
        separator,
        conditionsBox,
        contactLabel
    );
    
    return cancellationVBox;
}

    private VBox createConditionItem(String title, String description) {
    VBox conditionBox = new VBox(5);
    
    Label titleLabel = new Label(title);
    titleLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #333333;");
    
    Label descLabel = new Label(description);
    descLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666; -fx-padding: 0 0 0 20;");
    descLabel.setWrapText(true);
    descLabel.setMaxWidth(750);
    
    conditionBox.getChildren().addAll(titleLabel, descLabel);
    
    return conditionBox;

   } 
}

package com.tirthongo.view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class AboutUs{

 
    public Scene getAboutUsScene (Stage primaryStage) throws Exception {
        
        // Main content container
        VBox mainContent = new VBox();
        mainContent.setStyle("-fx-background-color: linear-gradient(to bottom, #f8f9fa, #e9ecef);");
        
        // Hero Section
        VBox heroSection = createHeroSection();

        Button backButton = new Button("← Back");
        backButton.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        backButton.setTextFill(Color.WHITE);
        backButton.setStyle("-fx-background-color: #ec7208; -fx-background-radius: 5;");
        backButton.setPadding(new Insets(5, 15, 5, 15));
        backButton.setOnAction(e -> primaryStage.setScene(new Homeload().gethomescene(primaryStage)));
        
        // About Us Section
        VBox aboutSection = createAboutSection();
        
        // Mentors & Guidance Section
        VBox mentorsSection = createMentorsSection();
        
        // Team Section
        VBox teamSection = createTeamSection();
        
        // Footer Section
        VBox footerSection = createFooterSection();
        
        mainContent.getChildren().addAll(backButton, heroSection, aboutSection, mentorsSection, teamSection, footerSection);
        
        // ScrollPane for content
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background-color: transparent;");
        
        // Root container
        VBox root = new VBox();
        root.getChildren().addAll(scrollPane);
        
        Scene scene = new Scene(root, 1300, 700);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        return scene;
    }

    
    private VBox createHeroSection() {
        VBox heroSection = new VBox(20);
        heroSection.setAlignment(Pos.CENTER);
        heroSection.setPadding(new Insets(50, 40, 50, 40));
        heroSection.setStyle("-fx-background-color: linear-gradient(to top, #ffffffff, #ffc370ff);" +
                           "-fx-background-radius: 0;");
        
        Label heroTitle = new Label("About ॐ TirthOnGo");
        heroTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 42));
        heroTitle.setTextFill(Color.web("#ec7208ff"));
        heroTitle.setTextAlignment(TextAlignment.CENTER);

        Label heroSubtitle = new Label("Your Spiritual Travel Companion");
        heroSubtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 20));
        heroSubtitle.setTextFill(Color.web("#ffaa00ff"));
        heroSubtitle.setTextAlignment(TextAlignment.CENTER);
        
        // Decorative element
        Label decorativeElement = new Label("✦ ◆ ✦");
        decorativeElement.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        decorativeElement.setTextFill(Color.web("#ff861cff"));
        
        heroSection.getChildren().addAll(heroTitle, heroSubtitle, decorativeElement);
        
        return heroSection;
    }
    
    private VBox createAboutSection() {
        VBox aboutSection = new VBox(30);
        aboutSection.setPadding(new Insets(30, 60, 30, 60));
        aboutSection.setAlignment(Pos.CENTER);
        aboutSection.setStyle("-fx-background-color: white;");
        
        // Section header
        VBox headerBox = new VBox(10);
        headerBox.setAlignment(Pos.CENTER);
        
        Label sectionTitle = new Label("Our Mission & Vision");
        sectionTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 32));
        sectionTitle.setTextFill(Color.web("#000000ff"));
        
        Label sectionSubtitle = new Label("Connecting Hearts to Sacred Destinations");
        sectionSubtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        sectionSubtitle.setTextFill(Color.web("#ec7208ff"));
        sectionSubtitle.setStyle("-fx-font-style: italic;");
        
        headerBox.getChildren().addAll(sectionTitle, sectionSubtitle);
        
        // Content area
        HBox contentArea = new HBox(50);
        contentArea.setAlignment(Pos.CENTER);
        contentArea.setMaxWidth(1200);
        
        // Left side - main description
        VBox leftContent = new VBox(20);
        leftContent.setPrefWidth(600);
        leftContent.setAlignment(Pos.TOP_LEFT);
        
        Label mainDesc = new Label(
            "TirthOnGo is a comprehensive travel and pilgrimage management platform built for convenient and spiritual tourism. " +
            "It connects admin users, travel agents, and pilgrims by managing tours, user profiles, bookings, and destination data seamlessly."
        );
        mainDesc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        mainDesc.setTextFill(Color.web("#495057"));
        mainDesc.setWrapText(true);
        mainDesc.setLineSpacing(5);
        
        Label additionalDesc = new Label(
            "With rich visual interfaces, real-time updates, and Firebase backend integration, it offers a modern and accessible experience for users across India. " +
            "We provide the best we can by making your trip easier and more convenient."
        );
        additionalDesc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        additionalDesc.setTextFill(Color.web("#495057"));
        additionalDesc.setWrapText(true);
        additionalDesc.setLineSpacing(5);
        
        leftContent.getChildren().addAll(mainDesc, additionalDesc);
        
        // Right side - key features
        // Right side - image content only (no features)
        VBox rightContent = new VBox();
        rightContent.setPrefWidth(400);
        rightContent.setAlignment(Pos.CENTER);
        rightContent.setPadding(new Insets(10));
        rightContent.setStyle("-fx-background-color: #ffffffff;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-radius: 10;");

        // Load and add image
        Image image = new Image("Assets\\Images\\map.png", 200, 200, true, true); // Adjust size/path
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);

        rightContent.getChildren().add(imageView);
        
        contentArea.getChildren().addAll(leftContent, rightContent);
        
        aboutSection.getChildren().addAll(headerBox, contentArea);
        
        return aboutSection;
    }
    
    private VBox createMentorsSection() {
        VBox mentorsSection = new VBox(40);
        mentorsSection.setPadding(new Insets(50, 60, 50, 60));
        mentorsSection.setAlignment(Pos.CENTER);
        mentorsSection.setStyle("-fx-background-color: linear-gradient(to right, #f8f9fa, #ffffff);");
        
        Label sectionSubtitle = new Label("The pillars of wisdom who shaped our journey");
        sectionSubtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        sectionSubtitle.setTextFill(Color.web("#6c757d"));
        sectionSubtitle.setStyle("-fx-font-style: italic;");
        sectionSubtitle.setTextAlignment(TextAlignment.CENTER);
        
        // Mentors content
        HBox mentorsContent = new HBox(60);
        mentorsContent.setAlignment(Pos.CENTER);
        mentorsContent.setMaxWidth(1200);
        
        // Teacher's image section with professional styling
        VBox imageSection = new VBox(20);
        imageSection.setAlignment(Pos.CENTER);
        imageSection.setPrefWidth(300);
        
        // Enhanced image container
        StackPane imageContainer = new StackPane();
        imageContainer.setPrefSize(300, 300);
        imageContainer.setStyle("-fx-background-color: white;" +
                              "-fx-background-radius: 50;" +
                              "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 15, 0, 0, 5);");
        
        try {
            Image teacherImage = new Image("Assets\\Images\\sir2.jpg");
            ImageView imageView = new ImageView(teacherImage);
            imageView.setFitWidth(300);
            imageView.setFitHeight(290);
            imageView.setPreserveRatio(true);
            
            
            imageContainer.getChildren().add(imageView);
        } catch (Exception e) {
            // Fallback if image not found
            Label placeholderImage = new Label("👨‍🏫");
            placeholderImage.setFont(Font.font(80));
            imageContainer.getChildren().add(placeholderImage);
        }
        
        Label imageCaption = new Label("Shashi Bagal Sir");
        imageCaption.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        imageCaption.setTextFill(Color.web("#ec7208ff"));
        imageCaption.setTextAlignment(TextAlignment.CENTER);
        
        imageSection.getChildren().addAll(imageContainer, imageCaption);
        
        // Acknowledgment text section
        VBox textSection = new VBox(25);
        textSection.setPrefWidth(700);
        textSection.setAlignment(Pos.TOP_LEFT);
        
        Label acknowledgeTitle = new Label("Gratitude & Acknowledgment");
        acknowledgeTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        acknowledgeTitle.setTextFill(Color.web("#2c3e50"));
        
        TextFlow acknowledgmentText = new TextFlow();
        acknowledgmentText.setPrefWidth(680);
        acknowledgmentText.setLineSpacing(8);
        
        Text intro = new Text("We extend our heartfelt gratitude to the exceptional mentors who guided us throughout this journey:\n\n");
        intro.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        intro.setFill(Color.web("#495057"));
        
        Text shashiSir = new Text("★ Shashi Sir");
        shashiSir.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        shashiSir.setFill(Color.web("#ec7208ff"));
        
        Text shashiDesc = new Text(" - For creating the beautiful concept called SuperX and inspiring us to think beyond boundaries.\n\n");
        shashiDesc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        shashiDesc.setFill(Color.web("#495057"));
        
        Text sachinSir = new Text("✦ Sachin Sir");
        sachinSir.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        sachinSir.setFill(Color.web("#ec7208ff"));
        
        Text sachinDesc = new Text(" - For his invaluable guidance in creating the foundation and architectural framework for our project.\n\n");
        sachinDesc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        sachinDesc.setFill(Color.web("#495057"));
        
        Text pramondSir = new Text("✦ Pramond Sir");
        pramondSir.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        pramondSir.setFill(Color.web("#ec7208ff"));
        
        Text pramondDesc = new Text(" - For backend development expertise and structured lectures that shaped our technical understanding.\n\n");
        pramondDesc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        pramondDesc.setFill(Color.web("#495057"));
        
        Text atharavMentor = new Text("🎯 Atharav Survase");
        atharavMentor.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        atharavMentor.setFill(Color.web("#ec7208ff"));
        
        Text atharavDesc = new Text(" - For being there from the start, providing continuous support, and helping us refine this project to perfection.");
        atharavDesc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        atharavDesc.setFill(Color.web("#495057"));
        
        acknowledgmentText.getChildren().addAll(
            intro, shashiSir, shashiDesc, sachinSir, sachinDesc, 
            pramondSir, pramondDesc, atharavMentor, atharavDesc
        );
        
        textSection.getChildren().addAll(acknowledgeTitle,sectionSubtitle, acknowledgmentText);
        
        mentorsContent.getChildren().addAll(imageSection, textSection);
        
        mentorsSection.getChildren().addAll( mentorsContent);
        
        return mentorsSection;
    }
    
    private VBox createTeamSection() {
        VBox teamSection = new VBox(40);
        teamSection.setPadding(new Insets(60, 60, 60, 60));
        teamSection.setAlignment(Pos.CENTER);
        teamSection.setStyle("-fx-background-color: white;");
        
        // Section header
        Label sectionTitle = new Label("Meet Our Team");
        sectionTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 32));
        sectionTitle.setTextFill(Color.web("#2c3e50"));
        
        Label sectionSubtitle = new Label("Passionate engineering students creating impactful technology");
        sectionSubtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        sectionSubtitle.setTextFill(Color.web("#6c757d"));
        sectionSubtitle.setStyle("-fx-font-style: italic;");
        
        // Team description
        VBox teamDescription = new VBox(20);
        teamDescription.setAlignment(Pos.CENTER);
        teamDescription.setMaxWidth(800);

        Label teamDesc = new Label(
            "We are a passionate group of engineering students dedicated to creating user-friendly, impactful technology. " +
            "Our team brings together diverse skills in JavaFX development, Firebase integration, UI/UX design, and backend systems " +
            "to deliver a well-planned, quality product."
        );
        teamDesc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        teamDesc.setTextFill(Color.web("#495057"));
        teamDesc.setWrapText(true);
        teamDesc.setTextAlignment(TextAlignment.CENTER);
        teamDesc.setLineSpacing(5);
        
        // Team members grid
        HBox teamGrid = new HBox(30);
        teamGrid.setAlignment(Pos.CENTER);
        
        String[] members = {"Pooja Kulkarni", "Gauri Kulkarni", "Tejas Bijewar", "Aditya Gaikwad"};
        
        for (int i = 0; i < members.length; i++) {
            VBox memberCard = createTeamMemberCard(members[i]);
            teamGrid.getChildren().add(memberCard);
        }
        
        teamDescription.getChildren().addAll(teamDesc, teamGrid);
        
        teamSection.getChildren().addAll(sectionTitle, sectionSubtitle, teamDescription);
        
        return teamSection;
    }
    
    private VBox createTeamMemberCard(String name){
        VBox memberCard = new VBox(15);
        memberCard.setAlignment(Pos.CENTER);
        memberCard.setPrefWidth(200);
        memberCard.setPadding(new Insets(25, 20, 25, 20));
        memberCard.setStyle("-fx-background-color: #f8f9fa;" +
                           "-fx-background-radius: 15;" +
                           "-fx-border-color: #e9ecef;" +
                           "-fx-border-width: 1;" +
                           "-fx-border-radius: 15;" +
                           "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);");

        
        // Name
        Label nameLabel = new Label(name);
        nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        nameLabel.setTextFill(Color.web("#2c3e50"));
        nameLabel.setTextAlignment(TextAlignment.CENTER);
        nameLabel.setWrapText(true);
        
        memberCard.getChildren().addAll( nameLabel );
        
        // Hover effect
        memberCard.setOnMouseEntered(e -> memberCard.setStyle(
            "-fx-background-color: #ffffff;" +
            "-fx-background-radius: 15;" +
            "-fx-border-color: #b36800ff;" +
            "-fx-border-width: 2;" +
            "-fx-border-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(255, 170, 50, 0.98), 15, 0, 0, 5);"
        ));
        
        memberCard.setOnMouseExited(e -> memberCard.setStyle(
            "-fx-background-color: #f8f9fa;" +
            "-fx-background-radius: 15;" +
            "-fx-border-color: #e9ecef;" +
            "-fx-border-width: 1;" +
            "-fx-border-radius: 15;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 10, 0, 0, 3);"
        ));
        
        return memberCard;
    }
    
    private VBox createFooterSection() {
        VBox footerSection = new VBox(20);
        footerSection.setPadding(new Insets(60, 40, 60, 40));
        footerSection.setAlignment(Pos.CENTER);
        footerSection.setStyle("-fx-background-color: linear-gradient(to top, #ffffffff, #ffc370ff);" +
                           "-fx-background-radius: 30;");
        
        Label footerTitle = new Label("Building a Better Tomorrow");
        footerTitle.setFont(Font.font("Georgia", FontWeight.BOLD, 24));
        footerTitle.setTextFill(Color.BLACK);
        
        Label footerDesc = new Label(
            "India is known for its holy and divine places, and we take this opportunity to contribute to our country. " +
            "TirthOnGo is built on the idea of creating a better place for spiritual travelers and connecting hearts to sacred destinations."
        );
        footerDesc.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        footerDesc.setTextFill(Color.web("#000000ff"));
        footerDesc.setWrapText(true);
        footerDesc.setTextAlignment(TextAlignment.CENTER);
        footerDesc.setMaxWidth(800);
        footerDesc.setLineSpacing(5);

        Label copyright = new Label("© 2025 TirthOnGo. Made with by our engineering team.");
        copyright.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
        copyright.setTextFill(Color.web("#ec7208ff"));
        footerSection.getChildren().addAll(footerTitle, footerDesc, copyright);
        return footerSection;

    }
}
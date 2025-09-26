package com.tirthongo.view;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class PrivacyPolicyPage {
    
    public Scene getPrivacyPolicy(Stage primaryStage) {
        // Main container
        VBox mainContainer = new VBox();
        mainContainer.setPrefSize(1200, 700);
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");
        
        // Top navigation bar
        HBox topBar = createTopNavigationBar();

        
        Button backButton = new Button("← Back");
        backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-color: #34495e; -fx-border-width: 0 0 0 0; -fx-cursor: hand;");
        backButton.setOnMouseEntered(e -> backButton.setStyle("-fx-background-color: rgba(255, 255, 255, 0.2); -fx-text-fill: #ec7208ff; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-color: #ec7208ff; -fx-border-width: 0 0 0 0; -fx-cursor: hand;"));
        backButton.setOnMouseExited(e -> backButton.setStyle("-fx-background-color: transparent; -fx-text-fill: #34495e; -fx-font-size: 16px; -fx-font-weight: bold; -fx-border-color: #34495e; -fx-border-width: 0 0 0 0; -fx-cursor: hand;"));
        backButton.setOnAction(e -> primaryStage.setScene(new Homeload().gethomescene(primaryStage)));
       
        VBox VB = new VBox(10);
        VB.getChildren().addAll(topBar,backButton);
        
        // Content container with scroll
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: transparent;");
        
        VBox contentContainer = createPrivacyPolicyContent();
        scrollPane.setContent(contentContainer);
        
        mainContainer.getChildren().addAll(VB, scrollPane);
        
        Scene scene = new Scene(mainContainer, 1300, 700);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        return scene;
    }
    
    private HBox createTopNavigationBar() {
        HBox topBar = new HBox();
        topBar.setPadding(new Insets(15));
        topBar.setStyle("-fx-background-color: linear-gradient(to right, #ffffffff, #f9970dff); -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 8, 0, 0, 3);");
        topBar.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("ॐ TirthOnGo");
        title.setFont(Font.font("Georgia", FontWeight.BOLD, 22));
        title.setTextFill(Color.web("#ec7208ff")); 

        Region spacerForTitle = new Region();
        HBox.setHgrow(spacerForTitle, Priority.ALWAYS);

        topBar.getChildren().addAll(title, spacerForTitle);

        return topBar;
    }
    
        private VBox createPrivacyPolicyContent() {
        VBox contentContainer = new VBox(20);
        contentContainer.setPadding(new Insets(40, 80, 40, 80));
        contentContainer.setStyle("-fx-background-color: white;");
        
        // Header section
        VBox headerSection = createHeaderSection();
        
        // Privacy policy sections
        VBox policyContent = new VBox(25);
        
        policyContent.getChildren().addAll(
            createPolicySection("1. Information We Collect", getInformationWeCollectContent()),
            createPolicySection("2. How We Use Your Information", getHowWeUseInfoContent()),
            createPolicySection("3. Information Sharing and Disclosure", getInfoSharingContent()),
            createPolicySection("4. Data Security", getDataSecurityContent()),
            createPolicySection("5. Cookies and Tracking Technologies", getCookiesContent()),
            createPolicySection("6. Your Rights and Choices", getYourRightsContent()),
            createPolicySection("7. Data Retention", getDataRetentionContent()),
            createPolicySection("8. International Data Transfers", getInternationalTransferContent()),
            createPolicySection("9. Children's Privacy", getChildrenPrivacyContent()),
            createPolicySection("10. Changes to This Privacy Policy", getChangesToPolicyContent()),
            createPolicySection("11. Contact Us", getContactUsContent())
        );
        
        contentContainer.getChildren().addAll(headerSection, policyContent);
        
        return contentContainer;
    }
    
    private VBox createHeaderSection() {
        VBox headerSection = new VBox(15);
        headerSection.setAlignment(Pos.CENTER);
        headerSection.setPadding(new Insets(0, 0, 30, 0));
        
        Label titleLabel = new Label("Privacy Policy");
        titleLabel.setFont(Font.font("Georgia", FontWeight.BOLD, 36));
        titleLabel.setTextFill(Color.web("#ec7208ff"));
        
        Label subtitleLabel = new Label("TirthOnGo Tours & Travel Services");
        subtitleLabel.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        subtitleLabel.setTextFill(Color.web("#666666"));
        
        Label lastUpdated = new Label("Last Updated: January 2025");
        lastUpdated.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        lastUpdated.setTextFill(Color.web("#888888"));
        lastUpdated.setStyle("-fx-font-style: italic;");
        
        // Intro paragraph
        TextFlow introText = createTextFlow("At TirthOnGo, we are committed to protecting your privacy and ensuring the security of your personal information. This Privacy Policy explains how we collect, use, disclose, and safeguard your information when you visit our website, use our mobile application, or engage with our travel services.");
        
        headerSection.getChildren().addAll(titleLabel, subtitleLabel, lastUpdated, introText);
        
        return headerSection;
    }
    
    private VBox createPolicySection(String title, String content) {
        VBox section = new VBox(10);
        
        Label sectionTitle = new Label(title);
        sectionTitle.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        sectionTitle.setTextFill(Color.web("#2F2F2F"));
        
        TextFlow contentText = createTextFlow(content);
        
        section.getChildren().addAll(sectionTitle, contentText);
        
        return section;
    }
    
    private TextFlow createTextFlow(String content) {
        TextFlow textFlow = new TextFlow();
        Text text = new Text(content);
        text.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        text.setFill(Color.web("#555555"));
        textFlow.getChildren().add(text);
        textFlow.setLineSpacing(5);
        return textFlow;
    }
    
    // Content methods for each section
    private String getInformationWeCollectContent() {
        return "We collect information you provide directly to us, such as when you:\n" +
               "• Create an account or profile\n" +
               "• Make a booking or reservation\n" +
               "• Contact us for customer support\n" +
               "• Subscribe to our newsletter\n" +
               "• Participate in surveys or promotions\n\n" +
               "This may include your name, email address, phone number, postal address, payment information, travel preferences, and any other information you choose to provide.\n\n" +
               "We also automatically collect certain information when you use our services, including device information, usage data, location information (with your consent), and cookies and similar technologies.";
    }
    
    private String getHowWeUseInfoContent() {
        return "We use the information we collect to:\n" +
               "• Provide, maintain, and improve our travel services\n" +
               "• Process transactions and send related information\n" +
               "• Send you technical notices, updates, security alerts, and support messages\n" +
               "• Respond to your comments, questions, and customer service requests\n" +
               "• Communicate with you about products, services, offers, and events\n" +
               "• Monitor and analyze trends, usage, and activities\n" +
               "• Personalize and improve your experience\n" +
               "• Facilitate contests, sweepstakes, and promotions\n" +
               "• Comply with legal obligations and protect our rights";
    }
    
    private String getInfoSharingContent() {
        return "We may share your information in the following circumstances:\n" +
               "• With service providers who perform services on our behalf\n" +
               "• With travel suppliers (hotels, airlines, tour operators) to fulfill your bookings\n" +
               "• When required by law or to respond to legal process\n" +
               "• To protect the rights, property, or safety of TirthOnGo, our users, or others\n" +
               "• In connection with a merger, acquisition, or sale of assets\n" +
               "• With your consent or at your direction\n\n" +
               "We do not sell, rent, or share your personal information with third parties for their direct marketing purposes without your explicit consent.";
    }
    
    private String getDataSecurityContent() {
        return "We implement appropriate technical and organizational measures to protect your personal information against unauthorized access, alteration, disclosure, or destruction. These measures include:\n" +
               "• Encryption of sensitive data in transit and at rest\n" +
               "• Regular security assessments and updates\n" +
               "• Access controls and authentication measures\n" +
               "• Employee training on data protection practices\n\n" +
               "However, no method of transmission over the internet or electronic storage is 100% secure, and we cannot guarantee absolute security.";
    }
    
    private String getCookiesContent() {
        return "We use cookies and similar tracking technologies to collect and store information about your interactions with our services. These technologies help us:\n" +
               "• Remember your preferences and settings\n" +
               "• Analyze website traffic and usage patterns\n" +
               "• Provide personalized content and advertisements\n" +
               "• Improve our services and user experience\n\n" +
               "You can control cookies through your browser settings, but disabling them may affect the functionality of our services.";
    }
    
    private String getYourRightsContent() {
        return "Depending on your location, you may have certain rights regarding your personal information, including:\n" +
               "• Access: Request a copy of the personal information we hold about you\n" +
               "• Correction: Request correction of inaccurate or incomplete information\n" +
               "• Deletion: Request deletion of your personal information\n" +
               "• Portability: Request transfer of your information to another service\n" +
               "• Restriction: Request limitation of processing your information\n" +
               "• Objection: Object to certain types of processing\n\n" +
               "To exercise these rights, please contact us using the information provided in the 'Contact Us' section.";
    }
    
    private String getDataRetentionContent() {
        return "We retain your personal information for as long as necessary to fulfill the purposes for which it was collected, including:\n" +
               "• Providing our services to you\n" +
               "• Complying with legal obligations\n" +
               "• Resolving disputes\n" +
               "• Enforcing our agreements\n\n" +
               "When we no longer need your information, we will securely delete or anonymize it in accordance with our data retention policies and applicable laws.";
    }
    
    private String getInternationalTransferContent() {
        return "Your information may be transferred to and processed in countries other than your country of residence. These countries may have different data protection laws than your country. When we transfer your information internationally, we implement appropriate safeguards to protect your information in accordance with applicable laws.";
    }
    
    private String getChildrenPrivacyContent() {
        return "Our services are not directed to children under the age of 13, and we do not knowingly collect personal information from children under 13. If you are a parent or guardian and believe your child has provided us with personal information, please contact us, and we will take steps to remove such information from our systems.";
    }
    
    private String getChangesToPolicyContent() {
        return "We may update this Privacy Policy from time to time to reflect changes in our practices or applicable laws. We will notify you of any material changes by posting the updated policy on our website and updating the 'Last Updated' date. Your continued use of our services after such changes constitutes your acceptance of the updated Privacy Policy.";
    }
    
    private String getContactUsContent() {
        return "If you have any questions about this Privacy Policy or our privacy practices, please contact us at:\n\n" +
               "TirthOnGo Tours & Travel Services\n" +
               "Address: 1234 TirthOnGo Tours, Navi Mumbai, Maharashtra\n" +
               "Email: privacy@tirthongo.com\n" +
               "Phone: (+91) 8233 1602 770\n" +
               "Website: www.tirthongo.com\n\n" +
               "We will respond to your inquiry within a reasonable timeframe and work to address any concerns you may have.";
    }
}
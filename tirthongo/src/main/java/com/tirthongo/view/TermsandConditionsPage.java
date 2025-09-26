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

public class TermsandConditionsPage {
    
    public Scene getTermsAndConditions(Stage primaryStage) {
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
        
        VBox contentContainer = createTermsAndConditionsContent();
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
    
    private VBox createTermsAndConditionsContent() {
        VBox contentContainer = new VBox(20);
        contentContainer.setPadding(new Insets(40, 80, 40, 80));
        contentContainer.setStyle("-fx-background-color: white;");
        
        // Header section
        VBox headerSection = createHeaderSection();
        
        // Terms and conditions sections
        VBox termsContent = new VBox(25);
        
        termsContent.getChildren().addAll(
            createTermsSection("1. Acceptance of Terms", getAcceptanceOfTermsContent()),
            createTermsSection("2. Use of Our Services", getUseOfServicesContent()),
            createTermsSection("3. Booking and Reservations", getBookingReservationsContent()),
            createTermsSection("4. Payment Terms", getPaymentTermsContent()),
            createTermsSection("5. Cancellation and Refund Policy", getCancellationRefundContent()),
            createTermsSection("6. Travel Insurance", getTravelInsuranceContent()),
            createTermsSection("7. Traveler Responsibilities", getTravelerResponsibilitiesContent()),
            createTermsSection("8. Service Modifications", getServiceModificationsContent()),
            createTermsSection("9. Limitation of Liability", getLimitationOfLiabilityContent()),
            createTermsSection("10. Force Majeure", getForceMajeureContent()),
            createTermsSection("11. Intellectual Property", getIntellectualPropertyContent()),
            createTermsSection("12. User Conduct", getUserConductContent()),
            createTermsSection("13. Privacy Policy", getPrivacyPolicyContent()),
            createTermsSection("14. Dispute Resolution", getDisputeResolutionContent()),
            createTermsSection("15. Governing Law", getGoverningLawContent()),
            createTermsSection("16. Changes to Terms", getChangesToTermsContent()),
            createTermsSection("17. Contact Information", getContactInformationContent())
        );
        
        contentContainer.getChildren().addAll(headerSection, termsContent);
        
        return contentContainer;
    }
    
    private VBox createHeaderSection() {
        VBox headerSection = new VBox(15);
        headerSection.setAlignment(Pos.CENTER);
        headerSection.setPadding(new Insets(0, 0, 30, 0));
        
        Label titleLabel = new Label("Terms and Conditions");
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
        TextFlow introText = createTextFlow("Welcome to TirthOnGo! These Terms and Conditions ('Terms') govern your use of our website, mobile application, and travel booking services. By accessing or using our services, you agree to be bound by these Terms. Please read them carefully before making any bookings or using our services.");
        
        headerSection.getChildren().addAll(titleLabel, subtitleLabel, lastUpdated, introText);
        
        return headerSection;
    }
    
    private VBox createTermsSection(String title, String content) {
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
    private String getAcceptanceOfTermsContent() {
        return "By accessing and using TirthOnGo's services, including our website and mobile application, you acknowledge that you have read, understood, and agree to be bound by these Terms and Conditions. If you do not agree with any part of these terms, you must not use our services.\n\n" +
               "These Terms constitute a legally binding agreement between you and TirthOnGo Tours & Travel Services. Your continued use of our services constitutes acceptance of any modifications to these Terms.";
    }
    
    private String getUseOfServicesContent() {
        return "TirthOnGo provides online travel booking services, including but not limited to:\n" +
               "• Flight reservations and ticketing\n" +
               "• Hotel and accommodation bookings\n" +
               "• Tour package arrangements\n" +
               "• Transportation services\n" +
               "• Travel-related activities and experiences\n\n" +
               "You may use our services only for lawful purposes and in accordance with these Terms. You are responsible for ensuring that your use of our services complies with all applicable laws and regulations.";
    }
    
    private String getBookingReservationsContent() {
        return "When you make a booking through TirthOnGo:\n" +
               "• All bookings are subject to availability and confirmation\n" +
               "• Prices are subject to change until booking is confirmed\n" +
               "• You must provide accurate and complete information\n" +
               "• You are responsible for verifying all booking details\n" +
               "• Confirmation emails serve as proof of your reservation\n\n" +
               "TirthOnGo acts as an intermediary between you and service providers (airlines, hotels, tour operators). We facilitate bookings but are not directly responsible for the services provided by third parties.\n\n" +
               "Special requests (dietary requirements, room preferences, etc.) will be communicated to service providers but cannot be guaranteed.";
    }
    
    private String getPaymentTermsContent() {
        return "Payment and Pricing:\n" +
               "• All prices are displayed in Indian Rupees (INR) unless otherwise specified\n" +
               "• Prices include applicable taxes and fees unless stated otherwise\n" +
               "• Payment is required at the time of booking or as specified\n" +
               "• We accept major credit cards, debit cards, and online payment methods\n" +
               "• Service fees and booking charges are non-refundable\n\n" +
               "Dynamic Pricing:\n" +
               "• Prices may vary based on demand, seasonality, and availability\n" +
               "• Promotional rates may have specific terms and conditions\n\n" +
               "Payment Security:\n" +
               "• All transactions are processed through secure payment gateways\n" +
               "• We do not store your complete payment card information\n" +
               "• Additional verification may be required for certain transactions";
    }
    
    private String getCancellationRefundContent() {
        return "Cancellation Policy:\n" +
               "• Cancellation terms vary by service provider and booking type\n" +
               "• Some bookings may be non-refundable or have cancellation fees\n" +
               "• Cancellation requests must be made through our platform or customer service\n" +
               "• Processing time for refunds may vary (typically 7-14 business days)\n\n" +
               "Refund Policy:\n" +
               "• Refunds are subject to the terms of the specific service provider\n" +
               "• TirthOnGo service fees are generally non-refundable\n" +
               "• Refunds will be processed to the original payment method\n" +
               "• Partial refunds may apply for certain modifications\n\n" +
               "Flight Cancellations:\n" +
               "• Airline cancellation policies apply\n" +
               "• We will assist with rebooking or refund processing as per airline policies\n\n" +
               "Please review the specific cancellation terms for each booking before confirming your reservation.";
    }
    
    private String getTravelInsuranceContent() {
        return "Travel Insurance:\n" +
               "• We strongly recommend purchasing comprehensive travel insurance\n" +
               "• Travel insurance can protect against trip cancellations, medical emergencies, and other unforeseen circumstances\n" +
               "• Insurance options may be offered during the booking process\n" +
               "• TirthOnGo is not responsible for losses that could have been covered by travel insurance\n\n" +
               "Coverage Recommendations:\n" +
               "• Trip cancellation and interruption\n" +
               "• Medical and emergency evacuation\n" +
               "• Baggage and personal belongings\n" +
               "• Travel delays and missed connections\n\n" +
               "Please carefully review insurance policy terms and coverage before purchase.";
    }
    
    private String getTravelerResponsibilitiesContent() {
        return "As a traveler booking through TirthOnGo, you are responsible for:\n\n" +
               "Documentation:\n" +
               "• Ensuring valid passports, visas, and other required travel documents\n" +
               "• Checking entry requirements for your destinations\n" +
               "• Maintaining document validity for the entire travel period\n\n" +
               "Health and Safety:\n" +
               "• Meeting health requirements and vaccination standards\n" +
               "• Declaring any medical conditions that may affect travel\n" +
               "• Following safety guidelines and local laws\n\n" +
               "Booking Information:\n" +
               "• Providing accurate personal and contact information\n" +
               "• Reviewing all booking details before confirmation\n" +
               "• Notifying us of any changes to your contact information\n\n" +
               "Travel Conduct:\n" +
               "• Arriving at designated locations on time\n" +
               "• Following instructions from tour guides and service providers\n" +
               "• Respecting local customs and regulations";
    }
    
    private String getServiceModificationsContent() {
        return "TirthOnGo reserves the right to:\n" +
               "• Modify or discontinue services with or without notice\n" +
               "• Update pricing, terms, and conditions\n" +
               "• Change website features and functionality\n\n" +
               "Service Provider Changes:\n" +
               "• Service providers may modify itineraries, schedules, or accommodations\n" +
               "• Alternative arrangements of similar standard will be provided when possible\n" +
               "• Significant changes will be communicated as soon as reasonably possible\n\n" +
               "Force Majeure Events:\n" +
               "• Services may be modified or cancelled due to circumstances beyond our control\n" +
               "• This includes natural disasters, political unrest, health emergencies, or government restrictions\n\n" +
               "We will make reasonable efforts to minimize disruption and provide suitable alternatives when modifications are necessary.";
    }
    
    private String getLimitationOfLiabilityContent() {
        return "Limitation of Liability:\n" +
               "TirthOnGo's liability is limited to the maximum extent permitted by law. We are not liable for:\n\n" +
               "• Acts or omissions of third-party service providers\n" +
               "• Delays, cancellations, or changes by airlines, hotels, or other service providers\n" +
               "• Personal injury, illness, or death during travel\n" +
               "• Loss or damage to personal belongings\n" +
               "• Consequential, indirect, or punitive damages\n\n" +
               "Maximum Liability:\n" +
               "• Our total liability shall not exceed the amount paid for the specific service\n" +
               "• This limitation applies regardless of the cause of action\n\n" +
               "Third-Party Services:\n" +
               "• We act as an intermediary for third-party service providers\n" +
               "• Service providers are solely responsible for their services\n" +
               "• Complaints regarding services should be directed to the appropriate provider\n\n" +
               "This limitation of liability does not affect your statutory rights as a consumer where prohibited by law.";
    }
    
    private String getForceMajeureContent() {
        return "Force Majeure Events:\n" +
               "TirthOnGo shall not be held liable for any failure or delay in performance due to circumstances beyond our reasonable control, including but not limited to:\n\n" +
               "• Natural disasters (earthquakes, floods, hurricanes)\n" +
               "• Pandemics or health emergencies\n" +
               "• War, terrorism, or civil unrest\n" +
               "• Government actions or travel restrictions\n" +
               "• Strikes or labor disputes\n" +
               "• Technical failures or cyber attacks\n\n" +
               "Response to Force Majeure:\n" +
               "• We will make reasonable efforts to minimize impact\n" +
               "• Alternative arrangements will be offered when possible\n" +
               "• Refunds will be processed according to service provider policies\n" +
               "• Additional costs due to force majeure events are not covered by TirthOnGo\n\n" +
               "Customers are advised to have appropriate travel insurance to cover losses due to such events.";
    }
    
    private String getIntellectualPropertyContent() {
        return "Intellectual Property Rights:\n" +
               "All content on the TirthOnGo platform, including but not limited to:\n" +
               "• Website design, layout, and graphics\n" +
               "• Text, images, videos, and multimedia content\n" +
               "• Software, applications, and technology\n" +
               "• Trademarks, logos, and brand elements\n" +
               "• Database rights and compilations\n\n" +
               "These are owned by or licensed to TirthOnGo and are protected by intellectual property laws.\n\n" +
               "Permitted Use:\n" +
               "• You may view and use our content for personal, non-commercial purposes\n" +
               "• You may not reproduce, distribute, or modify our content without permission\n" +
               "• Screen scraping, data mining, or automated data collection is prohibited\n\n" +
               "User-Generated Content:\n" +
               "• Reviews, photos, and other content you submit may be used by TirthOnGo\n" +
               "• You retain ownership but grant us a license to use such content\n" +
               "• You are responsible for ensuring you have rights to submit content";
    }
    
    private String getUserConductContent() {
        return "Acceptable Use:\n" +
               "When using TirthOnGo services, you agree not to:\n\n" +
               "• Use our services for illegal or unauthorized purposes\n" +
               "• Provide false, misleading, or fraudulent information\n" +
               "• Interfere with or disrupt our services or servers\n" +
               "• Attempt to gain unauthorized access to our systems\n" +
               "• Use automated tools to access our services without permission\n" +
               "• Violate any applicable laws or regulations\n\n" +
               "Account Security:\n" +
               "• You are responsible for maintaining account confidentiality\n" +
               "• Notify us immediately of any unauthorized use\n" +
               "• Use strong passwords and keep login credentials secure\n\n" +
               "Prohibited Activities:\n" +
               "• Commercial use without authorization\n" +
               "• Harassment or abusive behavior\n" +
               "• Posting inappropriate or offensive content\n" +
               "• Circumventing security measures\n\n" +
               "Violation of these terms may result in account suspension or termination.";
    }
    
    private String getPrivacyPolicyContent() {
        return "Data Protection and Privacy:\n" +
               "Your privacy is important to us. Our Privacy Policy explains:\n" +
               "• What information we collect and how we use it\n" +
               "• How we protect your personal data\n" +
               "• Your rights regarding your information\n" +
               "• How to contact us about privacy concerns\n\n" +
               "By using our services, you consent to the collection and use of your information as described in our Privacy Policy, which is incorporated into these Terms by reference.\n\n" +
               "Data Sharing:\n" +
               "• We share necessary information with service providers to fulfill bookings\n" +
               "• We may share data as required by law or for legal proceedings\n" +
               "• We do not sell personal information to third parties for marketing purposes\n\n" +
               "Please review our complete Privacy Policy for detailed information about our data practices.";
    }
    
    private String getDisputeResolutionContent() {
        return "Dispute Resolution Process:\n\n" +
               "Step 1 - Direct Resolution:\n" +
               "• Contact our customer service team first\n" +
               "• We will attempt to resolve issues promptly and fairly\n" +
               "• Most disputes can be resolved through direct communication\n\n" +
               "Step 2 - Formal Complaint:\n" +
               "• If direct resolution fails, submit a formal written complaint\n" +
               "• Include all relevant details and supporting documentation\n" +
               "• We will investigate and respond within 15 business days\n\n" +
               "Step 3 - Mediation:\n" +
               "• For unresolved disputes, we may suggest mediation\n" +
               "• Mediation costs will be shared equally\n" +
               "• This is a voluntary process for both parties\n\n" +
               "Step 4 - Legal Action:\n" +
               "• As a last resort, disputes may be resolved through the courts\n" +
               "• Legal proceedings must be initiated within one year of the incident\n\n" +
               "Class Action Waiver:\n" +
               "You agree not to participate in class action lawsuits against TirthOnGo.";
    }
    
    private String getGoverningLawContent() {
        return "Governing Law and Jurisdiction:\n" +
               "These Terms and Conditions are governed by the laws of India. Any disputes arising from or relating to these Terms or your use of our services shall be subject to the exclusive jurisdiction of the courts in Mumbai, Maharashtra, India.\n\n" +
               "International Users:\n" +
               "• Users from other countries are subject to Indian law when using our services\n" +
               "• Local consumer protection laws may also apply where permitted\n" +
               "• Currency conversions and international transaction fees may apply\n\n" +
               "Regulatory Compliance:\n" +
               "• TirthOnGo operates under Indian travel and tourism regulations\n" +
               "• We comply with applicable data protection and consumer protection laws\n" +
               "• Service providers may be subject to their local regulations\n\n" +
               "If any provision of these Terms is found to be unenforceable, the remaining provisions will continue in full force and effect.";
    }
    
    private String getChangesToTermsContent() {
        return "Modifications to Terms:\n" +
               "TirthOnGo reserves the right to modify these Terms and Conditions at any time. Changes may be made to:\n" +
               "• Reflect changes in our services or business practices\n" +
               "• Comply with legal or regulatory requirements\n" +
               "• Improve clarity or address new circumstances\n\n" +
               "Notification of Changes:\n" +
               "• Updated Terms will be posted on our website\n" +
               "• The 'Last Updated' date will be revised\n" +
               "• Significant changes may be communicated via email\n" +
               "• Continued use of our services constitutes acceptance of changes\n\n" +
               "Your Options:\n" +
               "• Review Terms regularly for any updates\n" +
               "• Contact us if you have questions about changes\n" +
               "• Discontinue use if you do not agree with modified Terms\n\n" +
               "It is your responsibility to stay informed about changes to these Terms.";
    }
    
    private String getContactInformationContent() {
        return "For questions, concerns, or support regarding these Terms and Conditions or our services, please contact us:\n\n" +
               "TirthOnGo Tours & Travel Services\n\n" +
               "Customer Service:\n" +
               "📧 Email: support@tirthongo.com\n" +
               "📞 Phone: (+91) 8233 1602 770\n" +
               "📞 Alternative: (+91) 8212 7431 5462\n\n" +
               "Business Address:\n" +
               "1234 TirthOnGo Tours\n" +
               "Navi Mumbai, Maharashtra\n" +
               "India\n\n" +
               "Website: www.tirthongo.com\n\n" +
               "Legal Department:\n" +
               "📧 Email: legal@tirthongo.com\n\n" +
               "Business Hours:\n" +
               "Monday - Saturday: 08:00 - 17:30\n" +
               "Sunday: Closed\n\n" +
               "We strive to respond to all inquiries within 24-48 hours during business days. For urgent travel-related issues, please call our customer service hotline.";
    }
}
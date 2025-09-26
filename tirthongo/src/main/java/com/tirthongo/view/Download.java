package com.tirthongo.view;

import com.tirthongo.model.DestinationModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Download {

    public static DestinationModel model;

    public static void setModel(DestinationModel model1) {
        model = model1;
    }

    public void showTicketDownloadPage(DestinationModel model) {
        Stage ticketStage = new Stage();
        VBox layout = new VBox(12);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.CENTER);

        Label title = new Label("🎫 Your Travel Ticket");
        title.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #2b2b2b;");

        // Booking details
        Label destination = createInfoLabel("📍 Destination: ", model.getEndDestination());
        Label start = createInfoLabel("🛫 Starting From: ", model.getStartDestination());
        Label price = createInfoLabel("💰 Price per Adult: ₹", model.getPricePerAdult());
        Label bookingAmount = createInfoLabel("💵 Booking Amount: ₹", model.getBookAmount());
        Label guide = createInfoLabel("🧑‍✈ Guide: ", model.getGuideName());

        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy - hh:mm a"));
        Label dateLabel = createInfoLabel("📅 Booking Date: ", dateTime);

        // Download button
        Button downloadBtn = new Button("⬇ Download Ticket");
        styleMainButton(downloadBtn);
        downloadBtn.setOnAction(e -> {
            // Placeholder for PDF export
            System.out.println("Downloading ticket for:" + model.getEndDestination());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Download Ticket");
            alert.setHeaderText("Ticket Downloaded Successfully!");
            alert.setContentText("Your ticket for " + model.getEndDestination() + " has been saved.");
            alert.showAndWait();
        });

        // Back button
        Button backBtn = new Button("🔙 Back");
        backBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");
        backBtn.setOnAction(e -> ticketStage.close());

        HBox buttonBox = new HBox(15, downloadBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(title, destination, start, price, bookingAmount, guide, dateLabel, buttonBox);

        Scene scene = new Scene(layout, 420, 420);
        ticketStage.setTitle("Ticket Preview");
        ticketStage.setScene(scene);
        ticketStage.show();
    }

    private Label createInfoLabel(String label, String value) {
        Label l = new Label(label + value);
        l.setStyle("-fx-font-size: 16px;");
        return l;
    }

    private void styleMainButton(Button btn) {
        btn.setPrefWidth(180);
        btn.setPrefHeight(45);
        btn.setStyle("-fx-background-color: linear-gradient(to right, #4facfe, #00f2fe);"
                + " -fx-text-fill: black; -fx-effect: dropshadow(gaussian, black, 2, 0.5, 1, 1);"
                + " -fx-font-weight: bold; -fx-font-size: 14px;");
    }
}
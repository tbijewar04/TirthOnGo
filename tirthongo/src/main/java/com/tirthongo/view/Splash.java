// package com.tirthongo.view;

// import javafx.animation.*;
// import javafx.application.Application;
// import javafx.geometry.Pos;
// import javafx.scene.Scene;
// import javafx.scene.control.Label;
// import javafx.scene.effect.DropShadow;
// import javafx.scene.image.Image;
// import javafx.scene.image.ImageView;
// import javafx.scene.layout.*;
// import javafx.scene.paint.Color;
// import javafx.scene.shape.Circle;
// import javafx.scene.text.Font;
// import javafx.scene.text.FontWeight;
// import javafx.stage.Stage;
// import javafx.util.Duration;

// public class Splash extends Application {

//     @Override
//     public void start(Stage stage) {
//         // Load logo
//         Image logoImage = new Image(getClass().getResource("/Assets/Images/TirthOnGo2.png").toExternalForm());
//         ImageView logo = new ImageView(logoImage);
//         logo.setFitWidth(700);
//         logo.setFitHeight(500);
//         logo.setOpacity(0);

//         // Glow effect on logo
//         DropShadow glow = new DropShadow(40, Color.ORANGE);
//         glow.setSpread(0.4);
//         logo.setEffect(glow);

//         // Tagline label
//         Label tagline = new Label("Welcome");
//         tagline.setFont(Font.font("Georgia", FontWeight.MEDIUM, 28));
//         tagline.setTextFill(Color.ORANGE);
//         tagline.setOpacity(1); // Set full visibility

//         // Glitter sparkles behind logo
//         Pane sparkleLayer = createSparkleLayer();

//         VBox vbox = new VBox(20, logo, tagline);
//         vbox.setAlignment(Pos.CENTER);

//         StackPane root = new StackPane(sparkleLayer, vbox); // sparkles below main content
//         root.setStyle("-fx-background-color: white;");

//         Scene scene = new Scene(root, 1200, 680);
//         stage.setScene(scene);
//         stage.setTitle("TirthOnGo - Splash");
//         stage.show();

//         // Logo animation
//         FadeTransition fadeLogo = new FadeTransition(Duration.seconds(2), logo);
//         fadeLogo.setFromValue(0);
//         fadeLogo.setToValue(1);

//         ScaleTransition scaleLogo = new ScaleTransition(Duration.seconds(2), logo);
//         scaleLogo.setFromX(0.7);
//         scaleLogo.setFromY(0.7);
//         scaleLogo.setToX(1);
//         scaleLogo.setToY(1);

//         ParallelTransition logoAnim = new ParallelTransition(fadeLogo, scaleLogo);

//         // Typewriter effect for subtitle
//         String message = "✨ Your Spiritual Journey Begins Here... ✨";
//         Timeline typewriter = new Timeline();
//         final StringBuilder current = new StringBuilder();
//         for (int i = 0; i < message.length(); i++) {
//             final int index = i;
//             KeyFrame kf = new KeyFrame(Duration.millis(40 * i), e -> {
//                 current.append(message.charAt(index));
//                 tagline.setText(current.toString());
//             });
//             typewriter.getKeyFrames().add(kf);
//         }

//         SequentialTransition finalSequence = new SequentialTransition(
//                 logoAnim,
//                 typewriter
//         );

//         finalSequence.setOnFinished(e -> {
//             PauseTransition pause = new PauseTransition(Duration.seconds(3));
//             pause.setOnFinished(ev -> {
//                 stage.close(); // Replace with: stage.setScene(Homeload.getScene(stage));
//             });
//             pause.play();
//         });

//         finalSequence.play();
//     }

//     // Sparkle layer generator
//     private Pane createSparkleLayer() {
//         Pane sparklePane = new Pane();
//         for (int i = 0; i < 20; i++) {
//             Circle sparkle = new Circle(3, Color.GOLD);
//             sparkle.setTranslateX(Math.random() * 1200);
//             sparkle.setTranslateY(Math.random() * 700);
//             sparkle.setOpacity(0);

//             FadeTransition fade = new FadeTransition(Duration.seconds(2 + Math.random()), sparkle);
//             fade.setFromValue(0);
//             fade.setToValue(1);
//             fade.setCycleCount(Animation.INDEFINITE);
//             fade.setAutoReverse(true);
//             fade.play();

//             sparklePane.getChildren().add(sparkle);
//         }
//         return sparklePane;
//     }
// }

package com.tirthongo.view;

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Splash extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Load logo
        Image logoImage = new Image(getClass().getResource("/Assets/Images/TirthOnGo2.png").toExternalForm());
        ImageView logo = new ImageView(logoImage);
        logo.setFitWidth(700);
        logo.setFitHeight(500);
        logo.setOpacity(0);

        // Glow effect on logo
        DropShadow glow = new DropShadow(40, Color.ORANGE);
        glow.setSpread(0.4);
        glow.setRadius(40); // Ensure radius is set for DropShadow
        logo.setEffect(glow);

        // Tagline label
        Label tagline = new Label("Welcome");
        tagline.setFont(Font.font("Georgia", FontWeight.MEDIUM, 28)); // <-- FontWeight.MEDIUM is still an error here
        tagline.setTextFill(Color.ORANGE);
        tagline.setOpacity(1); // Set full visibility

        // Glitter sparkles behind logo
        Pane sparkleLayer = createSparkleLayer();

        VBox vbox = new VBox(20, logo, tagline);
        vbox.setAlignment(Pos.CENTER);

        StackPane root = new StackPane(sparkleLayer, vbox); // sparkles below main content
        root.setStyle("-fx-background-color: white;");

        Scene scene = new Scene(root);
        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle("TirthOnGo - Splash");
        primaryStage.show();

        // Logo animation
        FadeTransition fadeLogo = new FadeTransition(Duration.seconds(2), logo);
        fadeLogo.setFromValue(0);
        fadeLogo.setToValue(1);

        ScaleTransition scaleLogo = new ScaleTransition(Duration.seconds(2), logo);
        scaleLogo.setFromX(0.7);
        scaleLogo.setFromY(0.7);
        scaleLogo.setToX(1);
        scaleLogo.setToY(1);

        ParallelTransition logoAnim = new ParallelTransition(fadeLogo, scaleLogo);

        // Typewriter effect for subtitle
        String message = "✨ Your Spiritual Journey Begins Here... ✨";
        Timeline typewriter = new Timeline();
        final StringBuilder current = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            final int index = i;
            KeyFrame kf = new KeyFrame(Duration.millis(40 * i), e -> {
                current.append(message.charAt(index));
                tagline.setText(current.toString());
            });
            typewriter.getKeyFrames().add(kf);
        }

        SequentialTransition finalSequence = new SequentialTransition(
                logoAnim,
                typewriter
        );

        finalSequence.setOnFinished(e -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(ev -> {
                // --- NEW NAVIGATION LINE INSERTED HERE ---
                LoginPage loginPage = new LoginPage(); // Instantiate LoginPage
                loginPage.start(primaryStage); // Call LoginPage's start method to navigate
            });
            pause.play();
        });

        finalSequence.play();
    }

    // Sparkle layer generator
    private Pane createSparkleLayer() {
        Pane sparklePane = new Pane();
        for (int i = 0; i < 20; i++) {
            Circle sparkle = new Circle(3, Color.GOLD);
            sparkle.setTranslateX(Math.random() * 1200);
            sparkle.setTranslateY(Math.random() * 700);
            sparkle.setOpacity(0);

            FadeTransition fade = new FadeTransition(Duration.seconds(2 + Math.random()), sparkle);
            fade.setFromValue(0);
            fade.setToValue(1);
            fade.setCycleCount(Animation.INDEFINITE);
            fade.setAutoReverse(true);
            fade.play();

            sparklePane.getChildren().add(sparkle);
        }
        return sparklePane;
    }
}
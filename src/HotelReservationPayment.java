
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HotelReservationPayment extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hotel Reservation Payment");

        // Creating UI components
        Label cardNumberLabel = new Label("Credit Card Number:");
        TextField cardNumberField = new TextField();
        Button confirmButton = new Button("Confirm Payment");
        confirmButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        // Layout
        VBox root = new VBox();
        root.setSpacing(10);
        root.setPadding(new Insets(10));
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        gridPane.add(cardNumberLabel, 0, 0);
        gridPane.add(cardNumberField, 1, 0);
        root.getChildren().addAll(gridPane, confirmButton);

        // Event handling
        confirmButton.setOnAction(e -> {
            String cardNumber = cardNumberField.getText();
            if (isValidCreditCardNumber(cardNumber)) {
                displayConfirmation();
            } else {
                displayError("Invalid credit card number.");
            }
        });

        // Displaying the scene
        Scene scene = new Scene(root, 300, 150);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to validate credit card number (a simple check for demonstration purposes)
    private boolean isValidCreditCardNumber(String cardNumber) {
        // Simple check for demo - only checks if the card number is not empty
        return !cardNumber.trim().isEmpty();
    }

    // Method to display confirmation popup
    private void displayConfirmation() {
        Stage stage = new Stage();
        stage.setTitle("Booking Confirmation");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Label label = new Label("Booking successfully confirmed ✅");
        vbox.getChildren().add(label);

        Scene scene = new Scene(vbox, 250, 100);
        stage.setScene(scene);
        stage.show();
    }

    // Method to display error popup
    private void displayError(String errorMessage) {
        Stage stage = new Stage();
        stage.setTitle("Error");

        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        Label label = new Label(errorMessage);
        vbox.getChildren().add(label);

        Scene scene = new Scene(vbox, 250, 100);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
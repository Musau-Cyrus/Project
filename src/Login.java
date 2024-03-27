import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/login";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    @Override
    public void start(Stage primaryStage) {

        ImageView homeIcon = new ImageView(new Image("file:C:/Users/Administrator/Desktop/Code/Hompage/src/download.png"));
        homeIcon.setFitWidth(32); // Set width of the icon
        homeIcon.setFitHeight(32); // Set height of the icon

        Button homeButton = new Button();
        homeButton.setGraphic(homeIcon);
        homeButton.setOnAction(event -> {
            HomePage homePage = new HomePage();
            homePage.start(primaryStage);
        });
        // Creating label email
        Text text1 = new Text("Email");

        // Creating label password
        Text text2 = new Text("Password");

        // Creating Text Field for email
        TextField textField1 = new TextField();

        // Creating Text Field for password
        PasswordField textField2 = new PasswordField();

        // Creating Buttons
        Button button1 = new Button("Login");

        // Creating a Grid Pane
        GridPane gridPane = new GridPane();

        // Setting size for the pane
        gridPane.setMinSize(400, 200);

        // Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        // Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        // Arranging all the nodes in the grid
        gridPane.add(homeButton, 0, 0);
        gridPane.add(text1,0, 1);
        gridPane.add(textField1, 1, 1);
        gridPane.add(text2, 0, 2);
        gridPane.add(textField2, 1, 2);
        gridPane.add(button1, 0, 3);

        // Styling nodes
        button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        text1.setStyle("-fx-font: normal bold 20px 'serif'");
        text2.setStyle("-fx-font: normal bold 20px 'serif'");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        // Creating a scene object
        Scene scene = new Scene(gridPane);

        // Setting title to the Stage
        primaryStage.setTitle("Administrator Login Page");

        // Adding scene to the stage
        primaryStage.setScene(scene);

        // Database verification
        button1.setOnAction(event -> {
            String email = textField1.getText();
            String password = textField2.getText();
            if (verifyCredentials(email, password)) {
                Client_Details_Table table = new Client_Details_Table();
                table.start(new Stage());
                primaryStage.close();
            } else {
                displayError("Invalid email or Password.");
            }
        });

        // Displaying the contents of the stage
        primaryStage.show();
    }

    // Method to verify credentials from database
    private boolean verifyCredentials(String email, String password) {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM user WHERE email=? AND password=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            boolean isValidUser = resultSet.next();
            resultSet.close();
            statement.close();
            connection.close();
            return isValidUser;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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

    public static void main(String args[]) {

        launch(args);
    }
}

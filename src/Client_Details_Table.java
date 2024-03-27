import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Client_Details_Table extends Application {

    private static final String URL = "jdbc:mysql://localhost:3306/client_details";
    private static final String USERNAME = "root";

    private static final String PASSWORD = "";

    @Override
    public void start(Stage primaryStage) {
        // Create a table view
        TableView<String[]> tableView = new TableView<>();

        // Create columns for the table view
        String[] columnHeaders = {"First_Name", "Last_Name", "Sur_Name", "Email_Address", "Phone_Number",
                "ID_Number", "Gender", "Arrival_Date", "Departure_Date", "Purpose_of_Visit"};
        for (int i = 0; i < columnHeaders.length; i++) {
            final int columnIndex = i;
            TableColumn<String[], String> column = new TableColumn<>(columnHeaders[i]);
            column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[columnIndex]));
            tableView.getColumns().add(column);
        }

        // Fetch data from the database and populate the table view
        fetchDataFromDatabase(tableView);

        // Create a vertical box to hold the table view
        VBox vbox = new VBox(10, tableView);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        // Create the scene and set it to the stage
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Clients Details");
        primaryStage.show();
    }

    private void fetchDataFromDatabase(TableView<String[]> tableView) {
        try {
            // Connect to the database
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Create a statement
            Statement statement = connection.createStatement();

            // Execute the select query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM client_details");

            // Populate the table view with the result set data
            while (resultSet.next()) {
                String[] rowData = new String[10];
                for (int i = 1; i <= 10; i++) { // Start from index 1
                    rowData[i - 1] = resultSet.getString(i); // Adjust the array index
                }
                tableView.getItems().add(rowData);
            }

            // Close resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

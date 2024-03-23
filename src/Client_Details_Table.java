import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Client_Details_Table extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create a table view
        TableView<String[]> tableView = new TableView<>();

        // Create columns for the table view
        String[] columnHeaders = {"First Name", "Last Name", "Sur Name", "Email Address", "Phone Number",
                "ID Number", "Gender", "Arrival Date", "Departure Date", "Purpose of Visit"};
        for (int i = 0; i < columnHeaders.length; i++) {
            final int columnIndex = i;
            TableColumn<String[], String> column = new TableColumn<>(columnHeaders[i]);
            column.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[columnIndex]));
            tableView.getColumns().add(column);
        }




        // Create a vertical box to hold the grid pane, button, and table view
        VBox vbox = new VBox(10, tableView);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);

        // Create the scene and set it to the stage
        Scene scene = new Scene(vbox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Clients Details.");
        primaryStage.show();
    }

    private void clearFields(TextField... fields) {
        for (TextField field : fields) {
            field.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


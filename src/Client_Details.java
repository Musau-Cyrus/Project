import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.time.LocalDate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Client_Details  extends Application{

    // Database connection parameters
    private static final String URL = "jdbc:mysql://localhost:3306/client_details";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

@Override
public void start(Stage primaryStage){


    primaryStage.setTitle("Hotel Reservation System");
    //First Name
    Label label = new Label("FirstName");
    TextField firstname = new TextField();
    firstname.setPromptText("Enter FirstName");
    Label asterisk = new Label("*");
    asterisk.setStyle("-fx-text-fill: red; -fx-font-size: 20px; -fx-vertical-ailignment: top");
    Label errorLabel = new Label();
    errorLabel.setTextFill(Color.RED); // Set color to red for error message
    
    //LastName
    Label label2 = new Label("LastName");
    TextField lastname = new TextField();
    lastname.setPromptText("Enter LastName");
    Label asterisk1 = new Label("*");
    asterisk1.setStyle("-fx-text-fill: red; -fx-font-size: 20px; -fx-vertical-ailignment: top");
    Label errorLabel1 = new Label();
    errorLabel1.setTextFill(Color.RED); // Set color to red for error message
    //Surname
    Label label3 = new Label("SurName");
    TextField surname = new TextField();
    surname.setPromptText("Enter SurName");
    //Email
    Label label4 = new Label("Email Address");
    TextField email = new TextField();
    email.setPromptText("Enter Email address");
    Label asterisk2 = new Label("*");
    asterisk2.setStyle("-fx-text-fill: red; -fx-font-size: 20px; -fx-vertical-ailignment: top");
    
    //Phone Number
    Label label5 = new Label("Phone Number");
    TextField phone = new TextField();
    phone.textProperty().addListener((observable, oldValue, newValue) -> {
        if(!newValue.matches("\\d*")){ // \\d* matches any number of digits
            phone.setText(newValue.replaceAll("[^\\d]", "")); // Replace non-digit characters with empty string
        }
    });

    
    phone.setPromptText("Enter Pone Number");
    Label asterisk3 = new Label("*");
    asterisk3.setStyle("-fx-text-fill: red; -fx-font-size: 20px; -fx-vertical-ailignment: top");
    
    //National ID Number
    Label label6 = new Label("ID Number");
    TextField id = new TextField();
     id.textProperty().addListener((observable, oldValue, newValue) -> {
        if(!newValue.matches("\\d*")){ // \\d* matches any number of digits
            id.setText(newValue.replaceAll("[^\\d]", "")); // Replace non-digit characters with empty string
        }
    });
    id.setPromptText("Enter ID number");
    Label asterisk4 = new Label("*");
    asterisk4.setStyle("-fx-text-fill: red; -fx-font-size: 20px; -fx-vertical-ailignment: top");
    
    //Label for Gender
    Text text = new Text("Gender");
    //Creating RadioButtons for Gender selection
    ToggleGroup groupGender = new ToggleGroup();
    RadioButton maleRadio = new RadioButton("Male");
    maleRadio.setToggleGroup(groupGender);
    RadioButton femaleRadio = new RadioButton("female");
    femaleRadio.setToggleGroup(groupGender);
    Label asterisk7 = new Label("*");
    asterisk7.setStyle("-fx-text-fill: red; -fx-font-size: 20px; -fx-vertical-ailignment: top");
    
    //Arrival Date
    Text text2 = new Text("Arrival Date");
    DatePicker arrival = new DatePicker();
    arrival.setDayCellFactory(picker -> new DateCell() {
        @Override
        public void updateItem(LocalDate date, boolean empty) {
            super.updateItem(date, empty);
            setDisable(date.isBefore(LocalDate.now()));
        }
    });
    arrival.setEditable(false);
    arrival.setPromptText("Arrival Date");
    Label asterisk5 = new Label("*");
    asterisk5.setStyle("-fx-text-fill: red; -fx-font-size: 20px; -fx-vertical-ailignment: top");
    
    //Departure Date
    Text text3 = new Text("Departure Date");
    DatePicker departure = new DatePicker();
    departure.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(LocalDate.now()));
            }
        });
    departure.setEditable(false);
    departure.setPromptText("Departure Date");
    Label asterisk6 = new Label("*");
    asterisk6.setStyle("-fx-text-fill: red; -fx-font-size: 20px; -fx-vertical-ailignment: top");
    
    //Porpose of visit
    Text text4 = new Text("Pupose of visit");
    ToggleGroup groupPurpose = new ToggleGroup();
    RadioButton leisureRadio = new RadioButton("Leisure");
    leisureRadio.setToggleGroup(groupPurpose);
    RadioButton businessRadio = new RadioButton("Business");
    businessRadio.setToggleGroup(groupPurpose);
    
    //Payment
    Text text5 = new Text("Pay full amount now to secure your reservation.");
    Text text6 = new Text("Pay securely using: Visa, MasterCard, AMEX or mobile money.");
    Button btn = new Button("Book Now");
    btn.setOnAction(event -> {
        String fname = firstname.getText();
        String lname = lastname.getText();
        String mail = email.getText();
        String number = phone.getText();
        String id_no = id.getText();
        String gender = "";
        String arrive = "";
        String depart = "";
        String purpose = "";

        // Retrieve selected gender from ToggleGroup
        if (groupGender.getSelectedToggle() != null) {
            gender = ((RadioButton) groupGender.getSelectedToggle()).getText();
        }

        // Retrieve selected arrival date from DatePicker
        if (arrival.getValue() != null) {
            arrive = arrival.getValue().toString();
        }

        // Retrieve selected departure date from DatePicker
        if (departure.getValue() != null) {
            depart = departure.getValue().toString();
        }

        // Retrieve selected purpose of visit from ToggleGroup
        if (groupPurpose.getSelectedToggle() != null) {
            purpose = ((RadioButton) groupPurpose.getSelectedToggle()).getText();
        }

        if (fname.isEmpty() || lname.isEmpty() || mail.isEmpty() || number.isEmpty() || id_no.isEmpty()) {
            errorLabel.setText("Please fill in all required fields!");
        } else {
            try {
                // Establishing the database connection
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                // Creating a prepared statement
                // Creating a prepared statement
                String query = "INSERT INTO client_details (First_Name, Last_Name, Sur_Name, Email_Address, Phone_Number, ID_Number, Gender, Arrival_Date, Departure_Date, Purpose_of_Visit) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


                PreparedStatement statement = connection.prepareStatement(query);

// Setting parameters for the prepared statement
                statement.setString(1, fname);
                statement.setString(2, lname);
                statement.setString(3, surname.getText()); // Add surname
                statement.setString(4, mail);
                statement.setString(5, number);
                statement.setString(6, id_no);
                statement.setString(7, gender);
                statement.setString(8, arrive);
                statement.setString(9, depart);
                statement.setString(10, purpose);

                // Executing the statement to insert data
                int rowsInserted = statement.executeUpdate();

                if (rowsInserted > 0) {
                    System.out.println("A new client record inserted successfully!");
                }

                // Closing resources
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error occurred while inserting client details: " + e.getMessage());
                e.printStackTrace();
            }

            // Perform other actions if needed after inserting data

            Room hotel = new Room();
            hotel.start(new Stage());
            primaryStage.close(); // Close Hotel Management
        }
    });

    Button btn2 = new Button("Back");
        btn2.setOnAction(event -> {
            HomePage homePage = new HomePage();
            homePage.start(primaryStage);
        });
    
    //Creating a grid pane
    GridPane gridpane = new GridPane();
   gridpane.setMinSize(350,450);
   gridpane.setAlignment(Pos.BASELINE_LEFT);
   gridpane.setPadding(new Insets(10, 10, 10, 10));
   gridpane.setHgap(10);
   gridpane.setVgap(10);
   
   //Arranging Nodes in a GridPane.
   //First Name
   gridpane.add(label, 0, 0);
   gridpane.add(firstname, 1, 0);
   gridpane.add(asterisk, 2, 0);
   
   
   //LastName
   gridpane.add(label2, 0,1);
   gridpane.add(lastname, 1, 1);
    gridpane.add(asterisk1, 2, 1);
   
   //Surname
   gridpane.add(label3, 0, 2);
   gridpane.add(surname, 1, 2);
    
   
   //Email
   gridpane.add(label4, 0, 3);
   gridpane.add(email, 1, 3);
   gridpane.add(asterisk2, 2, 3);
   
   //Phone number
   gridpane.add(label5, 0, 4);
   gridpane.add(phone, 1, 4);
    gridpane.add(asterisk3, 2, 4);
   
   //Displaying ID number
   gridpane.add(label6, 0, 5);
   gridpane.add(id, 1, 5);
    gridpane.add(asterisk4, 2, 5);

   
   //Gender node
   gridpane.add(text, 0, 6);
   gridpane.add(maleRadio, 1, 6);
   gridpane.add(femaleRadio, 2, 6);
    
   // gridpane.add(asterisk7, 2, 6);
   
   //Arrival Date node
   gridpane.add(text2, 0, 8);
   gridpane.add(arrival, 1, 8);
    gridpane.add(asterisk5, 2, 8);

   
   //Depature Date Node
   gridpane.add(text3, 0, 9);
   gridpane.add(departure, 1, 9);
    gridpane.add(asterisk6, 2, 9);

   
   //Pupose of visiting Node
    gridpane.add(text4, 0, 10);
   gridpane.add(leisureRadio, 1, 10);
   gridpane.add(businessRadio, 1, 11);
   
  //Formats
    errorLabel.setStyle("-fx-font:normal bold 15px 'serif' ");
   label.setStyle("-fx-font:normal bold 15px 'serif' ");
   label2.setStyle("-fx-font:normal bold 15px 'serif' ");
   label3.setStyle("-fx-font:normal bold 15px 'serif' ");
   label4.setStyle("-fx-font:normal bold 15px 'serif' ");
   label5.setStyle("-fx-font:normal bold 15px 'serif' ");
   label6.setStyle("-fx-font:normal bold 15px 'serif' ");
   text.setStyle("-fx-font:normal bold 15px 'serif' ");
   text2.setStyle("-fx-font:normal bold 15px 'serif' ");
   text3.setStyle("-fx-font:normal bold 15px 'serif' ");
   text4.setStyle("-fx-font:normal bold 15px 'serif' ");
   text5.setStyle("-fx-font:normal bold 15px 'serif' ");
   //Setting Background color
   gridpane.setStyle("-fx-background-color:BEIGE");
   
  
   HBox hbox = new HBox(10, btn2, btn);
   hbox.setPadding(new Insets(10, 10, 10, 10));
   hbox.setSpacing(50);
    hbox.setAlignment(Pos.BOTTOM_LEFT);
   VBox vbox = new VBox(5,gridpane, text5, text6,errorLabel, hbox);
   vbox.setStyle("-fx-background-color:grey");
   vbox.setPadding(new Insets(10, 10, 10, 10));
   VBox.setMargin(hbox, new Insets(30,30,30,30));
    Scene scene = new Scene( vbox, 900,600);
    primaryStage.setScene(scene);
    primaryStage.show();
    
    
    
}

    public static void main(String[] args) {
       launch(args);
    }
    
}
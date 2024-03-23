import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Room extends Application {
    @Override
    public void start(Stage primaryStage){
        primaryStage.setTitle("Hotel Room Reservtion");
        Label room1 = new Label("Standard Twin");
        room1.setStyle("-fx-font:normal bold 15px 'serif' ");
        Text one = new Text("Bed and Breakfast - KES10,000 for one night.");
        Text two = new Text("Half Board - KES13,000 for one night.");
        Text three= new Text("Full Board - KES15,000 for one night.");
        Button btn1 = new Button("Book");
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Replace this with code to open the login page
                HotelReservationPayment room = new HotelReservationPayment();
                room.start(new Stage());
                primaryStage.close();//Close Home Page


                // Here you can launch a new window for login page or change the scene to login page.
                // For simplicity, I'll just print a message for demonstration purposes.
            }
        });
        Text four = new Text("");
        VBox vbox = new VBox(room1, one, two, three, btn1, four);
         VBox.setMargin(btn1, new Insets(5,5,40,40));
        
        Label room2 = new Label("Standard KingRoom");
         room2.setStyle("-fx-font:normal bold 15px 'serif' ");
        Text txt1 = new Text("Bed and Breakfast - KES11,000 for one night.");
        Text txt2 = new Text("Half Board - KES13,000 for one night.");
        Text txt3= new Text("Full Board - KES15,000 for one night.");
        Button btn2 = new Button("Book");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Replace this with code to open the login page
                HotelReservationPayment room = new HotelReservationPayment();
                room.start(new Stage());
                primaryStage.close();//Close Home Page


                // Here you can launch a new window for login page or change the scene to login page.
                // For simplicity, I'll just print a message for demonstration purposes.
            }
        });
        Text txt4 = new Text("");
        VBox vbox2 = new VBox(room2, txt1, txt2,txt3, btn2,  txt4);
         VBox.setMargin(btn2, new Insets(5,5,40,40));
        
         Label room3 = new Label("Family Room");
           room3.setStyle("-fx-font:normal bold 15px 'serif' ");
        Text text1 = new Text("Bed and Breakfast - KES11,000 for one night.");
        Text text2 = new Text("Half Board - KES13,000 for one night.");
        Text text3= new Text("Full Board - KES15,000 for one night.");
        Button btn3 = new Button("Book");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Replace this with code to open the login page
                HotelReservationPayment room = new HotelReservationPayment();
                room.start(new Stage());
                primaryStage.close();//Close Home Page


                // Here you can launch a new window for login page or change the scene to login page.
                // For simplicity, I'll just print a message for demonstration purposes.
            }
        });
        Text text4 = new Text("");
        VBox vbox3 = new VBox(room3, text1, text2,text3, btn3,  text4);
         VBox.setMargin(btn3, new Insets(5,5,40,40));
        
        VBox vboxMain = new VBox(vbox,  vbox2, vbox3);
         vboxMain.setStyle("-fx-background-color:CYAN");
         vboxMain.setPadding(new Insets(10, 10, 10, 10));
        
        Scene scene = new Scene(vboxMain, 900, 600);
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    
    }
    
}
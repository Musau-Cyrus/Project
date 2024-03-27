import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class HomePage extends Application {

    @Override
    public void start(Stage primaryStage) {
    
        // Create layout containers
        BorderPane borderPane = new BorderPane();
        VBox contentBox = new VBox(20);
        HBox buttonBox = new HBox(20);
        HBox menubox = new HBox(20);

         // Create a menu bar
        MenuBar menuBar = new MenuBar();

        // Create menus
        Menu adminMenu = new Menu("Administrator");
        Menu contactMenu = new Menu("Contact us");
        Menu locationMenu = new Menu("Location");
        Menu aboutMenu = new Menu("About us");

        // Create menu items
        MenuItem loginItem = new MenuItem("Login");
       

        Menu phoneItem = new Menu("Phone");
        MenuItem number = new MenuItem("+254 701345670");
        MenuItem number1 = new MenuItem("+254 798765432");
        phoneItem.getItems().addAll(number, number1);
        Menu emailItem = new Menu("email");
        MenuItem email = new MenuItem("springshotel@gmail.com");
        emailItem.getItems().addAll(email);
        Menu whatsappItem = new Menu("Whatsapp");
        MenuItem whatsapp = new MenuItem("https://wa.me/+254 012345678");
        whatsappItem.getItems().addAll(whatsapp);


        MenuItem aboutItem = new MenuItem("Google Maps Location");

        // Add menu items to the menus
       adminMenu.getItems().addAll(loginItem);
        contactMenu.getItems().addAll(phoneItem, emailItem, whatsappItem);
        locationMenu.getItems().add(aboutItem);

        menuBar.getMenus().addAll(adminMenu, contactMenu, locationMenu, aboutMenu);
        menuBar.setStyle("-fx-background-color:grey ;");
          menuBar.getMenus().forEach(menu -> menu.setStyle("-fx-text-fill: white;")); // Customize menu text color
        menuBar.getMenus().forEach(menu -> menu.getItems().forEach(item -> item.setStyle("-fx-text-fill: #800000;")));

        // Event handler for the login menu item
        loginItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Replace this with code to open the login page
                Login login = new Login();
                login.start(new Stage());
                primaryStage.close();//Close Home Page


                // Here you can launch a new window for login page or change the scene to login page.
                // For simplicity, I'll just print a message for demonstration purposes.
            }
        });

        Image image = new Image("file:C:/Users/Administrator/Documents/NetBeansProjects/BG_Image/src/2.jpg");

        BackgroundSize bSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);

        borderPane.setBackground(new Background(new BackgroundImage(image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                bSize)));

        

        // Create content elements
        Text titleLabel = new Text("Welcome to Our Hotel");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Text descriptionLabel = new Text("Enjoy a luxurious stay with breathtaking views.");

        // Create buttons
        Button bookButton = new Button("Check Availability");
        bookButton.setStyle("-fx-font-size: 16px;");
        // Event handler for the login menu item
        bookButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Replace this with code to open the login page
                Client_Details client = new Client_Details();
                client.start(new Stage());
                primaryStage.close();//Close Home Page
                // Here you can launch a new window for login page or change the scene to login page.
            }
        });

        // Add elements to containers
        contentBox.getChildren().addAll(titleLabel, descriptionLabel);
        buttonBox.getChildren().addAll(bookButton);
        contentBox.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);
        menubox.setAlignment(Pos.TOP_LEFT);

        // Set container positions in the border pane
        borderPane.setCenter(contentBox);
        borderPane.setBottom(buttonBox);
        borderPane.setTop(menuBar);
        BorderPane.setAlignment(contentBox, Pos.BOTTOM_LEFT);
        BorderPane.setAlignment(buttonBox, Pos.CENTER);
       
        // Set the scene
        Scene scene = new Scene(borderPane, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
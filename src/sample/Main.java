package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;




public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("f_open.fxml"));
        primaryStage.setTitle("");
        primaryStage.getIcons().add(new Image("robot.png")) ;
        primaryStage.setScene(new Scene(root, 480, 225));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package sample;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class c_open {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField fld_login;

    @FXML
    private PasswordField fld_password;

    @FXML
    private Button btn_open;

    @FXML
    private Label lb_status;

    @FXML
    void initialize() {

btn_open.setOnAction(event -> {
    openProject();
});

    }








public void openProject(){
    if(fld_login.getText().trim().isEmpty() || fld_password.getText().trim().isEmpty() ){
        lb_status.setText("Заполните все поля!");
        lb_status.setTextFill(Color.web("RED"));
    } else {
        MySqlCon.getCon(fld_login.getText(), fld_password.getText());

        if (MySqlCon.conState.equals("08S01")) {
            lb_status.setText("Сервер недоступен, обратитесь к админу!");
            lb_status.setTextFill(Color.web("RED"));
        } else {

            if (MySqlCon.con == null) {
                //System.out.println("Соединение неудачное");
                lb_status.setText("Введите правильный логин и пароль!");
                lb_status.setTextFill(Color.web("RED"));
            } else {
                lb_status.setText("Соединение установлено!");
                lb_status.setTextFill(Color.web("GREEN"));
                btn_open.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/sample/f_sales.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.getIcons().add(new Image("robot.png"));
                stage.setScene(new Scene(root));
                stage.showAndWait();
            }
        }
    }
}


}




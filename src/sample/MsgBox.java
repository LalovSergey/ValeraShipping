package sample;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class MsgBox {
    public static final int ONLY_OK = 1;
    public static final int OK_CANCEL = 2;
    public static int status;
    public static void showMessage(String msgTitle,String msgHeader,String msgContent, int callType){
        Alert alert = null;

        if (callType==1) {
            alert = new Alert(Alert.AlertType.INFORMATION);
        }
        if (callType==2) {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
        }

        alert.setTitle(msgTitle);
       alert.setHeaderText(msgHeader);
     alert.setContentText(msgContent);

alert.showAndWait().ifPresent(al->{
    if (al== ButtonType.OK){
       MsgBox.status=1;
   } else {
       MsgBox.status=0;
   }
});

    }
}

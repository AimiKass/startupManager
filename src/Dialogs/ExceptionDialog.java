package Dialogs;

import javafx.scene.control.Alert;

public class ExceptionDialog
{
    public void popDialog(String whereTheExceptionOccurred)
    {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText(null);
        alert.setContentText(whereTheExceptionOccurred);

        alert.showAndWait();
    }

}

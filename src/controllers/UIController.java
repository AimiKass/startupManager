package controllers;

import checkForExistingData.UsernameCheck;
import extractions.UsernameExtraction;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import writeReadFromDB.ReadFromDataBase;
import writeReadFromDB.WriteToDataBase;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UIController
{
    private Path dBPath = Paths.get("src\\dataBase\\username.txt");

    @FXML
    ListView listView;
    @FXML
    TextField textFieldForDiskName = new TextField();
    @FXML
    ChoiceBox<String> choiceBox = new ChoiceBox<>();

    @FXML
    private void initialize()
    {

        UsernameCheck usernameCheck = new UsernameCheck();
        UsernameExtraction usernameExtraction = new UsernameExtraction();
        WriteToDataBase writeToDataBase = new WriteToDataBase();
        ReadFromDataBase readFromDataBase = new ReadFromDataBase();

        if (!usernameCheck.userNameInDataBaseIs(dBPath))
        {
            writeToDataBase.writeUsername(dBPath, usernameExtraction.getTheFullUsernameAndTheHalfOne());
        }

        choiceBox.getItems().add(readFromDataBase.readUsername(dBPath)[0]);
        choiceBox.getSelectionModel().select(1);

    }


    @FXML
    private void addBtn(ActionEvent actionEvent)
    {

    }

    @FXML
    private void deleteBtn(ActionEvent actionEvent)
    {

    }


    @FXML
    private void saveBtn(ActionEvent actionEvent) throws IOException
    {

    }






    }





package controllers;

import checkForExistingData.CheckInDataBase;
import extractions.UsernameExtraction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import writeReadFromDB.ReadFromDataBase;
import writeReadFromDB.WriteToDataBase;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UIController
{
    private Path dBPath = Paths.get("src\\dataBase\\username.txt");

    @FXML
    ListView<String> listView;
    @FXML
    TextField textFieldForDiskName = new TextField();
    @FXML
    ChoiceBox<String> choiceBox = new ChoiceBox<>();

    ObservableList<String> observableListWithSites = FXCollections.observableArrayList("test1", "test2");


    @FXML
    private void initialize()
    {

        CheckInDataBase checkInDataBase = new CheckInDataBase();
        UsernameExtraction usernameExtraction = new UsernameExtraction();
        WriteToDataBase writeToDataBase = new WriteToDataBase();
        ReadFromDataBase readFromDataBase = new ReadFromDataBase();

        if (!checkInDataBase.forUsername(dBPath))
        {
            writeToDataBase.username(dBPath, usernameExtraction.getTheFullUsernameAndTheHalfOne());
        }

        choiceBox.getItems().add("Just You ("+readFromDataBase.readUsername(dBPath)[0]+")");
        choiceBox.getSelectionModel().select(1);

        listView.setItems(observableListWithSites);
        listView.setCellFactory(TextFieldListCell.forListView());
        listView.setEditable(true);
    }


    @FXML
    private void addBtn(ActionEvent actionEvent)
    {
        listView.getItems().addAll("newUrl");
    }

    @FXML
    private void deleteBtn(ActionEvent actionEvent)
    {
        observableListWithSites.remove(listView.getSelectionModel().getSelectedItem());
    }


    @FXML
    private void saveBtn(ActionEvent actionEvent) throws IOException
    {

    }






    }





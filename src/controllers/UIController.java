package controllers;

import checkForExistingData.CheckInDataBase;
import extractions.UsernameExtraction;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldListCell;
import models.Site;
import writeReadFromDB.ReadFromDataBase;
import writeReadFromDB.WriteToDataBase;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UIController
{
    private Path dBPath = Paths.get("src\\dataBase\\username.txt");

    @FXML
    ListView<Site> listView;
    @FXML
    TextField textFieldForDiskName = new TextField();
    @FXML
    ChoiceBox<String> choiceBox = new ChoiceBox<>();

    private ObservableList<Site> observableListWithSites = FXCollections.observableArrayList(new Site("https:/www.facebook.com","Facebook"));


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
        // TODO: 3/17/2019 Check that shit
//        listView.setCellFactory(TextFieldListCell.forListView());
        listView.setEditable(true);


        // TODO: 3/17/2019 Check that shit too
        listView.setOnEditCommit(new EventHandler<ListView.EditEvent<Site>>()
        {
            @Override
            public void handle(ListView.EditEvent<Site> event)
            {
                listView.getItems().set(event.getIndex(), event.getNewValue());
            }
        });


    }


    @FXML
    private void addBtn(ActionEvent actionEvent)
    {
        observableListWithSites.addAll(new Site("N/A","newUrl"+(observableListWithSites.size()+1)));
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





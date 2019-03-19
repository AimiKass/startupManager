package controllers;

import checkForExistingData.CheckInDataBase;
import executeScripts.RetrieveUrlsName;
import extractions.UsernameExtraction;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Site;
import writeReadFromDB.ReadFromDataBase;
import writeReadFromDB.WriteToDataBase;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class UIController
{
    public TextField textFieldForNewUrl;
    public TextField textFieldForNewUrlName;
    private Path dbPathForUsername = Paths.get("src\\dataBase\\username.txt");
    private Path dbPathForUrls = null;

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

        if (!checkInDataBase.forExistingData(dbPathForUsername))
        {
            writeToDataBase.username(dbPathForUsername, usernameExtraction.getTheFullUsernameAndTheHalfOne());
        }

        choiceBox.getItems().add("Just You ("+readFromDataBase.readUsername(dbPathForUsername)[0]+")");
        choiceBox.getSelectionModel().select(1);


        dbPathForUrls = Paths.get(textFieldForDiskName.getText()+"Users\\"+readFromDataBase.readUsername(dbPathForUsername)[1]+"\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\StartUpShit.bat");


        listView.setItems(observableListWithSites);

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Site>() {
            @Override
            public void changed(ObservableValue<? extends Site> observable, Site oldValue, Site newValue)
            {
                textFieldForNewUrl.setText(observable.getValue().getUrl());
                textFieldForNewUrlName.setText(observable.getValue().getName());
            }
        });


    }


    @FXML
    private void addBtn(ActionEvent actionEvent) throws IOException
    {
        RetrieveUrlsName retrieveUrlsName = new RetrieveUrlsName();


        if (textFieldForNewUrlName.getText().equals(""))
            observableListWithSites.addAll(new Site(textFieldForNewUrl.getText(), retrieveUrlsName.getNameFrom(textFieldForNewUrl.getText())));
        else
            observableListWithSites.addAll(new Site(textFieldForNewUrl.getText(),textFieldForNewUrlName.getText()));

        clearTextFields();
    }

    @FXML
    private void deleteBtn(ActionEvent actionEvent)
    {
        observableListWithSites.remove(listView.getSelectionModel().getSelectedItem());
    }


    @FXML
    private void saveBtn(ActionEvent actionEvent)
    {
        WriteToDataBase writeToDataBase = new WriteToDataBase();
        CheckInDataBase checkInDataBase = new CheckInDataBase();
        System.out.println(dbPathForUrls);

        List<String> urls = new ArrayList<String>();

        for (Site site:observableListWithSites)
            urls.add(site.getUrl());

        writeToDataBase.urls(dbPathForUrls,urls);

    }

    @FXML
    private void clearBtn(ActionEvent actionEvent) throws IOException
    {
        clearTextFields();
    }




    private void clearTextFields()
    {
        textFieldForNewUrl.clear();
        textFieldForNewUrlName.clear();
    }


    }





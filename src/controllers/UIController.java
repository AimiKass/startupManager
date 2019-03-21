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
    private Path dbPathForUrlName = Paths.get("src\\dataBase\\UrlNames.txt");
    private Path dbPathForUrls = null;
    private ObservableList<Site> observableListWithSites;

    @FXML
    ListView<Site> listView;
    @FXML
    TextField textFieldForDiskName = new TextField();
    @FXML
    ChoiceBox<String> choiceBox = new ChoiceBox<>();



    @FXML
    private void initialize()
    {

        CheckInDataBase checkInDataBase = new CheckInDataBase();
        UsernameExtraction usernameExtraction = new UsernameExtraction();
        WriteToDataBase writeToDataBase = new WriteToDataBase();
        ReadFromDataBase readFromDataBase = new ReadFromDataBase();

        List<String> allUrls = new ArrayList<>();
        List<String> allUrlNames = new ArrayList<>();


        if (!checkInDataBase.forExistingData(dbPathForUsername))
        {
            writeToDataBase.username(dbPathForUsername, usernameExtraction.getTheFullUsernameAndTheHalfOne());
            observableListWithSites = FXCollections.observableArrayList(new Site("https:/www.facebook.com","Facebook"));
            dbPathForUrls = Paths.get(textFieldForDiskName.getText()+"Users\\"+readFromDataBase.readUsername(dbPathForUsername)[1]+"\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\StartUpShit.bat");

        }
        else
        {
            dbPathForUrls = Paths.get(textFieldForDiskName.getText()+"Users\\"+readFromDataBase.readUsername(dbPathForUsername)[1]+"\\AppData\\Roaming\\Microsoft\\Windows\\Start Menu\\Programs\\Startup\\StartUpShit.bat");

            allUrls.addAll(readFromDataBase.readUrls(dbPathForUrls));


            allUrlNames.addAll(readFromDataBase.readUrlNames(dbPathForUrlName));


            List<Site> allSites = new ArrayList<>();

            for (int i=0 ; i< allUrls.size() ; i++)
                allSites.add(new Site(allUrls.get(i), allUrlNames.get(i)));

            observableListWithSites = FXCollections.observableArrayList(allSites);

        }


        choiceBox.getItems().add("Just You ("+readFromDataBase.readUsername(dbPathForUsername)[0]+")");
        choiceBox.getSelectionModel().select(1);


        listView.setItems(observableListWithSites);




        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Site>() {
            @Override
            public void changed(ObservableValue<? extends Site> observable, Site oldValue, Site newValue)
            {
                if (observable.getValue() != null)
                    if (observableListWithSites.size() == 0)
                    {
                        textFieldForNewUrl.setText("");
                        textFieldForNewUrlName.setText("");
                    }else
                    {
                        textFieldForNewUrl.setText(observable.getValue().getUrl());
                        textFieldForNewUrlName.setText(observable.getValue().getName());
                    }
                else
                {
                    textFieldForNewUrl.setText("");
                    textFieldForNewUrlName.setText("");
                }

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
        listView.getSelectionModel().clearSelection(listView.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void deleteBtn(ActionEvent actionEvent)
    {
        observableListWithSites.remove(listView.getSelectionModel().getSelectedItem());
    }



    @FXML
    private void saveBtn(ActionEvent actionEvent)
    {
        ReadFromDataBase readFromDataBase = new ReadFromDataBase();

        List<String> urlsToBeChecked = new ArrayList<String>(readFromDataBase.readUrls(dbPathForUrls));
        boolean newWriteInDataBaseIs = true;


        for (int i=0 ; i< urlsToBeChecked.size() ; i++)
            if (urlsToBeChecked.get(i).equals(observableListWithSites.get(i).getUrl()))
                newWriteInDataBaseIs = false;




        if (newWriteInDataBaseIs)
        {
            WriteToDataBase writeToDataBase = new WriteToDataBase();

            List<String> urlsToBeAdded = new ArrayList<String>();
            List<String> namesToBeAdded = new ArrayList<String>();


            for (Site site:observableListWithSites)
            {
                urlsToBeAdded.add(site.getUrl());
                namesToBeAdded.add(site.getName());
            }
            writeToDataBase.urls(dbPathForUrls,urlsToBeAdded);
            writeToDataBase.urlNames(dbPathForUrlName,namesToBeAdded);



        }else
        {
            observableListWithSites.get(listView.getSelectionModel().getSelectedIndex()).setName(textFieldForNewUrlName.getText());
            listView.getSelectionModel().clearSelection(listView.getSelectionModel().getSelectedIndex());
        }


    }

    @FXML
    private void clearBtn(ActionEvent actionEvent) throws IOException
    {
        clearTextFields();
        listView.getSelectionModel().clearSelection(listView.getSelectionModel().getSelectedIndex());
    }



    private void clearTextFields()
    {
        textFieldForNewUrl.clear();
        textFieldForNewUrlName.clear();
    }


}





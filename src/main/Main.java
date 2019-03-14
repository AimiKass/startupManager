package main;

import controllers.UIController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{

    private Pane pane;
    private static Stage stage;
    private UIController ctrl;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("/fxmls/sample.fxml"));
        pane = (Pane) loader.load();
        Scene scene = new Scene(pane);

        stage = primaryStage;
        stage.setScene(scene);
        stage.setTitle("LALALALALALA");
        stage.show();

    }


    public static void main(String[] args)
    {
        launch(args);
    }
}

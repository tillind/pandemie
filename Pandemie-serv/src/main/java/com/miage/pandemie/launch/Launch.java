package com.miage.pandemie.launch;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launch extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("com.miage.pandemie.launch.Launch.start()::serveur");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


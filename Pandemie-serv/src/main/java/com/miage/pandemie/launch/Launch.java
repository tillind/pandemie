package com.miage.pandemie.launch;

import com.miage.pandemie.business.chat.ServerChat;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launch extends Application {
    /*exemple du master*/
    @Override
    public void start(Stage stage) throws Exception {
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
               System.exit(0);
            }
         });

        Parent root = FXMLLoader.load(getClass().getResource("/com/miage/pandemie/view/index.fxml"));    
        Scene scene = new Scene(root);    
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}


package com.miage.pandemie.launch;


import com.miage.pandemie.business.param.EMenu;
import com.miage.pandemie.business.param.JsonParam;
import com.miage.pandemie.controller.BoardController;
import com.miage.pandemie.controller.IndexController;
import com.miage.pandemie.controller.OptionsController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Launch extends Application {
    private Stage stage ;
    private Rectangle2D visualBounds;
    private BoardController controlBoard;
    /**
     * @param args the command line arguments
    */
    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage pStage) throws Exception {
        stage=pStage;
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
            }
        });
        stage.setResizable(false);
        stage.setFullScreen(true);
        switchScene(EMenu.MAIN_MENU);   
        JsonParam.getParamJson();
    }
    
    @Override
    public void stop(){
       if(!(this.controlBoard == null)){
           this.controlBoard.stopControl();
       }
    }

    public void switchScene(EMenu value){
        FXMLLoader loader;
        Parent root=null;
        try 
        {     
            switch(value){
                case MAIN_MENU:
                    loader = new FXMLLoader(getClass().getResource("/com/miage/pandemie/view/Index.fxml"));
                    root = (Parent)loader.load();
                    IndexController controlMenu = (IndexController)loader.getController();
                    controlMenu.setLogic(this); 
                    break;
                case OPTIONS:
                    loader = new FXMLLoader(getClass().getResource("/com/miage/pandemie/view/options.fxml"));
                    root = (Parent)loader.load();
                    OptionsController controlOpt = (OptionsController)loader.getController();
                    controlOpt.setLogic(this);
                    break;
                case RULES:
                    loader = new FXMLLoader(getClass().getResource("/com/miage/pandemie/view/Index.fxml"));
                    root = (Parent)loader.load();
                    //IndexController control = (IndexController)loader.getController();
                    //control.setLogic(this);
                    break;
                case NEW_GAME:
                    loader = new FXMLLoader(getClass().getResource("/com/miage/pandemie/view/board.fxml"));
                    root = (Parent)loader.load();
                    controlBoard = (BoardController)loader.getController();
                    controlBoard.setLogic(this);
                    break;
                default: Platform.exit();break;
            }   
            visualBounds = Screen.getPrimary().getVisualBounds();    
            stage.setScene(new Scene(root,visualBounds.getWidth(),visualBounds.getHeight()));
            stage.show();
        } 
        catch (IOException e)
        {
            Logger.getLogger(Launch.class.getName()).log(Level.SEVERE, null, e);
        }
    } 
}

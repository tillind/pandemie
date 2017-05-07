package com.miage.pandemie.controller;



import com.miage.pandemie.business.chat.ClientChatImpl;
import com.miage.pandemie.business.chat.ServeurChat;
import com.miage.pandemie.business.jeu.ClientJeuImpl;
import com.miage.pandemie.business.jeu.ServeurJeu;
import com.miage.pandemie.business.param.EMenu;
import com.miage.pandemie.business.param.JsonParam;
import com.miage.pandemie.business.param.ParamCli;
import com.miage.pandemie.launch.Launch;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;







/**

 * FXML Controller class

 *

 * @author alex

 */

public class BoardController implements Initializable {



    private Launch myApps;  

    private ClientChatImpl cdiChat;

    private ClientJeuImpl cdiGame;

    private ServeurChat cdChat;

    private ServeurJeu cdGame;

    private ParamCli param;

 
    

    @FXML

    private Button quitBtn;

    @FXML

    private ListView chatView;

    @FXML

    private TextField chatField;

    @FXML

    private ImageView defausseInfectionImageView;

    

    /**

     * Initializes the controller class.

     */

    @Override

    public void initialize(URL url, ResourceBundle rb) {

        param = JsonParam.getParamJson().getParameters();

        

        try {

            cdChat =(ServeurChat) Naming.lookup("chat");

            cdGame = (ServeurJeu) Naming.lookup("game");

                       

        } catch (MalformedURLException | NotBoundException | RemoteException ex) {

            Alert alert = new Alert(Alert.AlertType.ERROR);

             alert.setTitle("Attention erreur de conexion");

             alert.setHeaderText("Nous n'avons pas pu trouvé le serveur, vérifier vos options de connexion.");

             alert.showAndWait();

             this.myApps.switchScene(EMenu.MAIN_MENU);

            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);

        }     

        try {

            cdiChat = new ClientChatImpl(this);

            cdChat.Connect(cdiChat, param.getName());

        } catch (RemoteException ex) {

            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Attention erreur de conexion");

            alert.setHeaderText("Erreur de connexion au chat inconnue. Veuillez ressayer.");

            alert.showAndWait();

        }

        

        try {

            cdiGame= new ClientJeuImpl(this);

            cdGame.Connect(cdiGame, param.getName());

        } catch (RemoteException ex) {

            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);

            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Attention erreur de conexion");

            alert.setHeaderText("Erreur de connexion au jeu inconnue. Veuillez ressayer.");

            alert.showAndWait();

        } 

    }  

    

    @FXML

    private void quitHandle(ActionEvent event){    

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Quitter le jeux");

        alert.setHeaderText("Attention, vous allez quitter la partie en cours");

        alert.setContentText("Que voulez vous faire ?");

        ButtonType quitToMenu = new ButtonType("Retourner au menu");

        ButtonType quitToDesktop = new ButtonType("Quitter le programme");

        ButtonType cancel = new ButtonType("Annuler",ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(quitToMenu, quitToDesktop , cancel);

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == quitToMenu){

            this.myApps.switchScene(EMenu.MAIN_MENU);

        } else if (result.get() == quitToDesktop) {

            Platform.exit();

        }else{

            

        }

    }

    @FXML

    private void sendMessageHandle(){

        try {

            cdChat.Getmessage(this.chatField.getText(),this.param.getName());

            this.chatField.clear();

        } catch (RemoteException ex) {

            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    

    

    public void addMessageChat(String message){

        this.chatView.getItems().add(message);

    }

    

    public void setLogic(Application apps){

        this.myApps = (Launch) apps;

    }

    

    public void stopControl(){

        try {

            cdChat.Desconnect(cdiChat, this.param.getName());

        } catch (RemoteException ex) {

            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    

    

   @FXML

    public void afficherCarte(){

        Label cardLbl = new Label("Carte jouer par tutu");

        

        Image img = new Image("/com/miage/pandemie/image/Roles/Expert.jpg");

        ImageView imgView = new ImageView(img);

        imgView.setRotate(90);

        imgView.setFitHeight(500);

        imgView.setFitWidth(620);

        

        

        

        FXMLLoader loader = new FXMLLoader(

                getClass().getResource(

                        "/com/miage/pandemie/view/paneCard.fxml"

                )

        );

        DialogPane pane = null;

        try {

            pane = loader.load();

        } catch (IOException ex) {

            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);

        }

        

        

              

        GridPane gridImageView = (GridPane) pane.getContent();

        GridPane gridLbl = (GridPane) pane.getHeader();

        gridImageView.add(imgView, 0, 0);

        gridLbl.add(cardLbl, 0, 0);

        this.defausseInfectionImageView.setImage(img);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setWidth(590);

        alert.setHeight(765);

        alert.setTitle("Carte jouer");

        alert.setContentText("");

        alert.setGraphic(pane);   

        alert.showAndWait();    

    }  
    private String tmpVilleClick;
    @FXML
    public void clickVille(MouseEvent event){
       Object obj = event.getSource();
       if(obj instanceof Label){
           System.out.println("com.miage.pandemie.controller.BoardController.clickVille()");
           tmpVilleClick = ((Label)obj).getText();
       }
    }
    
    @FXML
    public void volCharterClick(Event enEvent){
      Object obj = enEvent.getSource();
        if ( obj instanceof MenuItem){
           System.out.println("menuitem");
           Object obj1 = ((MenuItem)obj).parentPopupProperty().get();
           if(obj1 instanceof ContextMenu){
               Object obj2 =  ((ContextMenu) obj1).ownerNodeProperty();
           
               System.out.println(tmpVilleClick);
               
           }
       }
    }

}

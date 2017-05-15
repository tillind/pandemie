package com.miage.pandemie.controller;



import com.miage.pandemie.business.chat.ClientChatImpl;
import com.miage.pandemie.business.chat.ServeurChat;
import com.miage.pandemie.business.jeu.ClientJeuImpl;
import com.miage.pandemie.business.jeu.ServeurJeu;
import com.miage.pandemie.business.param.EMenu;
import com.miage.pandemie.business.param.JsonParam;
import com.miage.pandemie.business.param.LocatedImage;
import com.miage.pandemie.business.param.ParamCli;
import com.miage.pandemie.launch.Launch;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;







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
    private String role;

    //la main du joueur
    @FXML
    private ImageView c0,c1,c2,c3,c4,c5,c6;
    // foyer d'infection
    @FXML
    private Label t1,t2,t3,t4,t5,t6,t7,t8;
    //taux d'infection
    @FXML
    private Circle f0,f1,f2,f3,f4,f5,f6;
    //le pseudo des autres joueurs
    @FXML
    public Label j1,j2,j3;
  
    @FXML
    private ListView chatView;

    @FXML
    private TextField chatField;

    @FXML
    private ImageView defausseInfectionImageView;

    @FXML
    private Button launchPartieBtn;
     @FXML
    private Button fintour;
    
    @FXML
    private Label pseudoLbl,roleLbl;
    

    /**

     * Initializes the controller class.

     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initMain();
        param = JsonParam.getParamJson().getParameters();
        this.carteSelected = new ArrayList<>();
        this.carteClicked = null;
        
        roleLbl.setVisible(false);
        launchPartieBtn.setVisible(false);
        
        //j1.setVisible(false);
     
        //j2.setVisible(false);
        //j3.setVisible(false);
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
            pseudoLbl.setText(param.getName());
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
    public void afficherRole(){
        this.afficherCarte(this.roleLbl.getText());
    }


    public void afficherCarte(String link){
        Label cardLbl = new Label("Carte jouer"+this.param.getName());
        Image img = new Image(link);
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
    
    @FXML
    public void clickLaunchGame() {
        try {
            this.cdGame.launchGame(param.getName());
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void displayStartGame(){
        this.launchPartieBtn.setVisible(true);
    }
    
    public void addJoueur(String pseudo){
       
       
            j1.setText("ezpfok");
            
            System.out.println(j1.getText());
            
        /*else if(!j2.isVisible()){
           
            j2.setVisible(true);
             j2.setText(pseudo);
        }
        else if(!j3.isVisible()){
            
            j3.setVisible(true);
            j3.setText(pseudo);
        }*/
    }
    
    @FXML
    public void clickSelectCardHandle(Event event){
        ImageView tmp = (ImageView) event.getSource();
        LocatedImage img = (LocatedImage) tmp.getImage();
        this.carteClicked = img.getURL();     
        if(this.carteSelected.contains(img.getURL())){
            tmp.setEffect(null);
            this.carteSelected.remove( img.getURL());
            
        }else{
            DropShadow ds = new DropShadow(20,Color.AQUA);
            tmp.setEffect(ds);
            this.carteSelected.add( img.getURL());
        }
    }
    
    @FXML
    public void clickPseudoHandle(Event event){
        Label lbl = (Label) event.getSource();
        if(this.joueurSelected.equals(lbl.getText())){
            this.joueurSelected= null;
            lbl.setFont(Font.font(null, FontWeight.NORMAL, 24));
            this.joueurSelected = lbl.getText();
            lbl.setFont(Font.font(null, FontWeight.BOLD, 24));
        }
    }
    
    public void addCarteMain(String link){
        System.out.println(link);
        LocatedImage tmp = new LocatedImage(link);

        if(c0.isDisable()){
            c0.setImage(tmp);
            c0.setDisable(false);
        }else if(c1.isDisable()){
            c1.setImage(tmp);
            c1.setDisable(false);
        }else if(c2.isDisable()){
            c2.setImage(tmp);
            c2.setDisable(false);
        }else if(c3.isDisable()){
            c3.setImage(tmp);
            c3.setDisable(false);
        }else if(c4.isDisable()){
            c4.setImage(tmp);
            c4.setDisable(false);
        }else if(c5.isDisable()){
            c5.setImage(tmp);
            c5.setDisable(false);
        }else if(c6.isDisable()){
            c6.setImage(tmp);
            c6.setDisable(false);
        }
    }
    private void initMain() {
        this.c0.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c0.setDisable(true);
        this.c1.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c1.setDisable(true);
        this.c2.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c2.setDisable(true);
        this.c3.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c3.setDisable(true);
        this.c4.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c4.setDisable(true);
        this.c5.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c5.setDisable(true);
        this.c6.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));        
        this.c6.setDisable(true);
    }
    
    /***************************************************************************
     * allez server action joueur **********************************************
    * *************************************************************************/
    
    private String tmpVilleClick;
    private String villeDepart;
    private String carteClicked;
    private String joueurSelected;
    private List<String> carteSelected;
    
    @FXML
    public void clickVille(MouseEvent event){
       System.out.println("com.miage.pandemie.controller.BoardController.clickVille()");
       
       Object obj = event.getSource();
       if(obj instanceof Label){
           tmpVilleClick = ((Label)obj).getText();
       }
    }
    
    @FXML
    public void conduireClick(Event enEvent){
        try {
            this.cdGame.conduire(this.param.getName(), tmpVilleClick);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    public void volNavetteClick(Event enEvent){
        try {
            this.cdGame.volNavette(this.param.getName(),villeDepart, tmpVilleClick);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    public void volDirectClick(Event enEvent){
        try {
            this.cdGame.volDirect(this.param.getName(),carteClicked);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void volCharterClick(Event enEvent){        
        try {
            this.cdGame.volCharter(this.param.getName(),carteClicked,tmpVilleClick);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   @FXML
   public void traiterMaladieClick(Event event){
       MenuItem mi = (MenuItem) event.getSource();
       String color = mi.getText();
       try {
            this.cdGame.retirerCubeMaladie(this.param.getName(),color.toUpperCase());
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @FXML
   public void construireStationDeRechercheClick(){
        try {
            this.cdGame.construireStationRecherche(this.param.getName(),carteClicked,tmpVilleClick);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @FXML
   public void decouvrirRemedeClick(Event event){
       MenuItem mi = (MenuItem) event.getSource();
       String color = mi.getText();
        try {
            this.cdGame.decouvrirRemede(this.param.getName(),carteSelected,color);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @FXML
   public void partageConnaissanceClick(){
       try {
            this.cdGame.donnerCarte(this.param.getName(),joueurSelected,carteClicked);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
    @FXML
    public void finDeTour(Event event){
        try {
            this.cdGame.finDetour(this.param.getName());
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /***************************************************************************
     * retour server modification de la vue ***********************************
    * *************************************************************************/
   
    public void setFoyerInfection(int valeur){
        if(valeur>=1){
            t1.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=2) {
            t2.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=3) {
            t3.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=4) {
            t4.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=5) {
            t5.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=6) {
            t6.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=7) {
            t7.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=8) {
            t8.setFont(Font.font(null, FontWeight.BOLD, 36));
        }
    }
    
    
    public void setTauxInfection(int valeur,int position){
        switch (position) {
            case 0:
                f0.setFill(Color.DARKGREEN);
                break;
            case 1:
                f1.setFill(Color.DARKGREEN);
                break;
            case 2:
                f2.setFill(Color.DARKGREEN);
                break;
            case 3:
                f3.setFill(Color.DARKGREEN);
                break;
            case 4:
                f4.setFill(Color.DARKGREEN);
                break;
            case 5:
                f5.setFill(Color.DARKGREEN);
                break;
            case 6:
                f6.setFill(Color.DARKGREEN);
                break;
            default:
                break;
        }
        
    }
    
    public void setPion(String couleur, String position){
        //Mettre le pion de la couleur donnée à la position donnée ( c'est un nom de ville )
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    public void setVille(String nom, String couleur, int nbCubeMaladie){
        // Afficher nbCubeMaladie de la couleur donnée pour la ville donnée ( nom )
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    public void removeCarteMain(String link) {
        //Supprimer la carte correspondant au lien donné dans la main
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
   
    public void addCarteDefausseJoueur(String link) {
        //Afficher dans la defausse Joueur la carte correspondant au lien donné
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
  
    public void addCarteDefausseInfection(String link) {
        //Afficher dans la defausse Infection la carte correspondant au lien donné
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     
    public void addRole(String link) {
        roleLbl.setText(link);
        roleLbl.setVisible(true);
    }    

 
    public void addStation(String ville) {
        // Ajouter une station de recherche dans la ville donnée
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void decouvrirRemede(String couleur) {
        //Afficher le remede de la couleur donnée comme découvert
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void victoire() {
        //Afficher un joli gif
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void defaite() {
        //Fermer brutalement le jeu
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void addMaladieEradique(String couleur) {
        //Indiqué que la maladie de la couleur indiquée a été éradiquée
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}



import com.miage.pandemie.business.chat.ClientChatImpl;
import com.miage.pandemie.business.chat.ServeurChat;
import com.miage.pandemie.business.jeu.ClientJeuImpl;
import com.miage.pandemie.business.jeu.ServeurJeu;
import com.miage.pandemie.business.param.EMenu;
import com.miage.pandemie.business.param.JsonParam;
import com.miage.pandemie.business.param.LocatedImage;
import com.miage.pandemie.business.param.ParamCli;
import com.miage.pandemie.launch.Launch;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;







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
    private String role;

    //la main du joueur
    @FXML
    private ImageView c0,c1,c2,c3,c4,c5,c6;
    // foyer d'infection
    @FXML
    private Label t1,t2,t3,t4,t5,t6,t7,t8;
    //taux d'infection
    @FXML
    private Circle f0,f1,f2,f3,f4,f5,f6;
    //le pseudo des autres joueurs
    @FXML
    public Label j1,j2,j3;
    
    //Les maladies erradiquées
    @FXML
    private Label jauneEradiquee,rougeEradiquee,bleuEradiquee, noirEradiquee;
  
    //Les maladies erradiquées
    @FXML
    private Label jauneRemede,rougeRemede,bleuRemede, noirRemede;
  
    @FXML
    private ListView chatView;

    @FXML
    private TextField chatField;

    @FXML
    private ImageView defausseInfectionImageView,defausseJoueurImageView;

    @FXML
    private Button launchPartieBtn;
    
    @FXML
    private Button fintour;
    
    @FXML
    private Label pseudoLbl,roleLbl;
    

    /**

     * Initializes the controller class.

     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initMain();
        param = JsonParam.getParamJson().getParameters();
        this.carteSelected = new ArrayList<>();
        this.carteClicked = null;
        
        roleLbl.setVisible(false);
        launchPartieBtn.setVisible(false);
        
        //j1.setVisible(false);
     
        //j2.setVisible(false);
        //j3.setVisible(false);
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
            pseudoLbl.setText(param.getName());
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
    public void afficherRole(){
        this.afficherCarte(this.roleLbl.getText());
    }


    public void afficherCarte(String link){
        Label cardLbl = new Label("Carte jouer"+this.param.getName());
        Image img = new Image(link);
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
    
    @FXML
    public void clickLaunchGame() {
        try {
            this.cdGame.launchGame(param.getName());
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void displayStartGame(){
        this.launchPartieBtn.setVisible(true);
    }
    
    public void addJoueur(String pseudo){
       
       
            j1.setText("ezpfok");
            
            System.out.println(j1.getText());
            
        /*else if(!j2.isVisible()){
           
            j2.setVisible(true);
             j2.setText(pseudo);
        }
        else if(!j3.isVisible()){
            
            j3.setVisible(true);
            j3.setText(pseudo);
        }*/
    }
    
    @FXML
    public void clickSelectCardHandle(Event event){
        ImageView tmp = (ImageView) event.getSource();
        LocatedImage img = (LocatedImage) tmp.getImage();
        this.carteClicked = img.getURL();     
        if(this.carteSelected.contains(img.getURL())){
            tmp.setEffect(null);
            this.carteSelected.remove( img.getURL());
            
        }else{
            DropShadow ds = new DropShadow(20,Color.AQUA);
            tmp.setEffect(ds);
            this.carteSelected.add( img.getURL());
        }
    }
    
    @FXML
    public void clickPseudoHandle(Event event){
        Label lbl = (Label) event.getSource();
        if(this.joueurSelected.equals(lbl.getText())){
            this.joueurSelected= null;
            lbl.setFont(Font.font(null, FontWeight.NORMAL, 24));
            this.joueurSelected = lbl.getText();
            lbl.setFont(Font.font(null, FontWeight.BOLD, 24));
        }
    }
    
    public void addCarteMain(String link){
        System.out.println(link);
        LocatedImage tmp = new LocatedImage(link);

        if(c0.isDisable()){
            c0.setImage(tmp);
            c0.setDisable(false);
        }else if(c1.isDisable()){
            c1.setImage(tmp);
            c1.setDisable(false);
        }else if(c2.isDisable()){
            c2.setImage(tmp);
            c2.setDisable(false);
        }else if(c3.isDisable()){
            c3.setImage(tmp);
            c3.setDisable(false);
        }else if(c4.isDisable()){
            c4.setImage(tmp);
            c4.setDisable(false);
        }else if(c5.isDisable()){
            c5.setImage(tmp);
            c5.setDisable(false);
        }else if(c6.isDisable()){
            c6.setImage(tmp);
            c6.setDisable(false);
        }
    }
    private void initMain() {
        this.c0.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c0.setDisable(true);
        this.c1.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c1.setDisable(true);
        this.c2.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c2.setDisable(true);
        this.c3.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c3.setDisable(true);
        this.c4.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c4.setDisable(true);
        this.c5.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));
        this.c5.setDisable(true);
        this.c6.setImage(new LocatedImage("/com/miage/pandemie/image/Joueur/Verso.jpg"));        
        this.c6.setDisable(true);
    }
    
    /***************************************************************************
     * allez server action joueur **********************************************
    * *************************************************************************/
    
    private String tmpVilleClick;
    private String villeDepart;
    private String carteClicked;
    private String joueurSelected;
    private List<String> carteSelected;
    
    @FXML
    public void clickVille(MouseEvent event){
       System.out.println("com.miage.pandemie.controller.BoardController.clickVille()");
       
       Object obj = event.getSource();
       if(obj instanceof Label){
           tmpVilleClick = ((Label)obj).getText();
       }
    }
    
    @FXML
    public void overVille(MouseEvent event){
    Object obj = event.getSource();
       if(obj instanceof Label){
           tmpVilleClick= ((Label)obj).getText();
      }
      try {
            this.cdGame.getInfoVille(this.param.getName(),tmpVilleClick);
        }
      catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    @FXML
    public void conduireClick(Event enEvent){
        try {
            this.cdGame.conduire(this.param.getName(), tmpVilleClick);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    @FXML
    public void volNavetteClick(Event enEvent){
        try {
            this.cdGame.volNavette(this.param.getName(),villeDepart, tmpVilleClick);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     @FXML
    public void volDirectClick(Event enEvent){
        try {
            this.cdGame.volDirect(this.param.getName(),carteClicked);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    public void volCharterClick(Event enEvent){        
        try {
            this.cdGame.volCharter(this.param.getName(),carteClicked,tmpVilleClick);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   @FXML
   public void traiterMaladieClick(Event event){
       MenuItem mi = (MenuItem) event.getSource();
       String color = mi.getText();
       try {
            this.cdGame.retirerCubeMaladie(this.param.getName(),color.toUpperCase());
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @FXML
   public void construireStationDeRechercheClick(){
        try {
            this.cdGame.construireStationRecherche(this.param.getName(),carteClicked,tmpVilleClick);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @FXML
   public void decouvrirRemedeClick(Event event){
       MenuItem mi = (MenuItem) event.getSource();
       String color = mi.getText();
        try {
            this.cdGame.decouvrirRemede(this.param.getName(),carteSelected,color);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
   @FXML
   public void partageConnaissanceClick(){
       try {
            this.cdGame.donnerCarte(this.param.getName(),joueurSelected,carteClicked);
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
   
    @FXML
    public void finDeTour(Event event){
        try {
            this.cdGame.finDetour(this.param.getName());
        } catch (RemoteException ex) {
            Logger.getLogger(BoardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    /***************************************************************************
     * retour server modification de la vue ***********************************
    * *************************************************************************/
   
    public void setFoyerInfection(int valeur){
        if(valeur>=1){
            t1.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=2) {
            t2.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=3) {
            t3.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=4) {
            t4.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=5) {
            t5.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=6) {
            t6.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=7) {
            t7.setFont(Font.font(null, FontWeight.BOLD, 36));
        }else if(valeur>=8) {
            t8.setFont(Font.font(null, FontWeight.BOLD, 36));
        }
    }
    
    
    public void setTauxInfection(int valeur,int position){
        switch (position) {
            case 0:
                f0.setFill(Color.DARKGREEN);
                break;
            case 1:
                f1.setFill(Color.DARKGREEN);
                break;
            case 2:
                f2.setFill(Color.DARKGREEN);
                break;
            case 3:
                f3.setFill(Color.DARKGREEN);
                break;
            case 4:
                f4.setFill(Color.DARKGREEN);
                break;
            case 5:
                f5.setFill(Color.DARKGREEN);
                break;
            case 6:
                f6.setFill(Color.DARKGREEN);
                break;
            default:
                break;
        }
        
    }
    
    public void setPion(String couleur, String position){
        //Mettre le pion de la couleur donnée à la position donnée ( c'est un nom de ville )
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
    
    public void removeCarteMain(String link) {
       LocatedImage testImage =  new LocatedImage(link);
       if(c0.getImage().equals(testImage))
       {
           c0.setImage(null);
       }
       else if(c1.getImage().equals(testImage))
       {
           c1.setImage(null);
       }
       else if (c2.getImage().equals(testImage))
       {
           c2.setImage(null);
       }
       else if (c3.getImage().equals(testImage))
       {
           c3.setImage(null);
       }
        else if (c4.getImage().equals(testImage))
       {
           c4.setImage(null);
       }
       else if (c5.getImage().equals(testImage))
       {
           c5.setImage(null);
       }
               else if (c6.getImage().equals(testImage))
       {
           c6.setImage(null);
       }
    }
    
   
    public void addCarteDefausseJoueur(String link) {
        //Afficher dans la defausse Joueur la carte correspondant au lien donné
        defausseJoueurImageView.setImage(new LocatedImage(link));
        defausseJoueurImageView.setVisible(true);
       
    }
    
  
    public void addCarteDefausseInfection(String link) {
        //Afficher dans la defausse Infection la carte correspondant au lien donné
        defausseInfectionImageView.setImage(new LocatedImage(link));
        defausseJoueurImageView.setVisible(true);

    }

     
    public void addRole(String link) {
        roleLbl.setText(link);
        roleLbl.setVisible(true);
    }    



    public void decouvrirRemede(String couleur) {
        //Afficher le remede de la couleur donnée comme découvert
        switch(couleur){
            case "jaune": jauneRemede.setVisible(true);break;
            case "rouge": rougeRemede.setVisible(true);break;
            case "bleu": bleuRemede.setVisible(true);break;
            case "noir": noirRemede.setVisible(true);break;
            default : break;
        }
    }


    public void victoire() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Victoire");
        alert.setHeaderText("Vous avez gagné");
        alert.setContentText("Bravo ! C'est pas facile");
        alert.showAndWait();
    }

    public void defaite() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Défaite");
        alert.setHeaderText("Vous avez perdu");
        alert.setContentText("Essayez encore !");
        alert.showAndWait();
    }

    public void addMaladieEradique(String couleur) {
        //Indiqué que la maladie de la couleur indiquée a été éradiquée
                //Afficher le remede de la couleur donnée comme découvert
        switch(couleur){
            case "jaune": jauneEradiquee.setVisible(true);break;
            case "rouge": rougeEradiquee.setVisible(true);break;
            case "bleu": bleuEradiquee.setVisible(true);break;
            case "noir": noirEradiquee.setVisible(true);break;
            default : break;
        }
    }

    public void afficherInfoVille(boolean aStation, int nbCubeJaune, int nbCubeRouge, int nbCubeBleu, int nbCubeNoir) {
        Tooltip infoVille = new Tooltip();
        infoVille.setText("A une Station de Recherche"+aStation+"\n"
        +"Cube maladie jaune : "+nbCubeJaune+"\n"
        +"Cube maladie rouge : "+nbCubeRouge+"\n"
        +"Cube maladie bleu : "+nbCubeBleu+"\n"
        +"Cube maladie noir : "+nbCubeNoir+"\n");
    }
}

        if(c0.isDisable()){
            c0.setImage(tmp);
        }else if(c1.isDisable()){
            c0.setDisable(false);
            c1.setImage(tmp);
            c1.setDisable(false);
        }else if(c2.isDisable()){
            c2.setImage(tmp);
        }else if(c3.isDisable()){
            c2.setDisable(false);
            c3.setImage(tmp);
        }else if(c4.isDisable()){
            c3.setDisable(false);
            c4.setImage(tmp);
            c4.setDisable(false);
        }else if(c5.isDisable()){
            c5.setImage(tmp);
            c5.setDisable(false);
        }else if(c6.isDisable()){
            c6.setImage(tmp);
            c6.setDisable(false);
        }

        LocatedImage tmp = new LocatedImage(link);
        System.out.println(link);
    public void addCarteMain(String link){
    }
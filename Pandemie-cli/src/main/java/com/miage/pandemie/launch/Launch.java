package com.miage.pandemie.launch;

import com.miage.pandemie.business.chat.ClientDistantImpl;

import com.miage.pandemie.business.chat.ServeurChat;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;
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
    
    @Override
    public void start(Stage stage) throws Exception {
          stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Platform.exit();
               System.exit(0);
            }
         });
          
        System.out.println("com.miage.pandemie.launch.Launch.start()");
        Parent root = FXMLLoader.load(getClass().getResource("/com/miage/pandemie/view/Index.fxml"));      
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        ServeurChat cd = null ;
        ClientDistantImpl cdi;
        
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Nom d'utilisateur ?:");
        String str = sc.nextLine();
        
        
        try {
            try {
                cd =(ServeurChat) Naming.lookup("chat");
                cdi = new ClientDistantImpl();
                cd.Connect(cdi,str);
            } catch (NotBoundException ex) {
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MalformedURLException ex) {
                //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
         
            
        } catch (RemoteException ex) {
            //Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String msg = sc.nextLine();
        cd.Getmessage(msg, str);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

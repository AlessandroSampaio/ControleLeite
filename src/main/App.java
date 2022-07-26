package main;

import controls.MainPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author AlessandroSampaio
 */
public class App extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        MainPane root = new MainPane();
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("Controle de Coleta");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

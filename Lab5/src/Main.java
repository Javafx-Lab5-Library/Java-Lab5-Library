/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import model.CollectionOfBooks;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.MainView;

/**
 *
 * @author Niklas
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();
        VBox imageBox = new VBox();
        MainView view = new MainView(primaryStage, imageBox);
        
        
        
        root.getChildren().setAll(view, imageBox); 
        
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Library");
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}



        /*Button btn = new Button();
        btn.setText("read from file");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    readFile(library, stage);
                } catch (IOException ex) {
                    System.out.println("shit happned");
                }
                System.out.println("Hello World!");
            }
        });*/
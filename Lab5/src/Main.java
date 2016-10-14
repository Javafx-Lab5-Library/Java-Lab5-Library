/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CollectionOfBooks;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.MainView;

/**
 *
 * @author Niklas
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        CollectionOfBooks books = new CollectionOfBooks();
        MainView view = new MainView(books, primaryStage);
        
        /*Button btn = new Button();
        btn.setText("read from file");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try {
                    readFile(books, stage);
                } catch (IOException ex) {
                    System.out.println("shit happned");
                }
                System.out.println("Hello World!");
            }
        });*/
        
        Scene scene = new Scene(view);
        
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

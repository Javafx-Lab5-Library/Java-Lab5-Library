/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import model.CollectionOfBooks;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Niklas
 */
public class MainView extends VBox {
    private CollectionOfBooks books;
    private Controller controller;
    private BottomHboxView bottomHbox;
    private CenterTableView centerTable;
    private SearchFieldView searchField;
    private MenuFieldView menuField;
    private FileChooserView fileChooser;
    private ExitVBoxView exitView;
    
    public MainView(CollectionOfBooks books, Stage stage) {
        this.books = books;
        initView(stage);
    }
    
    private void initView(Stage stage) {
        centerTable = new CenterTableView(books);
        fileChooser = new FileChooserView(stage);
        controller = new Controller(this, books, centerTable, fileChooser);
        bottomHbox = new BottomHboxView(books, controller);
        searchField = new SearchFieldView(books, controller);
        menuField = new MenuFieldView(controller);
        
        
        this.setPrefSize(600, 450);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5, 10, 5, 10));
        this.getChildren().addAll(menuField, searchField, centerTable, bottomHbox);
        
        stage.setOnCloseRequest(e-> {
            e.consume();
            saveBeforeQuit();
                });
        
    }
    
    private void saveBeforeQuit(){
        exitView = new ExitVBoxView();
        System.out.println("hej");
    }
}

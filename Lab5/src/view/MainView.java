/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.CollectionOfBooks;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Niklas
 */
public class MainView extends VBox {
    private CollectionOfBooks books;
    private Stage stage;
    private Controller controller;
    private BottomHboxView bottomHbox;
    private CenterTableView centerTable;
    private SearchFieldView searchField;
    private MenuFieldView menuField;
    private FileChooserView fileChooser;
    private ExitVBoxView exitView;
    private SaveAnimationView saveAniView;
    private StackPane st;
    
    public MainView(CollectionOfBooks books, Stage stage, Canvas canvas, 
            ImageView bookImage, HBox imageBox) {
        this.books = books;
        this.stage = stage;
        this.st = st;
        initView(canvas, bookImage, imageBox);
    }
    
    private void initView(Canvas canvas, ImageView bookImage, HBox imageBox) {
        centerTable = new CenterTableView(books);
        fileChooser = new FileChooserView(stage);
        controller = new Controller(this, books, centerTable, fileChooser, 
                stage, canvas, bookImage, imageBox);
        bottomHbox = new BottomHboxView(books, controller);
        searchField = new SearchFieldView(books, controller);
        menuField = new MenuFieldView(controller);
        books.addObserver(centerTable);
        
        this.setPrefSize(470, 420);
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(0, 0, 0, 0));
        this.getChildren().addAll(menuField, bottomHbox, searchField, centerTable);
        this.setVgrow(centerTable, Priority.ALWAYS);
        
        saveBeforeQuit();
    }
    
    private void saveBeforeQuit(){
        stage.setOnCloseRequest(e-> {
            e.consume();
            controller.exitProgram();
        });
    }
}

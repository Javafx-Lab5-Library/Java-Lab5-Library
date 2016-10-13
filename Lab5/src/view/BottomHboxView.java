/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.io.IOException;
import model.Author;
import model.Book;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import model.CollectionOfBooks;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 *
 * @author Niklas
 */
public class BottomHboxView extends HBox {
    private CollectionOfBooks books;
    private Controller controller;
    private Button addBook;
    private Button removeBook;
    private Button quickSave;
    private Button refresh;

    public BottomHboxView(CollectionOfBooks books, Controller controller) {
        super(30);
        this.books = books;
        this.controller = controller;
        initView(books);
        addEventHandlers(books);
    }
    
    private void initView(CollectionOfBooks books) {
        this.setAlignment(Pos.BASELINE_LEFT);
        this.setPadding(new Insets(10, 10, 5, 10));
        
        addBook = new Button("Add Book");
        removeBook = new Button("Remove Book");
        quickSave = new Button("Save");
        refresh = new Button("Refresh");
        
        addBook.setMinWidth(90);
        removeBook.setMinWidth(90);
        quickSave.setMinWidth(90);
        refresh.setMinWidth(90);

        this.getChildren().addAll(addBook, removeBook, refresh, quickSave);
    }
    
    private void addEventHandlers(CollectionOfBooks books) {
        
        // med lambda expressions
        addBook.setOnAction((ActionEvent event) -> controller.addBook());
        /*addBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                controller.addBook();
                System.out.println("ADD IN GRID TEST");
            }
        });*/
        
        removeBook.setOnAction((ActionEvent event) -> {
            controller.removeBook();
        });
        
        refresh.setOnAction((ActionEvent event) -> {
            controller.refresh();
        });
        
        quickSave.setOnAction((ActionEvent event) -> {
            controller.saveToFile();
        });
    }
}


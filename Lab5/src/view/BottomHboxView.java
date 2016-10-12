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
    private Button loanBook;
    private Button refresh;

    BottomHboxView(CollectionOfBooks books, Controller controller) {
        super(30);
        this.books = books;
        this.controller = controller;
        initView(books);
        addEventHandlers(books);
    }
    
    private void initView(CollectionOfBooks books) {
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5, 10, 5, 10));
        
        addBook = new Button("Add Book");
        removeBook = new Button("Remove Book");
        loanBook = new Button("Loan Book");
        refresh = new Button("Refresh");

        this.getChildren().addAll(addBook, removeBook, loanBook, refresh);
    }
    
    private void addEventHandlers(CollectionOfBooks books) {
        
        
        addBook.setOnAction(e -> controller.addBook());
        /*addBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                controller.addBook();
                System.out.println("ADD IN GRID TEST");
            }
        });*/
        
        removeBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                controller.removeBook();
                System.out.println("REMOVE IN GRID TEST");
            }
        });
        
        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                try {
                    controller.refresh();
                } catch (IOException ex) {
                    System.out.println("sd");
                }
            }
        });
        
        
    }
}


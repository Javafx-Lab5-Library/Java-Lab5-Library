/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import model.Author;
import model.Book;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import model.CollectionOfBooks;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Niklas
 */
public class BottomGridPane extends GridPane {
    private CollectionOfBooks books;
    private Controller controller;
    private Button addBook;
    private Button removeBook;
    private Button tempOne;
    private Button tempTwo;

    BottomGridPane(CollectionOfBooks books, Controller controller) {
        this.books = books;
        this.controller = controller;
        initView(books);
        addEventHandlers(books);
    }
    
    private void initView(CollectionOfBooks books) {
        this.setPadding(new Insets(5, 20, 5, 40));
        this.setVgap(15);
        this.setHgap(40);
        addBook = new Button("Add Book");
        removeBook = new Button("Remove Book");
        tempOne = new Button("temp One");
        tempTwo = new Button("temp two");
        //GridPane.setHalignment(addBook, HPos.RIGHT);
        this.add(addBook, 0, 0);
        this.add(removeBook, 1, 0);
        this.add(tempOne, 2, 0);
        this.add(tempTwo, 3, 0);
    }
    
    private void addEventHandlers(CollectionOfBooks books) {
        addBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                controller.addBook();
                System.out.println("IN GRID TEST");
                //books.addBook(new Book("gfhgh", "54", 9, 8, new Author("hj")));
                
            }
            
        });
    }
}


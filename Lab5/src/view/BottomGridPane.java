/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.List;
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
    private Button addBook;
    private Button removeBook;
    private Button tempOne;
    private Button tempTwo;

    BottomGridPane(CollectionOfBooks books) {
        this.books = books;
        
        initView();
    }
    
    private void initView() {
        this.setPadding(new Insets(5, 20, 5, 40));
        this.setVgap(15);
        this.setHgap(40);
        addBook = new Button("Add Book");
        removeBook = new Button("Remove Book");
        tempOne = new Button("temp One");
        tempTwo = new Button("temp two");
        GridPane.setHalignment(addBook, HPos.RIGHT);
        this.add(addBook, 0, 0);
        this.add(removeBook, 1, 0);
        this.add(tempOne, 2, 0);
        this.add(tempTwo, 3, 0);
        
        
    }
}


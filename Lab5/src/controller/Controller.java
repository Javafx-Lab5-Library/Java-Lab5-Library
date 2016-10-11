/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.List;
import java.util.Optional;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.Author;
import model.Book;
import model.CollectionOfBooks;
import view.AddBookView;
import view.CenterTableView;
import view.MainView;

/**
 *
 * @author Niklas
 */
public class Controller {
    private CollectionOfBooks books;
    private MainView mainView;
    private AddBookView addBookView;
    private CenterTableView centerTableView;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    public Controller(MainView mainView, CollectionOfBooks books, CenterTableView centerTableView) {
        this.books = books;
        this.mainView = mainView;
        this.centerTableView = centerTableView;
        
    }
    
    public void addBook() {
        addBookView = new AddBookView(books, this);
    }
    
    public boolean handleInput() {
        addBookView.setBlackLabels();
        List<String> tmp = addBookView.getInfo();
        Boolean error = false;
        Double price = 0.0;
        int edition = 0;
        for (int i = 0; i < 5; i++) {
            if (tmp.get(i).length() <= 0) {
                addBookView.setRedLabels(i);
                error = true;
            }
        }
        
        try {
            edition = Integer.parseInt(tmp.get(2));
            if (edition < 0) {
                addBookView.setRedLabels(2);
                error = true;
            }
        }
        catch(NumberFormatException e) {
            addBookView.setRedLabels(2);
            error = true;
        }
        try {
            price = Double.parseDouble(tmp.get(3));
            if (price < 0) {
                addBookView.setRedLabels(3);
                error = true;
            }
        }
        catch(NumberFormatException e) {
            addBookView.setRedLabels(3);
            error = true;
        }
        if (error) {
            addBookView.showAlert();
            return false;
        }
        else {
            books.addBook(new Book(tmp.get(0), tmp.get(1), edition, price, new Author(tmp.get(4))));
            addBookView.exitStage();
            return true;
        }
    }
    
    public void handleCancel() {
        addBookView.exitStage();
    }
    
    public void removeBook() {
        centerTableView.removeBook();
    }
}

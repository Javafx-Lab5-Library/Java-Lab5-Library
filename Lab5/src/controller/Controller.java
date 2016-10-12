/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import externalfile.SaveAndLoad;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Author;
import model.Book;
import model.CollectionOfBooks;
import view.AddBookView;
import view.CenterTableView;
import view.FileChooserView;
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
    private FileChooserView fileChooserView;
    private SaveAndLoad saveAndLoad;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    public Controller(MainView mainView, CollectionOfBooks books, 
            CenterTableView centerTableView, FileChooserView fileChooserView) {
        this.books = books;
        this.mainView = mainView;
        this.centerTableView = centerTableView;
        this.fileChooserView = fileChooserView;
        saveAndLoad = new SaveAndLoad(books);
        
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
            centerTableView.refresh();
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
    
    public void searchBook(String searchValue, String searched) {
        if ("Title".equals(searchValue))
            centerTableView.setSearchedList(books.searchByTitle(searched));
        else if("ISBN".equals(searchValue)) 
            centerTableView.setSearchedList(books.searchByIsbn(searched));
        else if("Author".equals(searchValue)) 
            centerTableView.setSearchedList(books.searchByAuthor(searched));
        else
            centerTableView.setSearchedList(books.searchByTitle(searched));
            
    }
    
    public void refresh() throws IOException {
        centerTableView.refresh();
    }
        
    public void saveToFile() {
        String path = fileChooserView.saveToFile();
        saveAndLoad.objectOutput(path);
    }
    
    public void loadFromFile() {
        String path = fileChooserView.loadFromFile();
        saveAndLoad.objectInput(path);
        centerTableView.refresh();
    }
}

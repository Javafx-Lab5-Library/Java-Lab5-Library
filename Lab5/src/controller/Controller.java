/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import externalfile.SaveAndLoad;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.Book;
import model.CollectionOfBooks;
import view.AddBookView;
import view.AlertView;
import view.SaveAnimationView;
import view.CenterTableView;
import view.ExitVBoxView;
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
    private ExitVBoxView exitView;
    private Stage stage;
    private Canvas canvas;
    private SaveAnimationView saveAniView;
    private ImageView saveImage;
    private AlertView alertView;
    
    
    public Controller(MainView mainView, CollectionOfBooks books, 
            CenterTableView centerTableView, FileChooserView fileChooserView, 
            Stage stage, Canvas canvas, ImageView saveImage, HBox imageBox) {
        this.books = books;
        this.mainView = mainView;
        this.centerTableView = centerTableView;
        this.fileChooserView = fileChooserView;
        this.saveImage = saveImage;
        this.stage = stage;
        this.canvas = canvas;
        this.saveAniView = new SaveAnimationView(canvas, saveImage, imageBox);
        saveAndLoad = new SaveAndLoad();
        alertView = new AlertView();
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
            alertView.showAlert("Make sure you fill all fields!\n"
                + "Make sure Edition and Price are Positive numbers!");
            return false;
        }
        else {
            String tmpS = tmp.get(4);
            String author = "";
            ArrayList<String> authors = new ArrayList();
            int i = 0;
            for (i = 0; i < tmpS.length(); i++) {
                if (tmpS.charAt(i) != ',') {
                    author += tmpS.charAt(i);
                }
                else {
                    authors.add(author.trim());
                    author = "";
                } 
            }
            if (author.length() != 0)
                authors.add(author.trim());
            
            System.out.println(author.length());
            books.addBook(new Book(tmp.get(0), tmp.get(1), edition, price, authors));
            
            addBookView.exitStage();
            return true;
        }
    }
    
    public void handleAddBookCancel() {
        addBookView.exitStage();
    }
    
    public void removeBook() {
        ArrayList<Book> tmp = centerTableView.removeBook();
        for (Book b : tmp) {
            books.removeBook(b);
        }
    }
    
    public void searchBook(String searchedFor, String searched) {
        if ("Title".equals(searchedFor))
            centerTableView.setSearchedList(books.searchByTitle(searched));
        else if("ISBN".equals(searchedFor)) 
            centerTableView.setSearchedList(books.searchByIsbn(searched));
        else if("Author".equals(searchedFor)) 
            centerTableView.setSearchedList(books.searchByAuthor(searched));
        else
            centerTableView.setSearchedList(books.searchByTitle(searched));
            
    }
    
    public void refresh() {
        saveAniView.startAnimation();
        centerTableView.refresh();
    }
        
    public void exitProgram(){
        exitView = new ExitVBoxView(this);
    }
    
    public void closeWithSaving() {
        saveToFile();
        exitView.close();
        stage.close();
    }
    
    public void closeWithoutSaving() {
        exitView.close();
        stage.close();
    }
    
    public void closeCanceled() {
        exitView.close();
    }
    
    public void saveToFile() {
        String path = fileChooserView.saveToFile();
        if (path != null) {
            if (!saveAndLoad.objectOutput(path, books))
                alertView.showAlert("File did not save!");
            saveAniView.startAnimation();
        }
        else
            alertView.showAlert("File did not save!");
    }
    
    public void saveAsToFile() {
        String path = fileChooserView.saveAsToFile();
        if (path != null) {
            if (!saveAndLoad.objectOutput(path, books))
                alertView.showAlert("File did not save!");
        }
        else
            alertView.showAlert("File did not save!");
    }
        
    public void loadFromFile() {
        String path = fileChooserView.loadFromFile();
        if (path != null) {
            if (saveAndLoad.objectInput(path) != null) {
                books.setBooks(saveAndLoad.objectInput(path));
                centerTableView.refresh();
            }
            else
                alertView.showAlert("File did not load!");
        }
        else 
            alertView.showAlert("File did not load!");
    }
}

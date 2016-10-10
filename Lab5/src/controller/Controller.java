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
import view.MainView;

/**
 *
 * @author Niklas
 */
public class Controller {
    private CollectionOfBooks books;
    private MainView mainView;
    private AddBookView addBookView;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    public Controller(CollectionOfBooks books, MainView mainView) {
        this.books = books;
        this.mainView = mainView;
        
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
    
    public void addBook() {
        // Alert thing
        //addBookView.addBook();
        //addBookView.addBook();
        addBookView = new AddBookView(books, this);
        /*Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Add a new book");
        dialog.setHeaderText("");
        dialog.setContentText("Title of book");
        
        ButtonType asd = new ButtonType("Accept", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(asd, ButtonType.CANCEL);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));
         
        TextField title = new TextField();
        title.setPromptText("Title");
        TextField  isbn = new TextField ();
        isbn.setPromptText("Isbn");
        
        
        grid.add(new Label("Title:"), 0, 0);
        grid.add(title, 1, 0);
        grid.add(new Label("Isbn:"), 0, 1);
        grid.add(isbn, 1, 1);
        
        /*Node loginButton = dialog.getDialogPane().lookupButton(asd);
        loginButton.setDisable(false);

        title.textProperty().addListener((observable, oldValue, newValue) -> {
            loginButton.setDisable(newValue.trim().isEmpty());
        });*/

        /* SHOW TEXT FIELDS 
        dialog.getDialogPane().setContent(grid);
        
        /* SETS TYPING TO 'title field' 
        Platform.runLater(() -> title.requestFocus());
        
        String t;
        String s;
        t = title.getText();
        s = isbn.getText();
        System.out.println("error1");
        /*dialog.setResultConverter(dialogButton -> {
            if (dialogButton == asd) {
                return new (title.getText(), isbn.getText());
                //t = title.getText();
                //s = isbn.getText();
            }
            return null;
        });
        
        System.out.println("error2");
        //Optional<String> result = dialog.showAndWait();
        Optional<String> result = dialog.showAndWait();
        System.out.println("error3");
        if (result.ifPresent()) {
            //result.get();
            System.out.println("error4");
            //System.out.println("tit: " + t + "isb: " + s);
            //System.out.println("Title=" + text.getKey() + ", Isbn=" + text.getValue());
        });
        */
    }
}

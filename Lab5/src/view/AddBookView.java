/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.CollectionOfBooks;

/**
 *
 * @author Niklas
 */
public class AddBookView extends Stage{
    private CollectionOfBooks books;
    private Controller controller;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    private Label titleL;
    private Label isbnL;
    private Label editionL;
    private Label priceL;
    private Label authorL;
    private TextField title;
    private TextField isbn;
    private TextField edition;
    private TextField price;
    private TextField author;

    private Button addBook;
    private Button cancel;
    
    private GridPane grid;
    private HBox hBox;
    private BorderPane border;
    
    public AddBookView(CollectionOfBooks books, Controller controller) {
        super();
        this.books = books;
        this.controller = controller;
        initView();
    }
    
    private void initView() {
        
       // this.initModality(Modality.WINDOW_MODAL);

        initFields();
        clearFields();
        
        grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(5, 20, 5, 20));
        
        grid.add(titleL, 0, 0);
        grid.add(isbnL, 0, 1);
        grid.add(editionL, 0, 2);
        grid.add(priceL, 0, 3);
        grid.add(authorL, 0, 4);
        
        grid.add(title, 1, 0);
        grid.add(isbn, 1, 1);
        grid.add(edition, 1, 2);
        grid.add(price, 1, 3);
        grid.add(author, 1, 4);
        
        hBox = new HBox(5);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.getChildren().addAll(addBook, cancel);

        border = new BorderPane();
        border.setCenter(grid);
        border.setBottom(hBox);
        
        //event handerls
        addEventHandlers();
        System.out.println("ddsfsdfIN AddBookView");
        
        Scene scene = new Scene(border);
        this.setResizable(false);
        this.setTitle("Add a new Book");
        this.setScene(scene);
        //this.requestFocus();
        this.show();
    }
    
    private void initFields() {
        titleL = new Label("Title:");
        isbnL = new Label("Isbn:");
        editionL = new Label("Edition:");
        priceL = new Label("Price:");
        authorL = new Label("Author:");
        setBlackLabels();

        title = new TextField();
        isbn = new TextField();
        edition = new TextField();
        price = new TextField();
        author = new TextField();
        
        title.setPromptText("Type title here");
        isbn.setPromptText("Type isbn here");
        edition.setPromptText("Type edition here");
        price.setPromptText("Type price here");
        author.setPromptText("Type author here");
        
        addBook = new Button("Add Book");
        cancel = new Button("Cancel");
    }
    
    public void clearFields() {
        title.clear();
        isbn.clear();
        edition.clear();
        price.clear();
        author.clear();
    }
    
    public void setBlackLabels() {
        titleL.setTextFill(Color.BLACK);
        isbnL.setTextFill(Color.BLACK);
        editionL.setTextFill(Color.BLACK);
        priceL.setTextFill(Color.BLACK);
        authorL.setTextFill(Color.BLACK);
    }
    
    public void setRedLabels(int index) {
        if (index == 0)
            titleL.setTextFill(Color.RED);
        else if (index == 1)
            isbnL.setTextFill(Color.RED);
        else if (index == 2)
            editionL.setTextFill(Color.RED);
        else if (index == 3)
            priceL.setTextFill(Color.RED);
        else if (index == 4)
            authorL.setTextFill(Color.RED);
    }
    
    
    public void addBook() {
        clearFields();
        this.show(); 
    }
    
    public List<String> getInfo() {
        List<String> tmp = new ArrayList();
        tmp.add(title.getText().trim());
        tmp.add(isbn.getText().trim());
        tmp.add(edition.getText().trim());
        tmp.add(price.getText().trim());
        tmp.add(author.getText().trim());
        
        return tmp;
    }
    
    public void exitStage() {
        clearFields();
        this.hide();
    }
    
    public void showAlert() {
        alert.setHeaderText("");
        alert.setTitle("Alert!");
        alert.setContentText("Make sure you fill all fields!\n"
                + "Make sure Edition and Price are Positive numbers!");
        alert.show();
    }
    
    private void addEventHandlers() {
        addBook.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                //controller.addBook();
                System.out.println("IN AddBookView");
                controller.handleInput();
                //books.addBook(new Book("gfhgh", "54", 9, 8, new Author("hj")));
            }
        });
        
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override 
            public void handle(ActionEvent event) {
                System.out.println("IN AddBookView");
                controller.handleCancel();
                //books.addBook(new Book("gfhgh", "54", 9, 8, new Author("hj")));
            }
        });
    }
    
}

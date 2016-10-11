/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import model.Author;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import model.Book;
import model.CollectionOfBooks;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 *
 * @author Niklas
 */
public class CenterTableView extends TableView {
    private CollectionOfBooks books;
    private TableColumn title;
    private TableColumn isbn;
    private TableColumn edition;   
    private TableColumn price;        
    private TableColumn author;
    
    
    public CenterTableView (CollectionOfBooks books) {
        this.books = books;

                
        books.addBook(new Book("wer", "214124", 3, 4, new Author("qwe")));
        books.addBook(new Book("sdf", "234625", 4, 5, new Author("asd")));
        books.addBook(new Book("xcv", "423454", 6, 6, new Author("zxc")));

        //test = books.getRealList();
        //observBooks = FXCollections.observableArrayList(books.getRealList());
        
        initView();
    }
    
    public void removeBook() {

        ObservableList<Book> booksSelected;

        booksSelected = this.getSelectionModel().getSelectedItems();
        if (booksSelected == null)
            System.out.println("ASDAKSDLASAJS");
 
        for (Book b : booksSelected) {
            this.getItems().remove(b);
        }
        
    }
    
    private void initView() {
        this.setEditable(true);
        title = new TableColumn("Title");
        title.setMinWidth(80);
        isbn = new TableColumn("Isbn");
        isbn.setMinWidth(80);
        edition = new TableColumn("Edition");
        edition.setMinWidth(80);
        price = new TableColumn("Price");
        price.setMinWidth(80);
        author = new TableColumn("Author");
        author.setMinWidth(80);
        
        this.getColumns().addAll(title, isbn, edition, price, author);
        
        title.setCellValueFactory(
            new PropertyValueFactory<Book, String>("title"));
        isbn.setCellValueFactory(
            new PropertyValueFactory<Book, String>("isbn"));
        edition.setCellValueFactory(
            new PropertyValueFactory<Book, Integer>("edition"));
        price.setCellValueFactory(
            new PropertyValueFactory<Book, Double>("price"));
        author.setCellValueFactory(
            new PropertyValueFactory<Book, ArrayList<Author>>("authors"));
        this.setItems(books.getRealList());   

    }    

}

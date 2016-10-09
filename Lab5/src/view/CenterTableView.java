/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import model.Author;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import model.Book;
import model.CollectionOfBooks;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Niklas
 */
public class CenterTableView extends TableView {
    private ObservableList<Book> observBooks;
    private TableColumn title;
    private TableColumn isbn;
    private TableColumn edition;   
    private TableColumn price;        
    private TableColumn author;
    private TableColumn emailCol;
    
    public CenterTableView (CollectionOfBooks books) {
        //ArrayList<Book> test = new ArrayList();
        //LinkedList<Book> test2 = new LinkedList();
        //observBooks = FXCollections.observableArrayList(test2);
        observBooks = FXCollections.observableArrayList(

                
            new Book("qwe", "214124", 3, 4, new Author("qwe")),
            new Book("asd", "234625", 4, 5, new Author("asdasdasd")),
            new Book("zxc", "423454", 6, 6, new Author("zxczxczxczxczxc"))
        );
        
        initView();
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
            new PropertyValueFactory<Book, Author>("author"));
        this.setItems(observBooks);               
    }    
}

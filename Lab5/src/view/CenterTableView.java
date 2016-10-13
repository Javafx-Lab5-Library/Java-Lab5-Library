/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import model.Author;
import java.util.ArrayList;
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
    private CollectionOfBooks books;
    private ObservableList<Book> observBooks;
    private TableColumn title;
    private TableColumn isbn;
    private TableColumn edition;   
    private TableColumn price;        
    private TableColumn author;
    
    
    public CenterTableView (CollectionOfBooks books) {
        this.books = books;

        ArrayList<String> s = new ArrayList();
        s.add("qwe");
        books.addBook(new Book("wer", "214124", 3, 4, s));
        s.add("asd");
        books.addBook(new Book("sdf", "234625", 4, 5, s));
        s.add("zxc");
        books.addBook(new Book("xcv", "423454", 6, 6, s));

        //test = books.getRealList();
        observBooks = FXCollections.observableArrayList(books.getRealList());
        
        initView();
    }
    
    public void removeBook() {
        ObservableList<Book> booksSelected;

        booksSelected = this.getSelectionModel().getSelectedItems();
 
        for (Book b : booksSelected) {
            books.removeBook(b);
            refresh();
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
        refresh();
    }    
    
    public void setSearchedList(ArrayList<Book> tmpBooks) {
        ObservableList<Book> tmpObvBooks;
        tmpObvBooks = FXCollections.observableArrayList(tmpBooks);
        this.setItems(tmpObvBooks);
    }
    
    public void refresh() {
        observBooks = FXCollections.observableArrayList(books.getRealList());
        this.setItems(observBooks);
    }

    
    
}

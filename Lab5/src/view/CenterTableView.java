/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import model.Author;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
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
public class CenterTableView extends TableView implements Observer {
    private CollectionOfBooks books;
    private ObservableList<Book> observBooks;
    private TableColumn title;
    private TableColumn isbn;
    private TableColumn edition;   
    private TableColumn price;        
    private TableColumn author;
    
    
    public CenterTableView (CollectionOfBooks books) {
        this.books = books;

        /*ArrayList<String> s = new ArrayList();
        s.add("qwe");
        books.addBook(new Book("wdser", "214124", 3, 4, s));
        s.add("asd");
        books.addBook(new Book("sdsdf", "234625", 4, 5, s));
        s.add("zxc");
        books.addBook(new Book("xcv", "423454", 6, 6, s));
        */
        initView();
    }
    
    public ArrayList<Book> removeBook() {
        ObservableList<Book> booksSelected;

        booksSelected = this.getSelectionModel().getSelectedItems();
        ArrayList<Book> tmp = new ArrayList();
        for (Book b : booksSelected) {
            tmp.add(b);
            books.removeBook(b);
        }
        return tmp;
    }
    
    private void initView() {
        this.setEditable(true);
        title = new TableColumn("Title");
        title.setMinWidth(100);
        isbn = new TableColumn("Isbn");
        isbn.setMinWidth(80);
        edition = new TableColumn("Edition");
        edition.setMinWidth(80);
        price = new TableColumn("Price");
        price.setMinWidth(80);
        author = new TableColumn("Author");
        author.setMinWidth(128);
        
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
        
        observBooks = FXCollections.observableArrayList(books.getRealList());
        refresh();
        this.setItems(observBooks);
    }    
    
    public void setSearchedList(ArrayList<Book> tmpBooks) {
        ObservableList<Book> tmpObvBooks;
        tmpObvBooks = FXCollections.observableArrayList(tmpBooks);
        this.setItems(tmpObvBooks);
    }
    
    public void refresh() {
        observBooks.removeAll(observBooks);
        observBooks.addAll(books.getRealList());
    }

    @Override
    public void update(Observable o, Object o1) {
        refresh();
    }
    
}

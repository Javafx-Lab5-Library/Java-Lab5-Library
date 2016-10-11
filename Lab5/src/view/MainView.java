/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import model.CollectionOfBooks;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Niklas
 */
public class MainView extends BorderPane {
    private CollectionOfBooks books;
    private Controller controller;
    private BottomHboxView bottomHbox;
    private CenterTableView centerTable;
    
    public MainView(CollectionOfBooks books) {
        this.books = books;
        initView();
    }
    
    private void initView() {
        centerTable = new CenterTableView(books);
        controller = new Controller(this, books, centerTable);
        bottomHbox = new BottomHboxView(books, controller);
        this.setPrefSize(500, 350);
        this.setCenter(centerTable);
        this.setBottom(bottomHbox);
        
    }
}

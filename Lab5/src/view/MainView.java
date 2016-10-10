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
    private BottomGridPane bottomGrid;
    private CenterTableView centerTable;
    
    public MainView(CollectionOfBooks books) {
        this.books = books;
        controller = new Controller(books, this);
        initView();
    }
    
    private void initView() {
        bottomGrid = new BottomGridPane(books, controller);
        centerTable = new CenterTableView(books);
        this.setPrefSize(500, 350);
        this.setCenter(centerTable);
        this.setBottom(bottomGrid);
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.CollectionOfBooks;
import javafx.scene.layout.BorderPane;

/**
 *
 * @author Niklas
 */
public class MainView extends BorderPane {
    private CollectionOfBooks books;
    private BottomGridPane bottomGrid;
    private CenterTableView centerTable;
    
    public MainView(CollectionOfBooks books) {
        this.books = books;
        initView(books);
    }
    
    private void initView(CollectionOfBooks books) {
        bottomGrid = new BottomGridPane(books);
        centerTable = new CenterTableView(books);
        this.setPrefSize(500, 350);
        this.setCenter(centerTable);
        this.setBottom(bottomGrid);
        
    }
}

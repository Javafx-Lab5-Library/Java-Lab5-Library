/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Niklas
 */
public class MenuFieldView extends MenuBar{
    private Controller controller;
    private Menu menu;
    private MenuItem save;
    private MenuItem load;
    private MenuItem exit;
    
    public MenuFieldView(Controller controller) {
        super();
        this.controller = controller;
        initView();
    }

    private void initView() {
        Menu menu = new Menu("File");
        save = new MenuItem("Save");
        load = new MenuItem("Load");
        exit = new MenuItem("Exit");
        menu.getItems().add(save);
        menu.getItems().add(load);
        menu.getItems().add(exit);
        this.getMenus().addAll(menu);
        
        addActionHandlers();
    }
    
    private void addActionHandlers() {
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.saveToFile();
            }
        });
        
 
        
        
        
        
        load.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.loadFromFile();
            }
        });
        
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
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
    
    public MenuFieldView(Controller controller) {
        super();
        this.controller = controller;
        initView();
    }

    private void initView() {
        Menu menu = new Menu("File");
        menu.getItems().add(new MenuItem("Save"));
        menu.getItems().add(new MenuItem("Load"));
        menu.getItems().add(new MenuItem("Exit"));
        
        this.getMenus().addAll(menu);
    }
    
    
}

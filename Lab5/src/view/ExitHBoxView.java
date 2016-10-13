/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 *
 * @author Admin
 */
public class ExitHBoxView extends HBox{
    private Button yesButton;
    private Button noButton;
    private Button cancelButton;
    
    public ExitHBoxView(Controller controller){
        super(10);
        
        initView(controller);
    }

    private void initView(Controller controller) {
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5, 10, 5, 10));
        
        yesButton = new Button("Save");
        noButton = new Button("Don't save");
        cancelButton = new Button("Cancel");

        this.getChildren().addAll(yesButton, noButton, cancelButton);
        
        addHandlers(controller);
    }
    
    private void addHandlers(Controller controller) {
        yesButton.setOnAction(e ->{
            controller.closeWithSaving();
        });
        noButton.setOnAction(e ->{
            controller.closeWithoutSaving();
        });
        cancelButton.setOnAction(e ->{
            controller.closeCanceled();
        });
    }
    
    
}

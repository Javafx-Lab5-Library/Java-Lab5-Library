/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
    
    public ExitHBoxView(){
        super(10);
        
        initView();
    }

    private void initView() {
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(5, 10, 5, 10));
        
        yesButton = new Button("Yes");
        noButton = new Button("No");
        cancelButton = new Button("Cancel");
        
        
        yesButton.setOnAction(e ->{
            System.out.println("jag ska spara och lämna programmet");
        });
        noButton.setOnAction(e ->{
            System.out.println("jag ska lämna men inte spara");
        });
        cancelButton.setOnAction(e ->{
            System.out.println("jag ska varken spara eller lämna programmet");
        });

        this.getChildren().addAll(yesButton, noButton, cancelButton);
    }
}

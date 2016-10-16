/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javafx.scene.control.Alert;

public class AlertView {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public void showAlert(String message) {
        alert.setHeaderText("");
        alert.setTitle("Alert!");
        alert.setContentText("Make sure you fill all fields!\n"
                + "Make sure Edition and Price are Positive numbers!");
        alert.show();
    }
}

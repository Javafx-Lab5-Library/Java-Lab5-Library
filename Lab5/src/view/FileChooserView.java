/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *
 * @author Niklas
 */
public class FileChooserView {
    private Stage stage;
    private String path;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    private FileChooser fileChooser;
    
    public FileChooserView(Stage stage) {
        this.stage = stage;
        path = "";
        initView();
    }
    
    private void initView() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
    }
    
    public String saveToFile() {
        if (path.length() > 0)
            return path;
        else
            return saveAsToFile();
    }
    
    public String saveAsToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SER", "*.ser"));
        try {
            File file = fileChooser.showSaveDialog(stage);
            path = file.getPath();
            return path;
        }
        catch (Exception e) {
            path = "";
            return null;
        }
    }
    
    public String loadFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SER", "*.ser"));
        try {
            File file = fileChooser.showOpenDialog(stage);
            path = file.getPath();
            return path;
        }
        catch (Exception e) {
            path = "";
            return null;
        }
    }   
    
    public void saveAlert() {
        alert.setHeaderText("");
        alert.setTitle("Alert!");
        alert.setContentText("File did not save!\n");
        alert.show();
    }
    
    public void loadAlert() {
        alert.setHeaderText("");
        alert.setTitle("Alert!");
        alert.setContentText("File did not load!\n");
        alert.show();
    }
}

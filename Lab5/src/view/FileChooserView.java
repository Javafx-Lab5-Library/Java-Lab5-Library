/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Niklas
 */
public class FileChooserView {
    private Stage stage;

    //private File file;
    private FileChooser fileChooser;
    
    public FileChooserView(Stage stage) {
        this.stage = stage;
        initView();
    }
    
    private void initView() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
    }
    
    public String saveToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        File file = fileChooser.showSaveDialog(stage);
           
        String path = file.getPath();
        return path;
    }
    
    public String loadFromFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File("C:\\Users\\Niklas\\Desktop"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            String path = file.getPath();
            System.out.println("path: " + path);
            return path;
        }
        return null;
    }        
}

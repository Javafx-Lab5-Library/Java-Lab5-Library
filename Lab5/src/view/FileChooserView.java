/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CollectionOfBooks;

/**
 *
 * @author Niklas
 */
public class FileChooserView {
    private CollectionOfBooks books;
    private Stage stage;
    //private File file;
    private FileChooser fileChooser;
    
    public FileChooserView(CollectionOfBooks books, Stage stage) {
        this.stage = stage;
        this.books = books;
        
        initView();
    }
    
    private void initView() {
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        
    }
    
    public void readFile() throws FileNotFoundException, IOException {
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            // var man valt
            String path = file.getPath();
            
            BufferedReader in = null;
            in = new BufferedReader(new FileReader(path));
                    
            
            ObjectInputStream inputFile = null;
            
            try {
                    inputFile = new ObjectInputStream(new FileInputStream(path));	
                    books.readFromFile(inputFile);
            }
            catch (Exception e) {
                    System.out.println("Could not read file into object");
            }

        }
    }
    
    public void saveFile() {
        File file = fileChooser.showSaveDialog(stage);
           
        // var man valt
            String path = file.getPath();
            

                    
            
            ObjectOutputStream outputFile = null;
            
            try {
                    outputFile = new ObjectOutputStream(new FileOutputStream(path));	
                    books.writeToFile(outputFile);
            }
            catch (Exception e) {
                    System.out.println("Could not read file into object");
            }

        }
        
}

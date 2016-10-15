/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package externalfile;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import model.Book;
import model.CollectionOfBooks;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Niklas
 */
public class SaveAndLoad {

	public SaveAndLoad() {

	}

        public ArrayList<Book> objectInput(String path) {
            ObjectInputStream inputFile = null;
            try {
                    inputFile = new ObjectInputStream(new FileInputStream(path));	
                    return readFromFile(inputFile);
            }
            catch (Exception e) {
                    return null;
            }
        }
        
        public boolean objectOutput(String path, CollectionOfBooks library) {         
            ObjectOutputStream outputFile = null;
            try {
                    outputFile = new ObjectOutputStream(new FileOutputStream(path));	
                    saveToFile(outputFile, library);
                    return true;
            }
            catch (Exception e) {
                    return false;
            }
        }      

	public ArrayList<Book> readFromFile(ObjectInputStream input) throws IOException, ClassNotFoundException {
		return (ArrayList<Book>) input.readObject();
	}

	public void saveToFile(ObjectOutputStream output, CollectionOfBooks library) throws IOException {
		output.writeObject(library.getRealList());
	}

    
}

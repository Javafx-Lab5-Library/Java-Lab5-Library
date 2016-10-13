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
        private CollectionOfBooks books;
    
	public SaveAndLoad(CollectionOfBooks books) {
            this.books = books;
	}

        public void objectInput(String path) {
            ObjectInputStream inputFile = null;
            try {
                    inputFile = new ObjectInputStream(new FileInputStream(path));	
                    books.setBooks(readFromFile(inputFile));
            }
            catch (Exception e) {
                    System.out.println("Could not read file into object");
            }
        }
        
        public void objectOutput(String path) {         
            ObjectOutputStream outputFile = null;
            try {
                    System.out.println("1");
                    outputFile = new ObjectOutputStream(new FileOutputStream(path));	
                    System.out.println("2");
                    
                    saveToFile(outputFile);
            }
            catch (Exception e) {
                    System.out.println("Could not read file into object");
            }
        }      

	public ArrayList<Book> readFromFile(ObjectInputStream input) throws IOException, ClassNotFoundException {
		return (ArrayList<Book>) input.readObject();
	}

	public void saveToFile(ObjectOutputStream output) throws IOException {
		output.writeObject(books.getRealList());
	}

    
}

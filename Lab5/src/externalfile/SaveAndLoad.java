/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package externalfile;

import model.Book;
import model.CollectionOfBooks;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

/**
 *
 * @author Niklas
 */
public class SaveAndLoad {
    
	public SaveAndLoad() {
		
	}

	/**
	* Reads from a file to load a list of <code>Book</code> into a new <code>CollectionOfBooks</code>.
	* @param input Contains an ObjectInputStream of the file to be read.
	* @throws IOException If a file is not found to read from.
	* @throws ClassNotFoundException If a class is not found to read in to.
	*/
	public LinkedList<Book> readFromFile(ObjectInputStream input) throws IOException, ClassNotFoundException {
		return (LinkedList<Book>) input.readObject();
	}

	/**
	* Writes to a file to save a list of <code>Book</code> when program is closed.
	* @param output Contains an ObjectOutputStream of the file that will be written to.
	* @throws IOException If a file is not found to write to.
	*/
	public void writeToFile(ObjectOutputStream output, CollectionOfBooks books) throws IOException {
		output.writeObject(books);
	}

    
}

package model;

import java.util.ArrayList;
import java.io.Serializable;
import java.util.Comparator;
import javafx.beans.property.SimpleStringProperty;


/**
 * A <code>Book</code> has a <code>title</code>, <code>isbn</code>, <code>edition</code>, 
 * <code>price</code> and a List of <code>Author</code>s. 
 * <code>Book</code> implements <code>Serializable</code> in order to save object in a file.
 * 
 * @author Niklas Ålander
 * @version 1.0
 */
public class Book implements Serializable {
	private String title;
	private String isbn;
	private int edition;
	private double price;
	private ArrayList<Author> authors;

	/**
	* Creates a new <code>Book</code>.
	* @param title The title.
	* @param isbn The isbn.
	* @param edition The edition.
	* @param price The price.
	* @param author The first author.
	*/
	public Book(String title, String isbn, int edition, double price, Author author) {
		//this.title = new SimpleStringProperty(title);
		//this.isbn = new SimpleStringProperty(isbn);
                this.title = title;
                this.isbn = isbn;
		this.edition = edition;
		this.price = price;
		authors = new ArrayList<Author>();
		authors.add(author);
                authors.add(new Author("another"));
	}

	/**
	* Returns the <code>title</code> of the <code>Book</code>.
	* @return Returns the <code>title</code> of the <code>Book</code>.
	*/
	public String getTitle() {
            return title;
	}
        
        public void setTitle(String title) {
            this.title = title;
        }

	/**
	* Returns the <code>isbn</code> of the <code>Book</code>.
	* @return Returns the <code>isbn</code> of the <code>Book</code>.
	*/
	public String getIsbn() {
            return isbn;
	}
        
        public void setIsbn(String isbn) {
            this.isbn = isbn;
        }

	/**
	* Returns the <code>edition</code> of the <code>Book</code>.
	* @return Returns the <code>edition</code> of the <code>Book</code>.
	*/
	public int getEdition() {
		return edition;
	}
        
        public void setEdition(int edition) {
            this.edition = edition;
        }

	public double getPrice() {
		return price;
	}
        
        public void setPrice(double price) {
            this.price = price;
        }

	/**
	* Creates a <code>clone</code> of the list of <code>Author</code>s 
	* of the <code>Book</code> to be returned.
	* @return Creates a <code>clone</code> of the list of 
	* <code>Author</code>s of the <code>Book</code> to be returned.
	*/
	public ArrayList<Author> getsAuthors() {
		return (ArrayList<Author>) authors.clone();
	}
        
	/**
	* Creates a <code>String</code> of the list of <code>Author</code>s 
	* of the <code>Book</code> to be returned.
	* @return Creates a <code>String</code> of the list of 
	* <code>Author</code>s of the <code>Book</code> to be returned.
	*/
        public String getAuthors() {
            String tmp = new String("");
            for (Author a : authors) {
                tmp += a.getName() + ", ";
            }
            return tmp;
        }

	/**
	* Creates a new <code>Author</code> and adds it to the list.
	* @param name The name of the new <code>Author</code>.
	*/
	public void addAuthor(String name) {
		authors.add(new Author(name));
	}

	/**
	* Creates a new <code>Author</code> and adds it to the list.
	* @param author The new <code>Author</code>.
	*/
	public void addAuthor(Author author) {
		authors.add(author);
	}

	/**
	* Creates a text <code>String</code> with information
	* about the <code>Book</code>.
	* @return Returns a text <code>String</code> with information
	* about the <code>Book</code>.
	*/
	public String toString() {
		String text = new String("");
		text += "Title: " + title;
		text += "\nISBN: " + isbn;
		text += "\nEdition: " + edition;
		text += "\nPrice: " + price;
		text += "\nAuthor(s): ";
		for (Author a : authors) {
			text += a.toString() + ", ";
		}
		return text;
	}
}
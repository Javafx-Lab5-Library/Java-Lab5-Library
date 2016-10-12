package model;



import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 * A CollectionOfBooks contains a ArrayList of <code>Book</code> objects.
 * The list can be manipulated by adding and removing <code>Book</code> objects.
 * 
 * @author Niklas Ålander
 * @version 1.0
 */
public class CollectionOfBooks {
	//private ArrayList<Book> books;
        private ArrayList<Book> books;

	/**
	* Constructs a new ArrayList of <code>Book</code> objects.
	*/
	public CollectionOfBooks() {
		books = new ArrayList<Book>();
                //books = FXCollections.observableArrayList();
	}

	/**
	* Constructs a ArrayList of <code>Book</code> objects.
	* @param books The new list of <code>Book</code>.
	*/
	/*public CollectionOfBooks(ArrayList<Book> books) {
		this.books = books;
	}*/

	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}

	public ArrayList<Book> getList() {
		return (ArrayList<Book>) books.clone();
	}

	public ArrayList<Book> getRealList() {
		return (ArrayList<Book>) books;
	}

	/**
	* Return the size of the list.
	* @return Returns the size of list.
	*/
	public int getSize() {
		return books.size();
	}

	/**
	* Adds a new <code>Book</code> to the list.
	* @param book The new <code>Book</code> to add to the list.
	*/
	public void addBook(Book book) {
		books.add(book);
	}

	/**
	* Removes a book from the list if given index is within the list and returns
	* <code>true</code> or <code>false</code> whether it was successful.
	* @param index The place in the list which specifies which book to remove.
	* @return Return <code>true</code> if the book was successfully removed,
	* otherwise return <code>false</code>.
	*/
	public boolean removeBook(int index) {
		if (index >= 0 && index < books.size()) {
			books.remove(index);
			return true;
		}
		else
			return false;
	}

	/**
	* Removes a book from the list if given index is within the list and returns
	* removed book or <code>null</code> is it was unsuccessful.
	* @param index The place in the list which specifies which book to remove.
	* @return If the book was successfully removed, return the book, 
	* otherwise return <code>null</code>.
	*/
	public Book removeAndGetBook(int index) {
		if (index >= 0 && index < books.size())
			return books.remove(index);
		else
			return null;
	}

	/**
	* Changes the name of an <code>Author</code>.
	* @param author The <code>Author</code> whos name is to be changed.
	* @param name The new name of specified <code>Author</code>.
	*/
	public void changeAuthorName(Author author, String name) {
		author.setName(name);
	}

	/**
	* Adds an <code>Author</code> to a <code>Book</code> object.
	* @param index The index in the list of books that will gain an <code>Author</code>.
	* @param name The name of the new <code>Author</code>.
	* @return Return <code>true</code> if the <code>Author</code> was successfully added,
	* otherwise return <code>false</code>.
	*/
	public boolean addAuthor(int index, String name) {
		if (index >= 0 && index < books.size()) {
			Author tmpAuthor = findAuthor(name);
			if (tmpAuthor != null) {
				books.get(index).addAuthor(tmpAuthor);
				return true;
			}
			else {
				books.get(index).addAuthor(name);
				return true;
			}
		}
		else 
			return false;
	}

	/**
	* Searches all books to find an <code>Author</code> to return.
	* @param name The name of the <code>Author</code> to be found.
	* @return If an <code>Author</code> is found return the <code>Author</code>,
	* otherwise return <code>null</code>.
	*/
	public Author findAuthor(String name) {
		Author tmpAuthor = new Author(name);
		for (Book b : books) {
			ArrayList<Author> tmpList = b.getsAuthors();
			for (Author a : tmpList) {
				if (a.compareTo(tmpAuthor) == 0) {
					return a;
				}
			}
		}
		return null;
	}

	/**
	* Searches for books by its <code>isbn</code>.
	* If a book's <code>isbn</code> contains the search word, the book 
	* will be added to a list which will be returned.
	* @param isbn The <code>isbn</code> that is being searched.
	* @return Returns the list of books that had matching isbns.
	*/
	public ArrayList<Book> searchByIsbn(String isbn) {
		ArrayList<Book> tmpBookList = new ArrayList<Book>();
		CharSequence tmpIsbn = isbn.toUpperCase();
		for (Book b : books) {
			if (b.getIsbn().toUpperCase().contains(tmpIsbn))
				tmpBookList.add(b);
		}
		Collections.sort(tmpBookList, new IsbnSort());
		return tmpBookList;
	}

	/**
	* Searches for books by its <code>title</code>.
	* If a book's <code>title</code> contains the search word, the book 
	* will be added to a list which will be returned.
	* @param title The <code>title</code> that is being searched.
	* @return Returns the list of books that had matching titles.
	*/
	public ArrayList<Book> searchByTitle(String title) {
		ArrayList<Book> tmpBookList = new ArrayList<Book>();
		CharSequence tmpTitle = title.toUpperCase();
		for (Book b : books) {
			if (b.getTitle().toUpperCase().contains(tmpTitle))
				tmpBookList.add(b);
		}
		Collections.sort(tmpBookList, new TitleSort());
		return tmpBookList;
	}

	/**
	* Searches for books by its <code>Author</code>.
	* If a book's <code>Author</code> contains the search word, the book 
	* will be added to a list which will be returned.
	* @param name The <code>Author</code> that is being searched.
	* @return Returns the list of books that had matching Authors.
	*/
	public ArrayList<Book> searchByAuthor(String name) {
		ArrayList<Book> tmpBookList = new ArrayList<Book>();
		CharSequence tmpName = name.toUpperCase();
		Author tmpAuthor = new Author(name);
		for (Book b : books) {
			ArrayList<Author> tmpList = b.getsAuthors();
			for (Author a : tmpList) {
				if (a.getName().toUpperCase().contains(tmpName)) {
					tmpBookList.add(b);
					break;
				}
			}
		}
		Collections.sort(tmpBookList, new AuthorSort());
		return tmpBookList;
	}

	/**
	* Reads from a file to load a list of <code>Book</code> into a new <code>CollectionOfBooks</code>.
	* @param input Contains an ObjectInputStream of the file to be read.
	* @throws IOException If a file is not found to read from.
	* @throws ClassNotFoundException If a class is not found to read in to.
	*/
	public void readFromFile(ObjectInputStream input) throws IOException, ClassNotFoundException {
		books = (ArrayList<Book>) input.readObject();
	}

	/**
	* Writes to a file to save a list of <code>Book</code> when program is closed.
	* @param output Contains an ObjectOutputStream of the file that will be written to.
	* @throws IOException If a file is not found to write to.
	*/
	public void writeToFile(ObjectOutputStream output) throws IOException {
		output.writeObject(books);
	}

	/**
	* Creates a text <code>String</code> with information of every 
	* <code>Book</code> is put together. 
	* @return Returns a text <code>String</code> with information
	* of every <code>Book</code> in the list.
	*/
	public String toString() {
		String text = new String("\n");
		int i = 0;
		for (Book b : books) {
			text += "\nIndex: " + i + "\n" + b.toString() + "\n";
			i += 1;
		}
		return text;
	}
}
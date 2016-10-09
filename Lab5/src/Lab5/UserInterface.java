import Lab5.model.CollectionOfBooks;
import Lab5.model.Author;
import Lab5.model.Book;
import Lab5.externalfile.SaveAndLoad;



import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;

public class UserInterface {

	private String fileName;
	private SaveAndLoad saveAndLoad;
	
	public void runInterface() throws ClassNotFoundException, IOException {
		CollectionOfBooks books = new CollectionOfBooks();
		fileName = new String("");

		readFromFile(books);

		menu(books);

		writeToFile(books);
	}

	public void menu(CollectionOfBooks books) throws FileNotFoundException {
		int choice = 1;
		String tmp;
		while (choice != 0) {
			System.out.println("\n----Choose an action----");
			printOptions();		
			do {
				tmp = getInputString();
				choice = Integer.parseInt(tmp);
			} while (choice < 0 || choice > 5);
			
			switch (choice) {
				case 1:
					addBook(books);
					break;
				case 2:
					printBooks(books);
					break;
				case 3:
					removeBook(books);
					break;
				case 4:
					search(books);
					break;
				case 5:
					changeAuthorName(books);
					break;
				case 0:
					break;
				default:
					break;
			}
		}
	}

	public void changeAuthorName(CollectionOfBooks books) {
		String name;
		String newName;
		String tmp;
		int keepTesting = 1;
		printBooks(books);
		
		while (keepTesting == 1) {
			System.out.println("Enter the name of the author you want to change");
			name = getInputString();
			Author tmpAuthor = books.findAuthor(name);
			if (tmpAuthor != null) {
				System.out.println("Author " + tmpAuthor.getName() 
					+ " found!\nType the new name of the author");
				newName = getInputString();
				books.changeAuthorName(tmpAuthor, newName);
				keepTesting = 2;
				System.out.println("Author " + name + " is now named: " + newName);
			}
			else {
				System.out.println("Author not found. Would you like to search again?"
				 + "\nYes(1)  No(2)");
				do {
					tmp = getInputString();
					keepTesting = Integer.parseInt(tmp);
				} while (keepTesting < 1 || keepTesting > 2);
			}
		}
	}

	public void addBook(CollectionOfBooks books) {
		String title;
		String isbn = "";
		int edition = 0;
		double price = 0;
		String name;

		System.out.println("Enter the title of the book");
		title = getInputString();
		System.out.println("Enter the isbn of the book (only integers)");
		isbn = getInputString();
		System.out.println("Enter the edition of the book (only integers)");
		String tmp = getInputString();
		edition = Integer.parseInt(tmp);
		System.out.println("Enter the price ($) of the book (only integers)");
		tmp = getInputString();
		price = Double.parseDouble(tmp);

		System.out.println("Enter the name of the Author");
		name = getInputString();
		Author author = books.findAuthor(name);
		if (author == null)
			author = new Author(name);
		books.addBook(new Book(title, isbn, edition, price, author));
		System.out.println("Would you like to add another Author?\nYes(1)  No(2)");
		
		int moreAuthors;
		do {
			tmp = getInputString();
			moreAuthors = Integer.parseInt(tmp);
		} while (moreAuthors < 1 || moreAuthors > 2);
		while (moreAuthors == 1) {
			addAuthor(books, books.getSize() - 1);
			System.out.println("Would you like to add another Author?\nYes(1)  No(2)");
			do {
				tmp = getInputString();
				moreAuthors = Integer.parseInt(tmp);
			} while (moreAuthors < 1 || moreAuthors > 2);
		}
	}

	public void removeBook(CollectionOfBooks books) {
		System.out.println("\nWhich book would you like to remove?");
		System.out.println("Enter the index of the book to be removed");
		printBooks(books);
		System.out.printf("Index: ");
		String tmp;
		int index;
		do {
			tmp = getInputString();
			index = Integer.parseInt(tmp);
		} while (index < 0 || index >= books.getSize());
		if (books.removeBook(index))
			System.out.println("Book successfully removed!");
		else 
			System.out.println("Could not remove Book");
	}

	public void addAuthor(CollectionOfBooks books, int index) {
		String name;
		System.out.println("\nEnter the name of the Author");
		name = getInputString();

		if (books.addAuthor(index, name))
			System.out.println("Author successfully added!");
		else 
			System.out.println("Could not add Author");
	}

	public void search(CollectionOfBooks books) {
		System.out.println("\n----Choose how you want to search----");
		searchOptions();
		int choice = 1;
		String tmp;
		do {
			tmp = getInputString();
			choice = Integer.parseInt(tmp);
		} while (choice < 0 || choice > 3);
		switch (choice) {
			case 1:
				getBooksByTitle(books);
				break;
			case 2:
				getBooksByIsbn(books);
				break;
			case 3:
				getBooksByAuthor(books);
				break;
			case 0:
				break;
			default:
				break;
		}
	}

	public void getBooksByTitle(CollectionOfBooks books) {
		System.out.println("\nType the title of the book");
		String title = getInputString();
		CollectionOfBooks tmpBooks = new CollectionOfBooks(books.searchByTitle(title));
		System.out.println("\n----Search resuslts----" + tmpBooks.toString());
	}
	
	public void getBooksByIsbn(CollectionOfBooks books) {
		System.out.println("\nType the ISBN of the book");
		String isbn = getInputString();
		CollectionOfBooks tmpBooks = new CollectionOfBooks(books.searchByIsbn(isbn));
		System.out.println("\n----Search resuslts----" + tmpBooks.toString());
	}

	public void getBooksByAuthor(CollectionOfBooks books) {
		System.out.println("\nType the name of the Author");
		String author = getInputString();
		CollectionOfBooks tmpBooks = new CollectionOfBooks(books.searchByAuthor(author));
		System.out.println("\n----Search resuslts----" + tmpBooks.toString());
	}

	public void printOptions() {
		System.out.println("(1) Add a new book!");
		System.out.println("(2) Print all books!");
		System.out.println("(3) Remove a book from the collection!");
		System.out.println("(4) Search for a book in the collection!");
		System.out.println("(5) Change an authors name!");
		System.out.println("(0) Exit and save collection!");
	}

	public void searchOptions() {
		System.out.println("(1) Search by title!");
		System.out.println("(2) Search by ISBN!");
		System.out.println("(3) Search by Author!");
		System.out.println("(0) Exit and save collection!");
	}

	public void printBooks(CollectionOfBooks books) {
		System.out.println("\n----All books in the collection----" + books.toString());
	}

	public String getInputString() {
		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}

	public void readFromFile(CollectionOfBooks books) throws ClassNotFoundException, IOException {
		System.out.println("\nType the name of the file you would like to read from");
		fileName = getInputString();
		ObjectInputStream inputFile = null;
		try {
			inputFile = new ObjectInputStream(new FileInputStream(fileName));
			try {
				//books.setBooks(saveAndLoad.readFromFile(inputFile));	
                                books.readFromFile(inputFile);
                        }
			catch (Exception e) {
				System.out.println("Could not read file into object");
			}
		}
		catch (Exception e) {
			System.out.println("Could not find file " + fileName
				+ "\nCreating new file!");
		}
		finally {
			if (inputFile != null) {
				inputFile.close();
				System.out.println("Successfully read from " + fileName);
			}
		}
	}

	public void writeToFile(CollectionOfBooks books) throws IOException {
		ObjectOutputStream output = null;
		try {
			output = new ObjectOutputStream(new FileOutputStream(fileName));
			try {
				books.writeToFile(output);
			}
			catch (Exception e) {
				System.out.println("Could not write to file " + fileName);
			}
		}
		catch (Exception e) {
			System.out.println("Could not find file " + fileName + "to write to");
			
		}
		finally {
			if (output != null) {
				output.close();
				System.out.println("Successfully wrote to " + fileName);
			}
		}	
	}
}
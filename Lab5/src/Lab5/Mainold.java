import java.io.IOException;

/**
 * Creates a <code>UserInterface</code> objcet and runs 
 * <code>runInterface</code> to start the program. 
 * 
 * @author Niklas Ålander
 * @version 1.0
 */
public class Mainold {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		UserInterface user = new UserInterface();
		user.runInterface();
	}
}
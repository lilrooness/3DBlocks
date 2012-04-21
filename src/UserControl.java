import org.lwjgl.input.Keyboard;

public class UserControl {
	
	private final static int FORWARD = Keyboard.KEY_W;
	private final static int BACK = Keyboard.KEY_S;
	private final static int RIGHT = Keyboard.KEY_D;
	private final static int LEFT = Keyboard.KEY_A;
	
	/**
	 * Creates new instance of UserControl
	 */
	public UserControl(){
	}
	
	/**
	 * Checks if user is pressing the forward key
	 * @return true if being pressed, false otherwise
	 */
	public boolean isForwardPressed(){
		 return Keyboard.isKeyDown(FORWARD);
	}
	
	/**
	 * Checks if user is pressing the forward key
	 * @return true if being pressed, false otherwise
	 */
	public boolean isBackPressed(){
		 return Keyboard.isKeyDown(BACK);
	}
	
	/**
	 * Checks if user is pressing the forward key
	 * @return true if being pressed, false otherwise
	 */
	public boolean isRightPressed(){
		 return Keyboard.isKeyDown(RIGHT);
	}
	
	/**
	 * Checks if user is pressing the forward key
	 * @return true if being pressed, false otherwise
	 */
	public boolean isLeftPressed(){
		 return Keyboard.isKeyDown(LEFT);
	}
}

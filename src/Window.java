import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;


public class Window {
	
	
	private int width;
	private int height;
	private float angle;
	
	private UserControl userKeyboard;
	private Camera camera;
	
	private int[][] map= {
			{1,0,1,0},
			{0,1,0,1},
			{1,0,1,0},
			{0,1,0,1}
		};
	
	private ArrayList<Block> blocks;
	
	
	/**
	 * Constructor creates a new Display
	 * @param width
	 * @param height
	 */
	public Window(int width, int height){
		this.width = width;
		this.height = height;

		
		blocks = new ArrayList<Block>(map.length + map[0].length + 1);
		
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			JOptionPane.showMessageDialog(null, "Could not create display (see stack trace)");
			e.printStackTrace();
		}
		
		userKeyboard = new UserControl();
		camera = new Camera();
	}
	
	/**
	 * Performs rendering in a loop while the window is not closed
	 */
	public void renderLoop(){
		initLights();
		ImageBank.texInit();
		
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthFunc(GL11.GL_LEQUAL);
		GL11.glHint(GL11.GL_PERSPECTIVE_CORRECTION_HINT, GL11.GL_NICEST);
		GLU.gluPerspective(45, width/height, 1, 100);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glViewport(0, 0, width, height);
		
		initBlocks();
		
		while(!Display.isCloseRequested()){
			GL11.glLoadIdentity();
			
			camera.moveCamera();
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPushMatrix();
			GL11.glTranslatef(0, -0.5f, -8);
		
			renderBlocks();
			
			GL11.glPopMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			
			
			Display.update();
			
			move();
		}
		Display.destroy();
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	
	/**
	 * Moves the camera
	 */
	public void move(){
		
		if(userKeyboard.isRightPressed()){
			camera.shiftRight();
		}
		
		if(userKeyboard.isLeftPressed()){
			camera.shiftLeft();
		}
		
		if(userKeyboard.isForwardPressed()){
			camera.moveForward();
		}
		
		if(userKeyboard.isBackPressed()){
			camera.moveBack();
		}
	}
	
	/**
	 * Initializes the blocks
	 */
	public void initBlocks(){
		for (int i = 0; i < map.length; i++){
			for (int j = 0; j < map[0].length; j++){
				if (map[i][j] == 1){
					blocks.add(new Block(i * 3, 0, j * 3, 2));
				}
			}
		}
	}
	
	/**
	 * Renders the blocks
	 */
	public void renderBlocks(){
		for(Block block : blocks){
			block.renderObject();
		}
	}
	
	public void initLights(){
		GL11.glShadeModel(GL11.GL_SMOOTH);
		float[] lightAmbient = {0.5f, 0.5f, 0.5f, 1.0f};
		float[] lightDiffuse = { 1.0f, 1.0f, 1.0f, 1.0f };
		float[] lightPosition = { 1.0f, 1.0f, 2.0f, 1.0f };
		
		ByteBuffer temp = ByteBuffer.allocateDirect(16);
        temp.order(ByteOrder.nativeOrder());
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_AMBIENT, (FloatBuffer)temp.asFloatBuffer().put(lightAmbient).flip());              // Setup The Ambient Light
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_DIFFUSE, (FloatBuffer)temp.asFloatBuffer().put(lightDiffuse).flip());              // Setup The Diffuse Light
        GL11.glLight(GL11.GL_LIGHT1, GL11.GL_POSITION,(FloatBuffer)temp.asFloatBuffer().put(lightPosition).flip());
		
		GL11.glEnable(GL11.GL_LIGHT1);
	}
}

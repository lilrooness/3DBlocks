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


public class Window{
	
	private int width;
	private int height;
	private float angle = 0;
	
	private boolean forward;
	private boolean back;
	private boolean left;
	private boolean right;
	
	private int[][] map = {
			{1,0,1,0},
			{0,1,0,1},
			{1,0,1,0},
			{0,1,0,1}
	};
	
	private ArrayList<Block> blocks;
	
	float x = 5;
	float y = 0;
	float z = 0;
	float camAngle = 0;
	float lx;
	float lz;
	/**
	 * Constructor creates a new Display
	 * @param width
	 * @param height
	 */
	public Window(int width, int height){
		this.width = width;
		this.height = height;
		blocks = new ArrayList<Block>();
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
		} catch (LWJGLException e) {
			JOptionPane.showMessageDialog(null, "Could not create display (see stack trace)");
			e.printStackTrace();
		}
	}
	
	/**
	 * Performs rendering in a loop while the window is not closed
	 */
	public void renderLoop(){
		initLights();
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GLU.gluPerspective(45, width/height, 1, 100);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glViewport(0, 0, width, height);
		
		initBlocks();
		while(!Display.isCloseRequested()){
			GL11.glLoadIdentity();
			GLU.gluLookAt(x, y, z,
						  x+lx, 0, z+lz,
						  0, 1, 0);
			
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glPushMatrix();
			GL11.glTranslatef(0, -0.5f, -8);
			//GL11.glRotatef(angle, 1, 0, 0);
			renderBlocks();
			GL11.glPopMatrix();
			GL11.glDisable(GL11.GL_LIGHTING);
			incAngle(0.01f);
			Display.update();
			pollInput();
			checkMovement();
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
	
	public void incAngle(float inc){
		if(angle<360){
			angle+=inc;
		}else{
			angle=0;
		}
	}
	
	/**
	 * Processes keyboard input
	 */
	public void pollInput(){
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			forward = true;
		}else{
			forward = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			back = true;
		}else{
			back = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			left = true;
		}else{
			left = false;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			right = true;
		}else{
			right = false;
		}
	}
	
	/**
	 * Moves the camera
	 */
	public void checkMovement(){
		
		float fraction = 0.001f;
		
		if(right){
			camAngle+=0.001f;
			lx = (float)Math.sin(camAngle);
			lz = -(float)Math.cos(camAngle);
		}
		if(left){
			camAngle -=0.001f;
			lx = (float)Math.sin(camAngle);
			lz = -(float)Math.cos(camAngle);
		}
		if(forward){
			x += lx * fraction;
			z += lz * fraction;
		}
		if(back){
			x -= lx * fraction;
			z -= lz * fraction;
		}
	}
	
	/**
	 * Initializes the blocks
	 */
	public void initBlocks(){
		for(int x=0; x<4; x++){
			for(int z=0; z<4; z++){
				if(map[x][z] == 1){
					blocks.add(new Block(x*3, 0, z*3, 2));
				}
			}
		}
	}
	
	/**
	 * Renders the blocks
	 */
	public void renderBlocks(){
		for(int i=0; i<blocks.size(); i++){
			blocks.get(i).renderObject();
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

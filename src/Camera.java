import org.lwjgl.util.glu.GLU;


public class Camera {
	

	final static float FRACTION = 0.001f;
	
	private float camAngle;
	
	private float x;
	private float y;
	private float z;
	private float lx;
    private	float lz;
	
	/**
	 * Creates new Camera instance
	 */
	public Camera(){
		camAngle = 0.0f;
		x = 5.0f;
		y = 0.0f;
		z = 0.0f;
		lx = 0.0f;
		lz = 0.0f;
	}
	
	/**
	 * Shift the camera right by FRACTION
	 */
	public void shiftRight(){
		camAngle += FRACTION;
		lx = (float)Math.sin(camAngle);
		lz = -(float)Math.cos(camAngle);
	}
	
	/**
	 * Shift the camera left by FRACTION
	 */
	public void shiftLeft(){
		camAngle -= FRACTION;
		lx = (float)Math.sin(camAngle);
		lz = -(float)Math.cos(camAngle);
	}
	
	/**
	 * Move forward by FRACTION
	 */
	public void moveForward(){
		x += lx * FRACTION;
		z += lz * FRACTION;
	}
	
	/**
	 * Move back by FRACTION
	 */
	public void moveBack(){
		x -= lx * FRACTION;
		z -= lz * FRACTION;
	}
	
	/**
	 * Points the camera at the current point viewed
	 */
	public void moveCamera(){
		GLU.gluLookAt(x, y, z,
				  x + lx, 0, z + lz,
				  0, 1, 0);
	}

}

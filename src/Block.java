import org.lwjgl.opengl.GL11;


public class Block {
	
	private float xPos;
	private float yPos;
	private float zPos;
	private float width;
	private float height;
	private float depth;
	private float dimention;
	
	/**
	 * used to create a rectangular prism of any size
	 * @param xPos
	 * @param yPos
	 * @param zPos
	 * @param width
	 * @param height
	 * @param depth
	 */
	public Block(float xPos, float yPos, float zPos, float width, float height, float depth){
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.width = width;
		this.height = height;
		this.depth = depth;
	}
	
	/**
	 * Used for creating a perfect cube of any size
	 * where the width height and depth are the same
	 * @param xPos
	 * @param yPos
	 * @param zPos
	 * @param dimention
	 */
	public Block(float xPos, float yPos, float zPos, float dimention){
		this.xPos = xPos;
		this.yPos = yPos;
		this.zPos = zPos;
		this.dimention = dimention;
		this.width = dimention;
		this.height = dimention;
		this.depth = dimention;
	}
	
	/**
	 * renders the object with the values that it has
	 */
	public void renderObject(){
		GL11.glBegin(GL11.GL_QUADS);
		//front face
		GL11.glColor3f(1, 0, 0);
		GL11.glVertex3f(xPos, yPos, zPos);
		GL11.glVertex3f(xPos+width, yPos, zPos);
		GL11.glVertex3f(xPos+width, yPos+height, zPos);
		GL11.glVertex3f(xPos, yPos+height, zPos);
		
		//back face
		GL11.glVertex3f(xPos, yPos, zPos-depth);
		GL11.glVertex3f(xPos+width, yPos, zPos-depth);
		GL11.glVertex3f(xPos+width, yPos+height, zPos-depth);
		GL11.glVertex3f(xPos, yPos+height, zPos-depth);
		
		//right face
		GL11.glColor3f(0, 1, 0);
		GL11.glVertex3f(xPos+width, yPos, zPos);
		GL11.glVertex3f(xPos+width, yPos, zPos-depth);
		GL11.glVertex3f(xPos+width, yPos+height, zPos-depth);
		GL11.glVertex3f(xPos+width, yPos+height, zPos);
		
		//left face
		GL11.glVertex3f(xPos, yPos, zPos);
		GL11.glVertex3f(xPos, yPos, zPos-depth);
		GL11.glVertex3f(xPos, yPos+height, zPos-depth);
		GL11.glVertex3f(xPos, yPos+height, zPos);
		
		//top face
		GL11.glVertex3f(xPos, yPos+height, zPos);
		GL11.glVertex3f(xPos, yPos+height, zPos-depth);
		GL11.glVertex3f(xPos+width, yPos+height, zPos-depth);
		GL11.glVertex3f(xPos+width, yPos+height, zPos);
		
		//bottom face
		GL11.glVertex3f(xPos, yPos, zPos);
		GL11.glVertex3f(xPos, yPos, zPos-depth);
		GL11.glVertex3f(xPos+width, yPos, zPos-depth);
		GL11.glVertex3f(xPos+width, yPos, zPos);
		GL11.glEnd();
	}

	/**
	 * @return the xPos
	 */
	public float getxPos() {
		return xPos;
	}

	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(float xPos) {
		this.xPos = xPos;
	}

	/**
	 * @return the yPos
	 */
	public float getyPos() {
		return yPos;
	}

	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(float yPos) {
		this.yPos = yPos;
	}

	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}

	/**
	 * @return the depth
	 */
	public float getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(float depth) {
		this.depth = depth;
	}

	/**
	 * @return the dimention
	 */
	public float getDimention() {
		return dimention;
	}

	/**
	 * @param dimention the dimention to set
	 */
	public void setDimention(float dimention) {
		this.dimention = dimention;
	}
}

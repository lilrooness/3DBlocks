import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;


public class ImageBank {
	
	private static Texture boxTex;
	
	public static void texInit(){
		try {
			boxTex = TextureLoader.getTexture("BMP", new FileInputStream("Crate.bmp"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not load texture");
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	/**
	 * returns the Texture boxTex
	 * @return boxTex
	 */
	public static Texture getBoxTex(){
		return boxTex;
	}
}

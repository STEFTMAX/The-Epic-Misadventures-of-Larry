import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glEnable;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import com.steftmax.temol.graphics.SpriteBatch;
import com.steftmax.temol.graphics.Texture;
import com.steftmax.temol.graphics.TextureAtlas;
import com.steftmax.temol.graphics.TextureRegion;


/**
 * @author pieter3457
 *
 */
public class TextureAtlasTest {
	public static void main(String[] args) {
		try {
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		SpriteBatch batch = new SpriteBatch(1, 1024, 1024);
		TextureAtlas ta = new TextureAtlas(1024, 1024);
		System.out.println(ta.add("gfx/arrow.png"));
		TextureRegion tr = ta.add("gfx/arrow.png");
		ta.add("gfx/menu.png");
		ta.dispose();
		GL11.glClearColor(0, 1, 1, 1);
		while(!Display.isCloseRequested()) {
			glEnable(GL_TEXTURE_2D);
			batch.begin();
			batch.draw(ta.getTexture(),0,0);
			batch.end();
			Display.update();
		}
	}
}

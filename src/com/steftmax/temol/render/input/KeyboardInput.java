package com.steftmax.temol.render.input;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import com.steftmax.temol.render.Updatable;


//TODO multiple keys with same function
//TODO timed precision
public class KeyboardInput implements Updatable{
	
	public int left = Keyboard.KEY_A, right = Keyboard.KEY_D, up = Keyboard.KEY_W, down = Keyboard.KEY_S, shift = Keyboard.KEY_LSHIFT;

	private boolean[] keys = new boolean[256];

	public KeyboardInput() {
		if (!Keyboard.isCreated()) {
			try {
				Keyboard.create();
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isKeyDown(int key) {
		return Keyboard.isKeyDown(key);
	}

	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.render.Renderable#render(long)
	 */
	@Override
	public void update(long delta) {
		
        while(Keyboard.next()) {
            if (Keyboard.getEventKey() < keys.length) {
                keys [Keyboard.getEventKey()] = Keyboard.getEventKeyState();
            }
        }
	}
	
	public boolean isLeftDown() {
		return keys[left];
	}
	
	public boolean isRightDown() {
		return keys[right];
	}
	
	public boolean isUpDown() {
		return keys[up];
	}
	
	public boolean isDownDown() {
		return keys[down];
	}
	
	public boolean isShiftDown() {
		return keys[shift];
	}
}

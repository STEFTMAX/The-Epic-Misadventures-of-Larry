package com.steftmax.larrys_epic_misadventures.input;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import com.steftmax.larrys_epic_misadventures.update.Updatable;

public class KeyboardInput implements Updatable{
	
	public int left = Keyboard.KEY_LEFT, right = Keyboard.KEY_RIGHT, up = Keyboard.KEY_UP, down = Keyboard.KEY_DOWN;

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
}

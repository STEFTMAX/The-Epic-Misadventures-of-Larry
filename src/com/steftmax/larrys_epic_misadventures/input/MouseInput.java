package com.steftmax.larrys_epic_misadventures.input;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

import com.steftmax.larrys_epic_misadventures.update.Updatable;

public class MouseInput implements Updatable{
	
	public MouseInput(boolean grabbed) {

		if (!Mouse.isCreated()){
			try {
				Mouse.create();
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
		
		
		Mouse.setGrabbed(grabbed);
		
	}
	
	
	public int getMouseWheelChange() {
		return Mouse.getDWheel();
	}


	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.render.Renderable#render(long)
	 */
	@Override
	public void update(long delta) {
		Mouse.poll();
	}
	
}

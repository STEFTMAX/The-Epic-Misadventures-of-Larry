package com.steftmax.larrys_epic_misadventures.input;

import java.util.HashSet;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Mouse;

import com.steftmax.larrys_epic_misadventures.Game;
import com.steftmax.larrys_epic_misadventures.update.Updatable;

public class MouseInput implements Updatable{
	
	
	private HashSet<MouseClickListener> listeners = new HashSet<MouseClickListener>();
	
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
	
	//Listener changing methods
	public void clearListeners() {
		listeners.clear();
	}
	
	public void addListener(MouseClickListener listener) {
		listeners.add(listener);
	}
	
	public void removeListener(MouseClickListener listener) {
		listeners.remove(listener);
	}


	/* (non-Javadoc)
	 * @see com.sessionstraps.game_engine.render.Renderable#render(long)
	 */
	@Override
	public void update(long delta) {
		while (Mouse.next()) {
			if (Mouse.getEventButton() >= 0) {
				//System.out.println("Mouse clicked at x: " + Mouse.getEventX() + " and y: " + Mouse.getEventY());
				//TODO seperate click and declick
				for(MouseClickListener listener :  listeners) {
					listener.onClick(
							Mouse.getEventButton()
							, Mouse.getEventX()
							, Game.WINDOW.height
							- Mouse.getEventY());
				}
			}
		}
	}
	
}

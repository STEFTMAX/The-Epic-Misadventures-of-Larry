package com.steftmax.larrys_epic_misadventures.render.state;

import com.steftmax.larrys_epic_misadventures.graphics.Drawable;
import com.steftmax.larrys_epic_misadventures.graphics.SpriteBatch;
import com.steftmax.larrys_epic_misadventures.graphics.sprite.TextureRegion;
import com.steftmax.larrys_epic_misadventures.math.AABB;
import com.steftmax.larrys_epic_misadventures.render.input.MouseClickListener;

/**
 * @author pieter3457
 *
 */
public class CheckBox implements MouseClickListener, Drawable {
	
	private AABB boundaryBox;
	private TextureRegion unchecked, checked;
	private boolean isChecked = false;

	public CheckBox(AABB boundaryBox, TextureRegion unchecked, TextureRegion checked, boolean isChecked) {
		this.boundaryBox = boundaryBox;
		this.unchecked = unchecked;
		this.checked = checked;
		this.isChecked = isChecked;
	}
	
	public CheckBox(AABB boundaryBox, TextureRegion unchecked, TextureRegion checked) {
		this(boundaryBox, unchecked, checked, false);
	}

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.graphics.Drawable#draw(com.steftmax.larrys_epic_misadventures.graphics.SpriteBatch)
	 */
	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.render.input.MouseClickListener#onClick(int, int, int)
	 */
	@Override
	public void onClick(int button, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.steftmax.larrys_epic_misadventures.render.input.MouseClickListener#onDeClick(int, int, int)
	 */
	@Override
	public void onDeClick(int button, int x, int y) {
		// TODO Auto-generated method stub
		
	}

}

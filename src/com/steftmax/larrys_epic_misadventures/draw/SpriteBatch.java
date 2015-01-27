package com.steftmax.larrys_epic_misadventures.draw;

import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.GL_DEPTH_TEST;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glDisable;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;

import java.util.HashMap;
import java.util.HashSet;

import com.steftmax.larrys_epic_misadventures.math.AABB;
import com.steftmax.larrys_epic_misadventures.sprite.Sprite;
import com.steftmax.larrys_epic_misadventures.sprite.Texture;

/**
 * @author pieter3457
 *
 */
public class SpriteBatch {

	HashMap<Texture, HashSet<Sprite>> drawingBuffer = new HashMap<Texture, HashSet<Sprite>>();
	AABB aim;
	private boolean hasStarted;
	private boolean testForContainment;

	public SpriteBatch(int width, int height) {
		glMatrixMode(GL_PROJECTION);
		glOrtho(0, 1280, 720, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		glEnable(GL_TEXTURE_2D);

		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

		glClearColor(.5f, .5f, .5f, 1f);

		glDisable(GL_DEPTH_TEST);

		glLoadIdentity();
	}

	public void begin() {
		drawingBuffer.clear();
		hasStarted = true;
		testForContainment = false;
	}

	public void begin(AABB aim) {

		begin();
		this.aim = aim;
		testForContainment = true;
	}

	public void add(Sprite s) {
		if (testForContainment
				&& aim.collides((int) Math.floor(s.pos.x),
						(int) Math.floor(s.pos.y), s.width, s.height)) {
			return;
		}

		Texture t = s.getTexture();

		if (drawingBuffer.containsKey(t)) {
			drawingBuffer.get(t).add(s);
		} else {

			// TODO Set pooling
			final HashSet<Sprite> temp = new HashSet<Sprite>();
			temp.add(s);
			drawingBuffer.put(t, temp);
		}
	}
	
	public void end() {
		for (Texture t: drawingBuffer.keySet()) {
			t.bind();
			for (Sprite s : drawingBuffer.get(t)) {
				//TODO the acutal drawing with all the sprite properties
			}
			t.unbind();
		}
	}

	// float[] positions = new float[] {1, 1, 1};
	// float[] colors = new float[] {1, 1, 1, 1};
	//
	// // Interleave the data in the proper format: byte buffer
	// FloatBuffer interleavedBuffer =
	// BufferUtils.createFloatBuffer(positions.length +
	// colors.length);
	// interleavedBuffer.put(positions); // Buffer contents: X, Y, Z
	// interleavedBuffer.put(colors); // Buffer contents: X, Y, Z, R, G, B, A
	// interleavedBuffer.flip();
	//
	// // Create a new VAO
	// int vaoID = GL30.glGenVertexArrays();
	// GL30.glBindVertexArray(vaoID);
	//
	// // Create a new VBO for our interleaved data
	// int interVboID = GL15.glGenBuffers();
	// GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, interVboID);
	// GL15.glBufferData(GL15.GL_ARRAY_BUFFER, interleavedBuffer,
	// GL15.GL_STATIC_DRAW);
	//
	// // -- We'll need to know some numbers beforehand, I'm using variables for
	// these
	// // -- so it's easier to see where they come from
	// // There are 4 bytes in a float
	// int floatByteSize = 4;
	// // We use 3 floats for our position
	// int positionFloatCount = 3;
	// // We use 4 floats for our color
	// int colorFloatCount = 4;
	// // So the total amount of floats used is ...
	// int floatsPerVertex = positionFloatCount + colorFloatCount;
	// // So the total amount of bytes per vertex used is (this is the 'stride')
	// ...
	// int vertexFloatSizeInBytes = floatByteSize * floatsPerVertex;
	//
	// // -- Now we can split our interleaved data over 2 attribute lists
	// // First up is our positional information in list 0
	// GL20.glVertexAttribPointer(0, positionFloatCount, GL11.GL_FLOAT, false,
	// vertexFloatSizeInBytes, 0);
	// // Second is our color information in list 1, for this we also need the
	// offset
	// int byteOffset = floatByteSize * positionFloatCount;
	// GL20.glVertexAttribPointer(1, colorFloatCount, GL11.GL_FLOAT, false,
	// vertexFloatSizeInBytes, byteOffset);
	//
	// GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	// GL30.glBindVertexArray(0);
}

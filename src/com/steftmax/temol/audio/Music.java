package com.steftmax.temol.audio;

import static org.lwjgl.openal.AL10.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.steftmax.temol.resource.Disposable;
import com.steftmax.temol.resource.loader.ResourceLoader;

/**
 * @author pieter3457
 *
 */
public class Music extends Audio implements Disposable {

	boolean looping = false;

	private IntBuffer buffers = OpenALSystem.createIntBuffer(2);

	private ByteBuffer dataBuffer = ByteBuffer.allocateDirect(4096 * 8);

	private OggInputStream stream, empty;

	private String path;


	public Music(String path) {
		this.path = path;
		stream = new OggInputStream(path);

		buffers.rewind();
		alGenBuffers(buffers);
		check();
	}

	public boolean play(int musicSource) {
		this.sourceID = musicSource;
		if (playing()) {
			return true;
		}

		for (int i = 0; i < buffers.capacity(); i++) {
			if (!stream(buffers.get(i))) {
				return false;
			}
		}

		alSourceQueueBuffers(sourceID, buffers);
		alSourcePlay(sourceID);

		return true;
	}

	/**
	 * check if the source is playing
	 */
	public boolean playing() {
		return (alGetSourcei(sourceID, AL_SOURCE_STATE) == AL_PLAYING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.resource.Disposable#dispose()
	 */
	@Override
	public void dispose() {
		stop();
		alDeleteBuffers(buffers);
		check();
		try {
			stream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * empties the queue
	 */
	protected void empty() {
		int queued = alGetSourcei(sourceID, AL_BUFFERS_QUEUED);
		while (queued-- > 0) {
			IntBuffer buffer = OpenALSystem.createIntBuffer(1);
			alSourceUnqueueBuffers(sourceID, buffer);
			check();
		}
	}

	protected boolean stream(int buffer) {
		try {
			int bytesRead = stream.read(dataBuffer, 0,
					dataBuffer.capacity());
			if (bytesRead >= 0) {
				dataBuffer.rewind();
				alBufferData(buffer, stream.getFormat(), dataBuffer,
						stream.getRate());
				check();
				return true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Copies data from the ogg stream to openal. Must be called often.
	 * 
	 * @return true if sound is still playing, false if the end of file is
	 *         reached.
	 */
	public synchronized boolean update() throws IOException {
		
		int state = alGetSourcei(sourceID, AL_SOURCE_STATE);
		if (state == AL_PAUSED) return false;
		
		boolean active = true;
		
		if (empty == null) {
			empty = new OggInputStream(path);
		}
		
		int processed = alGetSourcei(sourceID, AL_BUFFERS_PROCESSED);
		while (processed-- > 0) {
			IntBuffer buffer = OpenALSystem.createIntBuffer(1);
			alSourceUnqueueBuffers(sourceID, buffer);
			check();

			active = stream(buffer.get(0));
			buffer.rewind();

			alSourceQueueBuffers(sourceID, buffer);
			check();
		}
		if (!active) {
			if (looping) {
				active = true;
				loop();
				update();
			}
		}

		return active;
	}
	
	/**
	 * @throws IOException 
	 * 
	 */
	private void loop() throws IOException {
		stream.close();
		stream = empty;
		empty = null;
	}

	public void setLooping(boolean value) {
		looping = value;
	}
	
	public void stop() {
		alSourceUnqueueBuffers(sourceID);
		alSourceStop(sourceID);
		this.sourceID = -1;
	}
}

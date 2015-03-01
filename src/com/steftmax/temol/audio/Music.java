package com.steftmax.temol.audio;

import static org.lwjgl.openal.AL10.*;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import com.steftmax.temol.resource.Disposable;

/**
 * @author pieter3457
 *
 */
public class Music implements Disposable {

	boolean looping = false;

	private IntBuffer buffers = OpenALSystem.createIntBuffer(2);

	private IntBuffer source = OpenALSystem.createIntBuffer(1);

	private ByteBuffer dataBuffer = ByteBuffer.allocateDirect(4096 * 8);

	private OggInputStream oggInputStream;


	public Music(String path) {
		oggInputStream = new OggInputStream(path);

		buffers.rewind();
		alGenBuffers(buffers);
		check();

		source.rewind();
		alGenSources(source);
		check();

		alSourcef(source.get(0),AL_GAIN, 1);
		alSourcef(source.get(0), AL_ROLLOFF_FACTOR, 0);
		alSourcei(source.get(0), AL_SOURCE_RELATIVE, AL_FALSE);
	}

	protected void check() {
		int error = alGetError();
		if (error != AL_NO_ERROR) {
			System.out.println("OpenAL error was raised. errorCode=" + error);
		}
	}

	public boolean play() {
		if (playing()) {
			return true;
		}

		for (int i = 0; i < buffers.capacity(); i++) {
			if (!stream(buffers.get(i))) {
				return false;
			}
		}

		alSourceQueueBuffers(source.get(0), buffers);
		alSourcePlay(source.get(0));

		return true;
	}

	/**
	 * check if the source is playing
	 */
	public boolean playing() {
		return (alGetSourcei(source.get(0), AL_SOURCE_STATE) == AL_PLAYING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.resource.Disposable#dispose()
	 */
	@Override
	public void dispose() {
		alSourceStop(source);
		empty();
		alDeleteSources(source);
		check();
		alDeleteBuffers(buffers);
		check();
		try {
			oggInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * empties the queue
	 */
	protected void empty() {
		int queued = alGetSourcei(source.get(0), AL_BUFFERS_QUEUED);
		while (queued-- > 0) {
			IntBuffer buffer = OpenALSystem.createIntBuffer(1);
			alSourceUnqueueBuffers(source.get(0), buffer);
			check();
		}
	}

	protected boolean stream(int buffer) {
		try {
			int bytesRead = oggInputStream.read(dataBuffer, 0,
					dataBuffer.capacity());
			if (bytesRead >= 0) {
				dataBuffer.rewind();
				boolean mono = (oggInputStream.getFormat() == OggInputStream.FORMAT_MONO16);
				int format = (mono ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16);
				alBufferData(buffer, format, dataBuffer,
						oggInputStream.getRate());
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
		boolean active = true;
		int processed = alGetSourcei(source.get(0), AL_BUFFERS_PROCESSED);
		while (processed-- > 0) {
			IntBuffer buffer = OpenALSystem.createIntBuffer(1);
			alSourceUnqueueBuffers(source.get(0), buffer);
			check();

			active = stream(buffer.get(0));
			buffer.rewind();

			alSourceQueueBuffers(source.get(0), buffer);
			check();
		}

		return active;
	}
	
	public void setLooping() {
	    alSourcei(source.get(0), AL_LOOPING,  AL_TRUE);
	}
}

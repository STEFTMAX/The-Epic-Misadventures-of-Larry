package com.steftmax.temol.audio;

import static org.lwjgl.openal.AL10.AL_BUFFER;
import static org.lwjgl.openal.AL10.AL_FALSE;
import static org.lwjgl.openal.AL10.AL_GAIN;
import static org.lwjgl.openal.AL10.AL_PAUSED;
import static org.lwjgl.openal.AL10.AL_PLAYING;
import static org.lwjgl.openal.AL10.AL_ROLLOFF_FACTOR;
import static org.lwjgl.openal.AL10.AL_SOURCE_RELATIVE;
import static org.lwjgl.openal.AL10.AL_SOURCE_STATE;
import static org.lwjgl.openal.AL10.alDeleteSources;
import static org.lwjgl.openal.AL10.alGenSources;
import static org.lwjgl.openal.AL10.alGetSourcei;
import static org.lwjgl.openal.AL10.alSourceStop;
import static org.lwjgl.openal.AL10.alSourcef;
import static org.lwjgl.openal.AL10.alSourcei;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.HashSet;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;

import com.steftmax.temol.render.DeltaTimer;
import com.steftmax.temol.render.TimeScaler;
import com.steftmax.temol.resource.Disposable;

/**
 * @author pieter3457
 *
 */
public class OpenALSystem implements Runnable, Disposable {

	private class Source implements Disposable {
		public int distanceSquared;
		private final int id;

		public Source(int distanceSquared) {
			this.id = alGenSources();
			this.distanceSquared = distanceSquared;
		}

		public boolean isActive() {
			int state = alGetSourcei(id, AL_SOURCE_STATE);
			return (state == AL_PLAYING || state == AL_PAUSED);
		}

		public void dispose() {
			alDeleteSources(id);
		}
	}

	private int musicSource = -1;

	private final static int MAXSOURCES = 16;
	private HashSet<Source> sources = new HashSet<Source>();
	Music currentMusic;

	public OpenALSystem() {
		if (AL.isCreated()) {
			throw new RuntimeException(
					"There already is an OpenAL instance running. I won't allow two of us, mate!");
		}
		try {
			AL.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		musicSource = alGenSources();

		alSourcef(musicSource, AL_GAIN, 1);
		alSourcef(musicSource, AL_ROLLOFF_FACTOR, 0);
		alSourcei(musicSource, AL_SOURCE_RELATIVE, AL_FALSE);

		Thread thread = new Thread(this);
		thread.start();
	}

	public static IntBuffer createIntBuffer(int size) {
		ByteBuffer temp = ByteBuffer.allocateDirect(4 * size);
		temp.order(ByteOrder.nativeOrder());
		return temp.asIntBuffer();
	}

	public int obtainSource(int distanceSquared) {
		Source furthest = null;
		for (Source source : sources) {

			if (furthest == null) {
				furthest = source;
			} else {
				if (furthest.distanceSquared < source.distanceSquared) {
					furthest = source;
				}
			}

			if (!source.isActive()) {
				return source.id;
			}
		}

		if (sources.size() < MAXSOURCES) {
			Source newSource = new Source(distanceSquared);
			sources.add(newSource);
			return newSource.id;
		}

		int id = furthest.id;
		alSourceStop(id);
		alSourcei(id, AL_BUFFER, 0);
		return id;
	}

	private boolean stop;

	@Override
	public void run() {
		DeltaTimer dt = new DeltaTimer(1);
		while (!stop) {
			if (currentMusic != null && currentMusic.playing()) {
				try {
					currentMusic.update();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			try {
				Thread.sleep(Math.max(0, 5 - (long) TimeScaler.nanosToMilisF(dt.getDelta())));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.steftmax.temol.resource.Disposable#dispose()
	 */
	@Override
	public synchronized void dispose() {
		for (Source source : sources) {
			source.dispose();
		}
		currentMusic.dispose();
		AL.destroy();
		stop = true;
	}

	/**
	 * @param music
	 */
	public synchronized void setMusic(Music music) {
		stopMusic();
		currentMusic = music;
		music.play(musicSource);
	}
	
	public synchronized void stopMusic() {
		if (currentMusic != null) {
			currentMusic.stop();
			currentMusic = null;
		}
	}
}

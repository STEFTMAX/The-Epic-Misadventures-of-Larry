package com.steftmax.larrys_epic_misadventures.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.steftmax.larrys_epic_misadventures.resource.loader.ResourceLoader;

/**
 * @author pieter3457
 *
 */
public class Settings implements Serializable {
	
	private static final long serialVersionUID = -3993716425785885194L;
	
	private final static String saveFileName = ".settings";
	public final static int DEFAULT_WIDTH = 1280, DEFAULT_HEIGHT = 720;
	private int currentWidth, currentHeight, maxFPS;
	private static Settings INSTANCE;

	private static File getSettingsFile() {
		File gameDir = ResourceLoader.getPath();
		return new File(gameDir, saveFileName);
	}
	
	static {
		File saveFile = getSettingsFile();
		//saveFile.mkdirs();
		System.out.println(saveFile);
		if (saveFile.exists()) {
			INSTANCE = readSettings(saveFile);
		} else {
			INSTANCE = new Settings();
		}
	}
	
	public Settings() {
		currentWidth = DEFAULT_WIDTH;
		currentHeight = DEFAULT_HEIGHT;
		save(this);
	}

	public static void saveState() {
		save(INSTANCE);
	}
	
	private static void save(Settings INSTANCE) {
		ObjectOutputStream stream = null;
		File saveFile = getSettingsFile();
		try {
			saveFile.createNewFile();
			stream = new ObjectOutputStream(new FileOutputStream(saveFile));
			stream.writeObject(INSTANCE);
		} catch (IOException e) {
			e.printStackTrace();
		} finally { 
			try {
				stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @return
	 */
	private static Settings readSettings(File f) {
		Settings settings = null;
		InputStream is = null;
		ObjectInputStream stream = null;
		try {
			is = new FileInputStream(f);
			stream = new ObjectInputStream(is);
			settings = (Settings) stream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stream != null)
					stream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return settings;
	}
	
	public static void setResolution(int width, int height) {
		INSTANCE.currentWidth = width;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
		INSTANCE.currentHeight = height;
	}
	
	public static int getWidth() {
		return INSTANCE.currentWidth;
		
	}

	public static int getHeight() {
		return INSTANCE.currentHeight;
	}
}

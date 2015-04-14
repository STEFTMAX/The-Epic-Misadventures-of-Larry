package com.steftmax.temol.resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class represents a TextFile. It loads automatically in the constructor
 * and the String in the TextFile can be obtained using getContents();
 * 
 * @author pieter3457
 *
 */
public class TextFile {

	public final String fileContents;

	public TextFile(String path) {

		InputStreamReader fileReader = new InputStreamReader(
				ResourceLoader.load(path));

		BufferedReader bf = new BufferedReader(fileReader);

		String curLine;
		StringBuilder fileContents = new StringBuilder();

		try {
			try {
				while ((curLine = bf.readLine()) != null) {
					fileContents.append(curLine).append('\n');
				}
			} finally {
				bf.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.fileContents = fileContents.toString();
	}
	
	public String getContent() {
		return fileContents;
	}
}

package com.steftmax.larrys_epic_misadventures.resource.loader;

import java.io.File;
import java.io.InputStream;

public final class ResourceLoader {
	
	private ResourceLoader(){}

	
	public static InputStream load(String path) {
		InputStream input = ResourceLoader.class.getResourceAsStream(path);
		if (input == null) {
			input = ResourceLoader.class.getResourceAsStream("/" + path);
		}

		return input;
	}

	@Deprecated //deprecated as not being universal for every game
	public static InputStream image(String file) {
		return ResourceLoader.class.getResourceAsStream("/images/" + file);
	}

	public static File getPath() {
		return new File(ResourceLoader.class.getProtectionDomain().getCodeSource().getLocation().getPath());
	}

}

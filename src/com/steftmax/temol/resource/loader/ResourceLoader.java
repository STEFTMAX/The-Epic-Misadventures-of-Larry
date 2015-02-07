package com.steftmax.temol.resource.loader;

import java.io.File;
import java.io.InputStream;
import java.net.URISyntaxException;

public final class ResourceLoader {

	private ResourceLoader() {
	}

	public static InputStream load(String path) {
		InputStream input = ResourceLoader.class.getResourceAsStream(path);
		if (input == null) {
			input = ResourceLoader.class.getResourceAsStream("/" + path);
		}

		return input;
	}

	public static File getPath() {
		try {
			return new File(Thread.currentThread().getContextClassLoader()
					.getResource(".").toURI().getPath());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}

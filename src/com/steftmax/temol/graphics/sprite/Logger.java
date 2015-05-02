package com.steftmax.temol.graphics.sprite;

/**
 * @author pieter3457
 *
 */
public class Logger {

	public static enum Level {

		ERROR("Error: ", 0), WARNING("Warning: ", 1), INFO("Info: ", 2);

		public final String prefix;
		public final int index;

		private Level(String prefix, int index) {
			this.prefix = prefix;
			this.index = index;
		}

		public boolean allows(Level level) {
			return index >= level.index;
		}
	}

	private static Level minLevel = Level.ERROR;

	public static void info(Object caller, String message) {
		log(caller, Level.INFO, message);
	}

	public static void warn(Object caller, String message) {
		log(caller, Level.WARNING, message);
	}

	public static void err(Object caller, String message) {
		log(caller, Level.ERROR, message);
	}

	public static void log(Object caller, Level level, String message) {
		if (minLevel.allows(level)) {
			System.out.append(level.prefix);
			System.out.append(caller.getClass().getName());
			System.out.append(": ");
			System.out.append(message);
			System.out.append("\n");
			System.out.flush();
		}
	}

	public static void setLevel(Level level) {
		Logger.minLevel = level;
	}
}

package com.steftmax.temol.physics;

import java.awt.image.BufferedImage;

public class CollisionChecker {

	public static boolean boxCollides(int x1, int y1, int width1, int height1,
			int x2, int y2, int width2, int height2) {
		
		
		if (x1 < x2 + width2) {
			if (x2 < x1 + width1) {
				if (y1 < y2 + height2) {
					if (y2 < y1 + height1) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean[][] generateCollideMap(BufferedImage img) {
		boolean[][] collideMap = new boolean[img.getHeight()][img.getWidth()];
		
		for (int y = 0; y < collideMap.length; y++) {
			for (int x = 0; x< collideMap[y].length; x++) {
				collideMap[y][x] = img.getRGB(x, y) >> 24 != 0x00;
			}
		}
		return collideMap;
	}

//	public static List<Point> generateLine(int x1, int y1, int x2, int y2) {
//		
//		//TODO check if directional else edit to be so;
//		List<Point> points = new ArrayList<Point>();
//		
//		int w = x2 - x1;
//		int h = y2 - y1;
//		int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
//		if (w < 0)
//			dx1 = -1;
//		else if (w > 0)
//			dx1 = 1;
//		if (h < 0)
//			dy1 = -1;
//		else if (h > 0)
//			dy1 = 1;
//		if (w < 0)
//			dx2 = -1;
//		else if (w > 0)
//			dx2 = 1;
//		int longest = Math.abs(w);
//		int shortest = Math.abs(h);
//		if (!(longest > shortest)) {
//			longest = Math.abs(h);
//			shortest = Math.abs(w);
//			if (h < 0)
//				dy2 = -1;
//			else if (h > 0)
//				dy2 = 1;
//			dx2 = 0;
//		}
//		int numerator = longest >> 1;
//		for (int i = 0; i <= longest; i++) {
//			points.add(new Point(x1, y1));
//			numerator += shortest;
//			if (!(numerator < longest)) {
//				numerator -= longest;
//				x1 += dx1;
//				y1 += dy1;
//			} else {
//				x1 += dx2;
//				y1 += dy2;
//
//			}
//		}
//		return points;
//	}
}

package com.steftmax.temol.math;

/**
 * @author pieter3457
 *
 */
public class Matrix2 {
	float[] values = new float[4];
	public Matrix2() {
		values[0] = 1;
		values[0] = 0;
		values[0] = 0;
		values[0] = 1;
	}
	
	public void setRotate(float radians) {
		float cos = (float) Math.cos(radians);
		float sin = (float) Math.sin(radians);
		values[0] = cos;
		values[1] = sin;
		values[2] = -sin;
		values[3] = cos;
//		
//		for (int i =0; i < 4; i++) {
//			System.out.println(values[i]);
//		}
	}
}

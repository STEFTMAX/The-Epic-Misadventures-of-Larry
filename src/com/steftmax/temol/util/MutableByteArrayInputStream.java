package com.steftmax.temol.util;

import java.io.ByteArrayInputStream;

/**
 * @author pieter3457
 *
 */
public class MutableByteArrayInputStream extends ByteArrayInputStream {

	public MutableByteArrayInputStream(byte[] buf, int offset, int length) {
		super(buf, offset, length);
	}


	public MutableByteArrayInputStream(byte[] buf) {
		super(buf);
	}

	public void setBuffer(byte[] buffer) {

		count = buffer.length;
		reset();

		this.buf = buffer;
	}

	public byte[] getBuffer() {
		return this.buf;
	}

}

package com.steftmax.temol.audio;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.steftmax.temol.resource.loader.ResourceLoader;
import static org.lwjgl.openal.AL10.*;

/**
 * @author pieter3457
 *
 */
public class Sound {
	public final int channels;
	private final int bufferID;

	public Sound(String path) throws IOException {

		DataInputStream input = new DataInputStream(new BufferedInputStream(
				ResourceLoader.load(path)));

		if (input.read() != 'R' || input.read() != 'I' || input.read() != 'F'
				|| input.read() != 'F') {
			throw new RuntimeException("File is corrupt. " + path);
		}

		input.skipBytes(4);

		if (input.read() != 'W' || input.read() != 'A' || input.read() != 'V'
				|| input.read() != 'E') {
			throw new RuntimeException("File is corrupt. " + path);
		}

		int fmtLength = seekToChunk(input, 'f', 'm', 't', ' ');
		int type = input.read() & 0xff | (input.read() & 0xff) << 8;
		if (type != 1)
			throw new RuntimeException("Wave file isn't PCM!");

		channels = input.read() & 0xff | (input.read() & 0xff) << 8;

		if (channels != 1 && channels != 2)
			throw new RuntimeException(
					"This WAV contains more than 2 channels and is therefor invalid.");

		int sampleRate = input.read() & 0xff | (input.read() & 0xff) << 8
				| (input.read() & 0xff) << 16 | (input.read() & 0xff) << 24;
		input.skip(6);

		int bitsPerSample = input.read() & 0xff | (input.read() & 0xff) << 8;
		if (bitsPerSample != 16)
			throw new RuntimeException(
					"This wave file doesn't have 16 bits per sample.");

		input.skip(fmtLength - 16);

		int leftover = seekToChunk(input, 'd', 'a', 't', 'a');

		byte[] pcm = new byte[leftover];

		input.read(pcm);
		input.close();

		int bytes = pcm.length - (pcm.length % (channels > 1 ? 4 : 2));

		ByteBuffer buffer = ByteBuffer.allocateDirect(bytes);
		buffer.order(ByteOrder.nativeOrder());
		buffer.put(pcm, 0, bytes);
		buffer.flip();

		bufferID = alGenBuffers();
		alBufferData(bufferID, channels > 1 ? AL_FORMAT_STEREO16
				: AL_FORMAT_MONO16, buffer.asShortBuffer(), sampleRate);
	}

	private int seekToChunk(DataInputStream dis, char c1, char c2, char c3,
			char c4) throws IOException {
		while (true) {
			boolean found = dis.read() == c1;
			found &= dis.read() == c2;
			found &= dis.read() == c3;
			found &= dis.read() == c4;
			int chunkLength = dis.read() & 0xff | (dis.read() & 0xff) << 8
					| (dis.read() & 0xff) << 16 | (dis.read() & 0xff) << 24;
			if (chunkLength == -1)
				throw new IOException("Chunk not found: " + c1 + c2 + c3 + c4);
			if (found)
				return chunkLength;
			dis.skipBytes(chunkLength);
		}
	}
}

package br.umc.io.factory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BufferedReaderFactory {
	
	private InputStreamReader getInputStreamReader() {
		final InputStream is = System.in;
		return new InputStreamReader(is);
	}
	
	public BufferedReader getBufferedReader() {
		return new BufferedReader(getInputStreamReader());
	}
}

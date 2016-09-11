package br.umc.util;

public class AtmUtil {
	
	public static void showMessage(final String... message) {
		
		for (String selected : message)
			System.out.println(selected);
	}
}

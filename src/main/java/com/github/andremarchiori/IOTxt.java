package com.github.andremarchiori;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class IOTxt {
	
	public static BufferedWriter openWriterPersonagens() throws IOException {
		FileWriter escreveDados = new FileWriter("characters.txt", true);
		return new BufferedWriter(escreveDados);
	}
	
	public static BufferedReader openReaderPersonagens() throws FileNotFoundException {
		FileReader leitor = new FileReader("characters.txt");
		return new BufferedReader(leitor);
	}
	
	public static void clearTheFile() {
		try {
			FileWriter fwOb = new FileWriter("characters.txt", false);
			PrintWriter pwOb = new PrintWriter(fwOb, false);
			pwOb.flush();
			pwOb.close();
			fwOb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

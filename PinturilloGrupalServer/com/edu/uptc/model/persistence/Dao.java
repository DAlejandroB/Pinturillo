package com.edu.uptc.model.persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.edu.uptc.structure.LinkedList;

public class Dao {

	public LinkedList<String> readWords() throws IOException {
		LinkedList<String> words = new LinkedList<>();
		BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\camil\\git\\Pinturillo\\PinturilloGrupal\\data/listWords.csv"));
		String line = br.readLine();
		String wordsF[] = null;
		
		while (line != null) {
			wordsF = line.split(",");
			line = br.readLine();
		}
		
		for (int i = 0; i < wordsF.length; i++) {
			words.add(wordsF[i]);
		}
		return words;
	}
}

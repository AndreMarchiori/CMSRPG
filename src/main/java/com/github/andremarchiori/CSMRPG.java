package com.github.andremarchiori;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSMRPG {

	public static Map<Integer, Personagem> personagens;

	public static void main(String[] args) {

		personagens = new HashMap<>();

		Boolean loop = true;
		Scanner scanner = new Scanner(System.in);
		do {
			loop = Menus.mainMenuPersonagens(loop, scanner);
			Menus.espacamentoPadrao();
		} while (loop == true);
	}
}
package com.github.andremarchiori;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	public static Map<Integer, Personagem> personagens;

	public static void main(String[] args) {
		Boolean loop = true;
		Scanner scanner = new Scanner(System.in);
		do {
			loop = Menus.menuPrincipal(loop, scanner);
			Menus.espacamentoPadrao();
		}while(loop == true);
	}
	
	public static void MenuPersonagensLoop() { //essencial nesta classe
		personagens = new HashMap<>();

		Boolean loopPersonagens = true;
		Scanner scannerPersonagens = new Scanner(System.in);
		
		do {
			loopPersonagens = Menus.mainMenuPersonagens(loopPersonagens, scannerPersonagens);
			Menus.espacamentoPadrao();
		} while (loopPersonagens == true);
	}
}
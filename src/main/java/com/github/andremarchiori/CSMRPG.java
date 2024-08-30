package com.github.andremarchiori;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CSMRPG {
	
	public static Map<Integer, Personagem> personagens;

	public static void main(String[] args) {
		Boolean loop = true;
		Scanner scanner = new Scanner(System.in);
		do {
			Menus.menuPrincipal(loop, scanner);
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
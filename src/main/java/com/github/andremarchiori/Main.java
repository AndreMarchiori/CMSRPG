package com.github.andremarchiori;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	public static Map<Integer, Personagem> personagens;

	public static void main(String[] args) {
		Menus menus = new Menus();
		Boolean loop = true;
		Scanner scanner = new Scanner(System.in);
		do {
			loop = menus.menuPrincipal(loop, scanner);
			menus.espacamentoPadrao();
		}while(loop == true);
	}
	
	public static void MenuPersonagensLoop() { //essencial nesta classe
		Menus menus = new Menus();
		personagens = new HashMap<>();

		Boolean loopPersonagens = true;
		Scanner scannerPersonagens = new Scanner(System.in);
		
		do {
			loopPersonagens = menus.mainMenuPersonagens(loopPersonagens, scannerPersonagens);
			menus.espacamentoPadrao();
		} while (loopPersonagens == true);
	}
}
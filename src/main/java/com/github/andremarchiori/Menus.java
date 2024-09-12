package com.github.andremarchiori;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menus {
	public static Boolean menuPrincipal(Boolean loop, Scanner scanner) {

		switch (scanner.nextInt()) {
		case 1:
			espacamentoPadrao();
			CSMRPG.MenuPersonagensLoop();
			break;
		case 2:
			espacamentoPadrao();
			mainMenuCombatSis(loop, scanner);
			break;
		case 5:
			espacamentoPadrao();
			System.out.println("Finalizando");
			return false;
		default:
			System.out.println("Opção Inválida");
		}
		return true;
	}

	public static Boolean mainMenuCombatSis(Boolean loop, Scanner scanner) {
		do {

			List<String> menu = new ArrayList<String>();
			menu.add("Sistemas de Combate");
			menu.add(";-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=;");
			menu.add("1. Adicionar \"Token\"");
			menu.add("2. Listar Player");
			menu.add("3. Listar NPCS/Monstros");
			menu.add("");
			menu.add("5. Retornar");
			menu.add(";-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=;");

			for (String cadaLinha : menu)
				System.out.println(cadaLinha);

			switch (scanner.nextInt()) {
			case 1:
				espacamentoPadrao();
				File file = new File("combatLog.txt");
				System.out.println(" Cadastro de Personagem");
				System.out.println(";¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨;");
				if (file.exists()) {
					IOTxt.combateCadastro();
				} else {
					try {
						file.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println(";¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨¨;");
				break;
			case 2:
				espacamentoPadrao();
				System.out.println(" Lista de Players");
				System.out.println(";****************************************************************;");
				IOTxt.listarTokensPlayers();
				System.out.println(";****************************************************************;");
				break;
			case 3:
				espacamentoPadrao();
				System.out.println(" Lista de NPCs/Monstros");
				System.out.println(";****************************************************************;");
				IOTxt.listarTokensNpcs();
				System.out.println(";****************************************************************;");
				break;
			case 5:
				espacamentoPadrao();
				System.out.println("Retornando");
				return false;
			default:
				espacamentoPadrao();
				System.out.println("Opção inválida");
			}
		} while (loop = true);
		return loop;
	}

	public static Boolean mainMenuPersonagens(Boolean loop, Scanner scanner) {

		List<String> menu = new ArrayList<>();
		menu.add("Sistema de Armazenamento de Personas");
		menu.add(";----------------------------------------------------------------;");
		menu.add("Insira uma opcao:                                X");
		menu.add("1. Cadastro de Personagem                        X");
		menu.add("2. Listagem de Personagens                       X");
		menu.add("3. Alteração                                     X");
		menu.add("4. \"Excluir\" Arquivo                             X");
		menu.add("5. Retornar                                      X");
		menu.add(";----------------------------------------------------------------;");

		for (String cadaLinha : menu) {
			System.out.println(cadaLinha);
		}
		menu.clear();

		System.out.print("> ");

		switch (scanner.nextInt()) {
		case 1:
			espacamentoPadrao();
			File file = new File("characters.txt");
			System.out.println(" Cadastro de Personagem");
			System.out.println(";----------------------------------------------------------------;");
			if (file.exists()) {
				try {
					IOTxt.cadastrarPersonagem();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			System.out.println(";----------------------------------------------------------------;");
			break;
		case 2:
			espacamentoPadrao();
			System.out.println(" Listagem de Personagens");
			System.out.println(";----------------------------------------------------------------;");
			IOTxt.listarPersonagens();
			System.out.println(";----------------------------------------------------------------;");
			break;
		case 3:
			altMenuPersonagens(scanner);
			break;
		case 4:
			IOTxt.clearTheFile();
			break;
		case 5:
			espacamentoPadrao();
			System.out.println("Retornando para o menu principal");
			loop = false;
			break;
		default:
			throw new IllegalArgumentException("A opcao inserida não existe");
		}
		return loop;
	}

	public static void altMenuPersonagens(Scanner scanner) {
		while (true) {
			espacamentoPadrao();

			List<String> menu = new ArrayList<>();
			menu.add("Menu de Alterações");
			menu.add(";----------------------------------------------------------------;");
			menu.add("Insira uma opcao:                                              X");
			menu.add("1. Adicionar XP                                                X");
			menu.add("2. Reduzir XP                                                  X");
			menu.add("3. Remover Personagem                                          X");
			menu.add("4. Recuperar Personagem                                        X");
			menu.add("5. Retornar                                                    X");
			menu.add(";----------------------------------------------------------------;");

			for (String cadaLinha : menu) {
				System.out.println(cadaLinha);
			}
			menu.clear();

			System.out.print("> ");

			switch (scanner.nextInt()) {
			case 1:
				espacamentoPadrao();
				System.out.println(" Adição de XP");
				System.out.println(";----------------------------------------------------------------;");
				IOTxt.carregarPersonagens();
				IOTxt.callAdd(scanner);
				System.out.println(";----------------------------------------------------------------;");
				break;
			case 2:
				espacamentoPadrao();
				System.out.println(" Redução de XP");
				System.out.println(";----------------------------------------------------------------;");
				IOTxt.carregarPersonagens();
				IOTxt.callSub(scanner);
				System.out.println(";----------------------------------------------------------------;");
				break;
			case 3:
				IOTxt.carregarPersonagens();
				espacamentoPadrao();
				System.out.println(" Remoção de personagem");
				System.out.println(";----------------------------------------------------------------;");
				IOTxt.callRemoves(scanner);
				System.out.println(";----------------------------------------------------------------;");
				break;
			case 4:
				IOTxt.carregarPersonagens();
				espacamentoPadrao();
				System.out.println(" Recuperação de Personagem");
				System.out.println(";----------------------------------------------------------------;");
				IOTxt.callRecuperacao(scanner);
				System.out.println(";----------------------------------------------------------------;");
				break;
			case 5:
				espacamentoPadrao();
				System.out.println("Retornando");
				return;
			default:
				System.out.println("Opção Invalida");
				break;
			}
		}
	}

	public static void espacamentoPadrao() {
		System.out.printf("%n%n%n%n");
	}
}

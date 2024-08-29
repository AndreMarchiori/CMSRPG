package com.github.andremarchiori;

import java.util.ArrayList;
import java.util.List;

public class Menus {
	public static void chamarMenuPrincipal() {
		List<String> menu = new ArrayList<>();
		menu.add("Sistema de Armazenamento de Personas");
		menu.add(";----------------------------------------------------------------;");
		menu.add("Insira uma opcao:");
		menu.add("1. Cadastro de Personagem");
		menu.add("2. Listagem de Personagens");
		menu.add("3. Alteração ");
		menu.add("4. \"Excluir\" Arquivo");
		menu.add("5. Finalizar");
		menu.add(";----------------------------------------------------------------;");

		for (String cadaLinha : menu) {
			System.out.println(cadaLinha);
		}
		menu.clear();

		System.out.print("> ");
	}
	
	public static void chamarMenuAlteracao() {
		List<String> menu = new ArrayList<>();
		menu.add("Menu de Alterações");
		menu.add(";----------------------------------------------------------------;");
		menu.add("Insira uma opcao:");
		menu.add("1. Adicionar XP");
		menu.add("2. Reduzir XP");
		menu.add("3. Remover Personagem");
		menu.add("4. Recuperar Personagem");
		menu.add("5. Retornar");
		menu.add(";----------------------------------------------------------------;");

		for (String cadaLinha : menu) {
			System.out.println(cadaLinha);
		}
		menu.clear();

		System.out.print("> ");
		
		
	}
}

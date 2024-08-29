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
		menu.add("3. Adicionar XP");
		menu.add("4. Finalizar");
		menu.add(";----------------------------------------------------------------;");

		for (String cadaLinha : menu) {
			System.out.println(cadaLinha);
		}
		menu.clear();

		System.out.print("> ");
	}
}

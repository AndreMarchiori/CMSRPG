package com.github.andremarchiori;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CSMRPG {

	private static Map<Integer, Personagem> personagens;

	public static void main(String[] args) {
		personagens = new HashMap<>();

		Boolean loop = true;
		Scanner scanner = new Scanner(System.in);
		do {

			chamarMenuPrincipal();

			switch (scanner.nextInt()) {
			case 1:
				espacamentoPadrao();
				File file = new File("characters.txt");
				System.out.println(" Cadastro de Personagem");
				System.out.println(";----------------------------------------------------------------;");
				if (file.exists()) {
					try {
						cadastrarPersonagem();
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
				break;
			case 2:
				espacamentoPadrao();
				System.out.println(" Listagem de Personagens");
				System.out.println(";----------------------------------------------------------------;");
				listarPersonagens();
				System.out.println(";----------------------------------------------------------------;");
				break;
			case 3:
				espacamentoPadrao();
				carregarPersonagens();
				System.out.println(" Adição de XP");
				System.out.println(";----------------------------------------------------------------;");
				callAdd(scanner);
				System.out.println(";----------------------------------------------------------------;");
				break;
			case 4:
				espacamentoPadrao();
				System.out.println("Finalizado");
				loop = false;
				break;
			case 5:
				espacamentoPadrao();
				System.out.println(";----------------------------------------------------------------;");
				System.out.println("Deletas");
//				callRemoves(scanner);
				System.out.println(";----------------------------------------------------------------;");
				break;
			default:
				throw new IllegalArgumentException("A opcao inserida não existe");
			}
			espacamentoPadrao();
		} while (loop == true);
	}

//	public static void callRemoves(Scanner scanner) {
//		int idPersonagem;
//		ArrayList<Personagem> listaDePersonagens = new ArrayList<>();
//
//		while (true) {
//			System.out.printf("%nInsira o ID do personagem: ");
//			idPersonagem = Integer.parseInt(scanner.next());
//			if (idPersonagem == -1) {
//				return;
//			} else if (!personagens.containsKey(idPersonagem)) {
//				System.err.println("Id não encontrado. Insira -1 para encerrar ou tente novamente.");
//			} else {
//				break;
//			}
//		}
//
//		carregarPersonagens();
//		personagens.get(idPersonagem).setExLogic(0);
//		if (listaDePersonagens.isEmpty()) {
//
//			try {
//				for (int i = 0; i < proximoId() - 1; i++) {
//					listaDePersonagens.add(personagens.get(i + 1));
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		clearTheFile();
//		salvarPersonagem(listaDePersonagens);
//		System.out.println("BaNiDo");
//	}

	public static void callAdd(Scanner scanner) {
		int idPersonagem;
		ArrayList<Personagem> listaDePersonagens = new ArrayList<>();

		while (true) {
			System.out.printf("%nInsira o ID do personagem: ");
			idPersonagem = scanner.nextInt();
			if (idPersonagem == -1) {
				return;
			} else if (!personagens.containsKey(idPersonagem)) {
				System.err.println("Id não encontrado. Insira -1 para encerrar ou tente novamente.");
			} else {
				break;
			}
		}

		carregarPersonagens();
		System.out.printf("%nInsira o valor de experiência a ser adicionado: ");

		personagens.get(idPersonagem).addExperience(scanner.nextLong());
		if (listaDePersonagens.isEmpty()) {

			try {
				for (int i = 0; i < proximoId() - 1; i++) {
					listaDePersonagens.add(personagens.get(i + 1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		clearTheFile();
		salvarPersonagem(listaDePersonagens);
		System.out.println("Adição Completa");

	}

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

	public static void espacamentoPadrao() {
		System.out.printf("%n%n%n%n");
	}

	public static void cadastrarPersonagem() throws IOException {
		Personagem personagem = new Personagem();
		personagem.setId(proximoId());
		System.out.println("id = " + personagem.getId());
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira o nome do personagem");
		personagem.setName(scanner.nextLine());

		System.out.println("Insira o xp do personagem");
		personagem.setExperience(scanner.nextLong());

		salvarPersonagem(personagem);
		System.out.println("Cadastro Concluido");
	}

	private static void carregarPersonagens() {
		try {
			BufferedReader bufferedReader = openReaderPersonagens();
			String linha = bufferedReader.readLine();
			while (linha != null) {
				Personagem personagem = parsePersonagem(linha);
				personagens.put(personagem.getId(), personagem);
				linha = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static Personagem parsePersonagem(String linha) {
		String[] colunas = linha.split(";");
		Personagem personagem = new Personagem();
		personagem.setId(Integer.parseInt(colunas[0]));
		personagem.setName(colunas[1]);
		personagem.setLevel(Integer.parseInt(colunas[2]));
		personagem.setExperience(Long.parseLong(colunas[3]));
		return personagem;
	}

	public static int proximoId() throws IOException {
		int maiorId = 0;

		BufferedReader bufferedReader = openReaderPersonagens();
		String linha = bufferedReader.readLine();
		while (linha != null) {
			String[] colunas = linha.split(";");
			maiorId = obtencaoDeMaiorID(maiorId, linha, colunas[0]);
			linha = bufferedReader.readLine();
		}
		bufferedReader.close();

		return ++maiorId;
	}

	private static int obtencaoDeMaiorID(int maiorId, String linha, String coluna) {
		try {
			var id = Integer.parseInt(coluna);
			if (id > maiorId) {
				maiorId = id;
			}
		} catch (NumberFormatException e) {
			System.err.println("falha ao fazer parse da linha: [" + linha + "], falha: [" + e.getMessage() + "]");
			e.printStackTrace();
		}
		return maiorId;
	}

	private static void salvarPersonagem(Personagem personagem) {
		try {
			BufferedWriter bufferedWriter = openWriterPersonagens();
			bufferedWriter.write(personagem.toString());
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void salvarPersonagem(ArrayList<Personagem> listaDePersonagens) {
		try {
			BufferedWriter bufferedWriter = openWriterPersonagens();
			for (int j = 0; j < listaDePersonagens.size(); j++) {
				bufferedWriter = openWriterPersonagens();
				bufferedWriter.write(listaDePersonagens.get(j).toString());
				bufferedWriter.newLine();
				bufferedWriter.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void listarPersonagens() {
		try {
			BufferedReader bufferedReader = openReaderPersonagens();
			String linhas = bufferedReader.readLine();
			System.out.printf("  %-5s%-35s%-10s%-12s%n", "ID", "NOME", "LEVEL", "EXPERIENCIA");
			while (linhas != null) {
				String[] colunas = linhas.split(";");
//				if (Integer.parseInt(colunas[4]) == 0) {
//					linhas = bufferedReader.readLine();
//					continue;
//				}
				System.out.println(
						String.format("  %-5s%-35s%-10s%-12s", colunas[0], colunas[1], colunas[2], colunas[3]));
				linhas = bufferedReader.readLine();
			}

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static BufferedWriter openWriterPersonagens() throws IOException {
		FileWriter escreveDados = new FileWriter("characters.txt", true);
		return new BufferedWriter(escreveDados);
	}

	private static BufferedReader openReaderPersonagens() throws FileNotFoundException {
		FileReader leitor = new FileReader("characters.txt");
		return new BufferedReader(leitor);
	}

	private static void clearTheFile() {
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

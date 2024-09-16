package com.github.andremarchiori;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class IOTxt {
	
	public static BufferedWriter openWriterCombate() throws IOException {
		FileWriter escreveDados = new FileWriter("combatLog.txt", true);
		return new BufferedWriter(escreveDados);
	}

	public static BufferedReader openReaderCombate() throws FileNotFoundException {
		FileReader leitor = new FileReader("combatLog.txt");
		return new BufferedReader(leitor);
	}
	
	public static void clearTheFileCombate() {
		try {
			FileWriter fwOb = new FileWriter("combatLog.txt", false);
			PrintWriter pwOb = new PrintWriter(fwOb, false);
			pwOb.flush();
			pwOb.close();
			fwOb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void combateCadastro() {
		combateChar token = new combateChar();
		try {
			token.setId(proximoIdCombate());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("id = " + token.getId());
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		System.out.println("Insira o nome do token");
		token.setName(scanner.nextLine());

		System.out.println("Insira a vida maxima do personagem");
		token.setMaxHp(scanner.nextInt());
		
		System.out.println("Insira o tipo do personagem (1-Player|2-NPC/Monstro)");
		token.setType(scanner.nextInt());

		salvarPersonagemCombate(token);
		System.out.println("Cadastro Concluido");
	}

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

	public static void callRemoves(Scanner scanner) {
		int idPersonagem;
		ArrayList<Personagem> listaDePersonagens = new ArrayList<>();

		while (true) {
			System.out.printf("%n-1 = retorna");
			System.out.printf("%n 0 = listagem");
			System.out.printf("%nInsira o ID do personagem: ");
			idPersonagem = Integer.parseInt(scanner.next());
			if (idPersonagem == -1) {
				return;
			} else if (idPersonagem == 0) {
				Menus.espacamentoPadrao();
				listarPersonagens();
				Menus.espacamentoPadrao();
			} else if (!Main.personagens.containsKey(idPersonagem)) {
				System.err.println("Id não encontrado. Insira -1 para encerrar ou tente novamente.");
			} else {
				break;
			}
		}

		Main.personagens.get(idPersonagem).setExLogic(0);
		if (listaDePersonagens.isEmpty()) {

			try {
				for (int i = 0; i < proximoId() - 1; i++) {
					listaDePersonagens.add(Main.personagens.get(i + 1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		IOTxt.clearTheFile();
		salvarPersonagem(listaDePersonagens);
		System.out.println("BaNiDo");
	}

	public static void callRecuperacao(Scanner scanner) {
		int idPersonagem;
		ArrayList<Personagem> listaDePersonagens = new ArrayList<>();

		while (true) {
			System.out.printf("%n-1 = retorna");
			System.out.printf("%n 0 = listagem");
			System.out.printf("%nInsira o ID do personagem: ");
			idPersonagem = Integer.parseInt(scanner.next());
			if (idPersonagem == -1) {
				return;
			} else if (idPersonagem == 0) {
				Menus.espacamentoPadrao();
				listarDesativados();
				Menus.espacamentoPadrao();
			} else if (!Main.personagens.containsKey(idPersonagem)) {
				System.err.println("Id não encontrado. Insira -1 para encerrar ou tente novamente.");
			} else {
				break;
			}
		}

		Main.personagens.get(idPersonagem).setExLogic(1);
		if (listaDePersonagens.isEmpty()) {

			try {
				for (int i = 0; i < proximoId() - 1; i++) {
					listaDePersonagens.add(Main.personagens.get(i + 1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		IOTxt.clearTheFile();
		salvarPersonagem(listaDePersonagens);
		System.out.println("Personagem restaurado");
	}

	public static void callAdd(Scanner scanner) {
		int idPersonagem;
		ArrayList<Personagem> listaDePersonagens = new ArrayList<>();

		while (true) {
			System.out.printf("%n-1 = retorna");
			System.out.printf("%n 0 = listagem");
			System.out.printf("%nInsira o ID do personagem: ");
			idPersonagem = scanner.nextInt();
			if (idPersonagem == -1) {
				return;
			} else if (idPersonagem == 0) {
				Menus.espacamentoPadrao();
				listarPersonagens();
				Menus.espacamentoPadrao();
			} else if (!Main.personagens.containsKey(idPersonagem)) {
				System.err.println("Id Inválido. Insira -1 para encerrar ou tente novamente.");
			} else {
				break;
			}
		}
		carregarPersonagens();
		if (Main.personagens.get(idPersonagem).getExLogic() == 0) {
			System.err.println("Id Inválido.");
			return;
		}
		System.out.printf("%nInsira o valor de experiência a ser adicionado: ");

		Main.personagens.get(idPersonagem).addExperience(scanner.nextLong());
		if (listaDePersonagens.isEmpty()) {

			try {
				for (int i = 0; i < proximoId() - 1; i++) {
					listaDePersonagens.add(Main.personagens.get(i + 1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		IOTxt.clearTheFile();
		salvarPersonagem(listaDePersonagens);
		System.out.println("Adição Completa");
	}

	public static void callSub(Scanner scanner) {
		int idPersonagem;
		ArrayList<Personagem> listaDePersonagens = new ArrayList<>();

		while (true) {
			System.out.printf("%n-1 = retorna");
			System.out.printf("%n 0 = listagem");
			System.out.printf("%nInsira o ID do personagem: ");
			idPersonagem = scanner.nextInt();
			if (idPersonagem == -1) {
				return;
			} else if (idPersonagem == 0) {
				Menus.espacamentoPadrao();
				listarPersonagens();
				Menus.espacamentoPadrao();
			} else if (!Main.personagens.containsKey(idPersonagem)) {
				System.err.println("Id Inválido. Insira -1 para encerrar ou tente novamente.");
			} else {
				break;
			}
		}
		carregarPersonagens();
		if (Main.personagens.get(idPersonagem).getExLogic() == 0) {
			System.err.println("Id Inválido.");
			return;
		}
		System.out.printf("%nInsira o valor de experiência a ser reduzido(valores positivo apenas): ");

		Main.personagens.get(idPersonagem).subExperience(scanner.nextLong());
		if (listaDePersonagens.isEmpty()) {

			try {
				for (int i = 0; i < proximoId() - 1; i++) {
					listaDePersonagens.add(Main.personagens.get(i + 1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		IOTxt.clearTheFile();
		salvarPersonagem(listaDePersonagens);
		System.out.println("Subtração Completa");
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

	public static void carregarPersonagens() {
		try {
			BufferedReader bufferedReader = IOTxt.openReaderPersonagens();
			String linha = bufferedReader.readLine();
			while (linha != null) {
				Personagem personagem = parsePersonagem(linha);
				Main.personagens.put(personagem.getId(), personagem);
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
		personagem.setExLogic(Integer.parseInt(colunas[4]));
		return personagem;
	}

	public static int proximoId() throws IOException {
		int maiorId = 0;

		BufferedReader bufferedReader = IOTxt.openReaderPersonagens();
		String linha = bufferedReader.readLine();
		while (linha != null) {
			String[] colunas = linha.split(";");
			maiorId = obtencaoDeMaiorID(maiorId, linha, colunas[0]);
			linha = bufferedReader.readLine();
		}
		bufferedReader.close();

		return ++maiorId;
	}
	
	public static int proximoIdCombate() throws IOException {
		int maiorId = 0;

		BufferedReader bufferedReader = IOTxt.openReaderCombate();
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
			BufferedWriter bufferedWriter = IOTxt.openWriterPersonagens();
			bufferedWriter.write(personagem.toString());
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void salvarPersonagemCombate(combateChar personagem) {
		try {
			BufferedWriter bufferedWriter = IOTxt.openWriterCombate();
			bufferedWriter.write(personagem.toString());
			bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void salvarPersonagem(ArrayList<Personagem> listaDePersonagens) {
		try {
			BufferedWriter bufferedWriter = IOTxt.openWriterPersonagens();
			for (int j = 0; j < listaDePersonagens.size(); j++) {
				bufferedWriter = IOTxt.openWriterPersonagens();
				bufferedWriter.write(listaDePersonagens.get(j).toString());
				bufferedWriter.newLine();
				bufferedWriter.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void listarPersonagens() {
		try {
			BufferedReader bufferedReader = IOTxt.openReaderPersonagens();
			String linhas = bufferedReader.readLine();
			System.out.printf("  %-5s%-35s%-10s%-12s%n", "ID", "NOME", "LEVEL", "EXPERIENCIA");
			while (linhas != null) {
				String[] colunas = linhas.split(";");
				if (Integer.parseInt(colunas[4]) == 0) {
					linhas = bufferedReader.readLine();
					continue;
				}
				System.out.println(
						String.format("  %-5s%-35s%-10s%-12s", colunas[0], colunas[1], colunas[2], colunas[3]));
				linhas = bufferedReader.readLine();
			}

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarTokensPlayers() {
		try {
			BufferedReader bufferedReader = IOTxt.openReaderCombate();
			String linhas = bufferedReader.readLine();
			System.out.printf("  %-5s%-15s%s/%s%n", "ID", "NOME", "VidaMax/VidaAtual");
			while (linhas != null) {
				String[] colunas = linhas.split(";");
				if (Integer.parseInt(colunas[4]) != 1 || Integer.parseInt(colunas[5]) == 0) {
					linhas = bufferedReader.readLine();
					continue;
				}
				System.out.println(
						String.format("  %-5s%-15s%s/%s", colunas[0], colunas[1], colunas[2], colunas[3]));
				linhas = bufferedReader.readLine();
			}

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void listarTokensNpcs() {
		try {
			BufferedReader bufferedReader = IOTxt.openReaderCombate();
			String linhas = bufferedReader.readLine();
			System.out.printf("  %-5s%-15s%s/%s%n", "ID", "NOME", "VidaMax/VidaAtual");
			while (linhas != null) {
				String[] colunas = linhas.split(";");
				if (Integer.parseInt(colunas[4]) != 2 || Integer.parseInt(colunas[5]) == 0) {
					linhas = bufferedReader.readLine();
					continue;
				}
				System.out.println(
						String.format("  %-5s%-15s%s/%s", colunas[0], colunas[1], colunas[2], colunas[3]));
				linhas = bufferedReader.readLine();
			}

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void listarDesativados() {
		try {
			BufferedReader bufferedReader = IOTxt.openReaderPersonagens();
			String linhas = bufferedReader.readLine();
			System.out.printf("  %-5s%-35s%-10s%-12s%n", "ID", "NOME", "LEVEL", "EXPERIENCIA");
			while (linhas != null) {
				String[] colunas = linhas.split(";");
				if (Integer.parseInt(colunas[4]) == 1) {
					linhas = bufferedReader.readLine();
					continue;
				}
				System.out.println(
						String.format("  %-5s%-35s%-10s%-12s", colunas[0], colunas[1], colunas[2], colunas[3]));
				linhas = bufferedReader.readLine();
			}

			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

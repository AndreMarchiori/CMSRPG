package com.github.andremarchiori.gInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JTextArea;

public class MenuListar extends MenuFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4896254325075871190L;
	private static JTextArea txtLista;
	private JLabel lblMain;
	
	public MenuListar() {
		lblMain = new JLabel("Listagem de Personas");
		lblMain.setBounds(10, 10, 300, 15);
		add(lblMain);
		lblMain = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMain.setBounds(0, 30, 510, 15);
		add(lblMain);
	
		txtLista = new JTextArea();
		txtLista.setBounds(20, 50, 445, 320);
		txtLista.setEditable(false);
		listarPersonagens();
		add(txtLista);
		
		lblMain = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMain.setBounds(0, 380, 510, 15);
		add(lblMain);

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MenuPersonagens();
				dispose();
			}
		});
		
		repaint();
	}
	
	public static void listarPersonagens() {
		try {
			BufferedReader bufferedReader = iot.openReaderPersonagens();
			String linhas = bufferedReader.readLine();
			txtLista.append(String.format("%s%n", "-----------------------------------------------------------------------------------------------------------------"));
			txtLista.append(String.format("  %-5s| %-30s| %-20s| %-12s%n", "ID", "Nome", "Nível", "Experiencia"));
			while (linhas != null) {
				String[] colunas = linhas.split(";");
				if (Integer.parseInt(colunas[4]) == 0) {
					linhas = bufferedReader.readLine();
					continue;
				}
				txtLista.append(String.format("  %-5s| %-35s| %-20s| %-12s%n", colunas[0], colunas[1], colunas[2], colunas[3]));
				linhas = bufferedReader.readLine();
			}
			txtLista.append(String.format("%s%n", "-----------------------------------------------------------------------------------------------------------------"));
			bufferedReader.close();
		} catch (IOException e) {
			txtLista.append(String.format("Erro na abertura de arquivo, para resolver tente abrir a area de cadastrar%n"));
			txtLista.append(String.format(" personagens. Se o problema persistir contade o criador do código ou alguém mais %n"));
			txtLista.append("competente que ele.");
		}
	}
}

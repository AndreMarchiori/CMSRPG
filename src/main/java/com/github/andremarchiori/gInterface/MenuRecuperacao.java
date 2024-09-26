package com.github.andremarchiori.gInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.andremarchiori.Personagem;

public class MenuRecuperacao extends MenuFrame{
	private JLabel lblMain;
	private JTextArea txtLista;
	private JLabel lblId;
	private JTextField txtId;
	private JButton btnConfirmar;
	private JLabel lblConfirmar;
	
	public MenuRecuperacao() {
		MenuExpMod.personagens = new HashMap<>();
		lblMain = new JLabel("Menu de Recuperação de Personagem");
		lblMain.setBounds(10, 10, 300, 15);
		add(lblMain);
		lblMain = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMain.setBounds(0, 30, 510, 15);
		add(lblMain);
		
		txtLista = new JTextArea();
		txtLista.setBounds(20, 50, 445, 240);
		txtLista.setEditable(false);
		listarPersonagens(txtLista);
		add(txtLista);
		
		lblId = new JLabel("Insira o ID");
		lblId.setBounds(110, 295, 100, 16);
		add(lblId);
		txtId = new JTextField("");
		txtId.setAlignmentX(CENTER_ALIGNMENT);
		txtId.setAlignmentY(CENTER_ALIGNMENT);
		txtId.setBounds(120, 325, 35, 30);
		add(txtId);
		
		lblConfirmar = new JLabel("");
		lblConfirmar.setBounds(160, 350, 200, 15);
		add(lblConfirmar);
		
		btnConfirmar = new JButton("Recuperar Id Selecionado");
		btnConfirmar.setBounds(160, 325, 200, 30);
		btnConfirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addMouseMotionListener(new MouseHandler());
				try {
					int id = Integer.parseInt(txtId.getText());
					callRecuperacao(id);
					txtId.setText("");
					lblConfirmar.setText("Persona recuperada com sucesso!");
					txtLista.setText("");
					listarPersonagens(txtLista);
					repaint();
				}catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "O valor do Id PRECISA ser um número!!!");
				}
				
			}
		});
		add(btnConfirmar);
		
		lblMain = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMain.setBounds(0, 380, 510, 15);
		add(lblMain);
		
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new MenuAlteracao();
				dispose();
			}
		});
		
		repaint();
	}
	
	public void callRecuperacao(int id) {
		MenuExpMod.carregarPersonagens();
		int idPersonagem = id;
		ArrayList<Personagem> listaDePersonagens = new ArrayList<>();
		
		if (!MenuExpMod.personagens.containsKey(idPersonagem)) {
			JOptionPane.showMessageDialog(null, "Id Não encontrado");
			}
		
		MenuExpMod.personagens.get(idPersonagem).setExLogic(1);
		if (listaDePersonagens.isEmpty()) {
			try {
				for (int i = 0; i < iot.proximoId() - 1; i++) {
					listaDePersonagens.add(MenuExpMod.personagens.get(i + 1));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		iot.clearTheFile();
		iot.salvarPersonagem(listaDePersonagens);
		System.out.println("Recuperado");
	}
	
	public static void listarPersonagens(JTextArea txtLista) {
		try {
			BufferedReader bufferedReader = iot.openReaderPersonagens();
			String linhas = bufferedReader.readLine();
			txtLista.append(String.format("%s%n", "-----------------------------------------------------------------------------------------------------------------"));
			txtLista.append(String.format("  %-5s| %-30s| %-20s| %-12s%n", "ID", "Nome", "Nível", "Experiencia"));
			while (linhas != null) {
				String[] colunas = linhas.split(";");
				if (Integer.parseInt(colunas[4]) == 1) {
					linhas = bufferedReader.readLine();
					continue;
				}
				txtLista.append(String.format("  %-5s| %-35s| %-20s| %-12s%n", colunas[0], colunas[1], colunas[2], colunas[3]));
				linhas = bufferedReader.readLine();
			}
			txtLista.append(String.format("%s%n", "-----------------------------------------------------------------------------------------------------------------"));
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class MouseHandler implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			lblConfirmar.setText("");
			repaint();

		}
	}
}

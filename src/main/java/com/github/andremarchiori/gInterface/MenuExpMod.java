package com.github.andremarchiori.gInterface;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.andremarchiori.IOTxt;
import com.github.andremarchiori.Main;
import com.github.andremarchiori.Personagem;

public class MenuExpMod extends MenuFrame {
	private JLabel lblMain;
	private JLabel lblMult1;
	private JLabel lblMult2;
	private JLabel lblId;
	private JLabel lblExp;
	private JButton btnSum;
	private JButton btnSub;
	private JTextField txtMult;
	private JTextField txtId;
	private JTextField txtExp;
	private JTextArea txtLista;
	public static Map<Integer, Personagem> personagens;
	
	public MenuExpMod() {
		
		MenuExpMod.personagens = new HashMap<>();
		lblMain = new JLabel("Menu de Edição de Experiencia");
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
		
		
		lblMult1 = new JLabel("Multiplicador");
		lblMult1.setBounds(5, 295, 100, 15);
		add(lblMult1);
		lblMult2 = new JLabel("(valores ex: 1.5)");
		lblMult2.setBounds(5, 310, 100, 15);
		add(lblMult2);
		txtMult = new JTextField("1");
		txtMult.setBounds(20, 325, 35, 30);
		add(txtMult);
		
		lblId = new JLabel("Insira o ID");
		lblId.setBounds(110, 295, 100, 15);
		add(lblId);
		txtId = new JTextField("");
		txtId.setAlignmentX(CENTER_ALIGNMENT);
		txtId.setAlignmentY(CENTER_ALIGNMENT);
		txtId.setBounds(120, 325, 35, 30);
		add(txtId);
		
		lblExp = new JLabel("Experiencia");
		lblExp.setBounds(194, 288, 100, 30);
		add(lblExp);
		txtExp = new JTextField();
		txtExp.setBounds(180, 325, 100, 30);
		add(txtExp);
		
		btnSum = new JButton("+");
		btnSum.setAlignmentX(CENTER_ALIGNMENT);
		btnSum.setAlignmentY(CENTER_ALIGNMENT);
		btnSum.setFont(new Font("Arial", Font.PLAIN, 24));
		btnSum.setBounds(320, 325, 75, 30);
		btnSum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					var mult = Double.parseDouble(txtMult.getText());
					var exp = Integer.parseInt(txtExp.getText());
					var id = Integer.parseInt(txtId.getText());
					if(mult <= 0) {
						mult = 1;
					}
					carregarPersonagens();
					iot.callAdd((int) (exp*mult), id);
					txtLista.setText("");
					listarPersonagens(txtLista);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				repaint();
			}
		});
		add(btnSum);
		
		btnSub = new JButton("-");
		btnSub.setAlignmentX(CENTER_ALIGNMENT);
		btnSub.setAlignmentY(CENTER_ALIGNMENT);
		btnSub.setFont(new Font("Arial", Font.PLAIN, 24));
		btnSub.setBounds(400, 325, 75, 30);
		btnSub.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					var mult = Double.parseDouble(txtMult.getText());
					var exp = Integer.parseInt(txtExp.getText());
					var id = Integer.parseInt(txtId.getText());
					if(mult <= 0) {
						mult = 1;
					}
					carregarPersonagens();
					iot.callSub((int) (exp*mult), id);
					txtLista.setText("");
					listarPersonagens(txtLista);
				}catch(Exception ex) {
					ex.printStackTrace();
				}
				repaint();
			}
		});
		add(btnSub);
		
		
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
	
	public static void carregarPersonagens() {
		try {
			BufferedReader bufferedReader = iot.openReaderPersonagens();
			String linha = bufferedReader.readLine();
			while (linha != null) {
				Personagem personagem = iot.parsePersonagem(linha);
				MenuExpMod.personagens.put(personagem.getId(), personagem);
				linha = bufferedReader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static void listarPersonagens(JTextArea txtLista) {
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
			e.printStackTrace();
		}
	}
}

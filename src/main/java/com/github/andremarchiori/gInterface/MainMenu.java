package com.github.andremarchiori.gInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MainMenu extends MenuFrame {
	private JLabel lblMainLabel;
	private JButton btn1;
	private JButton btn2;

	public MainMenu() {

		// Inserção das labels
		lblMainLabel = new JLabel("Character Sistem Manager for Roleplay Gaming");
		lblMainLabel.setBounds(10, 10, 300, 15);
		add(lblMainLabel);
		lblMainLabel = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMainLabel.setBounds(0, 30, 510, 15);
		add(lblMainLabel);
		lblMainLabel = new JLabel("Insira uma das opções");
		lblMainLabel.setBounds(180, 50, 300, 15);
		add(lblMainLabel);

		// botões para inicializar os menus
		btn1 = new JButton("Armazenamento de Personas");
		btn1.setBounds(115, 250, 250, 45);		
		btn1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unused")
			MenuPersonagens menuPersonagens = new MenuPersonagens();
			setVisible(false);
			}
		});
		add(btn1);

		btn2 = new JButton("Sistemas de combate");
		btn2.setBounds(115, 200, 250, 45);
		add(btn2);

		lblMainLabel = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMainLabel.setBounds(0, 380, 510, 15);
		add(lblMainLabel);
		
		repaint();
		
		btnExit.setText("Sair");
	}
}

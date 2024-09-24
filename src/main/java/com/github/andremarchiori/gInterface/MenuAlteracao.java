package com.github.andremarchiori.gInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MenuAlteracao extends MenuFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7120226220592655330L;
	private JLabel lblMain;
	private JButton btnAtlExp;
	private JButton btnRemover;
	private JButton btnRecuperar;

	public MenuAlteracao() {
		lblMain = new JLabel("Menu de Edição de Persona");
		lblMain.setBounds(10, 10, 300, 15);
		add(lblMain);
		lblMain = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMain.setBounds(0, 30, 510, 15);
		add(lblMain);
		lblMain = new JLabel("Insira uma das opções");
		lblMain.setBounds(180, 50, 300, 15);
		add(lblMain);

		btnAtlExp = new JButton("Alterar Experiencia");
		btnAtlExp.setBounds(115, 250, 250, 45);
		btnAtlExp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new MenuExpMod();
				dispose();
			}
		});
		add(btnAtlExp);

		btnRemover = new JButton("Apagar Personagem");
		btnRemover.setBounds(115, 200, 250, 45);
		add(btnRemover);

		btnRecuperar = new JButton("Recuperar Personagem");
		btnRecuperar.setBounds(115, 150, 250, 45);
		add(btnRecuperar);

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
}

package com.github.andremarchiori.gInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class MenuCadastro extends MenuFrame{
	private JLabel lblMain;
	private JLabel lblId;
	private JTextArea txtNome;
	private JTextArea txtExp;
	
	public MenuCadastro(){
		lblMain = new JLabel("Sistema de Armazenamento de Personas");
		lblMain.setBounds(10, 10, 300, 15);
		add(lblMain);
		lblMain = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMain.setBounds(0, 30, 510, 15);
		add(lblMain);
		
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TestePainel.main(null);
				dispose();
			}
		});
		
		
		lblMain = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMain.setBounds(0, 380, 510, 15);
		add(lblMain);

		repaint();
	}
}

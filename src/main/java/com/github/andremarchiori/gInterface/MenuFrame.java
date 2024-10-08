package com.github.andremarchiori.gInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.github.andremarchiori.IOTxt;

@SuppressWarnings("serial")
public class MenuFrame extends JFrame {
	public JButton btnExit;

	static IOTxt iot = new IOTxt();

	public MenuFrame() {
		super("CSMRPG");
		// obtenção de icone
		URL iconURL = getClass().getResource("regu.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());

		// caracterização da janela
		setVisible(true);
		setSize(505, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		setLocationRelativeTo(null);

		// botão de Sair
		btnExit = new JButton("Voltar");
		btnExit.setBounds(10, 400, 80, 45);
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		add(btnExit);
	}
}

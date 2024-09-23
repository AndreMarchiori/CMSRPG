package com.github.andremarchiori.gInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MenuPersonagens extends MenuFrame{
	private JLabel lblConfirm;
	private JLabel lblMainLabel;
	private JButton btnCadastro;
	private JButton btnLista;
	private JButton btnAlteracao;
	private JButton btnExcluir;

	public MenuPersonagens() {

		// Inserção das labels
		lblMainLabel = new JLabel("Sistema de Armazenamento de Personas");
		lblMainLabel.setBounds(10, 10, 300, 15);
		add(lblMainLabel);
		lblMainLabel = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMainLabel.setBounds(0, 30, 510, 15);
		add(lblMainLabel);
		lblMainLabel = new JLabel("Insira uma das opções");
		lblMainLabel.setBounds(180, 50, 300, 15);
		add(lblMainLabel);
		
		lblConfirm = new JLabel("");
		//a ser adicionado
		
		btnCadastro = new JButton("Cadastrar Personagem");
		btnCadastro.setBounds(115, 150, 250, 45);
		btnCadastro.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				MenuCadastro menuCadastro = new MenuCadastro();
				dispose();
			}
		});
		add(btnCadastro);
		
		btnLista = new JButton("Listar Personagens");
		btnLista.setBounds(115, 200, 250, 45);
		btnLista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				ButtonListar listar = new ButtonListar();
				dispose();
			}
		});
		add(btnLista);
		
		btnAlteracao = new JButton("Alterar dados");
		btnAlteracao.setBounds(115, 250, 250, 45);
		btnAlteracao.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				MenuAlteracao alteracao = new MenuAlteracao();
				dispose();
			}
		});
		add(btnAlteracao);
	
		btnExcluir = new JButton("Excluir Data");
		btnExcluir.setBounds(115, 300, 250, 45);
		btnExcluir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				iot.clearTheFile();
				//adicionar confirmação
			}
		});
		add(btnExcluir);

		lblMainLabel = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMainLabel.setBounds(0, 380, 510, 15);
		add(lblMainLabel);
		
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TestePainel.main(null);
				dispose();
			}
		});
		
		repaint();
	}
}

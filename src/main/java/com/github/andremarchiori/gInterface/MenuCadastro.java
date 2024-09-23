package com.github.andremarchiori.gInterface;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.github.andremarchiori.IOTxt;
import com.github.andremarchiori.Personagem;


public class MenuCadastro extends MenuFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3801430078581775523L;
	private JLabel lblMain;
	private JLabel lblId;
	private JLabel lblNome;
	private JLabel lblXp;
	private JLabel lblConfirm;
	private JTextField txtNome;
	private JTextField txtExp;
	private JButton btnCadastro;

	public MenuCadastro() {
		File file = new File("characters.txt");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		lblMain = new JLabel("Sistema de Armazenamento de Personas");
		lblMain.setBounds(10, 10, 300, 15);
		add(lblMain);
		lblMain = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMain.setBounds(0, 30, 510, 15);
		add(lblMain);

		lblMain = new JLabel("Novo ID: ");
		lblMain.setBounds(40, 90, 50, 15);
		add(lblMain);

		lblId = new JLabel();
		try {
			lblId.setText(String.format("%d", iot.proximoId()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		lblId.setBounds(90, 90, 50, 15);
		add(lblId);

		lblNome = new JLabel("Insira o nome do personagem");
		lblNome.setBounds(125, 120, 250, 45);
		add(lblNome);
		txtNome = new JTextField();
		txtNome.setBounds(125, 150, 250, 45);
		add(txtNome);

		lblXp = new JLabel("Insira os pontos de xp do personagem");
		lblXp.setBounds(125, 200, 250, 45);
		add(lblXp);
		txtExp = new JTextField();
		txtExp.setBounds(125, 230, 250, 45);
		add(txtExp);

		lblConfirm = new JLabel("");
		lblConfirm.setBounds(150, 330, 200, 45);
		
		btnCadastro = new JButton("Cadastrar");
		btnCadastro.setBounds(150, 290, 200, 45);
		btnCadastro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MouseHandler handler = new MouseHandler();
				addMouseMotionListener(handler);
				try {
					String nome = txtNome.getText();
					long xp = Long.parseLong(txtExp.getText());
					cadastrarPersonagem(iot.proximoId(), nome, xp);
					
					lblConfirm.setText("Personagem criado com sucesso!");
					add(lblConfirm);
					txtNome.setText("");
					txtExp.setText("");
					
				} catch (InputMismatchException ex) {
					System.err.println("Invalid Value at Exp");
					ex.printStackTrace();
				} catch (IOException ex) {
					ex.printStackTrace();
				} catch (NumberFormatException ex) {
					System.err.println("Campo vazio ou com valor inválido");
					lblConfirm.setText("Campo vazio ou com valor inválido");
					lblConfirm.setForeground(Color.RED);
					add(lblConfirm);
					repaint();
				}

				try {
					lblId.setText(String.format("%d", iot.proximoId()));
				} catch (IOException ex) {
					ex.printStackTrace();
				}
				
				repaint();
			}
		});
		add(btnCadastro);

		lblMain = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMain.setBounds(0, 380, 510, 15);
		add(lblMain);

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TestePainel.main(null);
				dispose();
			}
		});

		repaint();
	}

	public void cadastrarPersonagem(int id, String nome, Long xp) throws IOException {
		Personagem personagem = new Personagem();
		personagem.setId(id);

		// inserir o nome do personagem
		personagem.setName(nome);

		// inserir xp do personagem
		personagem.setExperience(xp);

		iot.salvarPersonagem(personagem);
		// inserir saida de conclusão
	}

	private class MouseHandler implements MouseMotionListener {
		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			lblConfirm.setText("");
			lblConfirm.setForeground(Color.BLACK);
			repaint();
		}
	}
}

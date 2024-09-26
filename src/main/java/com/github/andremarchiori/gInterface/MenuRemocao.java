package com.github.andremarchiori.gInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.github.andremarchiori.Personagem;

public class MenuRemocao extends MenuFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3899575771522283669L;
	private JLabel lblMain;
	private JTextArea txtLista;
	private JLabel lblId;
	private JTextField txtId;
	private JButton btnConfirmar;
	private JLabel lblConfirmar;
	
	public MenuRemocao() {
		MenuExpMod.personagens = new HashMap<>();
		lblMain = new JLabel("Menu de Remoção de Personagem");
		lblMain.setBounds(10, 10, 300, 15);
		add(lblMain);
		lblMain = new JLabel(
				"-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		lblMain.setBounds(0, 30, 510, 15);
		add(lblMain);
		
		txtLista = new JTextArea();
		txtLista.setBounds(20, 50, 445, 240);
		txtLista.setEditable(false);
		MenuExpMod.listarPersonagens(txtLista);
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
		
		btnConfirmar = new JButton("Excluir Id Selecionado");
		btnConfirmar.setBounds(160, 325, 200, 30);
		btnConfirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addMouseMotionListener(new MouseHandler());
				try {
					int id = Integer.parseInt(txtId.getText());
					callRemoves(id);
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
	
	public void callRemoves(int id) {
		MenuExpMod.carregarPersonagens();
		ArrayList<Personagem> listaDePersonagens = new ArrayList<>();
		
		if (!MenuExpMod.personagens.containsKey(id)) {
			JOptionPane.showMessageDialog(null, "Id não encontrado");
			}else if(MenuExpMod.personagens.get(id).getExLogic() == 0) {
				JOptionPane.showMessageDialog(null, "Id não encontrado");
				return;
			}
		
		MenuExpMod.personagens.get(id).setExLogic(0);
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
		System.out.println("BaNiDo");
		txtId.setText("");
		lblConfirmar.setText("Persona excluida com sucesso!");
		txtLista.setText("");
		MenuExpMod.listarPersonagens(txtLista);
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

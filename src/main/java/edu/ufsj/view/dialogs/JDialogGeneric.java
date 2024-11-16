package edu.ufsj.view.dialogs;

import javax.swing.*;

public class JDialogGeneric extends JFrame {

	public void abrirDialog() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public void mostrarMensagemCpfInvalido() {
		JOptionPane.showMessageDialog(null, "Cpf inválido", "Revise o cpf informado", JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMensagemLoginInvalido() {
		JOptionPane.showMessageDialog(null, "Login não pode conter espaços", "Erro login informado",
				JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMensagemEmailInvalido() {
		JOptionPane.showMessageDialog(null, "E-mail em formato inválido", "Erro e-mail informado",
				JOptionPane.ERROR_MESSAGE);
	}

	public void mostrarMensagemErroInesperado() {
		JOptionPane.showMessageDialog(null, "Erro inesperado ocorrido", "Erro inesperado", JOptionPane.ERROR_MESSAGE);
	}

}

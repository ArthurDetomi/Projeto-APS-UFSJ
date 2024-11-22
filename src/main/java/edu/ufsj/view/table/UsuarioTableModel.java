package edu.ufsj.view.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.ufsj.model.TipoUsuario;
import edu.ufsj.model.Usuario;
import edu.ufsj.utils.DateUtil;

public class UsuarioTableModel extends AbstractTableModel {

	private final String[] colunas = { "Nome", "CPF", "E-mail", "Telefone", "Cadastrado" };
	private final List<Usuario> usuarios;

	public UsuarioTableModel(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@Override
	public int getRowCount() {
		return usuarios.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	public Integer getEntityId(int rowIndex) {
		return usuarios.get(rowIndex).getId();
	}

	public TipoUsuario getTipoUsuario(int rowIndex) {
		return usuarios.get(rowIndex).getTipoUsuario();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Usuario usuario = usuarios.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return usuario.getNome();
		case 1:
			return usuario.getCpf();
		case 2:
			return usuario.getEmail();
		case 3:
			return usuario.getTelefone();
		case 4:
			return usuario.getCadastrado().format(DateUtil.formatterPadraoDataHora);
		}

		return null;
	}
}

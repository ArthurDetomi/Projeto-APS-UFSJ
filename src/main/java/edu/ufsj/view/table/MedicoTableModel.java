package edu.ufsj.view.table;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.ufsj.model.Medico;

public class MedicoTableModel extends AbstractTableModel {
	private final String[] colunas = { "Nome", "CRM", "CPF", "E-mail", "Telefone", "Cadastrado" };
	private final List<Medico> medicos;

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public MedicoTableModel(List<Medico> medicos) {
		this.medicos = medicos;
	}

	@Override
	public String getColumnName(int column) {
		return colunas[column];
	}

	@Override
	public int getRowCount() {
		return medicos.size();
	}

	@Override
	public int getColumnCount() {
		return colunas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Medico medico = medicos.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return medico.getNome();
		case 1:
			return medico.getCrm();
		case 2:
			return medico.getCpf();
		case 3:
			return medico.getEmail();
		case 4:
			return medico.getTelefone();
		case 5:
			return medico.getCadastrado().format(formatter);
		}

		return null;
	}
}

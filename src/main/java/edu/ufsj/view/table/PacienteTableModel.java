package edu.ufsj.view.table;

import edu.ufsj.model.Paciente;

import javax.swing.table.AbstractTableModel;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PacienteTableModel extends AbstractTableModel {

    private final String[] colunas = {"Nome", "CPF", "Telefone", "Cidade", "Estado", "Número", "Cadastrado"};
    private final List<Paciente> pacientes;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public PacienteTableModel(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }

    @Override
    public int getRowCount() {
        return pacientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }


    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Paciente paciente = pacientes.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return paciente.getNome();
		case 1:
			return paciente.getCpf();
		case 2:
			return paciente.getTelefone();
		case 3:
			return paciente.getCidade();
		case 4:
			return paciente.getEstado();
		case 5:
			return paciente.getNumero();
		case 6:
			return paciente.getCadastrado().format(formatter);
		}

		return null;
	}

}

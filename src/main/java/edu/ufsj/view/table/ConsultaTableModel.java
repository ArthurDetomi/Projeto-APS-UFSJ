package edu.ufsj.view.table;

import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import edu.ufsj.model.Consulta;
import edu.ufsj.model.Medico;
import edu.ufsj.model.Paciente;
import edu.ufsj.utils.DateUtil;

public class ConsultaTableModel extends AbstractTableModel {
	private final String[] colunas = { "Nome médico", "CRM médico", "Nome paciente", "CPF paciente", "Descrição",
			"Data cadastro", "Data agendamento", "Data fim", "Realizada" };
	private final List<Consulta> consultas;

	public ConsultaTableModel(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	public Integer getEntityId(int rowIndex) {
		return consultas.get(rowIndex).getId();
	}

	public Consulta getConsulta(int rowIndex) {
		return consultas.get(rowIndex);
	}

	@Override
	public int getRowCount() {
		return consultas.size();
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
		Consulta consulta = consultas.get(rowIndex);

		Medico medico = consulta.getMedico();
		Paciente paciente = consulta.getPaciente();
		DateTimeFormatter formatterPadraoDataHora = DateUtil.formatterPadraoDataHora;

		switch (columnIndex) {
		case 0:
			return medico.getNome();
		case 1:
			return medico.getCrm();
		case 2:
			return paciente.getNome();
		case 3:
			return paciente.getCpf();
		case 4:
			return consulta.getDescricao();
		case 5:
			return formatterPadraoDataHora.format(consulta.getCadastrado());
		case 6:
			return formatterPadraoDataHora.format(consulta.getDataAgendamento());
		case 7:
			return (consulta.getDataFim() != null) ? formatterPadraoDataHora.format(consulta.getDataFim()) : null;
		case 8:
			return (consulta.isConsultaRealizada()) ? "SIM" : "NÃO";
		}

		return null;
	}
}

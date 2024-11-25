package edu.ufsj.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import edu.ufsj.dao.ConsultaDao;
import edu.ufsj.model.Consulta;
import edu.ufsj.utils.DateUtil;
import edu.ufsj.utils.Response;

public class ConsultaController {

	private ConsultaDao consultaDao = new ConsultaDao();

	public Response<Consulta> cadastrarConsulta(Consulta consulta) {
		if (!consulta.isValidForRegisterBD()) {
			return new Response<>(false, "Dados de consulta estão preenchidos incorretamente");
		}

		LocalDateTime dataAgendamento = consulta.getDataAgendamento();

		if (dataAgendamento.isBefore(LocalDateTime.now())) {
			return new Response<>(false, "Data de agendamento incorreta");
		}

		LocalDateTime lastDataAgendamento = consultaDao.findLastDataAgendamento(consulta);

		if (lastDataAgendamento != null) {
			LocalDateTime lastDataAgendamentoPlusFifteenMinutes = lastDataAgendamento.plusMinutes(15);

			if (dataAgendamento.isBefore(lastDataAgendamentoPlusFifteenMinutes)) {
				return new Response<>(false, "Horário de consulta deve ser posterior a "
						+ DateUtil.formatterPadraoDataHora.format(lastDataAgendamentoPlusFifteenMinutes));
			}
		}

		boolean registeredSuccess = consultaDao.create(consulta);

		if (!registeredSuccess) {
			return new Response<>(false, "Falha ao cadastrar consulta");
		}

		return new Response<>(true, "Consulta cadastrada com sucesso");
	}

	public List<Consulta> findAllConsultasDeHoje() {
		return consultaDao.findAllConsultasDeHoje();
	}

	public List<Consulta> findAllConsultas() {
		return consultaDao.findAll();
	}

	public Response<Consulta> finalizarConsulta(Consulta consulta) {
		LocalDateTime dataAgendamento = consulta.getDataAgendamento();

		if (consulta.getId() == null || dataAgendamento == null) {
			return new Response<>(false, "Consulta selecionada inválida");
		}

		if (!dataAgendamento.toLocalDate().equals(LocalDate.now())) {
			return new Response<>(false, "Somente consultas de hoje podem ser finalizadas");
		}

		boolean consultaFinalizadaComSucesso = consultaDao.finalizarConsulta(consulta);

		if (!consultaFinalizadaComSucesso) {
			return new Response<>(false, "Falha ao finalizar consulta");
		}

		return new Response<>(true, "Consulta finalizada com sucesso");
	}
}

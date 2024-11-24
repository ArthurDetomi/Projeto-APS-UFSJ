package edu.ufsj.controller;

import java.time.LocalDateTime;

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
}

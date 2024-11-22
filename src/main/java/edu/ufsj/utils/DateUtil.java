package edu.ufsj.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static final DateTimeFormatter formatterPadraoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

	public static String formatarParaSqlDateTime(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}

		DateTimeFormatter formatterSql = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return localDateTime.format(formatterSql);
	}

	public static LocalDateTime parseDateAndHourToLocalDateTime(String dateTime) {
		if (dateTime == null) {
			return null;
		}

		return LocalDateTime.parse(dateTime, formatterPadraoDataHora);
	}

}

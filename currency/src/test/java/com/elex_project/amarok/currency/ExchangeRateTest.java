/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.currency;

import com.elex_project.abraxas.Console;
import com.elex_project.abraxas.Timez;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ExchangeRateTest {
	@Test
	public void calcPrice() {
		ExchangeRate yen = new ExchangeRate(
				"JPY", "EUR", 117.18076, LocalDateTime.now());
		ExchangeRate won = new ExchangeRate(
				"KRW", "EUR", 1345.146398, LocalDateTime.now());
		ExchangeRate usd = new ExchangeRate(
				"USD", "EUR", 1.107935, LocalDateTime.now());

		Console.writeLine("1 yen = %s won.", won.calcPrice(1, yen));
		Console.writeLine("1 usd = %s won.", won.calcPrice(1, usd));

		Console.writeLine(won.toString());

		Console.writeLine(Currency.getInstance("USD").getCurrencyCode());
	}

	@Test
	public void test() {
		LocalDateTime now = LocalDateTime.now();

		OffsetDateTime dt = Timez.toOffsetDateTime(now, ZoneOffset.systemDefault());
		String t = dt.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		Console.writeLine(t);

		OffsetDateTime dt2 = OffsetDateTime.parse(t, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		Console.writeLine(dt2);
		assertEquals(dt, dt2);
	}

	@Test
	public void testjson() {
		//JSONObject json = new JSONObject("{\"XAU\": 7.19E-4}");
		//Console.writeLine(String.valueOf(json.optDouble("XAU") + 1));
	}
}

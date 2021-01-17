/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.currency;

import com.elex_project.abraxas.Console;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;


public class ExchangeRatesRequestTest {

	@Test
	//@Disabled
	public void send() throws URISyntaxException, IOException, InterruptedException {
		ExchangeRatesRequest request = new ExchangeRatesRequest();
		ExchangeRatesResponse response = request.send();

		Console.writeLine("Status = " + response.getHttpStatus());
		for (ExchangeRate item : response.getList()) {
			Console.writeLine(item);
		}
	}
}

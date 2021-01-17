/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.weather;

import com.elex_project.abraxas.Console;
import com.elex_project.abraxas.Env;
import com.elex_project.abraxas.Properties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Locale;

public class OpenWeatherMapTest {
	private static String API_KEY;

	@BeforeAll
	static void getKey() throws IOException {
		Properties properties = new Properties();
		try (FileInputStream is = new FileInputStream(new File(Env.getUserHome(), "api_key_store.properties"))) {
			properties.load(is);
		}
		API_KEY = properties.getProperty("open.weather.map").orElseThrow();
	}
	
	@Test
	public void send() throws URISyntaxException, IOException, InterruptedException {
		OpenWeatherMap api = new OpenWeatherMap(35.1802563, 127.8292504, API_KEY);
		OWMResponse response = api.send();

		Console.writeLine(response.getResult().toString());
	}
	@Test
	public void send2() throws URISyntaxException, IOException, InterruptedException {
		OpenWeatherMap api = new OpenWeatherMap("busan", Locale.KOREA, API_KEY);
		Console.writeLine("to: " + api.getRequest().uri());
		OWMResponse response = api.send();

		Console.writeLine(response.getResult().toString());
	}
}

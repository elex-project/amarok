/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

import com.elex_project.abraxas.Console;
import com.elex_project.abraxas.Env;
import com.elex_project.abraxas.Properties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class KakaoApiTest {
	private static String API_KEY;

	@BeforeAll
	static void getKey() throws IOException {
		Properties properties = new Properties();
		try (FileInputStream is = new FileInputStream(new File(Env.getUserHome(), "api_key_store.properties"))) {
			properties.load(is);
		}
		API_KEY = properties.getProperty("kakao").orElseThrow();
	}

	@Test
	public void search_address() throws URISyntaxException, IOException, InterruptedException {
		AddressSearchRequest api = new AddressSearchRequest("양운로 45", API_KEY);
		AddressSearchResponse response = api.send();

		Console.writeLine(response);
	}

	@Test
	public void search_coord() throws URISyntaxException, IOException, InterruptedException {
		CoordToAddressRequest api = new CoordToAddressRequest(129.176777393347, 35.16737568579, API_KEY);
		CoordToAddressResponse response = api.send();
		Console.writeLine(response);
	}

	@Test
	public void trans_coord() throws URISyntaxException, IOException, InterruptedException {
		TransCoordRequest api = new TransCoordRequest(129.176777393347, 35.16737568579, Coord.WGS84, Coord.WCONGNAMUL, API_KEY);
		TransCoordResponse response = api.send();
		Console.writeLine(response);
	}
}

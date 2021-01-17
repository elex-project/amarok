/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.currency;

import com.elex_project.abraxas.Console;
import com.elex_project.abraxas.Env;
import com.elex_project.abraxas.Properties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


public class FixerIoRequestTest {

	private static String API_KEY ;

	@BeforeAll
	static void getKey() throws IOException {
		Properties properties = new Properties();
		try (FileInputStream is = new FileInputStream(new File(Env.getUserHome(), "api_key_store.properties"))) {
			properties.load(is);
		}
		API_KEY = properties.getProperty("fixer.io").orElseThrow();
	}

	@Test// @Disabled
	public void send() throws URISyntaxException, IOException, InterruptedException {
		/*FixerIoRequest request = new FixerIoRequest(API_KEY,
				"0ee7dfdb6adb2982c541245303c974d8",
				"Thu, 09 Apr 2020 12:44:06 GMT");*/
		FixerIoRequest request = new FixerIoRequest(API_KEY,
				null,
				null);
		FixerIoResponse result = request.send();

		Console.writeLine("http Status = " + result.getHttpStatus());
		Console.writeLine("ETag = " + result.getETag());
		Console.writeLine("Last Modified = " + result.getLastModified());

		for (ExchangeRate item : result.getList()){
			Console.writeLine(item);
		}

	}
}

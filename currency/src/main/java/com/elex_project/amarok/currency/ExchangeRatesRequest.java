/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2019. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.currency;

import com.elex_project.abraxas.Env;
import com.elex_project.harpy.Uri;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * @author Elex
 */
@Getter
public final class ExchangeRatesRequest {

	private static final String USER_AGENT = "Amarok by ELEX/" +
			Env.getJavaName() + " " + Env.getJavaVersion() + "/" +
			Env.getOsName() + " " + Env.getOsVersion();

	private static HttpClient HTTP_CLIENT;
	private final HttpRequest request;

	public ExchangeRatesRequest() {
		request = HttpRequest.newBuilder()
				.GET()
				.version(HttpClient.Version.HTTP_2)
				.header("User-Agent", USER_AGENT)
				.uri(Uri.builder()
						.scheme("https").host("api.exchangeratesapi.io")
						.path("latest")
						.build().toURI())
				.timeout(Duration.ofSeconds(5))
				.build();
	}

	@Contract(" -> new")
	public @NotNull ExchangeRatesResponse send() throws IOException, InterruptedException {
		if (null==HTTP_CLIENT){
			HTTP_CLIENT = HttpClient.newBuilder()
					.connectTimeout(Duration.ofSeconds(10))
					.build();
		}
		HttpResponse<String> response = HTTP_CLIENT.send(request,
				HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

		return new ExchangeRatesResponse(response);
	}

	public static void setHttpClient(HttpClient httpClient) {
		HTTP_CLIENT = httpClient;
	}
}

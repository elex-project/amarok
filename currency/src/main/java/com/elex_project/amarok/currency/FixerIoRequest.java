/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2019. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.currency;

import com.elex_project.abraxas.Stringz;
import com.elex_project.harpy.Uri;
import lombok.Getter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
public final class FixerIoRequest {

	private static HttpClient HTTP_CLIENT;
	private final HttpRequest request;

	public FixerIoRequest(@Nullable final String apiKey,
	                      @Nullable final String eTag, @Nullable final String lastModified) {
		// "http://data.fixer.io/api/latest"
		HttpRequest.Builder builder = HttpRequest.newBuilder()
				.GET()
				.version(HttpClient.Version.HTTP_2)
				.uri(Uri.builder()
						.scheme("http").host("data.fixer.io")
						.path("api").path("latest")
						.query("access_key", apiKey)
						.build().toURI())
				.timeout(Duration.ofSeconds(5));
		if (!Stringz.isEmpty(eTag)) {
			builder.header("If-None-Match", eTag);
		}
		if (!Stringz.isEmpty(lastModified)) {
			builder.header("If-Modified-Since", lastModified);
		}
		request = builder.build();
	}

	public @NotNull FixerIoResponse send() throws IOException, InterruptedException {
		if (null == HTTP_CLIENT) {
			HTTP_CLIENT = HttpClient.newBuilder()
					.connectTimeout(Duration.ofSeconds(10))
					.build();
		}
		HttpResponse<String> response = HTTP_CLIENT.send(request,
				HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

		return new FixerIoResponse(response);
	}

	public static void setHttpClient(HttpClient httpClient) {
		HTTP_CLIENT = httpClient;
	}


}

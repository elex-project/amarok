/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.weather;

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
import java.util.Locale;

/**
 * api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=111111111111
 *
 * @author Elex
 */
@Getter
public final class OpenWeatherMap {

	private static final String USER_AGENT = "Amarok by ELEX/" +
			Env.getJavaName() + " " + Env.getJavaVersion() + "/" +
			Env.getOsName() + " " + Env.getOsVersion();

	private static HttpClient HTTP_CLIENT;

	private final HttpRequest request;

	/**
	 * 현재 날씨
	 *
	 * @param latitude latitude
	 * @param longitude longitude
	 * @param apiKey apiKey
	 */
	public OpenWeatherMap(final double latitude, final double longitude, final String apiKey) {
		request = HttpRequest.newBuilder()
				.GET()
				.uri(buildURI(latitude, longitude, apiKey))
				.version(HttpClient.Version.HTTP_2)
				.header("User-Agent", USER_AGENT)
				.timeout(Duration.ofSeconds(10))
				.build();
	}

	public OpenWeatherMap(final String cityName, final Locale country, final String apiKey) {
		request = HttpRequest.newBuilder()
				.GET()
				.uri(buildURI(cityName, country, apiKey))
				.version(HttpClient.Version.HTTP_2)
				.header("User-Agent", USER_AGENT)
				.timeout(Duration.ofSeconds(10))
				.build();
	}

	public OpenWeatherMap(final Long cityId, final String apiKey) {
		request = HttpRequest.newBuilder()
				.GET()
				.uri(buildURI(cityId, apiKey))
				.version(HttpClient.Version.HTTP_2)
				.header("User-Agent", USER_AGENT)
				.timeout(Duration.ofSeconds(10))
				.build();
	}

	public static void setHttpClient(@NotNull final HttpClient client) {
		HTTP_CLIENT = client;
	}

	@Contract(" -> new")
	public @NotNull OWMResponse send() throws IOException, InterruptedException {
		if (null == HTTP_CLIENT) {
			HTTP_CLIENT = HttpClient.newBuilder()
					.version(HttpClient.Version.HTTP_2)
					.connectTimeout(Duration.ofSeconds(10))
					.build();
		}
		HttpResponse<String> response = HTTP_CLIENT.send(request,
				HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
		return new OWMResponse(response);
	}

	private static Uri.Builder builder(final String apiKey) {
		// https://api.openweathermap.org/data/2.5/weather
		return Uri.builder()
				.scheme("https").host("api.openweathermap.org")
				.path("data").path("2.5").path("weather")
				.query("units", "metric")
				.query("APPID", apiKey);
	}

	private static @NotNull URI buildURI(final double latitude, final double longitude, final String apiKey) {
		return builder(apiKey)
				.query("lat", latitude)
				.query("lon", longitude)
				.build().toURI();
	}

	private static @NotNull URI buildURI(final String cityName, final Locale country, final String apiKey) {
		return builder(apiKey)
				.query("q", cityName + "," + country.getCountry())
				.build().toURI();
	}

	private static @NotNull URI buildURI(final Long cityId, final String apiKey) {
		return builder(apiKey)
				.query("id", cityId)
				.build().toURI();
	}
}

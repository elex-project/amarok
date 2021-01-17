/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

import com.elex_project.harpy.Uri;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * 카카오 좌표를 주소로 변환
 * https://developers.kakao.com/
 *
 * @author Elex
 */
@Slf4j
public final class CoordToAddressRequest extends BaseRequest<CoordToAddressResponse> {

	/**
	 * @param x      x 좌표로 경위도인 경우 longitude
	 * @param y      y 좌표로 경위도인 경우 latitude
	 * @param apiKey api key
	 */
	public CoordToAddressRequest(final double x, final double y, final String apiKey) {
		request = HttpRequest.newBuilder()
				.GET()
				.uri(buildURI(x, y))
				.version(HttpClient.Version.HTTP_2)
				.timeout(Duration.ofSeconds(5))
				.header("User-Agent", USER_AGENT)
				.headers("Authorization", "KakaoAK " + apiKey)
				.build();
	}

	public CoordToAddressRequest(final double x, final double y, final Coord coord, final String apiKey) {
		request = HttpRequest.newBuilder()
				.GET()
				.uri(buildURI(x, y, coord))
				.version(HttpClient.Version.HTTP_2)
				.timeout(Duration.ofSeconds(5))
				.header("User-Agent", USER_AGENT)
				.headers("Authorization", "KakaoAK " + apiKey)
				.build();
	}

	private static Uri.Builder builder() {
		//"https://dapi.kakao.com/v2/local/geo/coord2address.json";
		return Uri.builder()
				.scheme("https").host("dapi.kakao.com")
				.path("v2").path("local").path("geo").path("coord2address.json");
	}

	private static @NotNull URI buildURI(final double x, final double y) {
		return builder()
				.query("x", x)
				.query("y", y)
				.build().toURI();
	}

	private static @NotNull URI buildURI(final double x, final double y, final @NotNull Coord coord) {
		return builder()
				.query("x", x)
				.query("y", y)
				.query("input_coord", coord.name())
				.build().toURI();
	}

	public @NotNull CoordToAddressResponse send() throws IOException, InterruptedException {

		HttpResponse<String> response = getHttpClient().send(request,
				HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
		return new CoordToAddressResponse(response);
	}
}

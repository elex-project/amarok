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
 * 카카오 좌표계 변환
 * https://developers.kakao.com/
 *
 * @author Elex
 */
@Slf4j
public final class TransCoordRequest extends BaseRequest<TransCoordResponse> {

	/**
	 * @param x      x 좌표로 경위도인 경우 longitude
	 * @param y      y 좌표로 경위도인 경우 latitude
	 * @param from   x, y 로 입력되는 값에 대한 좌표 체계, 기본 값은 WGS84
	 *               지원 좌표계: WGS84, WCONGNAMUL, CONGNAMUL, WTM, TM
	 * @param to     x, y 로 입력되는 값에 대한 좌표 체계, 기본 값은 WGS84
	 *               지원 좌표계: WGS84, WCONGNAMUL, CONGNAMUL, WTM, TM
	 * @param apiKey api key
	 */
	public TransCoordRequest(final double x, final double y, final @NotNull Coord from, final @NotNull Coord to,
	                         final @NotNull String apiKey) {
		request = HttpRequest.newBuilder()
				.GET()
				.uri(buildURI(x, y, from, to))
				.version(HttpClient.Version.HTTP_2)
				.timeout(Duration.ofSeconds(5))
				.header("User-Agent", USER_AGENT)
				.headers("Authorization", "KakaoAK " + apiKey)
				.build();
	}

	private static @NotNull URI buildURI(final double x, final double y, final Coord from, final Coord to) {
		// https://dapi.kakao.com/v2/local/geo/transcoord.json
		return Uri.builder()
				.scheme("https").host("dapi.kakao.com")
				.path("v2").path("local").path("geo").path("transcoord.json")
				.query("x", x)
				.query("y", x)
				.query("input_coord", from.name())
				.query("output_coord", to.name())
				.build().toURI();
	}

	public @NotNull TransCoordResponse send() throws IOException, InterruptedException {

		HttpResponse<String> response = getHttpClient().send(request,
				HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
		return new TransCoordResponse(response);
	}
}

/******************************************************************************
 * Project Amarok                                                              *
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
 * 카카오 주소 검색
 * https://developers.kakao.com/
 *
 * @author Elex
 */
@Slf4j
public final class AddressSearchRequest extends BaseRequest<AddressSearchResponse> {

	/**
	 * @param address 검색을 원하는 질의어
	 * @param apiKey  api key
	 */
	public AddressSearchRequest(final String address, final String apiKey) {
		request = HttpRequest.newBuilder()
				.GET()
				.uri(buildURI(address))
				.version(HttpClient.Version.HTTP_2)
				.timeout(Duration.ofSeconds(5))
				.header("User-Agent", USER_AGENT)
				.headers("Authorization", "KakaoAK " + apiKey)
				.build();

	}


	/**
	 * @param address     검색을 원하는 질의어
	 * @param page        결과 페이지 번호, 1-45 사이, 기본 값 1
	 * @param addressSize 한 페이지에 보여질 문서의 개수, 1-30 사이, 기본 값 10
	 * @param apiKey      api key
	 */
	public AddressSearchRequest(final String address, final int page, final int addressSize, final String apiKey) {
		request = HttpRequest.newBuilder()
				.GET()
				.uri(buildURI(address, page, addressSize))
				.version(HttpClient.Version.HTTP_2)
				.timeout(Duration.ofSeconds(5))
				.header("User-Agent", USER_AGENT)
				.headers("Authorization", "KakaoAK " + apiKey)
				.build();

	}

	/**
	 * https://dapi.kakao.com/v2/local/search/address.json
	 *
	 * @return
	 */
	private static Uri.Builder builder() {
		return Uri.builder()
				.scheme("https")
				.host("dapi.kakao.com")
				.path("v2").path("local").path("search").path("address.json");
	}

	private static @NotNull URI buildURI(final String address, final int page, final int addressSize) {
		return builder()
				.encodedQuery("query", address)
				.encodedQuery("page", page)
				.encodedQuery("AddressSize", addressSize)
				.build().toURI();
	}

	private static @NotNull URI buildURI(final String address) {
		return builder()
				.encodedQuery("query", address)
				.build().toURI();
	}

	@Override
	public @NotNull AddressSearchResponse send() throws IOException, InterruptedException {
		HttpResponse<String> response = getHttpClient().send(request,
				HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
		return new AddressSearchResponse(response);
	}
}

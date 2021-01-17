/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

import com.elex_project.abraxas.Env;
import org.jetbrains.annotations.NotNull;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

/**
 *
 * @param <T> response type
 * @author Elex
 */
abstract class BaseRequest<T extends IResponse<?>> implements IRequest<T>, KakaoApi {
	protected static final String USER_AGENT = "Amarok by ELEX/" +
			Env.getJavaName() + " " + Env.getJavaVersion() + "/" +
			Env.getOsName() + " " + Env.getOsVersion();

	private static HttpClient HTTP_CLIENT;

	protected HttpRequest request;

	public HttpRequest getRequest() {
		return request;
	}

	public static void setHttpClient(@NotNull HttpClient client) {
		HTTP_CLIENT = client;
	}

	protected static HttpClient getHttpClient() {
		if (null == HTTP_CLIENT) {
			HTTP_CLIENT = HttpClient.newBuilder()
					.version(HttpClient.Version.HTTP_2)
					.connectTimeout(Duration.ofSeconds(10))
					.build();
		}
		return HTTP_CLIENT;
	}


}

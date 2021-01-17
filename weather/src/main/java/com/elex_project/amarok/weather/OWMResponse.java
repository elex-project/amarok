/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2019. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.weather;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.net.http.HttpResponse;

/**
 * {"dt":1552993200,"coord":{"lon":126.98,"lat":37.57},"visibility":5000,"weather":[{"icon":"50n","description":"haze","main":"Haze","id":721},{"icon":"50n","description":"mist","main":"Mist","id":701}],"name":"Seoul","cod":200,"main":{"temp":10.65,"temp_min":7,"humidity":76,"pressure":1017,"temp_max":14},"clouds":{"all":0},"id":1835848,"sys":{"country":"KR","sunrise":1552945102,"sunset":1552988507,"id":8105,"type":1,"message":0.0069},"base":"stations","wind":{"deg":180,"speed":1.5}}
 */
@Slf4j
@Getter
public final class OWMResponse {

	private final int httpStatus;
	private final OWMResult result;

	OWMResponse(final HttpResponse<String> response) throws JsonProcessingException {
		log.debug("Rx({}): {}", response.statusCode(), response.body());

		final ObjectMapper objectMapper = new ObjectMapper();
		this.result = objectMapper.readValue(response.body(), OWMResult.class);
		this.httpStatus = response.statusCode();

	}

	/*@Override
	public @NotNull String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("일시: ").append(getDatetime()).append(", ")
				.append("날씨: ").append(getWeatherDesc()).append(", ")
				.append("기온: ").append(getTemperature()).append(", ")
				.append("최저 기온: ").append(getTemperatureMin()).append(", ")
				.append("최고 기온: ").append(getTemperatureMax()).append(", ")
				.append("습도: ").append(getHumidity()).append(", ")
				.append("기압: ").append(getPressure()).append(", ")
				.append("가시거리: ").append(getVisibility()).append(", ")
				.append("구름: ").append(getClouds()).append(", ")
				.append("바람 방향: ").append(getWindDegree()).append(", ")
				.append("풍속: ").append(getWindSpeed()).append(", ")
				.append("강우량1: ").append(getRain1()).append(", ")
				.append("강우량3: ").append(getRain3()).append(", ")
				.append("강설량1: ").append(getSnow1()).append(", ")
				.append("강설량3: ").append(getSnow3());
		return sb.toString();
	}*/
}

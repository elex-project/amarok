/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2019. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.currency;

import com.elex_project.abraxas.Timez;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Elex
 */
@Slf4j
@Data
@Getter
public final class FixerIoResponse {

	private int httpStatus;
	private List<ExchangeRate> list;
	private String eTag, lastModified;

	@Data
	@NoArgsConstructor
	private static class ResultModel  implements IModel{
		@JsonProperty("success")
		private boolean success;
		@JsonIgnore
		private String date;
		@JsonProperty("timestamp")
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		private LocalDateTime timestamp;
		@JsonProperty("base")
		private String base;
		@JsonProperty("rates")
		private Map<String, Double> rates;
	}

	public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

		@Override
		public LocalDateTime deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
			return Timez.toLocalDateTime(Long.parseLong(p.getText()) * 1000);
		}
	}

	FixerIoResponse(@NotNull HttpResponse<String> response) throws JsonProcessingException {
		log.debug("Rx({}): {}", response.statusCode(), response.body());

		httpStatus = response.statusCode();

		eTag = response.headers()
				.firstValue("Etag").orElse(null);
		lastModified = response.headers()
				.firstValue("Last-Modified").orElse(null);

		list = new ArrayList<>();
		if (httpStatus == 200) {
			ObjectMapper objectMapper = new ObjectMapper();
			ResultModel model = objectMapper.readValue(response.body(), ResultModel.class);

			for (String currency : model.rates.keySet()) {
				ExchangeRate item = new ExchangeRate(currency, model.base, model.rates.get(currency), model.timestamp);
				list.add(item);
			}

		} else {
			log.debug("HTTP Status: " + httpStatus);
		}

	}

}

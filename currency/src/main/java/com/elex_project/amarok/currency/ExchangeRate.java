/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2019. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.currency;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Currency;

/**
 * @author Elex
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class ExchangeRate implements IModel {
	/**
	 * 3 letters currency code
	 */
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("base")
	private String baseCurrency;
	@JsonProperty("rate")
	private double rate;
	@JsonProperty("timestamp")
	@JsonDeserialize(using = OffsetDateTimeDeserializer.class)
	private LocalDateTime timestamp;

	public static class OffsetDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

		@Override
		public LocalDateTime deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
			return OffsetDateTime.parse(p.getText(), DateTimeFormatter.ISO_OFFSET_DATE_TIME).toLocalDateTime();
		}
	}

	public double getRate(ExchangeRate base) {
		return calcPrice(1, base);
	}

	/**
	 * 현재 환율과 계산하려는 환율 정보의 기준 화폐가 서로 동일하여야 한다.
	 * 그렇지 않으면, IllegalArgumentException를 던진다.
	 * 기본값은 EUR이다.
	 *
	 * @param price
	 * @param base
	 * @return
	 */
	public double calcPrice(double price, @NotNull ExchangeRate base) {
		if (!this.baseCurrency.equals(base.baseCurrency)) {
			throw new IllegalArgumentException("Base currency of the two currencies must be equal to.");
		}
		return (price / base.rate) * this.rate;
	}

	@NotNull
	@Contract(pure = true)
	@Override
	public String toString() {
		return "1 " + baseCurrency + " = " + rate + " " + currency + " @ " + timestamp.toString();
	}

	/**
	 * 비트코인(BTC) 등은 표준 코드가 아닌지라 예외가 발생할 것이다.
	 *
	 * @return
	 * @throws IllegalArgumentException if currencyCode is not a supported ISO 4217 code
	 * @see Currency#getInstance(String)
	 */
	public Currency toCurrency() throws IllegalArgumentException {
		return Currency.getInstance(currency);
	}

}

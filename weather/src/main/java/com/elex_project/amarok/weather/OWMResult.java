/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2021. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.weather;

import com.elex_project.abraxas.Timez;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class OWMResult implements IModel {

	@JsonProperty("coord")
	private Coord coord;
	@JsonProperty("weather")
	private Weather[] weather;
	/**
	 * Internal parameter
	 */
	@JsonProperty("base")
	private String base;
	@JsonProperty("main")
	private Main main;
	/**
	 * Visibility, meter
	 */
	@JsonProperty("visibility")
	private Long visibility;
	@JsonProperty("wind")
	private Wind wind;
	@JsonProperty("clouds")
	private Clouds clouds;
	@JsonProperty("rain")
	private Rain rain;
	@JsonProperty("snow")
	private Snow snow;
	/**
	 * Time of data calculation, unix, UTC
	 */
	@JsonProperty("dt")
	@JsonDeserialize(using = DateTimeDeserializer.class)
	private LocalDateTime dateTime;
	@JsonProperty("sys")
	private Sys sys;
	/**
	 * Shift in seconds from UTC
	 */
	@JsonProperty("timezone")
	private Long timezone;
	/**
	 * City ID
	 */
	@JsonProperty("id")
	private Integer cityId;
	/**
	 * City name
	 */
	@JsonProperty("name")
	private String cityName;
	/**
	 * Internal parameter
	 */
	@JsonProperty("cod")
	private Integer cod;

	public static class DateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

		@Override
		public LocalDateTime deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
			return Timez.toLocalDateTime(Long.parseLong(p.getText()) * 1000);
		}
	}

	@Data
	@NoArgsConstructor
	public static class Coord implements IModel{
		/**
		 * City geo location, longitude
		 */
		@JsonProperty("lon")
		private Float longitude;
		/**
		 * City geo location, latitude
		 */
		@JsonProperty("lat")
		private Float latitude;
	}

	@Data
	@NoArgsConstructor
	public static class Weather implements IModel{
		/**
		 * Weather condition id
		 */
		@JsonProperty("id")
		private int id;
		/**
		 * Group of weather parameters (Rain, Snow, Extreme etc.)
		 */
		@JsonProperty("main")
		private String main;
		/**
		 * Weather condition within the group. You can get the output in your language.
		 */
		@JsonProperty("description")
		private String description;
		/**
		 * Weather icon id
		 */
		@JsonProperty("icon")
		private String icon;
	}

	@Data
	@NoArgsConstructor
	public static class Main implements IModel{
		/**
		 * Temperature. Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
		 */
		@JsonProperty("temp")
		private Float temperature;
		/**
		 * Temperature. This temperature parameter accounts for the human perception of weather.
		 * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
		 */
		@JsonProperty("feels_like")
		private Float feelsLike;
		/**
		 * Atmospheric pressure (on the sea level, if there is no sea_level or grnd_level data), hPa
		 */
		@JsonProperty("pressure")
		private Float pressure;
		/**
		 * Humidity, %
		 */
		@JsonProperty("humidity")
		private Float humidity;
		/**
		 * Minimum temperature at the moment. This is minimal currently observed temperature (within large megalopolises and urban areas).
		 * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
		 */
		@JsonProperty("temp_min")
		private Float temperatureMin;
		/**
		 * Maximum temperature at the moment. This is maximal currently observed temperature (within large megalopolises and urban areas).
		 * Unit Default: Kelvin, Metric: Celsius, Imperial: Fahrenheit.
		 */
		@JsonProperty("temp_max")
		private Float temperatureMax;
		/**
		 * Atmospheric pressure on the sea level, hPa
		 */
		@JsonProperty("sea_level")
		private Float pressureAtSeaLevel;
		/**
		 * Atmospheric pressure on the ground level, hPa
		 */
		@JsonProperty("grnd_level")
		private Float pressureAtGroundLevel;
	}

	@Data
	@NoArgsConstructor
	public static class Wind implements IModel{
		/**
		 * Wind speed. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour.
		 */
		@JsonProperty("speed")
		private Float speed;
		/**
		 * Wind direction, degrees (meteorological)
		 */
		@JsonProperty("deg")
		private Float degree;
		/**
		 * Wind gust. Unit Default: meter/sec, Metric: meter/sec, Imperial: miles/hour
		 */
		@JsonProperty("gust")
		private Float gust;
	}

	@Data
	@NoArgsConstructor
	public static class Clouds implements IModel{
		/**
		 * Cloudiness, %
		 */
		@JsonProperty("all")
		private Float all;
	}

	@Data
	@NoArgsConstructor
	public static class Rain implements IModel{
		/**
		 * Rain volume for the last 1 hour, mm
		 */
		@JsonProperty("1h")
		private Float oneHour;
		/**
		 * Rain volume for the last 3 hours, mm
		 */
		@JsonProperty("3h")
		private Float threeHour;
	}

	@NoArgsConstructor
	public static class Snow extends Rain implements IModel{

	}

	@Data
	@NoArgsConstructor
	public static class Sys implements IModel{
		/**
		 * Internal parameter
		 */
		@JsonProperty("type")
		private Integer type;
		/**
		 * Internal parameter
		 */
		@JsonProperty("id")
		private int id;
		/**
		 * Internal parameter
		 */
		@JsonProperty("message")
		private Float message;
		/**
		 * Country code (GB, JP etc.)
		 */
		@JsonProperty("country")
		private String countryCode;
		/**
		 * Sunrise time, unix, UTC
		 */
		@JsonProperty("sunrise")
		@JsonDeserialize(using = DateTimeDeserializer.class)
		private LocalDateTime sunRise;
		/**
		 * Sunset time, unix, UTC
		 */
		@JsonProperty("sunset")
		@JsonDeserialize(using = DateTimeDeserializer.class)
		private LocalDateTime senSet;

	}
}

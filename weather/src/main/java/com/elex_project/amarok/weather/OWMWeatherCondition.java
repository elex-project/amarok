/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.weather;


import com.elex_project.abraxas.Stringz;
import com.elex_project.harpy.Uri;

/**
 * @author Elex
 */
public class OWMWeatherCondition {

	private OWMWeatherCondition() {
	}

	public static String getMain(final int id) {
		if (id >= 200 && id < 300) {
			return "천둥"; // Thunderstorm
		} else if (id >= 300 && id < 400) {
			return "이슬비"; //Drizzle
		} else if (id >= 500 && id < 600) {
			return "비"; //Rain
		} else if (id >= 600 && id < 700) {
			return "눈"; //Snow
		} else if (id >= 700 && id < 800) {
			return "대기"; //Atmosphere
		} else if (id == 800) {
			return "맑음"; //Clear
		} else if (id > 800 && id < 900) {
			return "흐림"; //Clouds
		}
		return Stringz.EMPTY_STRING;
	}

	public static String getDesc(final int id) {
		switch (id) {
			case 200:
				return "thunderstorm with light rain";
			case 201:
				return "thunderstorm with rain";
			case 203:
				return "thunderstorm with heavy rain";
			case 210:
				return "light thunderstorm";
			case 211:
				return "thunderstorm";
			case 212:
				return "heavy thunderstorm";
			case 221:
				return "ragged thunderstorm";
			case 230:
				return "thunderstorm with light drizzle";
			case 231:
				return "thunderstorm with drizzle";
			case 232:
				return "thunderstorm with heavy drizzle";
			case 300:
				return "light intensity drizzle";
			case 301:
				return "drizzle";
			case 302:
				return "heavy intensity drizzle";
			case 310:
				return "light intensity drizzle rain";
			case 311:
				return "drizzle rain";
			case 312:
				return "heavy intensity drizzle rain";
			case 313:
				return "shower rain and drizzle";
			case 314:
				return "heavy shower rain and drizzle";
			case 321:
				return "shower drizzle";
			case 500:
				return "light rain";
			case 501:
				return "moderate rain";
			case 502:
				return "heavy intensity rain";
			case 503:
				return "very heavy rain";
			case 504:
				return "extreme rain";
			case 511:
				return "freezing rain";
			case 520:
				return "light intensity shower rain";
			case 521:
				return "shower rain";
			case 522:
				return "heavy intensity shower rain";
			case 531:
				return "ragged shower rain";
			case 600:
				return "light snow";
			case 601:
				return "Snow";
			case 602:
				return "Heavy snow";
			case 611:
				return "Sleet";
			case 612:
				return "Light shower sleet";
			case 613:
				return "Shower sleet";
			case 615:
				return "Light rain and snow";
			case 616:
				return "Rain and snow";
			case 620:
				return "Light shower snow";
			case 621:
				return "Shower snow";
			case 622:
				return "Heavy shower snow";
			case 701:
				return "mist";
			case 711:
				return "Smoke";
			case 721:
				return "Haze";
			case 731:
				return "sand/ dust whirls";
			case 741:
				return "fog";
			case 751:
				return "sand";
			case 761:
				return "dust";
			case 762:
				return "volcanic ash";
			case 771:
				return "squalls";
			case 781:
				return "tornado";
			case 800:
				return "clear sky";
			case 801:
				return "few clouds: 11-25%";
			case 802:
				return "scattered clouds: 25-50%";
			case 803:
				return "broken clouds: 51-84%";
			case 804:
				return "overcast clouds: 85-100%";
			default:
				return Stringz.EMPTY_STRING;
		}
	}

	public static Uri getIcon(final String icon) {
		return Uri.builder()
				.scheme("http").host("openweathermap.org")
				.path("img").path("wn").path(icon + ".png")
				.build();
	}

	public static Uri getIcon2x(final String icon) {
		return Uri.builder()
				.scheme("http").host("openweathermap.org")
				.path("img").path("wn").path(icon + "@2x.png")
				.build();
	}
}

/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2021. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.astro;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

@Slf4j
public class SunriseSunsetCalculatorTest {
	Calendar _now = new GregorianCalendar(TimeZone.getTimeZone("GMT+9:00"));

	@Test
	public void sunrise_and_sunset() throws Exception {
		SunriseSunsetCalculator calculator = new SunriseSunsetCalculator(
				Location.BUSAN, TimeZone.getTimeZone("GMT+9:00")
		);
		log.info(calculator.getOfficialSunriseForDate(_now));
		log.info("시민박명: " + calculator.getCivilSunriseForDate(_now));
		log.info("천문박명: " + calculator.getAstronomicalSunriseForDate(_now));
		log.info("해상박명: " + calculator.getNauticalSunriseForDate(_now));
	}
}

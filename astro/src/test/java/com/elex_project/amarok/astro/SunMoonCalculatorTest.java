/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2021. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.astro;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.ZoneId;

@Slf4j
public class SunMoonCalculatorTest {
	@Test
	public void calcSunAndMoon() throws Exception {
		System.out.println("SunMoonCalculator test");

		ZoneId zone = ZoneId.of("GMT+9");

		// https://api.sunrise-sunset.org/json?lat=35.198362&lng=129.053922&date=2017-11-24
		try {
			int year = 2017, month = 11, day = 24, h = 19, m = 55, s = 0;
			double obsLon = 129.053922, obsLat = 35.198362;
			SunMoonCalculator smc = new SunMoonCalculator(year, month, day, h, m, s, obsLon, obsLat);

			smc.calcSunAndMoon();

			System.out.println("Sun");
			System.out.println(" Az:      " + Math.toDegrees(smc.sunAz) + "º");
			System.out.println(" El:      " + Math.toDegrees(smc.sunEl) + "º");
			System.out.println(" Dist:    " + smc.sunDist + " AU");
			System.out.println(" Rise:    " + SunMoonCalculator.getDateAsString(smc.sunRise, zone, null));
			System.out.println(" Set:     " + SunMoonCalculator.getDateAsString(smc.sunSet, zone, null));
			System.out.println(" Transit: " + SunMoonCalculator.getDateAsString(smc.sunTransit, zone, null) + " (max. elev. " + Math.toDegrees(smc.sunTransitElev) + "º)");
			System.out.println("Moon");
			System.out.println(" Az:      " + Math.toDegrees(smc.moonAz) + "º");
			System.out.println(" El:      " + Math.toDegrees(smc.moonEl) + "º");
			System.out.println(" Dist:    " + (smc.moonDist * SunMoonCalculator.AU) + " km");
			System.out.println(" Age:     " + smc.moonAge + " days");
			System.out.println(" Rise:    " + SunMoonCalculator.getDateAsString(smc.moonRise, zone, null));
			System.out.println(" Set:     " + SunMoonCalculator.getDateAsString(smc.moonSet, zone, null));
			System.out.println(" Transit: " + SunMoonCalculator.getDateAsString(smc.moonTransit, zone, null) + " (max. elev. " + Math.toDegrees(smc.moonTransitElev) + "º)");

			//smc.setTwilight(SunMoonCalculator.TWILIGHT.HORIZON_34arcmin);
			//smc.calcSunAndMoon();

			/*System.out.println("");
			System.out.println("Official twilights:");
			System.out.println("Sun");
			System.out.println(" Rise:    "+SunMoonCalculator.getDateAsString(smc.sunRise));
			System.out.println(" Set:     "+SunMoonCalculator.getDateAsString(smc.sunSet));
			System.out.println("Moon");
			System.out.println(" Rise:    "+SunMoonCalculator.getDateAsString(smc.moonRise));
			System.out.println(" Set:     "+SunMoonCalculator.getDateAsString(smc.moonSet));*/
			//smc.
			// Expected accuracy in over 1800 - 2200:
			// - Sun: 0.005 deg or 20 arcsec. 1-2s in rise/set/transit times.
			// - Mon: 0.02 deg or better. 10s or better in rise/set/transit times.
			//        In most of the cases the actual accuracy in the Moon will be better, but it is not guaranteed.
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}

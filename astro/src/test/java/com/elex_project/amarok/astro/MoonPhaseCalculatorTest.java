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
import java.util.EnumSet;
import java.util.List;

@Slf4j
public class MoonPhaseCalculatorTest {
	private Calendar _now = Calendar.getInstance();

	@Test
	public void calculate() throws Exception {

		MoonPhaseCalculator algorithm = new MoonPhaseCalculator();
		MoonPhaseCalculator.Moon moon = algorithm.calculate(_now);
		System.out.println(moon.toString());

		System.out.println("\n\n");

		MoonPhaseCalculator2.Moon moonPahse = new MoonPhaseCalculator2().calculate(_now);

		System.out.println(moonPahse.toString());
	}
	@Test
	public void calculate2() throws Exception {


		/*MoonPhaseCalculator algorithm = new MoonPhaseCalculator();
		List<MoonPhaseCalculator.MoonEvent> list = algorithm.getNextMoonEvents(_now, 5,
				EnumSet.allOf(MoonPhaseCalculator.MoonEventType.class));

		for (MoonPhaseCalculator.MoonEvent moonEvent : list) {
			System.out.println(moonEvent.toString() +"\t" +moonEvent.getDate());
		}

		System.out.println("\n\n");*/

		List<MoonPhaseCalculator2.MoonEvent> list2 = new MoonPhaseCalculator2()
				.getNextMoonEvents(_now, 5,
				EnumSet.allOf(MoonPhaseCalculator2.MoonEventType.class));

		for (MoonPhaseCalculator2.MoonEvent moonEvent : list2) {
			log.info(moonEvent.toString()+"\t" +moonEvent.getDate());
		}
	}

}

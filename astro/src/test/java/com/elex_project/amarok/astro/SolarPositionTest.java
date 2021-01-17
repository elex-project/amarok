/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.astro;

import com.elex_project.abraxas.Console;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

@Slf4j
public class SolarPositionTest {

	@Test
	public void estimate() {

		SolarPosition solarPosition = SolarPosition
				.estimate(LocalDateTime.now(),
						new Parameters(35.1802563, 127.8292504));
		log.info(solarPosition.toString());
	}
}

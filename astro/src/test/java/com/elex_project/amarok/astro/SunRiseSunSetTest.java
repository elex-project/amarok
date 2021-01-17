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
public class SunRiseSunSetTest {

	@Test
	public void estimate() {
		SunRiseSunSet estimation = SunRiseSunSet
				.estimate(LocalDateTime.now(),
						new Parameters(35.198362, 129.053922));
		log.info(estimation.toString());
	}
}

/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2021. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;

import java.net.http.HttpResponse;

/**
 * @author Elex
 */
@Slf4j
public final class TransCoordResponse
		extends BaseResponse<TransCoordResult> {

	TransCoordResponse(final HttpResponse<String> response) throws JsonProcessingException {
		log.debug("Rx({}): {}", response.statusCode(), response.body());
		setHttpStatus(response.statusCode());
		setBody(objectMapper
				.readValue(response.body(), TransCoordResult.class));
	}
}

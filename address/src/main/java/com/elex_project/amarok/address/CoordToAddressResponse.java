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
public final class CoordToAddressResponse
		extends BaseResponse<CoordToAddressResult> {
	CoordToAddressResponse(final HttpResponse<String> response) throws JsonProcessingException {
		log.debug("Rx({}): {}", response.statusCode(), response.body());
		setHttpStatus(response.statusCode());
		setBody(objectMapper
				.readValue(response.body(), CoordToAddressResult.class));
	}
}

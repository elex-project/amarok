/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

/**
 *
 * @param <T> response body type
 * @author Elex
 */
@Data
@NoArgsConstructor
@Getter
@Setter(AccessLevel.PROTECTED)
abstract class BaseResponse<T> implements IResponse<T>, KakaoApi {
	protected static ObjectMapper objectMapper = new ObjectMapper();

	private int httpStatus;
	private T body;

}

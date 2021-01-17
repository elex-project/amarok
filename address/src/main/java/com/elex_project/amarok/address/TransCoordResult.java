/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Elex
 */
@Data
@NoArgsConstructor
public final class TransCoordResult implements IResult,IModel {

	@JsonProperty("meta")
	private Meta meta;
	@JsonProperty("documents")
	private List<Result> results;

	@Data
	@NoArgsConstructor
	public static class Meta  implements IModel{
		/**
		 * 변환된 지번 주소 및 도로명 주소 의 개수, 0 또는 1
		 */
		@JsonProperty("total_count")
		private int totalCount = 1;
	}

	@Data
	@NoArgsConstructor
	public static class Result  implements IModel {

		@JsonProperty("x")
		private double x;
		@JsonProperty("y")
		private double y;

	}
}

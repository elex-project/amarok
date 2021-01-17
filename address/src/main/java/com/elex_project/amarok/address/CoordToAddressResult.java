/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static com.elex_project.amarok.address.Constants.EMPTY_STRING;

/**
 * 좌표 검색 결과 데이터
 * @author Elex
 */
@Data
@NoArgsConstructor
public final class CoordToAddressResult implements IResult, IModel {

	@JsonProperty("meta")
	private Meta meta;
	@JsonProperty("documents")
	private List<Result> addresses;

	@Data
	@NoArgsConstructor
	public static class Meta implements IModel {
		/**
		 * 변환된 지번 주소 및 도로명 주소 의 개수, 0 또는 1
		 */
		@JsonProperty("total_count")
		private int totalCount = 1;

	}
	/**
	 * 좌표 검색 결과 데이터
	 */
	@Data
	@NoArgsConstructor
	public static class Result implements IModel {

		@JsonProperty("address")
		private Address address;
		@JsonProperty("road_address")
		private RoadAddress roadAddress;

	}

	/**
	 * 지번 주소
	 */
	@Data
	@NoArgsConstructor
	public static class Address implements IModel {
		/**
		 * 전체 주소
		 */
		@JsonProperty("address_name")
		protected String addressName = EMPTY_STRING;
		/**
		 * 지역 1Depth명
		 */
		@JsonProperty("region_1depth_name")
		protected String regionLv1 = EMPTY_STRING;
		/**
		 * 지역 2Depth명
		 */
		@JsonProperty("region_2depth_name")
		protected String regionLv2 = EMPTY_STRING;
		/**
		 * 지역 3Depth명
		 */
		@JsonProperty("region_3depth_name")
		protected String regionLv3 = EMPTY_STRING;
		/**
		 * 우편번호
		 */
		@JsonProperty("zip_code")
		protected String zipCode = EMPTY_STRING;
		/**
		 * 산 여부, "Y" 또는 "N"
		 */
		@JsonProperty("mountain_yn")
		@JsonDeserialize(using = BooleanDeserializer.class)
		private boolean mountain = false;
		/**
		 * 지번 주 번지
		 */
		@JsonProperty("main_address_no")
		private String addressNumberMain = EMPTY_STRING;
		/**
		 * 지번 부 번지, 없을 경우 ""
		 */
		@JsonProperty("sub_address_no")
		private String addressNumberSub = EMPTY_STRING;

	}

	/**
	 * 도로명 주소
	 */
	@Data
	@NoArgsConstructor
	public static class RoadAddress implements IModel {
		/**
		 * 전체 주소
		 */
		@JsonProperty("address_name")
		protected String addressName = EMPTY_STRING;
		/**
		 * 지역 1Depth명
		 */
		@JsonProperty("region_1depth_name")
		protected String regionLv1 = EMPTY_STRING;
		/**
		 * 지역 2Depth명
		 */
		@JsonProperty("region_2depth_name")
		protected String regionLv2 = EMPTY_STRING;
		/**
		 * 지역 3Depth명
		 */
		@JsonProperty("region_3depth_name")
		protected String regionLv3 = EMPTY_STRING;
		/**
		 * 우편번호
		 */
		@JsonProperty("zone_no")
		protected String zipCode = EMPTY_STRING;
		/**
		 * 도로명
		 */
		@JsonProperty("road_name")
		private String roadName = EMPTY_STRING;
		/**
		 * 지하 여부, Y 또는 N
		 */
		@JsonProperty("underground_yn")
		@JsonDeserialize(using = BooleanDeserializer.class)
		private boolean underground = false;
		/**
		 * 건물 본번
		 */
		@JsonProperty("main_building_no")
		private String buildingNumberMain = EMPTY_STRING;
		/**
		 * 건물 부번. 없을 경우 ""
		 */
		@JsonProperty("sub_building_no")
		private String buildingNumberSub = EMPTY_STRING;
		/**
		 * 건물 이름
		 */
		@JsonProperty("building_name")
		private String buildingName = EMPTY_STRING;


	}


}

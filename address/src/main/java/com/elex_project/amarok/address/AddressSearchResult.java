/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2021. Elex. All Rights Reserved.                             *
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
 * 주소 검색 결과 데이터
 *
 * @author Elex
 */
@Data
@NoArgsConstructor
public final class AddressSearchResult implements IResult, IModel {

	@JsonProperty("meta")
	private Meta meta;
	@JsonProperty("documents")
	private List<Result> addresses;

	/**
	 * 주소 검색 결과 데이터
	 */
	@Data
	@NoArgsConstructor
	public static class Result implements IModel {

		/**
		 * 전체 지번 주소 또는 전체 도로명 주소, 입력에 따라 결정됨
		 */
		@JsonProperty("address_name")
		private String addressName;
		/**
		 * address_name의 값의 타입
		 */
		@JsonProperty("address_type")
		@JsonDeserialize(using = AddressTypeDeserializer.class)
		private AddressType addressType;
		/**
		 * X 좌표값 혹은 longitude
		 */
		@JsonProperty("x")
		private double x;
		/**
		 * Y 좌표값 혹은 latitude
		 */
		@JsonProperty("y")
		private double y;
		/**
		 * 지번 주소 상세 정보
		 */
		@JsonProperty("address")
		private Address address;
		/**
		 * 도로명 주소 상세 정보
		 */
		@JsonProperty("road_address")
		private RoadAddress roadAddress;

	}

	@Data
	@NoArgsConstructor
	public static class Meta implements IModel {
		/**
		 * 검색어에 검색된 문서 수
		 */
		@JsonProperty("total_count")
		private int totalCount = 1;
		/**
		 * 현재 페이지가 마지막 페이지인지 여부
		 * 값이 false이면 page를 증가시켜 다음 페이지 요청 가능
		 */
		@JsonProperty("is_end")
		private boolean isEnd = true;
		/**
		 * total_count 중 노출 가능 문서 수, 최대 45
		 */
		@JsonProperty("pageable_count")
		private int pageableCount = 1;
	}

	/**
	 * 지번 주소
	 */
	@Data
	@NoArgsConstructor
	public static class Address implements IModel {
		/**
		 * 전체 지번 주소 or 전체 도로명 주소
		 */
		@JsonProperty("address_name")
		protected String addressName = EMPTY_STRING;
		/**
		 * 지역명 1
		 */
		@JsonProperty("region_1depth_name")
		protected String regionLv1 = EMPTY_STRING;
		/**
		 * 지역명 2
		 */
		@JsonProperty("region_2depth_name")
		protected String regionLv2 = EMPTY_STRING;
		/**
		 * 지역명 3
		 */
		@JsonProperty("region_3depth_name")
		protected String regionLv3 = EMPTY_STRING;
		/**
		 * 지역 3 Depth, 행정동 명칭
		 */
		@JsonProperty("region_3depth_h_name")
		private String regionLv3H = EMPTY_STRING;
		/**
		 * 우편번호
		 */
		@JsonProperty("zip_code")
		protected String zipCode = EMPTY_STRING;
		/**
		 * X 좌표값 혹은 longitude
		 */
		@JsonProperty("x")
		protected double x = 0d;
		/**
		 * Y 좌표값 혹은 latitude
		 */
		@JsonProperty("y")
		protected double y = 0d;


		/**
		 * 행정 코드
		 */
		@JsonProperty("h_code")
		private String hCode = EMPTY_STRING;
		/**
		 * 법정 코드
		 */
		@JsonProperty("b_code")
		private String bCode = EMPTY_STRING;
		/**
		 * 산 여부
		 */
		@JsonProperty("mountain_yn")
		@JsonDeserialize(using = BooleanDeserializer.class)
		private boolean mountain = false;
		/**
		 * 지번 주번지
		 */
		@JsonProperty("main_address_no")
		private String addressNumberMain = EMPTY_STRING;
		/**
		 * 지번 부번지. 없을 경우 ""
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
		 * 전체 지번 주소 or 전체 도로명 주소
		 */
		@JsonProperty("address_name")
		protected String addressName = EMPTY_STRING;
		/**
		 * 지역명 1
		 */
		@JsonProperty("region_1depth_name")
		protected String regionLv1 = EMPTY_STRING;
		/**
		 * 지역명 2
		 */
		@JsonProperty("region_2depth_name")
		protected String regionLv2 = EMPTY_STRING;
		/**
		 * 지역명 3
		 */
		@JsonProperty("region_3depth_name")
		protected String regionLv3 = EMPTY_STRING;
		/**
		 * 우편번호
		 */
		@JsonProperty("zone_no")
		protected String zipCode = EMPTY_STRING;
		/**
		 * X 좌표값 혹은 longitude
		 */
		@JsonProperty("x")
		protected double x = 0d;
		/**
		 * Y 좌표값 혹은 latitude
		 */
		@JsonProperty("y")
		protected double y = 0d;
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

/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2021. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

import org.jetbrains.annotations.NotNull;

import static com.elex_project.amarok.address.Constants.EMPTY_STRING;

/**
 * @author Elex
 */
public enum AddressType {
	/**
	 * 지명
	 */
	REGION("REGION"),
	/**
	 * 도로명
	 */
	ROAD("ROAD"),
	/**
	 * 지번 주소
	 */
	REGION_ADDR("REGION_ADDR"),
	/**
	 * 도로명 주소
	 */
	ROAD_ADDR("ROAD_ADDR"),
	UNKNOWN(EMPTY_STRING);

	private final String title;

	AddressType(String title) {
		this.title = title;
	}

	@NotNull
	public static AddressType of(@NotNull final String value) {
		for (AddressType item : values()) {
			if (item.title.equals(value)) return item;
		}
		return UNKNOWN;
	}

	@Override
	public String toString() {
		return title;
	}
}

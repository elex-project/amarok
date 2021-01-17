/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

/**
 * @author Elex
 * @param <T>
 */
interface IResponse<T> {
	public int getHttpStatus();
	public T getBody();
}

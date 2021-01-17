/******************************************************************************
 * Project Psychopomp                                                              *
 * for Environments                                                           *
 *                                                                            *
 * Copyright (c) 2020. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

import java.io.IOException;

/**
 * @author Elex
 * @param <T>
 */
interface IRequest<T extends IResponse<?>> {
	public T send() throws IOException, InterruptedException;
}

/******************************************************************************
 * Project Amarok                                                             *
 *                                                                            *
 * Copyright (c) 2021. Elex. All Rights Reserved.                             *
 * https://www.elex-project.com/                                              *
 ******************************************************************************/

package com.elex_project.amarok.address;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Locale;

/**
 * @author Elex
 */
public class BooleanDeserializer extends JsonDeserializer<Boolean> {

	@Override
	public Boolean deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
		return p.getText().toLowerCase(Locale.getDefault()).equals("y");
	}
}
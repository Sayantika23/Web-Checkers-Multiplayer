package com.webcheckers.controller;

import com.webcheckers.ui.JsonUtils;

import spark.ResponseTransformer;

/**
 * The Class JsonTransformer.
 */
public class JsonTransformer implements ResponseTransformer {
	
	/**
	 * Renders json objects for
	 * get/post requests
	 *
	 * @return json object
	 */
	@Override
	public String render(Object obj) throws Exception {
		return JsonUtils.toJson(obj);
	}
}

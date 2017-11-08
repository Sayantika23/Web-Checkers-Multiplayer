package com.webcheckers.controller;

import com.webcheckers.ui.JsonUtils;

import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {
	
	@Override
	public String render(Object obj) throws Exception {
		return JsonUtils.toJson(obj);
	}

}

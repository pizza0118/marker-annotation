package com.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.annotation.vo.ApiVO;
import com.demo.reflect.ApiReflection;

@Controller
public class PageController {
	
	@GetMapping("/")
	public String init() {
		return "index";
	}
	
	@GetMapping("/api")
	public String api() {
		return "api";
	}
	
	@PostMapping("/apiData")
	public @ResponseBody ApiVO apiData() {
		return ApiReflection.apiInfo();
	}
}

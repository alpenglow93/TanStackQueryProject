package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.service.FoodService;
import com.sist.web.vo.FoodVO;

import lombok.RequiredArgsConstructor;

import java.util.*;

/*
 * 	1. Dockerfile
 * 		Spring-Boot
 * 			|
 * 		   Jar
 * 			|
 * 		Docker image
 * 			|
 * 		Container : 한 개를 실행하는 프로그램 (환경설정)
 * 
 * 	2. docker-compose
 * 	3. nginx.conf
 * 	4. Jenkinsfile
 * 
 */

@Controller
@RequiredArgsConstructor
public class FoodController {
	private final FoodService fService;
	
	@GetMapping("/main")
	public String main_page(Model model)
	{
		model.addAttribute("main_html", "main/home");
		return "main/main";
	}
	
	@RequestMapping("/food/list")
	public String food_list(@RequestParam(value="search", required = false) String search,
			@RequestParam(value="page", required = false) String page, Model model)
	{
		if(page==null)
			page="1";
		int curpage = Integer.parseInt(page);
		
		Map map = new HashMap();
		map.put("start", (curpage*12)-12);
		map.put("search", search);
		
		List<FoodVO> list = fService.foodListData(map);
		int count = fService.foodListTotalPage(search);
		
		// 페이지 나누기
		int totalpage = (int)(Math.ceil(count/12.0));
		final int BLOCK = 12;
		int startPage = ((curpage-1)/BLOCK*BLOCK)+1;
		int endPage = ((curpage-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage = totalpage;
		
		// HTML로 전송
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("count", count);
		
		model.addAttribute("main_html", "food/list");
		return "main/main";
	}
}

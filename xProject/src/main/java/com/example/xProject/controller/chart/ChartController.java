package com.example.xProject.controller.chart;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.xProject.service.chart.ChartService;

@RestController
@RequestMapping("chart/*")
public class ChartController {
	
	@Inject
	ChartService chartService;
	
	@RequestMapping("google_chart") //구매동향 분석 페이지로 이동(관리자만 가능)
	public ModelAndView chart1() {
		return new ModelAndView("chart/sales_chart");
	}
	
	@RequestMapping("cart_money_list") //상품별 구매동향 그래프 출력
	public JSONObject cart_money_list() {
		return chartService.getChartData1();
	}
	
	@RequestMapping("user_money_list") //회원별 구매동향 그래프 출력
	public JSONObject user_money_list() {
		return chartService.getChartData2();
	}	
}

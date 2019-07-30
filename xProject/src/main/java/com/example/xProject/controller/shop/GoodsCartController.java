package com.example.xProject.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.xProject.model.shop.dto.GoodsCartDTO;
import com.example.xProject.service.shop.GoodsCartService;

@Controller
@RequestMapping("shop/goodscart/*")
public class GoodsCartController {

	@Inject
	GoodsCartService goodscartService;
	
	@RequestMapping("list.do") //장바구니 조회(로그인한 회원만 가능)
	public ModelAndView list(HttpSession session, ModelAndView mav) {
		Map<String, Object> map = new HashMap<>();
		
		String userid = (String)session.getAttribute("userid");
		
		if(userid != null) {
			List<GoodsCartDTO> list = goodscartService.listGoodsCart(userid);
			int sumMoney = goodscartService.sumMoney(userid);			
			
			int fee = sumMoney >= 25000 ? 0 : 3500;
			
			map.put("sumMoney", sumMoney);
			map.put("fee", fee);
			map.put("sum", sumMoney+fee);
			
			map.put("list", list);//맵에 자료 추가
			map.put("count", list.size());
			mav.setViewName("shop/goodscart_list");
			mav.addObject("map",map);
			
			return mav;
		}else {
			return new ModelAndView("user/login","",null);
		}
	}
	
	@RequestMapping("insert.do") //장바구니에 물건 담기
	public String insert(HttpSession session, @ModelAttribute GoodsCartDTO dto) {

		String userid=(String)session.getAttribute("userid");
		
		dto.setUserid(userid);
		goodscartService.insert(dto);
		return "redirect:/shop/goodscart/list.do";
	}
	
	@RequestMapping("delete.do") //장바구니에서 특정 상품(레코드) 삭제
	public String delete(@RequestParam int cart_id, HttpSession session) {		
		if(session.getAttribute("userid")!=null) 
			goodscartService.delete(cart_id);
		return "redirect:/shop/goodscart/list.do";
	}

	@RequestMapping("deleteAll.do") //장바구니 비우기
	public String deleteAll(HttpSession session) {
		String userid=(String)session.getAttribute("userid");
		if (userid != null) {
			goodscartService.deleteAll(userid);
	}
	return "redirect:/shop/goodscart/list.do";
		
	}
	
	@RequestMapping("update.do") //장바구니 수정(물건 갯수)
	public String update(@RequestParam int[] amount,
			@RequestParam int[] cart_id, HttpSession session) {
		String userid = (String)session.getAttribute("userid");
		if(userid != null) {
			for(int i=0; i<cart_id.length; i++) {
				if(amount[i] == 0) {
					goodscartService.delete(cart_id[i]);
				}else {
					GoodsCartDTO dto = new GoodsCartDTO();
					dto.setUserid(userid);
					dto.setCart_id(cart_id[i]);
					dto.setAmount(amount[i]);
					goodscartService.modifyGoodsCart(dto);					
				}
			}
		}
		return "redirect:/shop/goodscart/list.do";
	}
	
	@RequestMapping("orderAll.do") //장바구니에 담은 물건 한꺼번에 주문하기
	public String orderAll(HttpSession session) throws Exception {
		String userid = (String)session.getAttribute("userid");
		if(userid != null) {			
			goodscartService.orderAll(userid);	
		}
		return "redirect:/shop/goodscart/list.do";
	}
	
	@RequestMapping("order.do") //개별 주문
	public String order(@RequestParam int cart_id, HttpSession session) throws Exception {
		String userid = (String)session.getAttribute("userid");
		if(userid != null) {
			goodscartService.order(cart_id);
		}
		return "redirect:/shop/goodscart/list.do";
	}
	
	@RequestMapping("mypage") //회원별 구매내역 조회
	public ModelAndView myPage(HttpSession session, ModelAndView mav) {
		String userid = (String)session.getAttribute("userid");
		Map<String,Object> map = new HashMap<>();
		if(userid!=null) {
			List<GoodsCartDTO> list = goodscartService.myPage(userid);
			map.put("list", list);
			map.put("cnt", list.size());
			mav.setViewName("shop/my_order");
			mav.addObject("map", map);
		}
		return mav;
	}
}	
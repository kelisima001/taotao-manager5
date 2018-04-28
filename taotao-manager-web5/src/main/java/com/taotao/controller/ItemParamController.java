package com.taotao.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数controller
 * @author wxdsg
 *
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping("/list")
	@ResponseBody
	public EasyUIDataGridResult getItemParam(Integer page,Integer rows){
//		System.out.println("/item/param/list:"+page+","+rows);
		EasyUIDataGridResult result=itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	@RequestMapping("/delete")
	public String deleteItemParam(Long id){
		
		itemParamService.deleteItemParam(id);
		return "ok";
	}
}

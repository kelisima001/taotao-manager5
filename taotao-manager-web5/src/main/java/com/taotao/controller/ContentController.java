package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.service.ContentService;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertCategory(TbContent tbContent) {
		
		contentService.saveContent(tbContent);
		return TaotaoResult.ok();
	}
	
	@RequestMapping("/query/list")
	@ResponseBody
	public List<TbContent> getContentList(Long cid) {
		
		List<TbContent> list = contentService.getContntList(cid);
		return list;
	}
	
	/*@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult updateContent(TbContent tbContent) {
		
		TaotaoResult result = contentService.updateContent(tbContent);
		return result;
	}*/
	
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContent(Long ids) {
		
		TaotaoResult result = contentService.deleteContent(ids);
		return result;
	}
}

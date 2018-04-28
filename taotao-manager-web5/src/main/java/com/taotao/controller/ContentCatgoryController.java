package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCatgoryService;

/**
 * 内容分类管理Controller
 * @author wxdsg
 *
 */
@Controller
@RequestMapping("/content/category")
public class ContentCatgoryController {

	@Autowired
	private ContentCatgoryService contentCatgoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	private List<EasyUITreeNode> getContentCategoryList
			(@RequestParam(value="id",defaultValue="0")Long parentId) {
		
		List<EasyUITreeNode> list = contentCatgoryService.getContentCatList(parentId);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	private TaotaoResult createContentCategory(String name,Long parentId) {
		
		TaotaoResult result = contentCatgoryService.insertCategory(name, parentId);
		return result;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	private TaotaoResult deleteContentCategory(Long id) {
		
		TaotaoResult result = contentCatgoryService.deleteCategory(id);
		return result;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	private TaotaoResult updateContentCategory(Long id,String name) {
		
		TaotaoResult result = contentCatgoryService.updateCategory(name, id);
		return result;
	}
}

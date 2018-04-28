package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCatgoryService {

	List<EasyUITreeNode> getContentCatList(Long parentId);
	TaotaoResult insertCategory(String name,Long parentId);
	TaotaoResult deleteCategory(Long id);
	TaotaoResult updateCategory(String name,Long id);
	TaotaoResult updateCategory(Long id,Boolean isParent);
}

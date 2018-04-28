package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	
	EasyUIDataGridResult getItemList(int page,int rows);
	TbItem getItemById(Long ItemId);
	TaotaoResult createItem(TbItem item,String desc);
}

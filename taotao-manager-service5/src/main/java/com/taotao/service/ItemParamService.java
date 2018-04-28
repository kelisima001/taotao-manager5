package com.taotao.service;


import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

public interface ItemParamService {

	EasyUIDataGridResult getItemParamList(int page,int rows);

	TaotaoResult getItemParamByCid(long cid);

	void deleteItemParam(Long id);
}

package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	List<TbContent> getContntList(Long cid);

	void saveContent(TbContent tbContent);

	TaotaoResult deleteContent(Long ids);

	
}

package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.ExceptionUtil;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;

/**
 * 内容管理service
 * @author wxdsg
 *
 */
@Service
public class ContentServiceImpl implements ContentService{

	@Autowired
	private TbContentMapper tbContentMapper;
	
	@Override
	public List<TbContent> getContntList(Long cid) {
		
		TbContentExample example=new TbContentExample();
		List<TbContent> list = tbContentMapper.selectByExampleWithBLOBs(example);

		return list;
	}

	/**
	 * 插入内容
	 */
	@Override
	public void saveContent(TbContent tbContent) {
		// 
		tbContent.setUpdated(new Date());
		tbContent.setCreated(new Date());
		tbContentMapper.insert(tbContent);
	}

	@Override
	public TaotaoResult deleteContent(Long ids) {
		// 
		tbContentMapper.deleteByPrimaryKey(ids);
		return TaotaoResult.ok();
	}

	

	

	
}

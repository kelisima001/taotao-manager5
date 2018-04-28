package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.ItemParamResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.mapper.TbItemParamExMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.pojo.TbItemParamItemExample;
import com.taotao.pojo.TbItemParamModel;
import com.taotao.service.ItemCatService;
import com.taotao.service.ItemParamService;

/**
 * 商品规格参数Service
 * @author wxdsg
 *
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	@Autowired
	private TbItemParamMapper itemParamMapper;
	
	@Autowired
	private TbItemParamExMapper itemParamExMapper;
	@Override
	public EasyUIDataGridResult getItemParamList(int page, int rows) {
		
		//分页插件
		PageHelper.startPage(page, rows);
		
		//商品规格参数列表
		List<TbItemParamModel> list = itemParamExMapper.getItemParamList();
		
		
		//取分页信息
		PageInfo<TbItemParamModel> pageInfo=new PageInfo<>(list);
		
		EasyUIDataGridResult easyResult=new EasyUIDataGridResult();
		easyResult.setTotal(pageInfo.getTotal());
		easyResult.setRows(list);
		
		return easyResult;
	}

	@Override
	public TaotaoResult getItemParamByCid(long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if (list != null && list.size() > 0) {
			return TaotaoResult.ok(list.get(0));
		}
		
		return TaotaoResult.ok();
	}

	@Override
	public void deleteItemParam(Long id) {
		
		itemParamMapper.deleteByPrimaryKey(id);
	}
}

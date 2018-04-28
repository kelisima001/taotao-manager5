package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.service.ItemCatService;

/**
 * 商品类目service
 * @author wxdsg
 *
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private  TbItemCatMapper itemCatMapper;

	@Override
	public List<EasyUITreeNode> getItemCatList(Long parentId) {
		
		// 根据parentId查询分类列表
		TbItemCatExample example=new TbItemCatExample();
		//设置查询条件
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		//转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList=new ArrayList<>();
		for(TbItemCat item:list) {
			//创建一个节点对象
			EasyUITreeNode node =new EasyUITreeNode();
			node.setId(item.getId());
			node.setState(item.getIsParent()?"closed":"open");
			node.setText(item.getName());
			//添加到列表中
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public TbItemCat getItemCatById(Long id) {
		TbItemCatExample example=new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		List<TbItemCat> list=itemCatMapper.selectByExample(example);
		
		TbItemCat item=new TbItemCat();
		
		//获取名字
		String name=null;
		if(list.size()>0&&list!=null) {
			item=list.get(0);
		}
		return item;
	}

}

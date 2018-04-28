package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCatgoryService;

/**
 * 内容分类管理service
 * @author wxdsg
 *
 */
@Service
public class ContentCatgoryServiceImpl implements ContentCatgoryService {

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;
	
	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		// 根据parentid查询子节点列表
		TbContentCategoryExample example=new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list=contentCategoryMapper.selectByExample(example);
		List<EasyUITreeNode> resultList=new ArrayList<>();
		for(TbContentCategory tbContentCategory:list) {
			//创建EasyUITreeNode节点
			EasyUITreeNode node=new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			//添加到列表
			resultList.add(node);
		}
		
		return resultList;
	}

	@Override
	public TaotaoResult insertCategory(String name, Long parentId) {
		//
		TbContentCategory contentCategory=new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setParentId(parentId);
		//1正常,2删除
		contentCategory.setStatus(1);
		contentCategory.setIsParent(false);
		contentCategory.setSortOrder(1);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		//插入数据
		contentCategoryMapper.insert(contentCategory);
		//取返回的主键
		Long id=contentCategory.getId();
//		判断父节点的isparent属性
//		查询父节点
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if(!parentNode.getIsParent()) {
			parentNode.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		//返回主键
		return TaotaoResult.ok(id);
	}

	@Override
	public TaotaoResult deleteCategory(Long id) {
		//如果有子节点则不能删除
		// 根据id查询子节点列表
		if(haveSonNode(id)) {
			return (TaotaoResult.ok("该节点有子节点不能删除"));
		}

		//删除节点
		contentCategoryMapper.deleteByPrimaryKey(id);
		//如父节点没有子节点,把父节点的isparent设为false
		//得到被删除的node的parentid
		
		Long parentId=contentCategoryMapper.selectByPrimaryKey(id).getParentId();
		
		if(!haveSonNode(parentId)) {
			updateCategory(parentId,false);
		}
		
		return TaotaoResult.ok();
	}
	
	/**
	 * 查询是否有子节点
	 */
	public boolean haveSonNode(Long parentId) {
		
		boolean b=false;
		try {
			TbContentCategoryExample example=new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentId);
			
			List<TbContentCategory> list=contentCategoryMapper.selectByExample(example);
			if(list.size()>0) {
				b= true;
			}else {
				b= false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	/**
	 * 修改name
	 */
	@Override
	public TaotaoResult updateCategory(String name,Long id) {
		//
		TbContentCategory category=contentCategoryMapper.selectByPrimaryKey(id);
		category.setName(name);
		contentCategoryMapper.updateByPrimaryKey(category);
		Long myId=category.getId();
		return TaotaoResult.ok(myId);
	}

	/**
	 * 修改isparent
	 */
	@Override
	public TaotaoResult updateCategory(Long id,Boolean isParent) {
		// 
		TbContentCategory category=contentCategoryMapper.selectByPrimaryKey(id);
		category.setIsParent(isParent);
		return TaotaoResult.ok(id);
	} 

}

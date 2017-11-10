package cn.e3mall.content.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.e3mall.content.service.ContentCategoryService;
import cn.e3mall.mapper.TbContentCategoryMapper;
import cn.e3mall.pojo.TbContentCategory;
import cn.e3mall.pojo.TbContentCategoryExample;
import cn.e3mall.pojo.TbContentCategoryExample.Criteria;
import cn.e3mall.utils.E3mallResult;
import cn.e3mall.utils.TreeNode;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

	@Autowired
	private TbContentCategoryMapper tbContentCategoryMapper;
	
	@Override
	public List<TreeNode> findContentCategoryByParentId(Long parentId) {
		
		List<TreeNode> list = new ArrayList<TreeNode>();
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list2 = tbContentCategoryMapper.selectByExample(example);
		
		//对结果进行封装
		for (TbContentCategory tbContentCategory : list2) {
			TreeNode treeNode = new TreeNode();
			treeNode.setId(tbContentCategory.getId());
			treeNode.setState(tbContentCategory.getIsParent() ? "closed":"open");
			treeNode.setText(tbContentCategory.getName());
			
			list.add(treeNode);
			
		}
		
		return list;
	}
	
	/**
	 * 需求:新建树形分类节点
	 * 参数:Long parentId,String name
	 * 返回值:E3mallResult.ok(TbCOntentCategory)
	 * 业务:
	 * 1,如果新建节点父节点是子节点,更新父节点状态.
	 * 2,如果新建节点父节点是父节点,直接添加即可
	 */
	public E3mallResult createNode(Long parentId, String name) {
		// 创建分类对象,封装分类数据
		TbContentCategory category = new TbContentCategory();
		//设置父id
		category.setParentId(parentId);
		//节点名称
		category.setName(name);
		
		//状态。可选值:1(正常),2(删除)
		category.setStatus(1);
		//排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
		category.setSortOrder(1);
		//该类目是否为父类目，1为true，0为false
		category.setIsParent(false);
		//节点时间
		Date date = new Date();
		category.setUpdated(date);
		category.setCreated(date);
		//保存节点	
		tbContentCategoryMapper.insert(category);
		
		
		//根据新建节点父id查询父节点数据
		//关系:当前节点父id是当前节点父节点的id
		TbContentCategory contentCategory = tbContentCategoryMapper.selectByPrimaryKey(parentId);
		//判断父节点是否是子节点
		if(!contentCategory.getIsParent()){
			//表示此节点是子节点,修改父节点状态
			contentCategory.setIsParent(true);
			//修改
			tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
		}
		
		//返回新建节点对象
		return E3mallResult.ok(category);
	}

}

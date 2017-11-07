package cn.e3mall.manager.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

import cn.e3mall.manager.service.ItemCatService;
import cn.e3mall.mapper.TbItemCatMapper;
import cn.e3mall.pojo.TbItemCat;
import cn.e3mall.pojo.TbItemCatExample;
import cn.e3mall.pojo.TbItemCatExample.Criteria;
import cn.e3mall.utils.ItemCatResult;

@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper tbItemCatMapper;
	
	/**
	 * 
	 */
	@Override
	public List<ItemCatResult> findItemCatByPrrentId(Long parentId) {
		
		List<ItemCatResult> result = new ArrayList<ItemCatResult>();
		
		//根据父id查询
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		
		List<TbItemCat> list = tbItemCatMapper.selectByExample(example);
		for (TbItemCat tbItemCat : list) {
			ItemCatResult itemCatResult = new ItemCatResult();
			
			itemCatResult.setId(tbItemCat.getId());
			itemCatResult.setText(tbItemCat.getName());
			itemCatResult.setState(tbItemCat.getIsParent() ? "closed":"open");
			
			result.add(itemCatResult);
		}
		
		//返回结果

		return result;
	}

}

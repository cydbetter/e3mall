package cn.e3mall.manager.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.manager.service.ItemService;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.utils.PageBeanResult;

@Service
public class ItemServiceImpl implements ItemService {

	//注入商品mapper接口代理对象
	@Autowired
	private TbItemMapper itemMapper;

	@Override
	public TbItem findItemById(Long itemId) {
		return itemMapper.selectByPrimaryKey(itemId);
	}

	
	public PageBeanResult  findItemsByPage(Integer pageNum,Integer pageSize) {
		
		//创建查询数据条件对象
		TbItemExample example = new TbItemExample();
		
		//在查询之前使用pageHelper对象，设置分页
		PageHelper.startPage(pageNum, pageSize);
		
		//获取分页数据
		List<TbItem> list = itemMapper.selectByExample(example);
		
		//创建PageInfo对象，封装数据,为了查询总记录数
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		
		//对结果进行包装
		PageBeanResult result = new PageBeanResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		
		return result;
	}
}

package cn.e3mall.manager.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.manager.service.ItemService;
import cn.e3mall.mapper.TbItemDescMapper;
import cn.e3mall.mapper.TbItemMapper;
import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.pojo.TbItemExample;
import cn.e3mall.utils.E3mallResult;
import cn.e3mall.utils.IDUtils;
import cn.e3mall.utils.PageBeanResult;

@Service
public class ItemServiceImpl implements ItemService {

	//注入商品mapper接口代理对象
	@Autowired
	private TbItemMapper itemMapper;
	
	@Autowired
	private TbItemDescMapper tbItemDescMapper;

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

	/**
	 * 
	 * 功能，插入一条商品数据
	 * 参数：Item对象，ItemDesc对象
	 * 返回：E3mallResult
	 */
	 
	@Override
	public E3mallResult saveItem(TbItem tbItem, TbItemDesc tbItemDesc) {
		
		//由于商品的ID不是自增的额，所以手动设置,使用工具类
		long itemId = IDUtils.genItemId();
		
		tbItem.setId(itemId);
		//补全日期
		Date date = new Date();
		tbItem.setCreated(date);
		tbItem.setUpdated(date);
		tbItem.setStatus((byte) 1);
		
		itemMapper.insert(tbItem);
		
		tbItemDesc.setItemId(itemId);
		//补全日期
		tbItemDesc.setCreated(date);
		tbItemDesc.setUpdated(date);
		
		tbItemDescMapper.insert(tbItemDesc);
		
		return E3mallResult.ok();
	}
}

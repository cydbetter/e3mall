package cn.e3mall.manager.service;

import cn.e3mall.pojo.TbItem;
import cn.e3mall.pojo.TbItemDesc;
import cn.e3mall.utils.E3mallResult;
import cn.e3mall.utils.PageBeanResult;

public interface ItemService {

	/**
	 * 需求:根据id查询商品数据
	 * 
	 * @param Long
	 *            itemId
	 * @return TbItem
	 */
	public abstract TbItem findItemById(Long itemId);
	
	public PageBeanResult  findItemsByPage(Integer pageNum,Integer pageSize);
	
	/**
	 * 功能，插入一条商品数据
	 * 参数：Item对象，ItemDesc对象
	 * 返回：E3mallResult
	 */
	public E3mallResult saveItem(TbItem tbItem,TbItemDesc tbItemDesc);

}

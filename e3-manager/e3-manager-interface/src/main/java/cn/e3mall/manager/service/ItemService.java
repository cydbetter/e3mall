package cn.e3mall.manager.service;

import cn.e3mall.pojo.TbItem;
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

}

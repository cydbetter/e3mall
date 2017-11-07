package cn.e3mall.manager.service;

import java.util.List;

import cn.e3mall.utils.ItemCatResult;

public interface ItemCatService {

	public List<ItemCatResult> findItemCatByPrrentId(Long parentId);
	
}

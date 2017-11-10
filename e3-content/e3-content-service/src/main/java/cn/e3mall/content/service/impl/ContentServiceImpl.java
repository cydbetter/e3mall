package cn.e3mall.content.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.e3mall.content.service.ContentService;
import cn.e3mall.mapper.TbContentMapper;
import cn.e3mall.pojo.TbContent;
import cn.e3mall.pojo.TbContentExample;
import cn.e3mall.utils.E3mallResult;
import cn.e3mall.utils.PageBeanResult;

@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	private TbContentMapper tbContentMapper;
	
	/**
	 * 需求:根据分类id分页查询查询内容数据
	 * 参数:Long categoryId
	 * 返回值:PageBeanResult
	 */
	
	@Override
	public PageBeanResult findByCategoryId(Long categoryId, Integer page, Integer rows) {
		
		
		//创建查询数据条件对象,并设置分类ID
		TbContentExample example = new TbContentExample();
		example.createCriteria().andCategoryIdEqualTo(categoryId);
		//在查询之前使用pageHelper对象，设置分页
		PageHelper.startPage(page, rows);
		
		//获取分页数据
		List<TbContent> list = tbContentMapper.selectByExample(example);
		
		//创建PageInfo对象，封装数据,为了查询总记录数
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		
		//对结果进行包装
		PageBeanResult result = new PageBeanResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(list);
		
		
		return result;
	}
	
	/**
	 * 需求:保存内容数据
	 * 参数:TbContent content
	 * 返回值:E3mallResult
	 */
	public E3mallResult saveContent(TbContent content) {
		//广告更新时间
		Date date = new Date();
		content.setUpdated(date);
		content.setCreated(date);
		//保存内容数据
		tbContentMapper.insert(content);
		
		//返回值
		return E3mallResult.ok();
	}

}

package cn.e3mall.utils;

import java.io.Serializable;

public class ItemCatResult implements Serializable {

	/**
	 * text：表示节点的名字
	 * state：表示节点的状态，如果is_parent是1，表示有子节点，默认是close。
	 * 		如果是0，表示是没有子节点，默认是open
	 */
	private Long id;
	
	private String text;
	
	private String state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
}

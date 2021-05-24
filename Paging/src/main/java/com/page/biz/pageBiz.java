package com.page.biz;

import java.util.List;

import com.page.dto.pageDto;

public interface pageBiz {
	
	public int AllCount();
	public List<pageDto> selectList(pageDto dto);

}

package com.page.dao;

import java.util.List;

import com.page.dto.pageDto;

public interface pageDao {
	
	public int AllCount();
	public List<pageDto> selectList(pageDto dto);

}

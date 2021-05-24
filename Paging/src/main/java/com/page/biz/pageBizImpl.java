package com.page.biz;

import java.util.List;

import com.page.dao.pageDao;
import com.page.dao.pageDaoImpl;
import com.page.dto.pageDto;

public class pageBizImpl implements pageBiz {
	
	private pageDao dao = new pageDaoImpl();

	@Override
	public int AllCount() {
		return dao.AllCount();
	}

	@Override
	public List<pageDto> selectList(pageDto dto) {
		return dao.selectList(dto);
	}

}

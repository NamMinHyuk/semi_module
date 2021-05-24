package com.page.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.page.dto.pageDto;

public class pageDaoImpl extends SqlMapConfig implements pageDao {
	
	private String namespace = "page.mapper.";

	@Override
	public int AllCount() {
		int res = 0;
		
		try(SqlSession session = getSqlSessionFactory().openSession(false)) {
			res = session.selectOne(namespace+"AllCount");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return res;
	}

	@Override
	public List<pageDto> selectList(pageDto dto) {
		List<pageDto> list = new ArrayList<pageDto>();
		
		try(SqlSession session = getSqlSessionFactory().openSession(false)) {
			list = session.selectList(namespace+"selectList",dto);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

}

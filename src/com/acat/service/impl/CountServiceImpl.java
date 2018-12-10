package com.acat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acat.mapper.CountMapper;
import com.acat.pojo.Count;
import com.acat.service.CountService;

@Service
public class CountServiceImpl implements CountService{

	@Autowired
	private CountMapper cm;
	
	@Override
	public void updateCount(Count count) {
		cm.updateCount(count);
	}

	@Override
	public int findCountById(int id) {
		return cm.findCountById(id);
	}
}

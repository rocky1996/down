package com.acat.mapper;

import com.acat.pojo.Count;

public interface CountMapper extends SqlMapper{
	void updateCount(Count count);
	int findCountById(int id);
}

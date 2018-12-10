package com.acat.service;

import com.acat.pojo.Count;

public interface CountService {
	void updateCount(Count count);
	int findCountById(int id);
}

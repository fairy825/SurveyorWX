package com.surveyor.service;

import com.surveyor.pojo.Tip;

public interface TipService {
	Tip get(Integer id);

	int getTotal();

}

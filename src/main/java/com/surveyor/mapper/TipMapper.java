package com.surveyor.mapper;

import com.surveyor.pojo.Tip;

import com.surveyor.utils.MyMapper;

public interface TipMapper extends MyMapper<Tip> {
	public Integer getTotal();

}
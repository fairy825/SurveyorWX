package com.surveyor.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.surveyor.mapper.TipMapper;
import com.surveyor.pojo.Tip;
import com.surveyor.service.TipService;

@Service
public class TipServiceImpl implements TipService {

	@Autowired 
	private TipMapper tipMapper;
	

	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public Tip get(Integer id) {
		return tipMapper.selectByPrimaryKey(id);
	}
	
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public int getTotal() {
		return tipMapper.getTotal();
	}

}

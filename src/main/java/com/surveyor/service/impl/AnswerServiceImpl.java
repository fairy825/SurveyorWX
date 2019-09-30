package com.surveyor.service.impl;
import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.surveyor.mapper.AnswerMapper;
import com.surveyor.mapper.AnswerMapperCustom;
import com.surveyor.pojo.vo.AnswerVO;
import com.surveyor.service.AnswerService;
import com.surveyor.utils.PagedResult;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired 
	private AnswerMapper answerMapper;
	@Autowired 
	private AnswerMapperCustom answerMapperCustom;
	@Autowired
	private Sid sid;
	
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public PagedResult getMySurveys(String userId, Integer page, Integer pageSize) {
		System.out.println("userId:"+userId);
		PageHelper.startPage(page, pageSize);
		List<AnswerVO> list = answerMapperCustom.queryMyAnsweredSurvey(userId);
		System.out.println("list:"+list);
		System.out.println("list.length:"+list.size());

		PageInfo<AnswerVO> pageList = new PageInfo<>(list);
		
		PagedResult pagedResult = new PagedResult();
		pagedResult.setPage(page);
		pagedResult.setTotal(pageList.getPages());
		pagedResult.setRows(list);
		pagedResult.setRecords(pageList.getTotal());
		
		return pagedResult;
	}
	
	
}

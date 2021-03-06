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
import com.surveyor.pojo.Answer;
import com.surveyor.pojo.Survey;
import com.surveyor.pojo.vo.AnswerVO;
import com.surveyor.service.AnswerService;
import com.surveyor.utils.PagedResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

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
	
	@Transactional(propagation= Propagation.REQUIRED)
    @Override
    public String add(Answer a) {
		String id = sid.nextShort();
		a.setId(id);
		answerMapper.insertSelective(a);
		return id;
    }
	
	
	@Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(String id) {
		answerMapper.deleteByPrimaryKey(id);
    }
	
	@Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void update(Answer a) {
		answerMapper.updateByPrimaryKeySelective(a);
    }
	
	@Transactional(propagation= Propagation.SUPPORTS)
    @Override
    public Answer get(String id) {
        return answerMapper.selectByPrimaryKey(id);
    }
	
	@Transactional(propagation= Propagation.SUPPORTS)
    @Override
	public List<Answer> queryBySurveyAndUser(String surveyId, String userId){
		Example example = new Example(Answer.class);
		Criteria criteria = example.createCriteria();
		
		criteria.andEqualTo("userid", userId);
		criteria.andEqualTo("surveyid", surveyId);
		
		return answerMapper.selectByExample(example);
//		return answerMapper.selectBySurveyIdAndUserId(surveyId,userId);
	}
	
}

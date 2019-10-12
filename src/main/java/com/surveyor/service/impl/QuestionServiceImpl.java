package com.surveyor.service.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.surveyor.mapper.QuestionMapper;
import com.surveyor.mapper.SurveyMapper;
import com.surveyor.pojo.Question;
import com.surveyor.pojo.SearchRecords;
import com.surveyor.pojo.Survey;
import com.surveyor.pojo.vo.QuestionVO;
import com.surveyor.service.QuestionService;
import com.surveyor.utils.PagedResult;

import comparator.SurveyAllComparator;
import comparator.SurveyCountComparator;
import comparator.SurveyDateComparator;
import comparator.SurveyPriceComparator;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired 
	private SurveyMapper surveyMapper;
	@Autowired 
	private QuestionMapper questionMapper;
	
	@Autowired
	private Sid sid;
	
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public int getTotalBySurvey(String surveyId) {
		return questionMapper.getTotalBySurvey(surveyId);
	}
	
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public String add(Question question) {
		String id = sid.nextShort();
		question.setId(id);
		questionMapper.insert(question);
		return id;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public void delete(String id) {
		questionMapper.deleteByPrimaryKey(id);
	}
	
//	@Transactional(propagation= Propagation.SUPPORTS)
//	@Override
//	public List<Question> queryBySurvey(String surveyId) {
//		return questionMapper.queryBySurvey(surveyId);
//	}
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public PagedResult queryBySurvey(String surveyId, Integer page, Integer pageSize) {
		
		
		PageHelper.startPage(page, pageSize);
		List<Question> list = questionMapper.queryBySurvey(surveyId);
		List<QuestionVO> list2= new ArrayList<>();

		for(Question question:list) {
			QuestionVO questionVO = new QuestionVO();
			BeanUtils.copyProperties(question, questionVO);
			questionVO.setChoices(questionVO.getChoices());
			System.out.println(questionVO);
			System.out.println(questionVO.getChoices());
			list2.add(questionVO);
		}
		System.out.println(list2);

		PageInfo<QuestionVO> pageList = new PageInfo<>(list2);
		
		PagedResult pagedResult = new PagedResult();
		pagedResult.setPage(page);
		pagedResult.setTotal(pageList.getPages());
		pagedResult.setRows(list2);
		pagedResult.setRecords(pageList.getTotal());
		
		return pagedResult;
	}
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public Question get(String id) {
		return questionMapper.selectByPrimaryKey(id);
	}
	
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public void update(Question question) {
		questionMapper.updateByPrimaryKey(question);
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public String getQuestionIdBySurveyAndSequency(String surveyId,Integer sequence) {
		return questionMapper.getQuestionIdBySurveyAndSequency(surveyId,sequence);
	}


}

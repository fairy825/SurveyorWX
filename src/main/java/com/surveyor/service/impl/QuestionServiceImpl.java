package com.surveyor.service.impl;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.surveyor.mapper.DetectQuestionMapper;
import com.surveyor.mapper.QuestionMapper;
import com.surveyor.mapper.SurveyMapper;
import com.surveyor.pojo.DetectQuestion;
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
	private DetectQuestionMapper detectQuestionMapper;
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
		PageInfo<Question> pl = new PageInfo<>(list);

		List<QuestionVO> list2= new ArrayList<>();
		System.out.println("list:");
		System.out.println(list);
		System.out.println(list.size());

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
		pagedResult.setTotal(pl.getPages());
		pagedResult.setRows(list2);
		pagedResult.setRecords(pl.getTotal());
		
		return pagedResult;
	}
	
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public PagedResult queryBySurveyWithDetect(String surveyId, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize-1);
		List<Question> list = questionMapper.queryBySurvey(surveyId);
		PageInfo<Question> pl = new PageInfo<>(list);

		List<QuestionVO> list2= new ArrayList<>();
		Map<Integer,DetectQuestion> detectmap = new HashMap<>();//在第几题后放测谎题
		int detect_total = detectQuestionMapper.getTotal();
		int question_total = list.size();
		for(int i =0;i<=list.size()/20;i++) {
			Random r = new Random();
			Integer detect_sequence = r.nextInt(detect_total)+1;//detect表里的第id道测谎题
			int number = r.nextInt(question_total);//放到原问卷的第几题后
			System.out.println("detect_sequence:"+detect_sequence);
			System.out.println("number:"+number);
			DetectQuestion dQuestion = detectQuestionMapper.selectByPrimaryKey(String.valueOf(detect_sequence));
			detectmap.put(number,dQuestion);
		}
		Integer i=0;
	    for(Question question:list) {
	    	if(detectmap.containsKey(i)) { 
				QuestionVO dQuestionVO = new QuestionVO();
				BeanUtils.copyProperties(detectmap.get(i), dQuestionVO);
				dQuestionVO.setChoices(dQuestionVO.getChoices());
				dQuestionVO.setDetect(true);
				dQuestionVO.setType(QuestionService.one);
				dQuestionVO.setMust(true);
				System.out.println(dQuestionVO);
				System.out.println(dQuestionVO.getChoices());
				
				list2.add(dQuestionVO);
			}
			QuestionVO questionVO = new QuestionVO();
			BeanUtils.copyProperties(question, questionVO);
			questionVO.setChoices(questionVO.getChoices());
			questionVO.setDetect(false);
			System.out.println(questionVO);
			System.out.println(questionVO.getChoices());
			list2.add(questionVO);
			
			i++;
		}
		System.out.println(list2);

		PageInfo<QuestionVO> pageList = new PageInfo<>(list2);
		int len= (int) (pl.getTotal());//原总题数
		PagedResult pagedResult = new PagedResult();
		pagedResult.setPage(page);
		pagedResult.setTotal(len%(pageSize-1)==0?len/(pageSize-1):len/(pageSize-1)+1);//22 22/9=2+1   9 9/9=1
		pagedResult.setRows(list2);
		pagedResult.setRecords(len+pagedResult.getTotal());
		
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

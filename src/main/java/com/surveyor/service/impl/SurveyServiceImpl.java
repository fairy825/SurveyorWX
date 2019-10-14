package com.surveyor.service.impl;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.surveyor.mapper.SearchRecordsMapper;
import com.surveyor.mapper.SurveyMapper;
import com.surveyor.pojo.SearchRecords;
import com.surveyor.pojo.Survey;
import com.surveyor.service.SurveyService;
import com.surveyor.utils.PagedResult;

import comparator.SurveyAllComparator;
import comparator.SurveyCountComparator;
import comparator.SurveyDateComparator;
import comparator.SurveyPriceComparator;

@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired 
	private SurveyMapper surveyMapper;
	@Autowired 
	private SearchRecordsMapper searchRecordsMapper;
	@Autowired
	private Sid sid;
	
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public List<String> getHotWords() {
		return searchRecordsMapper.getHotWords();
	}
	
    @Transactional(propagation= Propagation.REQUIRED)
	@Override
	public void addPaper(String id) {
		surveyMapper.addPaper(id);
		Survey survey = surveyMapper.selectByPrimaryKey(id);

		if(survey.getHadpaper().equals(survey.getNeedpaper())) {
			survey.setStatus(3);//改为已完成的状态
			System.out.println(survey.getStatus());

			surveyMapper.updateByPrimaryKeySelective(survey);
		}
	}
    
	@Transactional(propagation= Propagation.SUPPORTS)
	@Override
	public PagedResult getAllSurveys(Survey survey, Integer sort,Integer isSaveRecord, Integer page, Integer pageSize) {
		
		String title = survey.getTitle();
		System.out.println("title:"+title);
		System.out.println("isSaveRecord:"+isSaveRecord);
		if(isSaveRecord == 1){
			String id = sid.nextShort();
			SearchRecords searchRecords = new SearchRecords();
			searchRecords.setContent(title);
			searchRecords.setId(id);
			searchRecordsMapper.insert(searchRecords);
		}
		PageHelper.startPage(page, pageSize);
		List<Survey> list = surveyMapper.queryAllSurvey(title);
		
		 if(null==sort) sort = 0;
		 
	            switch(sort){
	                case 0:
	                    Collections.sort(list,new SurveyAllComparator());
	                    break;
	                case 1 :
	                    Collections.sort(list,new SurveyCountComparator());
	                    break;
	                case 2 :
	                    Collections.sort(list,new SurveyDateComparator());
	                    break;
	                case 3:
	                    Collections.sort(list,new SurveyPriceComparator());
	                    break;
	 
	            }
	 
		
		PageInfo<Survey> pageList = new PageInfo<>(list);
		
		PagedResult pagedResult = new PagedResult();
		pagedResult.setPage(page);
		pagedResult.setTotal(pageList.getPages());
		pagedResult.setRows(list);
		pagedResult.setRecords(pageList.getTotal());
		
		return pagedResult;
	}
	
	@Transactional(propagation= Propagation.REQUIRED)
    @Override
    public String add(Survey survey) {
		String id = sid.nextShort();
		survey.setId(id);
		surveyMapper.insertSelective(survey);
		return id;
    }
	
	
	@Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(int id) {
		surveyMapper.deleteByPrimaryKey(id);
    }
	
	@Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void update(Survey s) {
		surveyMapper.updateByPrimaryKeySelective(s);
    }
	
	@Transactional(propagation= Propagation.SUPPORTS)
    @Override
    public Survey get(String id) {
        return surveyMapper.selectByPrimaryKey(id);
    }
 

//    @Override
//    public void updateTitle(String title, Integer id){
//        surveyDAO.updateTitle(title,id);
//    }
//
//    @Override
//    public void updateEnd(Date end,Integer id){
//        surveyDAO.updateEnd(end,id);
//    }
//
//    @Override
//    public void updateStatus(Integer status,Integer id){
//        surveyDAO.updateStatus(status,id);
//    }
//
//    @Override
//    public void updatePrice(Integer price,Integer id){
//        surveyDAO.updatePrice(price,id);
//    }
	@Transactional(propagation= Propagation.SUPPORTS)
    @Override
    public     PagedResult getSurveyByUser(String userId, Integer page,Integer pageSize){
		PageHelper.startPage(page, pageSize);
		List<Survey> list = surveyMapper.queryByUser(userId);
		
		PageInfo<Survey> pageList = new PageInfo<>(list);
		
		PagedResult pagedResult = new PagedResult();
		pagedResult.setPage(page);
		pagedResult.setTotal(pageList.getPages());
		pagedResult.setRows(list);
		pagedResult.setRecords(pageList.getTotal());
		
		return pagedResult;
    }
	@Transactional(propagation= Propagation.SUPPORTS)
    @Override
    public     PagedResult getSurveyByUserAndStatus(String userId,Integer status, Integer page,Integer pageSize){
		PageHelper.startPage(page, pageSize);
		List<Survey> list = surveyMapper.queryByUserAndStatus(userId,status);
		
		PageInfo<Survey> pageList = new PageInfo<>(list);
		
		PagedResult pagedResult = new PagedResult();
		pagedResult.setPage(page);
		pagedResult.setTotal(pageList.getPages());
		pagedResult.setRows(list);
		pagedResult.setRecords(pageList.getTotal());
		
		return pagedResult;
    }
//
//    @Override
//    public void copySurvey(Integer num, Integer status, String title, Integer like){
//        surveyDAO.copySurvey(num,status,title,like);
//    }
//
//    @Override
//    public void copy(Integer num, Integer like){
//        surveyDAO.copy(num, like);
//    }
//
//    @Override
//    public void newSurvey(Integer need, Integer status, String title){
//        surveyDAO.newSurvey(need, status, title);
//    }
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public void suspend(String id) {
		surveyMapper.stopSurvey(id);
	}
	

}

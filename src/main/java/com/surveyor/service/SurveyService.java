package com.surveyor.service;

import java.util.List;

import com.surveyor.pojo.Survey;
import com.surveyor.utils.PagedResult;

public interface SurveyService {
//	public String saveSurvey(Survey video);
//	public void updateVideo(String videoId, String coverPath);
    public PagedResult getAllSurveys(Survey survey ,Integer sort,Integer isSaveRecord,Integer page,Integer pageSize);
    public List<String> getHotWords();
    String add(Survey s);
    void delete(int id);
    void update(Survey s);
    void publish(String id);
    Survey get(String id);
    void suspend(String id);
    void addPaper(String id);
    public void updateS(Survey s);
//    boolean end(String id);
    public void updateStatus();

//    void updateTitle(String title,Integer id);
//    void updateEnd(Date end,Integer id);
//    void updateStatus(Integer status,Integer id);
//    void updatePrice(Integer price,Integer id);
    PagedResult getSurveyByUser(String userId, Integer page,Integer pageSize);
    PagedResult getSurveyByUserAndStatus(String userId,Integer status, Integer page,Integer pageSize);
//    void copySurvey(Integer num,Integer status,String title,Integer like);
//    void copy(Integer num, Integer like);
//    void newSurvey(Integer need, Integer status, String title );
}

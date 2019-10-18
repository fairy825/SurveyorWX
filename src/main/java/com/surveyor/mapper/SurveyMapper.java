package com.surveyor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.surveyor.pojo.Survey;
import com.surveyor.utils.MyMapper;

public interface SurveyMapper extends MyMapper<Survey> {
	public List<Survey> queryAllSurvey(@Param("title")String title);
	public List<Survey> queryByUserAndStatus(@Param("userId")String userId,@Param("status")Integer status);
	public List<Survey> queryByUser(@Param("userId")String userId);
	public void stopSurvey(@Param("id")String id);
	public void addPaper(@Param("id")String id);
	public void publish(@Param("id")String id);
	
}
package com.surveyor.mapper;

import com.surveyor.pojo.DetectQuestion;
import com.surveyor.utils.MyMapper;

public interface DetectQuestionMapper extends MyMapper<DetectQuestion> {
	public Integer getTotal();

}
package com.surveyor.controller;

import com.surveyor.mapper.AnswerMapper;
import com.surveyor.mapper.QuestionMapper;
import com.surveyor.pojo.Answer;
import com.surveyor.pojo.Question;
import com.surveyor.pojo.Survey;
import com.surveyor.pojo.vo.QuestionVO;
import com.surveyor.service.QuestionService;
import com.surveyor.service.StatisticsService;
import com.surveyor.service.SurveyService;
import com.surveyor.utils.IMoocJSONResult;
import com.surveyor.utils.PagedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(value="统计", tags= {"统计controller"})
@RequestMapping("/statistics")
public class StatisticsController extends BasicController{

	@Autowired
	StatisticsService statisticsService;

	@GetMapping
	public IMoocJSONResult get(@RequestParam("surveyId")String surveyId){

		return IMoocJSONResult.ok(statisticsService.getResult(surveyId));
	}


}

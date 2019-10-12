package com.surveyor.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surveyor.pojo.Answer;
import com.surveyor.pojo.Question;
import com.surveyor.service.AnswerService;
import com.surveyor.service.QuestionService;
import com.surveyor.utils.IMoocJSONResult;
import com.surveyor.utils.PagedResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="答案相关业务的接口", tags= {"答案相关业务的controller"})
@RequestMapping("/answer")
public class AnswerController extends BasicController{
	
	@Autowired
	AnswerService answerService;

	@Autowired
	QuestionService questionService;

	//通过发布问卷id查找问题
	@ApiOperation(value="查找我作答的问卷", notes="查找我作答的问卷的接口")
	@PostMapping("/queryMySurvey")
    public IMoocJSONResult queryMySurvey(@RequestParam("userId")String userId,Integer page){
		if(page == null){
			page = 1;
		}
		PagedResult pagedResult  = answerService.getMySurveys(userId, page, PAGE_SIZE);
		return IMoocJSONResult.ok(pagedResult);
    }
	
	@ApiOperation(value="提交问卷", notes="提交问卷的接口")
    @PostMapping("/upload")
    public IMoocJSONResult upload(@RequestParam("userId") String userId,String surveyId,@RequestBody Map<String,Object> answ){
		System.out.println("answ:"+answ);
		
		Map<String,Object> answ1=(Map<String, Object>) answ.get("answ");
		String answerId = null;
			for(String questionId :answ1.keySet()) {
				String an =  answ1.get(questionId).toString();
				System.out.println(an);
				Answer answer=new Answer();
				answer.setSurveyid(surveyId);
				answer.setUserid(userId);
				answer.setContent(an);
				answer.setSequence(0);
	//			String questionId=questionService.getQuestionIdBySurveyAndSequency(surveyId, i);
				answer.setQuestionid(questionId);
				answerService.add(answer);
			}
		
		return IMoocJSONResult.ok();
    }
	
	@ApiOperation(value="查找某个回答者的答案", notes="查找某个回答者的答案的接口")
	@PostMapping("/queryByUser")
    public IMoocJSONResult queryByUser(@RequestParam("surveyId")String surveyId,String userId){
		List<Answer> answers = answerService.queryBySurveyAndUser(surveyId,userId);
		return IMoocJSONResult.ok(answers);
    }
	
}

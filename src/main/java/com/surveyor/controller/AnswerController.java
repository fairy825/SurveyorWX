package com.surveyor.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surveyor.service.AnswerService;
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
	
}

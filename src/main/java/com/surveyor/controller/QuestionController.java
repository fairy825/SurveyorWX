package com.surveyor.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surveyor.pojo.Question;
import com.surveyor.pojo.Survey;
import com.surveyor.service.QuestionService;
import com.surveyor.service.SurveyService;
import com.surveyor.utils.IMoocJSONResult;
import com.surveyor.utils.PagedResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="问题相关业务的接口", tags= {"问题相关业务的controller"})
@RequestMapping("/question")
public class QuestionController extends BasicController{
	
	@Autowired
	QuestionService questionService;

	@Autowired
	SurveyService surveyService;
//	
//	@PostMapping(value="/showAll")
//	public IMoocJSONResult showAll(@RequestBody Survey survey ,Integer sort,Integer isSaveRecord, Integer page) throws Exception {
//		if(page == null){
//			page = 1;
//		}
//		PagedResult pagedResult = surveyService.getAllSurveys(survey,sort,isSaveRecord,page, PAGE_SIZE);
//		return IMoocJSONResult.ok(pagedResult);
//	}
//	
//	
	//删除问题
	@ApiOperation(value="删除问题", notes="删除问题的接口")
    @PostMapping("/delete")
    public IMoocJSONResult delete(@RequestParam("id")String id){
    	questionService.delete(id);
		return IMoocJSONResult.ok();
    }
//
//	//通过发布用户id确定他发布的问卷
////	@ApiOperation(value="查当前用户发出的问卷", notes="查当前用户发出的问卷的接口")
//	@PostMapping("/getSurveyByUserAndStatus")
//    public IMoocJSONResult getSurveyByUserAndStatus(@RequestParam("userId")String userId,@RequestParam("status")Integer status,Integer page){
//		if(page == null){
//			page = 1;
//		}	
//		PagedResult pagedResult = surveyService.getSurveyByUserAndStatus(userId ,status,page, PAGE_SIZE);
//		return IMoocJSONResult.ok(pagedResult);
//    }
//	@ApiOperation(value="查当前用户发出的问卷", notes="查当前用户发出的问卷的接口")
//	@PostMapping("/getSurveyByUser")
//	 public IMoocJSONResult getSurveyByUser(@RequestParam("userId")String userId,Integer page){
//			if(page == null){
//				page = 1;
//			}
//			PagedResult pagedResult = surveyService.getSurveyByUser(userId ,page, PAGE_SIZE);
//			return IMoocJSONResult.ok(pagedResult);
//	    }

	@ApiOperation(value="创建问题", notes="创建问题的接口")
    @PostMapping("/add")
    public IMoocJSONResult add(@RequestBody Question question,String surveyId){
		question.setSurveyid(surveyId);
		int sequence = questionService.getTotalBySurvey(surveyId)+1;
		question.setSequence(sequence);
        String questionId = questionService.add(question);
		return IMoocJSONResult.ok(questionId);

    }
	
	//更新问题
	@ApiOperation(value="更新问题", notes="更新问题的接口")
    @PostMapping("/update")
    public IMoocJSONResult update(@RequestBody Question question){
		Question q = questionService.get(question.getId());
		question.setSequence(q.getSequence());
		question.setSurveyid(q.getSurveyid());
		questionService.update(question);
		return IMoocJSONResult.ok(question);

    }
	//通过发布问卷id查找问题
	@ApiOperation(value="查找某一张问卷下的问题", notes="查找某问卷下的所有问题的接口")
	@PostMapping("/queryAll")
    public IMoocJSONResult queryAll(@RequestParam("surveyId")String surveyId, Integer page){
		if(page == null){
			page = 1;
		}
		PagedResult pagedResult = questionService.queryBySurvey(surveyId,page, PAGE_SIZE);
//		List<Question> questions = questionService.queryBySurvey(surveyId);
		return IMoocJSONResult.ok(pagedResult);
    }

	
	@ApiOperation(value="查找问题", notes="查找问题的接口")
	@PostMapping("/queryOne")
    public IMoocJSONResult queryOne(@RequestParam("questionId")String questionId){
		return IMoocJSONResult.ok(questionService.get(questionId));
    }
	
}

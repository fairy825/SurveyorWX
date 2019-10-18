package com.surveyor.controller;

import java.util.List;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.surveyor.pojo.Answer;
import com.surveyor.pojo.DetectQuestion;
import com.surveyor.pojo.Question;
import com.surveyor.pojo.Survey;
import com.surveyor.service.AnswerService;
import com.surveyor.service.QuestionService;
import com.surveyor.service.SurveyService;
import com.surveyor.service.UserService;
import com.surveyor.service.DetectQuestionService;
import com.surveyor.utils.IMoocJSONResult;
import com.surveyor.utils.PagedResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "答案相关业务的接口", tags = { "答案相关业务的controller" })
@RequestMapping("/answer")
public class AnswerController extends BasicController {

	@Autowired
	AnswerService answerService;

	@Autowired
	QuestionService questionService;

	@Autowired
	SurveyService surveyService;

	@Autowired
	DetectQuestionService detectQuestionService;
	
	@Autowired
	UserService userService;

	// 通过发布问卷id查找问题
	@ApiOperation(value = "查找我作答的问卷", notes = "查找我作答的问卷的接口")
	@PostMapping("/queryMySurvey")
	public IMoocJSONResult queryMySurvey(@RequestParam("userId") String userId, Integer page) {
		if (page == null) {
			page = 1;
		}
		PagedResult pagedResult = answerService.getMySurveys(userId, page, PAGE_SIZE);
		return IMoocJSONResult.ok(pagedResult);
	}

	@ApiOperation(value = "提交问卷", notes = "提交问卷的接口")
	@PostMapping("/upload")
	public IMoocJSONResult upload(@RequestParam("userId") String userId, String surveyId,
			@RequestBody Map<String, Object> answ) {
		List<Answer> answers = answerService.queryBySurveyAndUser(surveyId, userId);
		if (answers.size() != 0)
			return IMoocJSONResult.errorMsg("您已答过该问卷");
		long end = System.currentTimeMillis();
		String start = redis.get(START_TIME + ":" + userId);
		long ansTime = end - Long.valueOf(start);
		Survey survey = surveyService.get(surveyId);
		int minTime = 0;
		if(survey.getMintime()!=null) minTime=survey.getMintime();
		if (ansTime <= 1000 * 60 * minTime)
			return IMoocJSONResult.errorMsg("提交过快，请认真填写！");

		System.out.println("answ:" + answ);

		Map<String, Object> answ1 = (Map<String, Object>) answ.get("answ");
		String answerId = null;
		for (String questionId : answ1.keySet()) {
			Question q = questionService.get(questionId);
			if (q == null) {// 测谎题
				DetectQuestion dQuestion = detectQuestionService.get(questionId);
				String an = answ1.get(questionId).toString();
				System.out.println(an);
				// 答错
				if (!an.equalsIgnoreCase(dQuestion.getAnswer()))
					return IMoocJSONResult.errorMsg("测谎题错误，提交失败！");
			}
		}
		for (String questionId : answ1.keySet()) {
			Question q = questionService.get(questionId);
			if (q != null) {// 普通题
				String an = answ1.get(questionId).toString();
				System.out.println(an);
				Answer answer = new Answer();
				answer.setSurveyid(surveyId);
				answer.setUserid(userId);
				answer.setContent(an);
				answer.setSequence(0);
				answer.setQuestionid(questionId);
				answerService.add(answer);
			}
		}
		userService.addPoint(userId, survey.getPrice()/survey.getNeedpaper());
		surveyService.addPaper(surveyId);
		return IMoocJSONResult.ok();

	}

	@ApiOperation(value = "查找某个回答者的答案", notes = "查找某个回答者的答案的接口")
	@PostMapping("/queryByUser")
	public IMoocJSONResult queryByUser(@RequestParam("surveyId") String surveyId, String userId) {
		List<Answer> answers = answerService.queryBySurveyAndUser(surveyId, userId);
		return IMoocJSONResult.ok(answers);
	}

}

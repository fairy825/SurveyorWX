package com.surveyor.controller;

import java.io.File;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.surveyor.pojo.Survey;
import com.surveyor.pojo.Tip;
import com.surveyor.pojo.Users;
import com.surveyor.pojo.vo.UsersVO;
import com.surveyor.service.SurveyService;
import com.surveyor.service.TipService;
import com.surveyor.utils.IMoocJSONResult;
import com.surveyor.utils.PagedResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "问卷相关业务的接口", tags = { "问卷相关业务的controller" })
@RequestMapping("/survey")
public class SurveyController extends BasicController {

	@Autowired
	SurveyService surveyService;
	
	@Autowired
	TipService tipService;
	
	@PostMapping(value = "/showAll")
	public IMoocJSONResult showAll(@RequestBody Survey survey, Integer sort, Integer isSaveRecord, Integer page)
			throws Exception {
		if (page == null) {
			page = 1;
		}
		PagedResult pagedResult = surveyService.getAllSurveys(survey, sort, isSaveRecord, page, PAGE_SIZE);
		return IMoocJSONResult.ok(pagedResult);
	}

	@PostMapping(value = "/hot")
	public IMoocJSONResult hot() {
		return IMoocJSONResult.ok(surveyService.getHotWords());
	}

	// 删除问卷
	@ApiOperation(value = "删除问卷", notes = "删除问卷的接口")
	@PostMapping("/delete")
	public IMoocJSONResult deleteSurvey(@RequestParam("id") String id) {
		Survey survey = new Survey();
		survey.setStatus(0);
		survey.setId(id);
		;
		surveyService.updateS(survey);
		return IMoocJSONResult.ok(survey);
	}

	// 通过发布用户id确定他发布的问卷
//	@ApiOperation(value="查当前用户发出的问卷", notes="查当前用户发出的问卷的接口")
	@PostMapping("/getSurveyByUserAndStatus")
	public IMoocJSONResult getSurveyByUserAndStatus(@RequestParam("userId") String userId,
			@RequestParam("status") Integer status, Integer page) {
		if (page == null) {
			page = 1;
		}
		PagedResult pagedResult = surveyService.getSurveyByUserAndStatus(userId, status, page, PAGE_SIZE);
		return IMoocJSONResult.ok(pagedResult);
	}

	@ApiOperation(value = "查当前用户发出的问卷", notes = "查当前用户发出的问卷的接口")
	@PostMapping("/getSurveyByUser")
	public IMoocJSONResult getSurveyByUser(@RequestParam("userId") String userId, Integer page) {
		if (page == null) {
			page = 1;
		}
		PagedResult pagedResult = surveyService.getSurveyByUser(userId, page, PAGE_SIZE);
		return IMoocJSONResult.ok(pagedResult);
	}

	@ApiOperation(value = "创建问卷", notes = "创建问卷的接口")
	@PostMapping("/add")
	public IMoocJSONResult add(@RequestBody Survey survey, String userId) {
		System.out.println(survey.getPrice());
		survey.setHadpaper(0);
		survey.setNum(0);
		survey.setPublishTime(new Date());
		survey.setStatus(2);
		survey.setUserid(userId);
		String surveyId = surveyService.add(survey);
		return IMoocJSONResult.ok(surveyId);

	}
	@PostMapping("/publish")
	public IMoocJSONResult publish(@RequestBody Survey survey) {
		Survey s = surveyService.get(survey.getId());
		surveyService.publish(s.getId());
		return IMoocJSONResult.ok();

	}
	@PostMapping("/suspend")
	public IMoocJSONResult suspend(@RequestBody Survey survey) {
		Survey s = surveyService.get(survey.getId());
		surveyService.suspend(s.getId());
		return IMoocJSONResult.ok();

	}
	// 更新问卷
	@ApiOperation(value = "更新问卷", notes = "更新问卷的接口")
	@PostMapping("/update")
	public IMoocJSONResult update(@RequestBody Survey survey) {
		Survey s = surveyService.get(survey.getId());
//		survey.setTitle(s.getTitle());
//		survey.setEndTime(s.getEndTime());
//		survey.setAnony(s.getAnony());
//		survey.setMintime(s.getMintime());
//		survey.setTestlie(s.getTestlie());
		survey.setHadpaper(s.getHadpaper());
//		survey.setDescription(s.getDescription());
//		survey.setPrice(s.getPrice());
//		survey.setNeedpaper(s.getNeedpaper());
		survey.setNum(s.getNum());
		survey.setPublishTime(s.getPublishTime());
		survey.setStatus(s.getStatus());
		survey.setUserid(s.getUserid());
		surveyService.updateS(survey);
		return IMoocJSONResult.ok(survey);
	}
	@PostMapping("/stopAndStart")
	public IMoocJSONResult stopAndStart(@RequestBody Survey survey) {
		surveyService.updateS(survey);
		return IMoocJSONResult.ok(survey);
	}
	
	// 通过发布问卷id查找问卷
	@ApiOperation(value = "查找问卷", notes = "查找问卷的接口")
	@PostMapping("/queryOne")
	public IMoocJSONResult queryOne(@RequestParam("surveyId") String surveyId) {
		Survey survey = surveyService.get(surveyId);
		return IMoocJSONResult.ok(survey);
	}

	@ApiOperation(value = "随机获得一个tip", notes = "随机获得一个tip的接口")
	@GetMapping("/getTip")
	public IMoocJSONResult getTip() {
		Random random = new Random();
		int id = random.nextInt(tipService.getTotal())+1;
		Tip tip = tipService.get(id);
		return IMoocJSONResult.ok(tip);
	}
//    @RequestMapping("/add")
//    public void add(@RequestParam("need")Integer need, @RequestParam("status")Integer status,
//                          @RequestParam("title") String title){
//        surveyService.newSurvey(need, status, title);
//    }

}

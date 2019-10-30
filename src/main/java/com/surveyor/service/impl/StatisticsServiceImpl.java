package com.surveyor.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.surveyor.mapper.AnswerMapper;
import com.surveyor.mapper.AnswerMapperCustom;
import com.surveyor.mapper.QuestionMapper;
import com.surveyor.pojo.Answer;
import com.surveyor.pojo.Question;
import com.surveyor.pojo.vo.AnswerVO;
import com.surveyor.pojo.vo.QuestionStatistics;
import com.surveyor.pojo.vo.QuestionVO;
import com.surveyor.pojo.vo.StatisticsVO;
import com.surveyor.service.AnswerService;
import com.surveyor.service.QuestionService;
import com.surveyor.service.StatisticsService;
import com.surveyor.utils.PagedResult;
import com.surveyor.utils.SpssUtils;
import javafx.beans.binding.ObjectExpression;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsServiceImpl implements StatisticsService {
	@Autowired
	QuestionMapper questionMapper;
	@Autowired
	AnswerMapper answerMapper;

	@Override
	public StatisticsVO getResult(String surveyId) {
		StatisticsVO statisticsVO = new StatisticsVO();
		List<Question> questionList = questionMapper.queryBySurvey(surveyId);
		List<QuestionStatistics> list = new ArrayList<>();
		for (Question question : questionList) {
			QuestionStatistics questionStatistics = new QuestionStatistics();
			String type = question.getType();
			questionStatistics.setQuestion(question);
			List<Answer> answerList = answerMapper.selectByQuestionId(question.getId(), type);

			if (type.equals(QuestionService.one) || type.equals(QuestionService.scale)) {//单选或量表
				List<Map<String, Object>> result = answerMapper.getOne(question.getId());
				int max ;
				if(type.equals(QuestionService.one)){
					max = getMax(question);
				}else {
					max=question.getUpscale();
				}
				for (int i = 0; i <= max; i++) {
					   if(i>=result.size()){
					      Map<String, Object> map1 = new HashMap();
					      map1.put("name", i+"");
					      map1.put("value", 0);
					      result.add(i,map1);
					   }else {
					      Map map = result.get(i);
					      if (!map.get("name").equals(i + "")) {
					         Map<String, Object> map1 = new HashMap();
					         map1.put("name", i + "");
					         map1.put("value", 0);
					         result.add(i, map1);
					      }
					   }
					}
				int total=0;
				for (Map map:result){
					String str = map.get("name")+"";
					if(type.equals(QuestionService.one)) {//单选才展示内容和转换为字母
						map.put("content", findContent(question, Integer.valueOf(str)));
						map.put("name", stringToAlpha((map.get("name") + "")));
					}
					total+=Integer.valueOf(map.get("value").toString());
				}
				double avg=0;
				for (Map map:result){
					int value=Integer.valueOf(map.get("value").toString());
					Double b=new BigDecimal((double)value/(double)total*100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					if(type.equals(QuestionService.scale)){
						int name=Integer.valueOf(map.get("name").toString());
						avg+=new BigDecimal((double) name*(double) value/(double) total).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					}
					map.put("percent",b);
				}
				questionStatistics.setChoiceList(result);
				questionStatistics.setTotal(total);
				questionStatistics.setAvg(avg);

				if(type.equals(QuestionService.scale)){//项目鉴别度
					List<Double> scaleAnswers=answerList.stream().map(answer -> Double.valueOf(answer.getContent())).collect(Collectors.toList());
					double district=SpssUtils.getDiscrinllnatloil(scaleAnswers);
//					double district=0.1;

					questionStatistics.setDistrict(district);
					String hint;
					if(district>0.8)hint="极强相关";
					else if(district>0.6)hint="强相关";
					else if(district>0.4)hint="中等程度相关";
					else if (district>0.2)hint="弱相关";
					else hint="极弱相关";
					questionStatistics.setHint(hint);

				}

			} else if (type.equals(QuestionService.many)) {//多选

//				List<Answer> answerList = answerMapper.selectByQuestionId(question.getId(), type);
				List<Map<String, Object>> choiceCount = new ArrayList<>();
				int max = getMax(question);
				int total=0;
				for (int i = 0; i <= max; i++) {
					Map map = new HashMap();
					map.put("name", stringToAlpha(String.valueOf(i)));
					map.put("value", 0);
					map.put("content", findContent(question, i));
					choiceCount.add(map);
				}
				int totalCount=answerList.size();

				for (Answer answer : answerList) {
					String content = answer.getContent();
					String subString = content.substring(1, content.length() - 1);
					List<String> answerPerUser = Arrays.asList(subString.split(",")).stream().map(s -> stringToAlpha(s.trim())).collect(Collectors.toList());
					for (String singleAnswer : answerPerUser) {
//						boolean flag=false;
						for (Map map : choiceCount) {
							if (map.get("name").equals(singleAnswer)) {
								map.put("value", Integer.valueOf(map.get("value")+"")+1);//可能有错
								total+=1;
//								flag=true;
								break;
							}
						}
//						if(!flag){
//							Map<String,Object> map=new HashMap();
//						    map.put("name",singleAnswer);
//						    map.put("value",1);
//						    choiceCount.add(map);
//						}
					}

				}
				for (Map map:choiceCount){
					int value=Integer.valueOf(map.get("value").toString());
					Double b=new BigDecimal((double)value/(double)total*100).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
					map.put("percent",b);
				}
				questionStatistics.setChoiceList(choiceCount);
				questionStatistics.setTotal(totalCount);

			} else {//填空
//				List<Answer> answerList = answerMapper.selectByQuestionId(question.getId(), type);
				questionStatistics.setAnswerList(answerList);
			}
			list.add(questionStatistics);
		}
		statisticsVO.setStatisticsList(list);
		return statisticsVO;
	}

	private int getMax(Question question) {
		if (!StringUtils.isEmpty(question.getChoicea())) {
			if (!StringUtils.isEmpty(question.getChoiceb())) {
				if (!StringUtils.isEmpty(question.getChoicec())) {
					if (!StringUtils.isEmpty(question.getChoiced())) {
						if (!StringUtils.isEmpty(question.getChoicee())) {
							return 4;
						} else return 3;
					} else return 2;
				} else return 1;
			} else return 0;
		}
		return -1;
	}

	private String findContent(Question question, int i) {
		switch (i) {
			case 0:
				return question.getChoicea();
			case 1:
				return question.getChoiceb();
			case 2:
				return question.getChoicec();
			case 3:
				return question.getChoiced();
			case 4:
				return question.getChoicee();
		}
		return "";
	}

	private String stringToAlpha(String s) {
		switch (s) {
			case "0":
				return "A";
			case "1":
				return "B";
			case "2":
				return "C";
			case "3":
				return "D";
			case "4":
				return "E";
		}
		return "error";
	}

	public static void main(String[] args) {
		Long a=1l;
		System.out.println(a.toString());
	}
}

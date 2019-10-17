package com.surveyor.pojo.vo;

import com.surveyor.pojo.Answer;
import com.surveyor.pojo.Question;

import java.util.List;
import java.util.Map;

public class QuestionStatistics {
	Question question;
	List<Map<String, Object>> choiceList;
	List<Answer> answerList;
	Integer total;
	boolean status=false;
	double avg=0;
	double district;
	String hint;

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public double getDistrict() {
		return district;
	}

	public void setDistrict(double district) {
		this.district = district;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}

	public List<Map<String, Object>> getChoiceList() {
		return choiceList;
	}

	public void setChoiceList(List<Map<String, Object>> choiceList) {
		this.choiceList = choiceList;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}



package com.surveyor.pojo.vo;

import com.surveyor.pojo.Answer;

import java.util.List;

public class StatisticsVO {
	List<QuestionStatistics> statisticsList;

	public List<QuestionStatistics> getStatisticsList() {
		return statisticsList;
	}

	public void setStatisticsList(List<QuestionStatistics> statisticsList) {
		this.statisticsList = statisticsList;
	}
}

package com.surveyor.pojo.vo;

import javax.persistence.*;

public class AnswerVO {
    private String id;
    private String surveyid;
    private String questionid;
    private Integer sequence;
    private String content;
    private String userid;

	private String title;
    private Float price;
	private Integer needpaper;

    public Integer getNeedpaper() {
		return needpaper;
	}

	public void setNeedpaper(Integer needpaper) {
		this.needpaper = needpaper;
	}

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取属于哪一个问卷的id
     *
     * @return surveyId - 属于哪一个问卷的id
     */
    public String getSurveyid() {
        return surveyid;
    }

    /**
     * 设置属于哪一个问卷的id
     *
     * @param surveyid 属于哪一个问卷的id
     */
    public void setSurveyid(String surveyid) {
        this.surveyid = surveyid;
    }

    /**
     * 获取该答案回答的是这张问卷哪一道问题的
     *
     * @return questionId - 该答案回答的是这张问卷哪一道问题的
     */
    public String getQuestionid() {
        return questionid;
    }

    /**
     * 设置该答案回答的是这张问卷哪一道问题的
     *
     * @param questionid 该答案回答的是这张问卷哪一道问题的
     */
    public void setQuestionid(String questionid) {
        this.questionid = questionid;
    }

    /**
     * 获取答案序号
     *
     * @return sequence - 答案序号
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置答案序号
     *
     * @param sequence 答案序号
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取作答内容
     *
     * @return content - 作答内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置作答内容
     *
     * @param content 作答内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取作答者是谁
     *
     * @return userId - 作答者是谁
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置作答者是谁
     *
     * @param userid 作答者是谁
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
}
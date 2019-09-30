package com.surveyor.pojo;

import javax.persistence.*;

public class Answer {
    @Id
    private String id;

    /**
     * 属于哪一个问卷的id
     */
    @Column(name = "surveyId")
    private String surveyid;

    /**
     * 该答案回答的是这张问卷哪一道问题的
     */
    @Column(name = "questionId")
    private String questionid;

    /**
     * 答案序号
     */
    private Integer sequence;

    /**
     * 作答内容
     */
    private String content;

    /**
     * 作答者是谁
     */
    @Column(name = "userId")
    private Integer userid;

    /**
     * @return id
     */
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
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置作答者是谁
     *
     * @param userid 作答者是谁
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }
}
package com.surveyor.pojo;

import javax.persistence.*;

@Table(name = "detect_question")
public class DetectQuestion {
    @Id
    private String id;

    /**
     * 题目内容
     */
    private String content;

    private String choicea;

    private String choiceb;

    private String choicec;

    private String choiced;

    private String choicee;

	private String answer;
    /**
     * @return Id
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
     * 获取题目内容
     *
     * @return content - 题目内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置题目内容
     *
     * @param content 题目内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return choicea
     */
    public String getChoicea() {
        return choicea;
    }

    /**
     * @param choicea
     */
    public void setChoicea(String choicea) {
        this.choicea = choicea;
    }

    /**
     * @return choiceb
     */
    public String getChoiceb() {
        return choiceb;
    }

    /**
     * @param choiceb
     */
    public void setChoiceb(String choiceb) {
        this.choiceb = choiceb;
    }

    /**
     * @return choicec
     */
    public String getChoicec() {
        return choicec;
    }

    /**
     * @param choicec
     */
    public void setChoicec(String choicec) {
        this.choicec = choicec;
    }

    /**
     * @return choiced
     */
    public String getChoiced() {
        return choiced;
    }

    /**
     * @param choiced
     */
    public void setChoiced(String choiced) {
        this.choiced = choiced;
    }

    /**
     * @return choicee
     */
    public String getChoicee() {
        return choicee;
    }

    /**
     * @param choicee
     */
    public void setChoicee(String choicee) {
        this.choicee = choicee;
    }
    public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
}
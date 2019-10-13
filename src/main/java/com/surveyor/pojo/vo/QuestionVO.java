package com.surveyor.pojo.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.surveyor.service.QuestionService;

public class QuestionVO {
    @Id
    private String id;

    /**
     * 属于哪一个问卷的id
     */
    @Column(name = "surveyId")
    private String surveyid;

    /**
     * 题目序号
     */
    private Integer sequence;

    /**
     * 题目类型
     */
    private String type;

    /**
     * 题目内容
     */
    private String content;

    /**
     * 是否为必答题 
     */
    private Boolean must;


    /**
     * 选项a内容
     */
    private String choicea;

    /**
     * 选项b内容
     */
    private String choiceb;

    /**
     * 选项c内容
     */
    private String choicec;

    /**
     * 选项d内容
     */
    private String choiced;

    /**
     * 选项e内容
     */
    private String choicee;

    /**
     * 多选题的选择上限
     */
    private Integer uplimit;

	/**
     * 多选题的选择下限
     */
    private Integer lowlimit;
    /**
     * 量表题的打分上限
     */
    private Integer upscale;

	/**
     * 量表题的打分下限
     */
    private Integer lowscale;
//    @Transient
	private List choices;
//  @Transient
	private boolean detect;
	@Transient
    private String typeDesc;
    
    public String getTypeDesc(){
        String typeDesc ="未知";
        switch(type){
            case QuestionService.one:
            	typeDesc="单选";
                break;
            case QuestionService.many:
            	typeDesc="多选";
                break;
            case QuestionService.fill:
            	typeDesc="填空";
                break;
            case QuestionService.scale:
            	typeDesc="量表";
                break;
            default:
            	typeDesc="未知";
        }
        return typeDesc;
    }
    public void setChoices(List choices) {
		this.choices = choices;
	}

    public List getChoices() {
		List<String> list = new ArrayList<>();
		if(choicea!=null) list.add(choicea);
		if(choiceb!=null) list.add(choiceb);
		if(choicec!=null) list.add(choicec);
		if(choiced!=null) list.add(choiced);
		if(choicee!=null) list.add(choicee);

		return list;
	}


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
     * 获取题目序号
     *
     * @return sequence - 题目序号
     */
    public Integer getSequence() {
        return sequence;
    }

    /**
     * 设置题目序号
     *
     * @param sequence 题目序号
     */
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    /**
     * 获取题目类型
     *
     * @return type - 题目类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置题目类型
     *
     * @param type 题目类型
     */
    public void setType(String type) {
        this.type = type;
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
     * 获取是否为必答题 
     *
     * @return must - 是否为必答题 
     */
    public Boolean getMust() {
        return must;
    }

    /**
     * 设置是否为必答题 
     *
     * @param must 是否为必答题 
     */
    public void setMust(Boolean must) {
        this.must = must;
    }

    /**
     * 获取选项a内容
     *
     * @return choicea - 选项a内容
     */
    public String getChoicea() {
        return choicea;
    }

    /**
     * 设置选项a内容
     *
     * @param choicea 选项a内容
     */
    public void setChoicea(String choicea) {
        this.choicea = choicea;
    }

    /**
     * 获取选项b内容
     *
     * @return choiceb - 选项b内容
     */
    public String getChoiceb() {
        return choiceb;
    }

    /**
     * 设置选项b内容
     *
     * @param choiceb 选项b内容
     */
    public void setChoiceb(String choiceb) {
        this.choiceb = choiceb;
    }

    /**
     * 获取选项c内容
     *
     * @return choicec - 选项c内容
     */
    public String getChoicec() {
        return choicec;
    }

    /**
     * 设置选项c内容
     *
     * @param choicec 选项c内容
     */
    public void setChoicec(String choicec) {
        this.choicec = choicec;
    }

    /**
     * 获取选项d内容
     *
     * @return choiced - 选项d内容
     */
    public String getChoiced() {
        return choiced;
    }

    /**
     * 设置选项d内容
     *
     * @param choiced 选项d内容
     */
    public void setChoiced(String choiced) {
        this.choiced = choiced;
    }

    /**
     * 获取选项e内容
     *
     * @return choicee - 选项e内容
     */
    public String getChoicee() {
        return choicee;
    }

    /**
     * 设置选项e内容
     *
     * @param choicee 选项e内容
     */
    public void setChoicee(String choicee) {
        this.choicee = choicee;
    }
    public Integer getUplimit() {
		return uplimit;
	}


	public void setUplimit(Integer uplimit) {
		this.uplimit = uplimit;
	}


	public Integer getLowlimit() {
		return lowlimit;
	}


	public void setLowlimit(Integer lowlimit) {
		this.lowlimit = lowlimit;
	}
	public Integer getUpscale() {
		return upscale;
	}


	public void setUpscale(Integer upscale) {
		this.upscale = upscale;
	}


	public Integer getLowscale() {
		return lowscale;
	}


	public void setLowscale(Integer lowscale) {
		this.lowscale = lowscale;
	}
	public boolean isDetect() {
		return detect;
	}
	public void setDetect(boolean detect) {
		this.detect = detect;
	}

}
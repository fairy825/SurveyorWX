package com.surveyor.pojo;

import java.util.Date;
import javax.persistence.*;

public class Survey {
    @Id
    private String id;

    /**
     * 用户id
     */
    @Column(name = "userId")
    private String userid;

    /**
     * 问卷标题
     */
    private String title;

    /**
     * 问卷描述
     */
    private String description;

    /**
     * 问卷状态
     */
    private Integer status;

    /**
     * 题数
     */
    private Integer num;

    /**
     * 悬赏金额
     */
    private Float price;

    /**
     * 需要的问卷数
     */
    @Column(name = "needPaper")
    private Integer needpaper;

    /**
     * 发布时间
     */
    @Column(name = "publish_time")
    private Date publishTime;

    /**
     * 截止时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 回收的问卷数 
     */
    @Column(name = "hadPaper")
    private Integer hadpaper; 
    
    private Boolean anony;
    @Column(name = "testLie")
    private Boolean testlie;

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
     * 获取用户id
     *
     * @return userId - 用户id
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取问卷标题
     *
     * @return title - 问卷标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置问卷标题
     *
     * @param title 问卷标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取问卷描述
     *
     * @return description - 问卷描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置问卷描述
     *
     * @param desc 问卷描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取问卷状态
     *
     * @return status - 问卷状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置问卷状态
     *
     * @param i 问卷状态
     */
    public void setStatus(int i) {
        this.status = i;
    }

    /**
     * 获取题数
     *
     * @return num - 题数
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置题数
     *
     * @param num 题数
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取悬赏金额
     *
     * @return price - 悬赏金额
     */
    public Float getPrice() {
        return price;
    }

    /**
     * 设置悬赏金额
     *
     * @param price 悬赏金额
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * 获取需要的问卷数
     *
     * @return needPaper - 需要的问卷数
     */
    public Integer getNeedpaper() {
        return needpaper;
    }

    /**
     * 设置需要的问卷数
     *
     * @param needpaper 需要的问卷数
     */
    public void setNeedpaper(Integer needpaper) {
        this.needpaper = needpaper;
    }

    /**
     * 获取发布时间
     *
     * @return publish_time - 发布时间
     */
    public Date getPublishTime() {
        return publishTime;
    }

    /**
     * 设置发布时间
     *
     * @param publishTime 发布时间
     */
    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    /**
     * 获取截止时间
     *
     * @return end_time - 截止时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置截止时间
     *
     * @param endTime 截止时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取回收的问卷数 
     *
     * @return hadPaper - 回收的问卷数 
     */
    public Integer getHadpaper() {
        return hadpaper;
    }

    /**
     * 设置回收的问卷数 
     *
     * @param hadpaper 回收的问卷数 
     */
    public void setHadpaper(Integer hadpaper) {
        this.hadpaper = hadpaper;
    }
    /**
     * @return anony
     */
    public Boolean getAnony() {
        return anony;
    }

    /**
     * @param anony
     */
    public void setAnony(Boolean anony) {
        this.anony = anony;
    }
    
    /**
     * @return testlie
     */
    public Boolean getTestlie() {
        return testlie;
    }

    /**
     * @param testlie
     */
    public void settestlie(Boolean testlie) {
        this.testlie = testlie;
    }
}
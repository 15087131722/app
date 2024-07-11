package com.example.im.model.bean;

import com.example.im.controller.activity.model.Experience;

import java.util.ArrayList;
import java.util.List;

public class user_info {
    private String name;
    private String jobTitle;
    private String introduction;
    private String experience;
    private String etExperience;
    private String avatarUrl; // 可选，用于存储头像的URL
    private List<Experience> experienceList; // 工作经历列表

    // 构造方法，根据需要自行添加
    public user_info(String name, String jobTitle, String introduction, String etExperience, String experience, String avatarUrl) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.introduction = introduction;
        this.experience = experience;
        this.etExperience = etExperience;
        this.avatarUrl = avatarUrl;
        this.experienceList = new ArrayList<>();
    }

    // 构造方法，适用于只传递工作经历列表的情况
    public user_info(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    // Getter 和 Setter 方法，根据需要生成
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getEtExperience() {
        return etExperience;
    }

    public void setEtExperience(String experience) {
        this.etExperience = experience;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Experience> getExperienceList() {
        return experienceList;
    }

    public void setExperienceList(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    // 添加工作经历到列表中
    public void addExperience(Experience experience) {
        if (experienceList == null) {
            experienceList = new ArrayList<>();
        }
        experienceList.add(experience);
    }
}

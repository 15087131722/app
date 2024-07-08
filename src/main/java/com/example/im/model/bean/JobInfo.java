package com.example.im.model.bean;

public class JobInfo {
    String name_HR;
    String JobName;
    String detail;
    String salary;

    public JobInfo(String name_HR, String jobName, String detail, String salary) {
        this.name_HR = name_HR;
        this.JobName = jobName;
        this.detail = detail;
        this.salary = salary;
    }

    public JobInfo() {
    }

    public String getName_HR() {
        return name_HR;
    }

    public void setName_HR(String name_HR) {
        this.name_HR = name_HR;
    }

    public String getJobName() {
        return JobName;
    }

    public void setJobName(String jobName) {
        JobName = jobName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }


}

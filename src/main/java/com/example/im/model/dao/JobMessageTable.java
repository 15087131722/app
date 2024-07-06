package com.example.im.model.dao;

public class JobMessageTable {

    public static final String TAB_NAME = "tab_message_Job";
    public static final String COL_JOBID = "jobid";//job id
    public static final String COL_HXID = "hxid";//hr环信id
    public static final String COL_JOBMESSAGE = "jobmessage";//简介
    public static final String COL_JOBTITLE = "jobtitle";//标题

    public static final String CREATE_TAB ="create table "
            + TAB_NAME + " ("
            + COL_HXID + " text primary key,"
            + COL_JOBID + " text primary key,"
            + COL_JOBMESSAGE + " text,"
            + COL_JOBTITLE +" text);";

}

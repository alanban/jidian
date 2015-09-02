package com.qidianliterature.jidian.DB.JavaBean;

/**
 * 项目名称：jidian
 * 类描述：
 * 创建人：BS
 * 创建时间：2015/8/20 0020 15:50
 * 修改人：
 * 修改时间：2015/8/20 0020 15:50
 * 修改备注：
 */
public class Draft {
    //草稿
    public  int Draft_ID;
    public  String Draft_PublishTime;//发布时间
    public int Draft_User_ID;
    public  String Draft_Section_Title="";//章节标题
    public  int Draft_Section_ID =0;//拥有的章节号
    public   int Draft_Writer_ID=0;//写手号
    public  String Draft_Section_Content="";//内容
    public  boolean Draft_Section_Continue_Write_Permission;

    public  int getDraft_ID() {
        return Draft_ID;
    }

    public void setDraft_ID(int draft_ID) {
        Draft_ID = draft_ID;
    }

    public  String getDraft_PublishTime() {
        return Draft_PublishTime;
    }

    public  void setDraft_PublishTime(String draft_PublishTime) {
        Draft_PublishTime = draft_PublishTime;
    }

    public  int getDraft_User_ID() {
        return Draft_User_ID;
    }

    public  void setDraft_User_ID(int draft_User_ID) {
        Draft_User_ID = draft_User_ID;
    }

    public String getDraft_Section_Title() {
        return Draft_Section_Title;
    }

    public  void setDraft_Section_Title(String draft_Section_Title) {
        Draft_Section_Title = draft_Section_Title;
    }

    public  int getDraft_Section_ID() {
        return Draft_Section_ID;
    }

    public  void setDraft_Section_ID(int draft_Section_ID) {
        Draft_Section_ID = draft_Section_ID;
    }

    public  int getDraft_Writer_ID() {
        return Draft_Writer_ID;
    }

    public  void setDraft_Writer_ID(int draft_Writer_ID) {
        Draft_Writer_ID = draft_Writer_ID;
    }

    public  String getDraft_Section_Content() {
        return Draft_Section_Content;
    }

    public  void setDraft_Section_Content(String draft_Section_Content) {
        Draft_Section_Content = draft_Section_Content;
    }

    public  boolean isDraft_Section_Continue_Write_Permission() {
        return Draft_Section_Continue_Write_Permission;
    }

    public  void setDraft_Section_Continue_Write_Permission(boolean draft_Section_Continue_Write_Permission) {
        Draft_Section_Continue_Write_Permission = draft_Section_Continue_Write_Permission;
    }
}

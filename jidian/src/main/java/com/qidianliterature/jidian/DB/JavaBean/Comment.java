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
public class Comment {

    public  int Comment_User_id;
    public  String Comment_time;
    public  int Comment_ID;
    public   int Comment_Parent_ID;
    public  String  Comment_Content;



    public  int getComment_User_id() {
        return Comment_User_id;
    }

    public  void setComment_User_id(int comment_User_id) {
        Comment_User_id = comment_User_id;
    }

    public  String getComment_time() {
        return Comment_time;
    }

    public  void setComment_time(String comment_time) {
        Comment_time = comment_time;
    }

    public  int getComment_ID() {
        return Comment_ID;
    }

    public  void setComment_ID(int comment_ID) {
        Comment_ID = comment_ID;
    }

    public  int getComent_Parent_ID() {
        return Comment_Parent_ID;
    }

    public  void setComent_Parent_ID(int comment_Parent_ID) {
        Comment_Parent_ID = comment_Parent_ID;
    }

    public  String getComment_Content() {
        return Comment_Content;
    }

    public  void setComment_Content(String comment_Content) {
        Comment_Content = comment_Content;
    }
}

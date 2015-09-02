package com.qidianliterature.jidian.DB.JavaBean;

import android.graphics.Bitmap;

/**
 * 项目名称：jidian
 * 类描述：
 * 创建人：BS
 * 创建时间：2015/8/20 0020 15:50
 * 修改人：
 * 修改时间：2015/8/20 0020 15:50
 * 修改备注：
 */
public class User {
    public    int User_Phone_Number=0;//用户手机号
    public  String User_NickNAME="";//用户名
    public   int User_ID=0;//用户号
    public   String User_Password="";
    public   int User_ArtID=0;
    //**************************************************
    public   int User_portrait_Id=0;//肖像号 我觉得没意义
    public   String User_PersonalInfo="";//个人信息
    public   String User_Brief="";//简介
    public   String User_valid="";//权限？ 还是can 或can't  boolean??
    public   String User_Birthday="";//生日
    public    String User_Sex="";//性别
    public Bitmap User_image=null;//用户头像   未加入数据库表
    public  String User_image_URL="";//头像地址
    public  String User_Email="";//用户邮箱
    public  int User_ArticleNumberInTotal=0 ;//用户文章总数   未加入数据库表
    public  int[] User_AllArticleNumberInDB ={};//用户拥有的文章的编号   未加入数据库表
    public  String[] User_ArticleNameInTotal={""};//用户拥有的文章的名称  未加入数据库表

    public  int getUser_Phone_Number() {
        return User_Phone_Number;
    }

    public  void setUser_Phone_Number(int user_Phone_Number) {
        User_Phone_Number = user_Phone_Number;
    }



    public  void setUser_AllArticleNumberInDB(int[] user_AllArticleNumberInDB) {
        User_AllArticleNumberInDB = user_AllArticleNumberInDB;
    }

    public  String getUser_NickNAME() {
        return User_NickNAME;
    }

    public  void setUser_NickNAME(String user_NickNAME) {
        User_NickNAME = user_NickNAME;
    }

    public  int getUser_ID() {
        return User_ID;
    }

    public  void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public  String getUser_Password() {
        return User_Password;
    }

    public  void setUser_Password(String user_Password) {
        User_Password = user_Password;
    }

    public  int getUser_ArtID() {
        return User_ArtID;
    }

    public  void setUser_ArtID(int user_ArtID) {
        User_ArtID = user_ArtID;
    }

    public  int getUser_portrait_Id() {
        return User_portrait_Id;
    }

    public  void setUser_portrait_Id(int user_portrait_Id) {
        User_portrait_Id = user_portrait_Id;
    }

    public  String getUser_PersonalInfo() {
        return User_PersonalInfo;
    }

    public  void setUser_PersonalInfo(String user_PersonalInfo) {
        User_PersonalInfo = user_PersonalInfo;
    }

    public  String getUser_Brief() {
        return User_Brief;
    }

    public  void setUser_Brief(String user_Brief) {
        User_Brief = user_Brief;
    }

    public  String getUser_valid() {
        return User_valid;
    }

    public  void setUser_valid(String user_valid) {
        User_valid = user_valid;
    }

    public  String getUser_Birthday() {
        return User_Birthday;
    }

    public  void setUser_Birthday(String user_Birthday) {
        User_Birthday = user_Birthday;
    }

    public  String getUser_Sex() {
        return User_Sex;
    }

    public  void setUser_Sex(String user_Sex) {
        User_Sex = user_Sex;
    }

    public  Bitmap getUser_image() {
        return User_image;
    }

    public  void setUser_image(Bitmap user_image) {
        User_image = user_image;
    }

    public  String getUser_image_URL() {
        return User_image_URL;
    }

    public  void setUser_image_URL(String user_image_URL) {
        User_image_URL = user_image_URL;
    }

    public  String getUser_Email() {
        return User_Email;
    }

    public  void setUser_Email(String user_Email) {
        User_Email = user_Email;
    }

    public  int getUser_ArticleNumberInTotal() {
        return User_ArticleNumberInTotal;
    }

    public  void setUser_ArticleNumberInTotal(int user_ArticleNumberInTotal) {
        User_ArticleNumberInTotal = user_ArticleNumberInTotal;
    }

    public  int[] getUser_AllArticleNumberInDB() {
        return User_AllArticleNumberInDB;
    }

    public  void AddUser_AllArticleNumberInDB(int user_ArticleNumberInDB) {
        User_AllArticleNumberInDB[User_ArticleNumberInTotal]= user_ArticleNumberInDB;
    }

    public  String[] getUser_ArticleNameInTotal() {
        return User_ArticleNameInTotal;
    }

    public  void setUser_ArticleNameInTotal(String[] user_ArticleNameInTotal) {
        User_ArticleNameInTotal = user_ArticleNameInTotal;
    }
}

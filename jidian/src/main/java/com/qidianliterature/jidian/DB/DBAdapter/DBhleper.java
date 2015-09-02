package com.qidianliterature.jidian.DB.DBAdapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 项目名称：jidian
 * 类描述：
 * 创建人：BS
 * 创建时间：2015/8/20 0020 15:49
 * 修改人：
 * 修改时间：2015/8/20 0020 15:49
 * 修改备注：
 */
public class DBhleper extends SQLiteOpenHelper {
    final static String DB_name="QidianDataBase2.db";
    final static int version=1;
    public DBhleper(Context context) {
        super(context, DB_name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        //�������һ����
        //����ʹ��  boolean����
        String CreateTable_User="create table User(User_Phone_Number INTEGER NOT NULL,User_NickName varchar(10) NOT NULL ,User_ID INTEGER PRIMARY KEY,User_Password char(16),User_ArtID varchar(8),User_PortraitID varchar(8),User_PersonalInfo char,User_Brief char(50),User_Valid char,User_Birthday char,User_Sex varchar,User_ImageURL varchar(50),User_Email varchar(30))";
        DB.execSQL(CreateTable_User);
        DB.execSQL("create table Article(Title VARCHAR,Article_ID INTEGER PRIMARY KEY, User_ID INTEGER NOT NULL,Tag_ID INTEGER,Remark char(50),NEW char , Put_Time char,ReadTime char" +
                ",Article_Content CHAR,Art_PicturePath char)");

        DB.execSQL("CREATE TABLE Tag(Tag_ID INTEGER PRIMARY KEY,Tag_Name VARCHAR(10))");
        DB.execSQL("CREATE TABLE Section(Section_Title VARCHAR(20),Section_ID INTEGER,Writer_ID INTEGER," +
                "Section_Content CHAR,Section_Continue_Write_Permission boolean, Article_ID INTEGER)");
        DB.execSQL("create table Comment(Comment_ID integer primary key,Comment_User_ID integer,Comment_time char,Comment_Parent_ID integer,Comment_Content char)");
        DB.execSQL("create table Draft(Draft_ID integer,Draft_PublishTime datetime,Draft_User_ID integer,Draft_Section_Title varchar,Draft_Section_ID integer,Draft_Writer_ID integer," +
                "Draft_Section_Content char,Draft_Section_Continue_Write_Permission boolean )");

        //ע�⣬
        // PRIMARY KEY must be unique
        // Android��Sqlite储存数据为泛类型储存，列如以String类型取出  bollean 以int 0 or 1取出
        // ���� DATETIME
        //        str = cursor.getString(cursor.getColumnIndex(��Ӧ������));
        //        format = new SimpleDateFormat(yyyy-MM-dd HH:mm:ss);
        //        date = (Date) format.parse(str);  ת��Ϊdate��
        //*******************************************************************************************
        //        boolean ���� �������Ϊ0 ��1 Ҳ������true��false   �����Ϊ0 ��1


    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i2) {

    }
}
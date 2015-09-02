package com.qidianliterature.jidian.DB.DBAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.qidianliterature.jidian.DB.JavaBean.Article;
import com.qidianliterature.jidian.DB.JavaBean.Comment;
import com.qidianliterature.jidian.DB.JavaBean.Draft;
import com.qidianliterature.jidian.DB.JavaBean.Section;
import com.qidianliterature.jidian.DB.JavaBean.Tag;
import com.qidianliterature.jidian.DB.JavaBean.User;

import java.util.ArrayList;

/**
 * 项目名称：jidian
 * 类描述：
 * 创建人：BS
 * 创建时间：2015/8/20 0020 15:49
 * 修改人：
 * 修改时间：2015/8/20 0020 15:49
 * 修改备注：
 */
public class DBAdapter {
    Context context;
    DBhleper my_dBhleper;
    SQLiteDatabase myDB;

    public DBAdapter(Context context) {
        this.context = context;
    }
    public void OpenDB(){

        try{
            my_dBhleper=new DBhleper(context);
            myDB=my_dBhleper.getWritableDatabase();
        }catch(SQLiteException exception){
            myDB=my_dBhleper.getReadableDatabase();
        }
    }
    public void CloseDB(){
        if(myDB!=null){
            myDB.close();
            myDB=null;
        }
    }
    //User�����ɾ�Ĳ�
    public static final String TableName_User="User";
    public static final String  User_Phone_Number="User_Phone_Number";
    public static final String  User_NickName="User_NickName";
    public static final String  User_iD ="User_ID";
    public static final String  User_Password="User_Password";
    public static final String  User_ArtID="User_ArtID";
    public static final String  User_PortraitID="User_PortraitID";
    public static final String  User_PersonalInfo="User_PersonalInfo";
    public static final String  User_Brief="User_Brief";
    public static final String  User_Valid="User_Valid";
    public static final String  User_Birthday="User_Birthday";
    public static final String  User_Sex="User_Sex";
    public static final String  User_ImageURL="User_ImageURL";
    public static final String  User_Email="User_Email";

    public long InsertUser(User user){
        long position;
        ContentValues values=new ContentValues();
        values.put(User_Phone_Number,user.getUser_Phone_Number());
        values.put(User_NickName,user.getUser_NickNAME());
        values.put(User_iD,user.getUser_ID());
        values.put(User_Password,user.getUser_Password());
        values.put(User_ArtID,user.getUser_ArtID());
        values.put(User_PortraitID,user.getUser_portrait_Id());
        values.put(User_PersonalInfo,user.getUser_PersonalInfo());
        values.put(User_Brief,user.getUser_Brief());
        values.put(User_Valid,user.getUser_valid());
        values.put(User_Birthday,user.getUser_Birthday());
        values.put(User_Sex,user.getUser_Sex());
        values.put(User_ImageURL,user.getUser_image_URL());
        values.put(User_Email,user.getUser_Email());
        position=myDB.insert(TableName_User,null,values);
        System.out.println("User-->>"+"插入位置为="+position);//
        return position;//
    }
    public int DeleteUserByID(int User_ID){
        int result=myDB.delete("User","User_ID="+User_ID,null);
        //Returns
        //the number of rows affected if a whereClause is passed in, 0 otherwise. To remove all rows and get a count pass "1" as the whereClause.
        return  result;
    }
    public int UpdateUserByID(User user,int User_ID){
        ContentValues values=new ContentValues();
        values.put(User_Phone_Number,user.getUser_Phone_Number());
        values.put(User_NickName,user.getUser_NickNAME());
        values.put(User_iD,user.getUser_ID());
        values.put(User_Password,user.getUser_Password());
        values.put(User_ArtID,user.getUser_ArtID());
        values.put(User_PortraitID,user.getUser_portrait_Id());
        values.put(User_PersonalInfo,user.getUser_PersonalInfo());
        values.put(User_Brief,user.getUser_Brief());
        values.put(User_Valid,user.getUser_valid());
        values.put(User_Birthday,user.getUser_Birthday());
        values.put(User_Sex,user.getUser_Sex());
        values.put(User_ImageURL,user.getUser_image_URL());
        values.put(User_Email,user.getUser_Email());
        int result=myDB.update("User",values,"User_ID="+User_ID,null);
        return  result;
    }
    public ArrayList<User> QueryUserByID(int User_ID){
        Cursor cursor=myDB.query("User",null,"User_ID="+User_ID,null, null, null, null);//��ѯ������
        return ConvertToUser(cursor);
    }
    public ArrayList<User> ConvertToUser(Cursor cursor){
        int count =cursor.getCount();
        if(count==0||!cursor.moveToFirst()){
            System.out.println("User-->>"+"数据库空");
            return null;
        }
        System.out.println("User-->>"+"符合条件数据库数据条目为："+count);
        User[] users=new User[count];
        ArrayList<User> usersList=new ArrayList<User>();
        for(int i=0;i<count;i++){

            users[i]=new User();
            users[i].setUser_Phone_Number(cursor.getInt(cursor.getColumnIndex(User_Phone_Number)));
            users[i].setUser_NickNAME(cursor.getString(cursor.getColumnIndex(User_NickName)));
            users[i].setUser_Birthday(cursor.getString(cursor.getColumnIndex(User_Birthday)));
            users[i].setUser_Brief(cursor.getString(cursor.getColumnIndex(User_Brief)));
            users[i].setUser_Email(cursor.getString(cursor.getColumnIndex(User_Email)));
            users[i].setUser_ArtID(cursor.getInt(cursor.getColumnIndex(User_ArtID)));
            users[i].setUser_ID(cursor.getInt(cursor.getColumnIndex(User_ID)));
            users[i].setUser_valid(cursor.getString(cursor.getColumnIndex(User_Valid)));
            users[i].setUser_Sex(cursor.getString(cursor.getColumnIndex(User_Sex)));
            users[i].setUser_portrait_Id(cursor.getInt(cursor.getColumnIndex(User_PortraitID)));
            users[i].setUser_PersonalInfo(cursor.getString(cursor.getColumnIndex(User_PersonalInfo)));
            users[i].setUser_Password(cursor.getString(cursor.getColumnIndex(User_Password)));
            users[i].setUser_image_URL(cursor.getString(cursor.getColumnIndex(User_ImageURL)));
            usersList.add(i,users[i]);
            cursor.moveToNext();

        }
        return  usersList;
    }


    public  ArrayList<User> QueryAllUser(){
        Cursor cursor=myDB.query(TableName_User,null,null,null,null,null,null);
        return ConvertToUser(cursor);
    }

    //Article表
    public static final String  TableName_Article="Article";
    public static final String  Title="Title";
    public static final String  Article_ID="Article_ID";
    public static final String  User_ID="User_ID";
    public static final String  Tag_ID="Tag_ID";
    public static final String  Remark="Remark";
    public static final String  NEW="NEW";
    public static final String  Put_Time="Put_Time";
    public static final String  ReadTime="ReadTime";
    public static final String  Article_Content="Article_Content";
    public static  final String Art_PicturePath="Art_PicturePath";


    public  long InsertArticle(Article article){
        long position;
        ContentValues values=new ContentValues();
        values.put(Title,article.getTitle());
        values.put(Article_ID,article.getArticle_ID());
        values.put(User_ID,article.getUser_ID());
        values.put(Tag_ID,article.getTag_ID());
        values.put(Remark,article.getRemark());
        values.put(NEW,article.getNew());
        values.put(Put_Time,article.getPub_Time());
        values.put(ReadTime,article.getRead_Time());
        values.put(Article_Content,article.getArticle_Content());
        values.put(Art_PicturePath,article.getArt_PicturePath());
        position=myDB.insert(TableName_Article,null,values);
        System.out.println("-->>"+TableName_Article+"插入位置为="+position);//
        return  position;
    }
    public  int DeleteArticleByID(int Article_id){
        int result= myDB.delete(TableName_Article,Article_ID+"="+Article_id,null);
        return  result;
    }
    public int UpdateArticleByID(Article article,int Article_id){
        ContentValues values=new ContentValues();
        values.put(Title,article.getTitle());
        values.put(Article_ID,article.getArticle_ID());
        values.put(User_ID,article.getUser_ID());
        values.put(Tag_ID,article.getTag_ID());
        values.put(Remark,article.getRemark());
        values.put(NEW,article.getNew());
        values.put(Put_Time,article.getPub_Time());
        values.put(ReadTime,article.getRead_Time());
        values.put(Article_Content,article.getArticle_Content());
        values.put(Art_PicturePath,article.getArt_PicturePath());
        int result=myDB.update(TableName_Article,values,Article_ID+"="+Article_id,null);
        return  result;
    }
    public Article[] QueryArticleByID(int Article_id){
        Cursor cursor=myDB.query(TableName_Article,null,Article_ID+"="+Article_id,null, null, null, null);//��ѯ������
        return ConvertToArticle(cursor);
    }
    public Article[] QueryArticleByUserID(int User_iD){
        Cursor cursor=myDB.query(TableName_Article,null,User_ID+"="+User_iD,null, null, null, null);
        return ConvertToArticle(cursor);
    }
    public Article[] QueryAllArticle(){
        Cursor cursor=myDB.query(TableName_Article,null,null,null,null,null,null);
        return ConvertToArticle(cursor);
    }
    public Article[] ConvertToArticle(Cursor cursor) {
        int count = cursor.getCount();
        if (count == 0 || !cursor.moveToFirst()) {
            System.out.println("-->>" + "数据库空");
            return null;
        }
        System.out.println("Article-->>" + "符合的数据库条目为：" + count);
        Article[] articles = new Article[count];
        for (int i = 0; i < count; i++) {
            articles[i] = new Article();
            articles[i].setTitle(cursor.getString(cursor.getColumnIndex(Title)));
            articles[i].setArticle_ID(cursor.getInt(cursor.getColumnIndex(Article_ID)));
            articles[i].setUser_ID(cursor.getInt(cursor.getColumnIndex(User_ID)));
            articles[i].setTag_ID(cursor.getInt(cursor.getColumnIndex(Tag_ID)));
            articles[i].setRemark(cursor.getString(cursor.getColumnIndex(Remark)));
            articles[i].setNew(cursor.getString(cursor.getColumnIndex(NEW)));
            articles[i].setPub_Time(cursor.getString(cursor.getColumnIndex(Put_Time)));
            articles[i].setRead_Time(cursor.getString(cursor.getColumnIndex(ReadTime)));
            articles[i].setArticle_Content(cursor.getString(cursor.getColumnIndex(Article_Content)));
            articles[i].setArt_PicturePath(cursor.getString(cursor.getColumnIndex(Art_PicturePath)));
            cursor.moveToNext();
        }

        return  articles;
    }


    //comment表
    public static final String Comment_ID="Comment_ID";
    public static final String Comment_User_id="Comment_User_ID";
    public static  final String Comment_time="Comment_time";
    public  static final String Comment_Parent_ID="Comment_Parent_ID";
    public static final String  Comment_Content="Comment_Content";
    public static final String TableName_Comment="Comment";

    public long InsertComment(Comment comment){
        ContentValues values=new ContentValues();
        values.put(Comment_ID,comment.getComment_ID());
        values.put(Comment_User_id,comment.getComment_User_id());
        values.put(Comment_time,comment.getComment_time());
        values.put(Comment_Parent_ID,comment.getComent_Parent_ID());
        values.put(Comment_Content, comment.getComment_Content());
        long position=myDB.insert(TableName_Comment,null,values);
        System.out.println("-->>"+TableName_Comment+"插入位置position="+position);//
        return position;
    }
    public int DeleteCommentByID(int Comment_id){
        int result= myDB.delete(TableName_Comment,Comment_ID+"="+Comment_id,null);
        return  result;
    }
    public int UpdateCommentByID(Comment comment,int Comment_id){
        ContentValues values=new ContentValues();
        values.put(Comment_ID,comment.getComment_ID());
        values.put(Comment_User_id,comment.getComment_User_id());
        values.put(Comment_time,comment.getComment_time());
        values.put(Comment_Parent_ID,comment.getComent_Parent_ID());
        values.put(Comment_Content, comment.getComment_Content());
        int result=myDB.update(TableName_Comment,values,Comment_ID+"="+Comment_id,null);
        return  result;
    }
    public Comment[] QueryCommentByID(int Comment_id){
        Cursor cursor=myDB.query(TableName_Comment,null,Comment_ID+"="+Comment_id,null, null, null, null);
        return  ConvertToComment(cursor);
    }
    public Comment[] QueryAllComment(){
        Cursor cursor=myDB.query(TableName_Comment,null,null,null, null, null, null);
        return  ConvertToComment(cursor);
    }
    public Comment[] ConvertToComment(Cursor cursor){
        int count=cursor.getCount();
        if(count==0||!cursor.moveToFirst()){
            System.out.println("-->>"+"数据库空");
            return null;
        }
        System.out.println("Comment-->>"+"数据库符合条件条目有"+count);
        Comment[] comments=new Comment[count];
        for(int i=0;i<count;i++){
            comments[i]=new Comment();
            comments[i].setComment_ID(cursor.getInt(cursor.getColumnIndex(Comment_ID)));
            comments[i].setComment_User_id(cursor.getInt(cursor.getColumnIndex(Comment_User_id)));
            comments[i].setComment_time(cursor.getString(cursor.getColumnIndex(Comment_time)));
            comments[i].setComent_Parent_ID(cursor.getInt(cursor.getColumnIndex(Comment_Parent_ID)));
            comments[i].setComment_Content(cursor.getString(cursor.getColumnIndex(Comment_Content)));
            cursor.moveToNext();
        }
        return comments;


    }

    //tag表
    public static String tag_ID="Tag_ID";
    public static String Tag_Name="Tag_Name";
    public static String TableName_Tag="Tag";

    public long  InsertTag(Tag tag){
        ContentValues values=new ContentValues();
        values.put(tag_ID,tag.getTag_id());
        values.put(Tag_Name, tag.getTag_name());
        long position=myDB.insert(TableName_Tag,null,values);
        System.out.println("-->>"+TableName_Tag+"插入位置为="+position);//
        return  position;
    }
    public int DeleteTagByID(int Tag_id){
        int result=myDB.delete(TableName_Tag,tag_ID+"="+Tag_id,null);
        return  result;
    }
    public int UpdateTagByID(Tag tag,int Tag_id){
        ContentValues values=new ContentValues();
        values.put(tag_ID,tag.getTag_id());
        values.put(Tag_Name, tag.getTag_name());
        int result=myDB.update(TableName_Tag,values,tag_ID+"="+Tag_id,null);
        return  result;
    }
    public Tag[] QueryTagByID(int Tag_id){
        Cursor cursor=myDB.query(TableName_Tag,null,tag_ID+"="+Tag_id,null,null,null,null);
        return ConvertToTag(cursor);
    }
    public Tag[] QueryAllTag(){
        Cursor cursor=myDB.query(TableName_Tag,null,null,null,null,null,null);
        return  ConvertToTag(cursor);
    }
    public Tag[] ConvertToTag(Cursor cursor){
        int count=cursor.getCount();
        if(count==0||!cursor.moveToFirst()){
            System.out.println("Tag-->>"+"数据库空");
            return null;
        }

        System.out.println("Tag-->>" + "符合条件数据库数据条目为" + count);
        Tag[] tags=new Tag[count];
        cursor.moveToFirst();

        for(int i=0;i<count;i++){

            tags[i]=new Tag();
            tags[i].setTag_id(cursor.getInt(cursor.getColumnIndex(tag_ID)));

            tags[i].setTag_name(cursor.getString(cursor.getColumnIndex(Tag_Name)));

            cursor.moveToNext();


        }

        return tags;
    }

    //Draft表
    public static final String TableName_Draft="Draft";
    public static final String Draft_ID="Draft_ID";
    public static final String Draft_PublishTime="Draft_PublishTime";
    public static final String Draft_User_ID="Draft_User_ID";
    public static final String Draft_Section_Title="Draft_Section_Title";
    public static final String Draft_Section_ID="Draft_Section_ID";
    public static final String Draft_Writer_ID="Draft_Writer_ID";
    public long InsertDraft(Draft draft){

        ContentValues values=new ContentValues();
        values.put(Draft_ID,draft.getDraft_ID());
        values.put(Draft_PublishTime,draft.getDraft_PublishTime());
        values.put(Draft_User_ID,draft.getDraft_User_ID());
        values.put(Draft_Section_Title,draft.getDraft_Section_Title());
        values.put(Draft_Section_ID,draft.getDraft_Section_ID());
        values.put(Draft_Writer_ID, draft.getDraft_Writer_ID());
        long position=myDB.insert(TableName_Draft,null,values);
        System.out.println("Draft-->>插入位置为="+position);//
        return  position;
    }
    public int DeleteDraftByID(int Draft_id){
        int result=myDB.delete(TableName_Draft,Draft_ID+"="+Draft_id,null);
        return  result;
    }
    public int UpdateDraftByID(Draft draft,int Draft_id){
        ContentValues values=new ContentValues();
        values.put(Draft_ID,draft.getDraft_ID());
        values.put(Draft_PublishTime,draft.getDraft_PublishTime());
        values.put(Draft_User_ID,draft.getDraft_User_ID());
        values.put(Draft_Section_Title,draft.getDraft_Section_Title());
        values.put(Draft_Section_ID,draft.getDraft_Section_ID());
        values.put(Draft_Writer_ID,draft.getDraft_Writer_ID());
        int result=myDB.update(TableName_Draft,values,Draft_ID+"="+Draft_id,null);
        return  result;
    }
    public Draft[] QueryDraftByID(int Draft_id){
        Cursor cursor=myDB.query(TableName_Draft,null,Draft_ID+"="+Draft_id,null,null,null,null);
        return  ConvertToDraft(cursor);
    }
    public Draft[] QueryAllDraft(){
        Cursor cursor=myDB.query(TableName_Draft,null,null,null,null,null,null);
        return  ConvertToDraft(cursor);
    }
    public Draft[] ConvertToDraft(Cursor cursor){
        int count=cursor.getCount();
        if(count==0||!cursor.moveToFirst()){
            System.out.println("Draft-->>"+"数据库空");
            return null;
        }
        System.out.println("Draft-->>"+"符合条件数据库数据条目："+count);
        Draft[] drafts=new Draft[count];
        for(int i=0;i<count;i++){
            drafts[i]=new Draft();
            drafts[i].setDraft_ID(cursor.getInt(cursor.getColumnIndex(Draft_ID)));
            drafts[i].setDraft_PublishTime(cursor.getString(cursor.getColumnIndex(Draft_PublishTime)));
            drafts[i].setDraft_User_ID(cursor.getInt(cursor.getColumnIndex(Draft_User_ID)));
            drafts[i].setDraft_Section_Title(cursor.getString(cursor.getColumnIndex(Draft_Section_Title)));
            drafts[i].setDraft_Section_ID(cursor.getInt(cursor.getColumnIndex(Draft_Section_ID)));
            drafts[i].setDraft_Writer_ID(cursor.getInt(cursor.getColumnIndex(Draft_Writer_ID)));
            cursor.moveToNext();
        }
        return  drafts;
    }

    //Section表
    public static final String TableName_Section="Section";
    public static final String Section_Title="Section_Title";
    public static final String  Section_ID="Section_ID";
    public static final String  Writer_ID="Writer_ID";
    public static final String  Section_Content="Section_Content";
    public static final String  Article_ID_Section="Article_ID";
    public static final String  Section_Continue_Write_Permission="Section_Continue_Write_Permission";
    public long InsertSection(Section section){
        ContentValues values=new ContentValues();
        values.put(Section_Title,section.getSection_Title());
        values.put(Section_ID,section.getSection_ID());
        values.put(Writer_ID,section.getWriter_ID());
        values.put(Section_Content,section.getSection_Content());
        values.put(Section_Continue_Write_Permission,section.getSection_Continue_Write_Permission());
        values.put(Article_ID_Section,section.getArticle_ID());
        long position=myDB.insert(TableName_Section,null,values);
        System.out.println("-->>"+TableName_Section+"插入位置为="+position);//
        return  position;
    }
    public int DeleteSectionByID(int Section_id){
        int result=myDB.delete(TableName_Section, Section_ID + "=" + Section_id, null);
        return  result;
    }
    public int UpdateSectionByID(Section section,int Section_id){
        ContentValues values=new ContentValues();
        values.put(Section_Title,section.getSection_Title());
        values.put(Section_ID,section.getSection_ID());
        values.put(Writer_ID,section.getWriter_ID());
        values.put(Section_Content,section.getSection_Content());
        values.put(Article_ID_Section,section.getArticle_ID());
        values.put(Section_Continue_Write_Permission,section.getSection_Continue_Write_Permission());
        int result=myDB.update(TableName_Section, values, Section_ID + "=" + Section_id, null);
        return  result;
    }

    public Section[] QuerySectionByID(int Section_id){
        Cursor cursor=myDB.query(TableName_Section, null, Section_ID + "=" + Section_id, null, null, null, null);
        return  ConvertToSection(cursor);
    }

    public Section[] QuerySectionByArticle_ID(int Article_ID){
        Cursor cursor=myDB.query(TableName_Section, null, Article_ID_Section + "=" + Article_ID,null,null,null,null);
        return  ConvertToSection(cursor);
    }

    public Section[] QueryAllSection(){
        Cursor cursor=myDB.query(TableName_Section,null, null, null, null, null, null);
        return ConvertToSection(cursor);
    }
    public Section[] ConvertToSection(Cursor cursor){
        int count=cursor.getCount();
        if(count==0||!cursor.moveToFirst()){
            System.out.println("Section-->>"+"数据库空");
            return null;
        }
        System.out.println("Section-->>"+"符合条件数据库数据条目："+count);
        Section[] sections=new Section[count];
        for (int i=0;i<count;i++){
            sections[i]=new Section();
            sections[i].setSection_Title(cursor.getString(cursor.getColumnIndex(Section_Title)));
            sections[i].setSection_ID(cursor.getInt(cursor.getColumnIndex(Section_ID)));
            sections[i].setWriter_ID(cursor.getInt(cursor.getColumnIndex(Writer_ID)));
            sections[i].setSection_Content(cursor.getString(cursor.getColumnIndex(Section_Content)));
            sections[i].setArticle_ID(cursor.getInt(cursor.getColumnIndex(Article_ID_Section)));
            sections[i].setSection_Continue_Write_Permission(cursor.getInt(cursor.getColumnIndex(Section_Continue_Write_Permission)));
            cursor.moveToNext();


        }
        return  sections;
    }
}
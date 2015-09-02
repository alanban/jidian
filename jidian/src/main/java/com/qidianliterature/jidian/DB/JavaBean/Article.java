package com.qidianliterature.jidian.DB.JavaBean;

import java.util.ArrayList;

/**
 * 项目名称：jidian
 * 类描述：
 * 创建人：BS
 * 创建时间：2015/8/20 0020 15:50
 * 修改人：
 * 修改时间：2015/8/20 0020 15:50
 * 修改备注：
 */
public class Article {
    public  String Title="";//文章标题
    public  int Article_ID =0;//文章号
    public   int User_ID=0;//作者号
    public    int Tag_ID;//标签号
    public   String Remark;
    public   String  New;
    public   String Pub_Time;
    public   String Read_Time;
    public  String Article_Content="";//文章内容

    public  String Art_PicturePath =null;//文章封面 未写入数据库
    public  int SectionNumber=0;//文章所拥有的章节总数，初始为0
    public ArrayList<Section> Article_Section;//文章所拥有的章节

    public Article() {

        Article_Section=new ArrayList<Section>();
    }


    //创建文章章节方法
    public void CreateSection(Section section){
        Section Section=section;
        this.Article_Section.add(section.Section_ID,Section);
        this.SectionNumber+=1;
    }
    //删除文章方法
    public Boolean DeleteSection(Section section){
        try{
            this.Article_Section.remove(section);
        }catch (Exception e){
            return false;
        }
        return  true;
    }
    //查找Section方法  返回 Section对象
    public Section FindSection(int Section_ID){

        for(Section result:Article_Section){
            if(result.Section_ID ==Section_ID){
                return result;
            }
        }
        return null;
    }

    public  String getArt_PicturePath() {
        return Art_PicturePath;
    }

    public  void setArt_PicturePath(String art_PicturePath) {
        Art_PicturePath = art_PicturePath;
    }
    //这里逻辑有问题
    public int getSectionID(){
        int ID=0;
        return  ID;
    }

    public  String getTitle() {
        return Title;
    }

    public  void setTitle(String title) {
        Title = title;
    }

    public  int getArticle_ID() {
        return Article_ID;
    }

    public  void setArticle_ID(int article_ID) {
        Article_ID = article_ID;
    }

    public  int getUser_ID() {
        return User_ID;
    }

    public  void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public  int getTag_ID() {
        return Tag_ID;
    }

    public  void setTag_ID(int tag_ID) {
        Tag_ID = tag_ID;
    }

    public  String getRemark() {
        return Remark;
    }

    public  void setRemark(String remark) {
        Remark = remark;
    }

    public  String getNew() {
        return New;
    }

    public  void setNew(String aNew) {
        New = aNew;
    }

    public  String getPub_Time() {
        return Pub_Time;
    }

    public  void setPub_Time(String pub_Time) {
        Pub_Time = pub_Time;
    }

    public  String getRead_Time() {
        return Read_Time;
    }

    public  void setRead_Time(String read_Time) {
        Read_Time = read_Time;
    }

    public  String getArticle_Content() {
        return Article_Content;
    }

    public  void setArticle_Content(String article_Content) {
        Article_Content = article_Content;
    }

    public  int getSectionNumber() {
        return SectionNumber;
    }

    public  void setSectionNumber(int sectionNumber) {
        SectionNumber = sectionNumber;
    }
}
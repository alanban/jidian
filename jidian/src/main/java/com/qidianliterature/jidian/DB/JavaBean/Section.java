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
public class Section {
    public  String Section_Title="";//章节标题
    public  int Section_ID =0;//章节号
    public   int Writer_ID=0;//作者号
    public  String Section_Content="";//文章内容
    public  int Article_ID;//文章号
    public  int Section_Continue_Write_Permission; //        boolean 类型 插入可以为0 或1 也可以是true和false   ，输出为0 或1

    public Section() {

    }

    public int getArticle_ID() {
        return Article_ID;
    }

    public void setArticle_ID(int article_ID) {
        Article_ID = article_ID;
    }

    public  String getSection_Title() {
        return Section_Title;
    }

    public  void setSection_Title(String section_Title) {
        Section_Title = section_Title;
    }

    public  int getSection_Continue_Write_Permission() {
        return Section_Continue_Write_Permission;
    }

    public  void setSection_Continue_Write_Permission(int section_Continue_Write_Permission) {
        Section_Continue_Write_Permission = section_Continue_Write_Permission;
    }

    public  int getSection_ID() {
        return Section_ID;
    }

    public  void setSection_ID(int section_ID) {
        Section_ID = section_ID;
    }

    public  int getWriter_ID() {
        return Writer_ID;
    }

    public  void setWriter_ID(int writer_ID) {
        Writer_ID = writer_ID;
    }

    public  String getSection_Content() {
        return Section_Content;
    }

    public  void setSection_Content(String section_Content) {
        Section_Content = section_Content;
    }
}

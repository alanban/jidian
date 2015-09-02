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
public class Tag {
    public  int Tag_id;
    public  String tag_name="";

    public  int getTag_id() {
        return Tag_id;
    }

    public  void setTag_id(int tag_id) {
        Tag_id = tag_id;
    }

    public  String getTag_name() {
        return tag_name;
    }

    public  void setTag_name(String tag_name) {
        this.tag_name = tag_name;
    }
}
package com.ccjoin.jmumall.mvp.model;

/**
 * @author liuzhang
 * @description 在MVP模式中，Model类的形式依然保持不变
 * @date 2016年08月29日 13:20
 */
public class UserBean {

    private String userName;
    private String passWord;


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassWord() {
        return passWord;
    }


    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}

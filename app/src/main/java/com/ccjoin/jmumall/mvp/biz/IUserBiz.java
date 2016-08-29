package com.ccjoin.jmumall.mvp.biz;

/**
 * @author liuzhang
 * @date 2016年08月29日 14:13
 */
public interface IUserBiz {

    void userLogin(String userName, String pwd, OnLoginListener loginListener);
}

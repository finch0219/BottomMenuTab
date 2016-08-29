package com.ccjoin.jmumall.mvp.biz;

import com.ccjoin.jmumall.mvp.model.UserBean;

/**
 * @author liuzhang
 * @description 检测用户登录的状态
 * @date 2016年08月29日 14:13
 */
public interface OnLoginListener {

    void loginSuccess(UserBean userBean);

    void loginFiled();
}

package com.ccjoin.jmumall.mvp.view;

import com.ccjoin.jmumall.mvp.model.UserBean;

/**
 * @author liuzhang
 * @description 提供给View(Activity, Fragment)层去具体实现的接口,
 * 每一个View(Activity or Fragment)层都需要实现类似这样一个接口.
 * @date 2016年08月29日 13:19
 */
public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void showLoading();

    void hideLoading();

    void toPersonCenter(UserBean userBean);

    void showFailedError();

    void clearUserName();

    void clearPassword();

}

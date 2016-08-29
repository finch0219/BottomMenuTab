package com.ccjoin.jmumall.mvp.biz;

import com.ccjoin.jmumall.mvp.model.UserBean;

/**
 * @author liuzhang
 * @description 用户登录的具体实现, 该实现类需要在Presenter中注入
 * @date 2016年08月29日 14:13
 */
public class UserBiz implements IUserBiz {

    @Override
    public void userLogin(final String userName, final String pwd, final OnLoginListener loginListener) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if ("lz".equals(userName) && "123".equals(pwd)) {
                    UserBean userBean = new UserBean();
                    userBean.setUserName(userName);
                    userBean.setPassWord(pwd);
                    loginListener.loginSuccess(userBean);
                } else {
                    loginListener.loginFiled();
                }
            }
        }.start();
    }
}

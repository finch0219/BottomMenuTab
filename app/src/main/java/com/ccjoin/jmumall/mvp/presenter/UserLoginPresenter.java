package com.ccjoin.jmumall.mvp.presenter;

import android.os.Handler;
import com.ccjoin.jmumall.mvp.biz.IUserBiz;
import com.ccjoin.jmumall.mvp.biz.OnLoginListener;
import com.ccjoin.jmumall.mvp.biz.UserBiz;
import com.ccjoin.jmumall.mvp.model.UserBean;
import com.ccjoin.jmumall.mvp.view.IUserLoginView;

/**
 * @author liuzhang
 * @description 集中处理业务逻辑(相当于MVC里的Controller层)
 * @date 2016年08月29日 13:20
 */
public class UserLoginPresenter {

    private IUserBiz userBiz;
    /** 注入View层需要实现的接口 */
    private IUserLoginView  userLoginView;
    private Handler mHandler = new Handler();


    public UserLoginPresenter(IUserLoginView userLoginView) {
        this.userLoginView = userLoginView;
        this.userBiz = new UserBiz();
    }


    /**
     * 登录实现
     * 此刻会看到，具体的一些业务逻辑都在UserLoginPresenter层去做处理
     */
    public void login() {
        userLoginView.showLoading();
        userBiz.userLogin(userLoginView.getUserName(), userLoginView.getPassword(),
            new OnLoginListener() {
                @Override
                public void loginSuccess(final UserBean userBean) {
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            userLoginView.toPersonCenter(userBean);
                            userLoginView.hideLoading();
                        }
                    });
                }


                @Override
                public void loginFiled() {
                    mHandler.post(new Runnable() {
                        @Override public void run() {
                            userLoginView.showFailedError();
                            userLoginView.hideLoading();
                        }
                    });
                }
            });
    }


    public void clear() {
        userLoginView.clearUserName();
        userLoginView.clearPassword();
    }

}

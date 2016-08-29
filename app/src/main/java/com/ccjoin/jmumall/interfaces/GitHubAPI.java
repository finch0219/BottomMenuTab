package com.ccjoin.jmumall.interfaces;

import com.ccjoin.jmumall.mvp.model.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author liuzhang
 * @description 声明GitHub的API
 * @date 2016年08月24日 13:19
 */
public interface GitHubAPI {

    @GET("users/{user}")
    Call<User> getUserInfo(@Path("user") String user);

}

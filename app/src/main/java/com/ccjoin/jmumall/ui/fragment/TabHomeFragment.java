package com.ccjoin.jmumall.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.ccjoin.jmumall.R;
import com.ccjoin.jmumall.mvp.model.User;
import com.ccjoin.jmumall.interfaces.GitHubAPI;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Home tab
 */
public class TabHomeFragment extends Fragment {

    @BindView(R.id.btn_getUserInfo) Button mGetUserInfoBtn;
    @BindView(R.id.tv_show_userInfo) TextView mShowUserInfoTv;

    private Unbinder unbinder;


    public TabHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_home, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @OnClick(R.id.btn_getUserInfo)
    public void onAction() {

        sendGetUserInfoRequest();

    }


    public void sendGetUserInfoRequest() {

        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        //获取GitHub的API
        GitHubAPI gitHubAPI = retrofit.create(GitHubAPI.class);

        //异步调用
        Call<User> userCall = gitHubAPI.getUserInfo("finch0219");
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                mShowUserInfoTv.setText(user.toString());
            }


            @Override
            public void onFailure(Call<User> call, Throwable t) {
                if (call.isCanceled()) {
                    mShowUserInfoTv.setText("the call is canceled " + toString());
                } else {
                    mShowUserInfoTv.setText(t.toString());
                }
            }
        });

    }


    @Override public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

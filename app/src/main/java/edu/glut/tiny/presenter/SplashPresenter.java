package edu.glut.tiny.presenter;

import com.hyphenate.chat.EMClient;

import edu.glut.tiny.contract.SplashContract;

public class SplashPresenter implements SplashContract.Presenter {

    private SplashContract.View view;

    public SplashPresenter(SplashContract.View view) {
        this.view = view;
    }

    @Override
    public void checkLoginStatus() {
        if (isLoggedIn())
            view.onLoggedIn();
        else
            view.onNotLoggedIn();
    }

    //是否登录到服务器
    private boolean isLoggedIn() {
        return EMClient.getInstance().isLoggedInBefore();
    }
}

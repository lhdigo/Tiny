package edu.glut.tiny.contract;

import edu.glut.tiny.presenter.BasePresenter;

/**
 * 登录界面MVP协议接口
 * */
public interface LoginContract {

    interface Presenter extends BasePresenter {
        public boolean login(String username, String password);
    }

    interface View {
        boolean onUserNameError();
        boolean onPasswordError();
        boolean onStartLogin();
        boolean onLoginSuccess();
        boolean onLoginFailed();
    }
}

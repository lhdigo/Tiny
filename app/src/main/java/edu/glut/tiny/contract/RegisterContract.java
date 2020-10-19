package edu.glut.tiny.contract;

import edu.glut.tiny.presenter.BasePresenter;


public interface RegisterContract {

    interface Presenter extends BasePresenter {
        boolean register(String username, String password, String confirmPassword);
    }

    interface View {
        void onUserNameError();
        void onPasswordError();
        void onConfirmPasswordError();
        void onStartRegister();
        void onRegisterSuccess();
        void onRegisterFailed();
    }
}

package edu.glut.tiny.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import edu.glut.tiny.ui.MainActivity;
import edu.glut.tiny.R;
import edu.glut.tiny.contract.SplashContract;
import edu.glut.tiny.presenter.SplashPresenter;

public class SplashActivity extends BaseActivity implements SplashContract.View {

    private final static long DELAY = 2000;   //延迟时间
    private final SplashPresenter presenter = new SplashPresenter(this);

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_splash;
    }


    @Override
    public void init() {
        super.init();
        presenter.checkLoginStatus();
    }

    /*
    * 延迟两秒跳转到登录界面
    * */
    @Override
    public void onNotLoggedIn() {
        new Handler(Looper.getMainLooper()).postDelayed( () ->
        {
            Intent login = new Intent(SplashActivity.this, LoginActivity.class);
            login.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(login);
            finish();
        },DELAY);
    }

    /*
    * 直接跳转到主界面
    * */
    @Override
    public void onLoggedIn() {
        Intent main = new Intent(SplashActivity.this, MainActivity.class);
        main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(main);
        finish();
    }
}
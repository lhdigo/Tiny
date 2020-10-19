package edu.glut.tiny.contract;

import java.util.List;

import edu.glut.tiny.data.entity.Contacts;
import edu.glut.tiny.presenter.BasePresenter;


public interface MainContract {

    interface Presenter extends BasePresenter {
        List<Contacts> search(String key);
    }

    interface View {
        void onSearchSuccess();
        void onSearchFailed();
    }
}

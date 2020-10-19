package edu.glut.tiny.contract;

import edu.glut.tiny.presenter.BasePresenter;

public interface AddContactsContract {

    interface Presenter extends BasePresenter {
        void search(String key);
    }

    interface View{
        void onAddContactsSuccess();
        void onAddContactsFailed();
    }
}

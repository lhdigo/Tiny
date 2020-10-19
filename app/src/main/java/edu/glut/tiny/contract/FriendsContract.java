package edu.glut.tiny.contract;

import edu.glut.tiny.presenter.BasePresenter;

public interface FriendsContract {

    interface Presenter extends BasePresenter {
        void loadFriends();
        void loadFriendsFromDB();
    }

    interface View{
        void onLoadFriendsSuccess();
        void onLoadFriendsFailed();

        void loadFriendsFromDBSuccess();
    }
}

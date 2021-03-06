package edu.glut.tiny.presenter;

import android.content.Context;

import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;

import java.util.List;

import edu.glut.tiny.contract.FriendsContract;
import edu.glut.tiny.data.AppDatabase;
import edu.glut.tiny.data.dao.ContactsDao;
import edu.glut.tiny.data.entity.Contacts;
import edu.glut.tiny.data.item.FriendsListItem;

public class FriendsPresenter implements FriendsContract.Presenter {

    private FriendsContract.View view;
    private List<FriendsListItem> friendsListItems = new ArrayList<>();
    private Context context;

    public FriendsPresenter(FriendsContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    public List<FriendsListItem> getFriendsListItems() {
        return friendsListItems;
    }

    private List<String> userList;


    /**
     * 从环信服务器获取好友信息
     */
    @Override
    public void loadFriends() {
        friendsListItems.clear();

        Thread t = new Thread(() -> {
            try {
                userList = EMClient.getInstance().contactManager().getAllContactsFromServer();
                for (String s : userList) {
                    friendsListItems.add(new FriendsListItem(s, s.toUpperCase().charAt(0), false));
                }
                sortFriends(friendsListItems);
                saveFriendsIntoDb(friendsListItems, context);
                System.out.println(userList);
                System.out.println(friendsListItems);
                uiThread(() -> view.onLoadFriendsSuccess());
            } catch (HyphenateException e) {
                e.printStackTrace();
                uiThread(() -> view.onLoadFriendsFailed());
            }
        });
        t.start();

    }

    /**
     * 从本地数据库获取好友信息
     */
    @Override
    public void loadFriendsFromDB() {

        friendsListItems.clear();
        Thread thread = new Thread(() -> {
            ContactsDao contactsDao = AppDatabase.getInstance(context).getContactsDao();
            List<Contacts> contacts = contactsDao.selectAll();

            if (!contacts.isEmpty()) {
                for (Contacts contact : contacts) {
                    friendsListItems.add(new FriendsListItem(contact.getContactsFriendUsername(),
                            contact.getContactsFriendUsername().toUpperCase().charAt(0),
                            false));
                }
                sortFriends(friendsListItems);
                uiThread(() -> view.loadFriendsFromDBSuccess());
            } else {
                //如果本地数据库为空  则联网查询好友
                loadFriends();
            }

        });
        thread.start();
    }

    /**
     * 按首字母排序 并且 根据确定是否显示首字母
     *
     * @param friendsListItems 准备要排序的
     */
    void sortFriends(List<FriendsListItem> friendsListItems) {
        friendsListItems.sort((o1, o2) -> o1.getUserName().compareTo(o2.getUserName()));

        for (int i = 0; i < friendsListItems.size(); i++) {
            //1.若是第一个 显示      2.若前一个首字母和当前首字母不同 显示
            if (i == 0 || friendsListItems.get(i).getFirstLetter() != friendsListItems.get(i - 1).getFirstLetter()) {
                friendsListItems.get(i).setShowFirstLetter(true);
            }
        }
    }

    /**
     * 保存好友列表信息到本地数据库
     * @param friendsListItems
     * @param context
     */
    void saveFriendsIntoDb(List<FriendsListItem> friendsListItems, Context context) {

        ContactsDao contactsDao = AppDatabase.getInstance(context).getContactsDao();
        contactsDao.deleteAll();
//      contactsDao.updateContactsSeq();
        for (int i = 0; i < friendsListItems.size(); i++) {
            String userName = friendsListItems.get(i).getUserName();
            contactsDao.insertInto(userName);
        }
    }


}

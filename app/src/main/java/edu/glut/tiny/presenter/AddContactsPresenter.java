package edu.glut.tiny.presenter;

import android.content.Context;
import android.util.Log;

import com.hyphenate.chat.EMClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import edu.glut.tiny.contract.AddContactsContract;
import edu.glut.tiny.data.AppDatabase;
import edu.glut.tiny.data.entity.ContactItem;
import edu.glut.tiny.data.entity.Contacts;
import edu.glut.tiny.data.entity.User;


public class AddContactsPresenter implements AddContactsContract.Presenter {

    private AddContactsContract.View view;
    private List<ContactItem> data = new ArrayList<>();
    private Context context;

    public List<ContactItem> getData() {
        return data;
    }

    public AddContactsPresenter(AddContactsContract.View view,Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void search(String key) {
        BmobQuery<User> search = new BmobQuery<>();
        try {
            List<String> localData = loadLocalContact();
            search.addWhereContains("username",key)
                    .addWhereNotEqualTo("username",EMClient.getInstance().getCurrentUser());
            search.findObjects(new FindListener<User>() {
                @Override
                public void done(List<User> list, BmobException e) {
                    if (e == null) {
                        list.forEach(user -> {
                            boolean flag = false;
                            for (String username : localData) {
                                if (user.getUsername().equals(username))
                                    flag = true;
                            }
                            ContactItem item =
                                    new ContactItem(user.getUsername(),user.getCreatedAt(),flag);
                            data.add(item);
                        });
                        uiThread(()-> view.onAddContactsSuccess());
                    }
                }
            });


        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<String> loadLocalContact() throws ExecutionException, InterruptedException {
        FutureTask<List<String>> futureTask = new FutureTask<>(new Callable<List<String>>() {
            @Override
            public List<String> call() throws Exception {
                List<String> username = new ArrayList<>();
                List<Contacts> contactsList = AppDatabase.getInstance(context)
                        .getContactsDao()
                        .selectAll();
                Log.d(this.getClass().getSimpleName(), "search: " + contactsList);

                contactsList.forEach(contacts -> username.add(contacts.getContactsFriendUsername()));
                return username;
            }
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        return futureTask.get();
    }
}

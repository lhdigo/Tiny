package edu.glut.tiny.presenter;

import android.content.Context;
import android.util.Log;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import edu.glut.tiny.contract.MainContract;
import edu.glut.tiny.data.AppDatabase;
import edu.glut.tiny.data.entity.Contacts;


public class MainPresenter implements MainContract.Presenter {
    private MainContract.View view;
    private Context context;

    public MainPresenter(MainContract.View view, Context context) {
        this.view = view;
        this.context = context;
    }

    @Override
    public List<Contacts> search(String key) {
        Log.d(this.toString(), "search: "+key);
        FutureTask<List<Contacts>> futureTask = new FutureTask<>(new Callable<List<Contacts>>() {
            @Override
            public List<Contacts> call() throws Exception {
                List<Contacts> contactsList = AppDatabase.getInstance(context)
                        .getContactsDao()
                        .selectContactsByUsername(key);
                Log.d(this.getClass().getSimpleName(), "search: " + contactsList);
                return contactsList;
            }
        });
        Thread thread = new Thread(futureTask);
        thread.start();
        try {
            List<Contacts> data = futureTask.get();
            view.onSearchSuccess();
            return  data;
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            view.onSearchFailed();
        }
        return null;
    }
}

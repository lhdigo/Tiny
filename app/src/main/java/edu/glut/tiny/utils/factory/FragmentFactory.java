package edu.glut.tiny.utils.factory;

import androidx.fragment.app.Fragment;

import java.util.HashMap;
import java.util.Map;

import edu.glut.tiny.R;
import edu.glut.tiny.ui.fragment.AccountFragment;
import edu.glut.tiny.ui.fragment.ConversationFragment;
import edu.glut.tiny.ui.fragment.FriendsFragment;


public class FragmentFactory {
    private FragmentFactory(){}
    private static volatile Map<Integer,Fragment> instances = new HashMap<>();

    public static Fragment getInstance(int id) {
        if (!instances.containsKey(id) ) {
            synchronized (FragmentFactory.class) {
                if (!instances.containsKey(id) ) {
                    switch (id){
                        case R.id.page_account:
                            instances.put(R.id.page_account,new AccountFragment());
                            break;
                        case R.id.page_friends:
                            instances.put(R.id.page_friends,new FriendsFragment());
                            break;
                        case R.id.page_message:
                           instances.put(R.id.page_message,new ConversationFragment());
                            break;
                    }
                }
            }
        }
        return instances.get(id);
    }

    public static Map<Integer, Fragment> getInstances() {
        return instances;
    }
}

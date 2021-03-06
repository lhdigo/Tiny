package edu.glut.tiny.ui.fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.glut.tiny.R;
import edu.glut.tiny.adapter.ConversationListAdapter;

import edu.glut.tiny.adapter.EMMessageListenerAdapter;
import edu.glut.tiny.ui.MainActivity;


public class ConversationFragment extends BaseFragment {

    @Override
    protected int setLayoutResourceId() {
        return R.layout.fragment_conversation;
    }

    private RecyclerView recyclerView;
    private List<EMConversation> conversations = new ArrayList<>();

    private EMMessageListenerAdapter adapter;

    @Override
    protected void init() {
        super.init();

        adapter =new EMMessageListenerAdapter(){
            @Override
            public void onMessageReceived(List<EMMessage> list) {
                loadConversations();
            }
        };
        MainActivity.getMaterialToolbar().setTitle(getString(R.string.text_label_conversation));
        recyclerView = mRootView.findViewById(R.id.conversation_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new ConversationListAdapter(context,conversations));

        //监听器
        EMClient.getInstance().chatManager().addMessageListener(adapter);

//        loadConversations();

    }

    private void loadConversations() {

        conversations.clear();
        new Thread(() -> {
            Map<String, EMConversation> allConversations = EMClient.getInstance().chatManager().getAllConversations();
            conversations.addAll((allConversations.values()));
            conversations.sort((o1, o2) -> {
                long msgTimeO1 = o1.getLastMessage().getMsgTime();
                long msgTimeO2 = o2.getLastMessage().getMsgTime();
                if (msgTimeO1==msgTimeO2){
                    return 0;
                }else if(msgTimeO1>msgTimeO2){
                    return -1;
                }else {
                    return 1;
                }
            });
            for (int i = 0; i < conversations.size(); i++) {
                if (conversations.get(i).getExtField().equals("deleted")){
                    conversations.remove(i);
                }
            }
           uiThread(() -> {
               recyclerView.getAdapter().notifyDataSetChanged();
           });
        }).start();

    }

    @Override
    public void onResume() {
        super.onResume();
        conversations.clear();
        loadConversations();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatManager().removeMessageListener(adapter);
    }
}
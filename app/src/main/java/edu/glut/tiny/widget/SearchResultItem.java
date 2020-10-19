package edu.glut.tiny.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import edu.glut.tiny.R;
import edu.glut.tiny.data.entity.Contacts;


public class SearchResultItem extends RelativeLayout {
    private TextView username;
    public SearchResultItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.search_result_item,this);
        username = findViewById(R.id.search_list_label_username);
    }

    public void bindView(Contacts contacts) {
        username.setText(contacts.getContactsFriendUsername());
    }
}

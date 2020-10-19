package edu.glut.tiny.ui.activity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.glut.tiny.R;
import edu.glut.tiny.adapter.SearchResultItemAdapter;
import edu.glut.tiny.data.entity.Contacts;


public class SearchableActivity extends BaseActivity {

    private RecyclerView listView;

    @Override
    public void init() {
        super.init();
        listView = findViewById(R.id.search_result);
        Bundle search = getIntent().getExtras();
        List<Contacts> searchData = (List<Contacts>) search.getSerializable("searchData");
        listView.setHasFixedSize(true);
        listView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listView.setAdapter(new SearchResultItemAdapter(getApplicationContext(),searchData));
    }

    @Override
    public int getLayoutResourceId() {
        return R.layout.activity_search;
    }
}

package com.example.applicationapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<News> newsArrayList;
    MyAdapter myAdapter;
    String[] newHeading;
    int[] imageResourceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        newsArrayList = new ArrayList<News>();

        newHeading = new String[]{
                "atletism text mainActivity",
                "basket text mainActivity",
                "football text mainActivity","innot text mainActivity",
                "tennis text mainActivity",
                "volley text mainActivity"
        };
        imageResourceId = new int[]{
                R.drawable.atletism,
                R.drawable.basket,
                R.drawable.forbal,
                R.drawable.inot,
                R.drawable.tenis,
                R.drawable.volei,

        };

        getData();
    }
    private void getData() {
        for( int i=0; i<newHeading.length; i++){
            News news = new News(newHeading[i], imageResourceId[i]);
            newsArrayList.add(news);
        }

        myAdapter = new MyAdapter(this, newsArrayList);
        recyclerView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search Here!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
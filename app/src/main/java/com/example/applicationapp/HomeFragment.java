package com.example.applicationapp;
import android.content.pm.ActivityInfo;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    ArrayList<News> newsArrayList;
    MyAdapter myAdapter;
    Button landscape,portait;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    FrameLayout frameLayout;
    MaterialToolbar toolbar;
    View homeFr;
    String[] newHeading;
    int[] imageResourceId;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        homeFr = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = homeFr.findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);

        newsArrayList = new ArrayList<News>();

        newHeading = new String[]{
                "Atletism",
                "Basketball",
                "Football",
                "Tennis",
                "Volleyball"
        };
        imageResourceId = new int[]{
                R.drawable.atletism,
                R.drawable.basket,
                R.drawable.forbal,
                R.drawable.inot,
                R.drawable.tenis,
                R.drawable.volei,

        };

        for( int i=0; i<newHeading.length; i++){
            News news = new News(newHeading[i], imageResourceId[i]);
            newsArrayList.add(news);
        }
        System.out.println("Ceva"+ newsArrayList.size());

        myAdapter = new MyAdapter(getActivity(), newsArrayList);
        recyclerView.setAdapter(myAdapter);

        myAdapter.notifyDataSetChanged();
       // getData();

        //return inflater.inflate(R.layout.fragment_home, container, false);

        landscape=homeFr.findViewById(R.id.landscape);
        portait=homeFr.findViewById(R.id.portait);

        landscape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

            }
        });

        portait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
        return homeFr;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
     //   setHasOptionsMenu(true);
        super.onCreateOptionsMenu(menu, menuInflater);
        menuInflater.inflate(R.menu.menu_item, menu);
       // getActivity().getMenuInflater().inflate(R.menu.menu_item, menu);
        MenuItem menuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) menuItem.getActionView();
       //searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search Here!");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("newText =>"+newText);
                myAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }

}

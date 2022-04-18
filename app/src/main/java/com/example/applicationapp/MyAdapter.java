package com.example.applicationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;
import java.util.Locale;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {
    Context context;
    ArrayList<News> newsArrayList;
    ArrayList<News> newsArrayListFull;

    public MyAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayListFull = newsArrayList;
        this.newsArrayList = new ArrayList<>(newsArrayListFull);
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        News news = newsArrayList.get(position);
        holder.tvHeading.setText(news.heading);
        holder.titleImage.setImageResource(news.titleImage);
    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return newsFilter;
    }
    private final Filter newsFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<News> filteredNewsList = new ArrayList<>();
            if(constraint == null || constraint.length() ==0){
                filteredNewsList.addAll(newsArrayListFull);
            }

            else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(News news: newsArrayListFull){
                    if(news.heading.toLowerCase().contains(filterPattern))
                        filteredNewsList.add(news);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredNewsList;
            results.count = filteredNewsList.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            newsArrayList.clear();
            newsArrayList.addAll((ArrayList)results.values);
            notifyDataSetChanged();

        }
    };

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvHeading;
        ShapeableImageView titleImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHeading = itemView.findViewById(R.id.tvHeading);
            titleImage = itemView.findViewById(R.id.title_image);
        }
    }
}

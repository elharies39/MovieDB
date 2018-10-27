package com.appku.elharies.moviedb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.appku.elharies.moviedb.R;
import com.appku.elharies.moviedb.listener.CustomOnItemClickListener;
import com.appku.elharies.moviedb.model.Results;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by El Haries on 9/4/2018.
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    private List<Results> results;
    private Context context;

    public RecycleViewAdapter(List<Results> results, Context context){
        this.context = context;
        this.results = results;
    }

    private List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.list_item,parent, false));
    }

    @Override
    public void onBindViewHolder(RecycleViewAdapter.ViewHolder holder, int position) {
        holder.tvTitle.setText(getResults().get(position).getTitle());
        holder.tvReleaseDate.setText(getResults().get(position).getReleaseDate());
        holder.tvVoteCount.setText(String.valueOf(getResults().get(position).getVoteCount()));
        holder.tvPopularity.setText(String.valueOf(getResults().get(position).getPopularity()));

        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500/"+getResults().get(position).getPosterPath())
                .override(350,550)
                .into(holder.ivPoster);

        holder.btnReadMore.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Segera", Toast.LENGTH_LONG).show();
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getResults().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvVoteCount;
        TextView tvReleaseDate;
        TextView tvPopularity;
        ImageView ivPoster;
        Button btnReadMore;

        private ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvVoteCount = itemView.findViewById(R.id.tv_vote_count);
            tvPopularity = itemView.findViewById(R.id.tv_popularity);
            tvReleaseDate = itemView.findViewById(R.id.tv_tgl_release);
            ivPoster = itemView.findViewById(R.id.img_poster);
            btnReadMore = itemView.findViewById(R.id.btn_detail);

        }
    }
}

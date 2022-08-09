package com.example.soccernews.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.soccernews.R;
import com.example.soccernews.databinding.NewsItemBinding;
import com.example.soccernews.domain.News;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private final List<News> news;
    private final FavoriteListener favoriteListener;

    public NewsAdapter(List<News>news, FavoriteListener favoriteListener){
        this.news = news;
        this.favoriteListener = favoriteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvDescription.setText(news.getDescription());
        Picasso.get().load(news.getImage()).into(holder.binding.ivThumbnail);
        holder.binding.btOpenLink.setOnClickListener(view ->{
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.getLink()));

            context.startActivity(i);
        });
        holder.binding.ivShare.setOnClickListener(view ->{
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(Intent.EXTRA_SUBJECT, news.getTitle());
            i.putExtra(Intent.EXTRA_TEXT, (news.getLink()));
            holder.itemView.getContext().startActivity(Intent.createChooser(i, "Share"));
        });
        holder.binding.ivFavorite.setOnClickListener(view ->{
            news.favorite = !news.favorite;
            this.favoriteListener.OnFavoite(news);
            notifyItemChanged(position);
        });
        if (news.favorite){
            holder.binding.ivFavorite.setColorFilter(context.getResources().getColor(R.color.purple_200));

        }else{
            holder.binding.ivFavorite.setColorFilter(context.getResources().getColor(R.color.teal_200));
        }
    }

    @Override
    public int getItemCount() {
        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface FavoriteListener{
        void OnFavoite(News news);
    }
}

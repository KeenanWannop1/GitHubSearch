package com.example.admin.githubsearch;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Repository> repositoryList;

    public MyAdapter(List<Repository> repositoryList) {
        this.repositoryList = repositoryList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView description;
        public TextView stars;
        public ImageView avatar;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.tvName);
            description = (TextView) view.findViewById(R.id.tvDescription);
            stars = (TextView) view.findViewById(R.id.tvStars);
            avatar = (ImageView) view.findViewById(R.id.ivAvatar);
        }
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.repository_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Repository repository = repositoryList.get(position);
        holder.name.setText(repository.getName());
        holder.description.setText(repository.getDescription());
        holder.stars.setText("\u2605" + repository.getStargazersCount());
        Glide.with(holder.itemView.getContext())
                .load(repository.getAvatarUrl())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return repositoryList.size();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap bmp = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                bmp = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}

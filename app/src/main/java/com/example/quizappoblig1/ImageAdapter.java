package com.example.quizappoblig1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private final Context context;
    private List<ImageAndText> images;


    public ImageAdapter(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageAndText imageResource = images.get(position);
        holder.imageView.setImageURI(imageResource.getImage());
        holder.imageName.setText(imageResource.getName());
        if (holder.imageView != null) {
            holder.imageView.setOnClickListener(v -> removeImage(position));
        }
    }
    public void setList(List<ImageAndText> images) {
        this.images = images;
        notifyDataSetChanged();
    }

    private void removeImage(int position) {
        images.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, images.size());
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public List<ImageAndText> getImages() {
        return images;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView imageName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.baseimage);
            imageName = itemView.findViewById(R.id.imageName);
        }
    }
}
package com.sisriego.sirmaar.variables;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class imageAdapter extends RecyclerView.Adapter<imageAdapter.ViewHolder> {

    ArrayList<Image> imagenArrayList;
    OnclickImageListener listener;
    Context context;


    public imageAdapter(ArrayList<Image> imagenArrayList, OnclickImageListener listener) {
        this.imagenArrayList = imagenArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_for_image, viewGroup, false);

        context = viewGroup.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final Image imagen = imagenArrayList.get(position);
        viewHolder.setListener(imagen,listener);

        if(imagen.getUrlImage() != null){

            RequestOptions options = new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.ALL);
            options.placeholder(R.mipmap.ic_launcher);
            options.centerCrop();

            Glide.with(context)
                    .load(imagen.getUrlImage())
                    .apply(options)
                    .into(viewHolder.image);
        }else{
            viewHolder.image.setImageDrawable(ContextCompat
                    .getDrawable(context,R.mipmap.ic_launcher_round));
        }


    }

    public void addView(Image imagen){

        if(!imagenArrayList.contains(imagen)){
            imagenArrayList.add(imagen);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return imagenArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.buttonD)
        Button buttonD;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
        void setListener(final Image imagen, final OnclickImageListener listener){
            buttonD.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickImageListener(imagen);
                }
            });
    }

}
}

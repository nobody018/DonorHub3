package com.example.donorhub.Helperfile;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.donorhub.R;
import com.example.donorhub.user.SingleRequest;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FeatuedAdapter extends RecyclerView.Adapter<FeatuedAdapter.FeaturedViewHolder> {
    ArrayList<FeaturedHelperClass> featuredLocations;
    Context context;
    itemClickListener itemClickListener;

    public FeatuedAdapter(ArrayList<FeaturedHelperClass> featuredLocations) {
        this.featuredLocations = featuredLocations;
        this.itemClickListener = itemClickListener;
    }
    
    public void setFilteredList(ArrayList<FeaturedHelperClass> filteredList){
        this.featuredLocations = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);
        FeaturedViewHolder featuredViewHolder = new FeaturedViewHolder(view, itemClickListener);
        return featuredViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        FeaturedHelperClass featuredHelperClass = featuredLocations.get(position);

        String imageuri = null;
        imageuri = featuredHelperClass.getNgo_image();

        Picasso.get().load(imageuri).into(holder.image);




        holder.title.setText(featuredHelperClass.getNgo_name());
        holder.desc.setText(featuredHelperClass.getDescription());
        holder.phoneNo.setText(featuredHelperClass.getPhoneNo());



    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView title, desc, contactUs, phoneNo;
         itemClickListener itemClickListener;

        String phoneNo_;



        public FeaturedViewHolder(@NonNull View itemView, itemClickListener itemClickListener) {
            super(itemView);

            image = itemView.findViewById(R.id.ngo_img);
            title = itemView.findViewById(R.id.ngo_name);
            desc = itemView.findViewById(R.id.desc);
            phoneNo = itemView.findViewById(R.id.phoneNo);





        }

        @Override
        public void onClick(View view) {

        }
    }
    public interface itemClickListener{
        void onItemcheck(int position);
    }


}

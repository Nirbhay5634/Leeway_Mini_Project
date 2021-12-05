package com.leeway.freecourses.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leeway.freecourses.GFGAndroidStudio.AndroidStudioGFG;
import com.leeway.freecourses.GFGDsaStriver.DsaStriverGFG;
import com.leeway.freecourses.GFGJavaAppDev.JavaAppDevGFG;
import com.leeway.freecourses.GFGJavaBackend.JavaBackendGFG;
import com.leeway.freecourses.GFGcp.CpGFG;
import com.leeway.freecourses.HelperClass.CategoriesHelperClass;
import com.leeway.freecourses.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder> {

    ArrayList<CategoriesHelperClass> categoriesLocations;

    public CategoriesAdapter(ArrayList<CategoriesHelperClass> categoriesLocations){
        this.categoriesLocations = categoriesLocations;
    }

    @NonNull
    @Override
    public CategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design,parent,false);
        CategoriesViewHolder categoriesViewHolder = new CategoriesViewHolder(view);
        return categoriesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesViewHolder holder, int position) {

        CategoriesHelperClass categoriesHelperClass = categoriesLocations.get(position);

        holder.image.setImageResource(categoriesHelperClass.getImage());
        holder.title.setText(categoriesHelperClass.getTitle());
        holder.desc.setText(categoriesHelperClass.getDesc());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()){
                    case 0:
                        v.getContext().startActivity(new Intent(v.getContext(), JavaAppDevGFG.class));
                        break;
                    case 1:
                        v.getContext().startActivity(new Intent(v.getContext(), CpGFG.class));
                        break;
                    case 2:
                        v.getContext().startActivity(new Intent(v.getContext(), DsaStriverGFG.class));
                        break;
                    case 3:
                        v.getContext().startActivity(new Intent(v.getContext(), AndroidStudioGFG.class));
                        break;
                    case 4:
                        v.getContext().startActivity(new Intent(v.getContext(), JavaBackendGFG.class));
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return categoriesLocations.size();
    }

    public static class CategoriesViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;

        ImageView image;
        TextView title,desc;

        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.categories_dsa_img);
            title = itemView.findViewById(R.id.categories_dsa_title);
            desc = itemView.findViewById(R.id.categories_dsa_self_placed_desc);
            relativeLayout = itemView.findViewById(R.id.categories_relative_layout);

        }
    }
}

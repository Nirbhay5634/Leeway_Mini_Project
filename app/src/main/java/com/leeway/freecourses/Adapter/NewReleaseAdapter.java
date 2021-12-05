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
import com.leeway.freecourses.HelperClass.NewReleaseHelperClass;
import com.leeway.freecourses.R;

import java.util.ArrayList;

public class NewReleaseAdapter extends RecyclerView.Adapter<NewReleaseAdapter.NewReleaseViewHolder> {

    ArrayList<NewReleaseHelperClass> newReleaseLocations;

    public NewReleaseAdapter(ArrayList<NewReleaseHelperClass> newReleaseLocations){
        this.newReleaseLocations = newReleaseLocations;
    }

    @NonNull
    @Override
    public NewReleaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.new_release_card_design,parent,false);
        NewReleaseViewHolder newReleaseViewHolder = new NewReleaseViewHolder(view);
        return newReleaseViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewReleaseViewHolder holder, int position) {

        NewReleaseHelperClass newReleaseHelperClass = newReleaseLocations.get(position);

        holder.image.setImageResource(newReleaseHelperClass.getImage());
        holder.title.setText(newReleaseHelperClass.getTitle());

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
        return newReleaseLocations.size();
    }

    public static class NewReleaseViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;
        ImageView image;
        TextView title;

        public NewReleaseViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.nr_image_view);
            title = itemView.findViewById(R.id.nr_text_view_title);
            relativeLayout = itemView.findViewById(R.id.nr_relative_layout);
        }
    }
}

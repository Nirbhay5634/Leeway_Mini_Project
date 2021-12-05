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
import com.leeway.freecourses.HelperClass.MostViewedHelperClass;
import com.leeway.freecourses.R;

import java.util.ArrayList;

public class MostViewedAdapter extends RecyclerView.Adapter<MostViewedAdapter.MostViewedViewHolder> {


    ArrayList<MostViewedHelperClass> mostViewedLocations;

    public MostViewedAdapter(ArrayList<MostViewedHelperClass> mostViewedLocations){
        this.mostViewedLocations = mostViewedLocations;
    }

    @NonNull
    @Override
    public MostViewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.most_viewed_card_design,parent,false);
        MostViewedViewHolder mostViewedViewHolder = new MostViewedViewHolder(view);
        return mostViewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostViewedViewHolder holder, int position) {

        MostViewedHelperClass mostViewedHelperClass = mostViewedLocations.get(position);

        holder.image.setImageResource(mostViewedHelperClass.getImage());
        holder.title.setText(mostViewedHelperClass.getTitle());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (holder.getAdapterPosition()){
                    case 0:
                        v.getContext().startActivity(new Intent(v.getContext(), AndroidStudioGFG.class));
                        break;
                    case 1:
                        v.getContext().startActivity(new Intent(v.getContext(), JavaBackendGFG.class));
                        break;
                    case 2:
                        v.getContext().startActivity(new Intent(v.getContext(), CpGFG.class));
                        break;
                    case 3:
                        v.getContext().startActivity(new Intent(v.getContext(), JavaAppDevGFG.class));
                        break;
                    case 4:
                        v.getContext().startActivity(new Intent(v.getContext(), DsaStriverGFG.class));
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mostViewedLocations.size();
    }


    public static class MostViewedViewHolder extends RecyclerView.ViewHolder{

        RelativeLayout relativeLayout;

        ImageView image;
        TextView title;

        public MostViewedViewHolder(@NonNull View itemView) {
            super(itemView);

            //Hooks
            image = itemView.findViewById(R.id.mv_image_view);
            title = itemView.findViewById(R.id.mv_text_view_title);
            relativeLayout = itemView.findViewById(R.id.mv_relative_layout);

        }

    }
}

package com.example.demoproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.demoproject.R;
import com.example.demoproject.model.ResultItem;

import java.util.ArrayList;

public class ProfileNamesAdapter extends RecyclerView.Adapter<ProfileNamesAdapter.ViewHolder> {

    private ArrayList<ResultItem> profilesNames;
    private Context context;

    public ProfileNamesAdapter(Context context, ArrayList<ResultItem> profilesNames) {
        this.context = context;
        this.profilesNames = profilesNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.activity_profile_names, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.firstName.setText(profilesNames.get(position).getFirstName());
        holder.lastName.setText(profilesNames.get(position).getLastName());
        holder.gender.setText(profilesNames.get(position).getGender());
    }

    @Override
    public int getItemCount() {
        return profilesNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView firstName;
        private TextView lastName;
        private TextView gender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firstName = itemView.findViewById(R.id.firstName);
            lastName = itemView.findViewById(R.id.lastName);
            gender = itemView.findViewById(R.id.gender);
        }
    }
}

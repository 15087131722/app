package com.example.im.controller.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.R;
import com.example.im.controller.activity.model.Experience;

import java.util.ArrayList;
import java.util.List;

public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder> {

    private List<Experience> experienceList;

    public ExperienceAdapter(List<Experience> experienceList) {
        this.experienceList = experienceList;
    }

    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_experience, parent, false);
        return new ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        Experience experience = experienceList.get(position);
        holder.etJobTitle.setText(experience.getJobTitle());
        holder.etJobDescription.setText(experience.getJobDescription());
    }

    @Override
    public int getItemCount() {
        return experienceList.size();
    }

    // 获取工作经历列表
    public List<Experience> getExperiences() {
        List<Experience> experiences = new ArrayList<>();
        for (int i = 0; i < getItemCount(); i++) {
            Experience experience = experienceList.get(i);
            String jobTitle = experience.getJobTitle();
            String jobDescription = experience.getJobDescription();
            experiences.add(new Experience(jobTitle, jobDescription));
        }
        return experiences;
    }

    public static class ExperienceViewHolder extends RecyclerView.ViewHolder {
        EditText etJobTitle;
        EditText etJobDescription;

        public ExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            etJobTitle = itemView.findViewById(R.id.et_job_title);
            etJobDescription = itemView.findViewById(R.id.et_job_description);
        }
    }

}

package com.app.uas_quran;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.uas_quran.Model.AudioModel.AudioFilesItem;
import com.app.uas_quran.Model.SurahModel.ChaptersItem;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<ChaptersItem> surah;
    private List<AudioFilesItem> audio;
    private Context context;
    private SharedPreferences sharedPreferences;

    public MainAdapter(List<ChaptersItem> results, List<AudioFilesItem> result, MainActivity mainActivity) {
        this.surah = results;
        this.audio = result;
        this.context = mainActivity;
        sharedPreferences = context.getSharedPreferences("SurahPrefs", Context.MODE_PRIVATE);
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.MainViewHolder holder, int position) {
        ChaptersItem result = surah.get(position);
        AudioFilesItem aud = audio.get(position);

        holder.textViewSurahLatin.setText(result.getNameSimple());
        holder.textViewSurahArab.setText(result.getNameArabic());
        holder.textViewTerjemahanSurah.setText(result.getTranslatedName().getName());
        holder.textViewSurahNomor.setText(String.valueOf(result.getId()));

        // Load checkbox state
        boolean isChecked = sharedPreferences.getBoolean("checkbox_" + result.getId(), false);
        holder.checkBox.setChecked(isChecked);

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked1) -> {
            // Save checkbox state
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("checkbox_" + result.getId(), isChecked1);
            editor.apply();
        });

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailSurahActivity.class);

            intent.putExtra("id", result.getId());
            intent.putExtra("name", result.getNameComplex());
            intent.putExtra("verses", result.getVersesCount());
            intent.putExtra("tempat", result.getRevelationPlace());
            intent.putExtra("arabic", result.getNameArabic());
            intent.putExtra("audio_url", aud.getAudioUrl());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return surah.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSurahLatin, textViewSurahArab, textViewTerjemahanSurah, textViewSurahNomor;
        CheckBox checkBox;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewSurahNomor = itemView.findViewById(R.id.tvSurahNomor);
            textViewSurahArab = itemView.findViewById(R.id.tvSurahArab);
            textViewSurahLatin = itemView.findViewById(R.id.tvSurahLatin);
            textViewTerjemahanSurah = itemView.findViewById(R.id.tvTerjemahanSurah);
            checkBox = itemView.findViewById(R.id.circle1);
        }
    }

    public void setData(List<ChaptersItem> data, List<AudioFilesItem> data1) {
        // Surah
        surah.clear();
        surah.addAll(data);
        // Audio
        audio.clear();
        audio.addAll(data1);
        notifyDataSetChanged();
    }
}

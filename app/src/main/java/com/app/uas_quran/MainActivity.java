package com.app.uas_quran;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.app.uas_quran.MainAdapter;
import com.app.uas_quran.Model.AudioModel.Audio;
import com.app.uas_quran.Model.AudioModel.AudioFilesItem;
import com.app.uas_quran.Model.SurahModel.Chapters;
import com.app.uas_quran.Model.SurahModel.ChaptersItem;
import com.app.uas_quran.Retrofit.ApiBase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private TextView textDate;
    private RecyclerView.LayoutManager layoutManager;
    private List<AudioFilesItem> audio = new ArrayList<>();
    private List<ChaptersItem> surah = new ArrayList<>();

    List<ChaptersItem> listSurah;
    List<AudioFilesItem> listAudio;
    //menampilkan halaman utama
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getDataFromApi();
        setUpView();
        setUpRecyclerView();

        //tanggal today
        textDate = findViewById(R.id.dateDisplay);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                @SuppressLint("SimpleDateFormat")

                DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyy");

                textDate.setText(dateFormat.format(new Date()));
                //interval
                handler.postDelayed(this, 1000);

            }
        });
        //end tanggal
    }

    //mengambil data surah dari api
    private void getDataFromApi() {
        ApiBase.endpoint().getSurah().enqueue(new Callback<Chapters>() {
            @Override
            public void onResponse(@NonNull Call<Chapters> call, @NonNull Response<Chapters> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    MainActivity.this.listSurah = response.body().getChapters();
                    // Filter surah id 78 -114 (juzz 30)
                    List<ChaptersItem> filteredSurah = new ArrayList<>();
                    for (ChaptersItem surah : listSurah) {
                        if (surah.getId() >= 78 && surah.getId() <= 114) {
                            filteredSurah.add(surah);
                        }
                    }
                    MainActivity.this.listSurah = filteredSurah;
                    getDataFromApiAudio();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Chapters> call, @NonNull Throwable t) {
                Log.d("ErrorMain", t.toString());
            }
        });
    }
    //mengambil data audio surah dari api
    private void getDataFromApiAudio() {
        ApiBase.endpoint().getAudio().enqueue(new Callback<Audio>() {
            @Override
            public void onResponse(@NonNull Call<Audio> call, @NonNull Response<Audio> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    MainActivity.this.listAudio = response.body().getAudioFiles();
                    // Filter audio berdasarkan surah id 78 -114 (juzz 30)
                    List<AudioFilesItem> filteredAudio = new ArrayList<>();
                    for (AudioFilesItem audio : listAudio) {
                        if (audio.getChapterId() >= 78 && audio.getChapterId() <= 114) {
                            filteredAudio.add(audio);
                        }
                    }
                    MainActivity.this.listAudio = filteredAudio;
                    mainAdapter.setData(listSurah, listAudio);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Audio> call, @NonNull Throwable t) {
                Log.d("ErrorMain", t.toString());
            }
        });
    }
    // set widget surah
    private void setUpRecyclerView() {
        mainAdapter = new MainAdapter(surah, audio, this);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mainAdapter);
    }

    private void setUpView() {
        recyclerView = findViewById(R.id.rvSurah);
    }
}

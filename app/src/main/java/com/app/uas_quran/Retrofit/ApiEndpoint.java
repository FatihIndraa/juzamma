package com.app.uas_quran.Retrofit;

import com.app.uas_quran.Model.AudioModel.Audio;
import com.app.uas_quran.Model.AyatModel.Verses;
import com.app.uas_quran.Model.SurahModel.Chapters;
import com.app.uas_quran.Model.Terjemahan.Indo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiEndpoint {
    @GET("chapters?language=id")
    Call<Chapters> getSurah();
    @GET("chapter_recitations/33?")
    Call<Audio> getAudio();
    @GET("quran/verses/uthmani?")
    Call<Verses> getAyat (@Query("chapter_number") int id);
    @GET("quran/translations/33?")
    Call<Indo> getIndo (@Query("chapter_number") int id);

}

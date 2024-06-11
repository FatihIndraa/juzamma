package com.app.uas_quran.Model.AudioModel;

import com.google.gson.annotations.SerializedName;

public class AudioFilesItem{

	@SerializedName("format")
	private String format;

	@SerializedName("audio_url")
	private String audioUrl;

	@SerializedName("id")
	private int id;

	@SerializedName("chapter_id")
	private int chapterId;

	@SerializedName("file_size")
	private Object fileSize;

	public String getFormat(){
		return format;
	}

	public String getAudioUrl(){
		return audioUrl;
	}

	public int getId(){
		return id;
	}

	public int getChapterId(){
		return chapterId;
	}

	public Object getFileSize(){
		return fileSize;
	}

	@Override
 	public String toString(){
		return 
			"AudioFilesItem{" + 
			"format = '" + format + '\'' + 
			",audio_url = '" + audioUrl + '\'' + 
			",id = '" + id + '\'' + 
			",chapter_id = '" + chapterId + '\'' + 
			",file_size = '" + fileSize + '\'' + 
			"}";
		}
}
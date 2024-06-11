package com.app.uas_quran.Model.Terjemahan;

import com.google.gson.annotations.SerializedName;

public class TranslationsItem{

	@SerializedName("resource_id")
	private int resourceId;

	@SerializedName("text")
	private String text;

	public int getResourceId(){
		return resourceId;
	}

	public String getText(){
		return text;
	}

	@Override
 	public String toString(){
		return 
			"TranslationsItem{" + 
			"resource_id = '" + resourceId + '\'' + 
			",text = '" + text + '\'' + 
			"}";
		}
}
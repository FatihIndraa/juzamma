package com.app.uas_quran.Model.Terjemahan;

import com.google.gson.annotations.SerializedName;

public class Meta{

	@SerializedName("author_name")
	private String authorName;

	@SerializedName("filters")
	private Filters filters;

	@SerializedName("translation_name")
	private String translationName;

	public String getAuthorName(){
		return authorName;
	}

	public Filters getFilters(){
		return filters;
	}

	public String getTranslationName(){
		return translationName;
	}

	@Override
 	public String toString(){
		return 
			"Meta{" + 
			"author_name = '" + authorName + '\'' + 
			",filters = '" + filters + '\'' + 
			",translation_name = '" + translationName + '\'' + 
			"}";
		}
}
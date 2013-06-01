package com.rhok.donatemyschoolstuff.model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Data {
	@SerializedName("name?")
	public String name ;

	@SerializedName("id?")
	public String id;

	@SerializedName("category?")
	public String category;

	@SerializedName("lat?")
	public String latitude;

	@SerializedName("lon?")
	public String longitude;

	@SerializedName("desc?")
	public String description;

	@SerializedName("tags")
	public List<String> tags;

	@SerializedName("image")
	public String image;

}

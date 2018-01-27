package com.sekulicd.emasters.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repository {
	
	@SerializedName("full_name")
    @Expose
    private String fullName;
	
	@SerializedName("description")
    @Expose
    private String description;	
	
	@SerializedName("owner")
    @Expose
    private User user;	
	
	@SerializedName("stargazers_count")
    @Expose
	private String star;
	
	@SerializedName("forks")
    @Expose
	private String forks;
	
	@SerializedName("pulls_url")
    @Expose
	private String pulls;

	public String getFullName() {
		return fullName;
	}

	public String getStars() {
		return star;
	}
	
	public User getUser() {
		return user;
	}

	public String getPulls() {
		return pulls;
	}

	public String getDescription() {
		return description;
	}

	public String getForks() {
		return forks;
	}	
	
	
}

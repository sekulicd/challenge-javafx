package com.sekulicd.emasters.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pull {
	
	@SerializedName("title")
    @Expose
	private String title;
	
	@SerializedName("created_at")
    @Expose
	private String created_at;
	
	@SerializedName("body")
    @Expose
	private String body;
	
	@SerializedName("html_url")
    @Expose
    private String htmlUrl;
	
	@SerializedName("user")
    @Expose
    private User user;	

	public String getTitle() {
		return title;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getBody() {
		return body;
	}

	public String getHtmlUrl() {
		return htmlUrl;
	}
	
	public User getUser() {
		return user;
	}
	
}

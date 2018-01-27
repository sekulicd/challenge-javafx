package com.sekulicd.emasters.service;

import java.util.List;

import com.sekulicd.emasters.model.Pull;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubPull {
//	  @GET("/repos/iluwatar/java-design-patterns/pulls")
//	  Call<List<Pull>> listPull();
	@GET("{fullUrl}")
	Call<List<Pull>> listPull(@Path(value = "fullUrl", encoded = true) String fullUrl);
}

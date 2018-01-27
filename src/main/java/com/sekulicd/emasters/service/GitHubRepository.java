package com.sekulicd.emasters.service;

import com.sekulicd.emasters.model.RepositoryList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubRepository {
  @GET("/search/repositories?q=language:Java&sort=stars")
  Call<RepositoryList> listRepository(@Query(value = "page", encoded = true) String page_number);
}
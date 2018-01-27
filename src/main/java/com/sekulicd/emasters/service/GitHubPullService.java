package com.sekulicd.emasters.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.sekulicd.emasters.model.Pull;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GitHubPullService extends Service<List<Pull>> {

	
	private final String GIT_HUB_URL = "https://api.github.com/";
		
	private Retrofit retrofit;
	
	private GitHubPull pullService;
	
	private String pullUrl;
	
	public GitHubPullService(){
		retrofit = new Retrofit.Builder().baseUrl(GIT_HUB_URL).addConverterFactory(GsonConverterFactory.create()).build();
		pullService = retrofit.create(GitHubPull.class);
	}

	@Override
	protected Task<List<Pull>> createTask() {
		return new Task<List<Pull>>() {
            protected List<Pull> call() 
                throws IOException, MalformedURLException {   
        		Call<List<Pull>> pullListCall = pullService.listPull(pullUrl);
        		Response<List<Pull>> pulls = pullListCall.execute();
        		List<Pull> pullListResponse = pulls.body();
        		return pullListResponse;
            }
        };
	}
	
	public void setPullUrl(String pullUrl){
		this.pullUrl = pullUrl;
	}

}

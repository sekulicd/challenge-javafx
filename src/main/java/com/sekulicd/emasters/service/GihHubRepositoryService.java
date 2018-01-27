package com.sekulicd.emasters.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.sekulicd.emasters.model.Repository;
import com.sekulicd.emasters.model.RepositoryList;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GihHubRepositoryService extends Service<List<Repository>> {
	
	private final String GIT_HUB_URL = "https://api.github.com/";
	
	private int pageNumber;
	
	private Retrofit retrofit;
	
	private GitHubRepository repositoryService;
	
	public GihHubRepositoryService(){
		retrofit = new Retrofit.Builder().baseUrl(GIT_HUB_URL).addConverterFactory(GsonConverterFactory.create()).build();
		repositoryService = retrofit.create(GitHubRepository.class);
	}
	

	@Override
	protected Task<List<Repository>> createTask() {
		return new Task<List<Repository>>() {
            protected List<Repository> call() 
                throws IOException, MalformedURLException {   
            	String pageNumberStr = Integer.toString(pageNumber);
        		Call<RepositoryList> userListCall = repositoryService.listRepository(pageNumberStr);
        		Response<RepositoryList> repos = userListCall.execute();
        		RepositoryList repoList = repos.body();
        		return repoList.getItems();
            }
        };
	}
	
	public void setPageNumber(int pageNumber){
		this.pageNumber = pageNumber;
	}

}

package com.sekulicd.emasters;

import java.io.IOException;
import java.util.List;

import com.sekulicd.emasters.service.GitHubPull;

import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Test {

	public static void main(String[] args) throws IOException {
//		Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
//				.addConverterFactory(GsonConverterFactory.create()).build();
//
//		GitHubRepositoryService service = retrofit.create(GitHubRepositoryService.class);
//		Call<RepositoryList> userListCall = service.listRepository();
//		Response<RepositoryList> repos = userListCall.execute();
//		RepositoryList repoList = repos.body();
//		for (Repository r : repoList.getItems()) {
//			System.out.println(r.getPulls() + " " + r.getUser().getLogin() + " " + r.getUser().getAvatarUrl());
//		}
//		
//		GitHubPullService servicePull = retrofit.create(GitHubPullService.class);
//		Call<List<Pull>> pullListCall = servicePull.listPull();
//		Response<List<Pull>> pulls = pullListCall.execute();
//		List<Pull> pullList = pulls.body();
//		for (Pull p : pullList) {
//			System.out.println(p.getTitle() + " " + p.getUser().getLogin() + " " + p.getUser().getAvatarUrl());
//		}
		
		String s = "https://api.github.com/repos/iluwatar/java-design-patterns/pulls{/number}";
		
		System.out.println(s.substring(0, s.indexOf("{", 0)));
	}

}

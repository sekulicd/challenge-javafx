package com.sekulicd.emasters.form.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sekulicd.emasters.model.Repository;
import com.sekulicd.emasters.model.RepositoryList;
import com.sekulicd.emasters.service.GihHubRepositoryService;
import com.sekulicd.emasters.service.GitHubRepository;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Callback;
import retrofit2.Call;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RepositorySceneController implements Initializable {
    
    @FXML
    private ListView<Repository> lvRepository;
    
    @FXML
    private Button btnPrev, btnNext, btnRefresh;
    
    private ObservableList<Repository> repositoryList = FXCollections.observableArrayList();
    
    private static int pageNumber = 1;
    
    private GihHubRepositoryService repositoryService;
    
    @FXML
    void onRefresh(ActionEvent event) throws IOException {
    	lvRepository.refresh();
    }
    
    @FXML
    void onPrev(ActionEvent event) throws IOException {
    	if(pageNumber > 1){
    		//repositoryList.clear();
    		fetchGitHubRepositories(--pageNumber);
    	}
    }
    
    @FXML
    void onNext(ActionEvent event) throws IOException {
    	//repositoryList.clear();
    	fetchGitHubRepositories(++pageNumber);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//    	try {
//			fetchGitHubRepositories(pageNumber);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	try {
    		repositoryService = new GihHubRepositoryService();
			fetchGitHubRepositories(pageNumber);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	setListViewCell();
    	lvRepository.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Repository>() {
    		public void changed(ObservableValue<? extends Repository> observable, Repository oldValue, Repository newValue) {
    			Parent root;
				try {
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/PullScene.fxml"));     
					root = (Parent)fxmlLoader.load();    
					PullSceneController pullSceneController = fxmlLoader.getController();
					pullSceneController.setPullUrlAndFetchPullRequest(newValue.getPulls().substring(0, newValue.getPulls().indexOf("{", 0)));
					//root = FXMLLoader.load(getClass().getResource("/fxml/PullScene.fxml"));
	    		    Scene scene =  new Scene(root);
	    		    Stage stage = new Stage();
	    	        stage.setTitle("Pulls");
	    	        stage.setScene(scene);
	    	        stage.show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	System.out.println(newValue.getPulls());  
    		}
        });

    }    
    
    private void fetchGitHubRepositories(int pageNum) throws IOException{
    	System.out.println(pageNum);
    	repositoryService.setPageNumber(pageNum);;
    	repositoryService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
    		@Override
    		public void handle(WorkerStateEvent t) {
    			repositoryList.clear();
    			//System.out.println("done:" + t.getSource().getValue());
    			repositoryList.addAll((ArrayList<Repository>) t.getSource().getValue());
    			//lvRepository.
    			//lvRepository.setItems(repositoryList);
    			lvRepository.getItems().clear();
    			lvRepository.getItems().addAll(repositoryList);
    			//lvRepository.refresh();
    			repositoryService.reset();
    			System.out.println(repositoryList.size());
    		}
    	});
    	repositoryService.start();
    }
    
    
    private void setListViewCell(){
    	lvRepository.setCellFactory(getRepositoryCellFactory());
	}
    
    private Callback<ListView<Repository>, ListCell<Repository>> getRepositoryCellFactory() {
        return new Callback<ListView<Repository>, ListCell<Repository>>() {
            @Override public ListCell<Repository> call(ListView<Repository> listView) {
                return new RepositoryListViewCell();
            }
        };
    }
    
}

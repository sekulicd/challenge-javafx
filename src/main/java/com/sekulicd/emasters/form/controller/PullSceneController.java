package com.sekulicd.emasters.form.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.sekulicd.emasters.model.Pull;
import com.sekulicd.emasters.service.GitHubPullService;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class PullSceneController implements Initializable{

	@FXML
	private ListView<Pull> lvPull;

	private ObservableList<Pull> pullList = FXCollections.observableArrayList();
	
	private String pullUrl;
	
    private GitHubPullService pullService;


	@Override
	public void initialize(URL url, ResourceBundle rb) {
		pullService = new GitHubPullService();
		lvPull.setItems(pullList);
		setListViewCell();
		lvPull.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Pull>() {
			@Override
			public void changed(ObservableValue<? extends Pull> observable, Pull oldValue, Pull newValue) {
				try {
					Desktop.getDesktop().browse(new URI(newValue.getHtmlUrl()));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	private void fetchGitHubPull() throws IOException {
		pullService.setPullUrl(pullUrl);
		pullService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
    		@Override
    		public void handle(WorkerStateEvent t) {
    			lvPull.getItems().clear();
    			pullList.addAll((ArrayList<Pull>) t.getSource().getValue());
    			lvPull.getItems().addAll(pullList);
    			pullService.reset();
    		}
    	});
		pullService.start();
	}

	private void setListViewCell() {
		lvPull.setCellFactory(getPullCellFactory());
	}

	private Callback<ListView<Pull>, ListCell<Pull>> getPullCellFactory() {
		return new Callback<ListView<Pull>, ListCell<Pull>>() {
			@Override
			public ListCell<Pull> call(ListView<Pull> listView) {
				return new PullListViewCell();
			}
		};
	}
	
	public void setPullUrlAndFetchPullRequest(String pullUrl){
		this.pullUrl = pullUrl;
		try {
			fetchGitHubPull();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

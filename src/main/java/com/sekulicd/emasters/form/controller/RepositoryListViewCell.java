package com.sekulicd.emasters.form.controller;

import java.io.IOException;

import com.sekulicd.emasters.model.Repository;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class RepositoryListViewCell extends ListCell<Repository> {

	@FXML
	private Label lblRepositoryName, lblRepositoryDescription, lblForksCount, lblStarsCount, lblUsername;

	@FXML
	private ImageView ivAvatar;

	@FXML
	private AnchorPane anchorPane;

	private FXMLLoader mLLoader;

	public void updateItem(Repository repository, boolean empty) {
		super.updateItem(repository, empty);
		if (empty || repository == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/fxml/ListCellRepository.fxml"));

				mLLoader.setController(this);

				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			lblRepositoryName.setText(repository.getFullName());
			lblRepositoryDescription.setText(repository.getDescription());
			lblForksCount.setText(repository.getForks());
			lblStarsCount.setText(repository.getStars());
			lblUsername.setText(repository.getUser().getLogin());
			Image avatar = new Image(repository.getUser().getAvatarUrl());
			ivAvatar.setImage(avatar);
			setText(null);
			setGraphic(anchorPane);

		}
	}

}

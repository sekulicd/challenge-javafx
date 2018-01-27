package com.sekulicd.emasters.form.controller;

import java.io.IOException;

import com.sekulicd.emasters.model.Pull;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class PullListViewCell extends ListCell<Pull> {
	@FXML
	private Label lblPulTitle, lblPullRequestDate, lblPullBody, lblUsername;

	@FXML
	private ImageView ivAvatar;

	@FXML
	private AnchorPane anchorPane;

	private FXMLLoader mLLoader;

	public void updateItem(Pull pull, boolean empty) {
		super.updateItem(pull, empty);
		if (empty || pull == null) {
			setText(null);
			setGraphic(null);
		} else {
			if (mLLoader == null) {
				mLLoader = new FXMLLoader(getClass().getResource("/fxml/ListCellPull.fxml"));
				mLLoader.setController(this);

				try {
					mLLoader.load();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			lblPulTitle.setText(pull.getTitle());
			lblPullRequestDate.setText(pull.getCreated_at());
			//lblPullBody.setText(pull.getBody());
			lblUsername.setText(pull.getUser().getLogin());
			Image avatar = new Image(pull.getUser().getAvatarUrl());
			ivAvatar.setImage(avatar);
			setText(null);
			setGraphic(anchorPane);

		}
	}
}

package views;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import Model.Track;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TrackCell extends ListCell<Track> {
	private HBox view;
	private ImageView cover;
	private Label title;
	private Label artist;
	private Label length;
	HBox trackPane;
	VBox labelPane;
	
	public TrackCell() {
		labelPane= new VBox();
		trackPane= new HBox();
		this.view=new HBox();
		cover = new ImageView(); 
		title=new Label();
		artist= new Label();
		length= new Label();
		labelPane.getChildren().addAll(title,artist);
		trackPane.getChildren().addAll(cover,labelPane,length);
		view.getChildren().addAll(trackPane);
		view.setHgrow(trackPane, Priority.ALWAYS);
		trackPane.setHgrow(labelPane, Priority.ALWAYS);
		this.setGraphic(view);
		this.getStylesheets().add(getClass().getResource("Playlist.css").toExternalForm());
		view.getStyleClass().add("box");
		cover.setFitHeight(75);
		cover.setFitWidth(75);
		
		artist.getStyleClass().add("artist");
		title.getStyleClass().add("title");
		trackPane.getStyleClass().add("listCell");
		labelPane.getStyleClass().add("box");
		title.getStyleClass().add("title");
		artist.getStyleClass().add("artist");	
	}
	
	@Override
	public void updateItem(Track t, boolean empty) {
		super.updateItem(t, empty);
		if (t!=null) {
				try {
					cover.setImage(t.createCover());
				} catch (IOException e) {
					e.printStackTrace();
				}

				
			title.setText(t.getTitle());
			artist.setText(t.getArtist());
			length.setText(formatTime(t.getDuration()));
			this.setGraphic(view);
		} else {
			
		}
	}
	
	public String formatTime(float t){
		int min = (int) t/60000;
		int sec =((int)t/1000)%60;
		String str= ((min<10)?"0"+min:min)+":"+((sec<10)?"0"+sec:sec);
		return str;
	}
}

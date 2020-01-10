package model;

import java.io.FileInputStream;

import java.io.IOException;

import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SampleListCell extends ListCell<Sample> {
	private Pane view;
	private Label titleLabel;
	Synthesizer synth;
	
	public SampleListCell(Synthesizer s) {
		this.synth = s;
		VBox trackPane = new VBox();
		titleLabel = new Label("Title");

		trackPane.getChildren().add(titleLabel);
		
		view = new HBox();
		view.getChildren().add(trackPane);
		
		this.setGraphic(view);
	}
	
	
	@Override
	protected void updateItem(Sample t, boolean empty) {
		super.updateItem(t, empty);
		
		setText(null);
		setGraphic(null);
		if(t != null) {
			try {
				setText(t.getName());
				ImageView imageView = new ImageView();
				imageView.setImage(new Image(new FileInputStream("assets/song_icon.png")));
				
		        imageView.setFitHeight(30);
		        imageView.setFitWidth(30);

				setGraphic(imageView);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void startEdit(){
		super.startEdit();
		Sample item = this.getItem();
		
		if (item != null) {
//			mp3player.stop();
//			mp3player.setTrack(item);
//			mp3player.setCurrentTrackToAudioPlayer();
//			System.out.println("item gef�llt: " + item.getSongName() + "---- " + mp3player._trackNumber + " " +  mp3player._currentTrack.getValue().getSongName() );
//			mp3player.play();
		}
		else {
			System.out.print("Item leer");
		}
	}
}

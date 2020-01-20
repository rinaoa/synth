package model;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class SampleListCell extends ListCell<Sample>{
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
		
		setOnDragDetected(event -> {
            if (getItem() == null) {
                return;
            }
 
            Dragboard dragboard = startDragAndDrop(TransferMode.ANY);
            ClipboardContent content = new ClipboardContent();
             
            // Serialize the object
//            String cellSerialized = "";
//            try {
//                ByteArrayOutputStream bo = new ByteArrayOutputStream();
//                ObjectOutputStream so = new ObjectOutputStream(bo);
//                so.writeObject((Sample)getItem());
//                so.flush();
//                cellSerialized = bo.toString();
//            } catch (Exception e) {
//                System.err.println(e);
//            }
 
//            content.putString(cellSerialized);
            content.putString(getItem().path);
            dragboard.setContent(content);
 
            event.consume();
        });
		
		
			setOnDragDone(DragEvent::consume);
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
			synth.setSample(item);
		}
		else {
			System.out.print("Item leer");
		}
	} 
	
}
package model;

import java.io.FileInputStream;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SampleListCell extends ListCell<Sample> implements ChangeListener<Number>{
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


	@Override
	public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
	     System.out.print("....1 in changed");
        //If some kind of mice will click on your then do this
        setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent arg0) {
            	System.out.print("....mouse clicked");
                getListView().scrollTo(getIndex()); 
            }   
            
        });
        //Some body is dragging and they are on me.
        setOnDragEntered(new EventHandler<DragEvent>(){
            @Override
            public void handle(DragEvent arg0) {
            	System.out.print("....drag entered");
                if(arg0.getTransferMode() == TransferMode.COPY){
//                            String cellS = (String)arg0.getDragboard().getContent(dataFormat);
                    
//                            Object o = arg0.getDragboard().getContent(dataFormat);
//                            if(toBeDeleted == getIndex()){
//                                return;
//                            }
//                            if(toBeDeleted != -1){
//                                items.remove(toBeDeleted);
//                                toBeDeleted = -1;
//                            }
//                            if(o != null && temp != null ){
//                                if(getIndex() < items.size())                                    
//                                    items.add(getIndex(),(T)temp);
//                                else if(getIndex() == items.size())
//                                    items.add((T)temp);
//                                
//                            }
//                            
//                            ind.set(getIndex());
                }
            }

        });
        
        //Some body just went off dragging from my cell.
        setOnDragExited(new EventHandler<DragEvent>(){

            @Override
            public void handle(DragEvent arg0) {
            	System.out.print("....drag exited");
                if(arg0.getTransferMode() == TransferMode.COPY){
//                            Object o = arg0.getDragboard().getContent(dataFormat);
//                            if(o != null){
//                                setEffect(null);                          
//                                if(getIndex()<items.size())
//                                    toBeDeleted = getIndex();
//                                  
//                            }
                }
            }

        });
       
        //OMG! That mice pressed me. I need to take some action
        pressedProperty().addListener(new ChangeListener<Boolean>(){
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
            	System.out.print("....pressed property");
            	InnerShadow is = new InnerShadow();
                        is.setOffsetX(1.0);
                        is.setColor(Color.web("#666666"));
                        is.setOffsetY(1.0);                               
                        setEffect(is);                            
                if(!arg2){
					setEffect(null);    
            	}
        }});
    
        //Ok I'm off I'm Over stop dragging me man!
        setOnDragOver(new EventHandler<DragEvent>(){
            @Override
            public void handle(DragEvent event) {           
                System.out.println("Over");
                event.acceptTransferModes(TransferMode.MOVE);             
            }
        });
        
        //Hey hey hey You are dragging me! Wait I need to call somebody
        setOnDragDetected(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent event) {
                	System.out.print("....drag detected");
//                            Dragboard db = getListView().startDragAndDrop(TransferMode.MOVE);
//                         
//                            ClipboardContent content = new ClipboardContent();
//                            if(item != null)
//                                content.put(dataFormat,item.toString());             
//                            else
//                                content.put(dataFormat,"XData");             
//                            db.setContent(content); 
//                            event.consume();

                }
        });
    }
    	
}
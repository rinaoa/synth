package ui;

import javafx.collections.FXCollections;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import model.Sample;
import model.SampleListCell;
import model.Synthesizer;

public class SettingsView extends VBox{
    ListView<Sample> samplesView;
	ObservableList<Sample> observableList;
	Label lbl = new Label("Settings");
	Synthesizer synth;
	
	public SettingsView(Synthesizer s) {
		this.synth = s;
    	this.setBackground(new Background(new BackgroundFill(Color.rgb(12, 82, 10, 0.3), CornerRadii.EMPTY, Insets.EMPTY)));
    	
	    this.observableList = FXCollections.observableArrayList();		
    	observableList.clear();		
    	observableList.setAll(synth.sampleManager.samples.values());
    	     	
    	this.samplesView = new ListView<Sample>();			    
    	samplesView.getItems().addAll(observableList);		
    	samplesView.setEditable(true);	
    	
    	samplesView.setCellFactory(
    			new Callback<ListView<Sample>, ListCell<Sample>>(){		
    				@Override		
    				public ListCell<Sample> call(ListView<Sample> v){		
    					return new SampleListCell(synth);		
    				}		
    			});		
    	samplesView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);		
        samplesView.setOrientation(Orientation.VERTICAL);		
        samplesView.setPrefSize(400,370);
        
        getChildren().add(samplesView);
        
		this.setWidth(200);
		this.setHeight(500);
	}
}

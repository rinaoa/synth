package ui;

import javafx.collections.FXCollections;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
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
	Button octaveMinusBtn;
	Button octavePlusBtn;
	VBox buttonsPane;
	
	public SettingsView(Synthesizer s) {
		this.synth = s;
		this.buttonsPane = new VBox();
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
					public SampleListCell call(ListView<Sample> v){	
    					SampleListCell cell = new SampleListCell(synth);
    					return cell;		
    				}		
    			});		

    	samplesView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);		
        samplesView.setOrientation(Orientation.VERTICAL);		
        samplesView.setPrefSize(203, 300);
        samplesView.setEditable(true);
        
        this.octaveMinusBtn = new Button("Octave -");
        this.octavePlusBtn = new Button("Octave +");
       
        buttonsPane.getChildren().addAll(octavePlusBtn, octaveMinusBtn);
        getChildren().addAll(samplesView, buttonsPane);
        
        buttonsPane.setPadding(new Insets(15));
        buttonsPane.setSpacing(10);
        buttonsPane.setPrefSize(203, 200);
        octavePlusBtn.setId("octave-button"); 
        octaveMinusBtn.setId("octave-button"); 
        
        buttonsPane.setAlignment(Pos.CENTER);

        
		this.setWidth(203);
		this.setHeight(500);
	}
}

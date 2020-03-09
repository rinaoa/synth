package UI;

import javax.swing.event.ChangeEvent;

import Model.Oscillator;
import Model.Waveform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class OscillatorView extends GridPane {
	
	ChoiceBox<Waveform> choicebox;
	Oscillator osc;
//	VBox vBoxes[]= {new VBox(),new VBox(),new VBox()};
	
	Label oscLabel,toneText, toneLabel, volumeText, volumeLabel;
	RadioButton active, holdFr;

	private final int MAX_LABEL_SIZE_X = 60;
	private final int MAX_LABEL_SIZE_Y = 23;
	
	public OscillatorView(Oscillator osc) {
		setVgap(5);
		setHgap(10);
		setPadding(new Insets(10));
		this.osc = osc;
		setPrefSize(250, 100);
		oscLabel=new Label("Oscillator:");
		active = new RadioButton("Active");
		holdFr = new RadioButton("Hold Key");
		toneText= new Label("Tone:");
		volumeText= new Label("Volume:");
		toneLabel = new Label("x0.000");
		volumeLabel = new Label("100%");
		active.setTextFill(Color.ANTIQUEWHITE);
		oscLabel.setTextFill(Color.ANTIQUEWHITE);
		toneText.setTextFill(Color.ANTIQUEWHITE);
		toneLabel.setTextFill(Color.ANTIQUEWHITE);
		volumeText.setTextFill(Color.ANTIQUEWHITE);
		volumeLabel.setTextFill(Color.ANTIQUEWHITE);
		Border border = new Border(new BorderStroke(Color.ANTIQUEWHITE, BorderStrokeStyle.SOLID, null
				, new BorderWidths(1)));
		toneLabel.setBorder(border);
		volumeLabel.setBorder(border);
		toneLabel.setPadding(new Insets(2));
		volumeLabel.setPadding(new Insets(2));
		setBorder(border);
		
		ObservableList<Waveform> oList = FXCollections.observableArrayList(Waveform.Sine, Waveform.Square, Waveform.Triangle, Waveform.Saw, Waveform.Noise);
		choicebox = new ChoiceBox<>(oList);
		choicebox.setValue(osc.getWaveform());
		setBackground(new Background(new BackgroundFill(Color.DARKSLATEGREY, CornerRadii.EMPTY, Insets.EMPTY)));
		
		active.setSelected(true);
		
		toneLabel.setMaxSize(MAX_LABEL_SIZE_X, MAX_LABEL_SIZE_Y);
		volumeLabel.setMaxSize(MAX_LABEL_SIZE_X - 20, MAX_LABEL_SIZE_Y);
		toneLabel.setMinSize(MAX_LABEL_SIZE_X, MAX_LABEL_SIZE_Y);
		volumeLabel.setMinSize(MAX_LABEL_SIZE_X - 20, MAX_LABEL_SIZE_Y);
		
		setHalignment(oscLabel, HPos.LEFT);
		setHalignment(toneText, HPos.LEFT);
		setHalignment(volumeText, HPos.LEFT);
		add(oscLabel,0,0, 2,1);
		add(holdFr,2,0, 3,1);
		add(choicebox,0,1, 2,1);
		add(active,2,1,2,1);
		add(toneText,0,2);
		add(volumeText,3,2);
		add(toneLabel,1,2);
		add(volumeLabel,4,2);
		
		
//		vBoxes[0].getChildren().addAll(choicebox,toneText);	
//		vBoxes[1].getChildren().addAll(oscLabel,toneLabel);	
//		for(VBox vB : vBoxes) {
//					
//			this.getChildren().add(vB);
//		}
		
	}
	
//	private void initialize() {
//		cb.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Oscillator.Waveform>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Waveform> observable, Waveform oldValue, Waveform newValue) {
//				osc.setWaveform(newValue);
//			}
//			
//		});	
//	}

}

package UI;

import Model.Synthesizer;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SettingsView extends  HBox{

	VBox vBOct,vBMod, vBVol;
	Button octavePlus, octaveMinus;
	Label modText, modValue, volLabel, volValue;
	
	Canvas cvMod, cvVol;
	GraphicsContext gcMod, gcVol;
	
	public SettingsView(Synthesizer synth) {
		setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
		setPrefSize(250, 298);
		vBOct = new VBox();
		vBMod = new VBox();
		vBVol = new VBox();
		octavePlus = new Button("Oktave +");
		octaveMinus = new Button("Oktave -");
		octaveMinus.setPadding(new Insets(15));
		octavePlus.setPadding(new Insets(15));
		
		vBOct.setSpacing(10);
		vBMod.setSpacing(10);
		vBVol.setSpacing(10);
		
		volValue = new Label(String.format("%.2f", synth.amplitude/100d));
		volLabel = new Label(" V O L");
		modText = new Label(" M O D");
		modValue = new Label("x 0");
		cvMod = new Canvas(50,100);
		gcMod = cvMod.getGraphicsContext2D();
		
		cvVol = new Canvas(50,100);
		gcVol = cvVol.getGraphicsContext2D();

		setPadding(new Insets(15));
		setSpacing(10);
		vBMod.getChildren().addAll(modValue, cvMod, modText);
		vBOct.getChildren().addAll(new Label(), octavePlus, octaveMinus);
		vBVol.getChildren().addAll(volValue, cvVol, volLabel);
		getChildren().addAll(vBOct, vBMod, vBVol);	}

}

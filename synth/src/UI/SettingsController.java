package UI;

import Model.Oscillator;
import Model.Synthesizer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Light.Point;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SettingsController extends HBox {
	
	VBox vBOct,vBMod, vBVol;
	Button octavePlus, octaveMinus;
	Label modText, modValue, volLabel, volValue;
	
	Synthesizer synth;
	private Point mouseClickedLocation = new Point();
	Canvas cvMod, cvVol;
	GraphicsContext gcMod, gcVol;
	MainView mView;
	
	public SettingsController(MainView mainView, Synthesizer synth) {
		this.synth = synth;
		this.mView = mainView;
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
		drawCont();
		
		cvVol = new Canvas(50,100);
		gcVol = cvVol.getGraphicsContext2D();
		drawCurVol();
		
		initialize();
		setPadding(new Insets(15));
		setSpacing(10);
		vBMod.getChildren().addAll(modValue, cvMod, modText);
		vBOct.getChildren().addAll(new Label(), octavePlus, octaveMinus);
		vBVol.getChildren().addAll(volValue, cvVol, volLabel);
		getChildren().addAll(vBOct, vBMod, vBVol);
	}

	private void drawCurVol() {
		gcVol.restore();
		gcVol.setFill(Color.CHOCOLATE);
		drawTriangle();
		gcVol.beginPath();
		gcVol.moveTo(0, cvVol.getHeight()*(100d - synth.amplitude) / 100d);
		gcVol.lineTo(cvVol.getWidth()*synth.amplitude / 100d, cvVol.getHeight()*(100d - synth.amplitude) / 100d);
		gcVol.lineTo(0, cvVol.getHeight());
//		gcVol.lineTo(0, 0);
		gcVol.closePath();
		gcVol.fill();
		gcVol.stroke();
		

		
	}

	private void drawTriangle() {
//		gcMod.setFill(Color.DARKSLATEGRAY);
		
		gcVol.clearRect(0, 0, cvVol.getWidth(), cvVol.getHeight());
		gcVol.setStroke(Color.BLACK);

		gcVol.beginPath();
		gcVol.moveTo(0, 0);
		gcVol.lineTo(0, cvMod.getHeight());
		gcVol.lineTo(cvVol.getWidth(), 0);
		gcVol.closePath();
		gcVol.stroke();
	}

	private void drawCont() {
		gcMod.restore();
		gcMod.setFill(Color.DARKSLATEGRAY);

		double midY = (cvMod.getHeight() / 2);
		
		gcMod.fillRect(0, 0, cvMod.getWidth(), cvMod.getHeight());
		gcMod.setStroke(Color.BLACK);

		gcMod.beginPath();
		gcMod.moveTo(0, cvMod.getHeight() - cvMod.getHeight() * (synth.modulation/Synthesizer.MAX_MOD));
		gcMod.lineTo(cvMod.getWidth(), cvMod.getHeight() - cvMod.getHeight() * (synth.modulation/Synthesizer.MAX_MOD));
		gcMod.stroke();
		
	}

	private void initialize() {
		
		cvMod.setOnMouseClicked(click -> {
			setCursor(Cursor.HAND);
			mouseClickedLocation.xProperty().set(click.getX());
			mouseClickedLocation.yProperty().set(click.getY());
		});
		
		cvMod.setOnMouseDragged(drag -> {
			
			
			if(synth.modulation + (mouseClickedLocation.getY() - drag.getY()) <= Synthesizer.MAX_MOD 
					&& synth.modulation + (mouseClickedLocation.getY() - drag.getY()) >= Synthesizer.MIN_MOD) {
				synth.modulation += (mouseClickedLocation.getY() - drag.getY());
			}
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					mView.wavVC.getWavView().drawWave();
					drawCont();
				}
			});
			mouseClickedLocation.xProperty().set(drag.getX());
			mouseClickedLocation.yProperty().set(drag.getY());
//			oView.osc.applyToneOffset();
			modValue.setText("x" + String.format("%3h",synth.modulation));
		});
		
		cvVol.setOnMouseClicked(click -> {
			setCursor(Cursor.HAND);
			mouseClickedLocation.xProperty().set(click.getX());
			mouseClickedLocation.yProperty().set(click.getY());
		});
		
		cvVol.setOnMouseDragged(drag -> {
			
			
			if(synth.amplitude + (mouseClickedLocation.getY() - drag.getY()) <= Synthesizer.MAX_AMP 
					&& synth.amplitude + (mouseClickedLocation.getY() - drag.getY()) >= Synthesizer.MIN_AMP) {
				synth.amplitude += (mouseClickedLocation.getY() - drag.getY());
			}
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					mView.wavVC.getWavView().drawWave();
					volValue.setText(String.format("%.2f", synth.amplitude/100d));
					drawCurVol();
					}
			});
			mouseClickedLocation.xProperty().set(drag.getX());
			mouseClickedLocation.yProperty().set(drag.getY());
//			oView.osc.applyToneOffset();
		});
		
		octavePlus.setOnMouseClicked(click -> {
			if (synth.getOctave() < Synthesizer.MAX_OKTAVE-1) {
				System.out.println(synth.getOctave() +1);
				synth.setOctave(synth.getOctave() +1);
			}
		});
		
		octaveMinus.setOnMouseClicked(click -> {
			if (synth.getOctave() > Synthesizer.MIN_OKTAVE) {
				synth.setOctave(synth.getOctave() -1);
			}
		});
		
		modValue.setOnMouseClicked(click -> {
			setCursor(Cursor.HAND);
			mouseClickedLocation.xProperty().set(click.getX());
			mouseClickedLocation.yProperty().set(click.getY());
		});

		modValue.setOnMouseReleased(release -> {
			setCursor(Cursor.DEFAULT);
		});
		
		modValue.setOnMouseDragged(drag -> {
			if(mouseClickedLocation.getY() != drag.getY()) {
				boolean movingUp = mouseClickedLocation.getY() - drag.getY() > 0;
				if ( movingUp && synth.modulation < Synthesizer.MAX_MOD-10) {
					synth.modulation +=10;
				} else if (!movingUp && synth.modulation > -Synthesizer.MAX_MOD+10) {
					synth.modulation-=10;
				}
			}
			
			
			mouseClickedLocation.xProperty().set(drag.getX());
			mouseClickedLocation.yProperty().set(drag.getY());
			mView.wavVC.getWavView().drawWave();	
			modValue.setText("x"+synth.modulation);

		});
	}

	
	

}

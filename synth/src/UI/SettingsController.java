package UI;

import Model.Oscillator;
import Model.Synthesizer;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
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

public class SettingsController  {
	
Synthesizer synth;
	private Point mouseClickedLocation = new Point();

	MainView mView;
	SettingsView setView;
	
	public SettingsController(MainView mainView, Synthesizer synth) {
		this.synth = synth;
		this.mView = mainView;
		setView = new SettingsView(synth);
		
		drawCurVol();
		drawCont();
		
		initialize();
	}

	private void drawCurVol() {
		setView.gcVol.restore();
		setView.gcVol.setFill(Color.CHOCOLATE);
		drawTriangle();
		setView.gcVol.beginPath();
		setView.gcVol.moveTo(0, setView.cvVol.getHeight()*(100d - synth.amplitude) / 100d);
		setView.gcVol.lineTo(setView.cvVol.getWidth()*synth.amplitude / 100d, setView.cvVol.getHeight()*(100d - synth.amplitude) / 100d);
		setView.gcVol.lineTo(0, setView.cvVol.getHeight());
//		gcVol.lineTo(0, 0);
		setView.gcVol.closePath();
		setView.gcVol.fill();
		setView.gcVol.stroke();
		

		
	}

	private void drawTriangle() {
//		gcMod.setFill(Color.DARKSLATEGRAY);
		
		setView.gcVol.clearRect(0, 0, setView.cvVol.getWidth(), setView.cvVol.getHeight());
		setView.gcVol.setStroke(Color.BLACK);

		setView.gcVol.beginPath();
		setView.gcVol.moveTo(0, 0);
		setView.gcVol.lineTo(0, setView.cvVol.getHeight());
		setView.gcVol.lineTo(setView.cvVol.getWidth(), 0);
		setView.gcVol.closePath();
		setView.gcVol.stroke();
	}

	private void drawCont() {
		setView.gcMod.restore();
		setView.gcMod.setFill(Color.DARKSLATEGRAY);

		double midY = (setView.cvMod.getHeight() / 2);
		
		setView.gcMod.fillRect(0, 0, setView.cvMod.getWidth(), setView.cvMod.getHeight());
		setView.gcMod.setStroke(Color.BLACK);

		setView.gcMod.beginPath();
		setView.gcMod.moveTo(0, setView.cvMod.getHeight() - setView.cvMod.getHeight() * (synth.modulation/synth.MAX_MOD));
		setView.gcMod.lineTo(setView.cvMod.getWidth(), setView.cvMod.getHeight() - setView.cvMod.getHeight() * (synth.modulation/synth.MAX_MOD));
		setView.gcMod.stroke();
		
	}

	private void initialize() {
		
		setView.cvMod.setOnMouseClicked(click -> {
			setView.setCursor(Cursor.HAND);
			mouseClickedLocation.xProperty().set(click.getX());
			mouseClickedLocation.yProperty().set(click.getY());
		});
		
		setView.cvMod.setOnMouseDragged(drag -> {
			
			
			if(synth.modulation + (mouseClickedLocation.getY() - drag.getY()) <= synth.MAX_MOD 
					&& synth.modulation + (mouseClickedLocation.getY() - drag.getY()) >= synth.MIN_MOD) {
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
			setView.modValue.setText("x" + String.format("%3h",synth.modulation));
		});
		
		setView.cvVol.setOnMouseClicked(click -> {
			setView.setCursor(Cursor.HAND);
			mouseClickedLocation.xProperty().set(click.getX());
			mouseClickedLocation.yProperty().set(click.getY());
		});
		
		setView.cvVol.setOnMouseDragged(drag -> {
			
			
			if(synth.amplitude + (mouseClickedLocation.getY() - drag.getY()) <= Synthesizer.MAX_AMP 
					&& synth.amplitude + (mouseClickedLocation.getY() - drag.getY()) >= Synthesizer.MIN_AMP) {
				synth.amplitude += (mouseClickedLocation.getY() - drag.getY());
			}
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					mView.wavVC.getWavView().drawWave();
					setView.volValue.setText(String.format("%.2f", synth.amplitude/100d));
					drawCurVol();
					}
			});
			mouseClickedLocation.xProperty().set(drag.getX());
			mouseClickedLocation.yProperty().set(drag.getY());
//			oView.osc.applyToneOffset();
		});
		
		setView.octavePlus.setOnMouseClicked(click -> {
			if (synth.getOctave() < Synthesizer.MAX_OKTAVE-1) {
				System.out.println(synth.getOctave() +1);
				synth.setOctave(synth.getOctave() +1);
			}
		});
		
		setView.octaveMinus.setOnMouseClicked(click -> {
			if (synth.getOctave() > Synthesizer.MIN_OKTAVE) {
				synth.setOctave(synth.getOctave() -1);
			}
		});
		
		setView.modValue.setOnMouseClicked(click -> {
			setView.setCursor(Cursor.HAND);
			mouseClickedLocation.xProperty().set(click.getX());
			mouseClickedLocation.yProperty().set(click.getY());
		});

		setView.modValue.setOnMouseReleased(release -> {
			setView.setCursor(Cursor.DEFAULT);
		});
		
		setView.modValue.setOnMouseDragged(drag -> {
			if(mouseClickedLocation.getY() != drag.getY()) {
				boolean movingUp = mouseClickedLocation.getY() - drag.getY() > 0;
				if ( movingUp && synth.modulation < synth.MAX_MOD) {
					synth.modulation ++;
				} else if (!movingUp && synth.modulation > synth.MIN_MOD) {
					synth.modulation--;
				}
			}
			
			
			mouseClickedLocation.xProperty().set(drag.getX());
			mouseClickedLocation.yProperty().set(drag.getY());
			mView.wavVC.getWavView().drawWave();	
			setView.modValue.setText("x"+synth.modulation);

		});
	}

	public Node getView() {
		return setView;
	}

	
	

}

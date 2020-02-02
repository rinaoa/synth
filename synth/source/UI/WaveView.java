package UI;


import java.util.function.Function;

import Model.Oscillator;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class WaveView extends Pane{
	
	Oscillator[] oscillators;
	final Canvas canvas;
	final int PAD = 20;
	double midY;
	final GraphicsContext gc;
	
	public WaveView(Oscillator[] oscillators) {
		this.oscillators = oscillators;
		this.canvas = new Canvas(300,200);
		gc = canvas.getGraphicsContext2D();
		midY = (canvas.getHeight()/2);
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null
				, new BorderWidths(1))));
		drawWave();		
		getChildren().addAll(canvas);
	}
	
	public void drawWave() {
		
		gc.restore();
		
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		gc.beginPath();
		gc.moveTo(PAD, midY);
		gc.lineTo(canvas.getWidth() - PAD, midY);
		gc.setStroke(Color.BLACK);
//		gc.closePath();
		gc.stroke();
		
		gc.beginPath();
		gc.moveTo(PAD, PAD);
		gc.lineTo(PAD, canvas.getHeight() - PAD);
		gc.stroke();
		
		int numSamples = (int) canvas.getWidth() - PAD * 2;
		double [] mixedSamples = new double[numSamples];
		for (Oscillator o: oscillators) {
			double[] samples = o.getSampleWaveform(numSamples);
			for (int i = 0; i<samples.length; ++i) {
				mixedSamples[i] += (samples[i] / oscillators.length);
			}
		}
		Function<Double, Integer> sampleToCoord = sample -> (int) (midY + sample * (midY - PAD));
		gc.beginPath();
		gc.moveTo(PAD, midY);
		for (int i = 0; i < numSamples-1;++i) {
//			int nextY = i == numSamples - 1 ? sampleToCoord.apply(mixedSamples[i]) : sampleToCoord.apply(mixedSamples[i + 1]);
//			int overNextY = i == numSamples - 2 ? sampleToCoord.apply(mixedSamples[nextY]) : sampleToCoord.apply(mixedSamples[i + 2]);
//			gc.bezierCurveTo(PAD + i, sampleToCoord.apply(mixedSamples[i]), PAD + i + 1, nextY, PAD + i + 2, overNextY);
			gc.lineTo(PAD + i, sampleToCoord.apply(mixedSamples[i]));
		}
		
		gc.stroke();
	}
}

package UI;


import java.util.function.Function;

import com.sun.swing.internal.plaf.synth.resources.synth;

import Model.Oscillator;
import Model.Synthesizer;
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
	Synthesizer synth;
	final Canvas canvas;
	final int PAD = 20;
	double midY;
	final GraphicsContext gc;
	
	public WaveView(Synthesizer synth) {
		this.synth = synth;
		this.oscillators = synth.getOscillators();
		this.canvas = new Canvas(495, 298);
		gc = canvas.getGraphicsContext2D();
		midY = (canvas.getHeight()/2);
		setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null
				, new BorderWidths(1))));
		drawWave();		
		getChildren().addAll(canvas);
	}
	
	public void drawWave() {
		gc.setFill(new Color(0.5, 0.6, 0.7, .3));

		gc.clearRect(0,0,495,298);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setLineWidth(1.);
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
		gc.setLineWidth(1.5);
		gc.setStroke(Color.CORAL);

		int numSamples = (int) canvas.getWidth() - PAD * 2;
		double [] mixedSamples = new double[numSamples];
		for (Oscillator o: oscillators) {
			double[] samples = o.getSampleWaveform(numSamples);
			for (int i = 0; i<samples.length; ++i) {
				mixedSamples[i] += (samples[i]);
			}
		}
		if(synth.modulation!= 0) {
			for (int i = 1; i< mixedSamples.length; i++) {
//				mixedSamples[i] = (mixedSamples[i] + synth.modOsc.getSampleWaveform(numSamples)[i]) / oscillators.length + 1;
				mixedSamples[i] += (((synth.amplitude/100d) * Math.cos(oscillators[0].getKeyFrequency() * oscillators[0].waveTableIndex + synth.modulation * Math.cos(synth.modOsc.getNextSample()))) / oscillators.length + 1) - 1;
			}
		} else {
			for (int i = 1; i< mixedSamples.length; i++) {
				mixedSamples[i] /= oscillators.length;
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

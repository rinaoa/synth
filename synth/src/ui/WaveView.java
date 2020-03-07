package ui;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;

import org.newdawn.slick.openal.WaveData;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.FFT;

public class WaveView extends AnchorPane{
	Label lbl;
	Canvas canvas;
	public GraphicsContext gc;
	private final int PAD = 20;
	private int midY;
	
	public WaveView() {
		try {
		this.setBackground(new Background(new BackgroundFill(Color.rgb(52, 82, 101, 0.3), CornerRadii.EMPTY, Insets.EMPTY)));
    	canvas = new Canvas(497, 300);
    	gc = canvas.getGraphicsContext2D();
    	midY = (int) canvas.getHeight() / 2;
    	gc.setFill(Color.GREEN);
    	gc.translate(PAD, midY);
		gc.strokeOval(0, 0, 10, 10);
		
//		double[] res = getMagnitudes("assets/samples/Cello A2.wav");
//		drawStuff(res);

		getChildren().add(canvas);
		setWidth(canvas.getWidth());
		setHeight(canvas.getHeight());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double[] getMagnitudes(String path) {
	    // call the fft and transform the complex numbers
	    FFT fft = new FFT(path);
	    double[] amplitudes = fft.fft();
	    // end call the fft and transform the complex numbers

	    // even indexes (0,2,4,6,...) are real parts
	    // odd indexes (1,3,5,7,...) are img parts
	    int indexSize = amplitudes.length / 2;

	    // FFT produces a transformed pair of arrays where the first half of the
	    // values represent positive frequency components and the second half
	    // represents negative frequency components.
	    // we omit the negative ones
	    int positiveSize = indexSize / 2;

	    double[] mag = new double[positiveSize];
	    for (int i = 0; i < indexSize; i += 2) {
	        mag[i / 2] = Math.sqrt(amplitudes[i] * amplitudes[i] + amplitudes[i + 1] * amplitudes[i + 1]);
	    }

	    return mag;
	}
	
	private void drawStuff(double[] res) {
		Platform.runLater(() ->{
			gc.setFill(Color.GREEN);
	        gc.setStroke(Color.RED);
	        gc.setLineWidth(1);
	        gc.strokeLine(0, 0, canvas.getWidth() - PAD, 0);
	        gc.setStroke(Color.BLUE);

			for(int i = 1; i < res.length - PAD; i++) {
 				gc.strokeLine(i-1, - res[i-1]*100, i, - res[i]*100);
				System.out.print(res[i]*100 + "\n");
			}
		});
	}
}

package Model;

import java.util.Random;

import com.sun.javafx.image.impl.ByteIndexed.Getter;

import Model.Synthesizer.AudioInfo;
import javafx.beans.property.SimpleObjectProperty;

public class Oscillator {
	
	public static final int TONE_OFFSET_LIMIT  = 2000;
	public static final int MAX_VOLUME = 100;
	public static final int MIN_VOLUME = 0;
	private Waveform waveForm;
//	private int wavePos;
//	private double frequency;
	public int toneOffset;
	public int volume=100;
	
	private double keyFrequency;
	
	private int waveTableStepSize;
	public int waveTableIndex;
	int lastVol;
	
	public boolean holdFrequency = false;
	
	public Oscillator() {
//		waveForm = new Waveform();
//		waveForm=new SimpleObjectProperty<>();
		waveForm = Waveform.Sine;
	}
	

	
	public void setWaveform(Waveform wForm) {
		
		this.waveForm = wForm;
		System.out.println(getWaveform());
	}

	public Waveform getWaveform() {
		return waveForm;
	} 
	
//	public double nextSample() {
//		
//		//change frequency
//		System.out.println(wavePos);
//		
//			
//	}
	
	public double getNextSample() {
//		System.out.println(waveTableIndex);
		double sample = waveForm.getSamples()[waveTableIndex] * (volume/100d);
		waveTableIndex = (waveTableIndex + waveTableStepSize) % Waveform.SIZE;
		return sample;
	}
	
	public void applyToneOffset(){
//		System.out.println("Tone OFFSET: "+toneOffset);
		waveTableStepSize = (int) ((Waveform.SIZE * (keyFrequency * Math.pow(2, (toneOffset/1000d)))) / AudioInfo.SAMPLE_RATE);
//		System.out.println("Step-Size:   "+waveTableStepSize);
//		frequency = keyFrequency * Math.pow(2, toneOffset);
	}
	
	
	public static double frequencyToAngularFrequency(double freq) {
		return 2 * Math.PI * freq;
	}
	
//	public double getFrequency() {
//		return frequency;
//	}
//	
//	public void setFrequency(double freq) {
//		this.frequency=freq;
//	}
	
	public double[] getSampleWaveform(int numSamples) {
		double [] samples = new double[numSamples];
		double frequency = 1.0 / (numSamples / (double) AudioInfo.SAMPLE_RATE) * 3.0;
		int index = 0;
		int stepSize = (int) (Waveform.SIZE * (frequency * Math.pow(2, (toneOffset/1000d))) / AudioInfo.SAMPLE_RATE);
		for(int i = 0; i < numSamples; ++i){
			samples[i] = - (Synthesizer.amplitude / 100d) * waveForm.getSamples()[index] * (volume/100d);
			index = (index + stepSize) % Waveform.SIZE;
		}
		return samples;
	}
	
	public void setKeyFrequency(double c) {
		if (!holdFrequency) keyFrequency = c;
		applyToneOffset();
//		System.out.println(keyFrequency);
	}
	
	public double getKeyFrequency() {
		return keyFrequency;
	}



	public void setActive(Boolean active) {
		
		if (!active) {
			lastVol=volume;
			volume = 0;
		} else volume = lastVol;
	}
}

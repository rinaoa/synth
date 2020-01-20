package model;

import javafx.beans.property.SimpleObjectProperty;

public class Synthesizer {
	public SampleManager sampleManager;
	public int octave = 0;
	public SimpleObjectProperty<Sample> currentSample;
	
	public Synthesizer() {
		this.sampleManager = new SampleManager();
		this.currentSample = new SimpleObjectProperty<>(new Sample("assets/samples/bounce.wav"));
	}
	
	public int getOctave() {
		return octave;
	}
	
	public SimpleObjectProperty<Sample> currentSampleProperty(){
		return currentSample;
	}
	
	public void setSample(Sample s){
		currentSample.setValue(s);
		System.out.print("\nSample resetted" + s.getName() + "\n");
	}
}

package model;

public class Synthesizer {
	public SampleManager sampleManager;
	public int octave = 0;
	
	public Synthesizer() {
		this.sampleManager = new SampleManager();
	}
	
	public int getOctave() {
		return octave;
	}
}

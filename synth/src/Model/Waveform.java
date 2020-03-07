package Model;

import java.util.Random;

import Model.Synthesizer.AudioInfo;

public enum Waveform {
	Sine, Square, Saw, Triangle, Noise;
	
	static final int SIZE = 8192;
	
	private final float[] samples = new float[SIZE];
	
	protected final static Random rdm = new Random();
	
	private static float amplitude = 1.0f;
	
	static {
		final double FUND_FREQ = 1d/ (SIZE / (double) Synthesizer.AudioInfo.SAMPLE_RATE);
		for (int i=0; i < SIZE; i++) {
			double t = (double) i / AudioInfo.SAMPLE_RATE;
			double tDivP = t / (1f / FUND_FREQ);
			
			Sine.samples[i] = (float) (amplitude * Math.sin(Oscillator.frequencyToAngularFrequency(FUND_FREQ) * t));
			Square.samples[i] = Math.signum(Sine.samples[i]);
			Saw.samples[i] = (float) (amplitude * 2d * (tDivP - Math.floor(0.5 + tDivP)));
			Triangle.samples[i] = (float) (2d * Math.abs(Saw.samples[i]) - 1f);
			Noise.samples[i] = (float) (amplitude *rdm.nextDouble());
		}
	}

	public float[] getSamples() {
		return samples;
	}
	
	public float getAmplitude() {
		return amplitude;
	}
	
	public void setAmplitude(float a) {
		amplitude = a;
	}
}

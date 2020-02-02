package Model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.ByteBuffer;
import java.nio.ShortBuffer;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.openal.WaveData;

public class Synthesizer {
	
	public final static double C = 	1.;
	public final static double CIS = 	1.06;
	public final static double D = 	1.12;
	public final static double DIS = 	1.19;
	public final static double E = 	1.26;
	public final static double F = 	1.33;
	public final static double FIS = 	1.41;
	public final static double G = 	1.5;
	public final static double GIS = 	1.59;
	public final static double A = 	1.68;
	public final static double AIS = 	1.78;
	public final static double H = 	1.89;
	public final static double C2 = 	2*C;
	public final static double CIS2 = 	2*CIS;
	public final static double D2 = 	2*D;
	public final static double DIS2 = 	2*DIS;
	public final static double E2 = 	2*E;
	public final static double F2 = 	2*F;
	public final static double FIS2 = 	2*FIS;
	public final static double G2 = 	2*G;
	public final static double GIS2 = 	2*GIS;
	public final static double A2 = 	2*A;
	public final static double AIS2 = 	2*AIS;
	public final static double H2 = 	2*H;
	public final static double C3 = 	2*C2;

	private static final double[] OCTAVES = {0.25,0.5,1.,2.,4.,8.};
	
	public static final double BASE_FREQUENCY = 261.63;
	
	public static final int MAX_OKTAVE = OCTAVES.length;
	public static final int MIN_OKTAVE = 0;

	public static final int MAX_PITCH = 1000;
	public int pitch =0;

	public boolean shouldGenerate = false;
//	private int wavePos = 0;
	private int octave=0;
	
	private Oscillator[] oscillators = {new Oscillator(),new Oscillator(),new Oscillator()};
//	public Oscillator osc2;
//	public Oscillator osc3;	
	public final AudioThread thread;
	
	public Synthesizer() {
		
//		thread = new AudioThread(() -> 
//		{
//			short[] s = null;
//			try {
//				s = initWavStream();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			return s;
//		});

		thread = new AudioThread(() -> 
		{
			short[] synth = initStream();
			short[] wav = null;
			try {
				wav = initWavStream();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if ( wav != null && synth != null) {
				for(int i = 0; i < synth.length; i++) {
					synth[i] = (short) ((synth[i] + wav[i]) / 2);
				}
			}
			return synth;
		});
	}

	public Oscillator getOsc(int n) {
		return oscillators[n];
	}
	
	private short[] initWavStream() throws FileNotFoundException {
		if (!shouldGenerate) 
		{
			return null;
		}
		WaveData sample = WaveData.create(new BufferedInputStream(new FileInputStream("Cello_A2.wav")));
		ShortBuffer buffer = sample.data.asShortBuffer();
		short[] data = new short[buffer.limit()];
		for(int i = 0; i< buffer.limit();i++) {
			data[i] = buffer.get(i);
//			System.out.println(buffer.get(i));
		}
		return data;
	}
	
	private short[] initStream() {
		// TODO Auto-generated method stub
		if (!shouldGenerate) 
		{
			return null;
		}
		short[] s = new short[AudioThread.BUFFER_SIZE];
		for (int i=0; i < AudioThread.BUFFER_SIZE; ++i) 
		{
			double d = 0;
			d += getOsc(0).getNextSample();
			d += getOsc(1).getNextSample();			
			d += getOsc(2).getNextSample();
			
			d /= oscillators.length;
			
			s[i] = (short) (Short.MAX_VALUE * d);
//			System.out.println("| "+ s[i] +" :| "+ i);
//			myFiltcer1(s);
//			System.out.println("FILTER         | "+ s[i] +" :| "+ i);
//			s[i] = (short) (Short.MAX_VALUE * Math.sin((2 * Math.PI * 440) / AudioInfo.SAMPLE_RATE * wavePos++));
		}
//		System.out.println(getOsc(0).getWaveform()+", "+getOsc(1).getWaveform()+", "+getOsc(1).getWaveform());
		
		
		
		return s; 
	}

	private void myFilter1(short[] data) {
		for(int i=0; i < data.length;i++) {
			data[i]=(short) (data[i] * 0.9f);
		}
	}

	public static class AudioInfo {
		
		public static final int SAMPLE_RATE = 44100;
		
	}

	public int getOctave() {
		// TODO Auto-generated method stub
		return octave;
	}
	
	public void setOctave(int o) {
		octave = o;
	}

	public void setWaveformOsc(int i, Waveform newValue) {
		oscillators[i].setWaveform(newValue);
	}

	public void setKeyFrequency(double c) {
		for(Oscillator o : oscillators) {
			o.setKeyFrequency(BASE_FREQUENCY * OCTAVES[getOctave()] * c);
		}
	}
	
	public void setPitch() {
		thread.setPitch((pitch));
	}
	
	public Oscillator[] getOscillators() {
		return oscillators;
	}
	
}

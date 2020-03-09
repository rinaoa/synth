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
	public double modFrequency= BASE_FREQUENCY;
	
	public static final int MAX_OKTAVE = OCTAVES.length;
	public static final int MIN_OKTAVE = 0;

	public static final int MAX_PITCH = 1000;
	public int pitch =0;
	
	public static double amplitude = 50;
	public static final double MAX_AMP = 100;
	public static final double MIN_AMP = 0;
	
	public static final double MAX_MOD = 100;
	public static final double MIN_MOD = 0;
	public static int modulation = 0;

	public boolean shouldGenerate = false;
//	private int wavePos = 0;
	private int octave=0;
	
	public Oscillator modOsc = new Oscillator();
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
		
		modOsc.setKeyFrequency(440);
		thread = new AudioThread(() -> 
		{
			short[] synth = initStream();
			
			return synth;
		});
		
//		thread = new AudioThread(() -> 
//		{
//			short[] synth = initStream();
//			short[] wav = null;
//			try {
//				wav = initWavStream();
//			} catch (FileNotFoundException e1) {
//				e1.printStackTrace();
//			}
//			if ( wav != null && synth != null) {
//				for(int i = 0; i < synth.length; i++) {
//					synth[i] = (short) ((synth[i] + wav[i]) / 2);
//				}
//			}
//			return synth;
//		});
	}

	public Oscillator getOsc(int n) {
		return oscillators[n];
	}
	
	private short[] initWavStream() throws FileNotFoundException {
		if (!shouldGenerate) 
		{
			return null;
		}
		WaveData sample = WaveData.create(new BufferedInputStream(new FileInputStream("assets/Cello_A2.wav")));
		ShortBuffer buffer = sample.data.asShortBuffer();
		short[] data = new short[buffer.limit()];
		for(int i = 0; i< buffer.limit();i++) {
			data[i] = buffer.get(i);
		}
		return data;
	}
	
	private short[] initStream() {
		if (!shouldGenerate) 
		{
			return null;
		}
		short[] s = new short[AudioThread.BUFFER_SIZE];
		for (int i=0; i < AudioThread.BUFFER_SIZE; ++i) 
		{
			double d = 0;
//			d += addOsc(getOsc(0).getKeyFrequency(), getOsc(1).getKeyFrequency());
//			d += addOsc(getOsc(1).getKeyFrequency(), getOsc(2).getKeyFrequency());
//			d += addOsc(getOsc(0).getKeyFrequency(), getOsc(2).getKeyFrequency());
//			
			d += getOsc(0).getNextSample();			
			d += getOsc(1).getNextSample();			
			d += getOsc(2).getNextSample();
			if (modulation != 0) {
				d +=  ((amplitude/100d) * Math.cos(oscillators[0].getKeyFrequency() * oscillators[0].waveTableIndex + modulation * Math.cos(modOsc.getNextSample()))) / 2;
				
			}
			
			d /= oscillators.length;
					
			System.out.println(String.format("%.5f", d));
			s[i] = (short) (amplitude/100d * (Short.MAX_VALUE * d));
		}		
		return s; 
	}

	private double addOsc(double root, double aim) {
		
		return (amplitude/100d) * Math.cos(root * oscillators[0].waveTableIndex + modulation * Math.cos(modOsc.waveTableIndex * aim));
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
		return octave;
	}
	
	public void setOctave(int o) {
		if (o<OCTAVES.length) {
			octave = o;
			if (o < MAX_OKTAVE - 2) {
				modOsc.setKeyFrequency(BASE_FREQUENCY * OCTAVES[getOctave() + 2]);
			}
		}
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

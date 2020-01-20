package model;

import static org.lwjgl.openal.AL10.*;

import static org.lwjgl.openal.ALC10.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.function.Supplier;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;

import model.wave.WaveData;
public class AudioThread extends Thread{
	Sample sss;
	WaveData sample;

	private final Supplier<Sample> bufferSupplier;
	private int buffer;
	private final long device = alcOpenDevice(alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER));
	private final long context = alcCreateContext(device, new int [1]);
	private int source;

	private boolean running;
	private boolean paused = true;
	private boolean killThread = false;
	
	public AudioThread(Supplier<Sample> supplier) {
			this.bufferSupplier = supplier;
			
			alcMakeContextCurrent(context);
			AL.createCapabilities(ALC.createCapabilities(device));
			source = alGenSources();
		try {
			sample = WaveData.create(new BufferedInputStream(new FileInputStream("assets/samples/bounce.wav")));
			bufferSamples(sample);
			
			catchInternalException();
			start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void run() {
		try {
		while(!running) {	
			while(paused) {
				AL10.alSourceStop(source);
				wait();
			}
			
			int processedBuffs = alGetSourcei(source, AL_BUFFERS_PROCESSED);
			for(int i = 0; i < processedBuffs; i++) {
				sss=bufferSupplier.get();
				sample = sss.getWaveData();
				if(sample == null) {
					paused = true; 
					break;
				}else {
					System.out.print("\nPLAYING: " + sss.getName()+ "\n");
				}
				alDeleteBuffers(alSourceUnqueueBuffers(source));
				buffer = alGenBuffers();
				bufferSamples(sample);
			}
			
			if(alGetSourcei(source, AL_SOURCE_STATE) != AL_PLAYING) {
				alSourcePlay(source);
			}
			catchInternalException();
		}
		
		if(killThread) {
			alDeleteSources(source);
			alDeleteBuffers(buffer);
			alcDestroyContext(context);
			alcCloseDevice(device);
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	public void setPitch(float d) {
		AL10.alSourcef(source, AL_PITCH, d);
	}
	public boolean isNotKilled(){
		return !killThread;
	}
	public synchronized void triggerPlayback() {
		paused = false; 
		
		notify();
	}
	public void pause() {
		paused = true;
	}
	public void close() {
		killThread = true;
	}
	
	private void bufferSamples(WaveData sample) {
		alBufferData(buffer, sample.format, sample.data, sample.samplerate); //fill the empty buffer(audio) with frequencies
		alSourceQueueBuffers(source, buffer); // load the audio(buffer) in the player queue
	}
	
	private void catchInternalException() {
		int err = alcGetError(device);
		if (err != ALC_NO_ERROR) {
			throw new RuntimeException();
		}
	}
}

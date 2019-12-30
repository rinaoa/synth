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
//idea > pitch the sound for every button
public class AudioThread extends Thread{

	public static final int BUFFER_SIZE = 512;

	private final Supplier<WaveData> bufferSupplier;
	private int buffer;
	private final long device = alcOpenDevice(alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER));
	private final long context = alcCreateContext(device, new int [1]);
	private final int source;

	private String waveFileName;
	private boolean running;
	private boolean paused;
	private boolean killThread = false;
	
	public AudioThread(Supplier<WaveData> supplier) {
			this.bufferSupplier = supplier;
			this.waveFileName = "assets/Cello_A2.wav";
			
			alcMakeContextCurrent(context);
			AL.createCapabilities(ALC.createCapabilities(device));
			source = alGenSources();
		try {
			WaveData waveFile = WaveData.create(new BufferedInputStream(new FileInputStream(waveFileName)));
			bufferSamples(waveFile);
			
			alSourcePlay(source);
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
				alSourcePause(source);
				alSourceRewind(source);
				//TODO start from the begin
				wait();
			}
			
			int processedBuffs = alGetSourcei(source, AL_BUFFERS_PROCESSED);
			for(int i = 0; i < processedBuffs; i++) {
				System.out.print("..\n");
				WaveData sample = bufferSupplier.get();
				if(sample == null) {
					paused = true; 
					break;
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
	public void setWaveFile(String path) {
		
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
		alBufferData(buffer, sample.format, sample.data, sample.samplerate);
		alSourceQueueBuffers(source, buffer);
	}
	
	private void catchInternalException() {
		int err = alcGetError(device);
		if (err != ALC_NO_ERROR) {
			throw new RuntimeException();
		}
	}
}

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
import org.newdawn.slick.openal.WaveData;

import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class AudioThread extends Thread{
	
	Sample sss;
	WaveData sample;
	GraphicsContext gc;
	volatile int x = 0;
	volatile int prevY = -1111111111;
	
	private final Supplier<Sample> bufferSupplier;
	private int buffer;
	private final long device = alcOpenDevice(alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER));
	private final long context = alcCreateContext(device, new int [1]);
	private int source;

	private boolean running;
	private boolean paused = true;
	private boolean killThread = false;
	
	public AudioThread(Supplier<Sample> supplier, GraphicsContext g) {
			this.bufferSupplier = supplier;
			this.gc = g;
			
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
				x = 0;
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
//					System.out.print("\nPLAYING: " + sss.getName()+ "\n");
//					System.out.print("*" + sss.getWaveData().data.getShort() + "\n"); 
//					System.out.print("*****" + sss.getWaveData().data.remaining() + "\n"); 
//					drawStuff(sss.getWaveData().data.getShort());
				}

				alDeleteBuffers(alSourceUnqueueBuffers(source));
				buffer = alGenBuffers();
				bufferSamples(sample);
			}
			if(alGetSourcei(source, AL_SOURCE_STATE) != AL_PLAYING) {
				x = 500;
				alSourcePlay(source);
			}
			if(sss != null) {
				short ddd = bufferSupplier.get().getWaveData().data.get();
//				System.out.print("\n@@@@@@@ " + ddd);
				drawStuff(ddd);
			}
			
			System.out.print(">>>>>FREQ: " + alGetBufferi(buffer, AL_FREQUENCY)  + "\n"); 
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
	
	private void drawStuff(short y) {
		Platform.runLater(() ->{
	        if (x==500) {
//	        	gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
//	        	gc.clearRect(0, 0, gc.getCanvas().getWidth(), -gc.getCanvas().getHeight());
	        	//x = 0;
	        }else {
	        	if(prevY == -1111111111) {
	        		System.out.println("\n SETTTT" + y);
	        		prevY = y;
	        		System.out.println("\n RESETTTT" + y);
	        	}else {
	        		gc.setFill(Color.GREEN);
			        gc.setStroke(Color.RED);
			        gc.setLineWidth(1);
//			        int yy = map(y, -150, 150, min, max);
			        gc.strokeLine(x-20, prevY, x, y); 
			        System.out.println("\n Line drawn: ");
			        System.out.println(x-20 + "," + prevY + "-> " + x + "," + y);
			        prevY = y;
	        	}
	        	x += 20; 
	        }
		});
	}
}

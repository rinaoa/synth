package Model;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.openal.ALC10.*;

import java.util.function.Supplier;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.lwjgl.openal.ALC;

import UI.MainViewController;

public class AudioThread extends Thread {

	static final int BUFFER_SIZE = 256;
	static final int BUFFER_COUNT = 8;
	
	private final Supplier<short[]> bufferSupplier;
	private final int[] buffers= new int[BUFFER_COUNT];
	private final long device = alcOpenDevice(alcGetString(0, ALC_DEFAULT_DEVICE_SPECIFIER));
	private final long context = alcCreateContext(device, new int[1]);
	private final int source;
	private boolean closed;
	public boolean running=false;
	
	private int bufferIndex = 0;
	
	AudioThread(Supplier<short[]> bufferSupplier) {
		this.bufferSupplier = bufferSupplier;
		alcMakeContextCurrent(context);
		AL.createCapabilities(ALC.createCapabilities(device));
		source = alGenSources();
		for (int i=0; i < BUFFER_COUNT; i++) {
			bufferSamples(new short [0]);
		}
		alSourcePlay(source);
		catchInternalException();
		start();
	}
	
	@Override
	public synchronized void run() 
	{
		while (!closed) 
		{
			while (!running) 
			{
				try 
				{
					System.out.println("wait");
					AL10.alSourceStop(source);
					wait();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			int processedBuffers = alGetSourcei(source, AL_BUFFERS_PROCESSED);
			for (int i=0; i< processedBuffers; i++) 
			{
				short[] samples = bufferSupplier.get();
				System.out.println("GET!!!");
				if (samples == null) 
				{
					running = false;
					break;
				}
				
				alDeleteBuffers(alSourceUnqueueBuffers(source));
				buffers[bufferIndex] = alGenBuffers();
				
				bufferSamples(samples);
//				for (int j=0; j<samples.length;j++) {
//					System.out.println(samples[j]);
//				}
			}
			if (alGetSourcei(source, AL_SOURCE_STATE) != AL_PLAYING) 
			{
				System.out.println("play");
				alSourcePlay(source);
			}
			catchInternalException();
		}
		alDeleteSources(source);
		alDeleteBuffers(buffers);
		alcDestroyContext(context);
		alcCloseDevice(device);
	}
	
	public synchronized void triggerPlayback() {
		running = true;
		notify();
	}
	
	public void close() {
		closed = true;
		triggerPlayback();
	}
	
	public boolean isRunning() {
		return running;
	}
	
	private void bufferSamples(short[] samples) {
		int buf = buffers[bufferIndex++];
		alBufferData(buf, AL_FORMAT_MONO16, samples, Synthesizer.AudioInfo.SAMPLE_RATE);
		alSourceQueueBuffers(source,  buf);
		bufferIndex %= BUFFER_COUNT;
	}
	
	private void catchInternalException() {
		int err = alcGetError(device);
		if (err != ALC_NO_ERROR) {
			throw new OpenALException(err);
		}
	}
	
	public void setPitch(float d) {
		AL10.alSourcef(source, AL_PITCH, d);
	}
}

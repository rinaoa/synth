package model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import model.wave.WaveData;

public class Sample {
	String path;
	WaveData waveFile;
	
	public Sample(String p) {
		this.path = p;
		
		try {
			waveFile = WaveData.create(new BufferedInputStream( new FileInputStream(path)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return path.substring(path.lastIndexOf("/")+1); //crop till the last /
	}
	public WaveData getWaveData() {
		return waveFile;
	}
}

package model;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class SampleManager {
	public HashMap<String, Sample> samples = new HashMap<>();
	String user = System.getProperty("user.name");
	
	public SampleManager() {
		init();
	}
	
	//method to find all samples
	private void init() {
		
        Path dir = Paths.get("/Users/"+user+"/git/synth/synth/assets/samples");

        try { 
        	DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.wav"); 
            for (Path entry : stream) {
                Sample s = new Sample("/Users/"+user+"/git/synth/synth/assets/samples/" + entry.getFileName().toString());
                samples.put(entry.getFileName().toString(), s);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        try { 
        	DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.WAV"); 
            for (Path entry : stream) {
                Sample s = new Sample("/Users/"+user+"/git/synth/synth/assets/samples/" + entry.getFileName().toString());
                samples.put(entry.getFileName().toString(), s);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public HashMap<String, Sample> getSampleList(){
		return samples;
	}
}
